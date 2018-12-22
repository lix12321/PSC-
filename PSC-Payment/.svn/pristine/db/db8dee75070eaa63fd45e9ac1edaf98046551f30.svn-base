package cn.wellcare.payment.modules.payset;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PayType;
import cn.wellcare.pojo.common.RpcResult;

public interface IPayTypeService {

	/**
     * 根据id取得支付方式
     * @param  payTypeId
     * @return
     */
    RpcResult<PayType> getPayTypeById(Integer payTypeId);
    
    /**
     * 保存支付方式
     * @param  payType
     * @return
     */
     RpcResult<Integer> savePayType(PayType payType);
     
     /**
     * 更新支付方式
     * @param  payType
     * @return
     */
     RpcResult<Integer> updatePayType(PayType payType);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PayType>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}