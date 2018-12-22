package cn.wellcare.api.trade;

import java.util.Map;

import cn.wellcare.pojo.common.AccPaymentResult;
import cn.wellcare.pojo.common.RpcResult;

public interface IPscAccRefundService {
	public RpcResult<AccPaymentResult> accRefund(Map<String, Object> param);
}
