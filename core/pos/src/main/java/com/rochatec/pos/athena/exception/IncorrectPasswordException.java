package com.rochatec.pos.athena.exception;

public class IncorrectPasswordException extends UserException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5674344868656107435L;
	
	public IncorrectPasswordException() {
		super("user.login.incorrect.exception");
	}
	
	public IncorrectPasswordException(String message) {
		super(message);
	}
	
	public IncorrectPasswordException(String message,Throwable throwable) {
		super(message,throwable);
	}

}
