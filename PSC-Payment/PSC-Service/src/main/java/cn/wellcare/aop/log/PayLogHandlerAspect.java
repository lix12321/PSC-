package cn.wellcare.aop.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.OpType;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.entity.log.PayLog;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.model.modules.log.PayLogModel;

/**
 * 支付日志切面
 * 
 * @author zhaihl
 *
 */
@Component
@Aspect
public class PayLogHandlerAspect {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private PayLogModel payLogModel;
	private final ThreadLocal<Map<String, Object>> currentObject = new ThreadLocal<>();

	@Pointcut("@annotation(cn.wellcare.core.constant.annotations.PaymentLog)")
	public void payHandler() {
	}

	@AfterReturning(value = "payHandler() && args(param)", returning = "result")
	public void payLogHandler(JoinPoint joinPoint, Map<String, Object> param, Object result) {
		String invokeClass = joinPoint.getTarget().getClass().getName();
		this.log.debug("支付日志切面，目标类：" + invokeClass);
		try {
			String sign = getCurrentRequestSignature();

			// TODO 配合token、拦截器
			// 重复校验
			if (this.currentObject.get() != null && this.currentObject.get().get(sign) != null) {
				log.warn("释放当前签名引用");
				this.currentObject.remove();

				throw new BusinessException("重复请求");
			}
			MethodSignature ms = (MethodSignature) joinPoint.getSignature();
			// 入参value
			Method method = ms.getMethod();

			// 方法的注解对象
			PaymentLog anno = method.getAnnotation(PaymentLog.class);
			PayLogHandler handler = anno.value();
			OpType type = anno.type();

			String payType = PaymentType.getPaymentCodeByNameOrCode((String) param.get(BaseParam.PAY_TYPE));
			if (payType != null && PaymentType.ACCRECHARGE.getPaymentCode().equals(payType)) {
				// 充值时类型
				type = OpType.RECHARGE;

				if (CommonUtils.isNull(param.get(BaseParam.RECHARGE_FLAG))) {
					// 支付时，使用了嵌套注解，忽略目标执行类，使用账户充值
					// if (!AccRechargeModel.class.getName().equals(invokeClass)) {
					// log.debug("账户充值嵌套注解，忽略执行，目标类：" + invokeClass);
					// return;
					// }

				}
			}
			PayOrder order = (PayOrder) param.get(Constants.ORDERS_INFO);
			if (order == null) {
				throw new BusinessException("订单信息异常");
			}

			if (handler == PayLogHandler.CREATE) {
				saveLog(order, type.getType(), (String) param.get(BaseParam.CLIENT_TRADE_TIME));
				this.log.debug("支付日志保存成功！");
			} else if (handler == PayLogHandler.UPDATE) {
				// 通知时返回值为订单
				// PayOrder order = (PayOrder) result;
				upadteLog(order, type.getType());
				this.log.debug("支付日志更新成功！");
			}

			// 保证嵌套使用时单一记录
			Map<String, Object> currentInvoke = new ConcurrentHashMap<>();
			currentInvoke.put(sign, this);
			this.currentObject.set(currentInvoke);

			log.debug("当前类：" + this + "，签名：" + sign);
		} catch (Exception e) {
			// 抛出业务异常
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new BusinessException(ErrorEnum.SERVER_EXCEPTION.getErrDesc(),
						ErrorEnum.SERVER_EXCEPTION.getErrCode());
			}
		}

	}

	private String getCurrentRequestSignature() {
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return Md5SignUtil.sign(now, this.getClass().getName());
	}

	private void upadteLog(PayOrder order, int optype) {
		log.debug("更新交易记录，订单信息：" + order + "，optype:" + optype);
		// 1.以订单id获取支付记录
		PayLog log = this.payLogModel.getPayLogByOrderIdAndOpType(order.getId(), optype);
		log.setPayMoney(order.getMoneyOrder());
		log.setUpdateTime(new Date());
		log.setTradeSn(order.getTradeSn());
		// 2.更新状态
		this.payLogModel.updatePayLog(log);
	}

	private void saveLog(PayOrder order, int optype, String rhTime) {
		Date tradetime  = new Date();
		tradetime.setTime(Long.valueOf(rhTime));
		// 创建支付操作日志
		PayLog log = new PayLog(order.getHandleNum(), order.getHandleName(), new Date(), order.getId(),
				order.getOrgId(), order.getOuterOrderSn(), order.getPaymentCode(), order.getPaymentName(),
				order.getMoneyOrder(), order.getPaySn(), order.getTradeSn(), new Date(), optype, tradetime);

		this.payLogModel.savePayLog(log);
	}

}
