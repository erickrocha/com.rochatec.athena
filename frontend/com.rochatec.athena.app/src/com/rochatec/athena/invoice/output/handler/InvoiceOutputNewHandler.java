package com.rochatec.athena.invoice.output.handler;

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
import com.rochatec.athena.invoice.output.editor.InvoiceOutputEditor;
import com.rochatec.athena.invoice.output.editor.InvoiceOutputEditorInput;
import com.rochatec.athena.invoice.output.perspective.InvoiceOutputPerspective;
import com.rochatec.athena.invoice.output.view.InvoiceOutputCustomerView;
import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.util.Enviroment;
import com.rochatec.athena.util.WorkbenchUtil;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.graphics.selection.DefaultSelectHandler;

public class InvoiceOutputNewHandler extends DefaultSelectHandler{
	
	protected InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	protected ResourceClientService resourceClientService = ServiceFactory.getInstance().getResourceClientService();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		Company company = resourceClientService.findCompanyBySocialSecurity(Enviroment.getString("company.current.socialSecurity"));
		Customer customer = getCustomer(event);
		if (customer != null){
			InvoiceOutput invoiceOutput = InvoiceOutput.factory(company,customer);
			invoiceOutput.setNumber(getInvoiceNumber(window.getShell()));
			try{						
				InvoiceOutputEditorInput input = new InvoiceOutputEditorInput(invoiceOutput);			
				WorkbenchUtil.handlerOpenEditorInPerspective(InvoiceOutputPerspective.ID, InvoiceOutputEditor.ID,input,HandlerUtil.getActiveWorkbenchWindow(event));			
			}catch (PartInitException ex){
				Activator.getDefault().addConsoleError(ex);
			}
			return invoiceOutput;
		}else{
			MessageDialog.openError(window.getShell(),Messages.getMessage("app.error"),Messages.getMessage("invoice.dialog.invoice.supplir.notfound"));
			return null;
		}		
	}
	
	private Customer getCustomer(ExecutionEvent event){
		IViewPart viewPart = (IViewPart) HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(InvoiceOutputCustomerView.ID);
		IStructuredSelection selection = (IStructuredSelection)viewPart.getSite().getSelectionProvider().getSelection();
		if (selection != null)
			return (Customer)selection.getFirstElement();
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
