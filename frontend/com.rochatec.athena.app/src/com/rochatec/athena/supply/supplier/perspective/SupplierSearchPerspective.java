package com.rochatec.athena.supply.supplier.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.perspective.AbstractSearchPesrpective;
import com.rochatec.athena.supply.supplier.view.SupplierSearchView;
import com.rochatec.athena.supply.supplier.view.SupplierTableView;

public class SupplierSearchPerspective extends AbstractSearchPesrpective{
	
	public static final String ID = "com.rochatec.athena.supply.supplier.perspective.SupplierSearchPerspective";
	
	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {		
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(SupplierSearchView.ID+":*");
		searchPart.addView(SupplierSearchView.ID);
		layout.getViewLayout(SupplierSearchView.ID).setCloseable(false);
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,	String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(SupplierTableView.ID+":*");
		tablePart.addView(SupplierTableView.ID);
		layout.getViewLayout(SupplierTableView.ID).setCloseable(false);
	}

}
