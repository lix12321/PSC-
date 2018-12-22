package cn.wellcare.portal.controller.demo;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.core.bean.DomainUrlUtil;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.BaseController;

/**
 * 统一支付演示
 * 
 * @author zhaihl
 *
 */
@RequestMapping("demo/unifyPay")
@Controller
public class DemoUnifyPaymentController extends BaseController {

	@Resource
	private ISysOrganizationService sysOrganizationService;

	@Resource(name = "wechatNativePaymentService")
	private PaymentApi wechatNativePaymentService;

	@Resource(name = "wechatJsAPI")
	private PaymentApi wechatJsAPI;

	@Resource(name = "wechatSaoMaPaymentService")
	private PaymentApi wechatSaoMaPaymentService;

	@Resource(name = "integrationPaymentService")
	private PaymentApi integrationPaymentService;

	@Resource(name = "alipayNativePaymentService")
	private PaymentApi alipayNativePaymentService;

	@Resource(name = "misPosPaymentService")
	private PaymentApi misPosPaymentService;

	@Resource(name = "alipaySaoMaPaymentService")
	private PaymentApi alipaySaoMaPaymentService;

	@Resource(name = "accountPayService")
	private PaymentApi accountPayService;

	@Resource(name = "accRechargeService")
	private PaymentApi accRechargeService;

	@Resource(name = "cashPayService")
	private PaymentApi cashPayService;

	@RequestMapping(value = Constants.DOPAY, produces = Constants.CONTENT_TYPE_JSON)
	@ResponseBody
	public RpcResult<PaymentResult> demopay(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> params) {
		RpcResult<PaymentResult> result = null;
		try {
			result = new RpcResult<>();

			params.put(BaseParam.CLIENT_IP, CommonUtils.getRemoteIpAddr(request));
			// TODO 业务通知地址，由业务系统提供
			params.put(BaseParam.BACK_URL, DomainUrlUtil.PSC_PAYMENT_URL + "/demo/serverNotify");

			Assert.notNull(params.get(BaseParam.PAY_TYPE));
			Assert.notNull(params.get(BaseParam.PAY_AMOUNT));
			Assert.notNull(params.get(BaseParam.CLIENT_TRADE_TIME));

			Map<String, Object> newparam = CommonUtils.sort(params);
			this.log.debug("客户端排序后参数集：" + result);

			String securtkey = sysOrganizationService
					.getSysOrganizationById(Integer.valueOf((String) params.get(BaseParam.ORG_ID))).getData()
					.getAuthSecret();
			log.debug("机构密钥：" + securtkey);

			String sign = Md5SignUtil.sginMD5(newparam, securtkey);
			params.put("sign", sign);

			String payType = (String) params.get(BaseParam.PAY_TYPE);
			Assert.notNull(payType);
			if (PaymentType.WECHAT_NATIVE.getPaymentCode().equals(payType)
					|| PaymentType.WECHAT_NATIVE.getPaymentName().equals(payType)) {
				// 微信本地扫码支付
				result = wechatNativePaymentService.doPay(params);
			} else if (PaymentType.WECHAT_JSAPI.getPaymentCode().equals(payType)
					|| PaymentType.WECHAT_JSAPI.getPaymentName().equals(payType)) {
				// 微信JS API
				result = wechatJsAPI.doPay(params);
			} else if (PaymentType.WECHAT_SAOMA.getPaymentCode().equals(payType)
					|| PaymentType.WECHAT_SAOMA.getPaymentName().equals(payType)) {
				// 微信扫码支付
				result = wechatSaoMaPaymentService.doPay(params);
			} else if (PaymentType.JUHPAY.getPaymentCode().equals(payType)
					|| PaymentType.JUHPAY.getPaymentName().equals(payType)) {
				// 聚合支付
				result = integrationPaymentService.doPay(params);
				// 获取服务的bean
			} else if (PaymentType.ALIPAY.getPaymentCode().equals(payType)
					|| PaymentType.ALIPAY.getPaymentName().equals(payType)) {
				// 支付宝
				result = alipayNativePaymentService.doPay(params);
			} else if (PaymentType.MISPOS.getPaymentCode().equals(payType)
					|| PaymentType.MISPOS.getPaymentName().equals(payType)) {
				// 支付宝
				result = misPosPaymentService.doPay(params);
			} else if (PaymentType.ALIPAY_SAOMA.getPaymentCode().equals(payType)
					|| PaymentType.ALIPAY_SAOMA.getPaymentName().equals(payType)) {
				// 支付宝扫码支付（扫码枪）
				result = alipaySaoMaPaymentService.doPay(params);
			} else if (PaymentType.ACCOUNT_PAY.getPaymentCode().equals(payType)
					|| PaymentType.ACCOUNT_PAY.getPaymentName().equals(payType)) {
				// 账户支付
				result = accountPayService.doPay(params);
			} else if (PaymentType.ACCRECHARGE.getPaymentCode().equals(payType)
					|| PaymentType.ACCRECHARGE.getPaymentName().equals(payType)) {
				// 账户充值
				result = accRechargeService.doPay(params);
			} else if (PaymentType.CASH_PAY.getPaymentCode().equals(payType)
					|| PaymentType.CASH_PAY.getPaymentName().equals(payType)) {
				// 现金支付
				result = cashPayService.doPay(params);
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

}
