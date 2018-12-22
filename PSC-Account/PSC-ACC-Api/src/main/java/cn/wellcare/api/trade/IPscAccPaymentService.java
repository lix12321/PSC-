package cn.wellcare.api.trade;

import java.util.Map;

import cn.wellcare.pojo.common.AccPaymentResult;
import cn.wellcare.pojo.common.RpcResult;

public interface IPscAccPaymentService {
	public RpcResult<AccPaymentResult> accPay(Map<String, Object> param);

	public RpcResult<AccPaymentResult> accRecharge(Map<String, Object> param);
}
