package com.rochatec.athena.provider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class SessionSourceProvider extends AbstractSourceProvider{
	
	public static final String SESSION_STATE = "com.rochatec.metallurgical.app.sessionState";
	private final static String LOGGED_IN = "loggedIn"; 
    private final static String LOGGED_OUT = "loggedOut"; 
    boolean loggedIn; 

	@Override
	public void dispose() {
		
	}

	@Override
	public Map<String,String> getCurrentState() {
		Map<String, String> currentState = new HashMap<String, String>(1); 
        String curState =  loggedIn?LOGGED_IN:LOGGED_OUT; 
        currentState.put(SESSION_STATE, curState); 
        return currentState; 
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] {SESSION_STATE}; 
	}
	
	public void setLoggedIn(boolean loggedIn) { 
        if(this.loggedIn == loggedIn) 
            return; // no change 
        this.loggedIn = loggedIn;  
        String currentState =  loggedIn?LOGGED_IN:LOGGED_OUT; 
        fireSourceChanged(ISources.WORKBENCH, SESSION_STATE, currentState); 
    } 

	

}
