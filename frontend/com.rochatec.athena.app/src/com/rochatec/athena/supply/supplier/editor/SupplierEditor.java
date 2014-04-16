package com.rochatec.athena.supply.supplier.editor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.address.viewer.AddressViewer;
import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.framework.formater.impl.PhoneFormaterImpl;
import com.rochatec.framework.formater.impl.SocialSecurityFormaterImpl;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.gui.MaskedText;
import com.rochatec.graphics.util.LayoutFactory;

public class SupplierEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.athena.supply.supplier.editor.SupplierEditor";
	
	protected SupplyClientService supplyClientService = ServiceFactory.getInstance().getSupplyClientService();
	private SupplierEditorInput editorInput;
	
	protected IdLabel idLabel;
	
	protected DateChooserCombo dateRegisterCal;
	protected Text txtCompanyName;
	protected Text txtTradeName;
	protected MaskedText txtSocialSecurity;
	protected Text txtRegisterNumber;
	protected Text txtCityRegister;
	protected Text txtWebSite;
	protected MaskedText txtPhone;
	protected MaskedText txtFax;
	protected Button btActive;
	
	protected AddressViewer addressViewer;
	
	@Override
	protected void createContents(Composite parent) {
		createHeader(form.getBody());
		createShortnameField(form.getBody());
		createFields(form.getBody());
		createAddressViewer(form.getBody());
		DataBindingFactory<Supplier> bindingFactory = new  DataBindingFactory<Supplier>(editorInput.getSupplier(),this);
		bindingFactory.bind(getBinds());
	}
	
	private void createHeader(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(3,10));
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("supplier.field.label.dateRegister"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("supplier.field.label.socialSecurity"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("supplier.field.label.businessName"));
		
		dateRegisterCal = new DateChooserCombo(panel,SWT.BORDER);
		dateRegisterCal.setLayoutData(new GridData(120,25));
		dateRegisterCal.setValue(new Date());
		
		txtSocialSecurity = new MaskedText(panel,new SocialSecurityFormaterImpl());
		txtSocialSecurity.setLayoutData(new GridData(150,17));
		
		txtCompanyName = new Text(panel, SWT.BORDER);
		txtCompanyName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createShortnameField(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("supplier.field.label.tradeName"));
		new Label(composite, SWT.NONE);
		
		txtTradeName = new Text(composite, SWT.BORDER);
		txtTradeName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		btActive = new Button(composite,SWT.CHECK);
		btActive.setText( Messages.getMessage("app.active"));
		btActive.setSelection(true);
		btActive.addSelectionListener(new ActiveListener());
	}
	
	private void createFields(Composite parent){
		Group composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(5,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("supplier.field.label.registerNumber"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("supplier.field.label.cityRegister"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("supplier.field.label.phone"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("supplier.field.label.fax"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("supplier.field.label.website"));
		
		txtRegisterNumber = new Text(composite, SWT.BORDER);
		txtRegisterNumber.setLayoutData(new GridData(200,15));
		
		txtCityRegister = new Text(composite,SWT.BORDER);
		txtCityRegister.setLayoutData(new GridData(200,15));
		
		txtPhone = new MaskedText(composite, new PhoneFormaterImpl());
		txtPhone.setLayoutData(new GridData(200,15));
		
		txtFax = new MaskedText(composite, new PhoneFormaterImpl());
		txtFax.setLayoutData(new GridData(200,15));
		
		txtWebSite = new Text(composite, SWT.BORDER);
		txtWebSite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createAddressViewer(Composite parent){
		addressViewer = new AddressViewer(parent,SWT.NONE,this);
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	protected void fill() {
		if (editorInput.getSupplier().getId() != null){
			Supplier supplier = editorInput.getSupplier();
			addressViewer.setInput(supplier.getAddress());
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (isDirty()){
			Supplier supplier = editorInput.getSupplier();
			supplier.setActive(btActive.getSelection());
			supplier.setAddress(addressViewer.getAddress());
			supplier = supplyClientService.persist(supplier);
			idLabel.setLabelText(supplier.getId());
			setDirty(false);
			setEnabled(true);
			getSite().getPage().closeEditor(this,true);
		}
	}
	
	public void setEnabled(boolean value){
		txtCompanyName.setEnabled(value);
		dateRegisterCal.setEnabled(value);
		txtSocialSecurity.setEnabled(value);
		txtTradeName.setEnabled(value);
		txtWebSite.setEnabled(value);
		txtPhone.setEnabled(value);
		txtRegisterNumber.setEnabled(value);
		txtFax.setEnabled(value);		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (SupplierEditorInput)input;
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dateRegister",dateRegisterCal);
		map.put("companyName",txtCompanyName);
		map.put("tradeName",txtTradeName);
		map.put("socialSecurity",txtSocialSecurity);
		map.put("registerNumber",txtRegisterNumber);
		map.put("cityRegister",txtCityRegister);
		map.put("phone",txtPhone);
		map.put("fax",txtFax);
		map.put("website",txtWebSite);		
		return map;
	}
	
	class ActiveListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Button button = (Button)e.widget;
			button.setText(btActive.getSelection() ? Messages.getMessage("app.active") : Messages.getMessage("app.inactive"));
		}
	}

}
