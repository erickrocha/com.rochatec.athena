package com.rochatec.athena.invoice.input.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.client.service.ResourceClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.invoice.input.editor.InvoiceInputEditor;
import com.rochatec.athena.invoice.input.editor.InvoiceInputEditorInput;
import com.rochatec.athena.invoice.input.perspective.InvoiceInputPerspective;
import com.rochatec.athena.invoice.input.view.InvoiceInputSupplierView;
import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.Enviroment;
import com.rochatec.athena.util.WorkbenchUtil;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.graphics.selection.DefaultSelectHandler;

public class InvoiceInputNewHandler extends DefaultSelectHandler{
	
	protected InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	protected ResourceClientService resourceClientService = ServiceFactory.getInstance().getResourceClientService();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		Company company = resourceClientService.findCompanyBySocialSecurity(Enviroment.getString("company.current.socialSecurity"));
		Supplier supplier = getSupplier(event);
		if (supplier != null){
			InvoiceInput invoiceInput = InvoiceInput.factory(company,supplier);
			invoiceInput.setNumber(getInvoiceNumber(window.getShell()));
			try{						
				InvoiceInputEditorInput input = new InvoiceInputEditorInput(invoiceInput);			
				WorkbenchUtil.handlerOpenEditorInPerspective(InvoiceInputPerspective.ID, InvoiceInputEditor.ID,input,HandlerUtil.getActiveWorkbenchWindow(event));			
			}catch (PartInitException ex){
				Activator.getDefault().addConsoleError(ex);
			}
			return invoiceInput;
		}else{
			MessageDialog.openError(window.getShell(),Messages.getMessage("app.error"),Messages.getMessage("invoice.dialog.invoice.supplir.notfound"));
			return null;
		}		
	}
	
	private Supplier getSupplier(ExecutionEvent event){
		IViewPart viewPart = (IViewPart) HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(InvoiceInputSupplierView.ID);
		IStructuredSelection selection = (IStructuredSelection)viewPart.getSite().getSelectionProvider().getSelection();
		if (selection != null)
			return (Supplier)selection.getFirstElement();
		return null;
	}
	
	private String getInvoiceNumber(Shell shell){
		InputDialog dialog = new InputDialog(shell,
				Messages.getMessage("invoice.dialog.invoice.number.title"),
				Messages.getMessage("invoice.dialog.invoice.number.message"), "",null);
		dialog.open();
		String value = dialog.getValue();
		return value;
	}
		
}
