package com.rochatec.framework.exception;

public class WeightFormatException extends BadFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4608546540282858387L;
	
	public WeightFormatException() {
		super("bad.format.weight.error");
	}
	
	public WeightFormatException(String message) {
		this(message,new Throwable());
	}
	
	public WeightFormatException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public WeightFormatException(Throwable throwable){
		this("bad.format.weight.error",throwable);
	}
			
			
}
