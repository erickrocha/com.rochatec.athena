package com.rochatec.pos.athena.app.dialogs;

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

    private String username;
    private String password;

    public LoginDialog(Shell owner) {
        super(owner);
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
        txtPassword = new Text(container, SWT.BORDER|SWT.PASSWORD);
        txtPassword.setLayoutData(dataPassword);
    }

    // save content of the Text fields because they get disposed
    // as soon as the Dialog closes
    private void saveInput() {
        username = txtUsername.getText();
        password = txtPassword.getText();

    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
