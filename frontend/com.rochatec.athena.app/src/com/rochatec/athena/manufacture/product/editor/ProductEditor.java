package com.rochatec.athena.manufacture.product.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.barcode.viewer.BarCodeViewer;
import com.rochatec.athena.manufacture.category.provider.CategoryLabelProvider;
import com.rochatec.athena.manufacture.icms.provider.IcmsLabelProvider;
import com.rochatec.athena.manufacture.ncm.dialog.NcmDialog;
import com.rochatec.athena.manufacture.ncm.provider.NcmLabelProvider;
import com.rochatec.athena.manufacture.product.helper.ProductHelper;
import com.rochatec.athena.manufacture.unitMeasure.provider.UnitMeasureLabelProvider;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.Ncm;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.UnitMeasure;
import com.rochatec.athena.util.ATHENA;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.DateFormatedText;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.gui.NumberFormatedText;
import com.rochatec.graphics.gui.TextField;
import com.rochatec.graphics.gui.TimeFormatedText;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.viewer.TextViewer;

public class ProductEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.athena.manufacture.product.editor.ProductEditor";
	protected ProductEditorInput editorInput;
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
	
	private TextField txtStock;
	private TextField txtStockDown;
	private TextField txtStockUp;
	private TextField txtSellPrice;
	private TextField txtCostPrice;
	private TextField txtLastSellPrice;
	private TextField txtLastCostPrice;
	private DateFormatedText txtDateLastSellPrice;
	private DateFormatedText txtDateLastCostPrice;
	private TextField txtMarkup;
	private TextField txtWeight;
	private TextField txtHeight;
	private TextField txtWidth;
	private TimeFormatedText txtProductionTime;
	private TimeFormatedText txtLastProductionTime;
	private TimeFormatedText txtAverageProductionTime;
	private BarCodeViewer barCodeViewer;
	
	@Override
	public void makeActions(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected void createContents(Composite parent) {
		createFields(form.getBody());
		createCategoryBox(form.getBody());
		createTributaryBox(form.getBody());
		createStockBox(form.getBody());
		createPriceBox(form.getBody());
		createInfoBox(form.getBody());
		createBarcode(form.getBody());
		DataBindingFactory<Product> factory = new DataBindingFactory<Product>(editorInput.getProduct(),this);
		factory.bind(getBinds());
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
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("product.field.label.dateRegister"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("product.field.label.name"));
		
		calendarCombo = new DateChooserCombo(panel,SWT.BORDER);
		calendarCombo.setLayoutData(new GridData(120,25));
		
		txtName = new Text(panel, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createCategoryBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.category"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.shortName"));
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
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.ncm"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.icms"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.ipi"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.unitmeasure"));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.manufacture"));
		
		textViewerNcm = new TextViewer(new Text(composite, SWT.BORDER));
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
		
		cmbUnitMeasure = new ComboViewer(new CCombo(composite, SWT.DROP_DOWN|SWT.BORDER));
		cmbUnitMeasure.getCCombo().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		cmbUnitMeasure.setContentProvider(new GenericContentProvider<UnitMeasure>());
		cmbUnitMeasure.setLabelProvider(new UnitMeasureLabelProvider());
		cmbUnitMeasure.setInput(UnitMeasure.getAll());
		cmbUnitMeasure.setSelection(new SearchSelection<UnitMeasure>(UnitMeasure.UN));
		
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
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.stockDown"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.stock"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.stockUp"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.markup"));
		
		txtStockDown = new TextField(composite,ATHENA.PATTERN_WEIGHT);
		txtStockDown.setFormatter(Formatter.getDecimal());
		txtStockDown.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtStock = new TextField(composite,ATHENA.PATTERN_WEIGHT);
		txtStock.setFormatter(Formatter.getDecimal());
		txtStock.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtStockUp = new TextField(composite,ATHENA.PATTERN_WEIGHT);
		txtStockUp.setFormatter(Formatter.getDecimal());
		txtStockUp.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtMarkup = new TextField(composite,ATHENA.PATTERN_BIGDECIMAL);
		txtMarkup.setFormatter(Formatter.getDecimal());
		txtMarkup.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createPriceBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setEnabled(false);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(6));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.costPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.lastCostPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.dateLastCostPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.sellPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.lastSellPrice"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.dateLastSellPrice"));
		
		txtCostPrice = new TextField(composite,ATHENA.PATTERN_BIGDECIMAL);
		txtCostPrice.setFormatter(Formatter.getDecimal());
		txtCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtLastCostPrice = new TextField(composite,ATHENA.PATTERN_BIGDECIMAL);
		txtLastCostPrice.setFormatter(Formatter.getDecimal());
		txtLastCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtDateLastCostPrice = new DateFormatedText(composite);
		txtDateLastCostPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtSellPrice = new TextField(composite,ATHENA.PATTERN_BIGDECIMAL);
		txtSellPrice.setFormatter(Formatter.getDecimal());
		txtSellPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtLastSellPrice = new TextField(composite,ATHENA.PATTERN_BIGDECIMAL);
		txtLastSellPrice.setFormatter(Formatter.getDecimal());
		txtLastSellPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtDateLastSellPrice = new DateFormatedText(composite);		
		txtDateLastSellPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
	}
	
	private void createInfoBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(6));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.weight"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.height"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.width"));		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.productionTime"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.lastProductionTime"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("product.field.label.averageProductionTime"));
		
		txtWeight = new TextField(composite,ATHENA.PATTERN_WEIGHT);
		txtWeight.setFormatter(Formatter.getWeight());
		txtWeight.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtHeight = new TextField(composite,ATHENA.PATTERN_WEIGHT);
		txtHeight.setFormatter(Formatter.getWeight());
		txtHeight.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtWidth = new TextField(composite,ATHENA.PATTERN_WEIGHT);
		txtWidth.setFormatter(Formatter.getWeight());
		txtWidth.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtProductionTime = new TimeFormatedText(composite);
		txtProductionTime.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtLastProductionTime = new TimeFormatedText(composite);
		txtLastProductionTime.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		txtAverageProductionTime = new TimeFormatedText(composite);
		txtAverageProductionTime.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));	
	}
	
	private void createBarcode(Composite parent){
		barCodeViewer = new BarCodeViewer(parent);
		barCodeViewer.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true));
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	protected void fill() {
		List<Icms> icms = manufactureClientService.findAllIcms();
		List<Category> categories = manufactureClientService.findAllCategories();
		viewerICms.setInput(icms);
		viewerCategory.setInput(categories);
		if (editorInput.getProduct().getId() != null){
			Product product = editorInput.getProduct();
			idLabel.setLabelText(product.getId());
			viewerICms.setSelection(new SearchSelection<Icms>(product.getIcms()));
			viewerCategory.setSelection(new SearchSelection<Category>(product.getCategory()));
			barCodeViewer.setBarCodes(product.getBarCodesList());
		}else{
			viewerICms.setSelection(new SearchSelection<Icms>(icms.get(0)));
			viewerCategory.setSelection(new SearchSelection<Category>(categories.get(0)));
			txtProductionTime.setEnabled(false);		
		}
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		Product product = editorInput.getProduct();
		ProductHelper helper = new ProductHelper(product);
		helper.fillBarCodes(barCodeViewer.getBarCodes());
		product = manufactureClientService.persist(product);
		idLabel.setLabelText(product.getId());
		setDirty(false);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (ProductEditorInput)input;
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
	
}
