package cn.wellcare.portal.controller.notify.alipay;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.web.NotifyBaseController;

/**
 * 支付宝异步通知
 * 
 * @author zhaihl
 *
 */
@RequestMapping(Constants.UNIFY_PAY_CONTEXT)
@Controller
public class AlipayNotifyController extends NotifyBaseController {

	@RequestMapping(value = Constants.ALIPAY_NATIVE_NOTIFY)
	public void wechatSaoMaPay(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param) throws IOException {

		super.invoke("alipayNotifyService", param);
		sendMsg(response, STATUC_SUCCESS.toLowerCase());
	}
}
