package com.app.exception;

public class SMSException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMSException() {
		super();
	}
	public SMSException(String message) {
		super(message);
	}
}
