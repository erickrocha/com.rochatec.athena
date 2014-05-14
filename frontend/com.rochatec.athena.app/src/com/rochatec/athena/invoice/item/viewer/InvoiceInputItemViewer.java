package com.rochatec.athena.invoice.item.viewer;

import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
import com.rochatec.athena.manufacture.item.provider.ProductItemLabelProvider;
import com.rochatec.athena.manufacture.item.table.ItemTable;
import com.rochatec.athena.manufacture.product.dialog.ProductDialog;
import com.rochatec.athena.model.IProductItem;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.util.UnitMeasureTradutor;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.util.IKeyPadConstants;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.WidgetUtils;

public class InvoiceInputItemViewer {
	
	private AbstractTable tableViewer;
	private Composite base;
	
	private Text txtProductId;
	private CLabel lblProductName;
	private CLabel lblEmb;
	private ComboViewer viewerICms;
	private Text txtIpiBase;
	private Text txtIpi;
	private CLabel lblLastCostPrice;	
	private Text txtCostPrice;
	private Text txtQuantity;
	private ImageHyperlink btSearch;
	private ListenerList listeners;
	
	private Product product;
	
	private ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	
	public InvoiceInputItemViewer(Composite parent) {
		listeners = new ListenerList();
		base = new Composite(parent,SWT.NONE);
		base.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		base.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createContents(base);
		createTable(base);
	}
	
	protected void createContents(Composite parent){
		Group composite = new Group(parent,SWT.BORDER);
		composite.setText(Messages.getMessage("itemViewer.title.label"));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		createFields(composite);
		
		Composite buttonComposite = new Composite(composite, SWT.NONE);
		buttonComposite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		buttonComposite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true,0,2));		
		
		new Label(buttonComposite, SWT.NONE);
		new Label(buttonComposite, SWT.NONE);
		
		btSearch = new ImageHyperlink(buttonComposite, SWT.NONE);
		btSearch.setImage(Activator.getImageDescriptor(IPathIcons.SEARCH_48).createImage());
		btSearch.setUnderlined(false);
		btSearch.addHyperlinkListener(new SearchListener());
		
		createInfoFields(composite);
		postCreate();
	}
	
	private void postCreate(){
		viewerICms.setInput(manufactureClientService.findAllIcms());
	}
	
	private void createFields(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(4));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.id.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.name.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.unitMeasure.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.quantity.label"));
		
		txtProductId = new Text(composite, SWT.BORDER);
		txtProductId.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		txtProductId.addKeyListener(new DialogListener());
		
		lblProductName = new CLabel(composite, SWT.BORDER);
		lblProductName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		lblEmb = new CLabel(composite, SWT.BORDER);
		lblEmb.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		
		txtQuantity = new Text(composite, SWT.BORDER);
		txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));		
	}
	
	private void createInfoFields(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(5));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.icms.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.ipiBase.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.ipi.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.lastCostPrice.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.costPrice.label"));
		
		viewerICms = new ComboViewer(new CCombo(composite, SWT.BORDER|SWT.DROP_DOWN|SWT.READ_ONLY));
		viewerICms.setContentProvider(new GenericContentProvider<Icms>());
		viewerICms.setLabelProvider(new IcmsLabelProvider());
		viewerICms.getCCombo().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtIpiBase = new Text(composite, SWT.BORDER);
		txtIpiBase.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtIpi = new Text(composite, SWT.BORDER);
		txtIpi.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		lblLastCostPrice = new CLabel(composite, SWT.BORDER);
		lblLastCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtCostPrice = new Text(composite, SWT.BORDER);
		txtCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtQuantity.addKeyListener(WidgetUtils.setNextFocusOnEnter(viewerICms.getCCombo()));
		viewerICms.getCCombo().addKeyListener(WidgetUtils.setNextFocusOnEnter(txtIpiBase));
		txtIpiBase.addKeyListener(WidgetUtils.setNextFocusOnEnter(txtIpi));
		txtIpi.addKeyListener(WidgetUtils.setNextFocusOnEnter(txtCostPrice));
		txtCostPrice.addKeyListener(WidgetUtils.setNextFocusOnEnter(btSearch));
	}
	
	private void createTable(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		tableViewer = new ItemTable(composite);
		tableViewer.setContentProvider(new GenericContentProvider<IProductItem>());
		tableViewer.setLabelProvider(new ProductItemLabelProvider());
	}
	
	public void setLayoutData(Object layoutData){
		this.base.setLayoutData(layoutData);
	}
	
	public Control getControl(){
		return base;
	}
	
	public void setInput(List<InvoiceInputItem> items){
		tableViewer.setInput(items);
	}
	
	private void fill(Product product){		
		if (product != null){
			try{
				txtProductId.setText(product.getId().toString());
				lblProductName.setText(product.getName());
				viewerICms.setSelection(new SearchSelection<Icms>(product.getIcms()));
				lblEmb.setText(UnitMeasureTradutor.getLabel(product.getUnitMeasure()));
				lblLastCostPrice.setText(Formatter.getCurrency().mask(product.getLastCostprice()));
			}catch(BadFormatException ex){
				Activator.getDefault().println(ex.getMessage());
			}
		}
	}
	
	private void search(){
		Long id = Long.parseLong(txtProductId.getText());
		product = manufactureClientService.findProductById(id);
		fill(product);		
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
	}
}
