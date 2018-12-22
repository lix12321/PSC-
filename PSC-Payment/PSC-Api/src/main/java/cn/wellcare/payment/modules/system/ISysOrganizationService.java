package cn.wellcare.payment.modules.system;

import java.util.List;
import java.util.Map;

import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.system.SysOrganization;
import cn.wellcare.pojo.common.RpcResult;

public interface ISysOrganizationService {

	/**
	 * 根据id取得机构
	 * 
	 * @param sysOrganizationId
	 * @return
	 */
	RpcResult<SysOrganization> getSysOrganizationById(Integer sysOrganizationId);

	/**
	 * 保存机构
	 * 
	 * @param sysOrganization
	 * @return
	 */
	RpcResult<Integer> saveSysOrganization(SysOrganization sysOrganization);

	/**
	 * 更新机构
	 * 
	 * @param sysOrganization
	 * @return
	 */
	RpcResult<Integer> updateSysOrganization(SysOrganization sysOrganization);

	/**
	 * 分页查询
	 * 
	 * @param queryMap
	 * @param pager
	 * @return
	 */
	RpcResult<List<SysOrganization>> page(Map<String, Object> queryMap, PagerInfo pager);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	RpcResult<Boolean> del(Integer id);

	/**
	 * 以认证名获取机构信息
	 * 
	 * @param username
	 * @return
	 */
	RpcResult<List<SysOrganization>> getByAuthName(String username);
}