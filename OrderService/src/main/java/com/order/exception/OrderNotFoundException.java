package com.order.exception;

public class OrderNotFoundException extends GenericException {

	private static final long serialVersionUID = 1L;
	
	public OrderNotFoundException(String message,String errorCode,String errorSummary ) {
		super(message,errorCode,errorSummary);
	}

}