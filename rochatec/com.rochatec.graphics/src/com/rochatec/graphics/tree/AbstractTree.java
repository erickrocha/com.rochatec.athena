package com.rochatec.graphics.tree;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.rochatec.graphics.dialog.ITreeOrTableSelection;
import com.rochatec.graphics.util.LayoutFactory;

public abstract class AbstractTree implements ITreeOrTableSelection{
	
	private TreeViewer treeViewer;
	
	public AbstractTree(Composite parent) {
		this(parent,SWT.NONE);
	}
	
	public AbstractTree(Composite parent,int style) {
		createContents(parent,style);
	}
	
	private void createContents(Composite parent, int style){
		parent.setLayout(LayoutFactory.getInstance().getFillLayout());
		treeViewer = new TreeViewer(parent,style);
		makeCollumns(treeViewer);
	}
	
	public void setHeaderVisible(boolean enabled){
		this.treeViewer.getTree().setHeaderVisible(enabled);
	}
	
	public void setLinesVisible(boolean enabled){
		this.treeViewer.getTree().setLinesVisible(enabled);
	}
	
	protected abstract void makeCollumns(TreeViewer treeViewer);
	
	public void setLabelProvider(ILabelProvider labelProvider){
		treeViewer.setLabelProvider(labelProvider);
	}	
	
	public void addDoubleClickListener(IDoubleClickListener listener){
		treeViewer.addDoubleClickListener(listener);
	}
	
	public void removeDoubleClickListener(IDoubleClickListener listener){
		treeViewer.removeDoubleClickListener(listener);
	}
	
	public void addKeyListener(KeyListener listener){
		treeViewer.getTree().addKeyListener(listener);
	}
	
	public void setInput(Object object){
		treeViewer.setInput(object);
	}
	
	public ISelection getSelection(){
		return treeViewer.getSelection();
	}
	
	public Tree getTree(){
		return treeViewer.getTree();
	}
	
	public void setSelection(ISelection selection){
		treeViewer.setSelection(selection);
	}
	
	public void setContentProvider(IContentProvider contentProvider){
		treeViewer.setContentProvider(contentProvider);
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener){
		treeViewer.addSelectionChangedListener(listener);
	}
	
	public void removeSelectonChangedListener(ISelectionChangedListener listener){
		treeViewer.removeSelectionChangedListener(listener);
	}
	
	public TreeViewer getViewer(){
		return treeViewer;
	}

}
