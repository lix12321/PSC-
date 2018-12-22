package cn.wellcare.entity.log;

import java.io.Serializable;
import java.util.Date;
/**
 * 通知日志
 * <p>Table: <strong>pay_notify_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>error</td><td>{@link java.lang.String}</td><td>error</td><td>text</td><td>异常信息</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>integer</td><td>主键</td></tr>
 *   <tr><td>notifyId</td><td>{@link java.lang.Integer}</td><td>notify_id</td><td>integer</td><td>通知主键</td></tr>
 *   <tr><td>notifyStatus</td><td>{@link java.lang.Integer}</td><td>notify_status</td><td>smallint</td><td>状态</td></tr>
 *   <tr><td>notifyTime</td><td>{@link java.util.Date}</td><td>notify_time</td><td>timestamp with time zone</td><td>通知时间</td></tr>
 * </table>
 *
 */
public class PayNotifyLog implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6115435724005478490L;
	private java.lang.Integer id; // 主键
	private java.lang.String error; // 异常信息
 	private java.lang.Integer notifyId; //通知主键
 	private java.lang.Integer notifyStatus; //状态
 	private java.util.Date notifyTime; //通知时间

	// 通知状态：已通知
	public static final int NOTIFY_DONE = 1;
	// 通知状态：未通知
	public static final int NOTIFY_UNDO = 0;
 		
	public PayNotifyLog() {
		super();
	}

	public PayNotifyLog(String error, Integer notifyId, Integer notifyStatus, Date notifyTime) {
		super();
		this.error = error;
		this.notifyId = notifyId;
		this.notifyStatus = notifyStatus;
		this.notifyTime = notifyTime;
	}

	/**
     * 获取异常信息
     */
	public java.lang.String getError(){
		return this.error;
	}
 		
	/**
     * 设置异常信息
     */
	public void setError(java.lang.String error){
		this.error = error;
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
     * 获取通知主键
     */
	public java.lang.Integer getNotifyId(){
		return this.notifyId;
	}
 		
	/**
     * 设置通知主键
     */
	public void setNotifyId(java.lang.Integer notifyId){
		this.notifyId = notifyId;
	}
 		
 		
	/**
     * 获取状态
     */
	public java.lang.Integer getNotifyStatus(){
		return this.notifyStatus;
	}
 		
	/**
     * 设置状态
     */
	public void setNotifyStatus(java.lang.Integer notifyStatus){
		this.notifyStatus = notifyStatus;
	}
 		
 		
	/**
     * 获取通知时间
     */
	public java.util.Date getNotifyTime(){
		return this.notifyTime;
	}
 		
	/**
     * 设置通知时间
     */
	public void setNotifyTime(java.util.Date notifyTime){
		this.notifyTime = notifyTime;
	}
 }