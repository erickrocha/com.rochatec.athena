package com.rochatec.athena.security.user.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.perspective.AbstractSearchPesrpective;
import com.rochatec.athena.security.user.view.UserSearchView;
import com.rochatec.athena.security.user.view.UserTableView;

public class UserSearchPerspective extends AbstractSearchPesrpective{

	public static final String ID = "com.rochatec.athena.security.user.perspective.UserSearchPerspective";
	
	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(UserSearchView.ID+":*");
		searchPart.addView(UserSearchView.ID);
		layout.getViewLayout(UserSearchView.ID).setCloseable(false);
		
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,
			String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(UserTableView.ID+":*");
		tablePart.addView(UserTableView.ID);
		layout.getViewLayout(UserTableView.ID).setCloseable(false);
		
	}

}
