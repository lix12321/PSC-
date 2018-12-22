package cn.wellcare.pojo;

import java.io.Serializable;

import cn.wellcare.constant.Constants;
import cn.wellcare.exception.BusinessException;
import cn.wellcare.exception.ErrorEnum;

public class CommonResponse<T> implements Serializable {
	private static final long serialVersionUID = -3404886040638951329L;

	protected Boolean success;

	protected String msgCode;
	protected String msgInfo;
	/**
	 * 是否有权限
	 */
	protected Boolean hasPermit = false;

	/**
	 * 请求URI
	 */
	protected String requestURI;

	public CommonResponse() {
		this.success = true;
		this.msgCode = Constants.RESONSE_SUCCESS_CODE;
		this.msgInfo = Constants.RESONSE_SUCCESS_MSG;
	}



	public void setError(ErrorEnum error) {
		this.success = false;
		this.msgCode = error.getErrCode();
		this.msgInfo = error.getErrDesc();
	}

	public void setError(BusinessException be) {
		this.success = false;
		this.msgCode = be.getCode();
		this.msgInfo = be.getMessage();
	}

	public String getMsgCode() {
		return this.msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgInfo() {
		return this.msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public boolean getHasPermit() {
		return hasPermit;
	}

	public void setHasPermit(boolean hasPermit) {
		this.hasPermit = hasPermit;
	}

	@Override
	public String toString() {
		return "ServerResponse [success=" + success + ", msgCode=" + msgCode + ", msgInfo=" + msgInfo + ", requestURI="
				+ requestURI + "]";
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
