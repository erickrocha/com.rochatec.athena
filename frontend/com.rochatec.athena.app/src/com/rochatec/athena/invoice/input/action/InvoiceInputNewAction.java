package com.rochatec.athena.invoice.input.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.client.service.ResourceClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.invoice.input.editor.InvoiceInputEditor;
import com.rochatec.athena.invoice.input.editor.InvoiceInputEditorInput;
import com.rochatec.athena.invoice.input.perspective.InvoiceInputPerspective;
import com.rochatec.athena.invoice.input.view.InvoiceInputSupplierHistoryView;
import com.rochatec.athena.model.Company;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.Enviroment;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.util.WorkbenchUtil;
import com.rochatec.athena.utils.ServiceFactory;

public class InvoiceInputNewAction extends Action{
	
	private InvoiceInputSupplierHistoryView view;
	private Supplier supplier;
	protected InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	protected ResourceClientService resourceClientService = ServiceFactory.getInstance().getResourceClientService();
	
	public InvoiceInputNewAction(InvoiceInputSupplierHistoryView view,Supplier supplier) {
		setId("com.rochatec.athena.invoice.input.action.InvoiceInputNewAction");
		setImageDescriptor(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_16));
		this.view = view;
		this.supplier = supplier;
	}
	
	@Override
	public void run() {
		Company company = resourceClientService.findCompanyBySocialSecurity(Enviroment.getString("company.current.socialSecurity"));
		InvoiceInput invoiceInput = InvoiceInput.factory(company,supplier);
		invoiceInput.setNumber(getInvoiceNumber(view.getSite().getShell()));
		view.add(supplier,invoiceInput);		
		try{						
			InvoiceInputEditorInput input = new InvoiceInputEditorInput(invoiceInput);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InvoiceInputPerspective.ID, InvoiceInputEditor.ID,input, view.getSite().getWorkbenchWindow());			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
	}
	
	private String getInvoiceNumber(Shell shell){
		InputDialog dialog = new InputDialog(view.getSite().getShell(),
				Messages.getMessage("invoice.dialog.invoice.number.title"),
				Messages.getMessage("invoice.dialog.invoice.number.message"), "",null);
		dialog.open();
		String value = dialog.getValue();
		return value;
	}
}
