package cn.wellcare.service.ccp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.dao.ccp.ServiceConfigDao;
import cn.wellcare.entity.ccp.ServiceConfig;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.utils.PagerInfo;

@Service
public class ServiceConfigService {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ServiceConfigDao serviceConfigDao;

	/**
	 * 根据id取得通道配置
	 * 
	 * @param serviceConfigId
	 * @return
	 */
	public ServiceResponse<ServiceConfig> getServiceConfigById(Integer serviceConfigId) {
		ServiceResponse<ServiceConfig> serviceResult = new ServiceResponse<ServiceConfig>();
		try {
			serviceResult.setData(serviceConfigDao.get(serviceConfigId));
		} catch (Exception e) {
			log.error("[IServiceConfigService][getServiceConfigById]根据id[" + serviceConfigId + "]取得通道配置时出现未知异常：", e);
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
	 * 保存通道配置
	 * 
	 * @param serviceConfig
	 * @return
	 */
	public ServiceResponse<Integer> saveServiceConfig(ServiceConfig serviceConfig) {
		ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
		try {
			serviceResult.setData(serviceConfigDao.save(serviceConfig));
		} catch (Exception e) {
			log.error("[IServiceConfigService][saveServiceConfig]保存通道配置时出现未知异常：", e);
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
	 * 更新通道配置
	 * 
	 * @param serviceConfig
	 * @return
	 * @see cn.wellcare.service.ccp.ServiceConfigService#updateServiceConfig(ServiceConfig)
	 */
	public ServiceResponse<Integer> updateServiceConfig(ServiceConfig serviceConfig) {
		ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
		try {
			serviceResult.setData(serviceConfigDao.update(serviceConfig));
		} catch (Exception e) {
			log.error("[IServiceConfigService][updateServiceConfig]更新通道配置时出现未知异常：", e);
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

	public ServiceResponse<List<ServiceConfig>> page(Map<String, Object> queryMap, PagerInfo pager) {
		ServiceResponse<List<ServiceConfig>> serviceResult = new ServiceResponse<List<ServiceConfig>>();
		try {
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(serviceConfigDao.getCount(queryMap));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			queryMap.put("start", start);
			queryMap.put("size", size);
			serviceResult.setData(serviceConfigDao.getList(queryMap));
		} catch (Exception e) {
			log.error("[ServiceConfigService][page] exception:" + e.getMessage());

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
			serviceResult.setData(serviceConfigDao.del(id) > 0);
		} catch (Exception e) {
			log.error("[ServiceConfigService][del] exception:" + e.getMessage());

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

	public ServiceResponse<ServiceConfig> getServiceConfigServerCode(String serverCode) {
		ServiceResponse<ServiceConfig> serviceResult = new ServiceResponse<>();
		try {
			serviceResult.setData(serviceConfigDao.getServiceConfigServerCode(serverCode));
		} catch (Exception e) {
			log.error("[ServiceConfigService][getServiceConfigServerCode] exception:" + e.getMessage());

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
}