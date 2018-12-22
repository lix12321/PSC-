package cn.wellcare.pojo.common;

import cn.wellcare.core.constant.Constants;

public class OrderResult {

    private String orderId;//订单编号
    private String amount;//订单金额
    private String status;//状态
    private String refund;//退款金额
    private boolean success;
    private String msg;

    public OrderResult() {
        this.success = true;
        this.msg = Constants.RESONSE_SUCCESS_MSG;
    }

    public OrderResult(String orderId, String amount, String status, String refund, String msg) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.refund = refund;
        this.msg = msg;
        this.success = false;
    }

    public OrderResult(String orderId, String amount, String status, String refund) {
        this.success = true;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.refund = refund;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
