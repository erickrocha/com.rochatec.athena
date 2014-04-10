package com.rochatec.athena.welcome.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import com.rochatec.athena.perspective.IPerspectiveConstants;
import com.rochatec.athena.welcome.view.ToolView;
import com.rochatec.athena.welcome.view.WelcomeView;

public class WelcomePerspective implements IPerspectiveFactory {
	
	public final static String ID = "com.rochatec.metallurgical.welcome.perspective.WelcomePerspective";
	
	private final static String VIEW_PART = "VIEW_PART";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		layout.setEditorAreaVisible(false);
		
		IFolderLayout toolsPart = layout.createFolder(IPerspectiveConstants.TOOLS_PART,IPageLayout.LEFT,0.2f,editorArea);
		toolsPart.addPlaceholder(ToolView.ID+":*");
		toolsPart.addView(ToolView.ID);
		
		IFolderLayout consolePart = layout.createFolder(IPerspectiveConstants.CONSOLE_PART,IPageLayout.BOTTOM,0.8f,editorArea);
		consolePart.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW+":*");
		consolePart.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		
		IFolderLayout viewPart = layout.createFolder(VIEW_PART,IPageLayout.TOP,0.2f,editorArea);				
		viewPart.addPlaceholder(WelcomeView.ID+":*");		
		viewPart.addView(WelcomeView.ID);
		
	}

}
