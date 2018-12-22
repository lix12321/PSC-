package cn.wellcare.pojo.alipay;

import cn.wellcare.pojo.common.PaymentResult;

public class AlipayPaymentResult extends PaymentResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6538867778383689664L;
	// 支付二维码
	private String codeUrl;

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

	public AlipayPaymentResult() {
	}

	public AlipayPaymentResult(String totalFee, Integer orderId, String codeUrl) {
		super(totalFee, orderId);
		this.codeUrl = codeUrl;
	}
}
