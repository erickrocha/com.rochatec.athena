package com.rochatec.athena.crm.customer.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.crm.customer.view.CustomerSearchView;
import com.rochatec.athena.crm.customer.view.CustomerTableView;
import com.rochatec.athena.perspective.AbstractSearchPesrpective;

public class CustomerSearchPerspective extends AbstractSearchPesrpective{
	
	public static final String ID = "com.rochatec.metallurgical.crm.customer.perspective.CustomerSearchPerspective";

	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,
			String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(CustomerSearchView.ID+":*");
		searchPart.addView(CustomerSearchView.ID);
		layout.getViewLayout(CustomerSearchView.ID).setCloseable(false);
		
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,
			String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(CustomerTableView.ID+":*");
		tablePart.addView(CustomerTableView.ID);
		layout.getViewLayout(CustomerTableView.ID).setCloseable(false);
		
	}

}
