package com.rochatec.graphics.dialog;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.events.KeyListener;

public interface ITreeOrTableSelection {

	public void addDoubleClickListener(IDoubleClickListener listener);
	
	public void addKeyListener(KeyListener listener);
	
	public void addSelectionChangedListener(ISelectionChangedListener listener);
	
	public ISelection getSelection();
	
	public void setInput(Object input);
	
	public void setContentProvider(IContentProvider contentProvider);
	
	public void setLabelProvider(ILabelProvider labelProvider);
	
	public void setHeaderVisible(boolean enabled);
	
	public void setLinesVisible(boolean enabled);
	
}
