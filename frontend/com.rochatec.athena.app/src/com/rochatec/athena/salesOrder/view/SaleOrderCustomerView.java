package com.rochatec.athena.salesOrder.view;

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

public class SaleOrderCustomerView extends AbstractView implements Executable<Customer>{

	public static final String ID = "com.rochatec.athena.salesOrder.view.SaleOrderCustomerView";
	private Text txtCustomer;
	private Button btSearch;
	private TablePropertieViewer viewer;	
	
	protected CRMClientService crmClientService = ServiceFactory.getInstance().getCrmClientService();
	
	
	@Override
	protected void createContents(Composite parent) {
		createSearchBox(parent);
		createInfoBox(parent);		
	}
	
	private void createSearchBox(Composite parent){
		Group group = new Group(parent,SWT.NONE);
		group.setText(Messages.getMessage("customer.search.title"));
		group.setLayout(LayoutFactory.getInstance().getGridLayout(2,false));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		txtCustomer = new Text(group, SWT.BORDER);
		txtCustomer.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		txtCustomer.addKeyListener(new EnterListener());
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

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						SaleOrderHistoryView.ID));
	}

	@Override
	public void setFocus() {
		txtCustomer.setFocus();
	}
	
	private List<Property> customerToList(Customer customer){
		List<Property> properties = new ArrayList<Property>();
		try {
			properties.add(new Property(getLabel("dateRegister"),Formatter.getDate().mask(customer.getDateRegister()),1));
			properties.add(new Property(getLabel("socialSecurity"),Formatter.getSocialSecurity().mask(customer.getSocialSecurity()),2));
			properties.add(new Property(getLabel("registerNumber"),customer.getRegisterNumber(),3));
			properties.add(new Property(getLabel("street"),customer.getAddress().getStreet(),4));
			properties.add(new Property(getLabel("streetNumber"),customer.getAddress().getAddressNumber(),5));
			properties.add(new Property(getLabel("zipcode"),Formatter.getZipCode().mask(customer.getZipcode()),6));
			properties.add(new Property(getLabel("neighborhood"),customer.getAddress().getNeighborhood(),7));
			properties.add(new Property(getLabel("city"),customer.getAddress().getCity(),8));
			properties.add(new Property(getLabel("province"),customer.getAddress().getProvince().getAcronym(),9));
			properties.add(new Property(getLabel("homePhone"),Formatter.getPhone().mask(customer.getHomePhone()),10));
			properties.add(new Property(getLabel("cellPhone"),Formatter.getPhone().mask(customer.getCellPhone()),11));
			properties.add(new Property(getLabel("email"),customer.getEmail(),12));
		} catch (BadFormatException e) {
			Activator.getDefault().addConsoleError(e);
		}
		return properties;
	}
	
	private String getLabel(String sufix){
		return Messages.getMessage("customer.field.label."+sufix);
	}
	
	private void searchCustomer(){
		Long id = Long.parseLong(txtCustomer.getText());
		Customer customer = crmClientService.findCustomerById(id);
		setTitle(customer.getName());
		viewer.setInput(customerToList(customer));		
		execute(customer);
	}
	
	class EnterListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode == IKeyPadConstants.KEY_ENTER || e.keyCode == IKeyPadConstants.KEY_ENTER_NUMERICO){
				searchCustomer();
			}
		}
	}
	
	class SearchProcessListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			searchCustomer();			
		}
	}

	@Override
	public void execute(List<Customer> customers) {
				
	}

	@Override
	public void execute(Customer customer) {
		SearchSelection<Customer> selection = new SearchSelection<Customer>(customer);
		getSite().getSelectionProvider().setSelection(selection);		
	}
}
