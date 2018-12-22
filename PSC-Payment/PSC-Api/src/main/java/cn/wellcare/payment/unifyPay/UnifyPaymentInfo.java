package cn.wellcare.payment.unifyPay;

import cn.wellcare.core.utils.RandomUtil;
import cn.wellcare.entity.order.PayOrder;

public abstract class UnifyPaymentInfo {
	/**
	 * 订单类型
	 * 
	 * @return
	 */
	public abstract String getOrderType();

	/**
	 * 支付方式名称
	 * 
	 * @return
	 */
	public abstract String getPaymentName();

	/**
	 * 订单号
	 * 
	 * @return
	 */
	public String getOrderSn() {
		return RandomUtil.getOrderSn();
	}

	public Integer getOrderState() {
		return PayOrder.ORDER_STATE_CREATE;
	}

	public Integer getPaymentStatus() {
		return PayOrder.ORDER_PAY_STATUS_NO_PAY;
	}
}
