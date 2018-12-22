package cn.wellcare.service.modules.payset;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PaySettingAlipay;
import cn.wellcare.model.modules.payset.PaySettingAlipayModel;
import cn.wellcare.payment.modules.payset.IPaySettingAlipayService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "paySettingAlipayService")
public class PaySettingAlipayService implements IPaySettingAlipayService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PaySettingAlipayModel paySettingAlipayModel;
    
     /**
     * 根据id取得支付设置-支付宝
     * @param  paySettingAlipayId
     * @return
     */
    @Override
    public RpcResult<PaySettingAlipay> getPaySettingAlipayById(Integer paySettingAlipayId) {
        RpcResult<PaySettingAlipay> serviceResult = new RpcResult<PaySettingAlipay>();
        try {
            serviceResult.setData(paySettingAlipayModel.getPaySettingAlipayById(paySettingAlipayId));
        } catch (Exception e) {
            log.error("[IPaySettingAlipayService][getPaySettingAlipayById]根据id["+paySettingAlipayId+"]取得支付设置-支付宝时出现未知异常：",
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
     * 保存支付设置-支付宝
     * @param  paySettingAlipay
     * @return
     */
     @Override
     public RpcResult<Integer> savePaySettingAlipay(PaySettingAlipay paySettingAlipay) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(paySettingAlipayModel.savePaySettingAlipay(paySettingAlipay));
        } catch (Exception e) {
            log.error("[IPaySettingAlipayService][savePaySettingAlipay]保存支付设置-支付宝时出现未知异常：",
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
     * 更新支付设置-支付宝
     * @param  paySettingAlipay
     * @return
     * @see cn.wellcare.service.payset.PaySettingAlipayService#updatePaySettingAlipay(PaySettingAlipay)
     */
     @Override
     public RpcResult<Integer> updatePaySettingAlipay(PaySettingAlipay paySettingAlipay) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(paySettingAlipayModel.updatePaySettingAlipay(paySettingAlipay));
        } catch (Exception e) {
            log.error("[IPaySettingAlipayService][updatePaySettingAlipay]更新支付设置-支付宝时出现未知异常：",
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
    public RpcResult<List<PaySettingAlipay>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PaySettingAlipay>> serviceResult = new RpcResult<List<PaySettingAlipay>>();
        try {
              serviceResult.setData(paySettingAlipayModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PaySettingAlipayService][page] exception:" + e.getMessage());
            
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
            serviceResult.setData(paySettingAlipayModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PaySettingAlipayService][del] exception:" + e.getMessage());
            
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