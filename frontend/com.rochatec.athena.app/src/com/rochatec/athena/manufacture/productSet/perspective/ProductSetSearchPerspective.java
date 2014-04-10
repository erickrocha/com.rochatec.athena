package com.rochatec.athena.manufacture.productSet.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.manufacture.productSet.view.ProductSetSearchView;
import com.rochatec.athena.manufacture.productSet.view.ProductSetTableView;
import com.rochatec.athena.perspective.AbstractSearchPesrpective;

public class ProductSetSearchPerspective extends AbstractSearchPesrpective{
	
	public static String ID = "com.rochatec.metallurgical.manufacture.productSet.perspective.ProductSetSearchPerspective";

	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(ProductSetSearchView.ID+":*");
		searchPart.addView(ProductSetSearchView.ID);
		layout.getViewLayout(ProductSetSearchView.ID).setCloseable(false);
		
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(ProductSetTableView.ID+":*");
		tablePart.addView(ProductSetTableView.ID);
		layout.getViewLayout(ProductSetTableView.ID).setCloseable(false);
	}

}
