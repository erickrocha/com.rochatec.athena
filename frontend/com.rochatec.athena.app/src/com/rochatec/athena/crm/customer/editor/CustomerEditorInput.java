package com.rochatec.athena.crm.customer.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Customer;

public class CustomerEditorInput implements IEditorInput{
	
	private Customer customer;
	
	public CustomerEditorInput(Customer customer) {
		this.customer = customer;
	}
	
	public CustomerEditorInput() {
		this(new Customer());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return Messages.getMessage("customer.name");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
