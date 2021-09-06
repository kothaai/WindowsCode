package com.indianbank.cbs.bean;

import java.util.List;

public class TransactionEnquiryRequest {
	private String customerId;
	private List<String> accountIds;
	private List<String> transactionIds;
	private String fromDate;
	private String toDate;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<String> getAccountIds() {
		return accountIds;
	}
	public void setAccountIds(List<String> accountIds) {
		this.accountIds = accountIds;
	}
	public List<String> getTransactionIds() {
		return transactionIds;
	}
	public void setTransactionIds(List<String> transactionIds) {
		this.transactionIds = transactionIds;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getMessageIds() {
		return messageIds;
	}
	public void setMessageIds(String messageIds) {
		this.messageIds = messageIds;
	}
	private String messageIds;

}
