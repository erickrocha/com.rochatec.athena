package com.rochatec.athena.manufacture.productSet.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.manufacture.productSet.box.ProductSetSearchBox;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class ProductSetSearchView extends AbstractView implements Executable<ProductSet>{
	
	public static final String ID = "com.rochatec.metallurgical.manufacture.productSet.view.ProductSetSearchView";
	private AbstractWrapperSearchBox<ProductSet> searchBox;
	
	@Override
	protected void createContents(Composite parent) {
		searchBox = new ProductSetSearchBox(parent,this);
		
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						ProductSetTableView.ID));		
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();		
	}

	@Override
	public void execute(List<ProductSet> products) {
		SearchSelection<ProductSet> selection = new SearchSelection<ProductSet>(products);
		getSite().getSelectionProvider().setSelection(selection);
	}

	@Override
	public void execute(ProductSet object) {
		
	}

}
