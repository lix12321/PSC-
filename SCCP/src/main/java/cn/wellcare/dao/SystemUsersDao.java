package cn.wellcare.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.bo.SystemUsersBO;
import cn.wellcare.entity.SystemUsers;

@Repository
public interface SystemUsersDao {
 
 	
 	/**
 	* 获取系统用户条目数
 	*/
  	Integer getCount(Map<String, Object> queryMap);

	/**
 	* 条件查询
 	*/
    List<SystemUsers> getList(Map<String, Object> queryMap);
 	
 	/**
 	* 保存系统用户
 	*/
	Integer save(SystemUsers systemUsers);
	
	/**
 	* 更新系统用户
 	*/
	Integer update(SystemUsers systemUsers);

	/**
 	* 删除系统用户
 	*/
    Integer del(Integer id);
	/**
	 * 获取系统用户
	 */
	SystemUsers get(Integer id);

	SystemUsersBO getByLoginName(String username);
}