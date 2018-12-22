package cn.wellcare.aop.notify;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.NotifyType;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.SignType;
import cn.wellcare.core.constant.annotations.Notify;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.entity.log.PayNotifyLog;
import cn.wellcare.entity.notify.PayNotify;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.model.base.OrderModel;
import cn.wellcare.model.modules.system.SysOrganizationModel;
import cn.wellcare.model.notify.paynotify.PayNotifyModel;
import cn.wellcare.model.payment.account.PscPiAccModel;

/**
 * 支付通知切面
 * 
 * @author zhaihl
 *
 */
@Component
@Aspect
public class PaymentNotifyAspect {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private NotifySchedule notifySchedule;
	@Resource
	private OrderModel orderModel;
	@Resource
	private PayNotifyModel payNotifyModel;
	@Resource
	private SysOrganizationModel sysOrganizationModel;
	@Resource
	private PscPiAccModel piAccModel;

	@Pointcut("@annotation(cn.wellcare.core.constant.annotations.Notify)")
	public void paymentNotify() {
	}

	@SuppressWarnings("unchecked")
	@Around("paymentNotify()")
	public Object paymentNotify(ProceedingJoinPoint joinPoint) {
		log.debug("通知环绕切面，目标类：" + joinPoint.getTarget().getClass().getName());

		String error = "";
		Object reuturnobj = null;
		try {
			// 目标方法
			reuturnobj = joinPoint.proceed();
			// 1.获取参数
			Object paramobj = joinPoint.getArgs()[0];
			Map<String, Object> param = (Map<String, Object>) paramobj;
			PayOrder order = (PayOrder) param.get(Constants.ORDERS_INFO);

			if (order == null) {
				this.log.error("订单信息异常，不进行业务通知");
				throw new BusinessException("订单信息获取失败，业务通知创建失败");
			}

			MethodSignature ms = (MethodSignature) joinPoint.getSignature();
			// 入参value
			Method method = ms.getMethod();
			// 方法的注解对象
			Notify notify = method.getAnnotation(Notify.class);
			NotifyType type = notify.value();

			// 2.创建通知
			if (NotifyType.AFTER_NOTIFY.equals(type)) {
				PayNotify pn = this.payNotifyModel.getPayNotifyByOrderId(order.getId());
				log.debug("以订单" + order.getId() + "获取到通知：" + pn);
				if (null != pn) {
					pn.setNotifyTime(new Date());
					pn.setTradeStatus(PayNotify.TRADE_STATUS_DONE);
					pn.setGmtClose(new Date());
					// 交易流水
					pn.setTradeNo(order.getTradeSn());

					// 机构密钥
					String securtkey = sysOrganizationModel.getSysOrganizationById(order.getOrgId()).getAuthSecret();
					String sign = Md5SignUtil.sginMD5(CommonUtils.sort(pn), securtkey);
					pn.setSign(sign);
					// 通知消息
					boolean sendresult = this.notifySchedule.scheduledRun(pn);
					this.payNotifyModel.updatePayNotify(pn);

					// 4.通知日志
					if (!sendresult) {
						// TODO 失败
						error = "消息通知失败";
					}
					createNotifyLog(error, pn, true);
				}

				String paySn = order.getPaySn();

				log.debug("获取到订单号：" + paySn);
				// 账户充值 TODO
				if (paySn.length() > 17
						&& PaymentType.ACCRECHARGE.getPaymentCode().equals(paySn.substring(17, paySn.length()))) {
					log.debug("账户充值，设置类型");
					param.put(BaseParam.PAY_TYPE, PaymentType.ACCRECHARGE.getPaymentCode());
					param.put(BaseParam.RECHARGE_FLAG, BaseParam.RECHARGE_FLAG);

					// 账户充值
					boolean success = piAccModel.accRecharge(order,
							Integer.valueOf((String) param.get(BaseParam.USER_ID)));
					if (!success) {
						throw new BusinessException("充值失败");
					}
				}

			} else if (NotifyType.CREATE_NOTIFY.equals(type)) {
				log.debug("创建通知。。。");
				createNotify(order, param);
			}
		} catch (Throwable e) {
			// 抛出业务异常
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new BusinessException(ErrorEnum.SERVER_EXCEPTION.getErrDesc(),
						ErrorEnum.SERVER_EXCEPTION.getErrCode());
			}
		}

		return reuturnobj;

	}

	/**
	 * 通知日志
	 * 
	 * @param error
	 * @param pn
	 * @param isNotify
	 */
	private void createNotifyLog(String error, PayNotify pn, boolean isNotify) {
		PayNotifyLog log = new PayNotifyLog(error, pn.getNotifyId(),
				isNotify ? PayNotifyLog.NOTIFY_DONE : PayNotifyLog.NOTIFY_UNDO, isNotify ? new Date() : null);
		payNotifyModel.createNotifyLog(log);
	}

	private void createNotify(PayOrder order, Map<String, Object> param) {
		PayNotify pn = new PayNotify();
		// 订单id
		pn.setOrderId(order.getId());
		pn.setOrgId(Integer.valueOf((String) param.get(BaseParam.ORG_ID)));
		// 业务订单号
		pn.setOuterOrderSn(order.getPaySn());
		pn.setBackUrl((String) param.get(BaseParam.BACK_URL));
		// TODO 目前签名固定md5
		pn.setSignType(SignType.MD5.getName());
		pn.setTradeStatus(PayNotify.TRADE_STATUS_ACTIVE);
		pn.setTotalAmount(order.getMoneyOrder());

		pn.setBody(order.getRemark());
		pn.setGmtPayment(new Date());

		// TODO insert
		this.payNotifyModel.insertPayNotify(pn);

		// createNotifyLog(null, pn, false);
	}

}
