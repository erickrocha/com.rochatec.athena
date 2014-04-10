package com.rochatec.athena.supply.shipper.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
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
import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.framework.formater.impl.PhoneFormaterImpl;
import com.rochatec.framework.formater.impl.SocialSecurityFormaterImpl;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.gui.MaskedText;
import com.rochatec.graphics.util.LayoutFactory;

public class ShipperEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.metallurgical.supply.shipper.editor.ShipperEditor";
	private ShipperEditorInput editorInput;
	private SupplyClientService supplyClientService = ServiceFactory.getInstance().getSupplyClientService();
	
	protected IdLabel idLabel;
	
	protected DateChooserCombo dateRegisterCal;
	protected Text txtBusinessName;
	protected Text txtComercialName;
	protected MaskedText txtSocialSecurity;
	protected Text txtRegisterNumber;
	protected Text txtCityRegister;
	protected Text txtEmail;
	protected MaskedText txtPhone;
	protected MaskedText txtFax;
	
	protected AddressViewer addressViewer;
	
	@Override
	protected void createContents(Composite parent) {
		createHeader(form.getBody());
		createShortnameField(form.getBody());
		createFields(form.getBody());
		createAddressViewer(form.getBody());
		DataBindingFactory<Shipper> factory = new DataBindingFactory<Shipper>(editorInput.getShipper(),this);
		factory.bind(getBinds());
	}
	
	private void createHeader(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(3,10));
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("shipper.field.label.dateRegister"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("shipper.field.label.socialSecurity"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("shipper.field.label.businessName"));
		
		dateRegisterCal = new DateChooserCombo(panel,SWT.BORDER);
		dateRegisterCal.setLayoutData(new GridData(120,25));
		dateRegisterCal.setValue(new java.util.Date());
		
		txtSocialSecurity = new MaskedText(panel,new SocialSecurityFormaterImpl());
		txtSocialSecurity.setLayoutData(new GridData(120,17));
		
		txtBusinessName = new Text(panel, SWT.BORDER);
		txtBusinessName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createShortnameField(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("shipper.field.label.tradeName"));
		
		txtComercialName = new Text(composite, SWT.BORDER);
		txtComercialName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createFields(Composite parent){
		Group composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(5,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("shipper.field.label.registerNumber"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("shipper.field.label.cityRegister"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("shipper.field.label.phone"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("shipper.field.label.fax"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("shipper.field.label.email"));
		
		txtRegisterNumber = new Text(composite, SWT.BORDER);
		txtRegisterNumber.setLayoutData(new GridData(200,15));
		
		txtCityRegister = new Text(composite,SWT.BORDER);
		txtCityRegister.setLayoutData(new GridData(200,15));
		
		txtPhone = new MaskedText(composite, new PhoneFormaterImpl());
		txtPhone.setLayoutData(new GridData(200,15));
		
		txtFax = new MaskedText(composite, new PhoneFormaterImpl());
		txtFax.setLayoutData(new GridData(200,15));
		
		txtEmail = new Text(composite, SWT.BORDER);
		txtEmail.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createAddressViewer(Composite parent){
		addressViewer = new AddressViewer(parent,SWT.NONE,this);
	}
	
	@Override
	protected void addListeners() {
		
	}

	@Override
	protected void fill() {
		if (editorInput.getShipper().getId() != null){
			Shipper shipper = editorInput.getShipper();
			addressViewer.setInput(shipper.getAddress());
			idLabel.setLabelText(shipper.getId());
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		Shipper shipper = editorInput.getShipper();
		shipper = supplyClientService.persist(shipper);
		idLabel.setLabelText(shipper.getId());
		setDirty(false);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (ShipperEditorInput)input;
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("businessName",txtBusinessName);
		map.put("comercialName",txtComercialName);
		map.put("cityRegister",txtCityRegister);
		map.put("email",txtEmail);
		map.put("fax",txtFax);
		map.put("phone",txtPhone);
		map.put("registerNumber",txtRegisterNumber);
		map.put("socialSecurity",txtSocialSecurity);
		map.put("dateRegister",dateRegisterCal);		
		return map;
	}

}
