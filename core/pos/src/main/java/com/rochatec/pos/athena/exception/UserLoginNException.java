package com.rochatec.pos.athena.exception;

public class UserLoginNException extends UserNException {

	private static final long serialVersionUID = 6888220608718391888L;
	
	public UserLoginNException() {
		super("user.login.exception");
	}
	
	public UserLoginNException(String message) {
		super(message);
	}
	
	public UserLoginNException(String message, Throwable throwable) {
		super(message,throwable);
	}
}
