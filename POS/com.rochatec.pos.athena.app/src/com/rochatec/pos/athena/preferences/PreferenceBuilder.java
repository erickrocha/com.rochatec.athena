package com.rochatec.pos.athena.preferences;

import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.pos.athena.app.Activator;
import com.rochatec.pos.athena.i18n.Message;
import com.rochatec.pos.athena.preferences.page.BackendPage;
import com.rochatec.pos.athena.preferences.page.PrinterPage;
import com.rochatec.pos.athena.preferences.page.SellPage;
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
		shell.setFont(FontToolkit.getInstance().getTahoma(10,SWT.NONE));
		IPreferencePage welcomePage = new WelcomePage();
		IPreferencePage sellPage = new SellPage();
		IPreferencePage printerPage = new PrinterPage();
		IPreferencePage backendPage = new BackendPage();
		
		welcomePage.setTitle(Message.getMessage("preference.page.welcome"));
		sellPage.setTitle(Message.getMessage("preference.page.sell"));
		printerPage.setTitle(Message.getMessage("preference.page.printer"));
		backendPage.setTitle(Message.getMessage("preference.page.backend"));
		
		PreferenceNode welcomeNode = new PreferenceNode("Welcome", welcomePage);
		PreferenceNode sellNode = new PreferenceNode("Sell",sellPage);
		PreferenceNode printerNode = new PreferenceNode("Printer",printerPage);
		PreferenceNode backendNode = new PreferenceNode("backend",backendPage);
		
		PreferenceManager pm = new PreferenceManager();
		pm.addToRoot(welcomeNode);
		pm.addToRoot(sellNode);
		pm.addToRoot(printerNode);
		pm.addToRoot(backendNode);
		
        PreferenceDialog pd = new PreferenceDialog(shell,pm);        
        pd.setPreferenceStore(Activator.getDefault().getPreferenceStore());
        pd.create();
        return pd;
	}
}
