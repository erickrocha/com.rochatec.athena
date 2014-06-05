package com.rochatec.athena.invoice.output.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.invoice.input.editor.InvoiceInputEditor;
import com.rochatec.athena.invoice.input.editor.InvoiceInputEditorInput;
import com.rochatec.athena.invoice.input.perspective.InvoiceInputPerspective;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.util.WorkbenchUtil;
import com.rochatec.framework.model.HierarchyObject;

public class InvoiceOutputEditHandler extends DefaultCrudHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		HierarchyObject hierarchyObject = (HierarchyObject)  ((ITreeSelection) HandlerUtil.getCurrentSelection(event)).getFirstElement();
		InvoiceInput invoiceInput = (InvoiceInput)hierarchyObject.getWrapper();
		try{						
			InvoiceInputEditorInput input = new InvoiceInputEditorInput(invoiceInput);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InvoiceInputPerspective.ID, InvoiceInputEditor.ID,input,HandlerUtil.getActiveWorkbenchWindow(event));			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return invoiceInput;
	}
}
