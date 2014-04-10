package com.rochatec.athena.humanresources.employee.editor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
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
import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.humanresources.job.provider.JobLabelProvider;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.framework.formater.impl.PhoneFormaterImpl;
import com.rochatec.framework.formater.impl.SocialSecurityFormaterImpl;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.gui.MaskedText;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.LayoutFactory;

public class EmployeeEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.metallurgical.humanresources.employee.editor.EmployeeEditor";

	private EmployeeEditorInput editorInput;
	protected IdLabel idLabel;
	protected DateChooserCombo hiredateCal;
	protected Text txtName;
	protected MaskedText txtSocialSecurity;
	protected Button btActive;
	
	protected TableComboViewer jobViewer; 
	protected Text txtRegisterNumber;
	protected Text txtEmail;
	protected MaskedText txtHomePhone;
	protected MaskedText txtCellPhone;
	
	protected AddressViewer addressViewer;
	
	protected HumanResourceClientService humanResourceClientService = ServiceFactory.getInstance().getHumanResourceClientService();
	
	@Override
	public void makeActions(IToolBarManager toolBarManager) {
		
	}

	@Override
	protected void createContents(Composite parent) {
		createHeader(form.getBody());
		createFields(form.getBody());
		createAddressViewer(form.getBody());				
		DataBindingFactory<Employee> factory = new DataBindingFactory<Employee>(editorInput.getEmployee(),this);
		factory.bind(getBinds());
	}
	
	private void createHeader(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(4,10));
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("employee.field.label.hiredate"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("employee.field.label.socialsecurity"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("employee.field.label.name"));
		new Label(panel, SWT.NONE);
		
		hiredateCal = new DateChooserCombo(panel,SWT.BORDER);
		hiredateCal.setLayoutData(new GridData(120,25));
		hiredateCal.setValue(new Date());
		
		txtSocialSecurity = new MaskedText(panel,new SocialSecurityFormaterImpl());
		txtSocialSecurity.setLayoutData(new GridData(150,17));
		
		txtName = new Text(panel, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		btActive = new Button(panel,SWT.CHECK);
		btActive.setText( Messages.getMessage("app.active"));
		btActive.setSelection(true);
		btActive.addSelectionListener(new ActiveListener());
		
	}
	
	private void createFields(Composite parent){
		Group composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(5,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("employee.field.label.job"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("employee.field.label.registerNumber"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("employee.field.label.homephone"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("employee.field.label.cellphone"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("employee.field.label.email"));
		
		jobViewer = new TableComboViewer(new TableCombo(composite, SWT.DROP_DOWN|SWT.BORDER));
		jobViewer.setContentProvider(new ObservableListContentProvider());
		jobViewer.setLabelProvider(new JobLabelProvider());
		jobViewer.getTableCombo().defineColumns(2);
		jobViewer.getTableCombo().setDisplayColumnIndex(1);
		jobViewer.getTableCombo().setLayoutData(new GridData(250,20));
		jobViewer.addSelectionChangedListener(new JobSelectedListener());
		
		txtRegisterNumber = new Text(composite, SWT.BORDER);
		txtRegisterNumber.setLayoutData(new GridData(200,15));
		
		txtHomePhone = new MaskedText(composite, new PhoneFormaterImpl());
		txtHomePhone.setLayoutData(new GridData(200,15));
		
		txtCellPhone = new MaskedText(composite, new PhoneFormaterImpl());
		txtCellPhone.setLayoutData(new GridData(200,15));
		
		txtEmail = new Text(composite, SWT.BORDER);
		txtEmail.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	private void createAddressViewer(Composite parent) {
		addressViewer = new AddressViewer(parent,SWT.BORDER);		
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	protected void fill() {
		WritableList writableList = new WritableList(humanResourceClientService.findAllJobs(), Job.class);
		jobViewer.setInput(writableList);
		if (editorInput.getEmployee().getId() != null){
			Employee employee = editorInput.getEmployee();
			addressViewer.setInput(employee.getAddress());
			jobViewer.setSelection(new SearchSelection<Job>(employee.getJob()));
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (isDirty()){
			Employee employee = editorInput.getEmployee();				
			employee.setActive(btActive.getSelection());
			employee.setAddress(addressViewer.getAddress());
			employee = humanResourceClientService.persist(employee);
			idLabel.setLabelText(employee.getId());
			setDirty(false);
			setEnabled(true);			
		}		
	}
	
	public void setEnabled(boolean value){
		txtName.setEnabled(value);
		hiredateCal.setEnabled(value);
		txtSocialSecurity.setEnabled(value);
		txtCellPhone.setEnabled(value);
		txtEmail.setEnabled(value);
		txtHomePhone.setEnabled(value);
		txtRegisterNumber.setEnabled(value);
		jobViewer.getTableCombo().setEnabled(value);		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput =  (EmployeeEditorInput)input;
	}
	
	class ActiveListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Button button = (Button)e.widget;
			button.setText(btActive.getSelection() ? Messages.getMessage("app.active") : Messages.getMessage("app.inactive"));
		}
	}
	
	class JobSelectedListener implements ISelectionChangedListener{

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			Job job = (Job)((IStructuredSelection)event.getSelection()).getFirstElement();
			editorInput.getEmployee().setJob(job);
			setDirty(true);
		}		
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name",txtName);
		map.put("hiredate",hiredateCal);
		map.put("socialSecurity",txtSocialSecurity);
		map.put("registerNumber",txtRegisterNumber);
		map.put("homePhone",txtHomePhone);
		map.put("cellPhone",txtCellPhone);
		map.put("email",txtEmail);
		map.put("job",jobViewer);
		return map;
	}

}
