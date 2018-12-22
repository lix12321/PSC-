package cn.wellcare.pojo.common;


import cn.wellcare.core.constant.Constants;

import java.io.Serializable;

public class AccountPaymentResult implements Serializable {
    private static final long serialVersionUID = 4136276831033390226L;
    private String totalFee;
    private String amtAcc;
    private boolean success;
    private String msg;

    public AccountPaymentResult(String totalFee, String amtAcc) {
        this.totalFee = totalFee;
        this.amtAcc = amtAcc;
        this.success = true;
    }

    public AccountPaymentResult() {
        this.success = true;
        this.msg = Constants.RESONSE_SUCCESS_MSG;
    }

    public AccountPaymentResult(String totalFee, String amtAcc, String msg) {
        this.totalFee = totalFee;
        this.amtAcc = amtAcc;
        this.success = false;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getAmtAcc() {
        return amtAcc;
    }

    public void setAmtAcc(String amtAcc) {
        this.amtAcc = amtAcc;
    }

    @Override
    public String toString() {
        return "AccountPaymentResult [totalFee=" + this.totalFee + ", amtAcc=" + this.amtAcc + ", success=" + this.success
                + ", msg=" + this.msg + "]";
    }

}
