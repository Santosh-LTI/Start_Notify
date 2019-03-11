package com.lti.notification.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.notificationmessage.server.entity.TransactionData;

@Entity
@Table(name = "message_info")
public class NotificationMessage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "message_id")
	private Long messageId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_type")
	private TransactionData transactionType;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "message_type")
	private String messageType;

	@Column(name = "message")
	private String message;

	@Column(name = "subject")
	private String subject;

	@Column(name = "email_tolist")
	private String toList;

	@Column(name = "email_cclist")
	private String ccList;

	@Column(name = "created_on")
	private Date createdOn;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public TransactionData getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionData transactionType) {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getToList() {
		return toList;
	}

	public void setToList(String toList) {
		this.toList = toList;
	}

	public String getCcList() {
		return ccList;
	}

	public void setCcList(String ccList) {
		this.ccList = ccList;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
