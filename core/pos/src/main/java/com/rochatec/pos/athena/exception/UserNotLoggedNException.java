package com.rochatec.pos.athena.exception;

public class UserNotLoggedNException extends UserNException {

	private static final long serialVersionUID = -4767793855088812100L;
	
	public UserNotLoggedNException() {
		super("user.login.not.logged");
	}
	
	public UserNotLoggedNException(String message) {
		super(message);
	}
	
	public UserNotLoggedNException(String message, Throwable throwable) {
		super(message,throwable);
	}
}
