package cn.wellcare.payment.modules.order;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.pojo.common.RpcResult;

public interface IPayOrderService {

	/**
     * 根据id取得支付订单
     * @param  payOrderId
     * @return
     */
    RpcResult<PayOrder> getPayOrderById(Integer payOrderId);
    
    /**
     * 保存支付订单
     * @param  payOrder
     * @return
     */
     RpcResult<Integer> savePayOrder(PayOrder payOrder);
     
     /**
     * 更新支付订单
     * @param  payOrder
     * @return
     */
     RpcResult<Integer> updatePayOrder(PayOrder payOrder);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PayOrder>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);

	RpcResult<Boolean> jobSystemCancelOrder();

    RpcResult<List<PayOrder>> getPayOrderByOrderIdAndDate(Map<String, List<String>> param);

}