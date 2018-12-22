package cn.wellcare.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import cn.wellcare.entity.SystemUserRole;

@Repository
public interface SystemUserRoleDao {
 
 	/**
 	* 以主键获取用户-角色关系
 	*/
	SystemUserRole get(java.lang.Integer id);
 	
 	/**
 	* 获取用户-角色关系条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<SystemUserRole> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存用户-角色关系
 	*/
	Integer save(SystemUserRole systemUserRole);
	
	/**
 	* 更新用户-角色关系
 	*/
	Integer update(SystemUserRole systemUserRole);

	/**
 	* 删除用户-角色关系
 	*/
    Integer del(Integer id);

	SystemUserRole getSystemUserRoleByUserId(Integer userId);
}