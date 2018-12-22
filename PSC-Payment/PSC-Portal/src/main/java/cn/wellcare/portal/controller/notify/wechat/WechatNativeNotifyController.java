package cn.wellcare.portal.controller.notify.wechat;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.web.NotifyBaseController;

/**
 * 微信扫码（主动）支付通知（银行）
 * 
 * @author zhaihl
 *
 */
@RequestMapping(Constants.UNIFY_PAY_CONTEXT)
@Controller
public class WechatNativeNotifyController extends NotifyBaseController {
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = Constants.WXPAY_NATIVE_NOTIFY)
	public void wechatNotify(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param) throws IOException {
		String returnCode = STATUC_SUCCESS;
		String returnMsg = "OK";
		try {
			log.debug("进入微信支付通知");

			ServletInputStream in = request.getInputStream();
			byte[] xmlbytes = IOUtils.toByteArray(in);

			param.put("reqStream", xmlbytes);
			super.invoke("wechatNativeNotifyService", param);
		} catch (Exception e) {
			returnCode = STATUC_FAIL;
			if (e instanceof BusinessException) {
				BusinessException pe = (BusinessException) e;
				returnMsg = pe.getMessage();
			} else {
				e.printStackTrace();
				returnMsg = ErrorEnum.SERVER_EXCEPTION.getErrDesc();
			}
		}

		String noticeStr = setXML(returnCode, returnMsg);
		sendMsg(Constants.CONTENT_TYPE_XML, response, noticeStr);
		log.debug("WechatNativeNotifyController:回复消息:" + noticeStr);
	}

	public static String setXML(String return_code, String returnMsg) {
		return String.format(
				"<xml><return_code><![CDATA[%s]]></return_code><return_msg><![CDATA[%s]]></return_msg></xml>",
				return_code, returnMsg);
	}

}
