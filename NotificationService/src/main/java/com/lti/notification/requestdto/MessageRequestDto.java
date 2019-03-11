package com.lti.notification.requestdto;

import java.io.Serializable;

import com.lti.notification.entity.TransactionData;

public class MessageRequestDto implements Serializable {

	private String transactionType;
	private String countryCode;
	private String messageType;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
