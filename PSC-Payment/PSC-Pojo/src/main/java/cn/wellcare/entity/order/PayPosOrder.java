package cn.wellcare.entity.order;

import java.io.Serializable;
/**
 * MisPOS订单
 * <p>Table: <strong>pay_pos_order</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>amount</td><td>{@link java.math.BigDecimal}</td><td>amount</td><td>numeric</td><td>金额（分）</td></tr>
 *   <tr><td>auth</td><td>{@link java.lang.String}</td><td>auth</td><td>character varying</td><td>POS授权号</td></tr>
 *   <tr><td>bankCode</td><td>{@link java.lang.String}</td><td>bank_code</td><td>character varying</td><td>银行行号</td></tr>
 *   <tr><td>batch</td><td>{@link java.lang.String}</td><td>batch</td><td>character varying</td><td>POS批次号</td></tr>
 *   <tr><td>cardNo</td><td>{@link java.lang.String}</td><td>card_no</td><td>character varying</td><td>卡号</td></tr>
 *   <tr><td>date</td><td>{@link java.util.Date}</td><td>date</td><td>timestamp with time zone</td><td>交易日期</td></tr>
 *   <tr><td>dtPayMode</td><td>{@link java.lang.String}</td><td>dt_pay_mode</td><td>character varying</td><td>POS支付方式</td></tr>
 *   <tr><td>expr</td><td>{@link java.lang.String}</td><td>expr</td><td>character varying</td><td>有效期</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>订单主键</td></tr>
 *   <tr><td>oldTerno</td><td>{@link java.lang.String}</td><td>old_terno</td><td>character varying</td><td>原终端号</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>integer</td><td>订单id</td></tr>
 *   <tr><td>orgId</td><td>{@link java.lang.Integer}</td><td>org_id</td><td>integer</td><td>机构</td></tr>
 *   <tr><td>refer</td><td>{@link java.lang.String}</td><td>refer</td><td>character varying</td><td>POS交易参考号</td></tr>
 *   <tr><td>respChin</td><td>{@link java.lang.String}</td><td>resp_chin</td><td>character varying</td><td>错误说明</td></tr>
 *   <tr><td>respCode</td><td>{@link java.lang.String}</td><td>resp_code</td><td>character varying</td><td>返回码</td></tr>
 *   <tr><td>settleStatus</td><td>{@link java.lang.Integer}</td><td>settle_status</td><td>smallint</td><td>结算状态</td></tr>
 *   <tr><td>szOrderTrace</td><td>{@link java.lang.String}</td><td>sz_order_trace</td><td>character varying</td><td>收银流水(订单)号</td></tr>
 *   <tr><td>terno</td><td>{@link java.lang.String}</td><td>terno</td><td>character varying</td><td>终端号</td></tr>
 *   <tr><td>trace</td><td>{@link java.lang.String}</td><td>trace</td><td>character varying</td><td>POS流水号</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp with time zone</td><td>更新时间</td></tr>
 *   <tr><td>userno</td><td>{@link java.lang.String}</td><td>userno</td><td>character varying</td><td>商户号</td></tr>
 * </table>
 *
 */
public class PayPosOrder implements Serializable {
 
 	private java.math.BigDecimal amount; //金额（分）
 	private java.lang.String auth; //POS授权号
 	private java.lang.String bankCode; //银行行号
 	private java.lang.String batch; //POS批次号
 	private java.lang.String cardNo; //卡号
 	private java.util.Date date; //交易日期
 	private java.lang.String dtPayMode; //POS支付方式
 	private java.lang.String expr; //有效期
 	private java.lang.Integer id; //订单主键
 	private java.lang.String oldTerno; //原终端号
 	private java.lang.Integer orderId; //订单id
 	private java.lang.Integer orgId; //机构
 	private java.lang.String refer; //POS交易参考号
 	private java.lang.String respChin; //错误说明
 	private java.lang.String respCode; //返回码
 	private java.lang.Integer settleStatus; //结算状态
 	private java.lang.String szOrderTrace; //收银流水(订单)号
 	private java.lang.String terno; //终端号
 	private java.lang.String trace; //POS流水号
 	private java.util.Date updateTime; //更新时间
 	private java.lang.String userno; //商户号
 	
 		
 		
	/**
     * 获取金额（分）
     */
	public java.math.BigDecimal getAmount(){
		return this.amount;
	}
 		
	/**
     * 设置金额（分）
     */
	public void setAmount(java.math.BigDecimal amount){
		this.amount = amount;
	}
 		
 		
	/**
     * 获取POS授权号
     */
	public java.lang.String getAuth(){
		return this.auth;
	}
 		
