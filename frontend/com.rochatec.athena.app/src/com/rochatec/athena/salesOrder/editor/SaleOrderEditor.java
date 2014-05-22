package com.rochatec.athena.salesOrder.editor;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.components.viewer.ItemViewer;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.util.StatusTradutor;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.graphics.editor.AbstractEditor;
import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.graphics.util.LayoutFactory;

public class SaleOrderEditor extends AbstractEditor implements Bindable{
	
	public static final String ID = "com.rochatec.athena.salesOrder.editor.SaleOrderEditor";
	private SaleOrderEditorInput editorInput;
	protected CLabel txtEmployeeName;
	protected DateChooserCombo dateValidityCal;
	protected DateChooserCombo dateDeliveryCal;
	protected CLabel  txtStatus;
	protected Text txtObservation;
	protected CLabel lblTotal;	
	
	@Override
	protected void createContents(Composite parent) {
		createInfoBox(parent);
		createItemBox(parent);
	}	
	
	protected void createInfoBox(Composite parent){
		Composite composite = new Group(parent,SWT.NONE);		
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2,0));		
		createOtherFields(composite);
		createObservationField(composite);		
		createFooter(parent);		
	}
	
	private void createOtherFields(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);		
		composite.setLayoutData(new GridData(200,230));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("saleOrder.editor.label.employee"));
		txtEmployeeName = new CLabel(composite, SWT.FLAT);
		txtEmployeeName.setFont(FontToolkit.getInstance().getTahoma(12,SWT.BOLD));
		txtEmployeeName.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("saleOrder.editor.label.date.validity"));
		dateValidityCal = new DateChooserCombo(composite,SWT.BORDER);
		dateValidityCal.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("saleOrder.editor.label.date.delivery"));
		dateDeliveryCal = new DateChooserCombo(composite,SWT.BORDER);
		dateDeliveryCal.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("saleOrder.editor.label.status"));
		txtStatus = new CLabel(composite, SWT.BORDER);
		txtStatus.setFont(FontToolkit.getInstance().getTahoma(12,SWT.BOLD));
		txtStatus.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));		
	}
	
	private void createObservationField(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);		
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("saleOrder.editor.label.observation"));
		txtObservation = new Text(composite, SWT.MULTI|SWT.BORDER);
		txtObservation.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
	}
	
	private void createFooter(Composite parent){
		Composite composite = new Composite(parent,SWT.BORDER);		
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL ,true,false));
		CLabel lblText = new CLabel(composite, SWT.NONE);
		lblText.setLayoutData(new GridData(SWT.RIGHT,SWT.RIGHT,false,false)); 
		lblText.setFont(FontToolkit.getInstance().getTahoma(30,SWT.NORMAL));
		lblText.setText(Messages.getMessage("saleOrder.info.total"));
		
		lblTotal = new CLabel(composite, SWT.RIGHT|SWT.RIGHT_TO_LEFT);
		lblTotal.setFont(FontToolkit.getInstance().getTahomaHeader());
		lblTotal.setLayoutData(new GridData(SWT.RIGHT,SWT.RIGHT,true,false));
		lblTotal.setAlignment(SWT.RIGHT);
		lblTotal.setText("0,00");		
	}
	
	private void createItemBox(Composite parent){
		new ItemViewer(parent);
		
	}

	@Override
	protected void addListeners() {
		
		
	}

	@Override
	protected void fill() {
		if (editorInput.getSaleOrder().getId() != null){
			SaleOrder saleOrder = editorInput.getSaleOrder();
			txtStatus.setText(StatusTradutor.getLabel(saleOrder.getStatus()));
			txtEmployeeName.setText(saleOrder.getEmployee().getName());
		}		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		this.editorInput = (SaleOrderEditorInput)input;		
	}
	
	@Override
	public Map<String, Object> getBinds() {
		
		return null;
	}

}
