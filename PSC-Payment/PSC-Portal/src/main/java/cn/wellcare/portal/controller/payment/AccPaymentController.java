package cn.wellcare.portal.controller.payment;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.pojo.common.PaymentResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.AccountNotifyBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping(Constants.UNIFY_PAY_CONTEXT)
@Controller
public class AccPaymentController extends AccountNotifyBaseController {
    @RequestMapping(value = Constants.DO_ACCPAY, produces = Constants.CONTENT_TYPE_JSON)
    @ResponseBody
    public RpcResult<PaymentResult> accountPayPayment(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestParam Map<String, Object> param) {
        return super.result;
    }
}
