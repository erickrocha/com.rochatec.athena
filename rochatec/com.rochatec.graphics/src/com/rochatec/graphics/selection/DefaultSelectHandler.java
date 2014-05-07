package com.rochatec.graphics.selection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public abstract class DefaultSelectHandler extends AbstractHandler implements ISelectionProvider{
	
	private ISelection selection;
	private ListenerList listeners;
	
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners == null)
			listeners = new ListenerList();
		listeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return selection;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (listeners != null)
			listeners.remove(listener);		
	}

	@Override
	public void setSelection(ISelection selection) {
		this.selection = selection;
		fireEvent();		
	}
	
	private void fireEvent() {
		for (final Object listener : listeners.getListeners()) {
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					((ISelectionChangedListener) listener)
							.selectionChanged(new SelectionChangedEvent(
									DefaultSelectHandler.this, selection));
				}
			});
		}
	}

}
