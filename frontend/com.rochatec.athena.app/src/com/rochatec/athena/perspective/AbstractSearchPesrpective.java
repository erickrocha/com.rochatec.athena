package com.rochatec.athena.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import com.rochatec.athena.welcome.view.ToolView;

public abstract class AbstractSearchPesrpective implements IPerspectiveFactory {
	
	private static final String SEARCH_PART = "SEARCH_PART";
	private static final String TABLE_PART = "TABLE_PART";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		
		String editorArea = layout.getEditorArea();

		layout.setEditorAreaVisible(false);

		createToolsPart(layout, ToolView.ID, editorArea);
		
		createSearchPart(layout,SEARCH_PART,editorArea);
		
		createTablePart(layout,TABLE_PART,editorArea);		
		
		createConsolePart(layout, IConsoleConstants.ID_CONSOLE_VIEW, editorArea);
	}
	
	public void createToolsPart(IPageLayout layout,String viewId,String editorArea){
		IFolderLayout toolsPart = layout.createFolder(IPerspectiveConstants.TOOLS_PART, IPageLayout.LEFT, 0.2f,editorArea);
		toolsPart.addPlaceholder(viewId + ":*");
		toolsPart.addView(viewId);		
	}
	
	public void createConsolePart(IPageLayout layout,String viewId,String editorArea){
		IFolderLayout consolePart = layout.createFolder(IPerspectiveConstants.CONSOLE_PART, IPageLayout.BOTTOM, 0.2f,editorArea);
		consolePart.addPlaceholder(viewId + ":*");
		consolePart.addView(viewId);		
		layout.getViewLayout(viewId).setCloseable(false);
	}
	
	public abstract void createSearchPart(IPageLayout layout,String SEARCH_PART,String editorArea);
	
	public abstract void createTablePart(IPageLayout layout,String TABLE_PART,String editorArea);

}
