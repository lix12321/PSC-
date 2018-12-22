package cn.wellcare.service.transaction.payment.account;

import java.util.Map;

import javax.annotation.Resource;

import cn.wellcare.entity.order.PayOrder;
import cn.wellcare.payment.order.IOrderService;
import cn.wellcare.payment.unifyPay.UnifyPaymentInfo;

/**
 * 账户支付生成订单等信息
 */
public abstract class AccountPayment {

    @Resource
    private IOrderService OrderService;
    public PayOrder payBefore(Map<String, Object> params)  {
        // 1.创建订单
        PayOrder po = this.OrderService.createOrder(params, new UnifyPaymentInfo() {
            @Override
            public String getOrderType() {
                return getaccountPayOrderType();
            }

            @Override
            public String getPaymentName() {
                return getaccountPayPaymentName();
            }
        });
        // 2.返回订单信息
        return po;
    }
    protected abstract String getaccountPayOrderType();

    protected abstract String getaccountPayPaymentName();
}
