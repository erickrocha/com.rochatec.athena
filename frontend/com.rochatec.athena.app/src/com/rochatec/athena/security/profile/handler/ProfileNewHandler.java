package com.rochatec.athena.security.profile.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.security.profile.editor.ProfileEditor;
import com.rochatec.athena.security.profile.editor.ProfileEditorInput;
import com.rochatec.athena.util.WorkbenchUtil;

public class ProfileNewHandler extends DefaultCrudHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{						
			ProfileEditorInput input = new ProfileEditorInput();			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, ProfileEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}
}
