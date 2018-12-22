package cn.wellcare.service.transaction.payment.alipay.base;

import java.util.Map;

import javax.annotation.Resource;

import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;

/**
 * 支付宝支付
 * 
 * @author zhaihl
 * @date 2018年10月15日
 */
public class AlipayPayment {
	@Resource
	private IOrderService orderService;

	public PayOrder payBefore(Map<String, Object> params) {
		// 1.创建订单
		PayOrder po = this.orderService.createOrder(params, new UnifyPaymentInfo() {

			@Override
			public String getPaymentName() {
				return getAlipayPaymentName();
			}

			@Override
			public String getOrderType() {
				return getAlipayOrderType();
			}
		});
		// 2.返回订单信息
		return po;
	}

	protected String getAlipayOrderType() {
		return null;
	}

	protected String getAlipayPaymentName() {
		return null;
	}

}
