package cn.wellcare.payment.modules.log;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.log.PayOrderLog;
import cn.wellcare.pojo.common.RpcResult;

public interface IPayOrderLogService {

	/**
     * 根据id取得订单操作日志
     * @param  payOrderLogId
     * @return
     */
    RpcResult<PayOrderLog> getPayOrderLogById(Integer payOrderLogId);
    
    /**
     * 保存订单操作日志
     * @param  payOrderLog
     * @return
     */
     RpcResult<Integer> savePayOrderLog(PayOrderLog payOrderLog);
     
     /**
     * 更新订单操作日志
     * @param  payOrderLog
     * @return
     */
     RpcResult<Integer> updatePayOrderLog(PayOrderLog payOrderLog);
     
          /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    RpcResult<List<PayOrderLog>> page(Map<String, Object> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    RpcResult<Boolean> del(Integer id);
}