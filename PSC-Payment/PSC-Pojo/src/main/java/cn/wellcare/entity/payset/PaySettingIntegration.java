package cn.wellcare.entity.payset;

import java.io.Serializable;
/**
 * 支付设置-建行聚合支付
 * <p>Table: <strong>pay_setting_integration</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>bankId</td><td>{@link java.lang.String}</td><td>bank_id</td><td>character varying</td><td>分行代码</td></tr>
 *   <tr><td>bankUrl</td><td>{@link java.lang.String}</td><td>bank_url</td><td>character varying</td><td>网银网关地址</td></tr>
 *   <tr><td>curcode</td><td>{@link java.lang.String}</td><td>curcode</td><td>character varying</td><td>币种</td></tr>
 *   <tr><td>custid</td><td>{@link java.lang.String}</td><td>custid</td><td>character varying</td><td>商户号</td></tr>
 *   <tr><td>enableNotify</td><td>{@link java.lang.Integer}</td><td>enable_notify</td><td>smallint</td><td>是否启用通知</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>主键</td></tr>
 *   <tr><td>merchTid</td><td>{@link java.lang.String}</td><td>merch_tid</td><td>character varying</td><td>商户代码</td></tr>
 *   <tr><td>notifyUrl</td><td>{@link java.lang.String}</td><td>notify_url</td><td>character varying</td><td>通知地址</td></tr>
 *   <tr><td>orderName</td><td>{@link java.lang.String}</td><td>order_name</td><td>character varying</td><td>订单名称</td></tr>
 *   <tr><td>orgId</td><td>{@link java.lang.Integer}</td><td>org_id</td><td>integer</td><td>机构</td></tr>
 *   <tr><td>posId</td><td>{@link java.lang.String}</td><td>pos_id</td><td>character varying</td><td>商户柜台代码</td></tr>
 *   <tr><td>pub32tr2</td><td>{@link java.lang.String}</td><td>pub32tr2</td><td>character varying</td><td>密钥</td></tr>
 *   <tr><td>publicKey</td><td>{@link java.lang.String}</td><td>public_key</td><td>text</td><td>公钥</td></tr>
 *   <tr><td>pwd</td><td>{@link java.lang.String}</td><td>pwd</td><td>character varying</td><td>操作员密码</td></tr>
 *   <tr><td>returnType</td><td>{@link java.lang.String}</td><td>return_type</td><td>character varying</td><td>返回类型</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>smallint</td><td>0、不可用 1、可用</td></tr>
 *   <tr><td>sysId</td><td>{@link java.lang.Integer}</td><td>sys_id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>txcode</td><td>{@link java.lang.String}</td><td>txcode</td><td>character varying</td><td>交易码</td></tr>
 *   <tr><td>userId</td><td>{@link java.lang.String}</td><td>user_id</td><td>character varying</td><td>操作员号</td></tr>
 * </table>
 *
 */
public class PaySettingIntegration implements Serializable {
 
 	private java.lang.String bankId; //分行代码
 	private java.lang.String bankUrl; //网银网关地址
 	private java.lang.String curcode; //币种
 	private java.lang.String custid; //商户号
 	private java.lang.Integer enableNotify; //是否启用通知
 	private java.lang.Integer id; //主键
 	private java.lang.String merchTid; //商户代码
 	private java.lang.String notifyUrl; //通知地址
 	private java.lang.String orderName; //订单名称
 	private java.lang.Integer orgId; //机构
 	private java.lang.String posId; //商户柜台代码
 	private java.lang.String pub32tr2; //密钥
 	private java.lang.String publicKey; //公钥
 	private java.lang.String pwd; //操作员密码
 	private java.lang.String returnType; //返回类型
 	private java.lang.Integer status; //0、不可用 1、可用
 	private java.lang.String txcode; //交易码
 	private java.lang.String userId; //操作员号
 	
 		
 		
	/**
     * 获取分行代码
     */
	public java.lang.String getBankId(){
		return this.bankId;
	}
 		
