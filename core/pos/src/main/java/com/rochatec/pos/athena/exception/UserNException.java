package com.rochatec.pos.athena.exception;

public class UserNException extends I18nException {

	private static final long serialVersionUID = 1L;
	
	
	public UserNException() {
		super("user.exception");
	}
	
	public UserNException(String message) {
		super(message);
	}
	
	public UserNException(String message, Throwable throwable) {
		super(message,throwable);
	}
	
}
