package com.rochatec.athena.manufacture.product.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.product.box.ProductSearchBox;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class ProductSearchView extends AbstractView implements Executable<Product>{
	
	public static final String ID = "com.rochatec.athena.manufacture.product.view.ProductSearchView";
	private AbstractWrapperSearchBox<Product> searchBox;
	
	@Override
	public String getPartName() {	
		return Messages.getMessage("product.view.search.title");
	}
	
	@Override
	public void execute(List<Product> products) {
		SearchSelection<Product> selection = new SearchSelection<Product>(products);
		getSite().getSelectionProvider().setSelection(selection);
	}

	@Override
	protected void createContents(Composite parent) {
		searchBox = new ProductSearchBox(parent,this);
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						ProductTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	@Override
	public void execute(Product object) {
		
	}

}
