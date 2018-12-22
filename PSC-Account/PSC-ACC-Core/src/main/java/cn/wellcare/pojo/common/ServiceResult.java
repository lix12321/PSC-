package cn.wellcare.pojo.common;

import java.io.Serializable;

import cn.wellcare.core.constant.Constants;
import cn.wellcare.core.exception.ErrorEnum;

/**
 * 
 * @author zhaihl
 *
 * @param <T>
 */
public class ServiceResult<T> implements Serializable {
	private static final long serialVersionUID = -3404886040638951329L;
	/**
	 * 数据
	 */
	protected T data;
	/**
	 * 状态（负数为服务异常，-1为业务异常）
	 */
	protected int status;
	/**
	 * 错误信息（服务异常消息）
	 */
	protected String errorMessage;
	/**
	 * 描述（业务异常消息）
	 */
	protected String desc;

	public ServiceResult() {
		this.status = Constants.SERVICE_RESULT_SUCCESS;
		this.errorMessage = "";
		this.desc = "";
	}

	public ServiceResult(T t) {
		this.status = Constants.SERVICE_RESULT_SUCCESS;
		this.errorMessage = "";
		this.desc = "";
		this.data = t;
	}

	public ServiceResult<T> failedResult(String msgInfo) {
		this.status = Constants.SERVICE_RESULT_BUS_ERROR;
		this.errorMessage = ErrorEnum.BUSINESS_EXCEPTION.getErrDesc();
		this.desc = msgInfo;
		return this;
	}

	public ServiceResult<T> convert2SR(RpcResult<T> rpc) {
		if (rpc == null) {
			return this;
		}
		this.status = rpc.isSuccess() ? Constants.SERVICE_RESULT_SUCCESS : Constants.SERVICE_RESULT_BUS_ERROR;
		this.data = rpc.getData();
		this.errorMessage = (!rpc.isSuccess()) ? ErrorEnum.BUSINESS_EXCEPTION.getErrDesc() : "";
		this.desc = (!rpc.isSuccess()) ? rpc.getMsgInfo() : Constants.RESONSE_SUCCESS_MSG;
		return this;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
