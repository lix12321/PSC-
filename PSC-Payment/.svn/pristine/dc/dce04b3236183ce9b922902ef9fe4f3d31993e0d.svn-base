package cn.wellcare.service.transaction.payment.alipay;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.handler.transaction.payment.alipay.AlipayNativePaymentHandler;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.service.transaction.payment.alipay.base.AlipayPayment;

/**
 * 支付宝支付
 * 
 * @author zhaihl
 * @date 2018年10月15日
 */
@Service("alipayNativePaymentService")
public class AlipayNativePaymentService extends AlipayPayment implements PaymentApi {
	@Override
	protected String getAlipayOrderType() {
		return PaymentType.ALIPAY.getPaymentCode();
	}

	@Override
	protected String getAlipayPaymentName() {
		return PaymentType.ALIPAY.getPaymentName();
	}

	@Resource
	private AlipayNativePaymentHandler alipayNativePaymentHandler;

	@Override
	@PaymentLog(PayLogHandler.CREATE)
	public RpcResult<PaymentResult> doPay(Map<String, Object> param) {
		RpcResult<PaymentResult> sr = new RpcResult<>();
		try {
			// 1.支付前操作
			PayOrder po = this.payBefore(param);
			if (CommonUtils.isNull(po)) {
				throw new BusinessException("创建订单失败");
			}

			// 2.支付
			sr.setData(alipayNativePaymentHandler.doPay(param));
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
