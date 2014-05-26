package com.rochatec.athena.invoice.input.editor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.client.helper.InvoiceInputHelper;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.invoice.item.Listener.InvoiceItemListener;
import com.rochatec.athena.invoice.item.event.InvoiceItemEvent;
import com.rochatec.athena.invoice.item.viewer.InvoiceInputItemViewer;
import com.rochatec.athena.invoice.viewer.InvoiceTotalViewer;
import com.rochatec.athena.manufacture.natureOfOperation.dialog.NatureOfOperationDialog;
import com.rochatec.athena.manufacture.natureOfOperation.provider.NatureOfOperationLabelProvider;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.util.StatusTradutor;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.viewer.TextViewer;

public class InvoiceInputEditor extends AbstractEditor implements InvoiceItemListener,Bindable{
	
	public static final String ID = "com.rochatec.athena.invoice.input.editor.InvoiceInputEditor";
	private InvoiceInputEditorInput editorInput;
	private InvoiceInputReceiverViewer receiverViewer;
	
	private TextViewer textViewer;
	private Text txtCfop;
	private Text txtInvoiceNumber;
	private Text txtSerialNumber;
	private Text txtStatus;
	private DateChooserCombo arrivalDate;
	private DateChooserCombo dateRegister;
	private InvoiceValueViewer valueViewer;
	private InvoiceInputItemViewer itemViewer;
	private InvoiceTotalViewer totalViewer;

	private List<InvoiceInputItem> items = new ArrayList<InvoiceInputItem>();
	
	@Override
	protected void createContents(Composite parent) {
		createReceiverBox(parent);
		createTributaryBox(parent);
		createInvoiceValueViewer(parent);
		createInvoiceItem(parent);
		createTotalViewer(parent);
		DataBindingFactory<InvoiceInput> factory = new DataBindingFactory<InvoiceInput>(editorInput.getInvoice(),this);
		factory.bind(getBinds());
	}
	
	private void createReceiverBox(Composite parent){
		receiverViewer = new InvoiceInputReceiverViewer(parent,editorInput.getInvoice().getReceiver(),this);
		receiverViewer.setEnabled(false);
	}
	
	private void createTotalViewer(Composite parent){
		totalViewer = new InvoiceTotalViewer(parent);
		totalViewer.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		BigDecimal[] values = {BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO};
		totalViewer.setInput(Arrays.asList(values));
	}
	
	private void createTributaryBox(Composite parent){
		Group group  = new Group(parent,SWT.NONE);
		group.setText(Messages.getMessage("invoice.tributary.group.title"));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		group.setLayout(LayoutFactory.getInstance().getGridLayout(7,false,5,10));
		
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.dateRegister.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.arrivalDate.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.invoiceNumber.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.serialNumber.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.natureOfOperation.field.label"));
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.cfop.field.label"));		
		new Label(group, SWT.NONE).setText(Messages.getMessage("invoice.status.field.label"));
		
		arrivalDate = new DateChooserCombo(group, SWT.BORDER);
		arrivalDate.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		dateRegister = new DateChooserCombo(group, SWT.BORDER);
		dateRegister.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtInvoiceNumber = new Text(group, SWT.BORDER|SWT.SHADOW_NONE);
		txtInvoiceNumber.setForeground(Colors.getColorBlue());		
		txtInvoiceNumber.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtSerialNumber = new Text(group, SWT.BORDER);
		txtSerialNumber.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		textViewer = new TextViewer(new Text(group, SWT.BORDER));
		textViewer.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));		
		textViewer.setContentProvider(new GenericContentProvider<NatureOfOperation>());
		textViewer.setLabelProvider(new NatureOfOperationLabelProvider());
		textViewer.addKeyListener(new NatureOfOperationSearchListener());
		
		txtCfop = new Text(group, SWT.BORDER);
		txtCfop.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtStatus = new Text(group, SWT.BORDER);
		txtStatus.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createInvoiceValueViewer(Composite parent){
		valueViewer = new InvoiceValueViewer(parent,editorInput.getInvoice().getValues(),this);
		valueViewer.setLayoutDate(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createInvoiceItem(Composite parent){
		itemViewer = new InvoiceInputItemViewer(parent,this);		
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	protected void fill() {		
		if (editorInput.getInvoice().getId() != null){
			InvoiceInput invoice = editorInput.getInvoice();
			txtStatus.setText(StatusTradutor.getLabel(invoice.getStatus()));
		}
		txtStatus.setEditable(false);
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
		InvoiceInputHelper helper = new InvoiceInputHelper(getItems());		
		helper.addItem(editorInput.getInvoice(),e.product,e.icms,e.costPrice,e.ipiBase,e.ipi,e.quantity);		
		setItems(helper.getItems());
		totalViewer.setInput(helper.getTotais(valueViewer.getValue()));
	}
	
	public void setItems(List<InvoiceInputItem> items){
		this.items = items;
		itemViewer.setInput(items);
		setDirty(true); 
	}
	
	public List<InvoiceInputItem> getItems(){
		return this.items;
	}

	@Override
	public void iItemUpdated(InvoiceItemEvent e) {
		InvoiceInputHelper helper = new InvoiceInputHelper(getItems());
		helper.updateItem(editorInput.getInvoice(),e.product,e.icms,e.costPrice,e.ipiBase,e.ipi,e.quantity);
		setItems(helper.getItems());
		totalViewer.setInput(helper.getTotais(valueViewer.getValue()));
	}

	@Override
	public void itemDeleted(InvoiceItemEvent itemEvent) {
		InvoiceInputHelper helper = new InvoiceInputHelper(getItems());
		helper.deleteItem((InvoiceInputItem)itemEvent.getSource());
		setItems(helper.getItems());
		totalViewer.setInput(helper.getTotais(valueViewer.getValue()));
	}
	
	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cfop",txtCfop);
		map.put("number",txtInvoiceNumber);
		map.put("serialNumber",txtSerialNumber);
		map.put("dateRegister",dateRegister);
		map.put("arrivalDate",arrivalDate);
		return map;
	}
	
	class NatureOfOperationSearchListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode == SWT.F9){
				NatureOfOperationDialog dialog = new NatureOfOperationDialog(e.display.getActiveShell());
				NatureOfOperation natureOfOperation = dialog.dialog();
				textViewer.setSelection(new SearchSelection<NatureOfOperation>(natureOfOperation));
			}			
		}
	}
}
