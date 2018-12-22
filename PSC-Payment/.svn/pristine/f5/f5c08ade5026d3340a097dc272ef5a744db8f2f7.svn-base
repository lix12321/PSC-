package cn.wellcare.pojo.common;

import java.io.Serializable;

/**
 * 支付返回结果
 * 
 * @author zhaihl
 * @date 2018年10月15日
 */
public class PaymentResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4136276831033390226L;
	private String totalFee;
	private Integer orderId;
	private Integer payOderSn;
	private Integer outerOrderSn;
	private int success = 0;

	public PaymentResult() {
		this.success = 1;
	}

	public PaymentResult(String totalFee, Integer orderId) {
		this.totalFee = totalFee;
		this.setOrderId(orderId);
		this.success = 1;
	}

	public PaymentResult(String totalFee, Integer orderId, Integer payOderSn, Integer outerOrderSn) {
		this.totalFee = totalFee;
		this.orderId = orderId;
		this.payOderSn = payOderSn;
		this.outerOrderSn = outerOrderSn;
		this.success = 1;
	}

	public boolean isSuccess() {
		return this.success == 1;
	}

	public void setSuccess(boolean success) {
		this.success = success ? 1 : 0;
	}

	public String getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return this.orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "PaymentResult [totalFee=" + totalFee + ", orderId=" + orderId + ", success=" + success + "]";
	}

	public Integer getPayOderSn() {
		return this.payOderSn;
	}

	public void setPayOderSn(Integer payOderSn) {
		this.payOderSn = payOderSn;
	}

	public Integer getOuterOrderSn() {
		return this.outerOrderSn;
	}

	public void setOuterOrderSn(Integer outerOrderSn) {
		this.outerOrderSn = outerOrderSn;
	}

	public int getSuccess() {
		return this.success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

}
