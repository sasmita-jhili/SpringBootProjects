package com.app.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.exception.SMSException;
import com.app.model.ErrorResponse;

@RestControllerAdvice
public class SMSExceptionHandler {

	@ExceptionHandler(SMSException.class)
	public ResponseEntity<ErrorResponse> showFacultyNotFoundError(SMSException sms) {

		return ResponseEntity.badRequest()
				.body(ErrorResponse.builder().errorId("123").errorMessage(sms.getMessage()).build());
	}
}
