package cn.wellcare.core.exception;

/**
 * 未授权
 * 
 * @author zhaihl
 *
 */
public class UnauthorizedException extends RuntimeException {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	public UnauthorizedException(Throwable cause, String code) {
		super(cause);
		this.code = code;
	}

	public UnauthorizedException(String message, String code) {
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