package cn.wellcare.service.transaction.payment.integrationpay.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class AggregatePay {

    private String money; //退款金额
    private String order; //订单号
    private String refundCode; //退款流水号

    @XmlElement(name="MONEY")
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    @XmlElement(name="ORDER")
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    @XmlElement(name="REFUND_CODE")
    public String getRefundCode() {
        return refundCode;
    }
    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

}
