package cn.wellcare.acc.bo;

import java.math.BigDecimal;

public class RechargeParamDto {
	// 账户id
	private Integer accId;
	// 姓名
	private String accName;
	// 机构
	private Integer orgId;
	// 订单id
	private Integer orderId;
	// 发生金额
	private BigDecimal money;
	// 充值类型
	private String rechargeType;
	// 操作员姓名
	private String handleName;

	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAccName() {
		return this.accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRechargeType() {
		return this.rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getHandleName() {
		return this.handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

}
