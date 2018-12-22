package cn.wellcare.pojo.unionpay;

import cn.wellcare.pojo.common.PaymentResult;

/**
 * 银联响应结果
 * 
 * @author zhaihl
 *
 */
public class UnionpayResult extends PaymentResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 612178013850896419L;
	// 编码方式
	private String encoding;
	// 商户订单号
	private Integer orderId;
	// 二维码
	private String codeUrl;
	// 响应码
	private String respCode;
	// 应答信息
	private String respMsg;
	// 订单发送时间
	private String txnTime;
	// 版本号
	private String version;

	public UnionpayResult() {
		super();
	}

	public UnionpayResult(String encoding, Integer orderId, String codeUrl, String respCode, String respMsg,
			String txnTime, String version) {
		super();
		this.encoding = encoding;
		this.orderId = orderId;
		this.codeUrl = codeUrl;
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.txnTime = txnTime;
		this.version = version;
	}

	public String getEncoding() {
		return this.encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public Integer getOrderId() {
		return this.orderId;
	}

	@Override
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCodeUrl() {
		return this.codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return this.respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getTxnTime() {
		return this.txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
