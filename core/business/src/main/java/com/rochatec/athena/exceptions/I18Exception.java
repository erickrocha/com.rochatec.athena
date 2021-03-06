package com.rochatec.athena.exceptions;

import com.rochatec.athena.tools.MessageBundleLoader;



public abstract class I18Exception extends Exception {

	private static final long serialVersionUID = 6132699296045747288L;
	
	public I18Exception() {
	}
	
	public I18Exception(String message) {
		super(MessageBundleLoader.getMessage(message));
	}
	
	public I18Exception(String message,Throwable throwable) {
		super(MessageBundleLoader.getMessage(message),throwable);
	}
	
}
