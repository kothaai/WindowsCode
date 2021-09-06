package com.indianbank.cbs.bean;

import java.util.List;

public class CustomerRegistrationRequest extends MetaDetails {
	private String customerId;
	private String firstName;
	private String lastName;
    private String mobileNumber;
    private List<AccountDetails> accounts;
    private List<AddressDetails> addressDetails;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
}
