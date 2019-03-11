package com.lti.notification.service;

import org.springframework.stereotype.Component;

import com.lti.notification.requestdto.MessageRequestDto;
import com.lti.notification.responsedto.MessageResponseDto;


@Component
public interface NotificationMessageService {

	public MessageResponseDto sendEmail(MessageRequestDto messageRequestDto);

}
