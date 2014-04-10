package com.rochatec.athena.humanresources.employee.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

import com.rochatec.athena.humanresources.employee.view.EmployeeSearchView;
import com.rochatec.athena.humanresources.employee.view.EmployeeTableView;
import com.rochatec.athena.perspective.AbstractSearchPesrpective;

public class EmployeeSearchPerspective extends AbstractSearchPesrpective{
	
	public static final String ID = "com.rochatec.metallurgical.humanresources.employee.perspective.EmployeeSearchPerspective";

	@Override
	public void createSearchPart(IPageLayout layout, String SEARCH_PART,String editorArea) {
		IFolderLayout searchPart = layout.createFolder(SEARCH_PART,IPageLayout.TOP, 0.2f,editorArea );
		searchPart.addPlaceholder(EmployeeSearchView.ID+":*");
		searchPart.addView(EmployeeSearchView.ID);
		layout.getViewLayout(EmployeeSearchView.ID).setCloseable(false);
	}

	@Override
	public void createTablePart(IPageLayout layout, String TABLE_PART,String editorArea) {
		IFolderLayout tablePart = layout.createFolder(TABLE_PART,IPageLayout.TOP, 0.8f,editorArea );
		tablePart.addPlaceholder(EmployeeTableView.ID+":*");
		tablePart.addView(EmployeeTableView.ID);
		layout.getViewLayout(EmployeeTableView.ID).setCloseable(false);
	}	

}
