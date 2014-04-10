package com.rochatec.athena.manufacture.productSet.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.productSet.box.ProductSetSearchBox;
import com.rochatec.athena.manufacture.productSet.provider.ProductSetLabelProvider;
import com.rochatec.athena.manufacture.productSet.table.ProductSetTable;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class ProductSetDialog extends AbstractDialog<ProductSet> implements Executable<ProductSet>{

	public ProductSetDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void execute(List<ProductSet> objects) {
		table.setInput(objects);		
	}

	@Override
	public void createSearchArea(Composite parent) {
		new ProductSetSearchBox(parent,this);
	}

	@Override
	public void createTable(Composite parent) {
		table = new ProductSetTable(parent);
		table.setContentProvider(new GenericContentProvider<ProductSet>());
		table.setLabelProvider(new ProductSetLabelProvider());
	}

	@Override
	public String getTitle() {		
		return Messages.getMessage("productset.dialog.title");
	}

	@Override
	public void execute(ProductSet object) {
		
	}

}
