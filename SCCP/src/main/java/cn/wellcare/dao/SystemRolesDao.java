package cn.wellcare.dao;

import cn.wellcare.entity.SystemRoles;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SystemRolesDao {
 
 	/**
 	* 以主键获取角色表
 	*/
	SystemRoles get(Integer id);
 	
 	/**
 	* 获取角色表条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<SystemRoles> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存角色表
 	*/
	Integer save(SystemRoles systemRoles);
	
	/**
 	* 更新角色表
 	*/
	Integer update(SystemRoles systemRoles);

	/**
 	* 删除角色表
 	*/
    Integer del(Integer id);
 
}