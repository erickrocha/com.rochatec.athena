package com.rochatec.pos.athena.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rochatec.pos.athena.views.WelcomeView;

public class WelcomePerspective implements IPerspectiveFactory{
	
	public static final String ID = "com.rochatec.pos.athena.perspective.WelcomePerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(WelcomeView.ID,false,IPageLayout.TOP,1.0f,editorArea);
		layout.getViewLayout(WelcomeView.ID).setCloseable(false);
		layout.getViewLayout(WelcomeView.ID).setMoveable(false);
	}

}
