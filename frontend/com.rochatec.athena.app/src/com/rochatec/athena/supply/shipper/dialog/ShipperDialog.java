package com.rochatec.athena.supply.shipper.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.supply.shipper.box.ShipperSearchBox;
import com.rochatec.athena.supply.shipper.provider.ShipperLabelProvider;
import com.rochatec.athena.supply.shipper.table.ShipperTable;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class ShipperDialog extends AbstractDialog<Shipper> implements Executable<Shipper>{

	public ShipperDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void execute(List<Shipper> objects) {
		table.setInput(objects);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new ShipperSearchBox(parent,this);
	}

	@Override
	public void createTable(Composite parent) {
		table = new ShipperTable(parent);
		table.setContentProvider(new GenericContentProvider<Shipper>());
		table.setLabelProvider(new ShipperLabelProvider());
	}

	@Override
	public String getTitle() {		
		return Messages.getMessage("supplier.dialog.tile");
	}

	@Override
	public void execute(Shipper object) {
		
	}

}
