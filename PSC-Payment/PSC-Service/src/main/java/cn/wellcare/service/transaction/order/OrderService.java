package cn.wellcare.service.transaction.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.NotifyType;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.annotations.Notify;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.model.base.OrderModel;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 訂單service
 * 
 * @author pc
 *
 */
@Service("orderService")
public class OrderService implements IOrderService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private OrderModel orderModel;

	@Override
	public RpcResult<PayOrder> getOrderByOuterSn(String ordersn) {
		RpcResult<PayOrder> result = new RpcResult<PayOrder>();
		try {
			PayOrder payOrder = orderModel.getOrderBySn(ordersn);
			if (null != payOrder) {
				result.setData(payOrder);
			} else {
				result.setSuccess(false);
				throw new BusinessException("没有此订单");
			}
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
				if (e instanceof UnauthorizedException) {
					result.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
				} else {
					e.printStackTrace();
					result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				}
			}
		}

		return result;
	}

	@Override
	public RpcResult<Boolean> updateOrder(PayOrder order) {
		RpcResult<Boolean> result = new RpcResult<Boolean>();
		boolean success = orderModel.updateOrder(order);
		if (!success) {
			result.setSuccess(success);
		}

		return result;
	}

	@Override
	public OrderResult queryOrderBySn(String ordersn) {
		return null;
	}


	@Override
	@Notify(NotifyType.CREATE_NOTIFY)
	public synchronized PayOrder createOrder(Map<String, Object> params, UnifyPaymentInfo payinfo) {

		String outOrderSn = (String) params.get(BaseParam.ORDER_ID);

		// TODO 业务订单号应由业务系统自己创建
		String payType = PaymentType.getPaymentCodeByNameOrCode((String) params.get(BaseParam.PAY_TYPE));
		if (PaymentType.ACCRECHARGE.getPaymentCode().equals(payType)) {
			outOrderSn = payinfo.getOrderSn();
			log.warn("账户充值，由支付中心生成业务订单号：" + outOrderSn);
		}

		log.debug("业务订单号：" + outOrderSn);
		// 查询订单
		PayOrder po = orderModel.getOrderBySn(outOrderSn);
		if (po != null) {
			log.error(ErrorEnum.ORDER_EXISTS_EXCEPTION.getErrDesc());
			throw new BusinessException(ErrorEnum.ORDER_EXISTS_EXCEPTION.getErrDesc());
		}
		
		this.log.debug("创建订单");
		PayOrder order = new PayOrder();
		order.setOuterOrderSn(outOrderSn);
		order.setPaySn(payinfo.getOrderSn());

		order.setOrderType(payinfo.getOrderType());
		order.setOrgId(Integer.valueOf((String) params.get(BaseParam.ORG_ID)));
		// 操作员
		order.setHandleNum((String) params.get(BaseParam.HANDLE_NUM));
		order.setHandleName((String) params.get(BaseParam.HANDLE_NAME));
		order.setOrderState(PayOrder.ORDER_STATE_CREATE);
		order.setPayTime(new Date());

		order.setPaymentStatus(PayOrder.ORDER_PAY_STATUS_NO_PAY);
		order.setMoneyOrder(new BigDecimal((String) params.get(BaseParam.PAY_AMOUNT)));
		order.setReqIp((String) params.get(BaseParam.CLIENT_IP));
		order.setPaymentName(payinfo.getPaymentName());
		order.setPaymentCode(PaymentType.getPaymentCodeByName(order.getPaymentName()));
		order.setRemark(payinfo.getPaymentName());
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());

		this.orderModel.insert(order);

		params.put(Constants.ORDERS_INFO, order);
		return order;
	}

}
