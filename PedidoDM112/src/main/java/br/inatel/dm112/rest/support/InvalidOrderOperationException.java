package br.inatel.dm112.rest.support;

public class InvalidOrderOperationException extends RuntimeException {

	public InvalidOrderOperationException() {
	}

	public InvalidOrderOperationException(String message) {
		super(message);
	}

	public InvalidOrderOperationException(Throwable cause) {
		super(cause);
	}

	public InvalidOrderOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidOrderOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
