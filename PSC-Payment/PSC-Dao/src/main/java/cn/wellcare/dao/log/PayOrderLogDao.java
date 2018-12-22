package cn.wellcare.dao.log;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.log.PayOrderLog;

@Repository
public interface PayOrderLogDao {
 
 	/**
 	* 以主键获取订单操作日志
 	*/
	PayOrderLog get(java.lang.Integer id);
 	
 	/**
 	* 获取订单操作日志条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayOrderLog> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存订单操作日志
 	*/
	Integer save(PayOrderLog payOrderLog);
	
	/**
 	* 更新订单操作日志
 	*/
	Integer update(PayOrderLog payOrderLog);

	/**
 	* 删除订单操作日志
 	*/
    Integer del(Integer id);
 
}