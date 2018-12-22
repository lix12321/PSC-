package cn.wellcare.portal.controller.demo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.core.bean.ServiceLocator;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.utils.CommonUtils;
import cn.wellcare.core.utils.Md5SignUtil;
import cn.wellcare.payment.modules.system.ISysOrganizationService;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.AccountNotifyBaseController;

@RequestMapping("demo/unifyPayAccount")
@Controller
public class DemoAccoutController extends AccountNotifyBaseController {
	@Override
	protected void setParams(Map<String, Object> params) {

		Map<String, Object> result = CommonUtils.sort(params);
		this.log.debug("客户端排序后参数集：" + result);

		ISysOrganizationService orgService = (ISysOrganizationService) ServiceLocator.getInstance()
				.getBean("sysOrganizationService");
		String securtkey = orgService.getSysOrganizationById(Integer.valueOf((String) params.get(BaseParam.ORG_ID)))
				.getData().getAuthSecret();
		log.debug("机构密钥：" + securtkey);
		String sign = Md5SignUtil.sginMD5(result, securtkey);
		params.put("sign", sign);

		super.setParams(params);
	}

	@RequestMapping(value = Constants.DO_ACCPAY, produces = Constants.CONTENT_TYPE_JSON)
	@ResponseBody
	public RpcResult<PaymentResult> wechatSaoMaPay(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param) {
		RpcResult<PaymentResult> result = super.result;
		Boolean accountBoolean = super.booleanResult;
		return result;
	}
}
