package com.lti.notification.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transation_data")
public class TransactionData implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "message_type")
	private String messageType;

	@Column(name = "status")
	private String status;

	@Column(name = "message_senton")
	private Date messageSentOn;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMessageSentOn() {
		return messageSentOn;
	}

	public void setMessageSentOn(Date messageSentOn) {
		this.messageSentOn = messageSentOn;
	}

}
