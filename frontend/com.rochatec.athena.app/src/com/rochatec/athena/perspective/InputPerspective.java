package com.rochatec.athena.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import com.rochatec.athena.welcome.view.ToolView;

public class InputPerspective implements IPerspectiveFactory{
	
	public static final String ID = "com.rochatec.metallurgical.perspective.InputPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		IFolderLayout toolsPart = layout.createFolder(IPerspectiveConstants.TOOLS_PART, IPageLayout.LEFT, 0.2f,editorArea);
		toolsPart.addPlaceholder(ToolView.ID + ":*");
		toolsPart.addView(ToolView.ID);
		
		IFolderLayout consolePart = layout.createFolder(IPerspectiveConstants.CONSOLE_PART, IPageLayout.BOTTOM, 0.8f,editorArea);
		consolePart.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW + ":*");
		consolePart.addView(IConsoleConstants.ID_CONSOLE_VIEW);	
		
	}

}
