package com.rochatec.pos.athena.app.service;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.composite.WelcomeComposite;
import com.rochatec.pos.athena.app.dialogs.LoginDialog;
import com.rochatec.pos.athena.app.event.FunctionEvent;
import com.rochatec.pos.athena.app.listeners.FunctionListener;
import com.rochatec.pos.athena.app.window.FunctionWindow;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.exception.FunctionNotExistException;
import com.rochatec.pos.athena.exception.UserException;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.utils.DialogUtils;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import java.util.List;

/**
 * Created by epr on 03/07/15.
 */
public class GUIService implements FunctionListener {

    private static Logger LOGGER = Logger.getLogger(GUIService.class);


    private ApplicationController controller;
    private AthenaApplicationWindow athenaApplicationWindow;

    public GUIService(ApplicationController controller, AthenaApplicationWindow athenaApplicationWindow) {
        this.controller = controller;
        this.athenaApplicationWindow = athenaApplicationWindow;
    }

    @Override
    public void execute(FunctionEvent event) {
        try {
            if (controller.isOpen()) {
                switch (event.function) {
                    case 1000:
                        athenaApplicationWindow.updateScreen(SellComposite.ID);
                        break;
                    case 1001:
                        athenaApplicationWindow.updateScreen(PaymentComposite.ID);
                        break;
                    case 999:
                        showFunctions(controller.getResourceService().findAllFunctions(),event);
                        break;
                    default:
                        String operadorId = controller.getPreferenceService().getString(IAppConfig.BOX_OPERATOR);
                        Operator operator = controller.getSecurityService().findOperatorByKey(operadorId);
                        Function function = controller.getResourceService().findFunctionById(event.function);
                        if (function.getHierarchy().equals(operator.getHierarchy())) {
                            controller.getExecuteService().execute(function, operator);
                        } else {
                            LoginDialog loginDialog = new LoginDialog(event.display.getActiveShell());
                            if (loginDialog.open() == SWT.OK) {
                                String username = loginDialog.getUsername();
                                String password = loginDialog.getPassword();
                                Operator autorizedBy = controller.getSecurityService().login(username, password);
                                controller.getExecuteService().execute(function, operator, autorizedBy);
                            }
                        }
                        break;
                }
            }else {
                switch (event.function){
                    case 999:
                        showFunctions(controller.getResourceService().findAllFunctions(),event);
                        break;
                }
            }
        } catch (UserException ex) {
            LOGGER.error(ex.getMessage());
            DialogUtils.openDialogError(event.display.getActiveShell(), ex);
        }catch (FunctionNotExistException ex) {
            DialogUtils.openDialogError(event.display.getActiveShell(), ex);
        }
    }

    private void showFunctions(List<Function> functions,FunctionEvent event){
        FunctionWindow window = new FunctionWindow(event.display.getActiveShell());
        window.setUpdate(functions);
        window.open();
    }
}
