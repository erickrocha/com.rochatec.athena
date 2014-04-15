package com.rochatec.athena.address.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.rochatec.athena.address.province.provider.ProvinceLabelProvider;
import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.ResourceClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Address;
import com.rochatec.athena.model.Province;
import com.rochatec.athena.util.DataBindingFactory;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.bind.Bindable;
import com.rochatec.framework.bind.Editable;
import com.rochatec.graphics.dialog.AbstractInputDialog;
import com.rochatec.graphics.gui.MaskedText;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.util.LayoutFactory;

public class AddressDialog extends AbstractInputDialog<Address> implements Bindable {

	private Text txtStreet;
	private Text txtStreetNumber;
	private MaskedText txtZipCode;
	private Text txtComplement;
	private Text txtNeighborhood;
	private Text txtCity;
	private ComboViewer viewerProvince;
	private Province province;
	private Editable editable;

	private ResourceClientService resourceClientService = ServiceFactory.getInstance().getResourceClientService();;

	public AddressDialog(Shell owner) {
		super(owner);
	}
	
	public AddressDialog(Shell owner,Editable editable){
		this(owner,editable,new Address());
	}
	
	public AddressDialog(Shell owner,Editable editable,Address selected) {
		super(owner);
		this.editable = editable;
		this.selected = selected;
	}
	
	@Override
	protected void createComponents(Composite parent) {
		setFormImage(Activator.getImageDescriptor(IPathIcons.INFRA_ADD_ADDRESS_48).createImage());
		createStreetFields(parent);
		createComplement(parent);
		createCityandProvinceFields(parent);
		DataBindingFactory<Address> factory = new DataBindingFactory<Address>(selected, editable);
		factory.bind(getBinds());
	}

	
	@Override
	protected Point getInitialSize() {
		return new Point(800, 350);
	}

	private void createStreetFields(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("address.field.label.zipcode"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("address.field.label.street"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("address.field.label.streetNumber"));

		txtZipCode = new MaskedText(composite, Formatter.getZipCode());
		txtZipCode.setLayoutData(new GridData(150, 15));
		txtStreet = new Text(composite, SWT.BORDER);
		txtStreet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		txtStreetNumber = new Text(composite, SWT.BORDER);
		txtStreetNumber.setLayoutData(new GridData(100, 15));
	}

	private void createComplement(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		new Label(composite, SWT.NONE).setText(Messages.getMessage("address.field.label.complement"));
		txtComplement = new Text(composite, SWT.BORDER);
		txtComplement.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,false));
	}

	private void createCityandProvinceFields(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		new Label(composite, SWT.NONE).setText(Messages.getMessage("address.field.label.neighborhood"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("address.field.label.city"));
		new Label(composite, SWT.NONE).setText(Messages.getMessage("address.field.label.province"));

		txtNeighborhood = new Text(composite, SWT.BORDER);
		txtNeighborhood.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,false));

		txtCity = new Text(composite, SWT.BORDER);
		txtCity.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		viewerProvince = new ComboViewer(new CCombo(composite, SWT.DROP_DOWN| SWT.READ_ONLY | SWT.BORDER));
		viewerProvince.setContentProvider(new GenericContentProvider<Province>());
		viewerProvince.setLabelProvider(new ProvinceLabelProvider());
		viewerProvince.setInput(resourceClientService.findAllProvinces());
	}

	@Override
	public String getTitle() {		
		return Messages.getMessage("address.title");
	}

	@Override
	public void save() {
		selected = new Address();
		selected.setStreet(txtStreet.getText());
		selected.setZipcode(txtZipCode.getValue());
		selected.setAddressNumber(txtStreetNumber.getText());
		selected.setComplement(txtComplement.getText());
		selected.setCity(txtCity.getText());
		selected.setNeighborhood(txtNeighborhood.getText());
		selected.setProvince(province);
		close();
	}
	
	class ProvinceSelectionChangedListener implements ISelectionChangedListener{

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection selection = (IStructuredSelection)event.getSelection();
			province = (Province)selection.getFirstElement();			
		}
		
	}

	@Override
	public Map<String, Object> getBinds() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("street", txtStreet);
		map.put("addressNumber", txtStreetNumber);
		map.put("complement", txtComplement);
		map.put("neighborhood", txtNeighborhood);
		map.put("city", txtCity);
		map.put("province",viewerProvince);
		map.put("zipcode",txtZipCode);
		return map;
	}

}
