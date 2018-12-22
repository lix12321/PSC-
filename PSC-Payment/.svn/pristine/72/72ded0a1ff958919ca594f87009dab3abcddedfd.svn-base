package cn.wellcare.service.transaction.payment.cash;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.handler.transaction.payment.cash.CashPayHandler;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 现金支付
 */
@Service("cashPayService")
public class CashPayService implements PaymentApi{

	protected Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IOrderService orderService;
	@Resource
	private CashPayHandler cashPayHandler;

	/**
	 * 创建订单
	 * 
	 * @param params
	 * @return
	 */
	public PayOrder payBefore(Map<String, Object> params) {
		// 1.创建订单
		PayOrder po = this.orderService.createOrder(params, new UnifyPaymentInfo() {

			@Override
			public String getPaymentName() {
				return PaymentType.CASH_PAY.getPaymentName();
			}

			@Override
			public String getOrderType() {
				return PaymentType.CASH_PAY.getPaymentCode();
			}
		});
		// 2.返回订单信息
		return po;
	}

	@PaymentLog(PayLogHandler.CREATE)
	@Override
	public RpcResult<PaymentResult> doPay(Map<String, Object> param) {
		RpcResult<PaymentResult> sr = new RpcResult<>();
		try {
			// 1.支付前操作
			PayOrder po = payBefore(param);
			if (CommonUtils.isNull(po)) {
				throw new BusinessException("创建订单失败");
			}

			sr.setData(cashPayHandler.doPay(param));
		} catch (Exception e) {
			sr.setSuccess(false);
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
					sr.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
				else
					sr.setMsgInfo(e.getMessage());
				sr.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
			} else {
				if (e instanceof UnauthorizedException) {
					sr.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
				} else {
					e.printStackTrace();
					sr.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
				}
			}
			throw e;
		}
		return sr;
	}

	@Override
	public RpcResult<PaymentResult> payQuery(Map<String, Object> param) {
		return null;
	}

	@Override
	public RpcResult<PaymentResult> payRefund(Map<String, Object> param) {
		return null;
	}

	@Override
	public RpcResult<PaymentResult> payRefundQuery(Map<String, Object> param) {
		return null;
	}
}
