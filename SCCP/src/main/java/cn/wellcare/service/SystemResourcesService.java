package cn.wellcare.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.wellcare.dao.SystemResourcesDao;
import cn.wellcare.entity.SystemResources;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;
import cn.wellcare.pojo.CommonResponse;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.utils.CommonUtils;
import cn.wellcare.utils.PagerInfo;

@Service(value = "systemResourcesService")
public class SystemResourcesService {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private SystemResourcesDao systemResourcesDao;

	/**
	 * 根据id取得资源表
	 * 
	 * @param systemResourcesId
	 * @return
	 */
	public ServiceResponse<SystemResources> getSystemResourcesById(Integer systemResourcesId) {
		ServiceResponse<SystemResources> serviceResult = new ServiceResponse<SystemResources>();
		try {
			serviceResult.setData(systemResourcesDao.get(systemResourcesId));
		} catch (Exception e) {
			log.error("[ISystemResourcesService][getSystemResourcesById]根据id[" + systemResourcesId + "]取得资源表时出现未知异常：",
					e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	/**
	 * 保存资源表
	 * 
	 * @param systemResources
	 * @return
	 */
	public ServiceResponse<Integer> saveSystemResources(SystemResources systemResources) {
		ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
		try {
			systemResources.setCreateTime(new Date());
			serviceResult.setData(systemResourcesDao.save(systemResources));
		} catch (Exception e) {
			log.error("[ISystemResourcesService][saveSystemResources]保存资源表时出现未知异常：", e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	/**
	 * 更新资源表
	 * 
	 * @param systemResources
	 * @return
	 * @see SystemResourcesService#updateSystemResources(SystemResources)
	 */
	public ServiceResponse<Integer> updateSystemResources(SystemResources systemResources) {
		ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
		try {
			serviceResult.setData(systemResourcesDao.update(systemResources));
		} catch (Exception e) {
			log.error("[ISystemResourcesService][updateSystemResources]更新资源表时出现未知异常：", e);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	public ServiceResponse<List<SystemResources>> page(Map<String, Object> queryMap, PagerInfo pager) {
		ServiceResponse<List<SystemResources>> serviceResult = new ServiceResponse<List<SystemResources>>();
		try {
			Map<String, Object> param = new HashMap<String, Object>(queryMap);
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(systemResourcesDao.getCount(param));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			param.put("start", start);
			param.put("size", size);
			serviceResult.setData(systemResourcesDao.getList(param));
		} catch (Exception e) {
			log.error("[SystemResourcesService][page] exception:" + e.getMessage());
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}

		}
		return serviceResult;
	}

	public ServiceResponse<Boolean> del(Integer id) {
		ServiceResponse<Boolean> serviceResult = new ServiceResponse<Boolean>();
		try {
			serviceResult.setData(systemResourcesDao.del(id) > 0);
		} catch (Exception e) {
			log.error("[SystemResourcesService][del] exception:" + e.getMessage());

			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	public ServiceResponse<List<SystemResources>> getByPid(Integer pid) {
		ServiceResponse<List<SystemResources>> serviceResult = new ServiceResponse<List<SystemResources>>();
		try {
			serviceResult.setData(systemResourcesDao.getTreeList(pid));
		} catch (Exception e) {
			log.error("[SystemResourcesService][getByPid] exception:" + e.getMessage());

			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	public ServiceResponse<List<SystemResources>> getResourceByRoleId(Integer roleId) {
		ServiceResponse<List<SystemResources>> serviceResult = new ServiceResponse<>();
		try {
			serviceResult.setData(systemResourcesDao.getResourceByRoleId(roleId));
		} catch (Exception e) {
			log.error("[SystemResourcesService][getResourceByRoleId] exception:" + e.getMessage());

			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	public ServiceResponse<Set<String>> getRolePermitted(Integer user) {
		ServiceResponse<Set<String>> serviceResult = new ServiceResponse<>();
		try {
			List<SystemResources> resourceList = systemResourcesDao.getResourceByUser(user);
			Set<String> urlSet = new HashSet<String>();
			if (resourceList != null && resourceList.size() > 0) {
				for (SystemResources resource : resourceList) {
					if (resource == null) {
						continue;
					}
					String url = resource.getUrl();
					if (!CommonUtils.isNull(url)) {
						// 用逗号分隔资源表的url字段，该字段可存储多个url，并用英文逗号（，）分隔
						String[] split = url.split(",");
						for (String urlSplit : split) {
							// 如果url中带参数，则截去参数部分
							int indexOf = urlSplit.indexOf("?");
							if (indexOf != -1) {
								urlSplit = urlSplit.substring(0, indexOf);
							}
							urlSet.add(urlSplit);
						}
					}
				}
			}
			serviceResult.setData(urlSet);
		} catch (Exception e) {
			log.error("[SystemResourcesService][getRolePermitted] exception:" + e.getMessage());

			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

	public CommonResponse<Boolean> isAccessAllowed(Set<String> data, String uri) {
		CommonResponse<Boolean> serviceResult = new CommonResponse<>();
		try {
			Assert.notNull(uri);

			boolean isAccessAllowed = false;
			for (String permituri : data) {
				if (!uri.startsWith("/")) {
					uri = "/" + uri;
				}
				if (permituri.equals(uri)) {
					isAccessAllowed = true;
					break;
				}
			}
			serviceResult.setHasPermit(isAccessAllowed);
		} catch (Exception e) {
			log.error("[SystemResourcesService][isAccessAllowed] exception:" + e.getMessage());

			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					serviceResult.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					serviceResult.setMsgInfo(e.getMessage());
				serviceResult.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return serviceResult;
	}

}