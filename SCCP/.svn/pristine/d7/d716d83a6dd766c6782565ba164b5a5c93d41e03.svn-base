package cn.wellcare.dao;

import cn.wellcare.entity.SysOrganization;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysOrganizationDao {
 
 	/**
 	* 以主键获取机构
 	*/
	SysOrganization get(Integer id);
 	
 	/**
 	* 获取机构条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<SysOrganization> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存机构
 	*/
	Integer save(SysOrganization sysOrganization);
	
	/**
 	* 更新机构
 	*/
	Integer update(SysOrganization sysOrganization);

	/**
 	* 删除机构
 	*/
    Integer del(Integer id);
 
}