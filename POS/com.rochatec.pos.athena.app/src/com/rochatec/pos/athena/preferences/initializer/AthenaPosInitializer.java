package com.rochatec.pos.athena.preferences.initializer;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.rochatec.pos.athena.app.Activator;
import com.rochatec.pos.athena.persistence.model.BoxStatus;
import com.rochatec.pos.athena.tools.POSATHENA;

public class AthenaPosInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	    store.setDefault("welcome.text","FECHADO");
	    store.setDefault(POSATHENA.SERVER_PROTOCOL,"HTTP");
	    store.setDefault(POSATHENA.SERVER_HOSTNAME,"localhost");
	    store.setDefault(POSATHENA.SERVER_PORT,8080);
	    store.setDefault(POSATHENA.SERVER_CONTEXT,"athena-web");
	    store.setDefault(POSATHENA.BOX_STATUS,BoxStatus.CLOSED.name());
	    store.setDefault(POSATHENA.BOX_USER_ID,"");
	}

}
