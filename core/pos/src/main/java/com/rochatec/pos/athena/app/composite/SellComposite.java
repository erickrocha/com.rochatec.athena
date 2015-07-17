package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.utils.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * Created by epr on 02/07/15.
 */
public class SellComposite extends Composite{

    public static final String ID = "com.rochatec.pos.athena.app.composite.SellComposite";
    private AthenaApplicationWindow window;

    public SellComposite(AthenaApplicationWindow window,Composite parent,int style) {
        super(parent, style);
        this.window = window;
        createContents();
    }

    public void createContents() {
        setLayout(new GridLayout(1, true));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite body = new Composite(this,SWT.NONE);
        body.setLayout(new GridLayout(1, false));
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createSellPanel(body);
        WidgetUtils.backgroundEquals(body);
    }


    private void createSellPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.NONE);
        composite.setLayout(new GridLayout(2, true));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createLeftPanel(composite);
        createRightPanel(composite);
    }

    private void createLeftPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.BORDER);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createProductDescription(composite);
        createItemBox(composite);
    }

    private void createProductDescription(Composite parent){
        Group group = new Group(parent, SWT.BORDER);
        group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        CLabel lblDescription = new CLabel(group, SWT.NONE);
        lblDescription.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblDescription.setText(Messages.getMessage("label.product.description"));

        Text txtDescription = new Text(group, SWT.READ_ONLY);
        txtDescription.setFont(FontToolkit.getInstance().getTahoma(35, SWT.BOLD));
        txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        window.register(IAppConfig.GUI_SELL_DESCRIPTION,txtDescription);
    }


    private void createItemBox(Composite parent){
        Group group = new Group(parent, SWT.BORDER);
        group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        Composite composite = new Composite(group, SWT.NONE);

        composite.setLayout(GridLayoutBuilder.getInstance().build(0,0,0,0,3));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL_EVEN_ODD, true, false));

        CLabel lblQuantity = new CLabel(composite,SWT.NONE);
        lblQuantity.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblQuantity.setText(Messages.getMessage("label.quantity"));
        new CLabel(composite, SWT.NONE|SWT.CENTER);
        CLabel lblPrice = new CLabel(composite, SWT.NONE);
        lblPrice.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblPrice.setText(Messages.getMessage("label.price"));

        composite.setLayout(new GridLayout(3, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Text txtQuantity = new Text(composite,SWT.NONE|SWT.READ_ONLY);
        txtQuantity.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        txtQuantity.setFont(FontToolkit.getInstance().getTahoma(50, SWT.BOLD));
        txtQuantity.setText("0");

        CLabel cLabel =new CLabel(composite, SWT.FLAT|SWT.CENTER);
        cLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        cLabel.setFont(FontToolkit.getInstance().getTahoma(50, SWT.BOLD));
        cLabel.setText("X");

        Text txtPrice = new Text(composite, SWT.NONE|SWT.RIGHT|SWT.READ_ONLY);
        txtPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        txtPrice.setFont(FontToolkit.getInstance().getTahoma(50, SWT.BOLD));
        txtPrice.setText("0,00");
        window.register(IAppConfig.GUI_SELL_QUANTITY, txtQuantity);
        window.register(IAppConfig.GUI_SELL_PRICE,txtPrice);
    }



    private void createRightPanel(Composite parent){
        Composite composite = new Composite(parent, SWT.BORDER);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createCupom(composite);
        createSubTotalComposite(composite);
    }

    private void createCupom(Composite parent){
        StyledText styledText = new StyledText(parent,SWT.BORDER|SWT.V_SCROLL);
        styledText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        styledText.setFont(FontToolkit.getInstance().getTahoma(20, SWT.NONE));
        styledText.setBackground(Colors.getColor(SWT.COLOR_INFO_BACKGROUND));
        window.register(IAppConfig.GUI_SELL_CUPOM,styledText);
    }

    private void createSubTotalComposite(Composite parent){
        Group group = new Group(parent, SWT.NONE);
        group.setLayout(new GridLayout(1,false));
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        CLabel lblSubTotal = new CLabel(group,SWT.NONE);
        lblSubTotal.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblSubTotal.setText(Messages.getMessage("label.subtotal"));
        Text txtSubTotal = new Text(group, SWT.FLAT|SWT.RIGHT|SWT.READ_ONLY);
        txtSubTotal.setFont(FontToolkit.getInstance().getTahoma(40, SWT.BOLD));
        txtSubTotal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        window.register(IAppConfig.GUI_SELL_SUBTOTAL,txtSubTotal);
    }

    @Override
    protected void checkSubclass() {
    }


}
