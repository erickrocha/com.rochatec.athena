package com.rochatec.pos.athena.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.rochatec.pos.athena.i18n.Message;

public class ExecuteDialog extends TitleAreaDialog {

	private Integer function;
	private Text txtInput;

	public ExecuteDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle(Message.getMessage("app.execute.dialog.title"));
		setMessage(Message.getMessage("app.execute.dialog.message"), IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		GridData dataInput = new GridData();
		dataInput.grabExcessHorizontalSpace = true;
		dataInput.horizontalAlignment = GridData.FILL;

		txtInput = new Text(container, SWT.BORDER);
		txtInput.setLayoutData(dataInput);
		return area;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		try {
			function = Integer.parseInt(txtInput.getText().trim());
			super.okPressed();
		} catch (NumberFormatException ex) {
			MessageDialog.openError(getShell(), Message.getMessage("app.execute.error"),
					Message.getMessage("app.execute.error.code"));
		}
	}

	public Integer getFunction() {
		return function;
	}

}
