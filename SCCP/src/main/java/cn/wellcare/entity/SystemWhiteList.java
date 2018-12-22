package cn.wellcare.entity;

import java.io.Serializable;
/**
 * 权限白名单
 * <p>Table: <strong>system_white_list</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>text</td><td>内容</td></tr>
 *   <tr><td>enable</td><td>{@link java.lang.Integer}</td><td>enable</td><td>smallint</td><td>是否生效</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>userId</td><td>{@link java.lang.Integer}</td><td>user_id</td><td>integer</td><td>用户id</td></tr>
 * </table>
 *
 */
public class SystemWhiteList implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6141705789110350424L;
	private java.lang.String content; // 内容
 	private java.lang.Integer enable; //是否生效
 	private java.lang.Integer id; //id
 	private java.lang.Integer userId; //用户id
 	
 		
 		
	/**
     * 获取内容
     */
	public java.lang.String getContent(){
		return this.content;
	}
 		
	/**
     * 设置内容
     */
	public void setContent(java.lang.String content){
		this.content = content;
	}
 		
 		
	/**
     * 获取是否生效
     */
	public java.lang.Integer getEnable(){
		return this.enable;
	}
 		
	/**
     * 设置是否生效
     */
	public void setEnable(java.lang.Integer enable){
		this.enable = enable;
	}
 		
 		
	/**
     * 获取id
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
 		
	/**
     * 获取用户id
     */
	public java.lang.Integer getUserId(){
		return this.userId;
	}
 		
	/**
     * 设置用户id
     */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
 }