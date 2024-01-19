package com.app.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.exception.SMSException;

@RestControllerAdvice
public class SMSExceptionHandler {

	@ExceptionHandler(SMSException.class)
	public ResponseEntity<String> showFacultyNotFoundError(SMSException sms) {
		
		return new ResponseEntity<String>(sms.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
