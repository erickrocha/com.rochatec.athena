package com.rochatec.athena.manufacture.category.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.manufacture.category.view.CategorySearchView;
import com.rochatec.athena.manufacture.category.view.CategoryTableView;
import com.rochatec.athena.perspective.AbstractSearchPesrpective;

public class CategorySearchPerspective extends AbstractSearchPesrpective{
	
	public static final String ID = "com.rochatec.metallurgical.manufacture.category.perspective.CategorySearchPerspective";

	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(CategorySearchView.ID+":*");
		searchPart.addView(CategorySearchView.ID);
		layout.getViewLayout(CategorySearchView.ID).setCloseable(false);
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(CategoryTableView.ID+":*");
		tablePart.addView(CategoryTableView.ID);
		layout.getViewLayout(CategoryTableView.ID).setCloseable(false);
	}

}