	/**
     * 设置POS授权号
     */
	public void setAuth(java.lang.String auth){
		this.auth = auth;
	}
 		
 		
	/**
     * 获取银行行号
     */
	public java.lang.String getBankCode(){
		return this.bankCode;
	}
 		
	/**
     * 设置银行行号
     */
	public void setBankCode(java.lang.String bankCode){
		this.bankCode = bankCode;
	}
 		
 		
	/**
     * 获取POS批次号
     */
	public java.lang.String getBatch(){
		return this.batch;
	}
 		
	/**
     * 设置POS批次号
     */
	public void setBatch(java.lang.String batch){
		this.batch = batch;
	}
 		
 		
	/**
     * 获取卡号
     */
	public java.lang.String getCardNo(){
		return this.cardNo;
	}
 		
	/**
     * 设置卡号
     */
	public void setCardNo(java.lang.String cardNo){
		this.cardNo = cardNo;
	}
 		
 		
	/**
     * 获取交易日期
     */
	public java.util.Date getDate(){
		return this.date;
	}
 		
	/**
     * 设置交易日期
     */
	public void setDate(java.util.Date date){
		this.date = date;
	}
 		
 		
	/**
     * 获取POS支付方式
     */
	public java.lang.String getDtPayMode(){
		return this.dtPayMode;
	}
 		
	/**
     * 设置POS支付方式
     */
	public void setDtPayMode(java.lang.String dtPayMode){
		this.dtPayMode = dtPayMode;
	}
 		
 		
	/**
     * 获取有效期
     */
	public java.lang.String getExpr(){
		return this.expr;
	}
 		
	/**
     * 设置有效期
     */
	public void setExpr(java.lang.String expr){
		this.expr = expr;
	}
 		
 		
	/**
     * 获取订单主键
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置订单主键
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
 		
	/**
     * 获取原终端号
     */
	public java.lang.String getOldTerno(){
		return this.oldTerno;
	}
 		
	/**
     * 设置原终端号
     */
	public void setOldTerno(java.lang.String oldTerno){
		this.oldTerno = oldTerno;
	}
 		
 		
	/**
     * 获取订单id
     */
	public java.lang.Integer getOrderId(){
		return this.orderId;
	}
 		
	/**
     * 设置订单id
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
     * 获取POS交易参考号
     */
	public java.lang.String getRefer(){
		return this.refer;
	}
 		
	/**
     * 设置POS交易参考号
     */
	public void setRefer(java.lang.String refer){
		this.refer = refer;
	}
 		
 		
	/**
     * 获取错误说明
     */
	public java.lang.String getRespChin(){
		return this.respChin;
	}
 		
	/**
     * 设置错误说明
     */
	public void setRespChin(java.lang.String respChin){
		this.respChin = respChin;
	}
 		
 		
	/**
     * 获取返回码
     */
	public java.lang.String getRespCode(){
		return this.respCode;
	}
 		
	/**
     * 设置返回码
     */
	public void setRespCode(java.lang.String respCode){
		this.respCode = respCode;
	}
 		
 		
	/**
     * 获取结算状态
     */
	public java.lang.Integer getSettleStatus(){
		return this.settleStatus;
	}
 		
	/**
     * 设置结算状态
     */
	public void setSettleStatus(java.lang.Integer settleStatus){
		this.settleStatus = settleStatus;
	}
 		
 		
	/**
     * 获取收银流水(订单)号
     */
	public java.lang.String getSzOrderTrace(){
		return this.szOrderTrace;
	}
 		
	/**
     * 设置收银流水(订单)号
     */
	public void setSzOrderTrace(java.lang.String szOrderTrace){
		this.szOrderTrace = szOrderTrace;
	}
 		
 		
	/**
     * 获取终端号
     */
	public java.lang.String getTerno(){
		return this.terno;
	}
 		
	/**
     * 设置终端号
     */
	public void setTerno(java.lang.String terno){
		this.terno = terno;
	}
 		
 		
	/**
     * 获取POS流水号
     */
	public java.lang.String getTrace(){
		return this.trace;
	}
 		
	/**
     * 设置POS流水号
     */
	public void setTrace(java.lang.String trace){
		this.trace = trace;
	}
 		
 		
	/**
     * 获取更新时间
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置更新时间
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 		
 		
	/**
     * 获取商户号
     */
	public java.lang.String getUserno(){
		return this.userno;
	}
 		
	/**
     * 设置商户号
     */
	public void setUserno(java.lang.String userno){
		this.userno = userno;
	}
 }