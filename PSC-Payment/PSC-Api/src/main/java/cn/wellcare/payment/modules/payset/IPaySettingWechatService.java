package cn.wellcare.payment.modules.payset;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PaySettingWechat;
import cn.wellcare.pojo.common.RpcResult;

public interface IPaySettingWechatService {

	/**
     * 根据id取得支付设置-微信
     * @param  paySettingWechatId
     * @return
     */
    RpcResult<PaySettingWechat> getPaySettingWechatById(Integer paySettingWechatId);
    
    /**
     * 保存支付设置-微信
     * @param  paySettingWechat
     * @return
     */
     RpcResult<Integer> savePaySettingWechat(PaySettingWechat paySettingWechat);
     
     /**
     * 更新支付设置-微信
     * @param  paySettingWechat
     * @return
     */
     RpcResult<Integer> updatePaySettingWechat(PaySettingWechat paySettingWechat);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PaySettingWechat>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}