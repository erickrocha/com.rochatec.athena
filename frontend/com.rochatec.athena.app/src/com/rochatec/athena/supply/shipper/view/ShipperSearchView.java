package com.rochatec.athena.supply.shipper.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.athena.supply.shipper.box.ShipperSearchBox;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class ShipperSearchView extends AbstractView implements Executable<Shipper>{

	public static final String ID = "com.rochatec.metallurgical.supply.shipper.view.ShipperSearchView";
	private AbstractWrapperSearchBox<Shipper> searchBox;
	
	@Override
	protected void createContents(Composite parent) {
		searchBox = new ShipperSearchBox(parent, this);
	}
	
	@Override
	public String getPartName() {		
		return Messages.getMessage("shipper.view.search.title");
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						ShipperTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	@Override
	public void execute(List<Shipper> objects) {
		SearchSelection<Shipper> selection = new SearchSelection<Shipper>(objects);
		getSite().getSelectionProvider().setSelection(selection);		
	}

	@Override
	public void execute(Shipper object) {
		
	}

}
