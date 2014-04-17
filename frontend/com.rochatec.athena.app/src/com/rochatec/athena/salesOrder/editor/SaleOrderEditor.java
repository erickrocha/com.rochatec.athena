package com.rochatec.athena.salesOrder.editor;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.framework.bind.Bindable;
import com.rochatec.graphics.editor.AbstractEditor;

public class SaleOrderEditor extends AbstractEditor implements Bindable{
	
	
	private SaleOrderEditorInput editorInput;
	
	@Override
	protected void createContents(Composite parent) {
		
		
	}	

	@Override
	protected void addListeners() {
		
		
	}

	@Override
	protected void fill() {
		
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (SaleOrderEditorInput)input;		
	}
	
	@Override
	public Map<String, Object> getBinds() {
		
		return null;
	}

}
