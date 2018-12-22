package cn.wellcare.entity;

import java.io.Serializable;
/**
 * 用户-角色关系
 * <p>Table: <strong>system_user_role</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>roleId</td><td>{@link java.lang.Integer}</td><td>role_id</td><td>integer</td><td>角色id</td></tr>
 *   <tr><td>userId</td><td>{@link java.lang.Integer}</td><td>user_id</td><td>integer</td><td>用户id</td></tr>
 * </table>
 *
 */
public class SystemUserRole implements Serializable {
 
 	private java.lang.Integer id; //id
 	private java.lang.Integer roleId; //角色id
 	private java.lang.Integer userId; //用户id
 	
 		
 		
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
     * 获取角色id
     */
	public java.lang.Integer getRoleId(){
		return this.roleId;
	}
 		
	/**
     * 设置角色id
     */
	public void setRoleId(java.lang.Integer roleId){
		this.roleId = roleId;
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