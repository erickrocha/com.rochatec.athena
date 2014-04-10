package com.rochatec.graphics.gui;

import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.rochatec.graphics.provider.TreeContentProvider;
import com.rochatec.graphics.provider.TreeLabelProvider;

public class TreePropertieViewer {

	protected TreeViewer viewer;

	public TreePropertieViewer(Composite parent, int style) {
		viewer = new TreeViewer(parent, style);
		viewer.setContentProvider(new TreeContentProvider());
		viewer.setLabelProvider(new TreeLabelProvider());		
	}

	public TreePropertieViewer(Composite parent) {
		this(parent, SWT.NONE);
	}

	public void setInput(Object object) {
		viewer.setInput(object);
	}
	
	public TreeViewer getViewer(){
		return this.viewer;
	}

	public Tree getTree() {
		return viewer.getTree();
	}

	public boolean setFocus(){
		return getTree().setFocus();
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener){
		viewer.addSelectionChangedListener(listener);
	}
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener){
		viewer.removeSelectionChangedListener(listener);
	}
	
	public void addDoubleClickListener(IDoubleClickListener listener){
		viewer.addDoubleClickListener(listener);
	}
	
	public void removeDoubleClickListener(IDoubleClickListener listener){
		viewer.removeDoubleClickListener(listener);
	}
}
