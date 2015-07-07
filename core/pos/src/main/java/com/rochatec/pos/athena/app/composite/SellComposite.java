package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.utils.Colors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by epr on 02/07/15.
 */
public class SellComposite extends Composite{

    public static final String ID = "com.rochatec.pos.athena.app.composite.SellComposite";
    private ApplicationController controller;

    public SellComposite(Composite parent,int style,ApplicationController controller) {
        super(parent,style);
        this.controller = controller;
        createContents();
    }

    public void createContents() {
        setLayout(new GridLayout(1, true));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite body = new Composite(this,SWT.NONE);
        body.setLayout(new GridLayout(1, false));
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        createSellPanel(body);

        Composite footer = new Composite(this,SWT.NONE);
        footer.setLayout(new GridLayout(1, false));
        GridData footerData = new GridData(SWT.FILL, SWT.FILL,true,false);
        footerData.heightHint = 30;
        footer.setLayoutData(footerData);
        footer.setBackground(Colors.getColorBlack());

    }

    private void createSellPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.NONE);
        composite.setLayout(new GridLayout(2,true));
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        createRightPanel(composite);
        createLeftPanel(composite);
    }

    private void createRightPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.BORDER);
        composite.setLayout(new GridLayout(1,false));
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
    }

    private void createLeftPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.BORDER);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    @Override
    protected void checkSubclass() {
    }
}
