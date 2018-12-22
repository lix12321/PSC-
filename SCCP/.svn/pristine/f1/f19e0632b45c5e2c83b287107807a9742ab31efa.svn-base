package cn.wellcare.dao.ccp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.entity.ccp.ServiceConfig;

@Repository
public interface ServiceConfigDao {
 
 	/**
 	* 以主键获取通道配置
 	*/
	ServiceConfig get(java.lang.Integer id);
 	
 	/**
 	* 获取通道配置条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<ServiceConfig> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存通道配置
 	*/
	Integer save(ServiceConfig serviceConfig);
	
	/**
 	* 更新通道配置
 	*/
	Integer update(ServiceConfig serviceConfig);

	/**
 	* 删除通道配置
 	*/
    Integer del(Integer id);

	ServiceConfig getServiceConfigServerCode(String serverCode);
 
}