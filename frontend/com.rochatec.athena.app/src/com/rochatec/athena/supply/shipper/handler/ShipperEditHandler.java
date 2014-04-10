package com.rochatec.athena.supply.shipper.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.supply.shipper.editor.ShipperEditor;
import com.rochatec.athena.supply.shipper.editor.ShipperEditorInput;
import com.rochatec.athena.util.WorkbenchUtil;

public class ShipperEditHandler extends DefaultCrudHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		Shipper shipper = (Shipper) selection.getFirstElement();
		try{						
			ShipperEditorInput input = new ShipperEditorInput(shipper);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, ShipperEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
