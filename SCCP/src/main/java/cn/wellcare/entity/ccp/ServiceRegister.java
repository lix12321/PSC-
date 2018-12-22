package cn.wellcare.entity.ccp;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>service_register</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>cache</td><td>{@link java.lang.String}</td><td>cache</td><td>character</td><td>cache</td></tr>
 *   <tr><td>cacheDuration</td><td>{@link java.lang.Integer}</td><td>cache_duration</td><td>integer</td><td>单位：小时</td></tr>
 *   <tr><td>dataType</td><td>{@link java.lang.String}</td><td>data_type</td><td>character varying</td><td>1-JSON 2-HTML 3-XML</td></tr>
 *   <tr><td>enable</td><td>{@link java.lang.String}</td><td>enable</td><td>character varying</td><td>1-启动 0-不启用</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>inFormat</td><td>{@link java.lang.String}</td><td>in_format</td><td>character varying</td><td>inFormat</td></tr>
 *   <tr><td>outFormat</td><td>{@link java.lang.String}</td><td>out_format</td><td>character varying</td><td>outFormat</td></tr>
 *   <tr><td>requestDomain</td><td>{@link java.lang.String}</td><td>request_domain</td><td>character varying</td><td>requestDomain</td></tr>
 *   <tr><td>requestMethod</td><td>{@link java.lang.String}</td><td>request_method</td><td>character varying</td><td>requestMethod</td></tr>
 *   <tr><td>requestPort</td><td>{@link java.lang.Integer}</td><td>request_port</td><td>integer</td><td>requestPort</td></tr>
 *   <tr><td>requestType</td><td>{@link java.lang.String}</td><td>request_type</td><td>character varying</td><td>1-GET  2-POST  3-SOAP</td></tr>
 *   <tr><td>serverCode</td><td>{@link java.lang.String}</td><td>server_code</td><td>character varying</td><td>serverCode</td></tr>
 *   <tr><td>timeOut</td><td>{@link java.lang.Integer}</td><td>time_out</td><td>integer</td><td>timeOut</td></tr>
 *   <tr><td>transCode</td><td>{@link java.lang.String}</td><td>trans_code</td><td>character varying</td><td>transCode</td></tr>
 * </table>
 *
 */
public class ServiceRegister implements Serializable {
 
 	private java.lang.String cache; //cache
 	private java.lang.Integer cacheDuration; //单位：小时
 	private java.lang.String dataType; //1-JSON 2-HTML 3-XML
 	private java.lang.String enable; //1-启动 0-不启用
 	private java.lang.Integer id; //id
 	private java.lang.String inFormat; //inFormat
 	private java.lang.String outFormat; //outFormat
 	private java.lang.String requestDomain; //requestDomain
 	private java.lang.String requestMethod; //requestMethod
 	private java.lang.Integer requestPort; //requestPort
 	private java.lang.String requestType; //1-GET  2-POST  3-SOAP
 	private java.lang.String serverCode; //serverCode
 	private java.lang.Integer timeOut; //timeOut
 	private java.lang.String transCode; //transCode
 	
 		
 		
	/**
     * 获取cache
     */
	public java.lang.String getCache(){
		return this.cache;
	}
 		
	/**
     * 设置cache
     */
	public void setCache(java.lang.String cache){
		this.cache = cache;
	}
 		
 		
	/**
     * 获取单位：小时
     */
	public java.lang.Integer getCacheDuration(){
		return this.cacheDuration;
	}
 		
	/**
     * 设置单位：小时
     */
	public void setCacheDuration(java.lang.Integer cacheDuration){
		this.cacheDuration = cacheDuration;
	}
 		
 		
	/**
     * 获取1-JSON 2-HTML 3-XML
     */
	public java.lang.String getDataType(){
		return this.dataType;
	}
 		
	/**
     * 设置1-JSON 2-HTML 3-XML
     */
	public void setDataType(java.lang.String dataType){
		this.dataType = dataType;
	}
 		
 		
	/**
     * 获取1-启动 0-不启用
     */
	public java.lang.String getEnable(){
		return this.enable;
	}
 		
	/**
     * 设置1-启动 0-不启用
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
     * 获取inFormat
     */
	public java.lang.String getInFormat(){
		return this.inFormat;
	}
 		
	/**
     * 设置inFormat
     */
	public void setInFormat(java.lang.String inFormat){
		this.inFormat = inFormat;
	}
 		
 		
	/**
     * 获取outFormat
     */
	public java.lang.String getOutFormat(){
		return this.outFormat;
	}
 		
	/**
     * 设置outFormat
     */
	public void setOutFormat(java.lang.String outFormat){
		this.outFormat = outFormat;
	}
 		
 		
	/**
     * 获取requestDomain
     */
	public java.lang.String getRequestDomain(){
		return this.requestDomain;
	}
 		
	/**
     * 设置requestDomain
     */
	public void setRequestDomain(java.lang.String requestDomain){
		this.requestDomain = requestDomain;
	}
 		
 		
	/**
     * 获取requestMethod
     */
	public java.lang.String getRequestMethod(){
		return this.requestMethod;
	}
 		
	/**
     * 设置requestMethod
     */
	public void setRequestMethod(java.lang.String requestMethod){
		this.requestMethod = requestMethod;
	}
 		
 		
	/**
     * 获取requestPort
     */
	public java.lang.Integer getRequestPort(){
		return this.requestPort;
	}
 		
	/**
     * 设置requestPort
     */
	public void setRequestPort(java.lang.Integer requestPort){
		this.requestPort = requestPort;
	}
 		
 		
	/**
     * 获取1-GET  2-POST  3-SOAP
     */
	public java.lang.String getRequestType(){
		return this.requestType;
	}
 		
	/**
     * 设置1-GET  2-POST  3-SOAP
     */
	public void setRequestType(java.lang.String requestType){
		this.requestType = requestType;
	}
 		
 		
	/**
     * 获取serverCode
     */
	public java.lang.String getServerCode(){
		return this.serverCode;
	}
 		
	/**
     * 设置serverCode
     */
	public void setServerCode(java.lang.String serverCode){
		this.serverCode = serverCode;
	}
 		
 		
	/**
     * 获取timeOut
     */
	public java.lang.Integer getTimeOut(){
		return this.timeOut;
	}
 		
	/**
     * 设置timeOut
     */
	public void setTimeOut(java.lang.Integer timeOut){
		this.timeOut = timeOut;
	}
 		
 		
	/**
     * 获取transCode
     */
	public java.lang.String getTransCode(){
		return this.transCode;
	}
 		
	/**
     * 设置transCode
     */
	public void setTransCode(java.lang.String transCode){
		this.transCode = transCode;
	}
 }