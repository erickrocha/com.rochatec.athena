package com.rochatec.athena.invoice.input.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.graphics.editor.AbstractEditor;

public class InvoiceInputEditor extends AbstractEditor{
	
	public static final String ID = "com.rochatec.athena.invoice.input.editor.InvoiceInputEditor";
	private InvoiceInputEditorInput editorInput;

	@Override
	protected void createContents(Composite parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (InvoiceInputEditorInput)input;		
		
	}

}
