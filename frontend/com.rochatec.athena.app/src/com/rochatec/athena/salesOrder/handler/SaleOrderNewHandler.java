package com.rochatec.athena.salesOrder.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.client.service.SalesClientService;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.salesOrder.view.SaleOrderHistoryView;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.TreeParent;

public class SaleOrderNewHandler extends DefaultCrudHandler implements ISelectionProvider{
	
	private Customer customer;
	private ISelection selection;
	private ListenerList listeners;
	private SaleOrderHistoryView saleOrderHistoryView;
	private SalesClientService salesClientService = ServiceFactory.getInstance().getSalesClientService();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITreeSelection selection =  (ITreeSelection)HandlerUtil.getCurrentSelection(event);		
		TreeParent root = (TreeParent)selection.getFirstElement();
		customer = (Customer)root.getObject();
		
		
		return super.execute(event);
	}

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
									SaleOrderNewHandler.this, selection));
				}
			});
		}
	}
	
}
