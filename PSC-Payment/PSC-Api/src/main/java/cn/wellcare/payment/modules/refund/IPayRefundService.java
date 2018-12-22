package cn.wellcare.payment.modules.refund;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.refund.PayRefund;
import cn.wellcare.pojo.common.RpcResult;

public interface IPayRefundService {

	/**
     * 根据id取得退款
     * @param  payRefundId
     * @return
     */
    RpcResult<PayRefund> getPayRefundById(Integer payRefundId);
    
    /**
     * 保存退款
     * @param  payRefund
     * @return
     */
     RpcResult<Integer> savePayRefund(PayRefund payRefund);
     
     /**
     * 更新退款
     * @param  payRefund
     * @return
     */
     RpcResult<Integer> updatePayRefund(PayRefund payRefund);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PayRefund>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}