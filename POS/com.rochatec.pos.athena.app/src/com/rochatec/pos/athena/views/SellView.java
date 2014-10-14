package com.rochatec.pos.athena.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.graphics.util.WidgetUtils;
import com.rochatec.pos.athena.i18n.Message;

public class SellView extends ViewPart{
	
	public static final String ID = "com.rochatec.pos.athena.views.SellView";
	
	private FormToolkit toolkit;
	private ScrolledForm form;
	private CLabel lblQuantity;
	private CLabel lblPrice;
	private StyledText styledText;
	private CLabel lblSubTotal;
	private StringBuilder builder = new StringBuilder();

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.getBody().setLayout(new GridLayout(1,false));
		toolkit.decorateFormHeading(form.getForm());
		createHeader(form.getBody());
		createPanels(form.getBody());
		WidgetUtils.backgroundEquals(form.getBody());
		
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
		createItemBox(leftPanel);
	}
	
	
	private void createRightComposite(Composite parent){
		Composite righPanel = new Composite(parent,SWT.NONE );
		righPanel.setLayout(new GridLayout(1,false));
		righPanel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		createCupom(righPanel);
		createSubTotalComposite(righPanel);
	}
	
	
	private void createItemBox(Composite parent){
		Group group = new Group(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(1,true);
		layout.marginTop = 10;
		layout.marginLeft = 25;
		layout.marginRight = 25;
		layout.marginBottom = 10;
		group.setLayout(layout);
		group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		
		Composite compositeLabel = new Composite(group, SWT.NONE);
		compositeLabel.setLayout(new GridLayout(3,true));
		compositeLabel.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
		new CLabel(compositeLabel, SWT.NONE|SWT.CENTER).setText(Message.getMessage("label.quantity"));
		new CLabel(compositeLabel, SWT.NONE|SWT.CENTER);
		new CLabel(compositeLabel, SWT.NONE|SWT.CENTER).setText(Message.getMessage("label.price"));
		
		for (Control control : compositeLabel.getChildren()){
			if (control instanceof CLabel){
				((CLabel)control).setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
			}
		}
		
		Composite compositeValues = new Composite(group, SWT.NONE);
		compositeValues.setLayout(new GridLayout(3,false));
		compositeValues.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
		lblQuantity = new CLabel(compositeValues,SWT.FLAT);
		lblQuantity.setAlignment(SWT.LEFT);		
		lblQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		lblQuantity.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));		
		lblQuantity.setText("0");
		
		CLabel cLabel =new CLabel(compositeValues, SWT.FLAT|SWT.CENTER);
		cLabel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
		cLabel.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
		cLabel.setText("X");
		
		lblPrice = new CLabel(compositeValues, SWT.FLAT|SWT.RIGHT_TO_LEFT);
		lblPrice.setAlignment(SWT.RIGHT);
		lblPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		lblPrice.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
		lblPrice.setText("0,00");
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
		new CLabel(group,SWT.RIGHT_TO_LEFT).setText(Message.getMessage("label.subtotal"));
		lblSubTotal = new CLabel(group, SWT.FLAT);
		lblSubTotal.setFont(FontToolkit.getInstance().getTahoma(40,SWT.BOLD));
		lblSubTotal.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
	}
	
	public void setSubTotal(String value){
		this.lblSubTotal.setText(value);
	}
	
	public void addLine(String value){
		builder.append(value+"\n");
		this.styledText.setText(builder.toString());
	}
	
	public void setQuantity(String quantity){
		this.lblQuantity.setText(quantity);
	}
	
	public void setSellPrice(String price){
		this.lblPrice.setText(price);
	}
	@Override
	public void setFocus() {
		
		
	}

}
