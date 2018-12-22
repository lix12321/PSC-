package cn.wellcare.dao.payset;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.entity.payset.PaySettingAlipay;

@Repository
public interface PaySettingAlipayDao {
 
 	/**
 	* 以主键获取支付设置-支付宝
 	*/
	PaySettingAlipay get(java.lang.Integer id);
 	
 	/**
 	* 获取支付设置-支付宝条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PaySettingAlipay> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存支付设置-支付宝
 	*/
	Integer save(PaySettingAlipay paySettingAlipay);
	
	/**
 	* 更新支付设置-支付宝
 	*/
	Integer update(PaySettingAlipay paySettingAlipay);

	/**
 	* 删除支付设置-支付宝
 	*/
    Integer del(Integer id);

	PaySettingAlipay getByOrg(Integer org);
 
}