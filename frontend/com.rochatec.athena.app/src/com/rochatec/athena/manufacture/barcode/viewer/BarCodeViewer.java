package com.rochatec.athena.manufacture.barcode.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.barcode.provider.BarCodeLabelProvider;
import com.rochatec.athena.model.BarCode;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.util.LayoutFactory;

public class BarCodeViewer {

	private Composite container;
	private Text txtBarcode;
	private ImageHyperlink button;
	private ListViewer viewer;
	private List<BarCode> barCodes = new ArrayList<BarCode>();
		
	public BarCodeViewer(Composite parent) {
		this.container = new Composite(parent,SWT.BORDER);
		createContents();
	}
	
	private void createContents(){
		container.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		createFields(container);
		createViewer(container);
	}
	
	private void createFields(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(2));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		new Label(composite, SWT.NONE).setText(Messages.getMessage("barcodeViewer.barcode.label"));
		new Label(composite, SWT.NONE);
		
		txtBarcode = new Text(composite, SWT.BORDER);
		txtBarcode.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		button = new ImageHyperlink(composite, SWT.NONE);
		button.setUnderlined(false);
		button.setImage(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_24).createImage());
		button.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true,0,2));
		button.addHyperlinkListener(new AddBarCodeListener());
	}
	
	private void createViewer(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,true));
		viewer = new ListViewer(composite,SWT.BORDER|SWT.SINGLE);
		viewer.setContentProvider(new GenericContentProvider<BarCode>());
		viewer.setLabelProvider(new BarCodeLabelProvider());
	}
	
	public void setLayoutData(GridData gridData){
		this.container.setLayoutData(gridData);
	}
	
	public void addBarCode(BarCode barCode){
		if (this.barCodes == null)
			this.barCodes = new ArrayList<BarCode>();
		barCodes.add(barCode);
		viewer.setInput(barCodes);
	}
	
	public void removeBarcode(BarCode barCode){
		if (barCodes.contains(barCode))
			barCodes.remove(barCode);
		viewer.setInput(barCodes);
	}
	
	public List<BarCode> getBarCodes(){
		return this.barCodes;
	}
	
	public void setBarCodes(List<BarCode> barCodes){
		this.barCodes = barCodes;
		viewer.setInput(barCodes);
	}
	
	class AddBarCodeListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			BarCode barCode = new BarCode();
			barCode.setBarcode(txtBarcode.getText().trim());
			addBarCode(barCode);
			txtBarcode.setText("");
		}
	}
	
	class RemoveBarCodeListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.character == SWT.DEL){
				if (MessageDialog.openConfirm(e.display.getActiveShell(),
						Messages.getMessage("barcodeViewer.delete.title"),
						Messages.getMessage("barcodeViewer.delete.message"))){
						BarCode barCode = (BarCode) ((IStructuredSelection)viewer.getSelection()).getFirstElement();
						removeBarcode(barCode);
				}
			}
		}
	}	
	
}
