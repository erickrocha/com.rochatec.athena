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
public class PaymentComposite extends Composite {

    public static final String ID = "com.rochatec.pos.athena.app.composite.PaymentComposite";

    public PaymentComposite(Composite parent, int style) {
        super(parent, style);
        createContents();
    }

    public void createContents() {
        setLayout(new GridLayout(1, true));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite body = new Composite(this,SWT.NONE);
        body.setLayout(new GridLayout(1, false));
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));


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
