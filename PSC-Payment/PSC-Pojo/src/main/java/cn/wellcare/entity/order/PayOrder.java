package cn.wellcare.entity.order;

import java.io.Serializable;
/**
 * 支付订单
 * <p>Table: <strong>pay_order</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>accountId</td><td>{@link java.lang.Integer}</td><td>account_id</td><td>integer</td><td>账户ID</td></tr>
 *   <tr><td>accountName</td><td>{@link java.lang.String}</td><td>account_name</td><td>character varying</td><td>账户姓名</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp with time zone</td><td>创建时间</td></tr>
 *   <tr><td>finishTime</td><td>{@link java.util.Date}</td><td>finish_time</td><td>timestamp with time zone</td><td>订单完成时间</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>id</td></tr>
 *   <tr><td>moneyOrder</td><td>{@link java.math.BigDecimal}</td><td>money_order</td><td>numeric</td><td>订单金额</td></tr>
 *   <tr><td>moneyPaidBalance</td><td>{@link java.math.BigDecimal}</td><td>money_paid_balance</td><td>numeric</td><td>账户余额支付总金额</td></tr>
 *   <tr><td>moneyPaidReality</td><td>{@link java.math.BigDecimal}</td><td>money_paid_reality</td><td>numeric</td><td>现金支付总金额</td></tr>
 *   <tr><td>orderState</td><td>{@link java.lang.Integer}</td><td>order_state</td><td>smallint</td><td>订单状态：1、已创建；2、退款中；3、已退款；4、已取消；5、已删除；6、已完成</td></tr>
 *   <tr><td>orderType</td><td>{@link java.lang.String}</td><td>order_type</td><td>character varying</td><td>订单类型</td></tr>
 *   <tr><td>orgId</td><td>{@link java.lang.Integer}</td><td>org_id</td><td>integer</td><td>机构ID</td></tr>
 *   <tr><td>outerOrderSn</td><td>{@link java.lang.String}</td><td>outer_order_sn</td><td>character varying</td><td>订单号</td></tr>
 *   <tr><td>paymentCode</td><td>{@link java.lang.String}</td><td>payment_code</td><td>character varying</td><td>支付方式编码</td></tr>
 *   <tr><td>paymentName</td><td>{@link java.lang.String}</td><td>payment_name</td><td>character varying</td><td>支付方式名称</td></tr>
 *   <tr><td>paymentStatus</td><td>{@link java.lang.Integer}</td><td>payment_status</td><td>smallint</td><td>支付状态 0-未支付；1-已支付</td></tr>
 *   <tr><td>paySn</td><td>{@link java.lang.String}</td><td>pay_sn</td><td>character varying</td><td>支付订单号，传给第三方支付的订单号（单个订单支付时与订单号一致，多商户支付时，为统一订单号）</td></tr>
 *   <tr><td>payTime</td><td>{@link java.util.Date}</td><td>pay_time</td><td>timestamp with time zone</td><td>付款时间</td></tr>
 *   <tr><td>relationOrderSn</td><td>{@link java.lang.String}</td><td>relation_order_sn</td><td>character varying</td><td>多商户拆单时使用</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>character varying</td><td>订单备注</td></tr>
 *   <tr><td>reqIp</td><td>{@link java.lang.String}</td><td>req_ip</td><td>character varying</td><td>请求ip</td></tr>
 *   <tr><td>tradeSn</td><td>{@link java.lang.String}</td><td>trade_sn</td><td>character varying</td><td>在线支付交易流水号</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp with time zone</td><td>更新时间</td></tr>
 * </table>
 *
 */
