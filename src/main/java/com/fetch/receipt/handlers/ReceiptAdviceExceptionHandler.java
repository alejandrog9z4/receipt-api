package com.fetch.receipt.handlers;

import java.util.HashMap;

import javax.validation.UnexpectedTypeException;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fetch.receipt.utils.ConstantsReceipt;
import com.fetch.receipt.utils.ReceiptUtils;

@RestControllerAdvice
public class ReceiptAdviceExceptionHandler {

	Logger log = LoggerFactory.getLogger(ReceiptAdviceExceptionHandler.class);
	private static Marker marker;

	@ExceptionHandler(value = { UnexpectedTypeException.class })
	public ResponseEntity<Object> errorPattern(UnexpectedTypeException exception) {
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("description", "The receipt is invalid");
		log.error(marker,ConstantsReceipt.MESSAGE_LOG_ERROR,exception,ReceiptUtils.getCurrentDate());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<Object> errorPattern(HttpMessageNotReadableException exception) {
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("description", "The receipt is invalid");
		log.error(marker,ConstantsReceipt.MESSAGE_LOG_ERROR,exception,ReceiptUtils.getCurrentDate());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> errorField(MethodArgumentNotValidException exception) {
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("description", "The receipt is invalid");
		log.error(marker,ConstantsReceipt.MESSAGE_LOG_ERROR,exception,ReceiptUtils.getCurrentDate());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

	}


}