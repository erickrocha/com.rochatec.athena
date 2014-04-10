package com.rochatec.athena.supply.supplier.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.athena.supply.supplier.box.SupplierSearchBox;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class SupplierSearchView extends AbstractView implements Executable<Supplier>{
	
	public static final String ID = "com.rochatec.athena.supply.supplier.view.SupplierSearchView";
	protected SupplyClientService supplyClientService = ServiceFactory.getInstance().getSupplyClientService();
	private AbstractWrapperSearchBox<Supplier> searchBox;
	
	@Override
	protected void createContents(Composite parent) {
		searchBox = new SupplierSearchBox(parent,this);
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						SupplierTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	@Override
	public void execute(List<Supplier> suppliers) {
		SearchSelection<Supplier> selection = new SearchSelection<Supplier>(suppliers);
		getSite().getSelectionProvider().setSelection(selection);
		
	}

	@Override
	public void execute(Supplier object) {
		
	}

}
