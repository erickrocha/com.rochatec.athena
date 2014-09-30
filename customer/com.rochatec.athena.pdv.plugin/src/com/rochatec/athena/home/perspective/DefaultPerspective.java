package com.rochatec.athena.home.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rochatec.athena.home.views.CupomView;

public class DefaultPerspective implements IPerspectiveFactory{
	
	public static final String id = "com.rochatec.athena.home.perspective.DefaultPerspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		layout.addStandaloneView(CupomView.ID,false,IPageLayout.LEFT,1.0f,editorArea);
		
	}

	
}
