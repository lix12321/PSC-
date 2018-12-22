package cn.wellcare.pojo.wechat;

import cn.wellcare.pojo.common.PaymentResult;

public class WechatPaymentResult extends PaymentResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3099501531166609251L;
	public static final int REQUEST_REDIRECT = 302;
	public static final int REQUEST_OK = 200;
	// 支付二维码
	private String codeUrl;
	// 请求码
	private int requestCode;
	// 重写向URL
	private String redirectURL;

	public WechatPaymentResult() {
		this.requestCode = REQUEST_OK;
	}

	public WechatPaymentResult(int codetype, String redirectURL) {
		this.requestCode = codetype;
		this.redirectURL = redirectURL;
	}

	public WechatPaymentResult(String totalFee, Integer orderId, String codeUrl) {
		super(totalFee, orderId);
		this.codeUrl = codeUrl;
		this.requestCode = REQUEST_OK;
	}

	/**
	 * @return the codeUrl
	 */
	public String getCodeUrl() {
		return this.codeUrl;
	}

	/**
	 * @param codeUrl the codeUrl to set
	 */
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

}
