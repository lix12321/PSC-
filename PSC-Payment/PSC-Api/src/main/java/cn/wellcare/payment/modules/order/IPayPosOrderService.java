package cn.wellcare.payment.modules.order;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.order.PayPosOrder;
import cn.wellcare.pojo.common.RpcResult;

public interface IPayPosOrderService {

	/**
     * 根据id取得MisPOS订单
     * @param  payPosOrderId
     * @return
     */
    RpcResult<PayPosOrder> getPayPosOrderById(Integer payPosOrderId);
    
    /**
     * 保存MisPOS订单
     * @param  payPosOrder
     * @return
     */
     RpcResult<Integer> savePayPosOrder(PayPosOrder payPosOrder);
     
     /**
     * 更新MisPOS订单
     * @param  payPosOrder
     * @return
     */
     RpcResult<Integer> updatePayPosOrder(PayPosOrder payPosOrder);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PayPosOrder>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}