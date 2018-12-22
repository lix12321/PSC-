package cn.wellcare.card.entity;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>card_batchreg</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>applyDatapath</td><td>{@link java.lang.String}</td><td>apply_datapath</td><td>character varying</td><td>applyDatapath</td></tr>
 *   <tr><td>codeApplydepartment</td><td>{@link java.lang.String}</td><td>code_applydepartment</td><td>character varying</td><td>codeApplydepartment</td></tr>
 *   <tr><td>codeApplyer</td><td>{@link java.lang.String}</td><td>code_applyer</td><td>character varying</td><td>codeApplyer</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp without time zone</td><td>createTime</td></tr>
 *   <tr><td>creator</td><td>{@link java.lang.String}</td><td>creator</td><td>character</td><td>creator</td></tr>
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>character</td><td>delFlag</td></tr>
 *   <tr><td>modifier</td><td>{@link java.lang.String}</td><td>modifier</td><td>character</td><td>modifier</td></tr>
 *   <tr><td>modityTime</td><td>{@link java.util.Date}</td><td>modity_time</td><td>timestamp without time zone</td><td>modityTime</td></tr>
 *   <tr><td>pkBatchreg</td><td>{@link java.lang.String}</td><td>pk_batchreg</td><td>character varying</td><td>pkBatchreg</td></tr>
 *   <tr><td>ts</td><td>{@link java.util.Date}</td><td>ts</td><td>timestamp without time zone</td><td>ts</td></tr>
 * </table>
 *
 */
public class CardBatchreg implements Serializable {
 
 	private java.lang.String applyDatapath; //applyDatapath
 	private java.lang.String codeApplydepartment; //codeApplydepartment
 	private java.lang.String codeApplyer; //codeApplyer
 	private java.util.Date createTime; //createTime
 	private java.lang.String creator; //creator
 	private java.lang.String delFlag; //delFlag
 	private java.lang.String modifier; //modifier
 	private java.util.Date modityTime; //modityTime
 	private java.lang.String pkBatchreg; //pkBatchreg
 	private java.util.Date ts; //ts
 	
 		
 		
	/**
     * 获取applyDatapath
     */
	public java.lang.String getApplyDatapath(){
		return this.applyDatapath;
	}
 		
	/**
     * 设置applyDatapath
     */
	public void setApplyDatapath(java.lang.String applyDatapath){
		this.applyDatapath = applyDatapath;
	}
 		
 		
	/**
     * 获取codeApplydepartment
     */
	public java.lang.String getCodeApplydepartment(){
		return this.codeApplydepartment;
	}
 		
	/**
     * 设置codeApplydepartment
     */
	public void setCodeApplydepartment(java.lang.String codeApplydepartment){
		this.codeApplydepartment = codeApplydepartment;
	}
 		
 		
	/**
     * 获取codeApplyer
     */
	public java.lang.String getCodeApplyer(){
		return this.codeApplyer;
	}
 		
	/**
     * 设置codeApplyer
     */
	public void setCodeApplyer(java.lang.String codeApplyer){
		this.codeApplyer = codeApplyer;
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
     * 获取pkBatchreg
     */
	public java.lang.String getPkBatchreg(){
		return this.pkBatchreg;
	}
 		
	/**
     * 设置pkBatchreg
     */
	public void setPkBatchreg(java.lang.String pkBatchreg){
		this.pkBatchreg = pkBatchreg;
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