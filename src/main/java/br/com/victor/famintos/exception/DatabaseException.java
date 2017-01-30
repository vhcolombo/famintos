package br.com.victor.famintos.exception;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
public class DatabaseException extends Exception {
	private static final long serialVersionUID = 3149973273100069779L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}

}
