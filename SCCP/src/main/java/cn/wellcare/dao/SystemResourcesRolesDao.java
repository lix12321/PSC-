package cn.wellcare.dao;

import cn.wellcare.entity.SystemResourcesRoles;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SystemResourcesRolesDao {

	/**
	 * 以主键获取角色资源对应表
	 */
	SystemResourcesRoles get(Integer id);

	/**
	 * 获取角色资源对应表条目数
	 */
	Integer getCount(Map<String, Object> queryMap);

	/**
	 * 条件查询
	 */
	List<SystemResourcesRoles> getList(Map<String, Object> queryMap);

	/**
	 * 保存角色资源对应表
	 */
	Integer save(SystemResourcesRoles systemResourcesRoles);

	/**
	 * 更新角色资源对应表
	 */
	Integer update(SystemResourcesRoles systemResourcesRoles);

	/**
	 * 删除角色资源对应表
	 */
	Integer del(Integer id);

	/**
	 * 删除该角色下的资源关联
	 * 
	 * @param roleId
	 * @return
	 */
	Integer delByRole(String roleId);

}