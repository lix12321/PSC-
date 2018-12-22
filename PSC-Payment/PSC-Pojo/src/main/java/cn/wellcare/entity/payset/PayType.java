package cn.wellcare.entity.payset;

import java.io.Serializable;
/**
 * 支付方式
 * <p>Table: <strong>pay_type</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>alipaySmNature</td><td>{@link java.lang.Integer}</td><td>alipay_sm_nature</td><td>smallint</td><td>支付宝扫码</td></tr>
 *   <tr><td>alipaySmPassive</td><td>{@link java.lang.Integer}</td><td>alipay_sm_passive</td><td>smallint</td><td>支付宝被动扫码</td></tr>
 *   <tr><td>balance</td><td>{@link java.lang.Integer}</td><td>balance</td><td>smallint</td><td>余额支付</td></tr>
 *   <tr><td>cash</td><td>{@link java.lang.Integer}</td><td>cash</td><td>smallint</td><td>现金支付</td></tr>
 *   <tr><td>credit</td><td>{@link java.lang.Integer}</td><td>credit</td><td>smallint</td><td>信用支付</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>支付方式主键</td></tr>
 *   <tr><td>jhSmNature</td><td>{@link java.lang.Integer}</td><td>jh_sm_nature</td><td>smallint</td><td>聚合支付扫码</td></tr>
 *   <tr><td>jhSmPassive</td><td>{@link java.lang.Integer}</td><td>jh_sm_passive</td><td>smallint</td><td>聚合支付被扫码</td></tr>
 *   <tr><td>orgId</td><td>{@link java.lang.Integer}</td><td>org_id</td><td>integer</td><td>机构主键</td></tr>
 *   <tr><td>wxSmNature</td><td>{@link java.lang.Integer}</td><td>wx_sm_nature</td><td>smallint</td><td>微信原生扫码</td></tr>
 *   <tr><td>wxSmPassive</td><td>{@link java.lang.Integer}</td><td>wx_sm_passive</td><td>smallint</td><td>微信被动扫码</td></tr>
 * </table>
 *
 */
public class PayType implements Serializable {
 
 	private java.lang.Integer alipaySmNature; //支付宝扫码
 	private java.lang.Integer alipaySmPassive; //支付宝被动扫码
 	private java.lang.Integer balance; //余额支付
 	private java.lang.Integer cash; //现金支付
 	private java.lang.Integer credit; //信用支付
 	private java.lang.Integer id; //支付方式主键
 	private java.lang.Integer jhSmNature; //聚合支付扫码
 	private java.lang.Integer jhSmPassive; //聚合支付被扫码
 	private java.lang.Integer orgId; //机构主键
 	private java.lang.Integer wxSmNature; //微信原生扫码
 	private java.lang.Integer wxSmPassive; //微信被动扫码
 	
 		
 		
	/**
     * 获取支付宝扫码
     */
	public java.lang.Integer getAlipaySmNature(){
		return this.alipaySmNature;
	}
 		
	/**
     * 设置支付宝扫码
     */
	public void setAlipaySmNature(java.lang.Integer alipaySmNature){
		this.alipaySmNature = alipaySmNature;
	}
 		
 		
	/**
     * 获取支付宝被动扫码
     */
	public java.lang.Integer getAlipaySmPassive(){
		return this.alipaySmPassive;
	}
 		
	/**
     * 设置支付宝被动扫码
     */
	public void setAlipaySmPassive(java.lang.Integer alipaySmPassive){
		this.alipaySmPassive = alipaySmPassive;
	}
 		
 		
	/**
     * 获取余额支付
     */
	public java.lang.Integer getBalance(){
		return this.balance;
	}
 		
	/**
     * 设置余额支付
     */
	public void setBalance(java.lang.Integer balance){
		this.balance = balance;
	}
 		
 		
	/**
     * 获取现金支付
     */
	public java.lang.Integer getCash(){
		return this.cash;
	}
 		
	/**
     * 设置现金支付
     */
	public void setCash(java.lang.Integer cash){
		this.cash = cash;
	}
 		
 		
	/**
     * 获取信用支付
     */
	public java.lang.Integer getCredit(){
		return this.credit;
	}
 		
	/**
     * 设置信用支付
     */
	public void setCredit(java.lang.Integer credit){
		this.credit = credit;
	}
 		
 		
	/**
     * 获取支付方式主键
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置支付方式主键
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
 		
	/**
     * 获取聚合支付扫码
     */
	public java.lang.Integer getJhSmNature(){
		return this.jhSmNature;
	}
 		
	/**
     * 设置聚合支付扫码
     */
	public void setJhSmNature(java.lang.Integer jhSmNature){
		this.jhSmNature = jhSmNature;
	}
 		
 		
	/**
     * 获取聚合支付被扫码
     */
	public java.lang.Integer getJhSmPassive(){
		return this.jhSmPassive;
	}
 		
	/**
     * 设置聚合支付被扫码
     */
	public void setJhSmPassive(java.lang.Integer jhSmPassive){
		this.jhSmPassive = jhSmPassive;
	}
 		
 		
	/**
     * 获取机构主键
     */
	public java.lang.Integer getOrgId(){
		return this.orgId;
	}
 		
	/**
     * 设置机构主键
     */
	public void setOrgId(java.lang.Integer orgId){
		this.orgId = orgId;
	}
 		
 		
	/**
     * 获取微信原生扫码
     */
	public java.lang.Integer getWxSmNature(){
		return this.wxSmNature;
	}
 		
	/**
     * 设置微信原生扫码
     */
	public void setWxSmNature(java.lang.Integer wxSmNature){
		this.wxSmNature = wxSmNature;
	}
 		
 		
	/**
     * 获取微信被动扫码
     */
	public java.lang.Integer getWxSmPassive(){
		return this.wxSmPassive;
	}
 		
	/**
     * 设置微信被动扫码
     */
	public void setWxSmPassive(java.lang.Integer wxSmPassive){
		this.wxSmPassive = wxSmPassive;
	}
 }