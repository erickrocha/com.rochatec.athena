package com.rochatec.athena.manufacture.product.dialog;

import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.product.box.ProductSearchBox;
import com.rochatec.athena.manufacture.product.provider.ProductLabelProvider;
import com.rochatec.athena.manufacture.product.table.ProductTable;
import com.rochatec.athena.model.Product;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class ProductDialog extends AbstractDialog<Product> implements Executable<Product>{

	public ProductDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void execute(List<Product> products) {
		table.setInput(products);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(1024,768);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new ProductSearchBox(parent,this);
	}

	@Override
	public void createTable(Composite parent) {
		table = new ProductTable(parent);
		table.setContentProvider(new GenericContentProvider<Product>());
		table.setLabelProvider(new ProductLabelProvider());
	}

	@Override
	public String getTitle() {		
		return Messages.getMessage("product.dialog.title");
	}

	@Override
	public void execute(Product object) {
		
	}

}
