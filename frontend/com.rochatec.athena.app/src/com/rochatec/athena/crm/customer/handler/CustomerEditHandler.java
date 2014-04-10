package com.rochatec.athena.crm.customer.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.crm.customer.editor.CustomerEditor;
import com.rochatec.athena.crm.customer.editor.CustomerEditorInput;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class CustomerEditHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		Customer customer = (Customer) selection.getFirstElement();
		try{						
			CustomerEditorInput input = new CustomerEditorInput(customer);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, CustomerEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
	
	
}
