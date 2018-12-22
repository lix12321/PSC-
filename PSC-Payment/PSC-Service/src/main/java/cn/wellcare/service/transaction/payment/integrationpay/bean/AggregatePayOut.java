package cn.wellcare.service.transaction.payment.integrationpay.bean;

import javax.xml.bind.annotation.XmlElement;

public class AggregatePayOut {
    private String orderNum;
    private String payAmount;
    private String amount;
    private String rem1;
    private String rem2;
    @XmlElement(name="ORDER_NUM")
    public String getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    @XmlElement(name="PAY_AMOUNT")
    public String getPayAmount() {
        return payAmount;
    }
    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
    @XmlElement(name="AMOUNT")
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    @XmlElement(name="REM1")
    public String getREM1() {
        return rem1;
    }
    public void setREM1(String rem1) {
        this.rem1 = rem1;
    }
    @XmlElement(name="REM2")
    public String getREM2() {
        return rem2;
    }
    public void setREM2(String rem2) {
        this.rem2 = rem2;
    }
}
