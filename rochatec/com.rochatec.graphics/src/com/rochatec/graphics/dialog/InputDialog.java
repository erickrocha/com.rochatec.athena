package com.rochatec.graphics.dialog;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.WidgetUtils;

public class InputDialog extends ApplicationWindow {

	private String title;
	private String message;

	private FormToolkit toolkit;
	private ScrolledForm form;
	private Text text;

	public InputDialog(Shell owner, String title, String message) {
		super(owner);
		setBlockOnOpen(true);
		this.title = title;
		this.message = message;
	}
	
	@Override
	protected Control createContents(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText(getTitle());
		toolkit.decorateFormHeading(form.getForm());
		form.getBody().setLayout(LayoutFactory.getInstance().getGridLayout(1,0));
		new Label(form.getBody(),SWT.NONE).setText(message);
		text = new Text(form.getBody(),SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		WidgetUtils.backgroundEquals(form.getBody());
		return parent;
	}
	
	public InputDialog(Shell owner) {
		this(owner, "", "");
	}
	
	@Override
	protected void configureShell(Shell shell) {		
		super.configureShell(shell);
		shell.setText(getTitle());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String dialog() {
		open();
		return text.getText();
	}

}
