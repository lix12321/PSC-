package cn.wellcare.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wellcare.entity.SystemWhiteList;

@Repository
public interface SystemWhiteListDao {

	/**
	 * 以主键获取权限白名单
	 */
	SystemWhiteList get(java.lang.Integer id);

	/**
	 * 获取权限白名单条目数
	 */
	Integer getCount(Map<String, Object> queryMap);

	/**
	 * 条件查询
	 */
	List<SystemWhiteList> getList(Map<String, Object> queryMap);

	/**
	 * 保存权限白名单
	 */
	Integer save(SystemWhiteList systemWhiteList);

	/**
	 * 更新权限白名单
	 */
	Integer update(SystemWhiteList systemWhiteList);

	/**
	 * 删除权限白名单
	 */
	Integer del(Integer id);

}