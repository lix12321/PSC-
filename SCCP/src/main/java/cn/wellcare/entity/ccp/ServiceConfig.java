package cn.wellcare.entity.ccp;

import java.io.Serializable;
/**
 * 通道配置
 * <p>Table: <strong>service_config</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>addr</td><td>{@link java.lang.String}</td><td>addr</td><td>character varying</td><td>地址</td></tr>
 *   <tr><td>enable</td><td>{@link java.lang.String}</td><td>enable</td><td>character</td><td>是否启用</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>serverCode</td><td>{@link java.lang.String}</td><td>server_code</td><td>character varying</td><td>服务编号</td></tr>
 *   <tr><td>serverName</td><td>{@link java.lang.String}</td><td>server_name</td><td>character varying</td><td>服务名</td></tr>
 * </table>
 *
 */
public class ServiceConfig implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 327614609776024730L;
	private java.lang.String addr; // 地址
 	private java.lang.String enable; //是否启用
 	private java.lang.Integer id; //id
 	private java.lang.String serverCode; //服务编号
 	private java.lang.String serverName; //服务名
 	
	public static final int ENABLE = 1;
	public static final int DISABLED = 0;
 		
	/**
     * 获取地址
     */
	public java.lang.String getAddr(){
		return this.addr;
	}
 		
	/**
     * 设置地址
     */
	public void setAddr(java.lang.String addr){
		this.addr = addr;
	}
 		
 		
	/**
     * 获取是否启用
     */
	public java.lang.String getEnable(){
		return this.enable;
	}
 		
	/**
     * 设置是否启用
     */
	public void setEnable(java.lang.String enable){
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
     * 获取服务编号
     */
	public java.lang.String getServerCode(){
		return this.serverCode;
	}
 		
	/**
     * 设置服务编号
     */
	public void setServerCode(java.lang.String serverCode){
		this.serverCode = serverCode;
	}
 		
 		
	/**
     * 获取服务名
     */
	public java.lang.String getServerName(){
		return this.serverName;
	}
 		
	/**
     * 设置服务名
     */
	public void setServerName(java.lang.String serverName){
		this.serverName = serverName;
	}
 }