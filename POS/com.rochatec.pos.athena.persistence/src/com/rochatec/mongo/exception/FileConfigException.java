package com.rochatec.mongo.exception;

import com.rochatec.mongo.message.Message;

/**
 * Created by epr on 07/10/14.
 */
public class FileConfigException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -39518241999988483L;

	public FileConfigException() {
        super(Message.getMessage("file.config.notfound"));
    }

    public FileConfigException(Throwable cause) {
        super(Message.getMessage("file.config.notfound"), cause);
    }

    
}
