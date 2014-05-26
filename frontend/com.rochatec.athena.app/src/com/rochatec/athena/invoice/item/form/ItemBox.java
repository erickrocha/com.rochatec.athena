package com.rochatec.athena.invoice.item.form;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.invoice.item.Listener.InvoiceItemListener;
import com.rochatec.athena.invoice.item.event.InvoiceItemEvent;
import com.rochatec.athena.manufacture.icms.provider.IcmsLabelProvider;
import com.rochatec.athena.manufacture.product.dialog.ProductDialog;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.UnitMeasure;
import com.rochatec.athena.util.ATHENA;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.util.MathTools;
import com.rochatec.athena.util.UnitMeasureTradutor;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.gui.CompositeText;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.IKeyPadConstants;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.WidgetUtils;

public class ItemBox {
	
	private CompositeText txtIdProduct;
	private CompositeText txtProductName;
	private CompositeText txtUnitMeasure;
	private CompositeText txtIpi;
	private CompositeText txtLastCostPrice;
	private CompositeText txtTotalIcms;
	private ComboViewer viewerICms;
	private Text txtIpiBase;
		
	private Text txtCostPrice;
	private CompositeText txtTotalItem;
	private Text txtQuantity;
	private ImageHyperlink btAdd;
	private Product product;
	private ListenerList listeners;
	private InvoiceInputItem item;
	
	private Shell shell;
	private int style;
	
	private ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	
	public ItemBox(Composite parent,InvoiceInputItem item,int style) {
		this.listeners = new ListenerList();
		this.item = item;
		this.shell = parent.getShell();
		this.style = style;
		createContents(parent);
	}
	
	public InvoiceInputItem getItem(){
		return item;
	}
	
	public void setItem(InvoiceInputItem item){
		this.item =item;
	}
	
	private void createContents(Composite parent){
		Group composite = new Group(parent,SWT.BORDER);
		composite.setText(Messages.getMessage("itemViewer.title.label"));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		createFields(composite);		
		createInfoFields(composite);
		postCreate();	
	}
	
	private void postCreate(){
		List<Icms> listIcms = manufactureClientService.findAllIcms();
		viewerICms.setInput(listIcms);
		try{
			if (style == ATHENA.EDIT){
				product = item.getProduct();
				txtIdProduct.setText(item.getProduct() != null ? item.getProductId().toString() : "");
				txtProductName.setText(item.getProduct() != null ? item.getLabel() : "");
				txtQuantity.setText(Formatter.getWeight().mask(item.getQuantity()));
				txtCostPrice.setText(Formatter.getDecimal().mask(item.getCostPrice()));
				viewerICms.setSelection(item.getIcms() != null ? new SearchSelection<Icms>(item.getIcms()) : new SearchSelection<Icms>(listIcms.get(0)));
				txtIpiBase.setText(Formatter.getDecimal().mask(item.getIpiBase()));
				txtUnitMeasure.setText(UnitMeasureTradutor.getLabel(item.getProduct() != null ? item.getProduct().getUnitMeasure() : UnitMeasure.UN));
				txtIpi.setText(Formatter.getDecimal().mask(item.getIpiValue()));
				txtLastCostPrice.setText(Formatter.getDecimal().mask(item.getProduct() != null ? item.getProduct().getLastCostprice() : BigDecimal.ZERO));
				txtTotalIcms.setText(Formatter.getDecimal().mask(item.getTotalIcms()));
				txtTotalItem.setText(Formatter.getDecimal().mask(item.getTotalItems()));
			}
		}catch (BadFormatException ex){
			
		}
	}
	
