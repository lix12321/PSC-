package cn.wellcare.pojo;

/**
 * 服务接口返回对象。
 * 
 * @author zhaihl
 *
 * @param <T>
 */
public class JsonResult<T>{
	private static final long serialVersionUID = -340488604556522529L;

	protected T data;

	private String status;

	private String desc;

	private String errorMessage;

	public JsonResult() {
	}

	public JsonResult(T t) {
		this.data = t;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
