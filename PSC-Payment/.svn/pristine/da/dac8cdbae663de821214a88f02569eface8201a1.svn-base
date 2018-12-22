package cn.wellcare.service.modules.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.PagerInfo;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.model.modules.order.PayOrderModel;
import cn.wellcare.payment.modules.order.IPayOrderService;
import cn.wellcare.pojo.common.RpcResult;

@Service(value = "payOrderService")
public class PayOrderService implements IPayOrderService {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PayOrderModel payOrderModel;
    
     /**
     * 根据id取得支付订单
     * @param  payOrderId
     * @return
     */
    @Override
    public RpcResult<PayOrder> getPayOrderById(Integer payOrderId) {
        RpcResult<PayOrder> serviceResult = new RpcResult<PayOrder>();
        try {
            serviceResult.setData(payOrderModel.getPayOrderById(payOrderId));
        } catch (Exception e) {
            log.error("[IPayOrderService][getPayOrderById]根据id["+payOrderId+"]取得支付订单时出现未知异常：",
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
     * 保存支付订单
     * @param  payOrder
     * @return
     */
     @Override
     public RpcResult<Integer> savePayOrder(PayOrder payOrder) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payOrderModel.savePayOrder(payOrder));
        } catch (Exception e) {
            log.error("[IPayOrderService][savePayOrder]保存支付订单时出现未知异常：",
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
     * 更新支付订单
     * @param  payOrder
     * @return
     * @see cn.wellcare.service.order.PayOrderService#updatePayOrder(PayOrder)
     */
     @Override
     public RpcResult<Integer> updatePayOrder(PayOrder payOrder) {
     	RpcResult<Integer> serviceResult = new RpcResult<Integer>();
        try {
            serviceResult.setData(payOrderModel.updatePayOrder(payOrder));
        } catch (Exception e) {
            log.error("[IPayOrderService][updatePayOrder]更新支付订单时出现未知异常：",
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
    public RpcResult<List<PayOrder>> page(Map<String, Object> queryMap, PagerInfo pager) {
        RpcResult<List<PayOrder>> serviceResult = new RpcResult<List<PayOrder>>();
        try {
              serviceResult.setData(payOrderModel.page(queryMap, pager));
        } catch (Exception e) {
            log.error("[PayOrderService][page] exception:" + e.getMessage());
            
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
            serviceResult.setData(payOrderModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[PayOrderService][del] exception:" + e.getMessage());
            
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
	public RpcResult<Boolean> jobSystemCancelOrder() {
		RpcResult<Boolean> serviceResult = new RpcResult<Boolean>();
		try {
			// 24小时未支付的订单
			List<PayOrder> orders = payOrderModel.getUnPaiedOrders();
			log.debug("超过24小时未支付的订单共计" + orders.size() + "个，开始取消");
			if (orders != null && orders.size() > 0) {
				// 删除
				for (PayOrder order : orders) {
					order.setOrderState(PayOrder.ORDER_STATE_CANCEL);
					payOrderModel.cancelUnPaiedOrders(order);
				}
				
			}
			log.debug("订单取消操作完成");
			serviceResult.setData(true);
		} catch (Exception e) {
			log.error("[PayOrderService][jobSystemCancelOrder] exception:" + e.getMessage());
			serviceResult.setData(false);
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
	public RpcResult<List<PayOrder>> getPayOrderByOrderIdAndDate(Map<String, List<String>> param){
     	RpcResult<List<PayOrder>> result = new RpcResult<>();
		try {
			List<PayOrder> payOrders = payOrderModel.getPayOrderByOrderIdAndStatus(param);
			result.setData(payOrders);
		} catch (Exception e) {
			result.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					result.setMsgInfo(e.getMessage());
				result.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				e.printStackTrace();
				result.setError(ErrorEnum.SERVER_EXCEPTION);
			}
			throw e;
		}
		return result;
	}
}