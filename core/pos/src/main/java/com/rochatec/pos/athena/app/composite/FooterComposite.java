package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.event.AppEvent;
import com.rochatec.pos.athena.utils.Colors;
import com.rochatec.pos.athena.utils.Messages;
import com.rochatec.pos.athena.utils.WidgetUtils;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Created by epr on 16/07/15.
 */
public class FooterComposite {

    private AthenaApplicationWindow window;
    private Composite container;

    public FooterComposite(AthenaApplicationWindow window, Composite parent, int style) {
        this.window = window;
        createContents(parent, style);
    }

    public FooterComposite(AthenaApplicationWindow window, Composite parent) {
        this(window, parent, SWT.NONE);
    }

    private void createContents(Composite parent, int style) {
        container = new Composite(parent, style);
        container.setLayout(new GridLayout(7, false));
        GridData footerData = new GridData(SWT.FILL, SWT.FILL, true, false);
        footerData.minimumHeight = 30;
        container.setLayoutData(footerData);
        container.setBackground(Colors.getColorBlack());
        new CLabel(container, SWT.NONE).setText(Messages.getMessage("label.operator"));
        CLabel operator = new CLabel(container, SWT.FLAT);
        GridData txtOperadorGridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        txtOperadorGridData.minimumWidth = 300;
        operator.setLayoutData(txtOperadorGridData);
        operator.setForeground(Colors.getColorWhite());
        new CLabel(container, SWT.NONE).setText(Messages.getMessage("label.ecf"));
        CLabel ecf = new CLabel(container, SWT.FLAT);
        ecf.setForeground(Colors.getColorWhite());
        GridData txtEcfGridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        txtEcfGridData.minimumWidth = 50;
        ecf.setLayoutData(txtEcfGridData);
        window.register(IAppConfig.GUI_FOOTER_OPERATOR, operator);
        window.register(IAppConfig.GUI_FOOTER_ECF, ecf);
        CLabel lblQuantity = new CLabel(container, SWT.NONE);
        lblQuantity.setText(Messages.getMessage("label.quantity"));
        Text txtQuantity = new Text(container,SWT.SEARCH |SWT.CANCEL | SWT.BORDER);
        GridData txtQuantityGridData = new GridData(SWT.FILL, SWT.FILL_EVEN_ODD, true, false);
        txtQuantityGridData.minimumWidth = 100;
        txtQuantity.addVerifyListener(new VerifyListenerImpl(window));
        txtQuantity.setLayoutData(txtQuantityGridData);

        txtQuantity.setForeground(Colors.getColorWhite());
        txtQuantity.addKeyListener(new KeyListenerImpl(window));

        Text txtBarcode = new Text(container, SWT.SEARCH | SWT.ICON_SEARCH | SWT.CANCEL | SWT.BORDER);
        txtBarcode.setMessage(Messages.getMessage("product.search"));
        GridData gridData = new GridData(SWT.FILL, SWT.FILL_EVEN_ODD, true, false);
        gridData.horizontalAlignment = SWT.RIGHT;
        gridData.minimumWidth = 250;
        txtBarcode.setLayoutData(gridData);
        txtBarcode.addKeyListener(new KeyListenerImpl(window));
        window.register(IAppConfig.GUI_FOOTER_LABEL_QUANTITY, lblQuantity);
        window.register(IAppConfig.GUI_FOOTER_QUANTITY, txtQuantity);
        window.register(IAppConfig.GUI_FOOTER_BARCODE, txtBarcode);
        container.addKeyListener(new KeyListenerImpl(window));
        WidgetUtils.backgroundEquals(container);
    }

    class VerifyListenerImpl implements VerifyListener{
        private char[] characteres = {'0','1','2','3','4','5','6','7','8','9',','};

        private AthenaApplicationWindow window;

        public VerifyListenerImpl(AthenaApplicationWindow window) {
            this.window = window;
        }

        @Override
        public void verifyText(VerifyEvent e) {
            Text text = window.getText(IAppConfig.GUI_SELL_QUANTITY);
            text.setText(((Text)e.widget).getText());
        }
    }

    class KeyListenerImpl extends KeyAdapter {

        private AthenaApplicationWindow window;

        public KeyListenerImpl(AthenaApplicationWindow window) {
            this.window = window;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            AppEvent event = new AppEvent(e);
            event.display = e.display;
            event.shell = e.display.getActiveShell().getShell();
            event.widget = e.widget;
            event.character = e.character;
            event.keyCode = e.keyCode;
            event.window = window;
            window.fireApplicationExecuteEvent(event);
        }

    }
}
