package com.rochatec.graphics.table;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.rochatec.graphics.util.LayoutFactory;

public abstract class AbstractCheckTable {
	
	protected CheckboxTableViewer tableViewer;
	
	public AbstractCheckTable(Composite parent) {
		createContents(parent);
	}
	
	private void createContents(Composite parent){
		parent.setLayout(LayoutFactory.getInstance().getFillLayout());
		tableViewer = CheckboxTableViewer.newCheckList(parent,SWT.BORDER | SWT.SINGLE| SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);
		buildCollumns(tableViewer);
		addListeners();
	}
	
	protected abstract void addListeners();
	
	protected abstract void buildCollumns(TableViewer tableViewer);
	
	public void setLabelProvider(ILabelProvider labelProvider){
		tableViewer.setLabelProvider(labelProvider);
	}	
	
	public void addDoubleClickListener(IDoubleClickListener listener){
		tableViewer.addDoubleClickListener(listener);
	}
	
	public void removeDoubleClickListener(IDoubleClickListener listener){
		tableViewer.removeDoubleClickListener(listener);
	}
	
	public void addKeyListener(KeyListener listener){
		tableViewer.getTable().addKeyListener(listener);
	}
	
	public void setInput(Object object){
		tableViewer.setInput(object);
	}
	
	public ISelection getSelection(){
		return tableViewer.getSelection();
	}
	
	public Table getTable(){
		return tableViewer.getTable();
	}
	
	public void setSelection(ISelection selection){
		tableViewer.setSelection(selection);
	}
	
	public void setContentProvider(IContentProvider contentProvider){
		tableViewer.setContentProvider(contentProvider);
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener){
		tableViewer.addSelectionChangedListener(listener);
	}
	
	public void removeSelectonChangedListener(ISelectionChangedListener listener){
		tableViewer.removeSelectionChangedListener(listener);
	}
	
	public TableViewer getViewer(){
		return tableViewer;
	}	
	
	public void setCheckedAll(boolean checked){
		tableViewer.setAllChecked(checked);
	}
	
	public boolean isCheckedAll(){
		return tableViewer.getCheckedElements().length == tableViewer.getTable().getItemCount();
	}
}
