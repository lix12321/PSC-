package cn.wellcare.portal.controller.trade;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.TradeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
@RequestMapping(Constants.UNIFY_PAY_CONTEXT)
@Controller
public class UnifyTradeController extends TradeController {
    @RequestMapping(value = Constants.QUERY_ORDERID,produces = Constants.CONTENT_TYPE_JSON)
    @ResponseBody
    public RpcResult<OrderResult> queryOrderId(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam Map<String, Object> param) {
        return super.orderResult;
    }
}
