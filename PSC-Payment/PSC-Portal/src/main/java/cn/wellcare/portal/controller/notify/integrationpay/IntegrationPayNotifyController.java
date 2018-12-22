package cn.wellcare.portal.controller.notify.integrationpay;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.web.NotifyBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 聚合支付异步通知
 */
@RequestMapping(Constants.UNIFY_PAY_CONTEXT)
@Controller
public class IntegrationPayNotifyController extends NotifyBaseController {
    @RequestMapping(Constants.INTEGRATIONPAY_NATIVE_NOTIFY)
    public void integrationPay(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam Map<String, Object> param) throws IOException {
        super.invoke("integrationpayNotifyService",param);
        if (super.result.getData()) {
            sendMsg(response,STATUC_SUCCESS.toLowerCase());
        } else {
            sendMsg(response, STATUC_FAIL.toLowerCase());
        }
    }
}
