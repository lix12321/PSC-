package cn.wellcare.dao.payset;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.payset.PaySettingIntegration;

@Repository
public interface PaySettingIntegrationDao {
 
 	/**
 	* 以主键获取支付设置-建行聚合支付
 	*/
	PaySettingIntegration get(java.lang.Integer id);
 	
 	/**
 	* 获取支付设置-建行聚合支付条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PaySettingIntegration> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存支付设置-建行聚合支付
 	*/
	Integer save(PaySettingIntegration paySettingIntegration);
	
	/**
 	* 更新支付设置-建行聚合支付
 	*/
	Integer update(PaySettingIntegration paySettingIntegration);

	/**
 	* 删除支付设置-建行聚合支付
 	*/
    Integer del(Integer id);
 
}