package cn.wellcare.portal.controller.refundpay.account;

import cn.wellcare.api.trade.IPscAccRefundService;
import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.refund.PayRefund;
import cn.wellcare.payment.api.RefundPayApi;
import cn.wellcare.payment.modules.refund.IPayRefundService;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.AccPaymentResult;
import cn.wellcare.pojo.common.RefundPayResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.pojo.common.ServiceResult;
import cn.wellcare.support.EnumerateParameter;
import cn.wellcare.web.AccountRefundController;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RequestMapping(Constants.UNIFIED_REFUND)
@Controller
public class AccountRefundPayController extends AccountRefundController {

    @Resource(name = "refundService")
    private IPscAccRefundService refundService;
    @Resource(name = "accountRefundService")
    private RefundPayApi accountRefundService;
    @Resource(name = "orderService")
    private IOrderService orderService;
    @Resource(name = "payRefundService")
    private IPayRefundService payRefundService;

    @RequestMapping(value = Constants.ACCOUNT_REFUNDPAY,produces = Constants.CONTENT_TYPE_JSON)
    @ResponseBody
    public ServiceResult<RefundPayResult> accountPayRefund(HttpServletRequest request, HttpServletResponse response,
                                                           @RequestParam Map<String, Object> param) {
        RpcResult<PayOrder> orderRpcResult = new RpcResult<>();
        RpcResult<RefundPayResult> resultRpcResult = new RpcResult<>();
        RpcResult<PayRefund> payRefundRpcResult = new RpcResult<>();
        RpcResult<AccPaymentResult> payResult = new RpcResult<>();
        PayOrder payOrder = new PayOrder();
        PayRefund payRefund = new PayRefund();
        try {
            Assert.notNull(param.get(BaseParam.USER_ID));
            Assert.notNull(param.get(BaseParam.ORG_ID));
            Assert.notNull(param.get(BaseParam.OUT_TRADE_NO));//退款订单号
            Assert.notNull(param.get(BaseParam.REFUND_AMOUNT));//退款金额
            String ouTradeNo = String.valueOf(param.get(BaseParam.OUT_TRADE_NO));

            //1.调用账户退款服务
            resultRpcResult = accountRefundService.refundPay(param);
            if (!resultRpcResult.isSuccess()) {
                throw new BusinessException(resultRpcResult.getMsgInfo());
            }
            payResult = refundService.accRefund(param);
            if (!payResult.isSuccess()) {
                throw new BusinessException(payResult.getMsgInfo());
            }
            //2.更新退款表
            //查询退款信息
            payRefundRpcResult = payRefundService.getPayRefundById(resultRpcResult.getData().getId());
            if (!payRefundRpcResult.isSuccess()) {
                throw new BusinessException(payRefundRpcResult.getMsgInfo());
            }
            payRefund.setStatus(Integer.valueOf(EnumerateParameter.THREE));
            payRefundService.updatePayRefund(payRefund);

            //3.更新订单状态
            orderRpcResult = orderService.getOrderByOuterSn(ouTradeNo);
            if (!orderRpcResult.isSuccess()) {
                throw new BusinessException(orderRpcResult.getMsgInfo());
            }
            payOrder = orderRpcResult.getData();
            payOrder.setUpdateTime(new Date());
            payOrder.setOrderState(Integer.valueOf(EnumerateParameter.THREE));
            payOrder.setMoneyPaidBalance(new BigDecimal(payResult.getData().getTotalFee()));
            orderService.updateOrder(payOrder); //更新订单状态及订单金额等信息
        } catch (Exception e) {
            refundPayResult.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.SERVER_EXCEPTION.getErrCode().equals(pe.getCode())) {
                    refundPayResult.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                } else {
                    refundPayResult.setMsgInfo(e.getMessage());
                }
            } else {
                if (e instanceof UnauthorizedException) {
                    refundPayResult.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
                } else {
                    e.printStackTrace();
                    refundPayResult.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                }
            }
        }
        return new ServiceResult<RefundPayResult>().convert2SR(resultRpcResult);
    }
}
