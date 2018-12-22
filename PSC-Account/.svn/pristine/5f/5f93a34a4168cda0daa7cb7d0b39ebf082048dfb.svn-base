package cn.wellcare.service.acc;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.acc.entity.PscPiAccCredit;
import cn.wellcare.api.acc.IPscPiAccCreditService;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.model.acc.PscPiAccCreditModel;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "pscPiAccCreditService")
public class PscPiAccCreditService implements IPscPiAccCreditService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PscPiAccCreditModel pscPiAccCreditModel;
    
     /**
     * 根据id取得psc_pi_acc_credit对象
     * @param  pscPiAccCreditId
     * @return
     */
    @Override
    public RpcResult<PscPiAccCredit> getPscPiAccCreditById(Integer pscPiAccCreditId) {
        RpcResult<PscPiAccCredit> serviceResult = new RpcResult<PscPiAccCredit>();
        try {
            serviceResult.setData(pscPiAccCreditModel.getPscPiAccCreditById(pscPiAccCreditId));
        } catch (Exception e) {
            log.error("[IPscPiAccCreditService][getPscPiAccCreditById]根据id["+pscPiAccCreditId+"]取得psc_pi_acc_credit对象时出现未知异常：",
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
     * 根据账户主键获取信用操作记录
     * @param  pkPiacc
     * @return
     */
    @Override
    public RpcResult<List<PscPiAccCredit>> getPscPiAccCreditByPkPiacc(Integer pkPiacc) {
        RpcResult<List<PscPiAccCredit>> serviceResult = new RpcResult<List<PscPiAccCredit>>();
        try {
            serviceResult.setData(pscPiAccCreditModel.getPscPiAccCreditByPkPiacc(pkPiacc));
        } catch (Exception e) {
            log.error("[IPscPiAccCreditService][getPscPiAccCreditById]根据id["+pkPiacc+"]取得psc_pi_acc_credit对象时出现未知异常：",
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
     * 保存psc_pi_acc_credit对象（新增信用记录流水）
     * @param  pscPiAccCredit
     * @return
     */
     @Override
     public RpcResult<Integer> savePscPiAccCredit(PscPiAccCredit pscPiAccCredit) {
             RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(pscPiAccCreditModel.savePscPiAccCredit(pscPiAccCredit));
        } catch (Exception e) {
            log.error("[IPscPiAccCreditService][savePscPiAccCredit]保存psc_pi_acc_credit对象时出现未知异常：",
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
     * 更新psc_pi_acc_credit对象
     * @param  pscPiAccCredit
     * @return
     * @see
     */
     @Override
     public RpcResult<Integer> updatePscPiAccCredit(PscPiAccCredit pscPiAccCredit) {
             RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(pscPiAccCreditModel.updatePscPiAccCredit(pscPiAccCredit));
        } catch (Exception e) {
            log.error("[IPscPiAccCreditService][updatePscPiAccCredit]更新psc_pi_acc_credit对象时出现未知异常：",
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
    public RpcResult<List<PscPiAccCredit>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PscPiAccCredit>> serviceResult = new RpcResult<List<PscPiAccCredit>>();
        try {
              serviceResult.setData(pscPiAccCreditModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PscPiAccCreditService][page] exception:" + e.getMessage());
            
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
    public RpcResult<Boolean> del(Integer id) {
 		RpcResult<Boolean> serviceResult = new RpcResult<Boolean>();
        try {
            serviceResult.setData(pscPiAccCreditModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PscPiAccCreditService][del] exception:" + e.getMessage());
            
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