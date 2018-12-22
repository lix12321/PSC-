package cn.wellcare.portal.controller.demo.notify;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wellcare.web.NotifyBaseController;

/**
 * 账户支付模拟通知（客户端）
 * 
 * @author zhaihl
 *
 */
@RequestMapping("demo")
@Controller
public class DemoNotifyController extends NotifyBaseController {
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "serverNotify")
	public void wechatSaoMaPay(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> param) throws IOException {
		log.debug("=========模拟通知=========");
		log.debug("接收参数：" + param);

		sendMsg(response, STATUC_SUCCESS);
	}
}
