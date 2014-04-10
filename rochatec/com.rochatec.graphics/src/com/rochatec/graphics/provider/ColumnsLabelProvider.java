package com.rochatec.graphics.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.graphics.Activator;
import com.rochatec.graphics.model.IColumn;
import com.rochatec.graphics.util.IResources;

public class ColumnsLabelProvider extends LabelProvider {
	
	@Override
	public Image getImage(Object element) {
		IColumn column = (IColumn)element;
		switch (column.getSQLType()) {
		case STRING:
			return  Activator.getImageDescriptor(IResources.ICON_STRING_16).createImage();
		case LONG:
			return Activator.getImageDescriptor(IResources.ICON_NUMBER_16).createImage();
		case DATE:
			return Activator.getImageDescriptor(IResources.ICON_DATE_16).createImage();
		case DOUBLE:
			return Activator.getImageDescriptor(IResources.ICON_NUMBER_16).createImage();
		case TIMESTAMP:
			return Activator.getImageDescriptor(IResources.ICON_DATE_16).createImage();
		default:
			return null;
		}		
	}
	
	@Override
	public String getText(Object element) {		
		return ((IColumn)element).getLabel();
	}
}
