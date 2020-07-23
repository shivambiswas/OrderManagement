package com.order.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorSummary;
	private String errorMessage;

	public GenericException() {
		// Default constructor
	}

	public GenericException(String errorCode, String errorSummary, String errorMessage) {
		this.errorCode = errorCode;
		this.errorSummary = errorSummary;
		this.errorMessage = errorMessage;
	}

	public GenericException(String errorCode, String errorSummary, String errorMessage, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorSummary = errorSummary;
		this.errorMessage = errorMessage;
	}

	public GenericException(Throwable cause) {
		super(cause);

	}

	public GenericException(String message, Throwable cause) {
		super(message, cause);

	}

	public GenericException(String message) {
		super(message);
		this.errorMessage = message;
	}

	public GenericException(String errorCode, String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorCode = errorCode;

	}

	public GenericException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;

	}

	public GenericException(String errorCode, Object... msgArg) {
		super(errorCode);

	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorSummary() {
		return errorSummary;
	}

}

