package com.rochatec.graphics.viewer;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Control;

public class LabelViewer extends AbstractViewer{

	private CLabel cLabel;
	
	public LabelViewer(CLabel cLabel) {
		this.cLabel = cLabel;
	}
	
	@Override
	public void setLayoutData(Object layoutData) {
		cLabel.setLayoutData(layoutData);
		
	}

	@Override
	public Control getControl() {
		return cLabel;
	}

}
