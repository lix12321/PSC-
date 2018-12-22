package cn.wellcare.acc.entity;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>psc_pi_acc_detail</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>amount</td><td>{@link java.math.BigDecimal}</td><td>amount</td><td>numeric</td><td>充值/消费金额</td></tr>
 *   <tr><td>amtBalance</td><td>{@link java.math.BigDecimal}</td><td>amt_balance</td><td>numeric</td><td>当前账户余额</td></tr>
 *   <tr><td>atmNo</td><td>{@link java.lang.String}</td><td>atm_no</td><td>character varying</td><td>自助机编号</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp with time zone</td><td>创建时间</td></tr>
 *   <tr><td>creator</td><td>{@link java.lang.String}</td><td>creator</td><td>character varying</td><td>创建人</td></tr>
 *   <tr><td>dateHap</td><td>{@link java.util.Date}</td><td>date_hap</td><td>timestamp with time zone</td><td>发生日期</td></tr>
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>character</td><td>删除标志</td></tr>
 *   <tr><td>euDirect</td><td>{@link java.lang.Integer}</td><td>eu_direct</td><td>smallint</td><td>1增加，-1减少</td></tr>
 *   <tr><td>euOptype</td><td>{@link java.lang.String}</td><td>eu_optype</td><td>character varying</td><td>1充值/提现 2消费
信用额度的增加和减少从pi_acc_credit中获取
退费时金额不能超过账户余额，并且不能超过同类型累计充值总额</td></tr>
 *   <tr><td>modifier</td><td>{@link java.lang.String}</td><td>modifier</td><td>character</td><td>修改人</td></tr>
 *   <tr><td>modityTime</td><td>{@link java.util.Date}</td><td>modity_time</td><td>timestamp with time zone</td><td>修改时间</td></tr>
 *   <tr><td>nameEmpOpera</td><td>{@link java.lang.String}</td><td>name_emp_opera</td><td>character varying</td><td>操作人姓名</td></tr>
 *   <tr><td>note</td><td>{@link java.lang.String}</td><td>note</td><td>text</td><td>摘要</td></tr>
 *   <tr><td>pkAccdt</td><td>{@link java.lang.Integer}</td><td>pk_accdt</td><td>integer</td><td>账户流水记录</td></tr>
 *   <tr><td>pkDepopi</td><td>{@link java.lang.String}</td><td>pk_depopi</td><td>character</td><td>非医院人员操作则不填</td></tr>
 *   <tr><td>pkEmpOpera</td><td>{@link java.lang.String}</td><td>pk_emp_opera</td><td>character</td><td>非医院人员操作则不填</td></tr>
 *   <tr><td>pkOrg</td><td>{@link java.lang.Integer}</td><td>pk_org</td><td>integer</td><td>所属机构</td></tr>
 *   <tr><td>pkPiacc</td><td>{@link java.lang.Integer}</td><td>pk_piacc</td><td>integer</td><td>账户主键</td></tr>
 * </table>
 *
 */
