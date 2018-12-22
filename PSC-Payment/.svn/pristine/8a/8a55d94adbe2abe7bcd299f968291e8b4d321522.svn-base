package cn.wellcare.entity.log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 订单操作日志
 * <p>Table: <strong>pay_order_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>accountId</td><td>{@link java.lang.Integer}</td><td>account_id</td><td>integer</td><td>账户ID</td></tr>
 *   <tr><td>accountName</td><td>{@link java.lang.String}</td><td>account_name</td><td>character varying</td><td>账户姓名</td></tr>
 *   <tr><td>amountAfter</td><td>{@link java.math.BigDecimal}</td><td>amount_after</td><td>numeric</td><td>操作后订单金额</td></tr>
 *   <tr><td>amountPrev</td><td>{@link java.math.BigDecimal}</td><td>amount_prev</td><td>numeric</td><td>操作前订单金额</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>character varying</td><td>操作内容</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp with time zone</td><td>创建时间</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>integer</td><td>订单ID</td></tr>
 *   <tr><td>orgId</td><td>{@link java.lang.Integer}</td><td>org_id</td><td>integer</td><td>机构</td></tr>
 *   <tr><td>outerOrderSn</td><td>{@link java.lang.String}</td><td>outer_order_sn</td><td>character varying</td><td>业务订单号</td></tr>
 *   <tr><td>paymentAfter</td><td>{@link java.lang.Integer}</td><td>payment_after</td><td>smallint</td><td>操作后支付状态</td></tr>
 *   <tr><td>paymentPrev</td><td>{@link java.lang.Integer}</td><td>payment_prev</td><td>smallint</td><td>操作前支付状态</td></tr>
 *   <tr><td>statusAfter</td><td>{@link java.lang.Integer}</td><td>status_after</td><td>smallint</td><td>操作后订单状态</td></tr>
 *   <tr><td>statusPrev</td><td>{@link java.lang.Integer}</td><td>status_prev</td><td>smallint</td><td>操作前订单状态</td></tr>
 * </table>
 *
 */
public class PayOrderLog implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5678685043820000565L;
	private java.lang.String handleNum; // 操作员工号
	private java.lang.String handleName; // 操作员姓名
 	private java.math.BigDecimal amountAfter; //操作后订单金额
 	private java.math.BigDecimal amountPrev; //操作前订单金额
 	private java.lang.String content; //操作内容
 	private java.util.Date createTime; //创建时间
 	private java.lang.Integer id; //id
 	private java.lang.Integer orderId; //订单ID
 	private java.lang.Integer orgId; //机构
 	private java.lang.String outerOrderSn; //业务订单号
 	private java.lang.Integer paymentAfter; //操作后支付状态
 	private java.lang.Integer paymentPrev; //操作前支付状态
 	private java.lang.Integer statusAfter; //操作后订单状态
 	private java.lang.Integer statusPrev; //操作前订单状态
 		
	public PayOrderLog() {
		super();
	}

	public PayOrderLog(String handleNum, String handleName, BigDecimal amountAfter, BigDecimal amountPrev,
			String content, Date createTime, Integer orderId, Integer orgId, String outerOrderSn, Integer paymentAfter,
			Integer paymentPrev, Integer statusAfter, Integer statusPrev) {
		super();
		this.handleNum = handleNum;
		this.handleName = handleName;
		this.amountAfter = amountAfter;
		this.amountPrev = amountPrev;
		this.content = content;
		this.createTime = createTime;
		this.orderId = orderId;
		this.orgId = orgId;
		this.outerOrderSn = outerOrderSn;
		this.paymentAfter = paymentAfter;
		this.paymentPrev = paymentPrev;
		this.statusAfter = statusAfter;
		this.statusPrev = statusPrev;
	}

	/**
     * 获取操作后订单金额
     */
	public java.math.BigDecimal getAmountAfter(){
		return this.amountAfter;
	}
 		
	/**
     * 设置操作后订单金额
     */
	public void setAmountAfter(java.math.BigDecimal amountAfter){
		this.amountAfter = amountAfter;
	}
 		
 		
	/**
     * 获取操作前订单金额
     */
	public java.math.BigDecimal getAmountPrev(){
		return this.amountPrev;
	}
 		
	/**
     * 设置操作前订单金额
     */
	public void setAmountPrev(java.math.BigDecimal amountPrev){
		this.amountPrev = amountPrev;
	}
 		
 		
	/**
     * 获取操作内容
     */
	public java.lang.String getContent(){
		return this.content;
	}
 		
	/**
     * 设置操作内容
     */
	public void setContent(java.lang.String content){
		this.content = content;
	}
 		
 		
	/**
     * 获取创建时间
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置创建时间
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
 		
	/**
     * 获取id
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
 		
	/**
     * 获取订单ID
     */
	public java.lang.Integer getOrderId(){
		return this.orderId;
	}
 		
	/**
     * 设置订单ID
     */
	public void setOrderId(java.lang.Integer orderId){
		this.orderId = orderId;
	}
 		
 		
	/**
     * 获取机构
     */
	public java.lang.Integer getOrgId(){
		return this.orgId;
	}
 		
	/**
     * 设置机构
     */
	public void setOrgId(java.lang.Integer orgId){
		this.orgId = orgId;
	}
 		
 		
	/**
     * 获取业务订单号
     */
	public java.lang.String getOuterOrderSn(){
		return this.outerOrderSn;
	}
 		
	/**
     * 设置业务订单号
     */
	public void setOuterOrderSn(java.lang.String outerOrderSn){
		this.outerOrderSn = outerOrderSn;
	}
 		
 		
	/**
     * 获取操作后支付状态
     */
	public java.lang.Integer getPaymentAfter(){
		return this.paymentAfter;
	}
 		
	/**
     * 设置操作后支付状态
     */
	public void setPaymentAfter(java.lang.Integer paymentAfter){
		this.paymentAfter = paymentAfter;
	}
 		
 		
	/**
     * 获取操作前支付状态
     */
	public java.lang.Integer getPaymentPrev(){
		return this.paymentPrev;
	}
 		
	/**
     * 设置操作前支付状态
     */
	public void setPaymentPrev(java.lang.Integer paymentPrev){
		this.paymentPrev = paymentPrev;
	}
 		
 		
	/**
     * 获取操作后订单状态
     */
	public java.lang.Integer getStatusAfter(){
		return this.statusAfter;
	}
 		
	/**
     * 设置操作后订单状态
     */
	public void setStatusAfter(java.lang.Integer statusAfter){
		this.statusAfter = statusAfter;
	}
 		
 		
	/**
     * 获取操作前订单状态
     */
	public java.lang.Integer getStatusPrev(){
		return this.statusPrev;
	}
 		
	/**
     * 设置操作前订单状态
     */
	public void setStatusPrev(java.lang.Integer statusPrev){
		this.statusPrev = statusPrev;
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

	@Override
	public String toString() {
		return "PayOrderLog [handleNum=" + handleNum + ", handleName=" + handleName + ", amountAfter=" + amountAfter
				+ ", amountPrev=" + amountPrev + ", content=" + content + ", createTime=" + createTime + ", id=" + id
				+ ", orderId=" + orderId + ", orgId=" + orgId + ", outerOrderSn=" + outerOrderSn + ", paymentAfter="
				+ paymentAfter + ", paymentPrev=" + paymentPrev + ", statusAfter=" + statusAfter + ", statusPrev="
				+ statusPrev + "]";
	}

 }