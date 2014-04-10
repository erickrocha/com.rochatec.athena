package com.rochatec.athena.supply.shipper.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.perspective.AbstractSearchPesrpective;
import com.rochatec.athena.supply.shipper.view.ShipperSearchView;
import com.rochatec.athena.supply.shipper.view.ShipperTableView;

public class ShipperSearchPerspective extends AbstractSearchPesrpective{

	public static final String ID = "com.rochatec.metallurgical.supply.shipper.perspective.ShipperSearchPerspective";
	
	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,
			String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(ShipperSearchView.ID+":*");
		searchPart.addView(ShipperSearchView.ID);
		layout.getViewLayout(ShipperSearchView.ID).setCloseable(false);
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,
			String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(ShipperTableView.ID+":*");
		tablePart.addView(ShipperTableView.ID);
		layout.getViewLayout(ShipperTableView.ID).setCloseable(false);
	}

}
