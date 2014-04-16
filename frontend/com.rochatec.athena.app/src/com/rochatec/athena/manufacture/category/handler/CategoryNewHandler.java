package com.rochatec.athena.manufacture.category.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.manufacture.category.editor.CategoryEditor;
import com.rochatec.athena.manufacture.category.editor.CategoryEditorInput;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class CategoryNewHandler extends DefaultCrudHandler {
	
	public static final String ID = "com.rochatec.athena.manufacture.category.handler.CategoryNewHandler";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{
			CategoryEditorInput input = new CategoryEditorInput();
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID,CategoryEditor.ID,input, event);
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
