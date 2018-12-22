package cn.wellcare.card.entity;

import java.io.Serializable;
/**
 * 数据字典
 * <p>Table: <strong>sys_code_master</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>codeCd</td><td>{@link java.lang.String}</td><td>code_cd</td><td>character varying</td><td>codeId</td></tr>
 *   <tr><td>codeDiv</td><td>{@link java.lang.String}</td><td>code_div</td><td>character varying</td><td>code组</td></tr>
 *   <tr><td>codeText</td><td>{@link java.lang.String}</td><td>code_text</td><td>character varying</td><td>code中文名称</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp with time zone</td><td>创建时间</td></tr>
 *   <tr><td>sortOrder</td><td>{@link java.lang.Integer}</td><td>sort_order</td><td>smallint</td><td>组内顺序</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp with time zone</td><td>更新时间</td></tr>
 *   <tr><td>useYn</td><td>{@link java.lang.Integer}</td><td>use_yn</td><td>smallint</td><td>1、使用 0、不使用</td></tr>
 * </table>
 *
 */
public class SysCodeMaster implements Serializable {
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 3191314848496192657L;
	private java.lang.String codeCd; //codeId
 	private java.lang.String codeDiv; //code组
 	private java.lang.String codeText; //code中文名称
 	private java.util.Date createTime; //创建时间
 	private java.lang.Integer sortOrder; //组内顺序
 	private java.util.Date updateTime; //更新时间
 	private java.lang.Integer useYn; //1、使用 0、不使用
 	
 	public static int STATUS_USE= 1;
 	public static int STATUS_UNUSE= 0;
 	
 		
 		
	/**
     * 获取codeId
     */
	public java.lang.String getCodeCd(){
		return this.codeCd;
	}
 		
	/**
     * 设置codeId
     */
	public void setCodeCd(java.lang.String codeCd){
		this.codeCd = codeCd;
	}
 		
 		
	/**
     * 获取code组
     */
	public java.lang.String getCodeDiv(){
		return this.codeDiv;
	}
 		
	/**
     * 设置code组
     */
	public void setCodeDiv(java.lang.String codeDiv){
		this.codeDiv = codeDiv;
	}
 		
 		
	/**
     * 获取code中文名称
     */
	public java.lang.String getCodeText(){
		return this.codeText;
	}
 		
	/**
     * 设置code中文名称
     */
	public void setCodeText(java.lang.String codeText){
		this.codeText = codeText;
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
     * 获取组内顺序
     */
	public java.lang.Integer getSortOrder(){
		return this.sortOrder;
	}
 		
	/**
     * 设置组内顺序
     */
	public void setSortOrder(java.lang.Integer sortOrder){
		this.sortOrder = sortOrder;
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
     * 获取1、使用 0、不使用
     */
	public java.lang.Integer getUseYn(){
		return this.useYn;
	}
 		
	/**
     * 设置1、使用 0、不使用
     */
	public void setUseYn(java.lang.Integer useYn){
		this.useYn = useYn;
	}
 }