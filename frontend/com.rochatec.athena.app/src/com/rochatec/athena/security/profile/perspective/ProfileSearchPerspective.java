package com.rochatec.athena.security.profile.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.perspective.AbstractSearchPesrpective;
import com.rochatec.athena.security.profile.view.ProfileSearchView;
import com.rochatec.athena.security.profile.view.ProfileTableView;

public class ProfileSearchPerspective extends AbstractSearchPesrpective{

	public static final String ID = "com.rochatec.athena.security.profile.perspective.ProfileSearchPerspective";
	
	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(ProfileSearchView.ID+":*");
		searchPart.addView(ProfileSearchView.ID);
		layout.getViewLayout(ProfileSearchView.ID).setCloseable(false);
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(ProfileTableView.ID+":*");
		tablePart.addView(ProfileTableView.ID);
		layout.getViewLayout(ProfileTableView.ID).setCloseable(false);
	}

}
