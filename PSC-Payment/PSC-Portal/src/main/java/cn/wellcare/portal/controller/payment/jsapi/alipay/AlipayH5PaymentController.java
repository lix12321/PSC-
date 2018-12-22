package cn.wellcare.portal.controller.payment.jsapi.alipay;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.core.bean.DomainUrlUtil;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.payment.api.PaymentApi;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.BaseController;

/**
 * 
 * @author zhaihl
 *
 */
@RequestMapping("alipay")
@Controller
public class AlipayH5PaymentController extends BaseController {
	@Resource(name = "wechatJsAPI")
	private PaymentApi wechatJsAPI;
	@Resource
	private ISysOrganizationService sysOrganizationService;

	@RequestMapping(value = Constants.ALIPAY_H5_PAY)
	public String payGateway(HttpServletRequest request, HttpServletResponse response,
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

			if (PaymentType.WECHAT_JSAPI.getPaymentCode().equals(payType)
					|| PaymentType.WECHAT_JSAPI.getPaymentName().equals(payType)) {
				// 微信JS API
				result = wechatJsAPI.doPay(params);
			}
			if (result.isSuccess()) {
				return "payment/alipay/jspay";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new BusinessException(ErrorEnum.ILLEGAL_REQUEST.getErrDesc());
	}

}
