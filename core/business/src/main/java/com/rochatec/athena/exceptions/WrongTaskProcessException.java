package com.rochatec.athena.exceptions;

public class WrongTaskProcessException extends I18Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7962472000526728695L;
	
	public WrongTaskProcessException() {
		super("task.process.wrong");
	}
	
	public WrongTaskProcessException(String message) {
		super(message);
	}
	
	public WrongTaskProcessException(String message,Throwable throwable) {
		super(message,throwable);
	}

}
