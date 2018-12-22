package cn.wellcare.dao.log;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.log.PayNotifyLog;

@Repository
public interface PayNotifyLogDao {
 
 	/**
 	* 以主键获取通知日志
 	*/
	PayNotifyLog get(java.lang.Integer id);
 	
 	/**
 	* 获取通知日志条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayNotifyLog> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存通知日志
 	*/
	Integer save(PayNotifyLog payNotifyLog);
	
	/**
 	* 更新通知日志
 	*/
	Integer update(PayNotifyLog payNotifyLog);

	/**
 	* 删除通知日志
 	*/
    Integer del(Integer id);
 
}