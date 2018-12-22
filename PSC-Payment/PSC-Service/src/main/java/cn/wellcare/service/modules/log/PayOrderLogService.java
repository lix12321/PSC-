package cn.wellcare.service.modules.log;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.log.PayOrderLog;
import cn.wellcare.model.modules.log.PayOrderLogModel;
import cn.wellcare.payment.modules.log.IPayOrderLogService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "payOrderLogService")
public class PayOrderLogService implements IPayOrderLogService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PayOrderLogModel payOrderLogModel;
    
     /**
     * 根据id取得订单操作日志
     * @param  payOrderLogId
     * @return
     */
    @Override
    public RpcResult<PayOrderLog> getPayOrderLogById(Integer payOrderLogId) {
        RpcResult<PayOrderLog> serviceResult = new RpcResult<PayOrderLog>();
        try {
            serviceResult.setData(payOrderLogModel.getPayOrderLogById(payOrderLogId));
        } catch (Exception e) {
            log.error("[IPayOrderLogService][getPayOrderLogById]根据id["+payOrderLogId+"]取得订单操作日志时出现未知异常：",
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
     * 保存订单操作日志
     * @param  payOrderLog
     * @return
     */
     @Override
     public RpcResult<Integer> savePayOrderLog(PayOrderLog payOrderLog) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payOrderLogModel.savePayOrderLog(payOrderLog));
        } catch (Exception e) {
            log.error("[IPayOrderLogService][savePayOrderLog]保存订单操作日志时出现未知异常：",
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
     * 更新订单操作日志
     * @param  payOrderLog
     * @return
     * @see cn.wellcare.service.log.PayOrderLogService#updatePayOrderLog(PayOrderLog)
     */
     @Override
     public RpcResult<Integer> updatePayOrderLog(PayOrderLog payOrderLog) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payOrderLogModel.updatePayOrderLog(payOrderLog));
        } catch (Exception e) {
            log.error("[IPayOrderLogService][updatePayOrderLog]更新订单操作日志时出现未知异常：",
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
    public RpcResult<List<PayOrderLog>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PayOrderLog>> serviceResult = new RpcResult<List<PayOrderLog>>();
        try {
              serviceResult.setData(payOrderLogModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PayOrderLogService][page] exception:" + e.getMessage());
            
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
            serviceResult.setData(payOrderLogModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PayOrderLogService][del] exception:" + e.getMessage());
            
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