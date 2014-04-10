package com.rochatec.athena.manufacture.category.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.gui.IdLabel;
import com.rochatec.graphics.util.LayoutFactory;

public class CategoryEditor extends AbstractEditor {
	
	public static final String ID = "com.rochatec.metallurgical.manufacture.category.editor.CategoryEditor";
	private CategoryEditorInput editorInput;
	private IdLabel idLabel;
	private Text txtName;
	protected ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
			
	@Override
	public void makeActions(IToolBarManager toolBarManager) {
		toolBarManager.add(ActionFactory.SAVE.create(getSite().getWorkbenchWindow()));	
	}

	@Override
	protected void createContents(Composite parent) {
		createFields(parent);
	}
	
	private void createFields(Composite parent) {
		Composite composite = new Group(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		 idLabel = new IdLabel(composite);
		
		Composite panel = new Composite(composite,SWT.NONE);
		panel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		panel.setLayout(LayoutFactory.getInstance().getGridLayout(1,10));

		new Label(panel, SWT.NONE).setText(Messages.getMessage("category.field.label.id"));
		
		txtName = new Text(panel, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		txtName.addModifyListener(new DirtyListener());
	}

	@Override
	protected void addListeners() {
		
		
	}

	@Override
	protected void fill() {
		if (editorInput.getCategory().getId() != null){			
			txtName.setText(editorInput.getCategory().getName());
			idLabel.setLabelText(editorInput.getCategory().getId());
		}
	}
	
	@Override
	public String getPartName() {
		return Messages.getMessage("category.editor.title");
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (isDirty()){
			Category category = editorInput.getCategory();
			category.setName(txtName.getText());
			category = manufactureClientService.persist(category);
			idLabel.setLabelText(category.getId());
			setDirty(false);
			setDisable(true);
		}		
	}
	
	private void setDisable(boolean value){
		txtName.setEnabled(value);		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (CategoryEditorInput)input;
		
	}
	
	@Override
	public void setFocus() {
		txtName.setFocus();
	}
	
	class DirtyListener implements ModifyListener {
		@Override
		public void modifyText(ModifyEvent e) {
			setDirty(true);	        	
		}		
	}

}
