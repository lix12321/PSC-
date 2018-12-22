package cn.wellcare.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.bo.SystemUsersBO;
import cn.wellcare.dao.SystemUsersDao;
import cn.wellcare.entity.SystemUsers;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;
import cn.wellcare.pojo.ServiceResponse;
import cn.wellcare.utils.Md5;
import cn.wellcare.utils.PagerInfo;

@Service("systemUsersService")
public class SystemUsersService {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private SystemUsersDao systemUsersDao;

    public ServiceResponse<List<SystemUsers>> page(Map<String, Object> queryMap, PagerInfo pager) {
        ServiceResponse<List<SystemUsers>> serviceResult = new ServiceResponse<List<SystemUsers>>();
        try {
            Map<String, Object> param = new HashMap<String, Object>(queryMap);
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(systemUsersDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            serviceResult.setData(systemUsersDao.getList(param));
        } catch (Exception e) {
            log.error("[SystemUsersService][page] exception:" + e.getMessage());

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

    public ServiceResponse<SystemUsers> getSystemUsersById(Integer id) {
        ServiceResponse<SystemUsers> serviceResult = new ServiceResponse<SystemUsers>();
        try {
            serviceResult.setData(systemUsersDao.get(id));
        } catch (Exception e) {
            log.error("[SystemUsersService][getSystemUsersById]根据id["+id+"]取得角色表时出现未知异常：",
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

    public ServiceResponse<Integer> updateSystemUsers(SystemUsers systemUsers) {
        ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
        try {
            systemUsers.setUpdateTime(new Date());
            serviceResult.setData(systemUsersDao.update(systemUsers));
        } catch (Exception e) {
            log.error("[ISystemRolesService][updateSystemRoles]更新角色表时出现未知异常：",
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

    public ServiceResponse<Integer> saveSystemUsers(SystemUsers systemUsers) {
        ServiceResponse<Integer> serviceResult = new ServiceResponse<Integer>();
        try {
            systemUsers.setPwd(Md5.getMd5String(systemUsers.getPwd()));
            systemUsers.setCreateTime(new Date());
            serviceResult.setData(systemUsersDao.save(systemUsers));
        } catch (Exception e) {
            log.error("[ISystemRolesService][saveSystemRoles]保存角色表时出现未知异常：",
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

    public ServiceResponse<Boolean> del(Integer id) {
        ServiceResponse<Boolean> serviceResult = new ServiceResponse<Boolean>();
        try {
            serviceResult.setData(systemUsersDao.del(id) > 0);
        } catch (Exception e) {
            log.error("[SystemUsersService][del] exception:" + e.getMessage());

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

	public ServiceResponse<SystemUsersBO> getByLoginName(String username) {
		ServiceResponse<SystemUsersBO> serviceResult = new ServiceResponse<>();
        try {
			serviceResult.setData(systemUsersDao.getByLoginName(username));
        } catch (Exception e) {
			log.error("[SystemUsersService][getByLoginName] exception:" + e.getMessage());
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
