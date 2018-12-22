package cn.wellcare.service.modules.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.order.PayPosOrder;
import cn.wellcare.model.modules.order.PayPosOrderModel;
import cn.wellcare.payment.modules.order.IPayPosOrderService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "payPosOrderService")
public class PayPosOrderService implements IPayPosOrderService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PayPosOrderModel payPosOrderModel;

     /**
     * 根据id取得MisPOS订单
     * @param  payPosOrderId
     * @return
     */
    @Override
    public RpcResult<PayPosOrder> getPayPosOrderById(Integer payPosOrderId) {
        RpcResult<PayPosOrder> serviceResult = new RpcResult<PayPosOrder>();
        try {
            serviceResult.setData(payPosOrderModel.getPayPosOrderById(payPosOrderId));
        } catch (Exception e) {
            log.error("[IPayPosOrderService][getPayPosOrderById]根据id["+payPosOrderId+"]取得MisPOS订单时出现未知异常：",
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
     * 保存MisPOS订单
     * @param  payPosOrder
     * @return
     */
     @Override
     public RpcResult<Integer> savePayPosOrder(PayPosOrder payPosOrder) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payPosOrderModel.savePayPosOrder(payPosOrder));
        } catch (Exception e) {
            log.error("[IPayPosOrderService][savePayPosOrder]保存MisPOS订单时出现未知异常：",
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
     * 更新MisPOS订单
     * @param  payPosOrder
     * @return
     * @see cn.wellcare.service.order.PayPosOrderService#updatePayPosOrder(PayPosOrder)
     */
     @Override
     public RpcResult<Integer> updatePayPosOrder(PayPosOrder payPosOrder) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payPosOrderModel.updatePayPosOrder(payPosOrder));
        } catch (Exception e) {
            log.error("[IPayPosOrderService][updatePayPosOrder]更新MisPOS订单时出现未知异常：",
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
    public RpcResult<List<PayPosOrder>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PayPosOrder>> serviceResult = new RpcResult<List<PayPosOrder>>();
        try {
              serviceResult.setData(payPosOrderModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PayPosOrderService][page] exception:" + e.getMessage());
            
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
            serviceResult.setData(payPosOrderModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PayPosOrderService][del] exception:" + e.getMessage());
            
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