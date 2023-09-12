package com.fetch.receipt.exception;

public class ReceiptAdviceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private int code;
	
	
	public ReceiptAdviceException(String message, int code) {
		this.message = message;
		this.code = code;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	

}
