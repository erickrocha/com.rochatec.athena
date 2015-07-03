package com.rochatec.pos.athena.exception;

public class UserNotLoggedException extends UserException{

	private static final long serialVersionUID = -4767793855088812100L;
	
	public UserNotLoggedException() {
		super("user.login.not.logged");
	}
	
	public UserNotLoggedException(String message) {
		super(message);
	}
	
	public UserNotLoggedException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
