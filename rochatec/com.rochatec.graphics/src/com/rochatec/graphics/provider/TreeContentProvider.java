package com.rochatec.graphics.provider;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.rochatec.framework.model.TreeObject;
import com.rochatec.framework.model.TreeParent;

public class TreeContentProvider implements IStructuredContentProvider,
		ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getChildren(Object parent) {
		if (parent instanceof TreeParent) {
			return ((TreeParent)parent).getChildren();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object child) {
		if (child instanceof TreeObject) {
			return ((TreeObject) child).getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object parent) {
		if (parent instanceof TreeParent)
			return ((TreeParent)parent).hasChildren();
		return false;
	}

	@Override
	public Object[] getElements(Object parent) {
		if (parent instanceof TreeParent && ((TreeParent)parent).getParent() == null){
			return new Object[]{parent};
		}
		return getChildren(parent);
	}

}
