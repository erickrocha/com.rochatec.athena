package com.rochatec.pos.athena.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rochatec.pos.athena.views.SellView;

public class SellPerspective implements IPerspectiveFactory{

	public static final String ID = "com.rochatec.pos.athena.perspective.SellPerspective";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(SellView.ID,false,IPageLayout.TOP,1.0f,editorArea);
		layout.getViewLayout(SellView.ID).setCloseable(false);
		layout.getViewLayout(SellView.ID).setMoveable(false);
		
	}

}
