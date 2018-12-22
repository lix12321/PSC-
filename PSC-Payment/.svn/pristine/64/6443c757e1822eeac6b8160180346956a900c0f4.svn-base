package cn.wellcare.service.transaction.payment.integrationpay.bean;

import cn.wellcare.pojo.common.OrderResult;
import cn.wellcare.pojo.common.RpcResult;

import javax.xml.bind.annotation.XmlElement;

public class AggregatePayOrderResult extends OrderResult {
    private String merchantId;//商户编码
    private String branchId;//分行编码
    private String posId;//pos编号
    private String orderId;//订单编号
    private String orderDate;//订单日期
    private String accDate;//记账日期
    private String amount;//订单金额
    private String statusCode;//状态码
    private String status;//状态
    private String refund;//退款金额
    private String sign;//验签
    private String returnCode;
    private String returnMsg;
    private String accId;


    @XmlElement(name = "MERCHANTID")
    public String getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    @XmlElement(name = "BRANCHID")
    public String getBranchId() {
        return branchId;
    }
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    @XmlElement(name = "POSID")
    public String getPosId() {
        return posId;
    }
    public void setPosId(String posId) {
        this.posId = posId;
    }
    @Override
    @XmlElement(name = "ORDERID")
    public String getOrderId() {
        return orderId;
    }
    @Override
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    @XmlElement(name = "ORDERDATE")
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    @XmlElement(name = "ACCDATE")
    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }
    @XmlElement(name = "STATUSCODE")
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    @Override
    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "SIGN")
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

    public AggregatePayOrderResult(String orderId, String orderDate, String accDate, String amount, String statusCode, String status, String refund) {
        super(orderId,status);
        this.orderDate = orderDate;
        this.accDate = accDate;
        this.statusCode = statusCode;
    }

    public AggregatePayOrderResult(String orderId,  String status,String accId,  String orderDate, String statusCode,  String sign ,String returnCode,String returnMsg) {
        super(orderId,  status);
        this.orderDate = orderDate;
        this.statusCode = statusCode;
        this.sign = sign;
        this.accId = accId;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }
}
