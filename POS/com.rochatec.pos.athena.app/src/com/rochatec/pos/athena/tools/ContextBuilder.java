package com.rochatec.pos.athena.tools;

import org.eclipse.jface.preference.IPreferenceStore;

import com.rochatec.pos.athena.context.IContext;
import com.rochatec.pos.athena.context.POSAthenaContext;

public class ContextBuilder {

	private static ContextBuilder instance;
	
	public static ContextBuilder create(){
		if (instance == null){
			instance = new ContextBuilder();
		}
		return instance;
	}
	
	public IContext buildContext(IPreferenceStore preferenceStore){
		IContext context = new POSAthenaContext();
		context.setAttribute(IContext.HOSTNAME,preferenceStore.getString("server.hostname"));
		context.setAttribute(IContext.PORT,preferenceStore.getInt("server.port"));
		context.setAttribute(IContext.WEB_CONTEXT,preferenceStore.getString("server.context"));
		context.setAttribute(IContext.PROTOCOL,preferenceStore.getString("server.protocol"));
		context.setAttribute(IContext.BOX_STATE,preferenceStore.getString("box.status"));
		return context;
	}
}
