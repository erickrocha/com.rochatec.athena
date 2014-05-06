package com.rochatec.athena.invoice.input.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.model.Executable;
import com.rochatec.framework.model.Property;
import com.rochatec.graphics.gui.TablePropertieViewer;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.IKeyPadConstants;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.view.AbstractView;

public class InvoiceInputSupplierView extends AbstractView implements Executable<Supplier>{

	public static final String ID = "com.rochatec.athena.invoice.input.view.InvoiceInputSupplierView";
	private Text txtSupplier;
	private Button btSearch;
	private TablePropertieViewer viewer;
	
	private SupplyClientService supplyClientService = ServiceFactory.getInstance().getSupplyClientService();
	
	@Override
	public void execute(List<Supplier> objects) {
	}

	@Override
	public void execute(Supplier supplier) {
		SearchSelection<Supplier> selection = new SearchSelection<Supplier>(supplier);
		getSite().getSelectionProvider().setSelection(selection);
	}

	@Override
	protected void createContents(Composite parent) {
		createSearchBox(parent);
		createInfoBox(parent);	
		
	}
	
	private void createSearchBox(Composite parent){
		Group group = new Group(parent,SWT.NONE);
		group.setText(Messages.getMessage("supplier.search.tile"));
		group.setLayout(LayoutFactory.getInstance().getGridLayout(2,false));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		txtSupplier = new Text(group, SWT.BORDER);
		txtSupplier.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		txtSupplier.addKeyListener(new EnterListener());
		btSearch = new Button(group, SWT.BORDER);
		btSearch.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,false,false));
		btSearch.setText(Messages.getMessage("app.search"));
		btSearch.addSelectionListener(new SearchProcessListener());
	}
	
	private void createInfoBox(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));						
		viewer = new TablePropertieViewer(composite);
	}
	
	private List<Property> supplierToList(Supplier supplier){
		List<Property> properties = new ArrayList<Property>();
		try {
			properties.add(new Property(getLabel("dateRegister"),Formatter.getDate().mask(supplier.getDateRegister()),1));
			properties.add(new Property(getLabel("tradeName"),supplier.getTradeName(),2));
			properties.add(new Property(getLabel("socialSecurity"),Formatter.getSocialSecurity().mask(supplier.getSocialSecurity()),3));
			properties.add(new Property(getLabel("registerNumber"),supplier.getRegisterNumber(),4));
			properties.add(new Property(getLabel("cityRegister"),supplier.getCityRegister(),5));
			properties.add(new Property(getLabel("street"),supplier.getAddress().getStreet(),6));
			properties.add(new Property(getLabel("streetNumber"),supplier.getAddress().getAddressNumber(),7));
			properties.add(new Property(getLabel("zipcode"),Formatter.getZipCode().mask(supplier.getZipcode()),8));
			properties.add(new Property(getLabel("neighborhood"),supplier.getAddress().getNeighborhood(),9));
			properties.add(new Property(getLabel("city"),supplier.getAddress().getCity(),10));
			properties.add(new Property(getLabel("province"),supplier.getAddress().getProvince().getAcronym(),11));
			properties.add(new Property(getLabel("phone"),Formatter.getPhone().mask(supplier.getPhone()),12));
			properties.add(new Property(getLabel("fax"),Formatter.getPhone().mask(supplier.getFax()),13));
			properties.add(new Property(getLabel("website"),supplier.getWebsite(),14));
		} catch (BadFormatException e) {
			Activator.getDefault().addConsoleError(e);
		}
		return properties;
	}
	
	private String getLabel(String sufix){
		return Messages.getMessage("supplier.field.label."+sufix);
	}
	
	private void searchSupplierr(){
		Long id = Long.parseLong(txtSupplier.getText());
		Supplier supplier = supplyClientService.findSupplierById(id);
		setTitle(supplier.getCompanyName());
		viewer.setInput(supplierToList(supplier));		
		execute(supplier);
	}

	@Override
	protected void addListeners() {
		
		
	}

	@Override
	public void setFocus() {
		txtSupplier.setFocus();
	}
	
	class EnterListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode == IKeyPadConstants.KEY_ENTER || e.keyCode == IKeyPadConstants.KEY_ENTER_NUMERICO){
				searchSupplierr();			}
		}
	}
	
	class SearchProcessListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			searchSupplierr();			
		}
	}

}
