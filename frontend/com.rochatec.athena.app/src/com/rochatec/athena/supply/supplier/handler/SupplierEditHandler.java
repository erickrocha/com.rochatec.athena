package com.rochatec.athena.supply.supplier.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.supply.supplier.editor.SupplierEditor;
import com.rochatec.athena.supply.supplier.editor.SupplierEditorInput;
import com.rochatec.athena.util.WorkbenchUtil;

public class SupplierEditHandler extends DefaultCrudHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		Supplier supplier = (Supplier) selection.getFirstElement();
		try{						
			SupplierEditorInput input = new SupplierEditorInput(supplier);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, SupplierEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
