package com.rochatec.framework.exception;

public class PercentageFormatException extends BadFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8225766220360857456L;
	
	public PercentageFormatException() {
		super("bad.format.percentage.error");
	}
	
	public PercentageFormatException(String message) {
		this(message,new Throwable());
	}
	
	public PercentageFormatException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public PercentageFormatException(Throwable throwable){
		this("bad.format.percentage.error",throwable);
	}

}
