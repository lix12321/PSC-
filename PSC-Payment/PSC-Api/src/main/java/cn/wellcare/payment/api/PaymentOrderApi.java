package cn.wellcare.payment.api;

import java.util.Map;

import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;

public interface PaymentOrderApi {
    /**
     * 以订单号获取订单
     *
     * @param ordersn
     * @return
     */
    public RpcResult<OrderResult> getOrderBySn(Map<String, Object> param);
}
