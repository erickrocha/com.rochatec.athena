package com.rochatec.pos.athena.exception;

public class UserLoginException extends UserException{

	private static final long serialVersionUID = 6888220608718391888L;
	
	public UserLoginException() {
		super("user.login.exception");
	}
	
	public UserLoginException(String message) {
		super(message);
	}
	
	public UserLoginException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
