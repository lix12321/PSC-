package cn.wellcare.service.modules.payset;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PaySettingWechat;
import cn.wellcare.model.modules.payset.PaySettingWechatModel;
import cn.wellcare.payment.modules.payset.IPaySettingWechatService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "paySettingWechatService")
public class PaySettingWechatService implements IPaySettingWechatService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PaySettingWechatModel paySettingWechatModel;
    
     /**
     * 根据id取得支付设置-微信
     * @param  paySettingWechatId
     * @return
     */
    @Override
    public RpcResult<PaySettingWechat> getPaySettingWechatById(Integer paySettingWechatId) {
        RpcResult<PaySettingWechat> serviceResult = new RpcResult<PaySettingWechat>();
        try {
            serviceResult.setData(paySettingWechatModel.getPaySettingWechatById(paySettingWechatId));
        } catch (Exception e) {
            log.error("[IPaySettingWechatService][getPaySettingWechatById]根据id["+paySettingWechatId+"]取得支付设置-微信时出现未知异常：",
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
     * 保存支付设置-微信
     * @param  paySettingWechat
     * @return
     */
     @Override
     public RpcResult<Integer> savePaySettingWechat(PaySettingWechat paySettingWechat) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(paySettingWechatModel.savePaySettingWechat(paySettingWechat));
        } catch (Exception e) {
            log.error("[IPaySettingWechatService][savePaySettingWechat]保存支付设置-微信时出现未知异常：",
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
     * 更新支付设置-微信
     * @param  paySettingWechat
     * @return
     * @see cn.wellcare.service.payset.PaySettingWechatService#updatePaySettingWechat(PaySettingWechat)
     */
     @Override
     public RpcResult<Integer> updatePaySettingWechat(PaySettingWechat paySettingWechat) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(paySettingWechatModel.updatePaySettingWechat(paySettingWechat));
        } catch (Exception e) {
            log.error("[IPaySettingWechatService][updatePaySettingWechat]更新支付设置-微信时出现未知异常：",
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
    public RpcResult<List<PaySettingWechat>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PaySettingWechat>> serviceResult = new RpcResult<List<PaySettingWechat>>();
        try {
              serviceResult.setData(paySettingWechatModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PaySettingWechatService][page] exception:" + e.getMessage());
            
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
            serviceResult.setData(paySettingWechatModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PaySettingWechatService][del] exception:" + e.getMessage());
            
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