package cn.wellcare.portal.controller.payment.jsapi.wechat;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.core.bean.DomainUrlUtil;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.pojo.wechat.WechatPaymentResult;
import cn.wellcare.web.BaseController;

/**
 * 微信JS API支付
 * 
 * @author zhaihl
 *
 */
@RequestMapping("wxJSpay")
@Controller
public class WechatJsAPIPaymentController extends BaseController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private ISysOrganizationService sysOrganizationService;

	@Resource(name = "wechatJsAPI")
	private PaymentApi wechatJsAPI;

	@RequestMapping(value = Constants.WXPAY_JSPAY)
	public String weChatJSpay(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> params, ModelMap dataMap) {
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
			if (PaymentType.WECHAT_JSAPI.getPaymentCode().equals(payType)
					|| PaymentType.WECHAT_JSAPI.getPaymentName().equals(payType)) {
				// 微信本地扫码支付
				result = wechatJsAPI.doPay(params);
			}

			Object datas = result.getData();
			if (datas instanceof WechatPaymentResult) {
				WechatPaymentResult wpr = (WechatPaymentResult) result.getData();
				return "redirect:" + wpr.getRedirectURL();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微信认证回调
	 * 
	 * @param request
	 * @param response
	 * @param param
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = Constants.WXPAY_TOPAY)
	public String wxCallback(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param, ModelMap dataMap) {

		param.remove(BaseParam.REDIRECT);
		param.put(BaseParam.PAY_TYPE, PaymentType.WECHAT_JSAPI.getPaymentCode());
		param.put(BaseParam.PAY_AMOUNT, param.get("money"));

		// TODO
		param.put(BaseParam.ORG_ID, "1");
		param.put(BaseParam.USER_ID, "1");

		RpcResult<?> hr = wechatJsAPI.doPay(param);
		String error = "";
		// if (!hr.isSuccess()) {
		// error = hr.getMsgInfo();
		// } else {
		dataMap.put("payparms", hr.getData());
		this.log.debug("payparms:" + dataMap.get("payparms"));
		// }
		// dataMap.put("error", error);
		return "payment/wechat/jspay";
	}
}
