package cn.wellcare.model.modules.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.dao.system.SysOrganizationDao;
import cn.wellcare.entity.system.SysOrganization;

@Component
public class SysOrganizationModel {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private SysOrganizationDao sysOrganizationDao;

	/**
	 * 根据id取得机构
	 * 
	 * @param sysOrganizationId
	 * @return
	 */
	public SysOrganization getSysOrganizationById(Integer sysOrganizationId) {
		return sysOrganizationDao.get(sysOrganizationId);
	}

	/**
	 * 保存机构
	 * 
	 * @param sysOrganization
	 * @return
	 */
	public Integer saveSysOrganization(SysOrganization sysOrganization) {
		this.dbConstrains(sysOrganization);
		return sysOrganizationDao.save(sysOrganization);
	}

	/**
	 * 更新机构
	 * 
	 * @param sysOrganization
	 * @return
	 */
	public Integer updateSysOrganization(SysOrganization sysOrganization) {
		this.dbConstrains(sysOrganization);
		return sysOrganizationDao.update(sysOrganization);
	}

	private void dbConstrains(SysOrganization sysOrganization) {
		sysOrganization.setAuthName(CommonUtils.dbSafeString(sysOrganization.getAuthName(), true, 32));
		sysOrganization.setAuthPwd(CommonUtils.dbSafeString(sysOrganization.getAuthPwd(), true, 32));
		sysOrganization.setAuthSecret(CommonUtils.dbSafeString(sysOrganization.getAuthSecret(), true, 255));
		sysOrganization.setOrgCode(CommonUtils.dbSafeString(sysOrganization.getOrgCode(), true, 32));
		sysOrganization.setOrgIndex(CommonUtils.dbSafeString(sysOrganization.getOrgIndex(), true, 255));
		sysOrganization.setOrgName(CommonUtils.dbSafeString(sysOrganization.getOrgName(), true, 32));
		sysOrganization.setPyCode(CommonUtils.dbSafeString(sysOrganization.getPyCode(), true, 32));
		sysOrganization.setShortName(CommonUtils.dbSafeString(sysOrganization.getShortName(), true, 32));
	}

	public List<SysOrganization> page(Map<String, Object> queryMap, PagerInfo pager) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>(queryMap);
		Integer start = 0, size = 0;
		if (pager != null) {
			pager.setRowsCount(sysOrganizationDao.getCount(param));
			start = pager.getStart();
			size = pager.getPageSize();
		}
		param.put("start", start);
		param.put("size", size);
		List<SysOrganization> list = sysOrganizationDao.getList(param);
		return list;
	}

	public Integer del(Integer id) {
		return sysOrganizationDao.del(id);
	}

	public List<SysOrganization> getByAuthName(String username) {
		Map<String, Object> param = new HashMap<>();
		param.put("authName", username);
		return sysOrganizationDao.getList(param);
	}

}