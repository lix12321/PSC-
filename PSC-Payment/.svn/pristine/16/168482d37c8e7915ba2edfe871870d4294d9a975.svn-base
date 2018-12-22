package cn.wellcare.service.transaction.refund.account;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.PayLogHandler;
import cn.wellcare.core.constant.annotations.PaymentLog;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.core.exception.ErrorEnum;
import cn.wellcare.core.exception.UnauthorizedException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.entity.refund.PayRefund;
import cn.wellcare.model.modules.refund.PayRefundModel;
import cn.wellcare.payment.api.RefundPayApi;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.RefundPayResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.support.EnumerateParameter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 账户退费操作
 */
@Service("accountRefundService")
public class AccountRefundService implements RefundPayApi {

    @Resource
    private IOrderService orderService;
    @Resource
    private PayRefundModel payRefundModel;
    @PaymentLog(PayLogHandler.CREATE)
    @Override
    public RpcResult<RefundPayResult> refundPay(Map<String, Object> param) {
        RpcResult<RefundPayResult> result = new RpcResult<>();

        try {
            //1.查询订单信息、更新订单信息
            String orderNo = String.valueOf(param.get(BaseParam.OUT_TRADE_NO));//退款订单号
            BigDecimal refundAmount = new BigDecimal(String.valueOf(param.get(BaseParam.REFUND_AMOUNT)));//退款金额

            RpcResult<PayOrder> payOrder = orderService.getOrderByOuterSn(orderNo);// 查询出退款的订单信息
            PayOrder order = payOrder.getData();
            String payAmount = String.valueOf(order.getMoneyOrder());
            LocalDateTime localDateTime=LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String outRequestNo = localDateTime.format(dateTimeFormatter);

            if (order.getPaymentStatus().equals(EnumerateParameter.ZERO)) {
                throw new BusinessException("此订单未支付，不能进行退款操作");
            }
            if (order.getMoneyOrder().compareTo(refundAmount) < 0) {
                throw new BusinessException("退费金额大于订单金额");
            }
            order.setOrderState(Integer.valueOf(EnumerateParameter.TWO));
            orderService.updateOrder(order);//更新订单状态为退款中
            //创建退款信息
            PayRefund payRefund = new PayRefund();
            payRefund.setIspartial(Integer.valueOf(EnumerateParameter.ZERO));
            payRefund.setStatus(Integer.valueOf(EnumerateParameter.ONE));
            payRefund.setRefundAmount(refundAmount);
            payRefund.setOrderId(order.getId());
            payRefund.setApplyNo(outRequestNo);
            payRefundModel.savePayRefund(payRefund);

            result.setData(new RefundPayResult(orderNo, payAmount, String.valueOf(refundAmount),payRefund.getId()));
        } catch (Exception e) {
            result.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode())) {
                    result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
                } else {
                    result.setMsgInfo(e.getMessage());
                }
                result.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
            } else {
                if (e instanceof UnauthorizedException) {
                    result.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
                } else {
                    e.printStackTrace();
                    result.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                }
            }
            throw e;
        }
        return result;
    }
}
