package com.rochatec.pos.athena.preferences.initializer;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.rochatec.pos.athena.app.Activator;

public class AthenaPosInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	    store.setDefault("welcome.text","FECHADO");
	    store.setDefault("server.protocol","HTTP");
	    store.setDefault("server.hostname","localhost");
	    store.setDefault("server.port",8080);
	    store.setDefault("server.context","athena-web");
	    store.setDefault("box.status","CLOSED");
	    store.setDefault("box.user.id","");
	}

}
