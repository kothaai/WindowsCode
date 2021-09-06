package com.indianbank.cbs.bean;

import java.util.List;

public class ViewAccountResponse {
	private String responseCode;
	private String responseMessage;
	private String transactionId;
	private String transactionDateTime;
	private List<AccountDetails> accounts;
	public List<AccountDetails> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountDetails> accounts) {
		this.accounts = accounts;
	}
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
}
