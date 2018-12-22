package cn.wellcare.portal.controller.demo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.web.TradeController;

@RequestMapping("demo/order")
@Controller
public class DemoTradeController extends TradeController {


	@RequestMapping(value = Constants.QUERY_PAY, produces = Constants.CONTENT_TYPE_JSON)
    @ResponseBody
    public RpcResult<OrderResult> queryOrderId(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam Map<String, Object> param) {
        return super.orderResult;
    }
}
