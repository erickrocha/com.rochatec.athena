package com.rochatec.athena.manufacture.productSet.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.category.provider.CategoryLabelProvider;
import com.rochatec.athena.manufacture.icms.provider.IcmsLabelProvider;
import com.rochatec.athena.manufacture.item.provider.ProductItemLabelProvider;
import com.rochatec.athena.manufacture.item.table.ItemTable;
import com.rochatec.athena.manufacture.ncm.dialog.NcmDialog;
import com.rochatec.athena.manufacture.ncm.provider.NcmLabelProvider;
import com.rochatec.athena.manufacture.product.provider.ProductLabelProvider;
import com.rochatec.athena.manufacture.product.viewer.ProductViewer;
import com.rochatec.athena.manufacture.unitMeasure.provider.UnitMeasureLabelProvider;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.model.IProductItem;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.Ncm;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.model.UnitMeasure;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.DateFormatedText;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.gui.NumberFormatedText;
import com.rochatec.graphics.gui.TimeFormatedText;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.viewer.TextViewer;

public class ProductSetEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.metallurgical.manufacture.productSet.editor.ProductSetEditor";
	protected ProductSetEditorInput editorInput;
	protected ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	
	private IdLabel idLabel;
	private DateChooserCombo calendarCombo;
	private Text txtName;
	
	private TableComboViewer viewerCategory;
	private Text txtShortName;
	
	private TextViewer textViewerNcm;
	private ComboViewer viewerICms;
	private NumberFormatedText txtIpi;
	private ComboViewer cmbUnitMeasure;
	private Button btActive;
	private Button btManufacture;
	
	private NumberFormatedText txtStock;
	private NumberFormatedText txtStockDown;
	private NumberFormatedText txtStockUp;
	private NumberFormatedText txtSellPrice;
	private NumberFormatedText txtCostPrice;
	private NumberFormatedText txtLastSellPrice;
	private NumberFormatedText txtLastCostPrice;
	private DateFormatedText txtDateLastSellPrice;
	private DateFormatedText txtDateLastCostPrice;
	private NumberFormatedText txtMarkup;
	private NumberFormatedText txtWeight;
	private NumberFormatedText txtHeight;
	private NumberFormatedText txtWidth;
	private TimeFormatedText txtProductionTime;
	private TimeFormatedText txtLastProductionTime;
	private TimeFormatedText txtAverageProductionTime;
	
	@Override
	protected void createContents(Composite parent) {
		createFields(form.getBody());
		createCategoryBox(form.getBody());
		createTributaryBox(form.getBody());
		createStockBox(form.getBody());
		createPriceBox(form.getBody());
		createInfoBox(form.getBody());
		createTabFolder(form.getBody());
		DataBindingFactory<ProductSet> bindingFactory = new DataBindingFactory<ProductSet>(editorInput.getProductSet(),this);
		bindingFactory.bind(getBinds());
	}
	
	private void createFields(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		GridLayout layout = LayoutFactory.getInstance().getGridLayout(2);
		layout.marginTop = 10;
		layout.marginLeft = 10;
		layout.marginRight = 10;
		panel.setLayout(layout);
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("productset.field.label.dateRegister"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("productset.field.label.name"));
		
		calendarCombo =new DateChooserCombo(panel,SWT.BORDER);
		calendarCombo.setLayoutData(new GridData(200,20));
		
		txtName = new Text(panel, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
	}
	
	private void createCategoryBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.category"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.shortName"));
		viewerCategory = new TableComboViewer(new TableCombo(composite,SWT.DROP_DOWN|SWT.READ_ONLY|SWT.BORDER));
		viewerCategory.setContentProvider(new GenericContentProvider<Category>());
		viewerCategory.getTableCombo().defineColumns(2);
		viewerCategory.getTableCombo().setDisplayColumnIndex(1);
		viewerCategory.setLabelProvider(new CategoryLabelProvider());
		viewerCategory.getTableCombo().setLayoutData(new GridData(200,20));
		
		txtShortName = new Text(composite, SWT.BORDER);
		txtShortName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createTributaryBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(6));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.ncm"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.icms"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.ipi"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.unitmeasure"));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.manufacture"));
		
		textViewerNcm =new TextViewer(new Text(composite, SWT.BORDER));
		textViewerNcm.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		textViewerNcm.setContentProvider(new GenericContentProvider<Ncm>());
		textViewerNcm.setLabelProvider(new NcmLabelProvider());
		textViewerNcm.addKeyListener(new NcmSearchListener());
		
		viewerICms = new ComboViewer(new CCombo(composite, SWT.BORDER|SWT.DROP_DOWN|SWT.READ_ONLY));
		viewerICms.setContentProvider(new GenericContentProvider<Icms>());
		viewerICms.setLabelProvider(new IcmsLabelProvider());
		viewerICms.getCCombo().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtIpi = new NumberFormatedText(composite);
		txtIpi.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		cmbUnitMeasure = new ComboViewer(new CCombo(composite, SWT.DROP_DOWN|SWT.BORDER|SWT.READ_ONLY));
		cmbUnitMeasure.getCCombo().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		cmbUnitMeasure.setContentProvider(new GenericContentProvider<UnitMeasure>());
		cmbUnitMeasure.setLabelProvider(new UnitMeasureLabelProvider());
		
		
		btActive = new Button(composite, SWT.CHECK);
		btActive.setText(Messages.getMessage("app.active"));
		btActive.setSelection(true);
		btActive.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		btActive.addSelectionListener(new ActiveListener());
		
		btManufacture = new Button(composite, SWT.CHECK);
		btManufacture.setText(Messages.getMessage("app.yes"));
		btManufacture.setSelection(true);
		btManufacture.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		btManufacture.addSelectionListener(new YesNoListener());
	}
	
	private void createStockBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setEnabled(false);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(4));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.stockDown"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.stock"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.stockUp"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.markup"));
		
		txtStockDown = new NumberFormatedText(composite);
		txtStockDown.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtStock = new NumberFormatedText(composite);
		txtStock.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtStockUp = new NumberFormatedText(composite);
		txtStockUp.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtMarkup = new NumberFormatedText(composite);
		txtMarkup.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createPriceBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setEnabled(false);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(6));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.costPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.lastCostPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.dateLastCostPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.sellPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.lastSellPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.dateLastSellPrice"));
		
		txtCostPrice = new NumberFormatedText(composite);
		txtCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtLastCostPrice = new NumberFormatedText(composite);
		txtLastCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtDateLastCostPrice = new DateFormatedText(composite);
		txtDateLastCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtSellPrice = new NumberFormatedText(composite);
		txtSellPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtLastSellPrice = new NumberFormatedText(composite);
		txtLastSellPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtDateLastSellPrice = new DateFormatedText(composite);
		txtDateLastSellPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
	}
	
	private void createInfoBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(6));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.weight"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.height"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.width"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.productionTime"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.lastProductionTime"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("productset.field.label.averageProductionTime"));
		
		txtWeight = new NumberFormatedText(composite);
		txtWeight.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtHeight = new NumberFormatedText(composite);
		txtHeight.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtWidth = new NumberFormatedText(composite);
		txtWidth.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtProductionTime = new TimeFormatedText(composite);
		txtProductionTime.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtLastProductionTime = new TimeFormatedText(composite);
		txtLastProductionTime.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtAverageProductionTime = new TimeFormatedText(composite);
		txtAverageProductionTime.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
	}
	
	private void createTabFolder(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		composite.setLayout(new FillLayout());
		final CTabFolder container = new CTabFolder(composite,  SWT.TOP| SWT.FLAT);
		container.setSimple( false );
		container.setMRUVisible(true);
		container.setBackground(Colors.getColorWhite());
		container.addTraverseListener(new TraverseListener() { 
		public void keyTraversed(TraverseEvent e) {
			switch (e.detail) {
				case SWT.TRAVERSE_PAGE_NEXT:
				case SWT.TRAVERSE_PAGE_PREVIOUS:
					int detail = e.detail;
					e.doit = true;
					e.detail = SWT.TRAVERSE_NONE;
					Control control = container.getParent();
					control.traverse(detail, new Event());
				}
			}
		});
		container.setLayout(LayoutFactory.getInstance().getFillLayout());
		createItemViewer(container);
		container.setSelection(0);
	}
	

	private void createItemViewer(CTabFolder container) {
		CTabItem cTabItem = new CTabItem(container,SWT.NONE);
		cTabItem.setText(Messages.getMessage("productset.tab.product"));
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		ProductViewer productViewer = new ProductViewer(composite, SWT.BORDER);
		productViewer.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		productViewer.setContentProvider(new GenericContentProvider<Product>());
		productViewer.setLabelProvider(new ProductLabelProvider());
				
		Composite panel = new Composite(composite, SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		ItemTable itemTable = new ItemTable(panel);
		itemTable.setContentProvider(new GenericContentProvider<IProductItem>());
		itemTable.setLabelProvider(new ProductItemLabelProvider());
		cTabItem.setControl(composite);
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fill() {
		List<Icms> icms = manufactureClientService.findAllIcms();
		List<Category> categories = manufactureClientService.findAllCategories();
		viewerICms.setInput(icms);
		viewerCategory.setInput(categories);
		if (editorInput.getProductSet().getId() != null){
			ProductSet productSet = editorInput.getProductSet();
			idLabel.setLabelText(productSet.getId());
			viewerICms.setSelection(new SearchSelection<Icms>(productSet.getIcms()));
			viewerCategory.setSelection(new SearchSelection<Category>(productSet.getCategory()));
		}else{
			viewerICms.setSelection(new SearchSelection<Icms>(icms.get(0)));
			viewerCategory.setSelection(new SearchSelection<Category>(categories.get(0)));
		}
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		ProductSet productSet = editorInput.getProductSet();
		productSet = manufactureClientService.persist(productSet);
		idLabel.setLabelText(productSet.getId());
		setDirty(false);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (ProductSetEditorInput)input;
	}
	
	class NcmSearchListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode == SWT.F9){
				NcmDialog dialog = new NcmDialog(e.display.getActiveShell());
				Ncm ncm = dialog.dialog();
				textViewerNcm.setSelection(new SearchSelection<Ncm>(ncm));
			}			
		}
	}		
	
	class ActiveListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			if ( ((Button)e.widget).getSelection()){
				((Button)e.widget).setText(Messages.getMessage("app.active"));
			}else{
				((Button)e.widget).setText(Messages.getMessage("app.inactive"));
			}
		}
	}
	
	class YesNoListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			if ( ((Button)e.widget).getSelection()){
				((Button)e.widget).setText(Messages.getMessage("app.yes"));
				txtProductionTime.setEnabled(true);
			}else{
				((Button)e.widget).setText(Messages.getMessage("app.no"));
				txtProductionTime.setEnabled(false);
			}
		}
	}
	
	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dateRegister", calendarCombo);
		map.put("name", txtName);
		map.put("category", viewerCategory);
		map.put("shortName", txtShortName);
		map.put("ncm",textViewerNcm);
		map.put("icms", viewerICms);
		map.put("ipi", txtIpi);
		map.put("unitMeasure", cmbUnitMeasure);
		map.put("active", btActive);
		map.put("manufacture", btManufacture);
		map.put("stock", txtStock);
		map.put("stockdown", txtStockDown);
		map.put("stockup", txtStockUp);
		map.put("sellprice", txtSellPrice);
		map.put("costprice", txtCostPrice);
		map.put("lastSellprice", txtLastSellPrice);
		map.put("lastCostprice", txtLastCostPrice);
		map.put("dateLastSellprice", txtDateLastSellPrice);
		map.put("dateLastCostprice", txtDateLastCostPrice);
		map.put("markup", txtMarkup);
		map.put("weight", txtWeight);
		map.put("height", txtHeight);
		map.put("width", txtWidth);
		map.put("productionTime", txtProductionTime);
		map.put("lastProductionTime", txtLastProductionTime);
		map.put("averageProductionTime", txtAverageProductionTime);
		return map;
	}	

}
