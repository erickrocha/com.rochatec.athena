package com.rochatec.athena.invoice.item.form;

import org.eclipse.core.runtime.ListenerList;
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
import com.rochatec.athena.model.Product;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.util.MathTools;
import com.rochatec.athena.util.UnitMeasureTradutor;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.IKeyPadConstants;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.WidgetUtils;

public class ItemBox {

	private Text txtProductId;
	private Text lblProductName;
	private Text lblEmb;
	private ComboViewer viewerICms;
	private Text txtTotalIcms;
	private Text txtIpiBase;
	private Text txtIpi;
	private Text lblLastCostPrice;	
	private Text txtCostPrice;
	private Text txtTotalItem;
	private Text txtQuantity;
	private ImageHyperlink btAdd;
	private Product product;
	private ListenerList listeners;
	
	private ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	
	public ItemBox(Composite parent) {
		this.listeners = new ListenerList();
		createContents(parent);
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
		viewerICms.setInput(manufactureClientService.findAllIcms());
	}
	
	private void createFields(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = LayoutFactory.getInstance().getGridLayout();
		gridLayout.makeColumnsEqualWidth = false;
		gridLayout.numColumns = 6;
		gridLayout.horizontalSpacing = 10;
		composite.setLayout(gridLayout);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.product.id.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.product.name.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.unitMeasure.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.quantity.field.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("invoiceItem.costPrice.field.label"));
		new Label(composite, SWT.NONE);
		
		txtProductId = new Text(composite, SWT.BORDER);
		txtProductId.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		txtProductId.addKeyListener(new DialogListener());
		
		lblProductName = new Text(composite, SWT.BORDER);
		lblProductName.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		lblEmb = new Text(composite, SWT.BORDER);
		lblEmb.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		
		txtQuantity = new Text(composite, SWT.BORDER);
		txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		txtQuantity.addFocusListener(new TotalItemCalculateListener());
		
		txtCostPrice = new Text(composite, SWT.BORDER);
		txtCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		txtCostPrice.addFocusListener(new TotalItemCalculateListener());
		
		btAdd = new ImageHyperlink(composite, SWT.NONE);
		btAdd.setImage(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_24).createImage());
		btAdd.setUnderlined(false);
		btAdd.addHyperlinkListener(new SearchListener());
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
		
		lblLastCostPrice = new Text(composite, SWT.BORDER);
		lblLastCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		txtTotalItem = new Text(composite, SWT.BORDER);
		txtTotalItem.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		viewerICms = new ComboViewer(new CCombo(composite, SWT.BORDER|SWT.DROP_DOWN|SWT.READ_ONLY));
		viewerICms.setContentProvider(new GenericContentProvider<Icms>());
		viewerICms.setLabelProvider(new IcmsLabelProvider());
		viewerICms.getCCombo().setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		viewerICms.addSelectionChangedListener(new ChageIcmsListener());
		
		txtTotalIcms = new Text(composite, SWT.BORDER);
		txtTotalIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		txtIpiBase = new Text(composite, SWT.BORDER);
		txtIpiBase.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		txtIpiBase.addFocusListener(new TotalIpiCalculateListener());
		
		txtIpi = new Text(composite, SWT.BORDER);
		txtIpi.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		
		txtQuantity.addKeyListener(WidgetUtils.setNextFocusOnEnter(txtCostPrice));
		txtCostPrice.addKeyListener(WidgetUtils.setNextFocusOnEnter(viewerICms.getCCombo()));
		viewerICms.getCCombo().addKeyListener(WidgetUtils.setNextFocusOnEnter(txtIpiBase));
		txtIpiBase.addKeyListener(WidgetUtils.setNextFocusOnEnter(btAdd));
		
		lblProductName.addFocusListener(WidgetUtils.setNotFocused(txtQuantity));
		lblEmb.addFocusListener(WidgetUtils.setNotFocused(txtQuantity));
		
		lblLastCostPrice.addFocusListener(WidgetUtils.setNotFocused(viewerICms.getCCombo()));
		txtTotalItem.addFocusListener(WidgetUtils.setNotFocused(viewerICms.getCCombo()));
		
		txtTotalIcms.addFocusListener(WidgetUtils.setNotFocused(txtIpiBase));
		txtIpi.addFocusListener(WidgetUtils.setNotFocused(btAdd));
		
	}
	
	private void search(){
		Long id = Long.parseLong(txtProductId.getText());
		product = manufactureClientService.findProductById(id);
		fill(product);		
	}
	
	private void fill(Product product){		
		if (product != null){
			try{
				txtProductId.setText(product.getId().toString());
				lblProductName.setText(product.getName());
				viewerICms.setSelection(new SearchSelection<Icms>(product.getIcms()));
				lblEmb.setText(UnitMeasureTradutor.getLabel(product.getUnitMeasure()));
				lblLastCostPrice.setText(Formatter.getDecimal().mask(product.getLastCostprice()));
				txtCostPrice.setText(Formatter.getDecimal().mask(product.getCostprice()));				
				txtIpiBase.setText(Formatter.getDecimal().mask(product.getIpi()));
			}catch(BadFormatException ex){
				Activator.getDefault().println(ex.getMessage());
			}
		}
	}
	
	private void clear(){
		txtProductId.setText("");
		lblProductName.setText("");
		lblEmb.setText("");
		lblLastCostPrice.setText("");
		txtQuantity.setText("");
		txtIpi.setText("");
		txtTotalIcms.setText("");
		txtTotalItem.setText("");
		txtCostPrice.setText("");
		viewerICms.getCCombo().select(0);
	}
	
	private InvoiceItemEvent buildEvent(Product product){
		InvoiceItemEvent itemEvent = new InvoiceItemEvent(product);
		itemEvent.quantity = txtQuantity;
		itemEvent.costPrice = txtCostPrice;
		itemEvent.ipi = txtIpi;		
		itemEvent.icms = (Icms)((IStructuredSelection)viewerICms.getSelection()).getFirstElement();
		itemEvent.ipiBase = txtIpiBase;
		return itemEvent;
	}
	
	protected void fireAddedEvent(Product product){
		InvoiceItemEvent itemEvent = buildEvent(product);
		for (Object listener : listeners.getListeners()){
			((InvoiceItemListener)listener).itemAdded(itemEvent);
		}
	}
	
	public void addInvoiceItemListener(InvoiceItemListener listener){
		this.listeners.add(listener);
	}
	
	public void removeInvoiceItemListener(InvoiceItemListener listener){
		this.listeners.remove(listener);
	}
	
	class SearchListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			fireAddedEvent(product);
			clear();
			txtProductId.setFocus();
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