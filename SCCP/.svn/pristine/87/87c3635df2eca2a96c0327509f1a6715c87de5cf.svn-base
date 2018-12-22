package cn.wellcare.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.entity.SystemResources;

@Repository
public interface SystemResourcesDao {

	/**
	 * 以主键获取资源表
	 */
	SystemResources get(Integer id);

	/**
	 * 获取资源表条目数
	 */
	Integer getCount(Map<String, Object> queryMap);

	/**
	 * 条件查询
	 */
	List<SystemResources> getList(Map<String, Object> queryMap);

	/**
	 * 保存资源表
	 */
	Integer save(SystemResources systemResources);

	/**
	 * 更新资源表
	 */
	Integer update(SystemResources systemResources);

	/**
	 * 删除资源表
	 */
	Integer del(Integer id);

	/**
	 * 树数据
	 * 
	 * @param param
	 * @return
	 */
	List<SystemResources> getTreeList(Integer pid);

	/**
	 * 以角色获取资源
	 * 
	 * @param roleId
	 * @return
	 */
	List<SystemResources> getResourceByRoleId(Integer roleId);

	List<SystemResources> getResourceByUser(Integer user);

}