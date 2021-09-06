package com.indianbank.cbs.bean;

public class TransactionEnquiryResponse {

	private String transactionDetails;
	private String messageId;
	private String messageName;
	private String deliveryChannel;
	private String transactionId;
	private String transactionAmount;
	private String transactionGroup;
	private String transactionDateTime;
	private String transactionStatus;
	private String remarks;
	private String responseCode;
	private String responseMessage;
	private String beneficiaryAccountNumber;
	private String beneficiaryName;
	private String beneficiaryIFSC;
	public String getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getDeliveryChannel() {
		return deliveryChannel;
	}
	public void setDeliveryChannel(String deliveryChannel) {
		this.deliveryChannel = deliveryChannel;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionGroup() {
		return transactionGroup;
	}
	public void setTransactionGroup(String transactionGroup) {
		this.transactionGroup = transactionGroup;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}
	public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getBeneficiaryIFSC() {
		return beneficiaryIFSC;
	}
	public void setBeneficiaryIFSC(String beneficiaryIFSC) {
		this.beneficiaryIFSC = beneficiaryIFSC;
	}
	

}
