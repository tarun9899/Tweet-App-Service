package com.tweetapp.tweets.common;

import org.springframework.stereotype.Service;

import com.tweetapp.tweets.DTO.MessageDTO;

@Service
public class MessageServiceResponse {
	
	public MessageDTO messageDetails(String message,int messageCode) {
		MessageDTO messageResponse = new MessageDTO();
		messageResponse.setSuccessMessage(message);
		messageResponse.setMessageCode(messageCode);
		return messageResponse;
	}

}
