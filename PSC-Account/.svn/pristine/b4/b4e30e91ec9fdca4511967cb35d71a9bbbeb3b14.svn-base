package cn.wellcare.pojo.common;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentNotify {
	//订单号
	private String orderSn;
	//外部订单号
    private String outOrderSn;
	// 推送地址
	private String backUrl;
	// 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss
	private Date notifyTime;
	// 通知校验ID
	private String notifyId;
	// 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	private String signType;
	// 签名
	private String sign;
	// 交易凭证号
	private String tradeNo;
	// 交易目前所处的状态
	private String tradeStatus;
	// 本次交易支付的订单金额，单位为人民币（元）
	private BigDecimal totalAmount;
	// 退款通知中，返回总退款金额，单位为元，支持两位小数
	private BigDecimal refundFee;
	// 实际退款给用户的金额，单位为元，支持两位小数
	private BigDecimal sendBackFee;
	// 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来
	private String body;
	// 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss
	private Date gmtPayment;
	// 该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S
	private Date gmtRefund;
	// 该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss
	private Date gmtClose;

	public Date getNotifyTime() {
		return this.notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getNotifyId() {
		return this.notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getSignType() {
		return this.signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getRefundFee() {
		return this.refundFee;
	}

	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}

	public BigDecimal getSendBackFee() {
		return this.sendBackFee;
	}

	public void setSendBackFee(BigDecimal sendBackFee) {
		this.sendBackFee = sendBackFee;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getGmtPayment() {
		return this.gmtPayment;
	}

	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	public Date getGmtRefund() {
		return this.gmtRefund;
	}

	public void setGmtRefund(Date gmtRefund) {
		this.gmtRefund = gmtRefund;
	}

	public Date getGmtClose() {
		return this.gmtClose;
	}

	public void setGmtClose(Date gmtClose) {
		this.gmtClose = gmtClose;
	}

	/**
	 * @return the backUrl
	 */
	public String getBackUrl() {
		return this.backUrl;
	}

	/**
	 * @param backUrl the backUrl to set
	 */
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOutOrderSn() {
		return outOrderSn;
	}

	public void setOutOrderSn(String outOrderSn) {
		this.outOrderSn = outOrderSn;
	}

	

}
