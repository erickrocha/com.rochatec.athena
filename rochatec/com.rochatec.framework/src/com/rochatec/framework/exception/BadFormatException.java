package com.rochatec.framework.exception;

public class BadFormatException extends I18nClientException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7855671613820762924L;
	
	public BadFormatException() {
		super("bad.format.error");
	}
	
	public BadFormatException(String message) {
		this(message,new Throwable());
	}
	
	public BadFormatException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public BadFormatException(Throwable throwable){
		this("bad.format.erro",throwable);
	}

}
