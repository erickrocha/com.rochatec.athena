package com.rochatec.pos.athena.preferences.page;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.rochatec.pos.athena.app.Activator;
import com.rochatec.pos.athena.i18n.Message;

public class BackendPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{
	
	
	
	public BackendPage() {
		super(GRID);
	}


	@Override
	public void init(IWorkbench arg0) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Message.getMessage("preference.page.backend"));
		
	}

	@Override
	protected void createFieldEditors() {
		String[][] protocols = {{"HTTP","http"},{"HTTPS","https"}};
		addField(new ComboFieldEditor("server.protocol","Protocol", protocols,getFieldEditorParent()));
		addField(new StringFieldEditor("server.hostname", "Hostname:",getFieldEditorParent()));
		addField(new IntegerFieldEditor("server.port", "Port", getFieldEditorParent()));
		addField(new StringFieldEditor("server.context","Contexto Web",getFieldEditorParent()));
		
	}

}
