package com.volvo.congestioncalculator.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity genericException(Exception ex) {
    	
    	logger.error("Exception occured ",ex);
    	
        return new ResponseEntity("Exception Occured "+ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
	
}