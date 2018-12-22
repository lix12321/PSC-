package cn.wellcare.entity.payset;

import java.io.Serializable;
/**
 * 支付设置-微信
 * <p>Table: <strong>pay_setting_wechat</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>accessToken</td><td>{@link java.lang.String}</td><td>access_token</td><td>character varying</td><td>微信访问token</td></tr>
 *   <tr><td>appid</td><td>{@link java.lang.String}</td><td>appid</td><td>character varying</td><td>开发者ID</td></tr>
 *   <tr><td>appScope</td><td>{@link java.lang.String}</td><td>app_scope</td><td>character varying</td><td>snsapi_base、snsapi_userinfo</td></tr>
 *   <tr><td>appsecret</td><td>{@link java.lang.String}</td><td>appsecret</td><td>character varying</td><td>应用密钥</td></tr>
 *   <tr><td>codeAddr</td><td>{@link java.lang.String}</td><td>code_addr</td><td>character varying</td><td>二维码请求地址</td></tr>
 *   <tr><td>createOrderUrl</td><td>{@link java.lang.String}</td><td>create_order_url</td><td>character varying</td><td>微信内部创建订单地址</td></tr>
 *   <tr><td>customerState</td><td>{@link java.lang.String}</td><td>customer_state</td><td>character varying</td><td>自定义状态值</td></tr>
 *   <tr><td>enableNotify</td><td>{@link java.lang.Integer}</td><td>enable_notify</td><td>smallint</td><td>是否启用通知</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>主键</td></tr>
 *   <tr><td>key</td><td>{@link java.lang.String}</td><td>key</td><td>character varying</td><td>API密钥</td></tr>
 *   <tr><td>mchid</td><td>{@link java.lang.String}</td><td>mchid</td><td>character varying</td><td>商户号</td></tr>
 *   <tr><td>notifyUrl</td><td>{@link java.lang.String}</td><td>notify_url</td><td>character varying</td><td>相对域名的uri地址</td></tr>
 *   <tr><td>oauth2Token</td><td>{@link java.lang.String}</td><td>oauth2_token</td><td>character varying</td><td>获取网页授权access-token地址</td></tr>
 *   <tr><td>oauth2Url</td><td>{@link java.lang.String}</td><td>oauth2_url</td><td>character varying</td><td>网页授权地址</td></tr>
 *   <tr><td>orderName</td><td>{@link java.lang.String}</td><td>order_name</td><td>character varying</td><td>订单名称</td></tr>
 *   <tr><td>orgId</td><td>{@link java.lang.Integer}</td><td>org_id</td><td>integer</td><td>机构</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>smallint</td><td>0、不可用 1、可用</td></tr>
 *   <tr><td>sysId</td><td>{@link java.lang.Integer}</td><td>sys_id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>userInfo</td><td>{@link java.lang.String}</td><td>user_info</td><td>character varying</td><td>公众号获取用户信息</td></tr>
 * </table>
 *
 */
public class PaySettingWechat implements Serializable {
 
 	private java.lang.String accessToken; //微信访问token
 	private java.lang.String appid; //开发者ID
 	private java.lang.String appScope; //snsapi_base、snsapi_userinfo
 	private java.lang.String appsecret; //应用密钥
 	private java.lang.String codeAddr; //二维码请求地址
 	private java.lang.String createOrderUrl; //微信内部创建订单地址
 	private java.lang.String customerState; //自定义状态值
 	private java.lang.Integer enableNotify; //是否启用通知
 	private java.lang.Integer id; //主键
 	private java.lang.String key; //API密钥
 	private java.lang.String mchid; //商户号
 	private java.lang.String notifyUrl; //相对域名的uri地址
 	private java.lang.String oauth2Token; //获取网页授权access-token地址
 	private java.lang.String oauth2Url; //网页授权地址
 	private java.lang.String orderName; //订单名称
 	private java.lang.Integer orgId; //机构
 	private java.lang.Integer status; //0、不可用 1、可用
 	private java.lang.Integer sysId; //id
 	private java.lang.String userInfo; //公众号获取用户信息
 	
 		
 		
	/**
     * 获取微信访问token
     */
	public java.lang.String getAccessToken(){
		return this.accessToken;
	}
 		
	/**
     * 设置微信访问token
     */
	public void setAccessToken(java.lang.String accessToken){
		this.accessToken = accessToken;
	}
 		
 		
	/**
     * 获取开发者ID
     */
	public java.lang.String getAppid(){
		return this.appid;
	}
 		
	/**
     * 设置开发者ID
     */
	public void setAppid(java.lang.String appid){
		this.appid = appid;
	}
 		
 		
	/**
     * 获取snsapi_base、snsapi_userinfo
     */
	public java.lang.String getAppScope(){
		return this.appScope;
	}
 		
