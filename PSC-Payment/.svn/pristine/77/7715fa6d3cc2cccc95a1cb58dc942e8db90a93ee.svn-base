package cn.wellcare.service.modules.log;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.log.PayLog;
import cn.wellcare.model.modules.log.PayLogModel;
import cn.wellcare.payment.modules.log.IPayLogService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "payLogService")
public class PayLogService implements IPayLogService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PayLogModel payLogModel;
    
     /**
     * 根据id取得交易记录
     * @param  payLogId
     * @return
     */
    @Override
    public RpcResult<PayLog> getPayLogById(Integer payLogId) {
        RpcResult<PayLog> result = new RpcResult<PayLog>();
        try {
            result.setData(payLogModel.getPayLogById(payLogId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMsgInfo(e.getMessage());
            log.error("[IPayLogService][getPayLogById]根据id["+payLogId+"]取得交易记录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
           	result.setError(ErrorEnum.SERVER_EXCEPTION);
            log.error("[IPayLogService][getPayLogById]根据id["+payLogId+"]取得交易记录时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存交易记录
     * @param  payLog
     * @return
     */
     @Override
     public RpcResult<Integer> savePayLog(PayLog payLog) {
     	RpcResult<Integer> result = new RpcResult<Integer>();
        try {
            result.setData(payLogModel.savePayLog(payLog));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMsgInfo(e.getMessage());
            log.error("[IPayLogService][savePayLog]保存交易记录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
           	result.setError(ErrorEnum.SERVER_EXCEPTION);
            log.error("[IPayLogService][savePayLog]保存交易记录时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新交易记录
     * @param  payLog
     * @return
     * @see cn.wellcare.service.log.PayLogService#updatePayLog(PayLog)
     */
     @Override
     public RpcResult<Integer> updatePayLog(PayLog payLog) {
     	RpcResult<Integer> result = new RpcResult<Integer>();
        try {
            result.setData(payLogModel.updatePayLog(payLog));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMsgInfo(e.getMessage());
            log.error("[IPayLogService][updatePayLog]更新交易记录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            result.setError(ErrorEnum.SERVER_EXCEPTION);
            log.error("[IPayLogService][updatePayLog]更新交易记录时出现未知异常：",
                e);
        }
        return result;
     }
     
     
    @Override
    public RpcResult<List<PayLog>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PayLog>> serviceResult = new RpcResult<List<PayLog>>();
        try {
              serviceResult.setData(payLogModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMsgInfo(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
        	serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
            e.printStackTrace();
            log.error("[PayLogService][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public RpcResult<Boolean> del(Integer id) {
 		RpcResult<Boolean> serviceResult = new RpcResult<Boolean>();
        try {
            serviceResult.setData(payLogModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMsgInfo(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
       		serviceResult.setError(ErrorEnum.SERVER_EXCEPTION);
            e.printStackTrace();
            log.error("[PayLogService][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}