package com.rochatec.athena.manufacture.icms.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.framework.exception.BadFormatException;

public class IcmsLabelProvider extends LabelProvider implements ITableLabelProvider{

	
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 2){
			Activator.getImageDescriptor(IPathIcons.TAX_16).createImage();
		}
		return null;
	}
	
	
	public String getText(Object element) {
		Icms icms = (Icms)element;
		return icms.getDescription();
	}

	public String getColumnText(Object element, int columnIndex) {
		try{
			Icms icms = (Icms)element;
			switch (columnIndex) {
			case 0:
				return icms.getDescription();
			case 1:
				return Formatter.getCurrency().mask(icms.getPercentage());
			}
		}catch (BadFormatException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
