package com.rochatec.athena.crm.customer.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.crm.customer.box.CustomerSearchBox;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class CustomerSearchView extends AbstractView implements Executable<Customer> {
	
	public static final String ID = "com.rochatec.athena.crm.customer.view.CustomerSearchView";
	private CustomerSearchBox searchBox;

	@Override
	public void execute(List<Customer> customers) {
		SearchSelection<Customer> selection = new SearchSelection<Customer>(customers);
		getSite().getSelectionProvider().setSelection(selection);		
	}

	@Override
	protected void createContents(Composite parent) {
		searchBox = new CustomerSearchBox(parent,this);		
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						CustomerTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();		
	}

	@Override
	public void execute(Customer object) {
		
	}

}
