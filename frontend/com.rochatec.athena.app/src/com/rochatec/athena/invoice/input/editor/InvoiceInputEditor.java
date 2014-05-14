package com.rochatec.athena.invoice.input.editor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.helper.InvoiceInputHelper;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.invoice.item.Listener.InvoiceItemListener;
import com.rochatec.athena.invoice.item.event.InvoiceItemEvent;
import com.rochatec.athena.invoice.item.viewer.InvoiceInputItemViewer;
import com.rochatec.athena.invoice.status.provider.InvoiceStatusLabelProvider;
import com.rochatec.athena.manufacture.natureOfOperation.provider.NatureOfOperationLabelProvider;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.InvoiceStatus;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.viewer.TextViewer;

public class InvoiceInputEditor extends AbstractEditor implements InvoiceItemListener{
	
	public static final String ID = "com.rochatec.athena.invoice.input.editor.InvoiceInputEditor";
	private InvoiceInputEditorInput editorInput;
	private CLabel lblCompanyName;
	private CLabel lblCompanySocialSecurity;
	private CLabel lblCompanyRegisterNumber;
	private CLabel lblCompanyInscMunicipal;
	
	private TextViewer textViewer;
	private Text txtCfop;
	private CLabel lblInvoiceNumber;
	private Text txtSerialNumber;
	private TextViewer statusViewer; 
	private InvoiceValueViewer valueViewer;
	private InvoiceInputItemViewer itemViewer;

	private List<InvoiceInputItem> items = new ArrayList<InvoiceInputItem>();
	
	@Override
	protected void createContents(Composite parent) {
		createReceiverBox(parent);
		createTributaryBox(parent);
		createInvoiceValueViewer(parent);
		createInvoiceItem(parent);
	}
	
	private void createReceiverBox(Composite parent){
		Group group  = new Group(parent,SWT.NONE);
		group.setText(Messages.getMessage("invoice.receiver.field.label"));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		group.setLayout(LayoutFactory.getInstance().getGridLayout(4));		
		
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.receiver.companyName.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.receiver.socialSecurity.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.receiver.registerNumber.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.receiver.cityRegister.field.label"));
		
		lblCompanyName = new CLabel(group, SWT.BORDER);
		lblCompanyName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));		
		lblCompanySocialSecurity = new CLabel(group, SWT.BORDER);
		lblCompanySocialSecurity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		lblCompanyRegisterNumber = new CLabel(group, SWT.BORDER);
		lblCompanyRegisterNumber.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		lblCompanyInscMunicipal = new CLabel(group, SWT.BORDER);
		lblCompanyInscMunicipal.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
	}
	
	private void createTributaryBox(Composite parent){
		Group group  = new Group(parent,SWT.NONE);
		group.setText(Messages.getMessage("invoice.tributary.group.title"));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		group.setLayout(LayoutFactory.getInstance().getGridLayout(5));
		
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.invoiceNumber.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.serialNumber.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.natureOfOperation.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.cfop.field.label"));		
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.status.field.label"));
		
		lblInvoiceNumber = new CLabel(group, SWT.BORDER|SWT.SHADOW_NONE);
		lblInvoiceNumber.setForeground(Colors.getColorBlue());
		lblInvoiceNumber.setAlignment(SWT.CENTER);
		lblInvoiceNumber.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtSerialNumber = new Text(group, SWT.BORDER);
		txtSerialNumber.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		textViewer = new TextViewer(new Text(group, SWT.BORDER));
		textViewer.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));		
		textViewer.setContentProvider(new GenericContentProvider<NatureOfOperation>());
		textViewer.setLabelProvider(new NatureOfOperationLabelProvider());
		
		txtCfop = new Text(group, SWT.BORDER);
		txtCfop.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		statusViewer = new TextViewer(new Text(group, SWT.BORDER));
		statusViewer.setContentProvider(new GenericContentProvider<InvoiceStatus>());
		statusViewer.setLabelProvider(new InvoiceStatusLabelProvider());
	}
	
	private void createInvoiceValueViewer(Composite parent){
		valueViewer = new InvoiceValueViewer(parent);
		valueViewer.setLayoutDate(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createInvoiceItem(Composite parent){
		itemViewer = new InvoiceInputItemViewer(parent);
		itemViewer.addInvoiceItemListener(this);
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	protected void fill() {		
		try {
			InvoiceInput invoice = editorInput.getInvoice();
			lblCompanyName.setText(invoice.getReceiver().getCompanyName());
			lblCompanySocialSecurity.setText(Formatter.getSocialSecurity().mask(invoice.getReceiver().getSocialSecurity()));
			lblCompanyRegisterNumber.setText(invoice.getReceiver().getRegisterNumber());
			lblCompanyInscMunicipal.setText(invoice.getReceiver().getCityRegister());
			statusViewer.setInput(invoice.getStatus());
			lblInvoiceNumber.setText(invoice.getNumber().toString());
			valueViewer.setValue(invoice.getValues());
			if (invoice.getId() != null){
				
			}
		} catch (BadFormatException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (InvoiceInputEditorInput)input;		
	}

	@Override
	public void itemAdded(InvoiceItemEvent e) {
		InvoiceInputHelper helper = new InvoiceInputHelper(items);		
		try{
			BigDecimal costPrice = Formatter.getDecimal().parse(e.costPrice.getText());
			BigDecimal ipiBase = Formatter.getDecimal().parse(e.ipiBase.getText());
			BigDecimal ipiValue = Formatter.getDecimal().parse(e.ipi.getText());
			BigDecimal quantity = Formatter.getWeight().parse(e.quantity.getText());
			InvoiceInputItem item = helper.newItem(editorInput.getInvoice(),e.product,e.icms,costPrice,ipiBase,ipiValue,quantity);		
			items.add(item);
			itemViewer.setInput(items);
		}catch (BadFormatException ex){
			ControlDecoration deco = new ControlDecoration(e.costPrice,SWT.TOP|SWT.LEFT);			
			deco.setImage(Activator.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR).createImage());			
		}				
	}

	@Override
	public void iItemUpdated(InvoiceItemEvent itemEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemDeleted(InvoiceItemEvent itemEvent) {
		// TODO Auto-generated method stub
		
	}	

}
