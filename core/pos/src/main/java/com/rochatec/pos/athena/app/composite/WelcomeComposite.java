package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.utils.Colors;
import com.rochatec.pos.athena.utils.FontToolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by epr on 02/07/15.
 */
public class WelcomeComposite extends Composite{

    public static final String ID = "com.rochatec.pos.athena.app.composite.WelcomeComposite";

    private CLabel txtMessage;


    public WelcomeComposite(Composite parent, int style) {
        super(parent,style);
        createContents();
    }

    public void createContents(){
        setLayout(new GridLayout(1,false));
        setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));

        Composite body = new Composite(this,SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        layout.marginTop = 100;
        layout.marginLeft = 100;
        layout.marginRight = 100;
        body.setLayout(layout);
        body.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        txtMessage = new CLabel(body,SWT.NONE);
        txtMessage.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        txtMessage.setAlignment(SWT.CENTER);
        txtMessage.setFont(FontToolkit.getInstance().getTahomaHeader());
        txtMessage.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        Composite footer = new Composite(this,SWT.NONE);
        footer.setLayout(new GridLayout(1, false));
        GridData footerData = new GridData(SWT.FILL, SWT.FILL,true,false);
        footerData.heightHint = 30;
        footer.setLayoutData(footerData);
        footer.setBackground(Colors.getColorBlack());
    }

    @Override
    protected void checkSubclass() {
    }
}
