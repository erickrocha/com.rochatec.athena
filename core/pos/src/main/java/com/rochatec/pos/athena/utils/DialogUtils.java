package com.rochatec.pos.athena.utils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by epr on 02/07/15.
 */
public class DialogUtils {

    public static void openDialogError(Shell parent,Exception ex){
        MessageDialog.openError(parent,Messages.getMessage("application.error.title"),ex.getMessage());
    }
}
