package com.rochatec.athena.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.services.ISourceProviderService;

import com.rochatec.athena.provider.CrudSourceProvider;

public class DefaultCrudHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	protected void fireCrudService(IWorkbenchWindow window,String instance){
		ISourceProviderService service = (ISourceProviderService)window.getService(ISourceProviderService.class); 
		CrudSourceProvider crudSourceProvider = (CrudSourceProvider) service.getSourceProvider(CrudSourceProvider.CRUD_INSTANCE); 
		crudSourceProvider.setInstance(instance); 
	}
}
