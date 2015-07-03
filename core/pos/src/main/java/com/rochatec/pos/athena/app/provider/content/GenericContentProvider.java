package com.rochatec.pos.athena.app.provider.content;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class GenericContentProvider<T> implements IStructuredContentProvider {

	
	public void dispose() {
	}

	
	public void inputChanged(Viewer viewer, Object oldValue, Object newValue) {
	}

	@SuppressWarnings("unchecked")
	
	public Object[] getElements(Object inputElement) {
		List<T> rows = (List<T>)inputElement;
		return rows.toArray();
	}

}