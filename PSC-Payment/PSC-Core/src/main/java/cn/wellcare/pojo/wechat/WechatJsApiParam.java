package cn.wellcare.pojo.wechat;

import cn.wellcare.pojo.common.PaymentResult;

public class WechatJsApiParam extends PaymentResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 340991734111110892L;
	private String appid;
	private String timeStamp;
	private String nonceStr;
	private String payPackage;
	private String sign;

	public WechatJsApiParam() {
		super();
	}

	public WechatJsApiParam(String appid, String timeStamp, String nonceStr, String payPackage, String sign) {
		super();
		this.appid = appid;
		this.timeStamp = timeStamp;
		this.nonceStr = nonceStr;
		this.payPackage = payPackage;
		this.sign = sign;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPayPackage() {
		return payPackage;
	}

	public void setPayPackage(String payPackage) {
		this.payPackage = payPackage;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "WechatJsApiParam [appid=" + appid + ", timeStamp=" + timeStamp + ", nonceStr=" + nonceStr
				+ ", payPackage=" + payPackage + ", sign=" + sign + "]";
	}

}
