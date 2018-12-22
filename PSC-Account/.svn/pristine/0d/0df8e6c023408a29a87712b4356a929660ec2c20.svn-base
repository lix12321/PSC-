package cn.wellcare.core.utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author zhaihl
 *
 * @param <T>
 */
public class HttpJsonResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8637111820477625638L;

	public HttpJsonResult() {
	}

	private T rows;
	private String message;
	private Integer total = 0;

	public HttpJsonResult(T rows) {
		this.rows = rows;
	}

	public HttpJsonResult(String errorMessage) {
		this.success = false;
		this.message = errorMessage;
	}

	private Boolean success = true;

	public Boolean getSuccess() {
		return this.success;
	}

	public T getRows() {
		return rows == null ? (T) new ArrayList() : rows;
	}

	public void setRows(T rows) {
		this.rows = rows;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.success = false;
		this.message = message;
	}

	public void setTotal(Integer count) {
		this.total = count;
	}

	public Integer getTotal() {
		return this.total;
	}

}
