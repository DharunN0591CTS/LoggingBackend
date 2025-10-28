package com.dharun.vps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class loggingGlobalExceptionHandler {
	
	@ExceptionHandler(noDataFoundException.class)
	public ResponseEntity<String> noDataFoundExceptionInfo(noDataFoundException ex){
		String msg = ex.getMessage();
		return new ResponseEntity<>("NO RECORD FOUND",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(alreadyRecordFoundException.class)
	public ResponseEntity<String> alreadyRecordFoundException(alreadyRecordFoundException ex){
		String msg = ex.getMessage();
		return new ResponseEntity<>("RECORD IS ALREADY FOUND",HttpStatus.BAD_REQUEST);
	}

}
