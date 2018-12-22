package cn.wellcare.service.transaction.payment.wechat.base;

import cn.wellcare.pojo.common.OrderResult;

/**
 * 微信订单查询VO类
 */
public class WechatTradeResult extends OrderResult {

    private String orderDate;//订单日期
    private String sign;//验签
    private String returnCode;
    private String returnMsg;
    private String accId;
    private String statusCode;//状态码

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public WechatTradeResult(String orderId,  String status,String accId,  String orderDate, String statusCode,  String sign ,String returnCode,String returnMsg) {
        super(orderId,status);
        this.orderDate = orderDate;
        this.statusCode = statusCode;
        this.sign = sign;
        this.accId = accId;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }
}
