package com.lti.notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.notification.requestdto.MessageRequestDto;
import com.lti.notification.responsedto.MessageResponseDto;
import com.lti.notification.service.NotificationMessageService;

//@RequestMapping("/notification")
@RestController
public class NotificationMessageController {
	public static final Logger LOGGER = LoggerFactory.getLogger(NotificationMessageController.class);

	@Autowired
	private NotificationMessageService notificationMessageService;

	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MessageResponseDto> saveOffer(@RequestBody MessageRequestDto messageRequestDto) {

		ResponseEntity<MessageResponseDto> responseStatus = null;

		try {
			MessageResponseDto messageResponseDto = notificationMessageService.sendEmail(messageRequestDto);
			responseStatus = ResponseEntity.status(HttpStatus.OK).body(messageResponseDto);
		} catch (Exception ex) {
			LOGGER.error(ex.toString());
			responseStatus = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} finally {
			return responseStatus;
		}

	}

}
