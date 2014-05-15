package com.rochatec.athena.invoice.input.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Company;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.TypePattern;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.framework.bind.Editable;
import com.rochatec.graphics.gui.TextField;
import com.rochatec.graphics.util.LayoutFactory;

public class InvoiceInputReceiverViewer implements Bindable{
	
	private Editable editable;
	private Company company;
	private Text txtCompanyName;
	private TextField txtCompanySocialSecurity;
	private Text txtCompanyRegisterNumber;
	private Text txtCompanyInscMunicipal;
	private Group container;
	
	
	public InvoiceInputReceiverViewer(Composite parent,int style,Company company,Editable editable) {
		this.company = company;
		this.editable = editable;
		createContents(parent);
	}
	
	public InvoiceInputReceiverViewer(Composite parent,Company company,Editable editable) {
		this(parent,SWT.NONE,company, editable);
	}
	
	private void createContents(Composite parent){
		container  = new Group(parent,SWT.NONE);
		container.setText(Messages.getMessage("invoice.receiver.field.label"));
		container.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		container.setLayout(LayoutFactory.getInstance().getGridLayout(4,false,5,10));		
		
		new Label(container, SWT.NONE).setText(Messages.getMessage("invoice.receiver.companyName.field.label"));
		new Label(container, SWT.NONE).setText(Messages.getMessage("invoice.receiver.socialSecurity.field.label"));
		new Label(container, SWT.NONE).setText(Messages.getMessage("invoice.receiver.registerNumber.field.label"));
		new Label(container, SWT.NONE).setText(Messages.getMessage("invoice.receiver.cityRegister.field.label"));
		
		txtCompanyName = new Text(container, SWT.BORDER);
		txtCompanyName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));		
		txtCompanySocialSecurity = new TextField(container,TypePattern.SOCIAL_SECURITY.ordinal());
		txtCompanySocialSecurity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		txtCompanyRegisterNumber = new Text(container, SWT.BORDER);
		txtCompanyRegisterNumber.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		txtCompanyInscMunicipal = new Text(container, SWT.BORDER);
		txtCompanyInscMunicipal.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		
		DataBindingFactory<Company> factory = new DataBindingFactory<Company>(company, editable);
		factory.bind(getBinds());
	}
	
	public Company getCompany(){
		return company;
	}
	
	public void setEnabled(boolean enabled){
		this.container.setEnabled(false);
	}
	
	public void setTitle(String value){
		this.container.setText(value);
	}
	
	public void setCompany(Company company){
		this.company = company;
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("companyName",txtCompanyName);
		map.put("socialSecurity",txtCompanySocialSecurity);
		map.put("registerNumber",txtCompanyRegisterNumber);
		map.put("cityRegister",txtCompanyInscMunicipal);
		return map;
	}

}
