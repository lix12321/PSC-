package cn.wellcare.service;

import cn.wellcare.dao.SystemUserRoleDao;
import cn.wellcare.entity.SystemUserRole;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.utils.PagerInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service(value = "systemUserRoleService")
public class SystemUserRoleService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private SystemUserRoleDao systemUserRoleDao;
    
     /**
     * 根据id取得用户-角色关系
     * @param  systemUserRoleId
     * @return
     */
    public ServiceResponse<SystemUserRole> getSystemUserRoleById(Integer systemUserRoleId) {
        ServiceResponse<SystemUserRole> serviceResult = new ServiceResponse<SystemUserRole>();
        try {
            serviceResult.setData(systemUserRoleDao.get(systemUserRoleId));
        } catch (Exception e) {
            log.error("[ISystemUserRoleService][getSystemUserRoleById]根据id["+systemUserRoleId+"]取得用户-角色关系时出现未知异常：",
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
     * 保存用户-角色关系
     * @param  systemUserRole
     * @return
     */
     public ServiceResponse<Integer> saveSystemUserRole(SystemUserRole systemUserRole) {
     	ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
        try {
            serviceResult.setData(systemUserRoleDao.save(systemUserRole));
        } catch (Exception e) {
            log.error("[ISystemUserRoleService][saveSystemUserRole]保存用户-角色关系时出现未知异常：",
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
     * 更新用户-角色关系
     * @param  systemUserRole
     * @return
     * @see cn.wellcare.service.SystemUserRoleService#updateSystemUserRole(SystemUserRole)
     */
     public ServiceResponse<Integer> updateSystemUserRole(SystemUserRole systemUserRole) {
     	ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
        try {
            serviceResult.setData(systemUserRoleDao.update(systemUserRole));
        } catch (Exception e) {
            log.error("[SystemUserRoleService][updateSystemUserRole]更新用户-角色关系时出现未知异常：",
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

    public ServiceResponse<List<SystemUserRole>> page(Map<String, Object> queryMap, PagerInfo pager) {
		ServiceResponse<List<SystemUserRole>> serviceResult = new ServiceResponse<List<SystemUserRole>>();
		try {
			Map<String, Object> param = new HashMap<String, Object>(queryMap);
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(systemUserRoleDao.getCount(param));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			param.put("start", start);
			param.put("size", size);
			serviceResult.setData(systemUserRoleDao.getList(param));
		} catch (Exception e) {
			log.error("[SystemUserRoleService][page] exception:" + e.getMessage());

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
            serviceResult.setData(systemUserRoleDao.del(id) > 0);
        } catch (Exception e) {
            log.error("[SystemUserRoleService][del] exception:" + e.getMessage());
            
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

    public SystemUserRole getSystemUserRoleByUserId(Integer userId) {
     	return systemUserRoleDao.getSystemUserRoleByUserId(userId);
    }
}