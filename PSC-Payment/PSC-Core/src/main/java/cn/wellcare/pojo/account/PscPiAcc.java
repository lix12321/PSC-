package cn.wellcare.pojo.account;

import java.io.Serializable;
/**
 * 账户
 * <p>Table: <strong>psc_pi_acc</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>amtAcc</td><td>{@link java.math.BigDecimal}</td><td>amt_acc</td><td>numeric</td><td>账户余额</td></tr>
 *   <tr><td>codeAcc</td><td>{@link java.lang.String}</td><td>code_acc</td><td>character varying</td><td>用于显示</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp with time zone</td><td>创建时间</td></tr>
 *   <tr><td>creator</td><td>{@link java.lang.String}</td><td>creator</td><td>character</td><td>创建人</td></tr>
 *   <tr><td>creditAcc</td><td>{@link java.math.BigDecimal}</td><td>credit_acc</td><td>numeric</td><td>信用额度</td></tr>
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>character</td><td>删除标志</td></tr>
 *   <tr><td>euStatus</td><td>{@link java.lang.String}</td><td>eu_status</td><td>character varying</td><td>1有效 2冻结9作废</td></tr>
 *   <tr><td>modifier</td><td>{@link java.lang.String}</td><td>modifier</td><td>character</td><td>修改人</td></tr>
 *   <tr><td>modityTime</td><td>{@link java.util.Date}</td><td>modity_time</td><td>timestamp with time zone</td><td>修改时间</td></tr>
 *   <tr><td>note</td><td>{@link java.lang.String}</td><td>note</td><td>character varying</td><td>备注</td></tr>
 *   <tr><td>pkPi</td><td>{@link java.lang.Integer}</td><td>pk_pi</td><td>integer</td><td>患者主键</td></tr>
 *   <tr><td>pkPiacc</td><td>{@link java.lang.Integer}</td><td>pk_piacc</td><td>integer</td><td>账户主键</td></tr>
 *   <tr><td>unavailableAcc</td><td>{@link java.math.BigDecimal}</td><td>unavailable_acc</td><td>numeric</td><td>不可用余额</td></tr>
 * </table>
 *
 */
public class PscPiAcc implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 5994666531544180237L;
	private java.math.BigDecimal amtAcc; // 账户余额
 	private java.lang.String codeAcc; //用于显示
 	private java.util.Date createTime; //创建时间
 	private java.lang.String creator; //创建人
 	private java.math.BigDecimal creditAcc; //信用额度
 	private java.lang.String delFlag; //删除标志
 	private java.lang.String euStatus; //1有效 2冻结9作废
 	private java.lang.String modifier; //修改人
 	private java.util.Date modityTime; //修改时间
 	private java.lang.String note; //备注
 	private java.lang.String pkPi; //患者主键
 	private java.lang.Integer pkPiacc; //账户主键
	private java.math.BigDecimal unavailableAcc; //不可用余额
	private java.lang.String pwd; //账户密码
 	
	public static final int ACC_ENABLE = 1;
	public static final int ACC_FREEZE = 2;
	public static final int ACC_DELETED = 9;
 		
	/**
     * 获取账户余额
     */
	public java.math.BigDecimal getAmtAcc(){
		return this.amtAcc;
	}
 		
	/**
     * 设置账户余额
     */
	public void setAmtAcc(java.math.BigDecimal amtAcc){
		this.amtAcc = amtAcc;
	}
 		
 		
	/**
     * 获取用于显示
     */
	public java.lang.String getCodeAcc(){
		return this.codeAcc;
	}
 		
	/**
     * 设置用于显示
     */
	public void setCodeAcc(java.lang.String codeAcc){
		this.codeAcc = codeAcc;
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
     * 获取创建人
     */
	public java.lang.String getCreator(){
		return this.creator;
	}
 		
	/**
     * 设置创建人
     */
	public void setCreator(java.lang.String creator){
		this.creator = creator;
	}
 		
 		
	/**
     * 获取信用额度
     */
	public java.math.BigDecimal getCreditAcc(){
		return this.creditAcc;
	}
 		
	/**
     * 设置信用额度
     */
	public void setCreditAcc(java.math.BigDecimal creditAcc){
		this.creditAcc = creditAcc;
	}
 		
 		
	/**
     * 获取删除标志
     */
	public java.lang.String getDelFlag(){
		return this.delFlag;
	}
 		
	/**
     * 设置删除标志
     */
	public void setDelFlag(java.lang.String delFlag){
		this.delFlag = delFlag;
	}
 		
 		
	/**
     * 获取1有效 2冻结9作废
     */
	public java.lang.String getEuStatus(){
		return this.euStatus;
	}
 		
	/**
     * 设置1有效 2冻结9作废
     */
	public void setEuStatus(java.lang.String euStatus){
		this.euStatus = euStatus;
	}
 		
 		
	/**
     * 获取修改人
     */
	public java.lang.String getModifier(){
		return this.modifier;
	}
 		
	/**
     * 设置修改人
     */
	public void setModifier(java.lang.String modifier){
		this.modifier = modifier;
	}
 		
 		
	/**
     * 获取修改时间
     */
	public java.util.Date getModityTime(){
		return this.modityTime;
	}
 		
	/**
     * 设置修改时间
     */
	public void setModityTime(java.util.Date modityTime){
		this.modityTime = modityTime;
	}
 		
 		
	/**
     * 获取备注
     */
	public java.lang.String getNote(){
		return this.note;
	}
 		
	/**
     * 设置备注
     */
	public void setNote(java.lang.String note){
		this.note = note;
	}
 		
 		
	/**
     * 获取患者主键
     */
	public java.lang.String getPkPi(){
		return this.pkPi;
	}
 		
	/**
     * 设置患者主键
     */
	public void setPkPi(java.lang.String pkPi){
		this.pkPi = pkPi;
	}
 		
 		
	/**
     * 获取账户主键
     */
	public java.lang.Integer getPkPiacc(){
		return this.pkPiacc;
	}
 		
	/**
     * 设置账户主键
     */
	public void setPkPiacc(java.lang.Integer pkPiacc){
		this.pkPiacc = pkPiacc;
	}
 		
 		
	/**
     * 获取不可用余额
     */
	public java.math.BigDecimal getUnavailableAcc(){
		return this.unavailableAcc;
	}
 		
	/**
     * 设置不可用余额
     */
	public void setUnavailableAcc(java.math.BigDecimal unavailableAcc){
		this.unavailableAcc = unavailableAcc;
	}

	/**
	 *获取密码
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 *设置密码
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
 }