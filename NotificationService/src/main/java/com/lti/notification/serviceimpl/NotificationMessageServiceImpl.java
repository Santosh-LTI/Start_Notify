package com.lti.notification.serviceimpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.lti.notification.dao.NotificationMessageDao;
import com.lti.notification.entity.NotificationMessage;
import com.lti.notification.entity.TransactionData;
import com.lti.notification.requestdto.MessageRequestDto;
import com.lti.notification.responsedto.MessageResponseDto;
import com.lti.notification.service.NotificationMessageService;

@Component
public class NotificationMessageServiceImpl implements NotificationMessageService {

	@Autowired
	public JavaMailSender javaMailSender;

	@Autowired
	NotificationMessageDao notificationMessageDao;

	public MessageResponseDto sendEmail(MessageRequestDto messageRequestDto) {
		MessageResponseDto messageResponseDto = new MessageResponseDto();
		TransactionData transactionData = notificationMessageDao
				.getTransactionData(messageRequestDto.getTransactionType());
		NotificationMessage notificationMessage = notificationMessageDao
				.getNotificationMessage(messageRequestDto,transactionData.getTransactionId());

		if (null != notificationMessage) {
			MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        try {
	            helper.setTo(notificationMessage.getToList());
	            helper.setCc(notificationMessage.getCcList());
	            helper.setText(notificationMessage.getMessage());
	            helper.setSubject(notificationMessage.getSubject());
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        javaMailSender.send(message);
			messageResponseDto.setResponseCode(200L);
			messageResponseDto.setResponseMessage("Mail sent successfully!");
		} else {
			messageResponseDto.setResponseCode(400L);
			messageResponseDto.setResponseMessage("Mail sent failed!");
		}
		return messageResponseDto;
	}

}
