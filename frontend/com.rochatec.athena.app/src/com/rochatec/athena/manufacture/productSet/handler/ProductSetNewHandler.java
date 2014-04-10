package com.rochatec.athena.manufacture.productSet.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.manufacture.productSet.editor.ProductSetEditor;
import com.rochatec.athena.manufacture.productSet.editor.ProductSetEditorInput;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class ProductSetNewHandler extends DefaultCrudHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{						
			ProductSetEditorInput input = new ProductSetEditorInput();			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, ProductSetEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
