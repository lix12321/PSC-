package cn.wellcare.exception;

public class InactiveTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4214964653067493716L;

	public InactiveTimeException() {
		super();
	}

	public InactiveTimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InactiveTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InactiveTimeException(String message) {
		super(message);
	}

	public InactiveTimeException(Throwable cause) {
		super(cause);
	}

}
