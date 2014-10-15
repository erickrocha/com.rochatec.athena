package com.rochatec.pos.athena.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rochatec.pos.athena.views.SumView;

public class SumPerspective implements IPerspectiveFactory{
	
	public static final String ID = "com.rochatec.pos.athena.perspective.SumPerspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(SumView.ID,false,IPageLayout.TOP,1.0f,editorArea);
		layout.getViewLayout(SumView.ID).setCloseable(false);
		layout.getViewLayout(SumView.ID).setMoveable(false);
	}

}
