package cn.wellcare.dao.notify;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.bo.PayNotifyBO;
import cn.wellcare.entity.notify.PayNotify;

@Repository
public interface PayNotifyDao {
 
 	/**
 	* 以主键获取支付通知
 	*/
	PayNotify get(java.lang.Integer notifyId);
 	
 	/**
 	* 获取支付通知条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PayNotifyBO> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存支付通知
 	*/
	Integer save(PayNotify payNotify);
	
	/**
 	* 更新支付通知
 	*/
	Integer update(PayNotify payNotify);

	/**
 	* 删除支付通知
 	*/
    Integer del(Integer id);

	PayNotify getSettingsByOrg(Integer orgId);

	int updateByOrderId(PayNotify record);

	PayNotify getPayNotifyByOrderId(Integer orderId);

}