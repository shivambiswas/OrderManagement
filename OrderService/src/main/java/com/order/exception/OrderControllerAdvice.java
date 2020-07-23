package com.order.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderControllerAdvice {

	private static final Logger LOG = LoggerFactory.getLogger(OrderControllerAdvice.class);
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponseVO> dataNotFoundException(HttpServletRequest req, OrderNotFoundException ex) {
		
		LOG.error("Inside OrderNotFoundExceptionHandler",ex);
			
		ErrorResponseVO errorList = getErrorList(ex.getErrorCode(), ex.getErrorMessage(),
				ex.getErrorSummary());
					return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
	} 
	
	private ErrorResponseVO getErrorList(String errorCode, String errorMessage, String errorSummary) {
		return new ErrorResponseVO(errorCode, errorMessage, errorSummary);
	} 
	
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ErrorResponseVO> genericExceptionException(HttpServletRequest req, GenericException ex) {
		
		LOG.error("Inside OrderNotFoundExceptionHandler",ex);
			
		ErrorResponseVO errorList = getErrorList(ex.getErrorCode(), ex.getErrorMessage(),
				ex.getErrorSummary());
					return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
	} 
	
	
}
