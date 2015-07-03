package com.rochatec.pos.athena.app.listeners;

import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.dialogs.ExecuteDialog;
import com.rochatec.pos.athena.app.dialogs.LoginDialog;
import com.rochatec.pos.athena.app.service.GUIService;
import com.rochatec.pos.athena.exception.FunctionNotExistException;
import com.rochatec.pos.athena.exception.UserException;
import com.rochatec.pos.athena.main.Activator;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.service.IExecuteService;
import com.rochatec.pos.athena.service.IResourceService;
import com.rochatec.pos.athena.service.ISecurityService;
import com.rochatec.pos.athena.utils.DialogUtils;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import java.util.Map;

/**
 * Created by epr on 02/07/15.
 */
public class ApplicationKeyListenerImpl extends KeyAdapter {

    private static Logger LOGGER = Logger.getLogger(ApplicationKeyListenerImpl.class);

    private IResourceService resourceService;
    private ISecurityService securityService;
    private IExecuteService executeService;
    private StackLayout stackLayout;
    private Map<String,Composite> compositeMap;

    public ApplicationKeyListenerImpl(StackLayout stackLayout,Map<String,Composite> compositeMap) {
        this.stackLayout = stackLayout;
        this.compositeMap = compositeMap;
        resourceService = Activator.getController().getResourceService();
        securityService = Activator.getController().getSecurityService();
        executeService = Activator.getController().getExecuteService();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Shell shell = e.display.getActiveShell();
        switch (e.keyCode) {
            case SWT.F1:
                break;
            case SWT.F2:
                try {
                    ExecuteDialog dialog = new ExecuteDialog(e.display.getActiveShell());
                    Integer functionId = dialog.open();
                    Function function = resourceService.findFunctionById(functionId);
                    Operator operator = securityService.findOperatorByKey("50001");
                    if (function.getHierarchy().equals(operator.getHierarchy())) {
                        executeService.execute(function, operator);
                    } else {
                        LoginDialog loginDialog = new LoginDialog(e.display.getActiveShell());
                        if (loginDialog.open() == SWT.OK) {
                            String username = loginDialog.getUsername();
                            String password = loginDialog.getPassword();
                            Operator autorizedBy = securityService.login(username, password);
                            GUIService guiService = new GUIService();
                            guiService.execute(function,e);
                            executeService.execute(function, operator, autorizedBy);
                        }
                    }
                } catch (UserException ex) {
                    LOGGER.error(ex.getMessage());
                    DialogUtils.openDialogError(e.display.getActiveShell(), ex);
                } catch (FunctionNotExistException ex) {
                    LOGGER.error(ex.getMessage());
                    DialogUtils.openDialogError(e.display.getActiveShell(), ex);
                }
                break;
            case SWT.F3:
                break;
            case SWT.F4:
                break;
            case SWT.F5:
                break;
            case SWT.F6:
                break;
            case SWT.F7:
                break;
            case SWT.F8:
                break;
            case SWT.F9:
                break;
            case SWT.F10:
                stackLayout.topControl = compositeMap.get(SellComposite.ID);
                shell.layout(true);
                break;
            case SWT.F11:
                stackLayout.topControl = compositeMap.get(PaymentComposite.ID);
                shell.layout(true);
                break;
            case SWT.F12:
                break;

        }
    }


}
