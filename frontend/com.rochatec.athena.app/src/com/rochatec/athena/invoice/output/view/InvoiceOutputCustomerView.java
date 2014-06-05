package com.rochatec.athena.invoice.output.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import com.rochatec.athena.client.service.CRMClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.provider.DefaultSelectionProvider;
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

public class InvoiceOutputCustomerView extends AbstractView implements Executable<Customer>{

	public static final String ID = "com.rochatec.athena.invoice.output.view.InvoiceOutputCustomerView";
	private Text txtSupplier;
	private Button btSearch;
	private TablePropertieViewer viewer;
	
	private CRMClientService crmClientService = ServiceFactory.getInstance().getCrmClientService();
	
	@Override
	public void execute(List<Customer> customers) {
	}

	@Override
	public void execute(Customer customer) {
		SearchSelection<Customer> selection = new SearchSelection<Customer>(customer);
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
	
	private List<Property> customerToList(Customer customer){
		List<Property> properties = new ArrayList<Property>();
		try {
			properties.add(new Property(getLabel("dateRegister"),Formatter.getDate().mask(customer.getDateRegister()),1));
			properties.add(new Property(getLabel("name"),customer.getName(),2));
			properties.add(new Property(getLabel("socialSecurity"),Formatter.getSocialSecurity().mask(customer.getSocialSecurity()),3));
			properties.add(new Property(getLabel("registerNumber"),customer.getRegisterNumber(),4));
			properties.add(new Property(getLabel("cityRegister"),customer.getCityRegister(),5));
			properties.add(new Property(getLabel("street"),customer.getAddress().getStreet(),6));
			properties.add(new Property(getLabel("streetNumber"),customer.getAddress().getAddressNumber(),7));
			properties.add(new Property(getLabel("zipcode"),Formatter.getZipCode().mask(customer.getZipcode()),8));
			properties.add(new Property(getLabel("neighborhood"),customer.getAddress().getNeighborhood(),9));
			properties.add(new Property(getLabel("city"),customer.getAddress().getCity(),10));
			properties.add(new Property(getLabel("province"),customer.getAddress().getProvince().getAcronym(),11));
			properties.add(new Property(getLabel("homePhone"),Formatter.getPhone().mask(customer.getHomePhone()),12));
			properties.add(new Property(getLabel("cellPhone"),Formatter.getPhone().mask(customer.getCellPhone()),13));
			properties.add(new Property(getLabel("email"),customer.getEmail(),14));
		} catch (BadFormatException e) {
			Activator.getDefault().addConsoleError(e);
		}
		return properties;
	}
	
	private String getLabel(String sufix){
		return Messages.getMessage("customer.field.label."+sufix);
	}
	
	private void searchCustomer(){
		Long id = Long.parseLong(txtSupplier.getText());
		Customer customer  = crmClientService.findCustomerById(id);
		setTitle(customer.getName());
		viewer.setInput(customerToList(customer));		
		execute(customer);
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						InvoiceOutputCustomerHistoryView.ID));
		
	}

	@Override
	public void setFocus() {
		txtSupplier.setFocus();
	}
	
	class EnterListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode == IKeyPadConstants.KEY_ENTER || e.keyCode == IKeyPadConstants.KEY_ENTER_NUMERICO){
				searchCustomer();			}
		}
	}
	
	class SearchProcessListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			searchCustomer();			
		}
	}

}
