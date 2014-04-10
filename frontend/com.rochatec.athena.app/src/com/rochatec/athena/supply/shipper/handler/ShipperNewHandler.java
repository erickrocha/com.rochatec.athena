package com.rochatec.athena.supply.shipper.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.supply.shipper.editor.ShipperEditor;
import com.rochatec.athena.supply.shipper.editor.ShipperEditorInput;
import com.rochatec.athena.util.WorkbenchUtil;

public class ShipperNewHandler extends DefaultCrudHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{						
			ShipperEditorInput input = new ShipperEditorInput();			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, ShipperEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
