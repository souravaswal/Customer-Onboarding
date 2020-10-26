package com.springboot.exceptions;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private int code;
	
	public BadRequestException(final String message, final int code) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