public class PscPiAccDetail implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6842340983450762877L;
	private java.math.BigDecimal amount; // 充值/消费金额
	private java.math.BigDecimal amtBalance; // 当前账户余额（变化后）
 	private java.lang.String atmNo; //自助机编号
 	private java.util.Date createTime; //创建时间
 	private java.lang.String creator; //创建人
 	private java.util.Date dateHap; //发生日期
 	private java.lang.String delFlag; //删除标志
	private java.lang.String rechargeType; // 充值方式
	private java.lang.String remark; // 操作备注,同订单备注
 	private java.lang.Integer euDirect; //1增加，-1减少
 	private java.lang.String euOptype; //1充值/提现 2消费
	/**
	 * 信用额度的增加和减少从pi_acc_credit中获取 <br>
	 * 退费时金额不能超过账户余额，并且不能超过同类型累计充值总额
	 */
 	private java.lang.String modifier; //修改人
 	private java.util.Date modityTime; //修改时间
 	private java.lang.String nameEmpOpera; //操作人姓名
 	private java.lang.String note; //摘要
 	private java.lang.Integer pkAccdt; //账户流水记录
 	private java.lang.String pkDepopi; //非医院人员操作则不填
 	private java.lang.String pkEmpOpera; //非医院人员操作则不填
 	private java.lang.Integer pkOrg; //所属机构
 	private java.lang.Integer pkPiacc; //账户主键
	private java.lang.Integer orderId;//订单id
 	
 		
 		
	/**
     * 获取充值/消费金额
     */
	public java.math.BigDecimal getAmount(){
		return this.amount;
	}
 		
	/**
     * 设置充值/消费金额
     */
	public void setAmount(java.math.BigDecimal amount){
		this.amount = amount;
	}
 		
 		
	/**
     * 获取当前账户余额
     */
	public java.math.BigDecimal getAmtBalance(){
		return this.amtBalance;
	}
 		
	/**
     * 设置当前账户余额
     */
	public void setAmtBalance(java.math.BigDecimal amtBalance){
		this.amtBalance = amtBalance;
	}
 		
 		
	/**
     * 获取自助机编号
     */
	public java.lang.String getAtmNo(){
		return this.atmNo;
	}
 		
	/**
     * 设置自助机编号
     */
	public void setAtmNo(java.lang.String atmNo){
		this.atmNo = atmNo;
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
     * 获取发生日期
     */
	public java.util.Date getDateHap(){
		return this.dateHap;
	}
 		
	/**
     * 设置发生日期
     */
	public void setDateHap(java.util.Date dateHap){
		this.dateHap = dateHap;
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
     * 获取1增加，-1减少
     */
	public java.lang.Integer getEuDirect(){
		return this.euDirect;
	}
 		
	/**
     * 设置1增加，-1减少
     */
	public void setEuDirect(java.lang.Integer euDirect){
		this.euDirect = euDirect;
	}
 		
 		
	/**
     * 获取1充值/提现 2消费
信用额度的增加和减少从pi_acc_credit中获取
退费时金额不能超过账户余额，并且不能超过同类型累计充值总额
     */
	public java.lang.String getEuOptype(){
		return this.euOptype;
	}
 		
	/**
     * 设置1充值/提现 2消费
信用额度的增加和减少从pi_acc_credit中获取
退费时金额不能超过账户余额，并且不能超过同类型累计充值总额
     */
	public void setEuOptype(java.lang.String euOptype){
		this.euOptype = euOptype;
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
     * 获取操作人姓名
     */
	public java.lang.String getNameEmpOpera(){
		return this.nameEmpOpera;
	}
 		
	/**
     * 设置操作人姓名
     */
	public void setNameEmpOpera(java.lang.String nameEmpOpera){
		this.nameEmpOpera = nameEmpOpera;
	}
 		
 		
	/**
     * 获取摘要
     */
	public java.lang.String getNote(){
		return this.note;
	}
 		
	/**
     * 设置摘要
     */
	public void setNote(java.lang.String note){
		this.note = note;
	}
 		
 		
	/**
     * 获取账户流水记录
     */
	public java.lang.Integer getPkAccdt(){
		return this.pkAccdt;
	}
 		
	/**
     * 设置账户流水记录
     */
	public void setPkAccdt(java.lang.Integer pkAccdt){
		this.pkAccdt = pkAccdt;
	}
 		
 		
	/**
     * 获取非医院人员操作则不填
     */
	public java.lang.String getPkDepopi(){
		return this.pkDepopi;
	}
 		
	/**
     * 设置非医院人员操作则不填
     */
	public void setPkDepopi(java.lang.String pkDepopi){
		this.pkDepopi = pkDepopi;
	}
 		
 		
	/**
     * 获取非医院人员操作则不填
     */
	public java.lang.String getPkEmpOpera(){
		return this.pkEmpOpera;
	}
 		
	/**
     * 设置非医院人员操作则不填
     */
	public void setPkEmpOpera(java.lang.String pkEmpOpera){
		this.pkEmpOpera = pkEmpOpera;
	}
 		
 		
	/**
     * 获取所属机构
     */
	public java.lang.Integer getPkOrg(){
		return this.pkOrg;
	}
 		
	/**
     * 设置所属机构
     */
	public void setPkOrg(java.lang.Integer pkOrg){
		this.pkOrg = pkOrg;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the rechargeType
	 */
	public java.lang.String getRechargeType() {
		return this.rechargeType;
	}

	/**
	 * @param rechargeType the rechargeType to set
	 */
	public void setRechargeType(java.lang.String rechargeType) {
		this.rechargeType = rechargeType;
	}

	/**
	 * @return the remark
	 */
	public java.lang.String getRemark() {
		return this.remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
}