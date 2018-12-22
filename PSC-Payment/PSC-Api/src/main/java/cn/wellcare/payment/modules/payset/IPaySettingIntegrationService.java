package cn.wellcare.payment.modules.payset;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PaySettingIntegration;
import cn.wellcare.pojo.common.RpcResult;

public interface IPaySettingIntegrationService {

	/**
     * 根据id取得支付设置-建行聚合支付
     * @param  paySettingIntegrationId
     * @return
     */
    RpcResult<PaySettingIntegration> getPaySettingIntegrationById(Integer paySettingIntegrationId);
    
    /**
     * 保存支付设置-建行聚合支付
     * @param  paySettingIntegration
     * @return
     */
     RpcResult<Integer> savePaySettingIntegration(PaySettingIntegration paySettingIntegration);
     
     /**
     * 更新支付设置-建行聚合支付
     * @param  paySettingIntegration
     * @return
     */
     RpcResult<Integer> updatePaySettingIntegration(PaySettingIntegration paySettingIntegration);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PaySettingIntegration>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}