	/**
     * 设置snsapi_base、snsapi_userinfo
     */
	public void setAppScope(java.lang.String appScope){
		this.appScope = appScope;
	}
 		
 		
	/**
     * 获取应用密钥
     */
	public java.lang.String getAppsecret(){
		return this.appsecret;
	}
 		
	/**
     * 设置应用密钥
     */
	public void setAppsecret(java.lang.String appsecret){
		this.appsecret = appsecret;
	}
 		
 		
	/**
     * 获取二维码请求地址
     */
	public java.lang.String getCodeAddr(){
		return this.codeAddr;
	}
 		
	/**
     * 设置二维码请求地址
     */
	public void setCodeAddr(java.lang.String codeAddr){
		this.codeAddr = codeAddr;
	}
 		
 		
	/**
     * 获取微信内部创建订单地址
     */
	public java.lang.String getCreateOrderUrl(){
		return this.createOrderUrl;
	}
 		
	/**
     * 设置微信内部创建订单地址
     */
	public void setCreateOrderUrl(java.lang.String createOrderUrl){
		this.createOrderUrl = createOrderUrl;
	}
 		
 		
	/**
     * 获取自定义状态值
     */
	public java.lang.String getCustomerState(){
		return this.customerState;
	}
 		
	/**
     * 设置自定义状态值
     */
	public void setCustomerState(java.lang.String customerState){
		this.customerState = customerState;
	}
 		
 		
	/**
     * 获取是否启用通知
     */
	public java.lang.Integer getEnableNotify(){
		return this.enableNotify;
	}
 		
	/**
     * 设置是否启用通知
     */
	public void setEnableNotify(java.lang.Integer enableNotify){
		this.enableNotify = enableNotify;
	}
 		
 		
	/**
     * 获取主键
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置主键
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
 		
	/**
     * 获取API密钥
     */
	public java.lang.String getKey(){
		return this.key;
	}
 		
	/**
     * 设置API密钥
     */
	public void setKey(java.lang.String key){
		this.key = key;
	}
 		
 		
	/**
     * 获取商户号
     */
	public java.lang.String getMchid(){
		return this.mchid;
	}
 		
	/**
     * 设置商户号
     */
	public void setMchid(java.lang.String mchid){
		this.mchid = mchid;
	}
 		
 		
	/**
     * 获取相对域名的uri地址
     */
	public java.lang.String getNotifyUrl(){
		return this.notifyUrl;
	}
 		
	/**
     * 设置相对域名的uri地址
     */
	public void setNotifyUrl(java.lang.String notifyUrl){
		this.notifyUrl = notifyUrl;
	}
 		
 		
	/**
     * 获取获取网页授权access-token地址
     */
	public java.lang.String getOauth2Token(){
		return this.oauth2Token;
	}
 		
	/**
     * 设置获取网页授权access-token地址
     */
	public void setOauth2Token(java.lang.String oauth2Token){
		this.oauth2Token = oauth2Token;
	}
 		
 		
	/**
     * 获取网页授权地址
     */
	public java.lang.String getOauth2Url(){
		return this.oauth2Url;
	}
 		
	/**
     * 设置网页授权地址
     */
	public void setOauth2Url(java.lang.String oauth2Url){
		this.oauth2Url = oauth2Url;
	}
 		
 		
	/**
     * 获取订单名称
     */
	public java.lang.String getOrderName(){
		return this.orderName;
	}
 		
	/**
     * 设置订单名称
     */
	public void setOrderName(java.lang.String orderName){
		this.orderName = orderName;
	}
 		
 		
	/**
     * 获取机构
     */
	public java.lang.Integer getOrgId(){
		return this.orgId;
	}
 		
	/**
     * 设置机构
     */
	public void setOrgId(java.lang.Integer orgId){
		this.orgId = orgId;
	}
 		
 		
	/**
     * 获取0、不可用 1、可用
     */
	public java.lang.Integer getStatus(){
		return this.status;
	}
 		
	/**
     * 设置0、不可用 1、可用
     */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
 		
 		
	/**
     * 获取id
     */
	public java.lang.Integer getSysId(){
		return this.sysId;
	}
 		
	/**
     * 设置id
     */
	public void setSysId(java.lang.Integer sysId){
		this.sysId = sysId;
	}
 		
 		
	/**
     * 获取公众号获取用户信息
     */
	public java.lang.String getUserInfo(){
		return this.userInfo;
	}
 		
	/**
     * 设置公众号获取用户信息
     */
	public void setUserInfo(java.lang.String userInfo){
		this.userInfo = userInfo;
	}
 }