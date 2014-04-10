package com.rochatec.athena.manufacture.abstractproduct.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.abstractproduct.box.AbstractProductSearchBox;
import com.rochatec.athena.manufacture.abstractproduct.provider.AbstractProductLabelProvider;
import com.rochatec.athena.manufacture.abstractproduct.table.AbstractProductTable;
import com.rochatec.athena.model.AbstractProduct;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class AbstractProductDialog extends AbstractDialog<AbstractProduct> implements Executable<AbstractProduct>{

	public AbstractProductDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void execute(List<AbstractProduct> products) {
		table.setInput(products);		
	}

	@Override
	public void createSearchArea(Composite parent) {
		new AbstractProductSearchBox(parent,this);
	}

	@Override
	public void createTable(Composite parent) {
		table = new AbstractProductTable(parent);
		table.setContentProvider(new GenericContentProvider<AbstractProduct>());
		table.setLabelProvider(new AbstractProductLabelProvider());
		
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("abstractProduct.dialog.title");
	}

	@Override
	public void execute(AbstractProduct object) {
		
	}

}
