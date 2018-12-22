package cn.wellcare.service.transaction.query;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wellcare.core.constant.BaseParam;
import cn.wellcare.core.constant.PaymentType;
import cn.wellcare.core.exception.BusinessException;
import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.api.PaymentOrderApi;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;
import cn.wellcare.service.transaction.query.alipay.AlipayOrderService;
import cn.wellcare.service.transaction.query.alipay.AlipayRefundOrderService;
import cn.wellcare.service.transaction.query.integration.IntegrationOrderService;
import cn.wellcare.service.transaction.query.wechat.WechatQueryRefundService;
import cn.wellcare.service.transaction.query.wechat.WechatQueryService;
import cn.wellcare.support.EnumerateParameter;

@Service("tradeOrderService")
public class TradeOrderService implements PaymentOrderApi {
	@Resource
	private IntegrationOrderService integrationOrderService;
	@Resource
	private AlipayOrderService alipayOrderService;
    @Resource
    private AlipayRefundOrderService alipayRefundOrderService;
	@Resource
	private WechatQueryService wechatQueryService;
    @Resource
    private WechatQueryRefundService queryService;
    @Resource
    private IOrderService orderService;

    @Override
    public RpcResult<OrderResult> getOrderBySn(Map<String, Object> param) {
        RpcResult<OrderResult>  result =new RpcResult<>();
        String orderNo = String.valueOf(param.get(BaseParam.ORDER_ID));//获取订单号
		RpcResult<PayOrder> payOrder = orderService.getOrderByOuterSn(orderNo);// 查询订单信息
        PayOrder order = payOrder.getData();
        if (order == null) {
            throw new BusinessException("平台中心未查询到订单信息");
        }
        String tradeType = order.getOrderType(); // 测试时先注释掉

        if (tradeType.equals(PaymentType.JUHPAY.getPaymentCode())) {
            // 目前先测试聚合支付查询
            result.setData(integrationOrderService.getOrderBySn(param, order));
        } else if (tradeType.equals(PaymentType.UNIONPAY.getPaymentCode())) {
            // 其他（银联）
        } else if (tradeType.equals(PaymentType.MISPOS)) {
            // misPOS相关
        } else if (tradeType.equals(PaymentType.WECHAT_NATIVE.getPaymentCode()) || tradeType.equals(PaymentType.WECHAT_JSAPI.getPaymentCode())
                || tradeType.equals(PaymentType.WECHAT_SAOMA.getPaymentCode())) {
            // 微信订单查询
            if (String.valueOf(order.getOrderState()).equals(EnumerateParameter.THREE)) {
                result.setData(queryService.queryOrderBySn(param,order));//退款订单查询
            }else {
                result.setData(wechatQueryService.getOrderBySn(param, order));
            }
        } else {
            // 支付宝订单查询
            if (String.valueOf(order.getOrderState()).equals(EnumerateParameter.THREE)) {
                result.setData(alipayRefundOrderService.getOrderBySn(param,order));
            } else {
                result.setData(alipayOrderService.getOrderBySn(param, order));
            }
        }
        return result;
    }

}
