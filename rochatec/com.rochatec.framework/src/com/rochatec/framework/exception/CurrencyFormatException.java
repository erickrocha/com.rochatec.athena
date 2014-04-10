package com.rochatec.framework.exception;

public class CurrencyFormatException extends BadFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7398113059032396723L;
	
	public CurrencyFormatException() {
		super("bad.format.currency.error");
	}
	
	public CurrencyFormatException(String message) {
		this(message,new Throwable());
	}
	
	public CurrencyFormatException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public CurrencyFormatException(Throwable throwable){
		this("bad.format.currency.error",throwable);
	}

}
