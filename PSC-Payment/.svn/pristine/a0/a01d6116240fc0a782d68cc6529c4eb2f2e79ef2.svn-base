package cn.wellcare.service.transaction.payment.mispos;

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
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.handler.transaction.payment.mispos.MisPosPaymentHandler;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 微信扫码支付
 * 
 * @author zhaihl
 *
 */
@Service("misPosPaymentService")
public class MisPosPaymentService implements PaymentApi {
	protected Logger log = Logger.getLogger(this.getClass());
	@Resource
	private IOrderService orderService;
	@Resource
	private MisPosPaymentHandler misPosPaymentHandler;

	/**
	 * MISPOS支付，只创建订单
	 */
	@Override
	@PaymentLog(PayLogHandler.CREATE)
	public RpcResult<PaymentResult> doPay(Map<String, Object> params) {
		RpcResult<PaymentResult> sr = new RpcResult<>();
		try {
			// 1.创建订单
			this.orderService.createOrder(params, new UnifyPaymentInfo() {

				@Override
				public String getOrderType() {
					return PaymentType.MISPOS.getPaymentCode();
				}

				@Override
				public String getPaymentName() {
					return PaymentType.MISPOS.getPaymentName();
				}

				@Override
				public Integer getOrderState() {
					return PayOrder.ORDER_STATE_FINISH;
				}

				@Override
				public Integer getPaymentStatus() {
					return PayOrder.ORDER_PAY_STATUS_PAEID;
				}
			});

			sr.setData(misPosPaymentHandler.doPay(params));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RpcResult<PaymentResult> payRefund(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RpcResult<PaymentResult> payRefundQuery(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

}