public class PayOrder implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6567915142378025114L;
	private java.lang.Integer id; // id
 	private java.util.Date createTime; //创建时间
 	private java.util.Date finishTime; //订单完成时间
	private java.lang.Integer accountId; // 账户ID
	private java.lang.String handleNum; // 操作员工号
	private java.lang.String handleName; // 操作员姓名
 	private java.math.BigDecimal moneyOrder; //订单金额
 	private java.math.BigDecimal moneyPaidBalance; //账户余额支付总金额
 	private java.math.BigDecimal moneyPaidReality; //现金支付总金额
 	private java.lang.Integer orderState; //订单状态：1、已创建；2、退款中；3、已退款；4、已取消；5、已删除；6、已完成
 	private java.lang.String orderType; //订单类型
 	private java.lang.Integer orgId; //机构ID
 	private java.lang.String outerOrderSn; //订单号
 	private java.lang.String paymentCode; //支付方式编码
 	private java.lang.String paymentName; //支付方式名称
 	private java.lang.Integer paymentStatus; //支付状态 0-未支付；1-已支付
 	private java.lang.String paySn; //支付订单号，传给第三方支付的订单号（单个订单支付时与订单号一致，多商户支付时，为统一订单号）
 	private java.util.Date payTime; //付款时间
 	private java.lang.String relationOrderSn; //多商户拆单时使用
 	private java.lang.String remark; //订单备注
 	private java.lang.String reqIp; //请求ip
 	private java.lang.String tradeSn; //在线支付交易流水号
 	private java.util.Date updateTime; //更新时间

	public static final Integer ORDER_TYPE_WECHAT_NATIVE = 1;
	public static final Integer ORDER_TYPE_WECHAT_JSAPI = 2;

	/**
	 * 订单状态：已创建
	 */
	public static final Integer ORDER_STATE_CREATE = 1;
	/**
	 * 订单状态：退款中
	 */
	public static final Integer ORDER_STATE_PAYBACK = 2;
	/**
	 * 订单状态：已退款
	 */
	public static final Integer ORDER_STATE_REFUND = 3;
	/**
	 * 订单状态：已取消
	 */
	public static final Integer ORDER_STATE_CANCEL = 4;
	/**
	 * 订单状态：已删除
	 */
	public static final Integer ORDER_STATE_DELETE = 5;
	/**
	 * 订单状态：已完成
	 */
	public static final Integer ORDER_STATE_FINISH = 6;

	/**
	 * 付款状态：未支付
	 */
	public static final Integer ORDER_PAY_STATUS_NO_PAY = 0;
	/**
	 * 付款状态：已支付
	 */
	public static final Integer ORDER_PAY_STATUS_PAEID = 1;
 		
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
     * 获取订单完成时间
     */
	public java.util.Date getFinishTime(){
		return this.finishTime;
	}
 		
	/**
     * 设置订单完成时间
     */
	public void setFinishTime(java.util.Date finishTime){
		this.finishTime = finishTime;
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
     * 获取订单金额
     */
	public java.math.BigDecimal getMoneyOrder(){
		return this.moneyOrder;
	}
 		
	/**
     * 设置订单金额
     */
	public void setMoneyOrder(java.math.BigDecimal moneyOrder){
		this.moneyOrder = moneyOrder;
	}
 		
 		
	/**
     * 获取账户余额支付总金额
     */
	public java.math.BigDecimal getMoneyPaidBalance(){
		return this.moneyPaidBalance;
	}
 		
	/**
     * 设置账户余额支付总金额
     */
	public void setMoneyPaidBalance(java.math.BigDecimal moneyPaidBalance){
		this.moneyPaidBalance = moneyPaidBalance;
	}
 		
 		
	/**
     * 获取现金支付总金额
     */
	public java.math.BigDecimal getMoneyPaidReality(){
		return this.moneyPaidReality;
	}
 		
	/**
     * 设置现金支付总金额
     */
	public void setMoneyPaidReality(java.math.BigDecimal moneyPaidReality){
		this.moneyPaidReality = moneyPaidReality;
	}
 		
 		
	/**
     * 获取订单状态：1、已创建；2、退款中；3、已退款；4、已取消；5、已删除；6、已完成
     */
	public java.lang.Integer getOrderState(){
		return this.orderState;
	}
 		
	/**
     * 设置订单状态：1、已创建；2、退款中；3、已退款；4、已取消；5、已删除；6、已完成
     */
	public void setOrderState(java.lang.Integer orderState){
		this.orderState = orderState;
	}
 		
 		
	/**
     * 获取订单类型
     */
	public java.lang.String getOrderType(){
		return this.orderType;
	}
 		
	/**
     * 设置订单类型
     */
	public void setOrderType(java.lang.String orderType){
		this.orderType = orderType;
	}
 		
 		
	/**
     * 获取机构ID
     */
	public java.lang.Integer getOrgId(){
		return this.orgId;
	}
 		
	/**
     * 设置机构ID
     */
	public void setOrgId(java.lang.Integer orgId){
		this.orgId = orgId;
	}
 		
 		
	/**
     * 获取订单号
     */
	public java.lang.String getOuterOrderSn(){
		return this.outerOrderSn;
	}
 		
	/**
     * 设置订单号
     */
	public void setOuterOrderSn(java.lang.String outerOrderSn){
		this.outerOrderSn = outerOrderSn;
	}
 		
 		
	/**
     * 获取支付方式编码
     */
	public java.lang.String getPaymentCode(){
		return this.paymentCode;
	}
 		
	/**
     * 设置支付方式编码
     */
	public void setPaymentCode(java.lang.String paymentCode){
		this.paymentCode = paymentCode;
	}
 		
 		
	/**
     * 获取支付方式名称
     */
	public java.lang.String getPaymentName(){
		return this.paymentName;
	}
 		
	/**
     * 设置支付方式名称
     */
	public void setPaymentName(java.lang.String paymentName){
		this.paymentName = paymentName;
	}
 		
 		
	/**
     * 获取支付状态 0-未支付；1-已支付
     */
	public java.lang.Integer getPaymentStatus(){
		return this.paymentStatus;
	}
 		
	/**
     * 设置支付状态 0-未支付；1-已支付
     */
	public void setPaymentStatus(java.lang.Integer paymentStatus){
		this.paymentStatus = paymentStatus;
	}
 		
 		
	/**
     * 获取支付订单号，传给第三方支付的订单号（单个订单支付时与订单号一致，多商户支付时，为统一订单号）
     */
	public java.lang.String getPaySn(){
		return this.paySn;
	}
 		
	/**
     * 设置支付订单号，传给第三方支付的订单号（单个订单支付时与订单号一致，多商户支付时，为统一订单号）
     */
	public void setPaySn(java.lang.String paySn){
		this.paySn = paySn;
	}
 		
 		
	/**
     * 获取付款时间
     */
	public java.util.Date getPayTime(){
		return this.payTime;
	}
 		
	/**
     * 设置付款时间
     */
	public void setPayTime(java.util.Date payTime){
		this.payTime = payTime;
	}
 		
 		
	/**
     * 获取多商户拆单时使用
     */
	public java.lang.String getRelationOrderSn(){
		return this.relationOrderSn;
	}
 		
	/**
     * 设置多商户拆单时使用
     */
	public void setRelationOrderSn(java.lang.String relationOrderSn){
		this.relationOrderSn = relationOrderSn;
	}
 		
 		
	/**
     * 获取订单备注
     */
	public java.lang.String getRemark(){
		return this.remark;
	}
 		
	/**
     * 设置订单备注
     */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
 		
 		
	/**
     * 获取请求ip
     */
	public java.lang.String getReqIp(){
		return this.reqIp;
	}
 		
	/**
     * 设置请求ip
     */
	public void setReqIp(java.lang.String reqIp){
		this.reqIp = reqIp;
	}
 		
 		
	/**
     * 获取在线支付交易流水号
     */
	public java.lang.String getTradeSn(){
		return this.tradeSn;
	}
 		
	/**
     * 设置在线支付交易流水号
     */
	public void setTradeSn(java.lang.String tradeSn){
		this.tradeSn = tradeSn;
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

	public java.lang.String getHandleNum() {
		return this.handleNum;
	}

	public void setHandleNum(java.lang.String handleNum) {
		this.handleNum = handleNum;
	}

	public java.lang.String getHandleName() {
		return this.handleName;
	}

	public void setHandleName(java.lang.String handleName) {
		this.handleName = handleName;
	}

	/**
	 * @return the accountId
	 */
	public java.lang.Integer getAccountId() {
		return this.accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(java.lang.Integer accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "PayOrder [id=" + id + ", createTime=" + createTime + ", finishTime=" + finishTime + ", accountId="
				+ accountId + ", handleNum=" + handleNum + ", handleName=" + handleName + ", moneyOrder=" + moneyOrder
				+ ", moneyPaidBalance=" + moneyPaidBalance + ", moneyPaidReality=" + moneyPaidReality + ", orderState="
				+ orderState + ", orderType=" + orderType + ", orgId=" + orgId + ", outerOrderSn=" + outerOrderSn
				+ ", paymentCode=" + paymentCode + ", paymentName=" + paymentName + ", paymentStatus=" + paymentStatus
				+ ", paySn=" + paySn + ", payTime=" + payTime + ", relationOrderSn=" + relationOrderSn + ", remark="
				+ remark + ", reqIp=" + reqIp + ", tradeSn=" + tradeSn + ", updateTime=" + updateTime + "]";
	}

 }