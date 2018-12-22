package cn.wellcare.payment.modules.payset;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PaySettingAlipay;
import cn.wellcare.pojo.common.RpcResult;

public interface IPaySettingAlipayService {

	/**
     * 根据id取得支付设置-支付宝
     * @param  paySettingAlipayId
     * @return
     */
    RpcResult<PaySettingAlipay> getPaySettingAlipayById(Integer paySettingAlipayId);
    
    /**
     * 保存支付设置-支付宝
     * @param  paySettingAlipay
     * @return
     */
     RpcResult<Integer> savePaySettingAlipay(PaySettingAlipay paySettingAlipay);
     
     /**
     * 更新支付设置-支付宝
     * @param  paySettingAlipay
     * @return
     */
     RpcResult<Integer> updatePaySettingAlipay(PaySettingAlipay paySettingAlipay);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PaySettingAlipay>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}