package com.rochatec.athena.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;

public class LogListener implements ILogListener{
	
	private static final Logger LOGGER = Logger.getLogger(LogListener.class.getName());
	
	public LogListener() {
		PropertyConfigurator.configure ("log4j.properties");
	}
	
	@Override
	public void logging(IStatus status, String plugin) {
		if (status.getSeverity() == IStatus.WARNING) {
            if (status.getException() == null) {
                LOGGER.warn(status.getMessage());
            } else {
                LOGGER.warn(status.getMessage() + status.getException());
            }
        } else if (status.getSeverity() == IStatus.ERROR) {
            if (status.getException() == null) {
                LOGGER.error(status.getMessage());            
            } else {
                LOGGER.error(status.getMessage()+status.getException());
            }
        }
	}

}
