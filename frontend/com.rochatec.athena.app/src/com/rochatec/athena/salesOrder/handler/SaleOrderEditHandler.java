package com.rochatec.athena.salesOrder.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.salesOrder.editor.SaleOrderEditor;
import com.rochatec.athena.salesOrder.editor.SaleOrderEditorInput;
import com.rochatec.athena.salesOrder.perspective.SaleOrderPerspective;
import com.rochatec.athena.util.WorkbenchUtil;
import com.rochatec.framework.model.TreeObject;

public class SaleOrderEditHandler extends DefaultCrudHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		SaleOrder saleOrder = (SaleOrder) ((TreeObject)selection.getFirstElement()).getObject();
		try{						
			SaleOrderEditorInput input = new SaleOrderEditorInput(saleOrder);			
			WorkbenchUtil.handlerOpenEditorInPerspective(SaleOrderPerspective.ID, SaleOrderEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
