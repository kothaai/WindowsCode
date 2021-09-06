package com.indianbank.cbs.bean;

import java.util.List;

public class ViewAccountRequest {
  public String customerId;
  public List<String> accountIds;
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

}
