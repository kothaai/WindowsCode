package com.indianbank.cbs.bean;

public class MetaDetails {
	private String applicationType;
    private String subApplicationType;
    private String deliveryChannelId;
    private String messageId;
    private String transactionDateTime;
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getSubApplicationType() {
		return subApplicationType;
	}
	public void setSubApplicationType(String subApplicationType) {
		this.subApplicationType = subApplicationType;
	}
	public String getDeliveryChannelId() {
		return deliveryChannelId;
	}
	public void setDeliveryChannelId(String deliveryChannelId) {
		this.deliveryChannelId = deliveryChannelId;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
}
