package com.rochatec.pos.athena.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.services.ISourceProviderService;

import com.rochatec.pos.athena.provider.SellSourceProvider;

public abstract class DefaultSellHandler extends AbstractHandler{

	protected void fireSellService(IWorkbenchWindow window,String instance){
		ISourceProviderService service = (ISourceProviderService)window.getService(ISourceProviderService.class); 
		SellSourceProvider sellSourceProvider = (SellSourceProvider) service.getSourceProvider(SellSourceProvider.SELL_INSTANCE); 
		sellSourceProvider.setInstance(instance); 
	}
}