	/**
     * 设置分行代码
     */
	public void setBankId(java.lang.String bankId){
		this.bankId = bankId;
	}
 		
 		
	/**
     * 获取网银网关地址
     */
	public java.lang.String getBankUrl(){
		return this.bankUrl;
	}
 		
	/**
     * 设置网银网关地址
     */
	public void setBankUrl(java.lang.String bankUrl){
		this.bankUrl = bankUrl;
	}
 		
 		
	/**
     * 获取币种
     */
	public java.lang.String getCurcode(){
		return this.curcode;
	}
 		
	/**
     * 设置币种
     */
	public void setCurcode(java.lang.String curcode){
		this.curcode = curcode;
	}
 		
 		
	/**
     * 获取商户号
     */
	public java.lang.String getCustid(){
		return this.custid;
	}
 		
	/**
     * 设置商户号
     */
	public void setCustid(java.lang.String custid){
		this.custid = custid;
	}
 		
 		
	/**
     * 获取是否启用通知
     */
	public java.lang.Integer getEnableNotify(){
		return this.enableNotify;
	}
 		
	/**
     * 设置是否启用通知
     */
	public void setEnableNotify(java.lang.Integer enableNotify){
		this.enableNotify = enableNotify;
	}
 		
 		
	/**
     * 获取主键
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置主键
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
 		
	/**
     * 获取商户代码
     */
	public java.lang.String getMerchTid(){
		return this.merchTid;
	}
 		
	/**
     * 设置商户代码
     */
	public void setMerchTid(java.lang.String merchTid){
		this.merchTid = merchTid;
	}
 		
 		
	/**
     * 获取通知地址
     */
	public java.lang.String getNotifyUrl(){
		return this.notifyUrl;
	}
 		
	/**
     * 设置通知地址
     */
	public void setNotifyUrl(java.lang.String notifyUrl){
		this.notifyUrl = notifyUrl;
	}
 		
 		
	/**
     * 获取订单名称
     */
	public java.lang.String getOrderName(){
		return this.orderName;
	}
 		
	/**
     * 设置订单名称
     */
	public void setOrderName(java.lang.String orderName){
		this.orderName = orderName;
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
     * 获取商户柜台代码
     */
	public java.lang.String getPosId(){
		return this.posId;
	}
 		
	/**
     * 设置商户柜台代码
     */
	public void setPosId(java.lang.String posId){
		this.posId = posId;
	}
 		
 		
	/**
     * 获取密钥
     */
	public java.lang.String getPub32tr2(){
		return this.pub32tr2;
	}
 		
	/**
     * 设置密钥
     */
	public void setPub32tr2(java.lang.String pub32tr2){
		this.pub32tr2 = pub32tr2;
	}
 		
 		
	/**
     * 获取公钥
     */
	public java.lang.String getPublicKey(){
		return this.publicKey;
	}
 		
	/**
     * 设置公钥
     */
	public void setPublicKey(java.lang.String publicKey){
		this.publicKey = publicKey;
	}
 		
 		
	/**
     * 获取操作员密码
     */
	public java.lang.String getPwd(){
		return this.pwd;
	}
 		
	/**
     * 设置操作员密码
     */
	public void setPwd(java.lang.String pwd){
		this.pwd = pwd;
	}
 		
 		
	/**
     * 获取返回类型
     */
	public java.lang.String getReturnType(){
		return this.returnType;
	}
 		
	/**
     * 设置返回类型
     */
	public void setReturnType(java.lang.String returnType){
		this.returnType = returnType;
	}
 		
 		
	/**
     * 获取0、不可用 1、可用
     */
	public java.lang.Integer getStatus(){
		return this.status;
	}
 		
	/**
     * 设置0、不可用 1、可用
     */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
 		
	/**
     * 获取交易码
     */
	public java.lang.String getTxcode(){
		return this.txcode;
	}
 		
	/**
     * 设置交易码
     */
	public void setTxcode(java.lang.String txcode){
		this.txcode = txcode;
	}
 		
 		
	/**
     * 获取操作员号
     */
	public java.lang.String getUserId(){
		return this.userId;
	}
 		
	/**
     * 设置操作员号
     */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
 }