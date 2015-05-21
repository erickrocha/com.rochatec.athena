package com.rochatec.pos.athena.provider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class SellSourceProvider extends AbstractSourceProvider{

	public static final String SELL_INSTANCE = "com.rochatec.pos.athena.provider.SellSourceProvider";
	private String instance;
	
	@Override
	public void dispose() {
		
	}

	@Override
	public Map<String, String> getCurrentState() {
		Map<String, String> currentState = new HashMap<String, String>(1);
		currentState.put(SELL_INSTANCE, instance); 
        return currentState; 
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] {SELL_INSTANCE}; 
	}
	
	public void setInstance(String instance){
		this.instance = instance;
		 fireSourceChanged(ISources.WORKBENCH, SELL_INSTANCE,instance); 
	}

}
