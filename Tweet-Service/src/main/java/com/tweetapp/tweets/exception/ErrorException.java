package com.tweetapp.tweets.exception;

public class ErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String exceptionMessage;
	public String errorMessage;
	public String methodName;

	public ErrorException(String exception, String message, String methodName) {

		this.exceptionMessage = exception;
		this.errorMessage = message;
		this.methodName = methodName;
	}

}
