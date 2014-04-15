package com.rochatec.athena.humanresources.job.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.humanresources.job.view.JobSearchView;
import com.rochatec.athena.humanresources.job.view.JobTableView;
import com.rochatec.athena.perspective.AbstractSearchPesrpective;

public class JobSearchPerspective extends AbstractSearchPesrpective {

	public static final String ID = "com.rochatec.athena.humanresources.job.perspective.JobSearchPerspective";

	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(JobSearchView.ID+":*");
		searchPart.addView(JobSearchView.ID);
		layout.getViewLayout(JobSearchView.ID).setCloseable(false);
		
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(JobTableView.ID+":*");
		tablePart.addView(JobTableView.ID);
		layout.getViewLayout(JobTableView.ID).setCloseable(false);
		
	}

}
