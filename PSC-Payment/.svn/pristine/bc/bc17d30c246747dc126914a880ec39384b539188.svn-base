package cn.wellcare.service.modules.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.system.SysCodeMaster;
import cn.wellcare.model.modules.system.SysCodeMasterModel;
import cn.wellcare.payment.modules.system.ISysCodeMasterService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "sysCodeMasterService")
public class SysCodeMasterService implements ISysCodeMasterService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private SysCodeMasterModel sysCodeMasterModel;
    
     /**
     * 根据id取得数据字典
     * @param  codeDiv
	  * @param  codeCd
     * @return
     */
    @Override
    public RpcResult<SysCodeMaster> getSysCodeMasterById(String codeDiv,String codeCd) {
        RpcResult<SysCodeMaster> serviceResult = new RpcResult<SysCodeMaster>();
        try {
            serviceResult.setData(sysCodeMasterModel.getSysCodeMasterById(codeDiv,codeCd));
        } catch (Exception e) {
            log.error("[ISysCodeMasterService][getSysCodeMasterById]根据id["+codeCd+"]取得数据字典时出现未知异常：",
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
     * 保存数据字典
     * @param  sysCodeMaster
     * @return
     */
     @Override
     public RpcResult<Integer> saveSysCodeMaster(SysCodeMaster sysCodeMaster) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
			sysCodeMaster.setCreateTime(new Date());
            serviceResult.setData(sysCodeMasterModel.saveSysCodeMaster(sysCodeMaster));
        } catch (Exception e) {
            log.error("[ISysCodeMasterService][saveSysCodeMaster]保存数据字典时出现未知异常：",
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
     * 更新数据字典
     * @param  sysCodeMaster
     * @return
     * @see cn.wellcare.service.system.SysCodeMasterService#updateSysCodeMaster(SysCodeMaster)
     */
     @Override
     public RpcResult<Integer> updateSysCodeMaster(SysCodeMaster sysCodeMaster) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
			sysCodeMaster.setUpdateTime(new Date());
            serviceResult.setData(sysCodeMasterModel.updateSysCodeMaster(sysCodeMaster));
        } catch (Exception e) {
            log.error("[ISysCodeMasterService][updateSysCodeMaster]更新数据字典时出现未知异常：",
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
     
     
    @Override
    public RpcResult<List<SysCodeMaster>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<SysCodeMaster>> serviceResult = new RpcResult<List<SysCodeMaster>>();
        try {
              serviceResult.setData(sysCodeMasterModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[SysCodeMasterService][page] exception:" + e.getMessage());
            
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

    @Override
    public RpcResult<Boolean> del(String codeDiv,String codeCd) {
 		RpcResult<Boolean> serviceResult = new RpcResult<Boolean>();
        try {
            serviceResult.setData(sysCodeMasterModel.del(codeDiv,codeCd) > 0);
        } catch (Exception e) {
            log.error("[SysCodeMasterService][del] exception:" + e.getMessage());
            
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