package com.rochatec.athena.exceptions;

public class UserException extends I18Exception{

	private static final long serialVersionUID = 1L;
	
	
	public UserException() {
		super("user.exception");
	}
	
	public UserException(String message) {
		super(message);
	}
	
	public UserException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
}
