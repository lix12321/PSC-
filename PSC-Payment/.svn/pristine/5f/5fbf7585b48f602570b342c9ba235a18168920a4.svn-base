package cn.wellcare.payment.api;

import java.util.Map;

import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;

public interface PaymentApi {
	/**
	 * 支付
	 */
	public RpcResult<PaymentResult> doPay(Map<String, Object> param);

	/**
	 * 支付查询
	 * 
	 * @param param
	 */
	public RpcResult<PaymentResult> payQuery(Map<String, Object> param);

	/**
	 * 退款
	 * 
	 * @param param
	 */
	public RpcResult<PaymentResult> payRefund(Map<String, Object> param);

	/**
	 * 退款查询
	 * 
	 * @param param
	 */
	public RpcResult<PaymentResult> payRefundQuery(Map<String, Object> param);

}
