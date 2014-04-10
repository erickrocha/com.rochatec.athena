package com.rochatec.framework.exception;

public class DateFormatException extends BadFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3428173119025515140L;
	
	public DateFormatException() {
		super("bad.format.date.error");
	}
	
	public DateFormatException(String message) {
		this(message,new Throwable());
	}
	
	public DateFormatException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public DateFormatException(Throwable throwable){
		this("bad.format.date.error",throwable);
	}

}
