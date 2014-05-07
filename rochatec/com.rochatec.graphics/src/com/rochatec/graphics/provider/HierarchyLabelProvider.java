package com.rochatec.graphics.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.framework.model.HierarchyObject;

public class HierarchyLabelProvider extends LabelProvider{

	@Override
	public String getText(Object element) {
		HierarchyObject hierarchyObject = (HierarchyObject)element;
		return hierarchyObject.getLabel();
	}
	
	@Override
	public Image getImage(Object element) {
		HierarchyObject hierarchyObject = (HierarchyObject)element;
		return hierarchyObject.getImage();
	}
	
}
