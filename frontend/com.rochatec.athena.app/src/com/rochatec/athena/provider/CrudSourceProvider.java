package com.rochatec.athena.provider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class CrudSourceProvider extends AbstractSourceProvider{
	
	public static final String CRUD_INSTANCE = "com.rochatec.athena.app.crudInstance";
	private String instance;
	
	@Override
	public void dispose() {
		
	}

	@Override
	public Map<String, String> getCurrentState() {
		Map<String, String> currentState = new HashMap<String, String>(1);
		currentState.put(CRUD_INSTANCE, instance); 
        return currentState; 
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] {CRUD_INSTANCE}; 
	}
	
	public void setInstance(String instance){
		this.instance = instance;
		 fireSourceChanged(ISources.WORKBENCH, CRUD_INSTANCE,instance); 
	}

}
