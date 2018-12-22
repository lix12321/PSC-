package cn.wellcare.card.entity;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>card_operate</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>cardNo</td><td>{@link java.lang.String}</td><td>card_no</td><td>character varying</td><td>cardNo</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp without time zone</td><td>createTime</td></tr>
 *   <tr><td>creator</td><td>{@link java.lang.String}</td><td>creator</td><td>character</td><td>creator</td></tr>
 *   <tr><td>dateHap</td><td>{@link java.util.Date}</td><td>date_hap</td><td>timestamp without time zone</td><td>dateHap</td></tr>
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>character</td><td>delFlag</td></tr>
 *   <tr><td>euOptype</td><td>{@link java.lang.String}</td><td>eu_optype</td><td>character varying</td><td>euOptype</td></tr>
 *   <tr><td>modifier</td><td>{@link java.lang.String}</td><td>modifier</td><td>character</td><td>modifier</td></tr>
 *   <tr><td>modityTime</td><td>{@link java.util.Date}</td><td>modity_time</td><td>timestamp without time zone</td><td>modityTime</td></tr>
 *   <tr><td>nameEmpOpera</td><td>{@link java.lang.String}</td><td>name_emp_opera</td><td>character varying</td><td>nameEmpOpera</td></tr>
 *   <tr><td>note</td><td>{@link java.lang.String}</td><td>note</td><td>character varying</td><td>note</td></tr>
 *   <tr><td>pkEmpOpera</td><td>{@link java.lang.String}</td><td>pk_emp_opera</td><td>character</td><td>pkEmpOpera</td></tr>
 *   <tr><td>pkOperateDetail</td><td>{@link java.lang.String}</td><td>pk_operate_detail</td><td>character</td><td>pkOperateDetail</td></tr>
 *   <tr><td>pkOrg</td><td>{@link java.lang.String}</td><td>pk_org</td><td>character</td><td>pkOrg</td></tr>
 *   <tr><td>pkReginfo</td><td>{@link java.lang.String}</td><td>pk_reginfo</td><td>character</td><td>pkReginfo</td></tr>
 *   <tr><td>ts</td><td>{@link java.util.Date}</td><td>ts</td><td>timestamp without time zone</td><td>ts</td></tr>
 * </table>
 *
 */
public class CardOperate implements Serializable {
 
 	private java.lang.String cardNo; //cardNo
 	private java.util.Date createTime; //createTime
 	private java.lang.String creator; //creator
 	private java.util.Date dateHap; //dateHap
 	private java.lang.String delFlag; //delFlag
 	private java.lang.String euOptype; //euOptype
 	private java.lang.String modifier; //modifier
 	private java.util.Date modityTime; //modityTime
 	private java.lang.String nameEmpOpera; //nameEmpOpera
 	private java.lang.String note; //note
 	private java.lang.String pkEmpOpera; //pkEmpOpera
 	private java.lang.String pkOperateDetail; //pkOperateDetail
 	private java.lang.String pkOrg; //pkOrg
 	private java.lang.String pkReginfo; //pkReginfo
 	private java.util.Date ts; //ts
 	
 		
 		
	/**
     * 获取cardNo
     */
	public java.lang.String getCardNo(){
		return this.cardNo;
	}
 		
	/**
     * 设置cardNo
     */
	public void setCardNo(java.lang.String cardNo){
		this.cardNo = cardNo;
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
     * 获取dateHap
     */
	public java.util.Date getDateHap(){
		return this.dateHap;
	}
 		
	/**
     * 设置dateHap
     */
	public void setDateHap(java.util.Date dateHap){
		this.dateHap = dateHap;
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
     * 获取euOptype
     */
	public java.lang.String getEuOptype(){
		return this.euOptype;
	}
 		
	/**
     * 设置euOptype
     */
	public void setEuOptype(java.lang.String euOptype){
		this.euOptype = euOptype;
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
     * 获取nameEmpOpera
     */
	public java.lang.String getNameEmpOpera(){
		return this.nameEmpOpera;
	}
 		
	/**
     * 设置nameEmpOpera
     */
	public void setNameEmpOpera(java.lang.String nameEmpOpera){
		this.nameEmpOpera = nameEmpOpera;
	}
 		
 		
	/**
     * 获取note
     */
	public java.lang.String getNote(){
		return this.note;
	}
 		
	/**
     * 设置note
     */
	public void setNote(java.lang.String note){
		this.note = note;
	}
 		
 		
	/**
     * 获取pkEmpOpera
     */
	public java.lang.String getPkEmpOpera(){
		return this.pkEmpOpera;
	}
 		
	/**
     * 设置pkEmpOpera
     */
	public void setPkEmpOpera(java.lang.String pkEmpOpera){
		this.pkEmpOpera = pkEmpOpera;
	}
 		
 		
	/**
     * 获取pkOperateDetail
     */
	public java.lang.String getPkOperateDetail(){
		return this.pkOperateDetail;
	}
 		
	/**
     * 设置pkOperateDetail
     */
	public void setPkOperateDetail(java.lang.String pkOperateDetail){
		this.pkOperateDetail = pkOperateDetail;
	}
 		
 		
	/**
     * 获取pkOrg
     */
	public java.lang.String getPkOrg(){
		return this.pkOrg;
	}
 		
	/**
     * 设置pkOrg
     */
	public void setPkOrg(java.lang.String pkOrg){
		this.pkOrg = pkOrg;
	}
 		
 		
	/**
     * 获取pkReginfo
     */
	public java.lang.String getPkReginfo(){
		return this.pkReginfo;
	}
 		
	/**
     * 设置pkReginfo
     */
	public void setPkReginfo(java.lang.String pkReginfo){
		this.pkReginfo = pkReginfo;
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