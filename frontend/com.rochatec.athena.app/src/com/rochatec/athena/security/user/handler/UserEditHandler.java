package com.rochatec.athena.security.user.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.model.User;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.security.user.editor.UserEditor;
import com.rochatec.athena.security.user.editor.UserEditorInput;
import com.rochatec.athena.util.WorkbenchUtil;

public class UserEditHandler extends DefaultCrudHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		User user = (User) selection.getFirstElement();
		try{						
			UserEditorInput input = new UserEditorInput(user);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, UserEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
