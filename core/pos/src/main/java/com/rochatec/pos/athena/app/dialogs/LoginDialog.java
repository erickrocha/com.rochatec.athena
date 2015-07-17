package com.rochatec.pos.athena.app.dialogs;

import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.exception.UserNException;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.service.ISecurityService;
import com.rochatec.pos.athena.utils.Messages;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Created by epr on 02/07/15.
 */
public class LoginDialog extends TitleAreaDialog {

    private Text txtUsername;
    private Text txtPassword;

    private ApplicationController controller;
    private Operator operator;

    public LoginDialog(Shell owner, ApplicationController controller) {
        super(owner);
        this.controller = controller;
    }

    @Override
    protected Point getInitialSize() {
        return new Point(300, 300);
    }

    @Override
    public void create() {
        super.create();
        setTitle(Messages.getMessage("app.login.dialog.title"));
        setMessage(Messages.getMessage("app.login.dialog.message"), IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText(Messages.getMessage("app.login.dialog.title"));
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);
        createUsername(container);
        createPassword(container);
        return area;
    }

    private void createUsername(Composite container) {
        CLabel lbtUsername = new CLabel(container, SWT.NONE);
        lbtUsername.setText(Messages.getMessage("app.login.dialog.label.username"));

        GridData dataUsername = new GridData();
        dataUsername.grabExcessHorizontalSpace = true;
        dataUsername.horizontalAlignment = GridData.FILL;

        txtUsername = new Text(container, SWT.BORDER);
        txtUsername.setLayoutData(dataUsername);
    }

    private void createPassword(Composite container) {
        CLabel lblPassword = new CLabel(container, SWT.NONE);
        lblPassword.setText(Messages.getMessage("app.login.dialog.label.password"));

        GridData dataPassword = new GridData();
        dataPassword.grabExcessHorizontalSpace = true;
        dataPassword.horizontalAlignment = GridData.FILL;
        txtPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
        txtPassword.setLayoutData(dataPassword);
    }

    public Operator show() {
        open();
        return operator;
    }

    @Override
    protected void okPressed() {
        try {
            ISecurityService securityService = controller.getFactory().getSecurityService();
            operator = securityService.login(txtUsername.getText().trim(), txtPassword.getText().trim());
            setReturnCode(SWT.OK);
            close();
        } catch (UserNException ex) {
            setMessage(ex.getMessage(), IMessageProvider.ERROR);
        }
    }
}
