package com.lti.notification.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lti.notification.dao.NotificationMessageDao;
import com.lti.notification.entity.NotificationMessage;
import com.lti.notification.entity.TransactionData;
import com.lti.notification.requestdto.MessageRequestDto;

@Component
public class NotificationMessageDaoImpl implements NotificationMessageDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMessageDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public NotificationMessage getNotificationMessage(MessageRequestDto messageRequestDto, Long transactionId) {
		
		NotificationMessage notificationMessage;
        try {
        	notificationMessage = (NotificationMessage) this.entityManager
        			.createQuery("from NotificationMessage where transaction_type=:transactionType and country_code=:countryCode and message_type=:messageType")
                    .setParameter("transactionType", transactionId)
                    .setParameter("countryCode", messageRequestDto.getCountryCode())
                    .setParameter("messageType", messageRequestDto.getMessageType())
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
		return notificationMessage;
	}

	@Override
	public TransactionData getTransactionData(String transactionType) {
		
		TransactionData td;
        try {
        	td = (TransactionData) this.entityManager
        			.createQuery("from TransactionData where transaction_type=:transactionType")
                    .setParameter("transactionType", transactionType)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
		return td;
	}

}