package cn.wellcare.card.bo.card;

import java.io.Serializable;
import java.math.BigDecimal;

public class RefundInfo implements Serializable {
    private BigDecimal refundAmount;//可退金额
    private String outTradeNo; //可退订单号
    private String payType;
    private boolean succes;

    public RefundInfo() {
    }

    public RefundInfo(BigDecimal refundAmount, String outTradeNo, String payType) {
        this.succes = true;
        this.refundAmount = refundAmount;
        this.outTradeNo = outTradeNo;
        this.payType = payType;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
