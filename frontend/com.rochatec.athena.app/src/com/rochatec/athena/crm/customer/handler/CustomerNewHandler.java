package com.rochatec.athena.crm.customer.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.crm.customer.editor.CustomerEditor;
import com.rochatec.athena.crm.customer.editor.CustomerEditorInput;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class CustomerNewHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{
			CustomerEditorInput input = new CustomerEditorInput();
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID,CustomerEditor.ID,input, event);
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
