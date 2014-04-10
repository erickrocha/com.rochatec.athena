package com.rochatec.athena.manufacture.category.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.manufacture.category.editor.CategoryEditor;
import com.rochatec.athena.manufacture.category.editor.CategoryEditorInput;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class CategoryEditHandler extends DefaultCrudHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		Category category = (Category) selection.getFirstElement();
		try{						
			CategoryEditorInput input = new CategoryEditorInput(category);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, CategoryEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
