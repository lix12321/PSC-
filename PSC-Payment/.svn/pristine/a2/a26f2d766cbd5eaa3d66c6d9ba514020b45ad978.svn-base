package cn.wellcare.service.transaction.payment.account;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.OpType;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.RandomUtil;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.handler.transaction.payment.alipay.AlipaySaoMaHandler;
import cn.wellcare.handler.transaction.payment.cash.CashPayHandler;
import cn.wellcare.handler.transaction.payment.mispos.MisPosPaymentHandler;
import cn.wellcare.handler.transaction.payment.wechat.WechatSaoMaPayHandler;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;

/**
 * 账户充值
 * 
 * @author zhaihl
 *
 */
@Service
public class AccRechargeService implements PaymentApi {
	protected Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IOrderService orderService;
	@Resource
	private WechatSaoMaPayHandler wechatSaoMaPayHandler;
	@Resource
	private AlipaySaoMaHandler alipaySaoMaHandler;
	@Resource
	private CashPayHandler cashPayHandler;
	@Resource
	private MisPosPaymentHandler misPosPaymentHandler;

	@Override
	@PaymentLog(value = PayLogHandler.CREATE, type = OpType.RECHARGE)
	public RpcResult<PaymentResult> doPay(Map<String, Object> param) {
		RpcResult<PaymentResult> sr = new RpcResult<>();
		try {
			Assert.notNull(param.get(BaseParam.RECHARGE_TYPE));
			// 1.支付前操作
			PayOrder po = payBefore(param);
			param.put(Constants.ORDERS_INFO, po);

			PaymentResult pr = null;
			// 2.按不同的支付类型调用相应服务
			String payType = PaymentType.getPaymentCodeByNameOrCode((String) param.get(BaseParam.RECHARGE_TYPE));
			log.debug("支付类型：" + payType);

			if (PaymentType.WECHAT_SAOMA.getPaymentCode().equals(payType)
					|| PaymentType.WECHAT_SAOMA.getPaymentName().equals(payType)) {
				// 微信扫码支付
				pr = wechatSaoMaPayHandler.doPay(param);
			} else if (PaymentType.MISPOS.getPaymentCode().equals(payType)
					|| PaymentType.MISPOS.getPaymentName().equals(payType)) {
				// pos已收款，直接返回
				pr = new PaymentResult("0", po.getId());
			} else if (PaymentType.ALIPAY_SAOMA.getPaymentCode().equals(payType)
					|| PaymentType.ALIPAY_SAOMA.getPaymentName().equals(payType)) {
				// 支付宝扫码支付（扫码枪）
				pr = alipaySaoMaHandler.doPay(param);
			} else if (PaymentType.CASH_PAY.getPaymentCode().equals(payType)
					|| PaymentType.CASH_PAY.getPaymentName().equals(payType)) {
				// 现金支付
				pr = cashPayHandler.doPay(param);
			} else {
				throw new BusinessException(ErrorEnum.UNSUPORT_PAY_TYPE.getErrDesc());
			}

			sr.setData(pr);
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

	public PayOrder payBefore(Map<String, Object> params) {
		// 1.创建订单
		PayOrder po = this.orderService.createOrder(params, new UnifyPaymentInfo() {

			@Override
			public String getPaymentName() {
				return PaymentType.ACCRECHARGE.getPaymentName();
			}

			@Override
			public String getOrderType() {
				return PaymentType.ACCRECHARGE.getPaymentCode();
			}

			@Override
			public String getOrderSn() {
				return RandomUtil.getOrderSn() + PaymentType.ACCRECHARGE.getPaymentCode();
			}

		});
		// 2.返回订单信息
		return po;
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
