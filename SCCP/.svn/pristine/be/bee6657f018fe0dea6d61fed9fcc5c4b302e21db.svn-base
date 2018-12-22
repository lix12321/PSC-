package cn.wellcare.service.ccp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.dao.ccp.ServiceRegisterDao;
import cn.wellcare.entity.ccp.ServiceRegister;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.utils.PagerInfo;

@Service(value = "serviceRegisterService")
public class ServiceRegisterService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ServiceRegisterDao serviceRegisterDao;
    
     /**
     * 根据id取得service_register对象
     * @param  serviceRegisterId
     * @return
     */
    public ServiceResponse<ServiceRegister> getServiceRegisterById(Integer serviceRegisterId) {
        ServiceResponse<ServiceRegister> serviceResult = new ServiceResponse<ServiceRegister>();
        try {
			serviceResult.setData(serviceRegisterDao.get(serviceRegisterId));
        } catch (Exception e) {
            log.error("[IServiceRegisterService][getServiceRegisterById]根据id["+serviceRegisterId+"]取得service_register对象时出现未知异常：",
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
     * 保存service_register对象
     * @param  serviceRegister
     * @return
     */
     public ServiceResponse<Integer> saveServiceRegister(ServiceRegister serviceRegister) {
     	ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
        try {
			serviceResult.setData(serviceRegisterDao.save(serviceRegister));
        } catch (Exception e) {
            log.error("[IServiceRegisterService][saveServiceRegister]保存service_register对象时出现未知异常：",
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
     * 更新service_register对象
     * @param  serviceRegister
     * @return
     * @see cn.wellcare.service.ccp.ServiceRegisterService#updateServiceRegister(ServiceRegister)
     */
     public ServiceResponse<Integer> updateServiceRegister(ServiceRegister serviceRegister) {
     	ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
        try {
			serviceResult.setData(serviceRegisterDao.update(serviceRegister));
        } catch (Exception e) {
            log.error("[IServiceRegisterService][updateServiceRegister]更新service_register对象时出现未知异常：",
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
     
     
    public ServiceResponse<List<ServiceRegister>> page(Map<String, Object> queryMap, PagerInfo pager) {
        ServiceResponse<List<ServiceRegister>> serviceResult = new ServiceResponse<List<ServiceRegister>>();
        try {
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(serviceRegisterDao.getCount(queryMap));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			queryMap.put("start", start);
			queryMap.put("size", size);
			serviceResult.setData(serviceRegisterDao.getList(queryMap));
        } catch (Exception e) {
            log.error("[ServiceRegisterService][page] exception:" + e.getMessage());
            
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
			serviceResult.setData(serviceRegisterDao.del(id) > 0);
        } catch (Exception e) {
            log.error("[ServiceRegisterService][del] exception:" + e.getMessage());
            
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

	public ServiceRegister getByTranscode(String transCode) {
		return serviceRegisterDao.getByTranscode(transCode);
	}
}