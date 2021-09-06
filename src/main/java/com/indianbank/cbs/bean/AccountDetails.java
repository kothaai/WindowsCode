package com.indianbank.cbs.bean;

public class AccountDetails {
	private String accountNumber;
	private String accountType;
	private String currency;
    private String primaryAccountFlag;
    private String ledgerBalance;
	private String availableBalance;
	private String combinedBalance;
	public String getLedgerBalance() {
		return ledgerBalance;
	}
	public void setLedgerBalance(String ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getCombinedBalance() {
		return combinedBalance;
	}
	public void setCombinedBalance(String combinedBalance) {
		this.combinedBalance = combinedBalance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPrimaryAccountFlag() {
		return primaryAccountFlag;
	}
	public void setPrimaryAccountFlag(String primaryAccountFlag) {
		this.primaryAccountFlag = primaryAccountFlag;
	}
}
