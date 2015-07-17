package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.utils.Colors;
import com.rochatec.pos.athena.utils.FontToolkit;
import com.rochatec.pos.athena.utils.WidgetUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by epr on 02/07/15.
 */
public class WelcomeComposite extends Composite {

    public static final String ID = "com.rochatec.pos.athena.app.composite.WelcomeComposite";

    private AthenaApplicationWindow window;

    public WelcomeComposite(AthenaApplicationWindow window,Composite parent, int style) {
        super(parent, style);
        this.window = window;
        createContents();
    }

    public void createContents() {
        setLayout(new GridLayout(1, false));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setBackground(Colors.getColorWhite());

        Composite body = new Composite(this, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        layout.marginTop = 100;
        layout.marginLeft = 100;
        layout.marginRight = 100;
        body.setLayout(layout);
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        CLabel txtMessage = new CLabel(body, SWT.NONE);
        txtMessage.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        txtMessage.setAlignment(SWT.CENTER);
        txtMessage.setFont(FontToolkit.getInstance().getTahomaHeader());

        WidgetUtils.backgroundEquals(this);
        txtMessage.setBackground(Colors.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        window.register(IAppConfig.GUI_WELCOME_MESSAGE, txtMessage);
    }

    @Override
    protected void checkSubclass() {
    }
}