	private void createFields(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = LayoutFactory.getInstance().getGridLayout();
		gridLayout.makeColumnsEqualWidth = false;
		if (style == ATHENA.EDIT){
			gridLayout.numColumns = 5;
		}
		if (style == ATHENA.INSERT){
			gridLayout.numColumns = 6;
		}		
		
		gridLayout.horizontalSpacing = 10;
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.product.id.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.product.name.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.unitMeasure.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.quantity.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.costPrice.field.label"));
		if (style == ATHENA.INSERT){
			new Label(composite, SWT.NONE);
		}		
				
		txtIdProduct = new CompositeText(composite);
		txtIdProduct.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		txtIdProduct.addKeyListener(new DialogListener());
		if (style == ATHENA.EDIT){ 
			txtIdProduct.setEnabled(false);
		}
		
		txtProductName = new CompositeText(composite,SWT.READ_ONLY);
		txtProductName.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		txtUnitMeasure = new CompositeText(composite, SWT.READ_ONLY);
		txtUnitMeasure.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		
		txtQuantity = new Text(composite, SWT.BORDER);
		txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		txtQuantity.addFocusListener(new TotalItemCalculateListener());
		
		txtCostPrice = new Text(composite, SWT.BORDER);
		txtCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		txtCostPrice.addFocusListener(new TotalItemCalculateListener());
		
		if (style == ATHENA.INSERT){
			btAdd = new ImageHyperlink(composite, SWT.NONE);
			btAdd.setImage(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_24).createImage());
			btAdd.setUnderlined(false);
			btAdd.addHyperlinkListener(new AddItemListener());
		}
	}
	
	private void createInfoFields(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = LayoutFactory.getInstance().getGridLayout();
		gridLayout.makeColumnsEqualWidth = false;
		gridLayout.numColumns = 6;
		gridLayout.horizontalSpacing = 10;
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.lastCostPrice.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.totalItems.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.icms.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.totalIcms.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.ipi.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.totalIpi.field.label"));
		
		txtLastCostPrice = new CompositeText(composite, SWT.READ_ONLY);
		txtLastCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		txtTotalItem = new CompositeText(composite, SWT.READ_ONLY);
		txtTotalItem.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		viewerICms = new ComboViewer(new CCombo(composite, SWT.BORDER|SWT.DROP_DOWN|SWT.READ_ONLY));
		viewerICms.setContentProvider(new GenericContentProvider<Icms>());
		viewerICms.setLabelProvider(new IcmsLabelProvider());
		viewerICms.getCCombo().setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		viewerICms.addSelectionChangedListener(new ChageIcmsListener());
		
		txtTotalIcms = new CompositeText(composite, SWT.READ_ONLY);
		txtTotalIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		txtIpiBase = new Text(composite, SWT.BORDER);
		txtIpiBase.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		txtIpiBase.addFocusListener(new TotalIpiCalculateListener());
		
		txtIpi = new CompositeText(composite, SWT.READ_ONLY);
		txtIpi.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		txtQuantity.addKeyListener(WidgetUtils.setNextFocusOnEnter(txtCostPrice));
		txtCostPrice.addKeyListener(WidgetUtils.setNextFocusOnEnter(viewerICms.getCCombo()));
		viewerICms.getCCombo().addKeyListener(WidgetUtils.setNextFocusOnEnter(txtIpiBase));
		txtIpiBase.addKeyListener(WidgetUtils.setNextFocusOnEnter(btAdd));
	}
	
	private void search(){
		Long id = Long.parseLong(txtIdProduct.getText());
		product = manufactureClientService.findProductByUniqueKey(id,txtIdProduct.getText());	
		fill(product);
	}
	
	private void fill(Product product){
		try{
			if (product != null){
				txtIdProduct.setText(product.getId().toString());
				txtProductName.setText(product.getName());
				txtUnitMeasure.setText(UnitMeasureTradutor.getLabel(product.getUnitMeasure()));
				txtLastCostPrice.setText(Formatter.getDecimal().mask(product.getLastCostprice()));
				viewerICms.setSelection(new SearchSelection<Icms>(product.getIcms()));
			}
		}catch (BadFormatException ex){
			MessageDialog.openError(shell,Messages.getMessage("app.error"),ex.getMessage());
		}
	}
		
	private void clear(){		
		txtIdProduct.setText("");
		txtProductName.clear();
		txtUnitMeasure.clear();
		txtLastCostPrice.clear();
		txtQuantity.setText("");
		txtIpi.clear();
		txtTotalIcms.clear();
		txtTotalItem.clear();
		txtCostPrice.setText("");
		viewerICms.getCCombo().select(0);
	}
	
	private InvoiceItemEvent buildEvent(Product product){		
		try{
			InvoiceItemEvent itemEvent = new InvoiceItemEvent(product);
			itemEvent.product = product;
			itemEvent.quantity = Formatter.getWeight().parse(txtQuantity.getText());
			itemEvent.costPrice = Formatter.getDecimal().parse(txtCostPrice.getText());
			itemEvent.ipi = Formatter.getDecimal().parse(txtIpi.getText());		
			itemEvent.icms = (Icms)((IStructuredSelection)viewerICms.getSelection()).getFirstElement();
			itemEvent.ipiBase = Formatter.getDecimal().parse(txtIpiBase.getText());
			itemEvent.lastCostPrice = Formatter.getDecimal().parse(txtCostPrice.getText());
			itemEvent.totalIcms = Formatter.getDecimal().parse(txtTotalIcms.getText());		
			return itemEvent;
		}catch (BadFormatException ex){
			
		}
		return null;
	}
	
	protected void fireAddedEvent(InvoiceItemEvent itemEvent){		
		for (Object listener : listeners.getListeners()){
			((InvoiceItemListener)listener).itemAdded(itemEvent);
		}
	}
	
	protected void fireEditedEvent(InvoiceItemEvent itemEvent){
		for (Object listener : listeners.getListeners()){
			((InvoiceItemListener)listener).iItemUpdated(itemEvent);
		}
	}
	
	protected void fireDeleteEvent(InvoiceItemEvent itemEvent){
		for (Object listener : listeners.getListeners()){
			((InvoiceItemListener)listener).itemDeleted(itemEvent);
		}
	}
	
	public void addInvoiceItemListener(InvoiceItemListener listener){
		this.listeners.add(listener);
	}
	
	public void removeInvoiceItemListener(InvoiceItemListener listener){
		this.listeners.remove(listener);
	}
	
	public void addItem(){
		InvoiceItemEvent itemEvent = buildEvent(product);
		fireAddedEvent(itemEvent);
		clear();
	}
	
	public void editItem(){
		InvoiceItemEvent itemEvent = buildEvent(product);
		fireEditedEvent(itemEvent);
		clear();
	}
	
	public void deleteItem(InvoiceInputItem item){
		InvoiceItemEvent itemEvent = new InvoiceItemEvent(item);
		fireDeleteEvent(itemEvent);		
	}
	
	class AddItemListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			addItem();
		}
	}
	
	class TotalItemCalculateListener extends FocusAdapter{
		@Override
		public void focusLost(FocusEvent e) {
			try {				
				txtTotalItem.setText(MathTools.multiply(txtQuantity.getText(), txtCostPrice.getText()));
			} catch (BadFormatException ex) {				
			}			
		}
	}
	
	class TotalIpiCalculateListener extends FocusAdapter{
		@Override
		public void focusLost(FocusEvent e) {
			try {				
				txtIpi.setText(MathTools.percentage(txtTotalItem.getText(), txtIpiBase.getText()));
			} catch (BadFormatException ex) {				
			}	
		}
	}
	
	class DialogListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.keyCode);
			switch (e.keyCode) {
			case IKeyPadConstants.KEY_F9:
				ProductDialog dialog = new ProductDialog(e.display.getActiveShell());
				Product product = dialog.dialog();
				fill(product);
				break;
			case IKeyPadConstants.KEY_ENTER:
				search();
				break;
			case IKeyPadConstants.KEY_ENTER_NUMERICO:
				search();
				break;
			default:
				break;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if (product != null){
				txtQuantity.setFocus();
			}
		}
	}
	
	class ChageIcmsListener implements ISelectionChangedListener{
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			try{
				Icms icms = (Icms) ((IStructuredSelection)event.getSelection()).getFirstElement();
				txtTotalIcms.setText(MathTools.percentage(txtTotalItem.getText(),icms.getPercentage()));
			}catch(BadFormatException ex){
				
			}
		}
	}	
	
}
