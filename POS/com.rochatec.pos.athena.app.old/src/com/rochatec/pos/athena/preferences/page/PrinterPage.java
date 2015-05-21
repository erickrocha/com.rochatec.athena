package com.rochatec.pos.athena.preferences.page;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.rochatec.pos.athena.app.Activator;
import com.rochatec.pos.athena.i18n.Message;

public class PrinterPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{
	
	public static final String ID = "com.rochatec.pos.athena.preferences.page.PrinterPage";
	
	public PrinterPage() {
		super(GRID);
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription(Message.getMessage("preference.page.printer"));
	}

	@Override
	protected void createFieldEditors() {
		String[][] manufacturers = {{"Bematech","bematech"},{"IBM","ibm"},{"Sweda","sweda"},{"Urmet Taruma","taruma"}};
		addField(new ComboFieldEditor("printer.manufacturer","Fabricante", manufacturers,getFieldEditorParent()));
		addField(new StringFieldEditor("printer.key", "Nº Série:",getFieldEditorParent()));
		addField(new IntegerFieldEditor("printer.number","Nº ECF",getFieldEditorParent()));
	}

}
