package com.rochatec.graphics.editor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.EditorPart;

import com.rochatec.framework.bind.Editable;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.WidgetUtils;

public abstract class AbstractEditor extends EditorPart implements Editable{
	
	private boolean _dirty = false;
	protected ScrolledForm form = null;

	@Override
	public void doSaveAs() {
		
	}
	
	public void setDirty(boolean dirty){
		this._dirty = dirty;
		firePropertyChange(PROP_DIRTY);	
	}	

	@Override
	public boolean isDirty() {
		return _dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText(getPartName());
		
		toolkit.decorateFormHeading(form.getForm());
		makeActions(form.getToolBarManager());		
		form.getToolBarManager().update(true);
		form.getBody().setLayout(LayoutFactory.getInstance().getGridLayout(1,0));
		form.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createContents(form.getBody());
		addListeners();
		fill();
		WidgetUtils.backgroundEquals(form.getBody());
	}
	
	public void makeActions(IToolBarManager toolBarManager){
		toolBarManager.add(ActionFactory.SAVE.create(getSite().getWorkbenchWindow()));	
	};
	
	protected abstract void createContents(Composite parent);
	
	protected abstract void addListeners();
	
	protected abstract void fill();

	@Override
	public void setFocus() {
		
	}

}
