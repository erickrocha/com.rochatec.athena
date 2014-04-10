package com.rochatec.athena.security.profile.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
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
import com.rochatec.athena.client.service.SecurityClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.model.Role;
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
import com.rochatec.graphics.util.LayoutFactory;

public class ProfileEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.metallurgical.security.profile.editor.ProfileEditor";
	protected ProfileEditorInput editorInput;
	protected SecurityClientService securityClientService = ServiceFactory.getInstance().getSecurityClientService();
	
	protected IdLabel idLabel;
	
	protected Text txtName;
	protected Text txtLabel;
	private ImageHyperlink linkCheckAll;	
	private RoleTable roleTable;
	
	@Override
	protected void createContents(Composite parent) {
		createHeader(form.getBody());
		createRoleBox(form.getBody());
		DataBindingFactory<Profile> factory = new DataBindingFactory<Profile>(editorInput.getProfile(),this);
		factory.bind(getBinds());
	}
	
	private void createHeader(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(2,10));
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("profile.field.label.name"));
		new Label(panel, SWT.NONE).setText(Messages.getMessage("profile.field.label.label"));
		
		txtName = new Text(panel,SWT.BORDER);
		txtName.setLayoutData(new GridData(200,15));
		txtName.setTextLimit(30);
		txtLabel = new Text(panel,SWT.BORDER);
		txtLabel.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		txtLabel.setTextLimit(60);
	}
	
	private void createRoleBox(Composite parent){
		Composite panel = new Composite(parent,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(2,5));
		
		linkCheckAll = new ImageHyperlink(panel,SWT.NONE);
		linkCheckAll.setImage(Activator.getImageDescriptor(IPathIcons.CHECK_ALL_16).createImage());
		linkCheckAll.setText(Messages.getMessage("app.checkall"));
		linkCheckAll.setUnderlined(false);
		linkCheckAll.setLayoutData(new GridData(SWT.LEFT,SWT.FILL,true,false));
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
		if (editorInput.getProfile().getId() != null){
			Profile profile = editorInput.getProfile();
			idLabel.setLabelText(profile.getId());
			roleTable.setCheckeds(profile.getRoles());
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		Profile profile = editorInput.getProfile();
		profile.setRoles(roleTable.getCheckeds());
		profile = securityClientService.persist(profile);
		idLabel.setLabelText(profile.getId());
		setDirty(false);
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

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (ProfileEditorInput)input;
	}
	
	class CheckAllListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			allRolesCheckeds();
		}
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name",txtName);
		map.put("label",txtLabel);
		return map;
	}

}
