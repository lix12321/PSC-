package cn.wellcare.service.modules.payset;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.bo.PayStrategyBO;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.payset.PayStrategy;
import cn.wellcare.model.modules.payset.PayStrategyModel;
import cn.wellcare.payment.modules.payset.IPayStrategyService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "payStrategyService")
public class PayStrategyService implements IPayStrategyService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PayStrategyModel payStrategyModel;
    
     /**
     * 根据id取得支付策略
     * @param  payStrategyId
     * @return
     */
    @Override
    public RpcResult<PayStrategy> getPayStrategyById(Integer payStrategyId) {
        RpcResult<PayStrategy> serviceResult = new RpcResult<PayStrategy>();
        try {
            serviceResult.setData(payStrategyModel.getPayStrategyById(payStrategyId));
        } catch (Exception e) {
            log.error("[IPayStrategyService][getPayStrategyById]根据id["+payStrategyId+"]取得支付策略时出现未知异常：",
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
     * 保存支付策略
     * @param  payStrategy
     * @return
     */
     @Override
     public RpcResult<Integer> savePayStrategy(PayStrategy payStrategy) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payStrategyModel.savePayStrategy(payStrategy));
        } catch (Exception e) {
            log.error("[IPayStrategyService][savePayStrategy]保存支付策略时出现未知异常：",
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
     * 更新支付策略
     * @param  payStrategy
     * @return
     * @see cn.wellcare.service.payset.PayStrategyService#updatePayStrategy(PayStrategy)
     */
     @Override
     public RpcResult<Integer> updatePayStrategy(PayStrategy payStrategy) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payStrategyModel.updatePayStrategy(payStrategy));
        } catch (Exception e) {
            log.error("[IPayStrategyService][updatePayStrategy]更新支付策略时出现未知异常：",
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
    public RpcResult<List<PayStrategyBO>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PayStrategyBO>> serviceResult = new RpcResult<List<PayStrategyBO>>();
        try {
              serviceResult.setData(payStrategyModel.findAllList(queryMap, pager));
        } catch (Exception e) {
            log.error("[PayStrategyService][page] exception:" + e.getMessage());
            
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
            serviceResult.setData(payStrategyModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PayStrategyService][del] exception:" + e.getMessage());
            
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