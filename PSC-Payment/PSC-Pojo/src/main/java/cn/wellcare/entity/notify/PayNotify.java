package cn.wellcare.entity.notify;

import java.io.Serializable;
/**
 * 支付通知
 * <p>Table: <strong>pay_notify</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>backUrl</td><td>{@link java.lang.String}</td><td>back_url</td><td>character varying</td><td>推送地址</td></tr>
 *   <tr><td>body</td><td>{@link java.lang.String}</td><td>body</td><td>text</td><td>订单描述信息</td></tr>
 *   <tr><td>gmtClose</td><td>{@link java.util.Date}</td><td>gmt_close</td><td>timestamp with time zone</td><td>交易结束时间</td></tr>
 *   <tr><td>gmtPayment</td><td>{@link java.util.Date}</td><td>gmt_payment</td><td>timestamp with time zone</td><td>买家付款时间</td></tr>
 *   <tr><td>gmtRefund</td><td>{@link java.util.Date}</td><td>gmt_refund</td><td>timestamp with time zone</td><td>交易退款时间</td></tr>
 *   <tr><td>notifyId</td><td>{@link java.lang.Integer}</td><td>notify_id</td><td>integer</td><td>通知主键</td></tr>
 *   <tr><td>notifyTime</td><td>{@link java.util.Date}</td><td>notify_time</td><td>timestamp with time zone</td><td>通知发送时间</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>integer</td><td>交易订单</td></tr>
 *   <tr><td>orderSn</td><td>{@link java.lang.String}</td><td>order_sn</td><td>character varying</td><td>订单号</td></tr>
 *   <tr><td>orgId</td><td>{@link java.lang.Integer}</td><td>org_id</td><td>integer</td><td>机构</td></tr>
 *   <tr><td>refundFee</td><td>{@link java.math.BigDecimal}</td><td>refund_fee</td><td>numeric</td><td>退款金额</td></tr>
 *   <tr><td>sendBackFee</td><td>{@link java.math.BigDecimal}</td><td>send_back_fee</td><td>numeric</td><td>实际退款金额</td></tr>
 *   <tr><td>sign</td><td>{@link java.lang.String}</td><td>sign</td><td>text</td><td>签名</td></tr>
 *   <tr><td>signType</td><td>{@link java.lang.String}</td><td>sign_type</td><td>character varying</td><td>签名算法</td></tr>
 *   <tr><td>totalAmount</td><td>{@link java.math.BigDecimal}</td><td>total_amount</td><td>numeric</td><td>交易金额</td></tr>
 *   <tr><td>tradeNo</td><td>{@link java.lang.String}</td><td>trade_no</td><td>character varying</td><td>交易凭证号</td></tr>
 *   <tr><td>tradeStatus</td><td>{@link java.lang.Integer}</td><td>trade_status</td><td>smallint</td><td>1、激活 2、已支付通知 3、退款中 4、已退款通知</td></tr>
 * </table>
 *
 */
