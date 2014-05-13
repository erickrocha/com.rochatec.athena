package com.rochatec.athena.crm.customer.dialog;

import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.crm.customer.box.CustomerSearchBox;
import com.rochatec.athena.crm.customer.provider.CustomerLabelProvider;
import com.rochatec.athena.crm.customer.table.CustomerTable;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Customer;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class CustomerDialog extends AbstractDialog<Customer> implements Executable<Customer>{

	public CustomerDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new CustomerSearchBox(parent,this);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(1024,768);
	}

	@Override
	public void createTable(Composite parent) {
		table = new CustomerTable(parent);
		table.setContentProvider(new GenericContentProvider<Customer>());
		table.setLabelProvider(new CustomerLabelProvider());
	}

	@Override
	public String getTitle() {		
		return Messages.getMessage("");
	}

	@Override
	public void execute(List<Customer> objects) {
		table.setInput(objects);
	}

	@Override
	public void execute(Customer object) {
		
	}

}
