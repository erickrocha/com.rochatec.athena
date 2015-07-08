package com.rochatec.pos.athena.exception;

public class IncorrectPasswordNException extends UserNException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5674344868656107435L;
	
	public IncorrectPasswordNException() {
		super("user.login.incorrect.exception");
	}
	
	public IncorrectPasswordNException(String message) {
		super(message);
	}
	
	public IncorrectPasswordNException(String message, Throwable throwable) {
		super(message,throwable);
	}

}
