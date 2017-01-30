package br.com.victor.famintos.exception;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
public class StepsException extends Exception {
	private static final long serialVersionUID = 1160890826067763980L;

	public StepsException() {
		super();
	}

	public StepsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StepsException(String message, Throwable cause) {
		super(message, cause);
	}

	public StepsException(String message) {
		super(message);
	}

	public StepsException(Throwable cause) {
		super(cause);
	}

}
