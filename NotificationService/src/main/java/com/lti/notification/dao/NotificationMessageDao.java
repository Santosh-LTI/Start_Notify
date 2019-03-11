package com.lti.notification.dao;

import org.springframework.stereotype.Component;

import com.lti.notification.entity.NotificationMessage;
import com.lti.notification.entity.TransactionData;
import com.lti.notification.requestdto.MessageRequestDto;

@Component
public interface NotificationMessageDao {

	public NotificationMessage getNotificationMessage(MessageRequestDto dto, Long transactionId);
	
	public TransactionData getTransactionData(String transactionType);
	
}
