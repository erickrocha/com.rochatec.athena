package com.rochatec.athena.humanresources.job.editor;

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

import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.util.LayoutFactory;

public class JobEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.athena.humanresources.job.editor.JobEditor";
	
	protected HumanResourceClientService humanResourceClientService = ServiceFactory.getInstance().getHumanResourceClientService();
	
	private IdLabel idLabel;
	private Text txtName;
	private JobEditorInput editorInput;
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (JobEditorInput)input;
	}
	
	@Override
	protected void createContents(Composite parent) {		
		createHeader(parent);
		DataBindingFactory<Job> bindingFactory = new DataBindingFactory<Job>(editorInput.getJob(),this);
		bindingFactory.bind(getBinds());
	}
	
	private void createHeader(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(1,10));
		
		new Label(panel, SWT.NONE).setText(Messages.getMessage("job.field.name.label"));
		txtName = new Text(panel, SWT.BORDER);
		txtName.setTextLimit(50);
		txtName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	@Override
	protected void addListeners() {

	}
	
	@Override
	public void setFocus() {
		txtName.setFocus();
	}	
	
	@Override
	public String getPartName() {		
		return Messages.getMessage("job.editor.title");
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {		
		if (isDirty()){
			Job job = editorInput.getJob();
			job.setName(txtName.getText());
			job = humanResourceClientService.persist(job);
			idLabel.setLabelText(job.getId());
			setDirty(false);
			setEnable(false);
		}		
	}
	
	private void setEnable(boolean value){
		txtName.setEnabled(value);		
	}

	@Override
	protected void fill() {
		if (editorInput.getJob().getId() != null){			
			idLabel.setLabelText(editorInput.getJob().getId());
		}
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name",txtName);
		return map;
	}

}
