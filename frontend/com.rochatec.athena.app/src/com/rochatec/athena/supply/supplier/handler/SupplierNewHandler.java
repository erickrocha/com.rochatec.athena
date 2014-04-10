package com.rochatec.athena.supply.supplier.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.supply.supplier.editor.SupplierEditor;
import com.rochatec.athena.supply.supplier.editor.SupplierEditorInput;
import com.rochatec.athena.util.WorkbenchUtil;

public class SupplierNewHandler extends DefaultCrudHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{						
			SupplierEditorInput input = new SupplierEditorInput();			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, SupplierEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
