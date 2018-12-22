package cn.wellcare.entity.log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p>
 * Table: <strong>pay_log</strong>
 * <p>
 * <table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1
 * #666;padding:3px;">
 * <tr style="background-color:#ddd;Text-align:Left;">
 * <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th
 * nowrap>字段类型</th><th nowrap>说明</th>
 * </tr>
 * <tr>
 * <td>accountId</td>
 * <td>{@link java.lang.Integer}</td>
 * <td>account_id</td>
 * <td>integer</td>
 * <td>账户ID</td>
 * </tr>
 * <tr>
 * <td>accountName</td>
 * <td>{@link java.lang.String}</td>
 * <td>account_name</td>
 * <td>character varying</td>
 * <td>账户姓名</td>
 * </tr>
 * <tr>
 * <td>createTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>create_time</td>
 * <td>timestamp with time zone</td>
 * <td>创建时间</td>
 * </tr>
 * <tr>
 * <td>id</td>
 * <td>{@link java.lang.Integer}</td>
 * <td>id</td>
 * <td>integer</td>
 * <td>id</td>
 * </tr>
 * <tr>
 * <td>orderId</td>
 * <td>{@link java.lang.Integer}</td>
 * <td>order_id</td>
 * <td>integer</td>
 * <td>订单ID</td>
 * </tr>
 * <tr>
 * <td>orgId</td>
 * <td>{@link java.lang.Integer}</td>
 * <td>org_id</td>
 * <td>integer</td>
 * <td>机构</td>
 * </tr>
 * <tr>
 * <td>outerOrderSn</td>
 * <td>{@link java.lang.String}</td>
 * <td>outer_order_sn</td>
 * <td>character varying</td>
 * <td>业务订单号</td>
 * </tr>
 * <tr>
 * <td>paymentCode</td>
 * <td>{@link java.lang.String}</td>
 * <td>payment_code</td>
 * <td>character varying</td>
 * <td>支付方式编码</td>
 * </tr>
 * <tr>
 * <td>paymentName</td>
 * <td>{@link java.lang.String}</td>
 * <td>payment_name</td>
 * <td>character varying</td>
 * <td>支付方式名称</td>
 * </tr>
 * <tr>
 * <td>payMoney</td>
 * <td>{@link java.math.BigDecimal}</td>
 * <td>pay_money</td>
 * <td>numeric</td>
 * <td>支付金额</td>
 * </tr>
 * <tr>
 * <td>paySn</td>
 * <td>{@link java.lang.String}</td>
 * <td>pay_sn</td>
 * <td>character varying</td>
 * <td>支付订单号</td>
 * </tr>
 * <tr>
 * <td>tradeSn</td>
 * <td>{@link java.lang.String}</td>
 * <td>trade_sn</td>
 * <td>character varying</td>
 * <td>交易流水号</td>
 * </tr>
 * <tr>
 * <td>updateTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>update_time</td>
 * <td>timestamp with time zone</td>
 * <td>更新时间</td>
 * </tr>
 * </table>
 *
 */
public class PayLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -753361047326395295L;
	private java.lang.Integer id; // id
	private java.lang.String handleNum; // 操作员工号
	private java.lang.String handleName; // 操作员姓名
	private java.util.Date createTime; // 创建时间
	private java.lang.Integer orderId; // 订单ID
	private java.lang.Integer orgId; // 机构
	private java.lang.String outerOrderSn; // 业务订单号
	private java.lang.String paymentCode; // 支付方式编码
	private java.lang.String paymentName; // 支付方式名称
	private java.math.BigDecimal payMoney; // 支付金额
	private java.lang.String paySn; // 支付订单号
	private java.lang.String tradeSn; // 交易流水号
	private java.util.Date updateTime; // 更新时间
	private java.lang.Integer opType; // 1、消费 2、退费 3、充值 4、提现
	private java.util.Date clientTradeTime; // 交易请求时间

	public PayLog() {
	}

	public PayLog(String handleNum, String handleName, Date createTime, Integer orderId, Integer orgId,
			String outerOrderSn, String paymentCode, String paymentName, BigDecimal payMoney, String paySn,
			String tradeSn, Date updateTime, Integer opType, Date clientTradeTime) {
		super();
		this.handleNum = handleNum;
		this.handleName = handleName;
		this.createTime = createTime;
		this.orderId = orderId;
		this.orgId = orgId;
		this.outerOrderSn = outerOrderSn;
		this.paymentCode = paymentCode;
		this.paymentName = paymentName;
		this.payMoney = payMoney;
		this.paySn = paySn;
		this.tradeSn = tradeSn;
		this.updateTime = updateTime;
		this.opType = opType;
		this.clientTradeTime = clientTradeTime;
	}

	/**
	 * 获取创建时间
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取id
	 */
	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * 设置id
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * 获取订单ID
	 */
	public java.lang.Integer getOrderId() {
		return this.orderId;
	}

	/**
	 * 设置订单ID
	 */
	public void setOrderId(java.lang.Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取机构
	 */
	public java.lang.Integer getOrgId() {
		return this.orgId;
	}

	/**
	 * 设置机构
	 */
	public void setOrgId(java.lang.Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 获取业务订单号
	 */
	public java.lang.String getOuterOrderSn() {
		return this.outerOrderSn;
	}

	/**
	 * 设置业务订单号
	 */
	public void setOuterOrderSn(java.lang.String outerOrderSn) {
		this.outerOrderSn = outerOrderSn;
	}

	/**
	 * 获取支付方式编码
	 */
	public java.lang.String getPaymentCode() {
		return this.paymentCode;
	}

	/**
	 * 设置支付方式编码
	 */
	public void setPaymentCode(java.lang.String paymentCode) {
		this.paymentCode = paymentCode;
	}

	/**
	 * 获取支付方式名称
	 */
	public java.lang.String getPaymentName() {
		return this.paymentName;
	}

	/**
	 * 设置支付方式名称
	 */
	public void setPaymentName(java.lang.String paymentName) {
		this.paymentName = paymentName;
	}

	/**
	 * 获取支付金额
	 */
	public java.math.BigDecimal getPayMoney() {
		return this.payMoney;
	}

	/**
	 * 设置支付金额
	 */
	public void setPayMoney(java.math.BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	/**
	 * 获取支付订单号
	 */
	public java.lang.String getPaySn() {
		return this.paySn;
	}

	/**
	 * 设置支付订单号
	 */
	public void setPaySn(java.lang.String paySn) {
		this.paySn = paySn;
	}

	/**
	 * 获取交易流水号
	 */
	public java.lang.String getTradeSn() {
		return this.tradeSn;
	}

	/**
	 * 设置交易流水号
	 */
	public void setTradeSn(java.lang.String tradeSn) {
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

	/**
	 * 设置操作类型
	 */
	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	/**
	 * 获取操作类型
	 */
	public Integer getOpType() {
		return opType;
	}

	public java.util.Date getClientTradeTime() {
		return this.clientTradeTime;
	}

	public void setClientTradeTime(java.util.Date clientTradeTime) {
		this.clientTradeTime = clientTradeTime;
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

}