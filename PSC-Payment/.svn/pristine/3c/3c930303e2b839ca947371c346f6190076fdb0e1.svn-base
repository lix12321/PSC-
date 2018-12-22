package cn.wellcare.service.transaction.refund.wechat;

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
import cn.wellcare.pojo.wechat.WXPay;
import cn.wellcare.pojo.wechat.WXPayConstants;
import cn.wellcare.service.settins.WechatSettings;
import cn.wellcare.support.EnumerateParameter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("wxNativeRefundPayService")
public class WxNativeRefundPayService implements RefundPayApi {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private IOrderService orderService;
	@Resource
	private PayRefundModel payRefundModel;
	@Resource
	private WechatSettings wechatSettings;

	@Override
	public RpcResult<RefundPayResult> refundPay(Map<String, Object> param) throws Exception {
        RpcResult<RefundPayResult> sr = new RpcResult<>();
        try {

            wechatSettings.init(Integer.valueOf(String.valueOf(param.get(BaseParam.ORG_ID))));
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String outRefundNo = localDateTime.format(dateTimeFormatter);// 商品退单号（部分退时需唯一）
            Map<String, String> resultMap = new HashMap<String, String>();
            Map<String, String> dataMap = new HashMap<String, String>();
            String outTradeNo = String.valueOf(param.get(BaseParam.OUT_TRADE_NO));// 商品交易号
            String refundAmount = String.valueOf(param.get(BaseParam.REFUND_AMOUNT));
            // 更新订单状态为退款中
            RpcResult<PayOrder> payOrder = orderService.getOrderByOuterSn(outTradeNo);// 查询出退款的订单信息
            PayOrder order = payOrder.getData();
            if (order.getPaymentStatus().equals(EnumerateParameter.ZERO)) {
                throw new BusinessException("此订单未支付，不能进行退款操作");
            }
            order.setOrderState(Integer.valueOf(EnumerateParameter.TWO));
            orderService.updateOrder(order);
            // 创建退款信息
            PayRefund payRefund = new PayRefund();
            payRefund.setIspartial(Integer.valueOf(EnumerateParameter.ZERO));
            payRefund.setStatus(Integer.valueOf(EnumerateParameter.ONE));
            payRefund.setRefundAmount(new BigDecimal(refundAmount));
            payRefund.setOrderId(order.getId());
            payRefund.setApplyNo(outRefundNo);
            payRefundModel.savePayRefund(payRefund);
            // 初始化商户app_id,mch_id,sign等认证
            WXPay wxpay;
            if (wechatSettings.isSandboxnewOpen()) {
                wxpay = new WXPay(wechatSettings, WXPayConstants.SignType.MD5, true);
            } else {
                wxpay = new WXPay(wechatSettings);
            }

            // 退费的总金额（分）
            BigDecimal needsPay = new BigDecimal(refundAmount);
            needsPay = needsPay.multiply(new BigDecimal(100));
            String txnAmt = needsPay.toString().split("\\.")[0]; // 付款金额，单位为分，不能有小数点，去掉

            dataMap.put("out_trade_no", order.getPaySn());
            dataMap.put("out_refund_no", outRefundNo);
            // dataMap.put("fee_type", "CNY"); // CNY 币种
            dataMap.put("total_fee",
                    String.valueOf(order.getMoneyOrder().multiply(new BigDecimal(100))).split("\\.")[0]); // 订单金额
            dataMap.put("refund_fee", txnAmt); // 退款金额

            resultMap = wxpay.refund(dataMap);// 调用微信退费

            String returnCode = resultMap.get("return_code"); // SUCCESS
            String returnMsg = resultMap.get("return_msg"); // OK
            if (returnCode.equalsIgnoreCase("FAIL")) {
                throw new BusinessException(String.format("交易失败码：%s, 返回信息：%s;", returnCode, returnMsg));
            }
            String resultCode = resultMap.get("result_code");
            if (resultCode.equalsIgnoreCase("FAIL")) {
                throw new BusinessException(
                        String.format("交易失败码：%s, 返回信息：%s;", resultMap.get("err_code"), resultMap.get("err_code_des")));
            }
            String refundId = resultMap.get("refund_id"); // 微信退款单号（流水号）
            logger.error(String.format("返回交易码：%s, 退款单号：%s, 退款id: %s", resultCode, outTradeNo, refundId));
            // 更新退款表
            payRefund.setStatus(Integer.valueOf(EnumerateParameter.THREE));
            payRefund.setTradeSn(refundId);
            payRefundModel.updatePayRefund(payRefund);
            // 更新订单
            order.setOrderState(Integer.valueOf(EnumerateParameter.THREE));
            order.setUpdateTime(new Date());
            orderService.updateOrder(order); // 更新订单状态
            param.put("orderInfo", order);

            sr.setData(new RefundPayResult(outTradeNo, String.valueOf(order.getMoneyOrder()),
                    String.valueOf(param.get(BaseParam.REFUND_AMOUNT))));
        } catch (Exception e) {
            sr.setSuccess(false);
            if (e instanceof BusinessException) {
                BusinessException pe = (BusinessException) e;
                if (pe.getCode() != null && ErrorEnum.BUSINESS_EXCEPTION.getErrCode().equals(pe.getCode()))
                    sr.setMsgInfo(ErrorEnum.BUSINESS_EXCEPTION.getErrDesc());
                else
                    sr.setMsgInfo(e.getMessage());
                sr.setMsgCode(ErrorEnum.BUSINESS_EXCEPTION.getErrCode());
            } else {
                if (e instanceof UnauthorizedException) {
                    sr.setError(ErrorEnum.UNAUTHORIZED_EXCEPTION);
                } else {
                    e.printStackTrace();
                    sr.setMsgInfo(ErrorEnum.SERVER_EXCEPTION.getErrDesc());
                }
            }
            throw e;
        }
        return sr;
    }
}
