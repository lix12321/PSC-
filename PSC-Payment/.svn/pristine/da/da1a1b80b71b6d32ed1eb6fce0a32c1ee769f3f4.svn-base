package cn.wellcare.service.transaction.payment.integrationpay.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="shop",propOrder ={"requestSn","custId","txCode","returnCode","returnMsg","language","aggregatePayOut"})
@XmlRootElement(name="TX")
public class AggregatePayOutVo {
    private String requestSn;//请求序列号
    private String custId;//商户号
    private String txCode;//交易码
    private String returnCode;//响应码
    private String returnMsg; //响应信息
    private String language; //语言
    private AggregatePayOut aggregatePayOut;
    @XmlElement(name="REQUEST_SN")
    public String getRequestSn() {
        return requestSn;
    }
    public void setRequestSn(String requestSn) {
        this.requestSn = requestSn;
    }
    @XmlElement(name="CUST_ID")
    public String getCustId() {
        return custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    @XmlElement(name="TX_CODE")
    public String getTxCode() {
        return txCode;
    }
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }
    @XmlElement(name="RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    @XmlElement(name="RETURN_MSG")
    public String getReturnMsg() {
        return returnMsg;
    }
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    @XmlElement(name="LANGUAGE")
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    @XmlElement(name="TX_INFO")
    public AggregatePayOut getAggregatePayOut() {
        return aggregatePayOut;
    }
    public void setAggregatePayOut(AggregatePayOut aggregatePayOut) {
        this.aggregatePayOut = aggregatePayOut;
    }

}

