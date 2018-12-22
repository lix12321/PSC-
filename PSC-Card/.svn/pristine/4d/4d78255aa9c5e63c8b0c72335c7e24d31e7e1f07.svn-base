package cn.wellcare.core.exception;

/**
 * 业务层级的异常，这类异常在message中不能包含任何调试信息，必须是用户友好的描述信息， 能够显示在界面给用户看
 * 
 * <p>
 * 异常处理规范：
 * </p>
 * <ul>
 * <li>1. 代码在抛出这类型异常之前，必须先将详细描述信息、内部异常堆栈等记录到日志或通过log4j打印
 * <li>2. 底层代码（算法和基层工具除外）不允许捕获任何运行异常，有异常的，一侓将异常抛出，服务端统一捕获，以返回给前端，前端可根据异常信息提示给调用者
 * 
 * </ul>
 * 
 * @author zhaihl
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(Throwable cause, String code) {
		super(cause);
		this.code = code;
	}

	public BusinessException(String message, String code) {
		super(message);
		this.code = code;
	}

	/**
	 * 异常码
	 */
	private String code;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}