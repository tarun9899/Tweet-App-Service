package com.tweetapp.tweets.DTO;

public class MessageDTO {
 public int messageCode;
 public String successMessage;
 public String errorMessage;
public int getMessageCode() {
	return messageCode;
}
public void setMessageCode(int messageCode) {
	this.messageCode = messageCode;
}
public String getSuccessMessage() {
	return successMessage;
}
public void setSuccessMessage(String successMessage) {
	this.successMessage = successMessage;
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
 
}
