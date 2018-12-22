package cn.wellcare.handler.transaction.payment.cash;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.support.EnumerateParameter;

/**
 * 现金支付
 */
@Service
public class CashPayHandler {

	protected Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IOrderService orderService;

	public PaymentResult doPay(Map<String, Object> param) {
		PaymentResult pr = new PaymentResult();
		try {
			// 1.订单处理
			PayOrder po = (PayOrder) param.get(Constants.ORDERS_INFO);
			Assert.notNull(po);

			String totalFee = String.valueOf(po.getMoneyOrder());
			// 更新订单
			po.setOrderState(Integer.valueOf(EnumerateParameter.SIX));
			po.setPaymentStatus(Integer.valueOf(EnumerateParameter.ONE));
			this.orderService.updateOrder(po);// 更新订单

			pr = new PaymentResult(totalFee, po.getId());
		} catch (Exception e) {
			pr.setSuccess(false);
			throw e;
		}
		return pr;
	}

}
