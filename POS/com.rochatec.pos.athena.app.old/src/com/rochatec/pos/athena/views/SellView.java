package com.rochatec.pos.athena.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.graphics.util.WidgetUtils;
import com.rochatec.pos.athena.i18n.Message;
import com.rochatec.pos.athena.tools.GridLayoutBuilder;

public class SellView extends ViewPart{
	
	public static final String ID = "com.rochatec.pos.athena.views.SellView";
	
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Text txtQuantity;
	private Text txtPrice;
	private StyledText styledText;
	private Text txtSubTotal;
	private Text txtItemLabel;
	private StringBuilder builder = new StringBuilder();
	private Text txtProduct;

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.getBody().setLayout(new GridLayout(1,false));
		toolkit.decorateFormHeading(form.getForm());
		createHeader(form.getBody());
		createPanels(form.getBody());		
		WidgetUtils.backgroundEquals(form.getBody());
		createFooter(form.getBody());
	}

	private void createHeader(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginLeft = 50;
		layout.marginRight = 50;
		layout.marginTop = 5;
		layout.marginBottom = 5;
		composite.setLayout(layout);
		Form form = new Form(composite, SWT.NONE);
		form.setFont(FontToolkit.getInstance().getTahoma(40,SWT.BOLD));
		form.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		toolkit.decorateFormHeading(form);
	}
	
	private void createPanels(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		composite.setLayout(new GridLayout(2,true));
		createLeftComposite(composite);
		createRightComposite(composite);
	}

	private void createLeftComposite(Composite parent) {
		Composite leftPanel = new Composite(parent, SWT.NONE);
		leftPanel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		leftPanel.setLayout(new GridLayout(1,false));	
		createItemLabel(leftPanel);
		createItemBox(leftPanel);
	}
	
	
	private void createRightComposite(Composite parent){
		Composite righPanel = new Composite(parent,SWT.NONE );
		righPanel.setLayout(new GridLayout(1,false));
		righPanel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createCupom(righPanel);
		createSubTotalComposite(righPanel);
	}
	
	private void createItemLabel(Composite parent){
		Group group = new Group(parent, SWT.BORDER);		
		group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		CLabel lblPrice = new CLabel(group, SWT.NONE);
		lblPrice.setFont(FontToolkit.getInstance().getTahomaLabel());
		lblPrice.setText(Message.getMessage("label.product.description"));
		
		txtItemLabel = new Text(group, SWT.READ_ONLY);
		txtItemLabel.setFont(FontToolkit.getInstance().getTahoma(35,SWT.BOLD));
		txtItemLabel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
	}
	
	
	private void createItemBox(Composite parent){
		Group group = new Group(parent, SWT.BORDER);		
		group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		Composite composite = new Composite(group, SWT.NONE);
		
		composite.setLayout(GridLayoutBuilder.getInstance().build(0,0,0,0,3));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		CLabel lblQuantity = new CLabel(composite,SWT.NONE);
		lblQuantity.setFont(FontToolkit.getInstance().getTahomaLabel());
		lblQuantity.setText(Message.getMessage("label.quantity"));
		new CLabel(composite, SWT.NONE|SWT.CENTER);
		CLabel lblPrice = new CLabel(composite, SWT.NONE);
		lblPrice.setFont(FontToolkit.getInstance().getTahomaLabel());
		lblPrice.setText(Message.getMessage("label.price"));
		
		composite.setLayout(new GridLayout(3,false));
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
		txtQuantity = new Text(composite,SWT.NONE|SWT.READ_ONLY);		
		txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		txtQuantity.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));		
		txtQuantity.setText("0");
		
		CLabel cLabel =new CLabel(composite, SWT.FLAT|SWT.CENTER);
		cLabel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		cLabel.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
		cLabel.setText("X");
		
		txtPrice = new Text(composite, SWT.NONE|SWT.RIGHT|SWT.READ_ONLY);
		txtPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		txtPrice.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
		txtPrice.setText("0,00");
	}
	
	private void createCupom(Composite parent){
		styledText = new StyledText(parent,SWT.BORDER|SWT.V_SCROLL);
		styledText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		styledText.setFont(FontToolkit.getInstance().getTahoma(20,SWT.NONE));
		styledText.setBackground(Colors.getColor(SWT.COLOR_INFO_BACKGROUND));
	}
	
	private void createSubTotalComposite(Composite parent){
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(1,false));
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		CLabel lblSubTotal = new CLabel(group,SWT.NONE);
		lblSubTotal.setFont(FontToolkit.getInstance().getTahomaLabel());
		lblSubTotal.setText(Message.getMessage("label.subtotal"));
		txtSubTotal = new Text(group, SWT.FLAT|SWT.RIGHT|SWT.READ_ONLY);
		txtSubTotal.setFont(FontToolkit.getInstance().getTahoma(40,SWT.BOLD));
		txtSubTotal.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));		
	}
	
	private void createFooter(Composite parent){
		Composite composite = new Composite(parent,SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		composite.setLayout(GridLayoutBuilder.getInstance().build(0,0,3));
		txtProduct = new Text(composite,  SWT.SEARCH | SWT.ICON_SEARCH | SWT.CANCEL| SWT.BORDER);
		txtProduct.setMessage("Product Search");
		GridData gridData = new GridData(SWT.RIGHT,SWT.FILL,true,false);
		gridData.minimumWidth = 250;
		txtProduct.setLayoutData(gridData);
	}
	
	public void setSubTotal(String value){
		this.txtSubTotal.setText(value);
	}
	
	public void addLine(String value){
		builder.append(value+"\n");
		this.styledText.setText(builder.toString());
	}
	
	public void setQuantity(String quantity){
		this.txtQuantity.setText(quantity);
	}
	
	public void setSellPrice(String price){
		this.txtPrice.setText(price);
	}
	@Override
	public void setFocus() {
		
		
	}

}
