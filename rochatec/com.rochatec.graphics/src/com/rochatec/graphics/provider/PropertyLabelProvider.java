package com.rochatec.graphics.provider;

import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.rochatec.framework.model.Property;

public class PropertyLabelProvider extends LabelProvider implements ITableLabelProvider,ITableColorProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		Property property = (Property)element;
		if (columnIndex == 0)
			return property.getImage();		
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Property property = (Property)element;
		switch (columnIndex) {
		case 0:
			return property.getKey();			
		case 1:
			return property.getValue();
		}
		return null;
	}
	
	private Color getSystemColor(int SWT_KEY){
		return Display.getCurrent().getSystemColor(SWT_KEY);
	}
	
	@Override
	public Image getImage(Object element) {		
		return ((Property)element).getImage();
	}
	
	@Override
	public String getText(Object element) {
		return ((Property)element).getValue();
	}

	@Override
	public Color getForeground(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getSystemColor(SWT.COLOR_BLACK);			
		case 1:
			return getSystemColor(SWT.COLOR_BLACK);		
		}		
		return null;
	}

	@Override
	public Color getBackground(Object element, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return getSystemColor(SWT.COLOR_GRAY);			
		case 1:
			return getSystemColor(SWT.COLOR_WHITE);					
		}		
		return null;
	}

}
