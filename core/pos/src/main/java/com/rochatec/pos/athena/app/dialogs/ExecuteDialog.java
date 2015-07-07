package com.rochatec.pos.athena.app.dialogs;

import com.rochatec.pos.athena.utils.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import javax.annotation.Resource;

@Resource
public class ExecuteDialog extends TitleAreaDialog {

    private Text txtInput;

    public ExecuteDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected Point getInitialSize() {
        return new Point(250, 200);
    }

    @Override
    public void create() {
        super.create();
        setTitle(Messages.getMessage("app.execute.dialog.title"));
        setMessage(Messages.getMessage("app.execute.dialog.message"), IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        parent.getShell().setText(Messages.getMessage("app.execute.dialog.title"));
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        GridLayout layout = new GridLayout(2, false);
        container.setLayout(layout);

        GridData dataInput = new GridData();
        dataInput.grabExcessHorizontalSpace = true;
        dataInput.horizontalAlignment = GridData.FILL;

        txtInput = new Text(container, SWT.BORDER);
        txtInput.setLayoutData(dataInput);
        txtInput.addKeyListener(new EnterKeyAdapterImpl());
        return area;
    }



    @Override
    protected boolean isResizable() {
        return true;
    }

    @Override
    protected void okPressed() {
        try {
            Integer function = Integer.parseInt(txtInput.getText().trim());
            setReturnCode(function);
            close();
        } catch (NumberFormatException ex) {
            MessageDialog.openError(getShell(), Messages.getMessage("app.execute.error.title"),
                    Messages.getMessage("app.execute.error.code"));
        }
    }

    class EnterKeyAdapterImpl extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.character == SWT.CR){
                okPressed();
            }
        }
    }

}
