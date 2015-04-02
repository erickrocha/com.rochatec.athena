package com.rochatec.pos.athena.preferences.page;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.rochatec.pos.athena.app.Activator;
import com.rochatec.pos.athena.i18n.Message;

public class WelcomePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{
	
	public static final String ID = "com.rochatec.pos.athena.preferences.page.WelcomePage";
	
	public WelcomePage() {
		super(GRID);
	}
	
	@Override
	public void init(IWorkbench workbench) {		
		 setPreferenceStore(Activator.getDefault().getPreferenceStore());
		 setDescription(Message.getMessage("preference.page.welcome"));
	}

	@Override
	protected void createFieldEditors() {
		addField(new StringFieldEditor("welcome.text", "Texto de boas vindas:",getFieldEditorParent()));		
	}
	
}
