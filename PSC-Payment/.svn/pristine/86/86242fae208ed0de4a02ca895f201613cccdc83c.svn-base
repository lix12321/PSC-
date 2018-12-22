package cn.wellcare.payment.modules.log;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.log.PayLog;
import cn.wellcare.pojo.common.RpcResult;

public interface IPayLogService {

	/**
     * 根据id取得交易记录
     * @param  payLogId
     * @return
     */
    RpcResult<PayLog> getPayLogById(Integer payLogId);
    
    /**
     * 保存交易记录
     * @param  payLog
     * @return
     */
     RpcResult<Integer> savePayLog(PayLog payLog);
     
     /**
     * 更新交易记录
     * @param  payLog
     * @return
     */
     RpcResult<Integer> updatePayLog(PayLog payLog);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PayLog>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}