package com.rochatec.framework.exception;

import com.rochatec.framework.util.Message;

public class I18nClientException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2695067801570986456L;
	
	public I18nClientException() {
	}
	
	public I18nClientException(String message) {
		super(Message.getMessage(message));
	}
	
	public I18nClientException(String message,Throwable throwable) {
		super(Message.getMessage(message),throwable);
	}

}
