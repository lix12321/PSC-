package cn.wellcare.dao.payset;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.entity.payset.PaySettingWechat;

@Repository
public interface PaySettingWechatDao {
 
 	/**
 	* 以主键获取支付设置-微信
 	*/
	PaySettingWechat get(java.lang.Integer id);

	/**
	 * 获取机构设置
	 * 
	 * @param id
	 * @return
	 */
	PaySettingWechat getByOrg(java.lang.Integer orgId);
 	
 	/**
 	* 获取支付设置-微信条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<PaySettingWechat> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存支付设置-微信
 	*/
	Integer save(PaySettingWechat paySettingWechat);
	
	/**
 	* 更新支付设置-微信
 	*/
	Integer update(PaySettingWechat paySettingWechat);

	/**
 	* 删除支付设置-微信
 	*/
    Integer del(Integer id);
 
}