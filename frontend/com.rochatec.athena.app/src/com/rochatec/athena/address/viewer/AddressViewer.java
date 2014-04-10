package com.rochatec.athena.address.viewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.rochatec.athena.address.converter.AddressConverter;
import com.rochatec.athena.address.dialog.AddressDialog;
import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Address;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.framework.bind.Editable;
import com.rochatec.framework.converter.IConverter;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.gui.TablePropertieViewer;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.LayoutFactory;

public class AddressViewer implements ISelectionProvider{

	private TablePropertieViewer propertieViewer;
	private Address address;
	protected ListenerList listeners;
	private ImageHyperlink linkAdd;
	private Editable editable;

	public AddressViewer(Composite parent,int style) {
		listeners = new ListenerList();
		createContents(parent,style);
	}
	
	public AddressViewer(Composite parent,int style,Editable editable) {
		listeners = new ListenerList();
		this.editable = editable;
		createContents(parent,style);
	}

	public void setInput(Address address){
		this.address = address;
		IConverter<Address> converter = new AddressConverter();
		propertieViewer.setInput(converter.toPropertyList(address));
		linkAdd.setText(Messages.getMessage("address.edit"));
	}
	
	public Address getAddress() {
		return address;
	}
	
	private void createContents(Composite parent,int style) {	
		createLink(parent);
		createTableViewer(parent,style);
	}
	
	private void createTableViewer(Composite parent,int style){
		Composite composite = new Composite(parent, style);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		propertieViewer = new  TablePropertieViewer(composite);
		propertieViewer.setLayoutDate(new GridData(SWT.FILL,SWT.FILL,true,true));
	}
	
	private void createLink(Composite parent){
		Composite composite = new Composite(parent,SWT.WRAP);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1,5));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		linkAdd = new ImageHyperlink(composite, SWT.NONE);
		linkAdd.setUnderlined(false);
		linkAdd.setForeground(Colors.getColorBlue());
		linkAdd.setImage(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_ADDRESS_32).createImage());
		linkAdd.setText(Messages.getMessage("address.new"));
		linkAdd.addHyperlinkListener(new AddAddressListener());
	}	
	
	class AddAddressListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			AddressDialog dialog = new AddressDialog(propertieViewer.getActiveShell(),editable);
			Address address = dialog.dialog();
			setInput(address);
			firechanged(address);
		}
	}
	
	private void firechanged(Address address){
		SearchSelection<Address> selection = new SearchSelection<>(address);
		SelectionChangedEvent event = new SelectionChangedEvent(this,selection);		
		for (Object listener : listeners.getListeners()){
			((ISelectionChangedListener)listener).selectionChanged(event);
		}
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners == null)
			listeners = new ListenerList();
		listeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		return new SearchSelection<Address>(addresses);
	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		Address address = (Address)((IStructuredSelection)selection).getFirstElement();
		setInput(address);
	}
}
