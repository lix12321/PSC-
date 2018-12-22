package cn.wellcare.pojo.common;

import java.io.Serializable;

import cn.wellcare.core.constant.Constants;

/**
 * 退款返回结果
 *
 */
public class RefundPayResult implements Serializable {
    private static final long serialVersionUID = -9046352664490755734L;
    private String orderNum;//订单号
    private String payAmount;//订单金额
    private String amount;//退款金额
    private Integer id;
    private Integer success;
	private String msg = "请求成功";

    public RefundPayResult(String orderNum, String payAmount, String amount) {
        this.orderNum = orderNum;
        this.payAmount = payAmount;
        this.amount = amount;
        this.success = 1;
    }

    public RefundPayResult(String orderNum, String payAmount, String amount, String msg) {
        this.orderNum = orderNum;
        this.payAmount = payAmount;
        this.amount = amount;
        this.msg = msg;
        this.success = 0;
    }

    public RefundPayResult(String orderNum, String payAmount, String amount, Integer id) {
        this.orderNum = orderNum;
        this.payAmount = payAmount;
        this.amount = amount;
        this.id = id;
        this.success = 1;
    }

    public RefundPayResult() {
        this.success = 1;
        this.msg = Constants.RESONSE_SUCCESS_MSG;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer isSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RefundPayResult{" +
                "orderNum='" + orderNum + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", amount='" + amount + '\'' +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
