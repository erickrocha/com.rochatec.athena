package com.rochatec.graphics.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.rochatec.framework.model.Property;

public class PropertyContentProvider implements IStructuredContentProvider{

	@Override
	public void dispose() {
		
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		List<Property> rows = (List<Property>)inputElement;
		return rows.toArray();
	}

}
