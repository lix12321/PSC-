package cn.wellcare.dao.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.wellcare.entity.log.PayLog;

@Repository
public interface PayLogDao {
 
 	/**
 	* 以主键获取
 	*/
	PayLog get(java.lang.Integer id);
 	
 	/**
 	* 获取条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayLog> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存
 	*/
	Integer save(PayLog payLog);
	
	/**
 	* 更新
 	*/
	Integer update(PayLog payLog);

	/**
 	* 删除
 	*/
    Integer del(Integer id);

	/**
	 * 以订单id获取支付日志
	 * 
	 * @param orderId
	 * @return
	 */
	PayLog getByOrderId(Integer orderId);

	/**
	 * 以订单id和操作类型获取唯一交易记录
	 * 
	 * @param orderId
	 * @param optype
	 * @return
	 */
	PayLog getPayLogByOrderIdAndOpType(@Param("orderId") Integer orderId, @Param("optype") int optype);
 
}