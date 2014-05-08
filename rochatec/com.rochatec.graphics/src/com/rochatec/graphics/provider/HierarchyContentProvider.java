package com.rochatec.graphics.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.rochatec.framework.model.HierarchyObject;

public class HierarchyContentProvider implements ITreeContentProvider{
	
	private Object[] EMPTY_ARRAY = {};

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		HierarchyObject hierarchyObject = (HierarchyObject)parentElement;
		if (hierarchyObject.getParent() == null && hierarchyObject.getChilds().isEmpty()){
			return new Object[]{hierarchyObject};
		}else if (hierarchyObject.getChilds() != null && !hierarchyObject.getChilds().isEmpty()){
			return hierarchyObject.getChilds().toArray();
		}else{
			return EMPTY_ARRAY;
		}
	}

	@Override
	public Object getParent(Object element) {
		HierarchyObject hierarchyObject = (HierarchyObject)element;
		if (hierarchyObject.getParent() != null)
			return hierarchyObject.getParent();
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		HierarchyObject hierarchyObject = (HierarchyObject)element;
		if (hierarchyObject.getChilds() != null && !hierarchyObject.getChilds().isEmpty())
			return true;
		return false;
	}

}
