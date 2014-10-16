package com.rochatec.pos.athena.preferences;

import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.pos.athena.app.Activator;
import com.rochatec.pos.athena.i18n.Message;
import com.rochatec.pos.athena.preferences.page.WelcomePage;

public class PreferenceBuilder {

	private static PreferenceBuilder instance;
	
	private PreferenceBuilder(){
		
	}
	
	public static PreferenceBuilder getInstance(){
		if (instance == null){
			instance = new PreferenceBuilder();
		}
		return instance;
	}
	
	public PreferenceDialog getDialog(Shell shell){
		shell.setFont(FontToolkit.getInstance().getFontDefault());
		IPreferencePage welcomePage = new WelcomePage();
		
		welcomePage.setTitle(Message.getMessage("preference.page.welcome"));
		
		PreferenceNode welcomeNode = new PreferenceNode("Welcome", welcomePage); 
		
		PreferenceManager pm = new PreferenceManager();
		pm.addToRoot(welcomeNode);
		
        PreferenceDialog pd = new PreferenceDialog(shell,pm);        
        pd.setPreferenceStore(Activator.getDefault().getPreferenceStore());
        pd.create();
        return pd;
	}
}
