package com.rochatec.graphics.provider;

import org.eclipse.jface.viewers.Viewer;

import com.rochatec.graphics.jface.ISingleContentProvider;

public class GenericSingleContentProvider<T> implements ISingleContentProvider{

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getElement(Object inputElement) {		
		return ((T)inputElement);
	}

}
