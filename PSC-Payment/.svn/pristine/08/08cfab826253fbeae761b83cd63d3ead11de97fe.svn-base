package cn.wellcare.service.transaction.payment.integrationpay.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.pojo.integrationpay.IntegrationPayConfig;

/**
 * 聚合支付创建订单
 */
public abstract class IntegrationPayment {
	@Resource
	private IOrderService orderService;

	protected String merchantid = IntegrationPayConfig.MERCHANTID;

	public PayOrder payBefore(Map<String, Object> params) {
		// 1.创建订单
		PayOrder po = this.orderService.createOrder(params, new UnifyPaymentInfo() {

			@Override
			public String getPaymentName() {
				return getIntegrationPaymentName();
			}

			@Override
			public String getOrderType() {
				return getIntegrationOrderType();
			}

			@Override
			public String getOrderSn() {
				return creatOrderSn();
			}
		});
		// 2.返回订单信息
		return po;
	}

	protected abstract String getIntegrationOrderType();

	protected abstract String getIntegrationPaymentName();

	/**
	 * 生成聚合支付订单号 -- 商户号+14位日期
	 * 
	 * @return
	 */
	public String creatOrderSn() {
		String orderId = "";
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			orderId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		} finally {
			lock.unlock();
		}
		return merchantid + orderId;
	}
}
