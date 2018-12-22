package cn.wellcare.payment.modules.payset;

import java.util.List;
import java.util.Map;

import cn.wellcare.bo.PayStrategyBO;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PayStrategy;
import cn.wellcare.pojo.common.RpcResult;

public interface IPayStrategyService {

	/**
     * 根据id取得支付策略
     * @param  payStrategyId
     * @return
     */
    RpcResult<PayStrategy> getPayStrategyById(Integer payStrategyId);
    
    /**
     * 保存支付策略
     * @param  payStrategy
     * @return
     */
     RpcResult<Integer> savePayStrategy(PayStrategy payStrategy);
     
     /**
     * 更新支付策略
     * @param  payStrategy
     * @return
     */
     RpcResult<Integer> updatePayStrategy(PayStrategy payStrategy);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PayStrategyBO>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}