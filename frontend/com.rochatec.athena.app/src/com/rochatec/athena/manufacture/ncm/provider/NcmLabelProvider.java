package com.rochatec.athena.manufacture.ncm.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.model.Ncm;

public class NcmLabelProvider extends LabelProvider implements ITableLabelProvider{

	
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	
	public String getText(Object element) {
		Ncm ncm = (Ncm)element;
		return ncm.getCode();
	}

	public String getColumnText(Object element, int columnIndex) {
		Ncm ncm = (Ncm)element;
		switch (columnIndex) {
		case 0:
			return ncm.getCode();
		case 1:
			return ncm.getDescription();
		case 2:
			return ncm.getUnitMeasure();
		}
		return null;
	}

}
