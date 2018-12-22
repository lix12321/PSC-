package cn.wellcare.payment.order;

import java.util.Map;

import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;

public interface IOrderService {
	/**
	 * 创建订单
	 * 
	 * @return
	 */
	public PayOrder createOrder(Map<String, Object> param, UnifyPaymentInfo payinfo);

	/**
	 * 以订单号获取订单
	 * 
	 * @param ordersn
	 * @return
	 */
	public RpcResult<PayOrder> getOrderByOuterSn(String ordersn);

	/**
	 * 更新订单
	 * 
	 * @param ordersn
	 * @return
	 */
	public RpcResult<Boolean> updateOrder(PayOrder order);

	/**
	 * 根据订单号获取第三方交易信息
	 * 
	 * @param ordersn
	 * @return
	 */
	public OrderResult queryOrderBySn(String ordersn);
}
