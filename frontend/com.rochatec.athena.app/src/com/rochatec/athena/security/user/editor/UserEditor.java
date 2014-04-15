package com.rochatec.athena.security.user.editor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
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
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.client.service.SecurityClientService;
import com.rochatec.athena.humanresources.employee.dialog.EmployeeDialog;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.User;
import com.rochatec.athena.security.profile.provider.ProfileLabelProvider;
import com.rochatec.athena.security.role.provider.RoleLabelProvider;
import com.rochatec.athena.security.role.table.RoleTable;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.IKeyPadConstants;
import com.rochatec.graphics.util.LayoutFactory;

public class UserEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.athena.security.user.editor.UserEditor";
	protected UserEditorInput editorInput;
	protected SecurityClientService securityClientService = ServiceFactory.getInstance().getSecurityClientService();
	
	private Employee employee;	
	
	protected IdLabel idLabel;
	
	private Text txtEmployee;
	private Text txtUserName;
	private Text txtPassword;
	private ImageHyperlink linkCheckAll;
	private Button btActive;
	private ControlDecoration deco;
	
	private TableComboViewer viewerProfile;
	private RoleTable roleTable;
	
	@Override
	protected void createContents(Composite parent) {
		createHeader(form.getBody());
		createFields(form.getBody());
		createRoleBox(form.getBody());
		DataBindingFactory<User> factory = new DataBindingFactory<User>(editorInput.getUser(),this);
		factory.bind(getBinds());
	}
	
	private void createHeader(Composite	parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(2,10));
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("user.field.label.name"));
		new Label(panel, SWT.NONE);
		
		txtEmployee = new Text(panel,SWT.BORDER);
		txtEmployee.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		txtEmployee.setMessage(Messages.getMessage("user.field.decorator.employee"));
		txtEmployee.addKeyListener(new EmployeeSearchListener());
		deco = new ControlDecoration(txtEmployee,SWT.TOP|SWT.LEFT);
		deco.setDescriptionText(Messages.getMessage("user.field.decorator.employee"));
		deco.setImage(Activator.getImageDescriptor(IPathIcons.EMPLOYEE_16).createImage());
		deco.setShowOnlyOnFocus(false);
		
		btActive = new Button(panel,SWT.CHECK);
		btActive.setText(Messages.getMessage("app.active"));
		btActive.setSelection(true);
		btActive.addSelectionListener(new ActiveListener());
	}
	
	private void createFields(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(3,5));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("user.field.label.profile"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("user.field.label.username"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("user.field.label.password"));
		
		viewerProfile = new TableComboViewer(new TableCombo(composite, SWT.DROP_DOWN|SWT.BORDER|SWT.READ_ONLY));
		viewerProfile.getTableCombo().setLayoutData(new GridData(300,20));
		viewerProfile.setContentProvider(new GenericContentProvider<Profile>());
		viewerProfile.setLabelProvider(new ProfileLabelProvider());
		viewerProfile.getTableCombo().defineColumns(2);
		viewerProfile.getTableCombo().setDisplayColumnIndex(1);		
		viewerProfile.addSelectionChangedListener(new ProfileSelectedListener());
		
		txtUserName = new Text(composite,SWT.BORDER);
		txtUserName.setLayoutData(new GridData(200,15));
		txtUserName.setTextLimit(30);
		
		txtPassword = new Text(composite,SWT.BORDER|SWT.PASSWORD);
		txtPassword.setLayoutData(new GridData(200,15));
		txtPassword.setTextLimit(20);
		txtPassword.setEchoChar('*');
		
	}
	
	private void createRoleBox(Composite parent){
		Composite panel = new Composite(parent,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(2,5));
		
		linkCheckAll = new ImageHyperlink(panel,SWT.NONE);
		linkCheckAll.setImage(Activator.getImageDescriptor(IPathIcons.CHECK_ALL_16).createImage());
		linkCheckAll.setText(Messages.getMessage("app.checkall"));
		linkCheckAll.setUnderlined(false);
		linkCheckAll.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		linkCheckAll.addHyperlinkListener(new CheckAllListener());
		
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		
		roleTable = new RoleTable(composite,this);
		roleTable.setContentProvider(new GenericContentProvider<Role>());
		roleTable.setLabelProvider(new RoleLabelProvider());
		roleTable.setInput(securityClientService.findAllRoles());
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	protected void fill() {
		List<Profile> profiles = securityClientService.findAllProfiles();
		viewerProfile.setInput(profiles);		
		if (editorInput.getUser().getId() != null){
			User user = editorInput.getUser();
			viewerProfile.setSelection(new SearchSelection<Profile>(user.getProfile()));
			employee = user.getEmployee();
			roleTable.setCheckeds(user.getMergedRoles());
		}else{
			viewerProfile.setSelection(new SearchSelection<Profile>(profiles.get(0)));
			roleTable.setCheckeds(profiles.get(0).getRoles());
		}
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		User user = editorInput.getUser();
		user.setEmployee(employee);		
		user.setRoles(roleTable.getCheckeds());
		Profile profile = (Profile) ((IStructuredSelection)viewerProfile.getSelection()).getFirstElement();
		user.setProfile(profile);
		user.setActive(btActive.getSelection());
		user.setBlocked(false);
		user = securityClientService.persist(user);
		idLabel.setLabelText(user.getId());
		setDirty(true);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (UserEditorInput)input;
	}
	
	private void allRolesCheckeds(){
		if (roleTable.isCheckedAll()){
			roleTable.setCheckedAll(false);
			linkCheckAll.setText(Messages.getMessage("app.checkall"));
		}else{
			roleTable.setCheckedAll(true);
			linkCheckAll.setText(Messages.getMessage("app.uncheckall"));
		}
	}
	
	class EmployeeSearchListener extends KeyAdapter{
		HumanResourceClientService humanResourceClientService = ServiceFactory.getInstance().getHumanResourceClientService();
		
		private void search(KeyEvent event){
			Text text = (Text)event.widget;
			Long id = Long.parseLong(text.getText());
			employee = humanResourceClientService.findEmployeeById(id);
			txtEmployee.setText(employee.getName());
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.keyCode) {
			case IKeyPadConstants.KEY_ENTER:
				search(e);
				break;
			case IKeyPadConstants.KEY_ENTER_NUMERICO:
				search(e);
				break;
			case IKeyPadConstants.KEY_F9:
				EmployeeDialog dialog = new EmployeeDialog(e.display.getActiveShell());
				employee = dialog.dialog();
				txtEmployee.setText(employee != null ? employee.getName() : "");
				break;
			default:
				break;
			}
				
		}
	}
	
	class ProfileSelectedListener implements ISelectionChangedListener{

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			Profile profile = (Profile)((IStructuredSelection)event.getSelection()).getFirstElement();
			if (profile != null){
				roleTable.setCheckeds(profile.getRoles());
				allRolesCheckeds();
			}
		}
		
	}
	
	class ActiveListener extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Button button = (Button)e.widget;
			button.setText(btActive.getSelection() ? Messages.getMessage("app.active") : Messages.getMessage("app.inactive"));
		}
	}
	
	class CheckAllListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			allRolesCheckeds();
		}
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("employee.name",txtEmployee);
		map.put("password",txtPassword);
		map.put("username",txtUserName);
		map.put("profile",viewerProfile);
		return map;
	}

}
