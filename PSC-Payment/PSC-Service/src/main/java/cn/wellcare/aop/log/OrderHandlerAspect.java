package cn.wellcare.aop.log;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.wellcare.core.constant.OrderHandler;
import cn.wellcare.core.constant.annotations.OrderLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.entity.log.PayOrderLog;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.model.base.OrderLogModel;
import cn.wellcare.model.base.OrderModel;
import cn.wellcare.support.EnumerateParameter;

/**
 * 订单操作切面，记录日志
 * 
 * @author zhaihl
 *
 */
@Component
@Aspect
public class OrderHandlerAspect {
	private Logger loger = Logger.getLogger(this.getClass());
	@Resource
	private OrderLogModel orderLogModel;
	@Resource
	private OrderModel orderModel;
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Pointcut("@annotation(cn.wellcare.core.constant.annotations.OrderLog)")
	public void orderHandler() {
	}

	@Around(value = "orderHandler() && args(order)")
	public Object orderHandler(ProceedingJoinPoint joinPoint, PayOrder order) {
		this.loger.debug("订单操作记录切面...");
		this.loger.debug("订单信息：" + order);

		Object returnval = null;
		try {
			Assert.notNull(order);

			MethodSignature ms = (MethodSignature) joinPoint.getSignature();
			// 入参value
			Method method = ms.getMethod();
			// 方法的注解对象
			OrderLog anno = method.getAnnotation(OrderLog.class);
			OrderHandler type = anno.value();

			// 以订单号查询出原订单信息
			String paysn = order.getPaySn();

			BigDecimal amountPrev = null;
			BigDecimal amountAfter = null;
			Integer statusPrev = null;
			Integer statusAfter = null;
			Integer paymentAfter = null;
			Integer paymentPrev = null;
			if (type == OrderHandler.CREATE) {
				amountPrev = new BigDecimal(EnumerateParameter.ZERO);
				amountAfter = new BigDecimal(EnumerateParameter.ZERO);

				statusPrev = PayOrder.ORDER_STATE_CREATE;
				statusAfter = PayOrder.ORDER_STATE_CREATE;
				paymentPrev = PayOrder.ORDER_PAY_STATUS_NO_PAY;
				paymentAfter = PayOrder.ORDER_PAY_STATUS_NO_PAY;

				// 执行目标
				returnval = joinPoint.proceed();
			} else {
				sqlSessionTemplate.clearCache();
				// 原订单信息
				PayOrder old = orderModel.queryOrderBySn(paysn);
				loger.debug("原订单信息：" + old);

				// 执行目标
				returnval = joinPoint.proceed();

				amountPrev = old.getMoneyOrder();
				statusPrev = old.getOrderState();
				paymentPrev = old.getPaymentStatus();

				// 操作后信息
				amountAfter = order.getMoneyOrder();
				statusAfter = order.getOrderState();
				paymentAfter = order.getPaymentStatus();
			}

			// 创建订单操作日志
			PayOrderLog log = new PayOrderLog(order.getHandleNum(), order.getHandleName(), amountAfter, amountPrev,
					type.getName(), new Date(), order.getId(), order.getOrgId(), order.getOuterOrderSn(), paymentAfter,
					paymentPrev, statusAfter, statusPrev);

			this.orderLogModel.insert(log);
			loger.debug("操作记录保存成功：" + log);
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
		this.loger.debug("订单操作记录保存成功！");
		return returnval;
	}

}
