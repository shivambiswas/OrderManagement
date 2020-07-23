package com.order.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseVO {

	@JsonProperty("errorCode")
	private String errorCode;
	
	@JsonProperty("errorSummary")
	private String errorSummary;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	public static ErrorResponseVO getErrorResponse(String errorCode, String errorSummary, String errorMessage) {
		return new ErrorResponseVO(errorCode, errorMessage, errorSummary);
	}
	
	public ErrorResponseVO(String errorCode, String errorMessage, String errorSummary) {
		
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
		this.errorSummary=errorSummary;
	} 
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorSummary() {
		return errorSummary;
	}
	
	public void setErrorSummary(String errorSummary) {
		this.errorSummary = errorSummary;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorResponseVO [errorCode=" + errorCode + ", errorSummary=" + errorSummary + ", errorMessage="
				+ errorMessage + "]";
	} 
	
}
