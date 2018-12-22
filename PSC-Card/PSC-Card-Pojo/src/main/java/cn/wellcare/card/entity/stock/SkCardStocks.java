package cn.wellcare.card.entity.stock;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>sk_card_stocks</strong>
    * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
    *   <tr style="background-color:#ddd;Text-align:Left;">
    *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
    *   </tr>
 *   <tr><td>cardEnd</td><td>{@link java.math.BigDecimal}</td><td>card_end</td><td>numeric</td><td>cardEnd</td></tr>
 *   <tr><td>cardStart</td><td>{@link java.math.BigDecimal}</td><td>card_start</td><td>numeric</td><td>操作类型
  1 入库
  2 出库
  3 退库
  4 作废</td></tr>
 *   <tr><td>cardType</td><td>{@link java.lang.String}</td><td>card_type</td><td>character varying</td><td>cardType</td></tr>
 *   <tr><td>count</td><td>{@link java.math.BigDecimal}</td><td>count</td><td>numeric</td><td>count</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp with time zone</td><td>createTime</td></tr>
 *   <tr><td>creator</td><td>{@link java.lang.String}</td><td>creator</td><td>character varying</td><td>creator</td></tr>
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>character varying</td><td>delFlag</td></tr>
 *   <tr><td>modifier</td><td>{@link java.lang.String}</td><td>modifier</td><td>character varying</td><td>modifier</td></tr>
 *   <tr><td>modityTime</td><td>{@link java.util.Date}</td><td>modity_time</td><td>timestamp with time zone</td><td>modityTime</td></tr>
 *   <tr><td>operType</td><td>{@link java.lang.String}</td><td>oper_type</td><td>character varying</td><td>operType</td></tr>
 *   <tr><td>pkStock</td><td>{@link java.lang.Integer}</td><td>pk_stock</td><td>integer</td><td>pkStock</td></tr>
 *   <tr><td>ts</td><td>{@link java.util.Date}</td><td>ts</td><td>timestamp with time zone</td><td>ts</td></tr>
    * </table>
 *
 */
public class SkCardStocks implements Serializable {

 	private java.math.BigDecimal cardEnd; //cardEnd
 	private java.math.BigDecimal cardStart; //操作类型
 	private java.lang.String cardType; //cardType
 	private java.math.BigDecimal count; //count
 	private java.util.Date createTime; //createTime
 	private java.lang.String creator; //creator
 	private java.lang.String delFlag; //delFlag
 	private java.lang.String modifier; //modifier
 	private java.util.Date modityTime; //modityTime
 	private java.lang.String operType; //operType
 	private java.lang.Integer pkStock; //pkStock
 	private java.util.Date ts; //ts

 		
 		
	/**
     * 获取cardEnd
     */
	public java.math.BigDecimal getCardEnd(){
		return this.cardEnd;
	}
 		
	/**
     * 设置cardEnd
     */
	public void setCardEnd(java.math.BigDecimal cardEnd){
		this.cardEnd = cardEnd;
	}
 		
 		
	/**
     * 获取操作类型
  1 入库
  2 出库
  3 退库
  4 作废
     */
	public java.math.BigDecimal getCardStart(){
		return this.cardStart;
	}
 		
	/**
     * 设置操作类型
  1 入库
  2 出库
  3 退库
  4 作废
     */
	public void setCardStart(java.math.BigDecimal cardStart){
		this.cardStart = cardStart;
	}
 		
 		
	/**
     * 获取cardType
     */
	public java.lang.String getCardType(){
		return this.cardType;
	}
 		
	/**
     * 设置cardType
     */
	public void setCardType(java.lang.String cardType){
		this.cardType = cardType;
	}
 		
 		
	/**
     * 获取count
     */
	public java.math.BigDecimal getCount(){
		return this.count;
	}
 		
	/**
     * 设置count
     */
	public void setCount(java.math.BigDecimal count){
		this.count = count;
	}
 		
 		
	/**
     * 获取createTime
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置createTime
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
 		
	/**
     * 获取creator
     */
	public java.lang.String getCreator(){
		return this.creator;
	}
 		
	/**
     * 设置creator
     */
	public void setCreator(java.lang.String creator){
		this.creator = creator;
	}
 		
 		
	/**
     * 获取delFlag
     */
	public java.lang.String getDelFlag(){
		return this.delFlag;
	}
 		
	/**
     * 设置delFlag
     */
	public void setDelFlag(java.lang.String delFlag){
		this.delFlag = delFlag;
	}
 		
 		
	/**
     * 获取modifier
     */
	public java.lang.String getModifier(){
		return this.modifier;
	}
 		
	/**
     * 设置modifier
     */
	public void setModifier(java.lang.String modifier){
		this.modifier = modifier;
	}
 		
 		
	/**
     * 获取modityTime
     */
	public java.util.Date getModityTime(){
		return this.modityTime;
	}
 		
	/**
     * 设置modityTime
     */
	public void setModityTime(java.util.Date modityTime){
		this.modityTime = modityTime;
	}
 		
 		
	/**
     * 获取operType
     */
	public java.lang.String getOperType(){
		return this.operType;
	}
 		
	/**
     * 设置operType
     */
	public void setOperType(java.lang.String operType){
		this.operType = operType;
	}
 		
 		
	/**
     * 获取pkStock
     */
	public java.lang.Integer getPkStock(){
		return this.pkStock;
	}
 		
	/**
     * 设置pkStock
     */
	public void setPkStock(java.lang.Integer pkStock){
		this.pkStock = pkStock;
	}
 		
 		
	/**
     * 获取ts
     */
	public java.util.Date getTs(){
		return this.ts;
	}
 		
	/**
     * 设置ts
     */
	public void setTs(java.util.Date ts){
		this.ts = ts;
	}
 }