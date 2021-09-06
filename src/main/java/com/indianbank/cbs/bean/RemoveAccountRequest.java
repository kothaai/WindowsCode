package com.indianbank.cbs.bean;

public class RemoveAccountRequest {

	   private String customerId;
	   private String accountId;
	   public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



}
