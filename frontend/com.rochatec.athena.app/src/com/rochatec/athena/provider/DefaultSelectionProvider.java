package com.rochatec.athena.provider;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class DefaultSelectionProvider implements ISelectionProvider {

	private ISelection selection;
	private ListenerList listeners = new ListenerList();

	public DefaultSelectionProvider(ISelection selection) {
		this.selection = selection;
	}

	public DefaultSelectionProvider() {
		this(null);
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return selection;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		this.listeners.remove(listener);
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
									DefaultSelectionProvider.this, selection));
				}
			});
		}
	}

}
