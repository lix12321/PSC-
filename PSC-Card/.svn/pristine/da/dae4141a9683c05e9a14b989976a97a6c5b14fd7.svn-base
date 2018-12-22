package cn.wellcare.card.bo.card;

import java.io.Serializable;

public class PayOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4444122293474423898L;
	private Integer accountId; // 账户ID
	private java.lang.String handleNum; // 操作员工号
	private java.lang.String handleName; // 操作员姓名
	private java.util.Date createTime; // 创建时间
	private java.util.Date finishTime; // 订单完成时间
	private Integer id; // id
	private java.math.BigDecimal moneyOrder; // 订单金额
	private java.math.BigDecimal moneyPaidBalance; // 账户余额支付总金额
	private java.math.BigDecimal moneyPaidReality; // 现金支付总金额
	private Integer orderState; // 订单状态：1、已创建；2、退款中；3、已退款；4、已取消；5、已删除；6、已完成
	private String orderType; // 订单类型
	private Integer orgId; // 机构ID
	private String outerOrderSn; // 订单号
	private String paymentCode; // 支付方式编码
	private String paymentName; // 支付方式名称
	private Integer paymentStatus; // 支付状态 0-未支付；1-已支付
	private String paySn; // 支付订单号，传给第三方支付的订单号（单个订单支付时与订单号一致，多商户支付时，为统一订单号）
	private java.util.Date payTime; // 付款时间
	private String relationOrderSn; // 多商户拆单时使用
	private String remark; // 订单备注
	private String reqIp; // 请求ip
	private String tradeSn; // 在线支付交易流水号
	private java.util.Date updateTime; // 更新时间

	public static final Integer ORDER_TYPE_WECHAT_NATIVE = 1;
	public static final Integer ORDER_TYPE_WECHAT_JSAPI = 2;

	/**
	 * 订单状态：已创建
	 */
	public static final Integer ORDER_STATE_CREATE = 1;
	/**
	 * 订单状态：退款中
	 */
	public static final Integer ORDER_STATE_PAYBACK = 2;
	/**
	 * 订单状态：已退款
	 */
	public static final Integer ORDER_STATE_REFUND = 3;
	/**
	 * 订单状态：已取消
	 */
	public static final Integer ORDER_STATE_CANCEL = 4;
	/**
	 * 订单状态：已删除
	 */
	public static final Integer ORDER_STATE_DELETE = 5;
	/**
	 * 订单状态：已完成
	 */
	public static final Integer ORDER_STATE_FINISH = 6;

	/**
	 * 付款状态：未支付
	 */
	public static final Integer ORDER_PAY_STATUS_NO_PAY = 0;
	/**
	 * 付款状态：已支付
	 */
	public static final Integer ORDER_PAY_STATUS_PAEID = 1;

	/**
	 * 获取账户ID
	 */
	public Integer getAccountId() {
		return this.accountId;
	}

	/**
	 * 设置账户ID
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public java.lang.String getHandleNum() {
		return this.handleNum;
	}

	public void setHandleNum(java.lang.String handleNum) {
		this.handleNum = handleNum;
	}

	public java.lang.String getHandleName() {
		return this.handleName;
	}

	public void setHandleName(java.lang.String handleName) {
		this.handleName = handleName;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取订单完成时间
	 */
	public java.util.Date getFinishTime() {
		return this.finishTime;
	}

	/**
	 * 设置订单完成时间
	 */
	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * 获取id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取订单金额
	 */
	public java.math.BigDecimal getMoneyOrder() {
		return this.moneyOrder;
	}

	/**
	 * 设置订单金额
	 */
	public void setMoneyOrder(java.math.BigDecimal moneyOrder) {
		this.moneyOrder = moneyOrder;
	}

	/**
	 * 获取账户余额支付总金额
	 */
	public java.math.BigDecimal getMoneyPaidBalance() {
		return this.moneyPaidBalance;
	}

	/**
	 * 设置账户余额支付总金额
	 */
	public void setMoneyPaidBalance(java.math.BigDecimal moneyPaidBalance) {
		this.moneyPaidBalance = moneyPaidBalance;
	}

	/**
	 * 获取现金支付总金额
	 */
	public java.math.BigDecimal getMoneyPaidReality() {
		return this.moneyPaidReality;
	}

	/**
	 * 设置现金支付总金额
	 */
	public void setMoneyPaidReality(java.math.BigDecimal moneyPaidReality) {
		this.moneyPaidReality = moneyPaidReality;
	}

	/**
	 * 获取订单状态：1、已创建；2、退款中；3、已退款；4、已取消；5、已删除；6、已完成
	 */
	public Integer getOrderState() {
		return this.orderState;
	}

	/**
	 * 设置订单状态：1、已创建；2、退款中；3、已退款；4、已取消；5、已删除；6、已完成
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	/**
	 * 获取订单类型
	 */
	public String getOrderType() {
		return this.orderType;
	}

	/**
	 * 设置订单类型
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * 获取机构ID
	 */
	public Integer getOrgId() {
		return this.orgId;
	}

	/**
	 * 设置机构ID
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取订单号
	 */
	public String getOuterOrderSn() {
		return this.outerOrderSn;
	}

	/**
	 * 设置订单号
	 */
	public void setOuterOrderSn(String outerOrderSn) {
		this.outerOrderSn = outerOrderSn;
	}

	/**
	 * 获取支付方式编码
	 */
	public String getPaymentCode() {
		return this.paymentCode;
	}

	/**
	 * 设置支付方式编码
	 */
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	/**
	 * 获取支付方式名称
	 */
	public String getPaymentName() {
		return this.paymentName;
	}

	/**
	 * 设置支付方式名称
	 */
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	/**
	 * 获取支付状态 0-未支付；1-已支付
	 */
	public Integer getPaymentStatus() {
		return this.paymentStatus;
	}

	/**
	 * 设置支付状态 0-未支付；1-已支付
	 */
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * 获取支付订单号，传给第三方支付的订单号（单个订单支付时与订单号一致，多商户支付时，为统一订单号）
	 */
	public String getPaySn() {
		return this.paySn;
	}

	/**
	 * 设置支付订单号，传给第三方支付的订单号（单个订单支付时与订单号一致，多商户支付时，为统一订单号）
	 */
	public void setPaySn(String paySn) {
		this.paySn = paySn;
	}

	/**
	 * 获取付款时间
	 */
	public java.util.Date getPayTime() {
		return this.payTime;
	}

	/**
	 * 设置付款时间
	 */
	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * 获取多商户拆单时使用
	 */
	public String getRelationOrderSn() {
		return this.relationOrderSn;
	}

	/**
	 * 设置多商户拆单时使用
	 */
	public void setRelationOrderSn(String relationOrderSn) {
		this.relationOrderSn = relationOrderSn;
	}

	/**
	 * 获取订单备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置订单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取请求ip
	 */
	public String getReqIp() {
		return this.reqIp;
	}

	/**
	 * 设置请求ip
	 */
	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}

	/**
	 * 获取在线支付交易流水号
	 */
	public String getTradeSn() {
		return this.tradeSn;
	}

	/**
	 * 设置在线支付交易流水号
	 */
	public void setTradeSn(String tradeSn) {
		this.tradeSn = tradeSn;
	}

	/**
	 * 获取更新时间
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "PayOrder [accountId=" + accountId + ", handleNum=" + handleNum + ", handleName=" + handleName
				+ ", createTime=" + createTime + ", finishTime=" + finishTime + ", id=" + id + ", moneyOrder="
				+ moneyOrder + ", moneyPaidBalance=" + moneyPaidBalance + ", moneyPaidReality=" + moneyPaidReality
				+ ", orderState=" + orderState + ", orderType=" + orderType + ", orgId=" + orgId + ", outerOrderSn="
				+ outerOrderSn + ", paymentCode=" + paymentCode + ", paymentName=" + paymentName + ", paymentStatus="
				+ paymentStatus + ", paySn=" + paySn + ", payTime=" + payTime + ", relationOrderSn=" + relationOrderSn
				+ ", remark=" + remark + ", reqIp=" + reqIp + ", tradeSn=" + tradeSn + ", updateTime=" + updateTime
				+ "]";
	}

}
