package com.indianbank.cbs.bean;

import java.util.List;

public class CustomerVerificationResponse {
	  private String responseCode;
	  private String responseMessage;
	  private String transactionId;
	  private String transactionDateTime;
	  private String firstName;
	  private String lastName;
	  private String mobileNumber;
	  private List<AccountDetails> accounts;
	  private List<AddressDetails> addressDetails;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public List<AccountDetails> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountDetails> accounts) {
		this.accounts = accounts;
	}
	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}
}
