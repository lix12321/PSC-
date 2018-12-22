package cn.wellcare.portal.controller.payment.jsapi.wechat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.web.BaseController;

/**
 * 微信JS支付结果
 * 
 * @author zhaihl
 *
 */
@RequestMapping("wxJSpay")
@Controller
public class JsApiResultController extends BaseController {
	/**
	 * 前台支付成功页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = Constants.WXPAY_JSAPI_RESULT, method = RequestMethod.GET)
	public String paysuccess(HttpServletRequest request, HttpServletResponse response, ModelMap dataMap, String res,
			Integer state) {
		try {
			res = URLDecoder.decode(res, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		dataMap.put("state", state != null && state == 1 ? "success" : "fail");
		dataMap.put("info", res);
		return "payment/wechat/payresult";
	}
}
