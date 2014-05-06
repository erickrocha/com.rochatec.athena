package com.rochatec.athena.components.viewer;

import org.eclipse.swt.SWT;
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
import com.rochatec.athena.manufacture.item.provider.ProductItemLabelProvider;
import com.rochatec.athena.manufacture.item.table.ItemTable;
import com.rochatec.athena.manufacture.product.dialog.ProductDialog;
import com.rochatec.athena.model.IProductItem;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.util.IKeyPadConstants;
import com.rochatec.graphics.util.LayoutFactory;


public class ItemViewer {

	private AbstractTable tableViewer;
	private Composite base;
	
	private Text txtProductId;
	private CLabel lblProductName;
	private CLabel lblIcms;
	private CLabel lblIpi;
	private CLabel lblPrice;
	private Text txtQuantity;
	private ImageHyperlink btSearch;
	
	private ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	
	public ItemViewer(Composite parent) {
		base = new Composite(parent,SWT.NONE);
		base.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		base.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createContents(base);
		createTable(base);
	}
	
	protected void createContents(Composite parent){
		Composite composite = new Group(parent,SWT.BORDER);
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
	}
	
	private void createFields(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(3));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.id.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.name.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.quantity.label"));
		
		txtProductId = new Text(composite, SWT.BORDER);
		txtProductId.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		txtProductId.addKeyListener(new DialogListener());
		
		lblProductName = new CLabel(composite, SWT.BORDER);
		lblProductName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtQuantity = new Text(composite, SWT.BORDER);
		txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
	}
	
	private void createInfoFields(Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(3));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.icms.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.ipi.label"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("itemViewer.product.price.label"));
		
		lblIcms = new CLabel(composite, SWT.BORDER);
		lblIcms.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		lblIpi = new CLabel(composite, SWT.BORDER);
		lblIpi.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		lblPrice = new CLabel(composite, SWT.BORDER);
		lblPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
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
	
	private void fill(Product product){
		if (product != null){
			try{
				txtProductId.setText(product.getId().toString());
				lblProductName.setText(product.getName());
				lblIcms.setText(product.getIcms().getDescription());
				lblIpi.setText(Formatter.getPercentage().mask(product.getIpi()));
				lblPrice.setText(Formatter.getCurrency().mask(product.getSellprice()));
			}catch(BadFormatException ex){
				
			}
		}
	}
	
	private void search(){
		Long id = Long.parseLong(txtProductId.getText());
		Product product = manufactureClientService.findProductById(id);
		fill(product);
	}
	
	
	class SearchListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			search();
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
