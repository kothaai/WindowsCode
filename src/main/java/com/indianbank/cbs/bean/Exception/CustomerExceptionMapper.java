package com.indianbank.cbs.bean.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.indianbank.cbs.bean.ErrorResponse;

@RestControllerAdvice
public class CustomerExceptionMapper {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value=CustomerException.class)
	public ResponseEntity<ErrorResponse> handleCustomerException(CustomerException customerException)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setResponseCode(messageSource.getMessage("error.response.code.500", null, null));
		errorResponse.setResponseMessage(messageSource.getMessage(errorResponse.getResponseCode(),null,null));
		
       ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			errorResponse.setResponseCode(customerException.getResponseCode());
			errorResponse.setResponseMessage(customerException.getResponseMessage());
			responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,null,Integer.parseInt(customerException.getHttpCode()));
			}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return responseEntity;
}
	
	}
