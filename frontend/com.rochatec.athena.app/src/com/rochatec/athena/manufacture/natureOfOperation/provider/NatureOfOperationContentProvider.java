package com.rochatec.athena.manufacture.natureOfOperation.provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.rochatec.athena.model.NatureOfOperation;

public class NatureOfOperationContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getElements(Object inputElement) {
		return ((List<NatureOfOperation>)inputElement).toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		NatureOfOperation operation =  (NatureOfOperation)parentElement;
		if (operation.getChilds() != null && !operation.getChilds().isEmpty()){
			return operation.getChilds().toArray();
		}
		return new Object[]{};
	}

	@Override
	public Object getParent(Object element) {
		NatureOfOperation operation =  (NatureOfOperation)element;
		return operation.getParent() != null ? operation.getParent() : null;
	}

	@Override
	public boolean hasChildren(Object element) {
		NatureOfOperation operation =  (NatureOfOperation)element;
		if (operation.getChilds() != null && !operation.getChilds().isEmpty()){
			return true;
		}
		return false;
	}

}
