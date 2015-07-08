package com.rochatec.pos.athena.exception;

import com.rochatec.pos.athena.utils.MessageBundleLoader;


public abstract class I18nException extends Exception {

	private static final long serialVersionUID = 6132699296045747288L;
	
	public I18nException() {
	}
	
	public I18nException(String message) {
		super(MessageBundleLoader.getMessage(message));
	}
	
	public I18nException(String message, Throwable throwable) {
		super(MessageBundleLoader.getMessage(message),throwable);
	}
	
}
