package cn.wellcare.service.modules.payset;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PaySettingIntegration;
import cn.wellcare.model.modules.payset.PaySettingIntegrationModel;
import cn.wellcare.payment.modules.payset.IPaySettingIntegrationService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "paySettingIntegrationService")
public class PaySettingIntegrationService implements IPaySettingIntegrationService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PaySettingIntegrationModel paySettingIntegrationModel;
    
     /**
     * 根据id取得支付设置-建行聚合支付
     * @param  paySettingIntegrationId
     * @return
     */
    @Override
    public RpcResult<PaySettingIntegration> getPaySettingIntegrationById(Integer paySettingIntegrationId) {
        RpcResult<PaySettingIntegration> serviceResult = new RpcResult<PaySettingIntegration>();
        try {
            serviceResult.setData(paySettingIntegrationModel.getPaySettingIntegrationById(paySettingIntegrationId));
        } catch (Exception e) {
            log.error("[IPaySettingIntegrationService][getPaySettingIntegrationById]根据id["+paySettingIntegrationId+"]取得支付设置-建行聚合支付时出现未知异常：",
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
     * 保存支付设置-建行聚合支付
     * @param  paySettingIntegration
     * @return
     */
     @Override
     public RpcResult<Integer> savePaySettingIntegration(PaySettingIntegration paySettingIntegration) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(paySettingIntegrationModel.savePaySettingIntegration(paySettingIntegration));
        } catch (Exception e) {
            log.error("[IPaySettingIntegrationService][savePaySettingIntegration]保存支付设置-建行聚合支付时出现未知异常：",
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
     * 更新支付设置-建行聚合支付
     * @param  paySettingIntegration
     * @return
     * @see cn.wellcare.service.payset.PaySettingIntegrationService#updatePaySettingIntegration(PaySettingIntegration)
     */
     @Override
     public RpcResult<Integer> updatePaySettingIntegration(PaySettingIntegration paySettingIntegration) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(paySettingIntegrationModel.updatePaySettingIntegration(paySettingIntegration));
        } catch (Exception e) {
            log.error("[IPaySettingIntegrationService][updatePaySettingIntegration]更新支付设置-建行聚合支付时出现未知异常：",
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
    public RpcResult<List<PaySettingIntegration>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PaySettingIntegration>> serviceResult = new RpcResult<List<PaySettingIntegration>>();
        try {
              serviceResult.setData(paySettingIntegrationModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PaySettingIntegrationService][page] exception:" + e.getMessage());
            
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
            serviceResult.setData(paySettingIntegrationModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PaySettingIntegrationService][del] exception:" + e.getMessage());
            
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