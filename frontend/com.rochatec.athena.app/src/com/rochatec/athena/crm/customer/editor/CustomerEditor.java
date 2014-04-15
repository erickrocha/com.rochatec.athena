package com.rochatec.athena.crm.customer.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.address.viewer.AddressViewer;
import com.rochatec.athena.client.service.CRMClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.framework.formater.impl.PhoneFormaterImpl;
import com.rochatec.framework.formater.impl.SocialSecurityFormaterImpl;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.DateField;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.gui.MaskedText;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.WidgetUtils;

public class CustomerEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.athena.crm.customer.editor.CustomerEditor";

	private CustomerEditorInput editorInput;
	protected IdLabel idLabel;

	protected DateField dateRegisterCal;
	protected Text txtName;
	protected MaskedText txtSocialSecurity;
	protected Text txtRegisterNumber;
	protected Text txtEmail;
	protected MaskedText txtHomePhone;
	protected MaskedText txtCellPhone;
	protected AddressViewer addressViewer;
	
	private CRMClientService crmClientService = ServiceFactory.getInstance().getCrmClientService();

	@Override
	public void makeActions(IToolBarManager toolBarManager) {

	}

	@Override
	protected void createContents(Composite parent) {
		createHeader(form.getBody());
		createFields(form.getBody());
		createAddressViewer(form.getBody());
		WidgetUtils.backgroundEquals(form.getBody()); 
		DataBindingFactory<Customer> factory = new DataBindingFactory<Customer>(editorInput.getCustomer(),this);
		factory.bind(getBinds());
	}

	private void createHeader(Composite parent) {
		Composite composite = new Group(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);

		Composite panel = new Composite(composite, SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(3, 10));

		new Label(panel, SWT.NONE).setText(Messages.getMessage("customer.field.label.dateRegister"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("customer.field.label.socialSecurity"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("customer.field.label.name"));

		dateRegisterCal = new DateField(panel);
		dateRegisterCal.setLayoutData(new GridData(120, 30));

		txtSocialSecurity = new MaskedText(panel,new SocialSecurityFormaterImpl());
		txtSocialSecurity.setLayoutData(new GridData(150, 17));

		txtName = new Text(panel, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	}

	private void createFields(Composite parent) {
		Group composite = new Group(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(4, false));

		new Label(composite, SWT.NONE).setText(Messages.getMessage("customer.field.label.registerNumber"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("customer.field.label.homePhone"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("customer.field.label.cellPhone"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("customer.field.label.email"));

		txtRegisterNumber = new Text(composite, SWT.BORDER);
		txtRegisterNumber.setLayoutData(new GridData(200, 15));

		txtHomePhone = new MaskedText(composite, new PhoneFormaterImpl());
		txtHomePhone.setLayoutData(new GridData(200, 15));

		txtCellPhone = new MaskedText(composite, new PhoneFormaterImpl());
		txtCellPhone.setLayoutData(new GridData(200, 15));

		txtEmail = new Text(composite, SWT.BORDER);
		txtEmail.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	}
	
	private void createAddressViewer(Composite parent) {
		addressViewer = new AddressViewer(parent,SWT.NONE,this);
	}

	@Override
	protected void addListeners() {

	}

	@Override
	protected void fill() {
		if (editorInput.getCustomer().getId() != null) {
			Customer customer = editorInput.getCustomer();
			idLabel.setLabelText(customer.getId());
			SearchSelection<Address> selection = new SearchSelection<Address>(customer.getAddress());
			addressViewer.setSelection(selection);
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		Customer customer = editorInput.getCustomer();
		customer.setAddress(addressViewer.getAddress());
		customer = crmClientService.persist(customer);
		idLabel.setLabelText(customer.getId());

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (CustomerEditorInput) input;
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new  HashMap<String, Object>();
		map.put("dateRegister",dateRegisterCal);
		map.put("socialSecurity",txtSocialSecurity);
		map.put("name",txtName);
		map.put("registerNumber",txtRegisterNumber);
		map.put("email",txtEmail);
		map.put("homePhone",txtHomePhone);
		map.put("cellPhone",txtCellPhone);
		map.put("address",addressViewer);
		return map;
	}

}
