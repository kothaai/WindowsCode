package com.indianbank.cbs.bean.Exception;

import com.indianbank.cbs.bean.CustomerRegistrationRequest;
import com.indianbank.cbs.bean.CustomerVerificationRequest;

public class CustomerException extends RuntimeException {
	private String responseCode;
	private String responseMessage;
	private String transactionId;
	private String transactionDateTime;
	private Object[] object;
	private String httpCode;
	private CustomerRegistrationRequest customerRegistrationRequest;
	private CustomerVerificationRequest customerVerificationRequest;

	public CustomerException(String responseCode, String responseMessage, String transactionId,
			String transactionDateTime, Object[] object, String httpCode,
			CustomerVerificationRequest customerVerificationRequest) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.transactionId = transactionId;
		this.transactionDateTime = transactionDateTime;
		this.object = object;
		this.httpCode = httpCode;
		this.customerVerificationRequest = customerVerificationRequest;
	}

	public CustomerException(String responseCode, String responseMessage, String transactionId,
			String transactionDateTime, Object[] object, String httpCode,
			CustomerRegistrationRequest customerRegistrationRequest) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.transactionId = transactionId;
		this.transactionDateTime = transactionDateTime;
		this.object = object;
		this.httpCode = httpCode;
		this.customerRegistrationRequest = customerRegistrationRequest;
	}
	
	public CustomerVerificationRequest getCustomerVerificationRequest() {
		return customerVerificationRequest;
	}

	public CustomerRegistrationRequest getCustomerRegistrationRequest() {
		return customerRegistrationRequest;
	}

	public Object[] getObject() {
		return object;
	}
	
	public String getHttpCode() {
		return httpCode;
	}
	
	public String getResponseCode() {
		return responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
}
