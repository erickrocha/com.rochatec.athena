package com.rochatec.athena.manufacture.natureOfOperation.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.model.NatureOfOperation;

public class NatureOfOperationLabelProvider extends LabelProvider implements ITableLabelProvider{

	
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		return null;
	}
	
	@Override
	public String getText(Object element) {
		NatureOfOperation operation = (NatureOfOperation)element;
		return operation.getCfop();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		NatureOfOperation operation = (NatureOfOperation)element;
		switch (columnIndex) {
		case 0:
			return operation.getCfop();
		case 1:
			return operation.getLabel();
		case 2:
			return operation.getApplication();
		}
		return null;
	}

}