public class PayNotify implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7547458447689969660L;
	private java.lang.String backUrl; // 推送地址
 	private java.lang.String body; //订单描述信息
 	private java.util.Date gmtClose; //交易结束时间
 	private java.util.Date gmtPayment; //买家付款时间
 	private java.util.Date gmtRefund; //交易退款时间
 	private java.lang.Integer notifyId; //通知主键
 	private java.util.Date notifyTime; //通知发送时间
 	private java.lang.Integer orderId; //交易订单
	private java.lang.String outerOrderSn; // 业务订单号
 	private java.lang.Integer orgId; //机构
 	private java.math.BigDecimal refundFee; //退款金额
 	private java.math.BigDecimal sendBackFee; //实际退款金额
 	private java.lang.String sign; //签名
 	private java.lang.String signType; //签名算法
 	private java.math.BigDecimal totalAmount; //交易金额
 	private java.lang.String tradeNo; //交易凭证号
 	private java.lang.Integer tradeStatus; //1、激活 2、已支付通知 3、退款中 4、已退款通知
 	
	public static final int TRADE_STATUS_ACTIVE = 1;
	public static final int TRADE_STATUS_DONE = 2;
	public static final int TRADE_STATUS_REFUNDING = 3;
	public static final int TRADE_STATUS_REFUNDED = 4;
 		
	/**
     * 获取推送地址
     */
	public java.lang.String getBackUrl(){
		return this.backUrl;
	}
 		
	/**
     * 设置推送地址
     */
	public void setBackUrl(java.lang.String backUrl){
		this.backUrl = backUrl;
	}
 		
 		
	/**
     * 获取订单描述信息
     */
	public java.lang.String getBody(){
		return this.body;
	}
 		
	/**
     * 设置订单描述信息
     */
	public void setBody(java.lang.String body){
		this.body = body;
	}
 		
 		
	/**
     * 获取交易结束时间
     */
	public java.util.Date getGmtClose(){
		return this.gmtClose;
	}
 		
	/**
     * 设置交易结束时间
     */
	public void setGmtClose(java.util.Date gmtClose){
		this.gmtClose = gmtClose;
	}
 		
 		
	/**
     * 获取买家付款时间
     */
	public java.util.Date getGmtPayment(){
		return this.gmtPayment;
	}
 		
	/**
     * 设置买家付款时间
     */
	public void setGmtPayment(java.util.Date gmtPayment){
		this.gmtPayment = gmtPayment;
	}
 		
 		
	/**
     * 获取交易退款时间
     */
	public java.util.Date getGmtRefund(){
		return this.gmtRefund;
	}
 		
	/**
     * 设置交易退款时间
     */
	public void setGmtRefund(java.util.Date gmtRefund){
		this.gmtRefund = gmtRefund;
	}
 		
 		
	/**
     * 获取通知主键
     */
	public java.lang.Integer getNotifyId(){
		return this.notifyId;
	}
 		
	/**
     * 设置通知主键
     */
	public void setNotifyId(java.lang.Integer notifyId){
		this.notifyId = notifyId;
	}
 		
 		
	/**
     * 获取通知发送时间
     */
	public java.util.Date getNotifyTime(){
		return this.notifyTime;
	}
 		
	/**
     * 设置通知发送时间
     */
	public void setNotifyTime(java.util.Date notifyTime){
		this.notifyTime = notifyTime;
	}
 		
 		
	/**
     * 获取交易订单
     */
	public java.lang.Integer getOrderId(){
		return this.orderId;
	}
 		
	/**
     * 设置交易订单
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
     * 获取退款金额
     */
	public java.math.BigDecimal getRefundFee(){
		return this.refundFee;
	}
 		
	/**
     * 设置退款金额
     */
	public void setRefundFee(java.math.BigDecimal refundFee){
		this.refundFee = refundFee;
	}
 		
 		
	/**
     * 获取实际退款金额
     */
	public java.math.BigDecimal getSendBackFee(){
		return this.sendBackFee;
	}
 		
	/**
     * 设置实际退款金额
     */
	public void setSendBackFee(java.math.BigDecimal sendBackFee){
		this.sendBackFee = sendBackFee;
	}
 		
 		
	/**
     * 获取签名
     */
	public java.lang.String getSign(){
		return this.sign;
	}
 		
	/**
     * 设置签名
     */
	public void setSign(java.lang.String sign){
		this.sign = sign;
	}
 		
 		
	/**
     * 获取签名算法
     */
	public java.lang.String getSignType(){
		return this.signType;
	}
 		
	/**
     * 设置签名算法
     */
	public void setSignType(java.lang.String signType){
		this.signType = signType;
	}
 		
 		
	/**
     * 获取交易金额
     */
	public java.math.BigDecimal getTotalAmount(){
		return this.totalAmount;
	}
 		
	/**
     * 设置交易金额
     */
	public void setTotalAmount(java.math.BigDecimal totalAmount){
		this.totalAmount = totalAmount;
	}
 		
 		
	/**
     * 获取交易凭证号
     */
	public java.lang.String getTradeNo(){
		return this.tradeNo;
	}
 		
	/**
     * 设置交易凭证号
     */
	public void setTradeNo(java.lang.String tradeNo){
		this.tradeNo = tradeNo;
	}
 		
 		
	/**
     * 获取1、激活 2、已支付通知 3、退款中 4、已退款通知
     */
	public java.lang.Integer getTradeStatus(){
		return this.tradeStatus;
	}
 		
	/**
     * 设置1、激活 2、已支付通知 3、退款中 4、已退款通知
     */
	public void setTradeStatus(java.lang.Integer tradeStatus){
		this.tradeStatus = tradeStatus;
	}

	@Override
	public String toString() {
		return "PayNotify [backUrl=" + backUrl + ", body=" + body + ", gmtClose=" + gmtClose + ", gmtPayment="
				+ gmtPayment + ", gmtRefund=" + gmtRefund + ", notifyId=" + notifyId + ", notifyTime=" + notifyTime
				+ ", orderId=" + orderId + ", outerOrderSn=" + outerOrderSn + ", orgId=" + orgId + ", refundFee="
				+ refundFee + ", sendBackFee=" + sendBackFee + ", sign=" + sign + ", signType=" + signType
				+ ", totalAmount=" + totalAmount + ", tradeNo=" + tradeNo
				+ ", tradeStatus="
				+ tradeStatus
				+ "]";
	}

	/**
	 * @return the outerOrderSn
	 */
	public java.lang.String getOuterOrderSn() {
		return this.outerOrderSn;
	}

	/**
	 * @param outerOrderSn the outerOrderSn to set
	 */
	public void setOuterOrderSn(java.lang.String outerOrderSn) {
		this.outerOrderSn = outerOrderSn;
	}

 }