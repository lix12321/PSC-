package cn.wellcare.service.transaction.refund.cash;

import cn.wellcare.core.constant.BaseParam;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class CashPayRefundService implements RefundPayApi {
    @Resource
    private IOrderService orderService;
    @Resource
    private PayRefundModel payRefundModel;
    @Override
    public RpcResult<RefundPayResult> refundPay(Map<String, Object> param) throws Exception {
        RpcResult<RefundPayResult> result = new RpcResult<>();
        try {
            String orderNo = String.valueOf(param.get(BaseParam.OUT_TRADE_NO));
            String refundAmount = String.valueOf(param.get(BaseParam.REFUND_AMOUNT));
            String requestSn = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 请求序列码
            RpcResult<PayOrder> payOrder = orderService.getOrderByOuterSn(orderNo);// 查询出退款的订单信息
            PayOrder order = payOrder.getData();
            if (order.getPaymentStatus().equals(EnumerateParameter.ZERO)) {
                throw new BusinessException("此订单未支付，不能进行退款操作");
            }
            //更新订单状态为退款中
            order.setOrderState(Integer.valueOf(EnumerateParameter.THREE));
            order.setUpdateTime(new Date());
            orderService.updateOrder(order);
            // 创建退款信息
            PayRefund payRefund = new PayRefund();
            payRefund.setIspartial(Integer.valueOf(EnumerateParameter.ZERO));
            payRefund.setStatus(Integer.valueOf(EnumerateParameter.THREE));//由于现金退款特殊性，估只需要直接创建退款信息
            payRefund.setRefundAmount(new BigDecimal(refundAmount));
            payRefund.setOrderId(order.getId());
            payRefund.setApplyNo(requestSn);
            payRefundModel.savePayRefund(payRefund);

            param.put("orderInfo", order);
            result.setData(new RefundPayResult(orderNo, order.getMoneyOrder().toString(), refundAmount));
        }catch (Exception e) {
            result.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
                    result.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
                else
                    result.setMsgInfo(e.getMessage());
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
