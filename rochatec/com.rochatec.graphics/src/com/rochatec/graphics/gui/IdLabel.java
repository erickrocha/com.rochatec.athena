package com.rochatec.graphics.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.rochatec.graphics.util.Message;

public class IdLabel {

	private Label label;
	private Group group;

	public IdLabel(Composite parent) {
		group = new Group(parent, SWT.NONE);
		createContents();
	}

	public void clear() {
		this.label.setText("");
	}

	public void setLabelBackground(Color color) {
		this.label.setBackground(color);
	}

	public void setLavelForeground(Color color) {
		this.label.setForeground(color);
	}

	public void setFont(Font font) {
		label.setFont(font);
	}

	public void setLabelText(String text) {
		this.label.setText(text);
	}

	public void setLabelText(Long value) {
		this.label.setText(Long.toString(value));
	}

	public void setLabelText(Integer value) {
		this.label.setText(Integer.toString(value));
	}
	
	public void setLayoutData(GridData gridData){
		this.group.setLayoutData(gridData);
	}

	private void createContents() {
		group.setText(Message.getMessage("idLabel.code.label"));
		FillLayout layout = new FillLayout();
		layout.marginHeight = 5;
		layout.marginWidth  = 5;
		group.setLayout(layout);
		GridData gridData = new GridData(50, 30);
		gridData.verticalSpan = 2;
		group.setLayoutData(gridData);
		label = new Label(group, SWT.NONE);
		label.setFont(new Font(group.getDisplay(), "Impact", 14, 1));
		label.setAlignment(SWT.CENTER);
	}
}
