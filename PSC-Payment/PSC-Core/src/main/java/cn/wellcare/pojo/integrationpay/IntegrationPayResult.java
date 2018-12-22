package cn.wellcare.pojo.integrationpay;

import cn.wellcare.pojo.common.PaymentResult;

public class IntegrationPayResult extends PaymentResult {
    // 支付二维码
    private String codeUrl;

	public IntegrationPayResult(String totalFee, Integer orderId, String codeUrl) {
		super(totalFee, orderId);
        this.codeUrl = codeUrl;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
