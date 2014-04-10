package com.rochatec.athena.manufacture.product.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.manufacture.product.view.ProductSearchView;
import com.rochatec.athena.manufacture.product.view.ProductTableView;
import com.rochatec.athena.perspective.AbstractSearchPesrpective;

public class ProductSearchPerspective extends AbstractSearchPesrpective{
	
	public static final String ID ="com.rochatec.athena.manufacture.product.perspective.ProductSearchPerspective";
	
	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(ProductSearchView.ID+":*");
		searchPart.addView(ProductSearchView.ID);
		layout.getViewLayout(ProductSearchView.ID).setCloseable(false);
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(ProductTableView.ID+":*");
		tablePart.addView(ProductTableView.ID);
		layout.getViewLayout(ProductTableView.ID).setCloseable(false);
	}

}
