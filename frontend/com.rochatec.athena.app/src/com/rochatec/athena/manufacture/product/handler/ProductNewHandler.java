package com.rochatec.athena.manufacture.product.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.manufacture.product.editor.ProductEditor;
import com.rochatec.athena.manufacture.product.editor.ProductEditorInput;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class ProductNewHandler extends DefaultCrudHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{						
			ProductEditorInput input = new ProductEditorInput();			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, ProductEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
