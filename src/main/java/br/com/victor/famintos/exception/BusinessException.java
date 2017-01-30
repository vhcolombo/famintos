package br.com.victor.famintos.exception;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = -6145991440458913052L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

}
