package com.rochatec.athena.supply.supplier.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.supply.supplier.box.SupplierSearchBox;
import com.rochatec.athena.supply.supplier.provider.SupplierLabelProvider;
import com.rochatec.athena.supply.supplier.table.SupplierTable;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class SupplierDialog extends AbstractDialog<Supplier> implements Executable<Supplier>{
	
	public SupplierDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void execute(List<Supplier> objects) {
		table.setInput(objects);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new SupplierSearchBox(parent, this);		
	}

	@Override
	public void createTable(Composite parent) {
		table = new SupplierTable(parent);
		table.setContentProvider(new GenericContentProvider<Supplier>());
		table.setLabelProvider(new SupplierLabelProvider());
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("supplier.dialog.tile");
	}

	@Override
	public void execute(Supplier object) {
		
	}

}
