package cn.wellcare.service.transaction.payment.integrationpay.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="shop",propOrder ={"requestSn","custId","userId","passWord","txCode","language","aggregatePay","signInfo","signcert"})
@XmlRootElement(name="TX")
public class AggregatePayVo {
    private String requestSn;//请求序列号
    private String custId;//商户号
    private String userId;//操作员号
    private String passWord;//操作员交易密码
    private String txCode;//交易码
    private String language;//语言
    private String signInfo; //签名信息
    private String signcert; //签名CA信息 socket连接时由建行客户端生成
    private AggregatePay aggregatePay;
    @XmlElement(name="TX_INFO")
    public AggregatePay getAggregatePay() {
        return aggregatePay;
    }
    public void setAggregatePay(AggregatePay aggregatePay) {
        this.aggregatePay = aggregatePay;
    }
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
    @XmlElement(name="USER_ID")
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @XmlElement(name="PASSWORD")
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    @XmlElement(name="TX_CODE")
    public String getTxCode() {
        return txCode;
    }
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }
    @XmlElement(name="LANGUAGE")
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    @XmlElement(name="SIGN_INFO")
    public String getSignInfo() {
        return signInfo;
    }
    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }
    @XmlElement(name="SIGNCERT")
    public String getSigncert() {
        return signcert;
    }
    public void setSigncert(String signcert) {
        this.signcert = signcert;
    }

}
