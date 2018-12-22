package cn.wellcare.entity;

import java.io.Serializable;

/**
 * 角色资源对应表
 * <p>Table: <strong>system_resources_roles</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp with time zone</td><td>create_time</td></tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>resourcesId</td><td>{@link Integer}</td><td>resources_id</td><td>integer</td><td>资源id</td></tr>
 *   <tr><td>rolesId</td><td>{@link Integer}</td><td>roles_id</td><td>integer</td><td>角色_id</td></tr>
 * </table>
 *
 */
public class SystemResourcesRoles implements Serializable {

 	private java.util.Date createTime; //create_time
 	private Integer id; //id
 	private Integer resourcesId; //资源id
 	private Integer rolesId; //角色_id



	/**
     * 获取create_time
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
     * 设置create_time
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}


	/**
     * 获取id
     */
	public Integer getId(){
		return this.id;
	}

	/**
     * 设置id
     */
	public void setId(Integer id){
		this.id = id;
	}


	/**
     * 获取资源id
     */
	public Integer getResourcesId(){
		return this.resourcesId;
	}

	/**
     * 设置资源id
     */
	public void setResourcesId(Integer resourcesId){
		this.resourcesId = resourcesId;
	}


	/**
     * 获取角色_id
     */
	public Integer getRolesId(){
		return this.rolesId;
	}

	/**
     * 设置角色_id
     */
	public void setRolesId(Integer rolesId){
		this.rolesId = rolesId;
	}
 }