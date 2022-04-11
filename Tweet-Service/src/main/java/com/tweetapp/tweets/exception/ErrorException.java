package com.tweetapp.tweets.exception;

public class ErrorException extends Exception {

	/**
	 * 
	 */
	
	public String exceptionMessage;
	public String errorMessage;
	public String methodName;

	public ErrorException(String exception, String message, String methodName) {
		super(exception);
	}

}
