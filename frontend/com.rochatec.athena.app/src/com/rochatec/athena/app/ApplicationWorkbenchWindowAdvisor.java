package com.rochatec.athena.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.rochatec.athena.i18n.Messages;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
    	IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1024,768));
//        configurer.getWindow().getShell().setMaximized(true);
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setShowMenuBar(true);
        configurer.setShowProgressIndicator(true);
        configurer.setTitle(Messages.getMessage("application.title"));
    }
    
    @Override
   	public boolean preWindowShellClose() {
   		MessageBox msg = new MessageBox(getWindowConfigurer().getWorkbenchConfigurer().getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.ICON_QUESTION|SWT.YES|SWT.NO);
   		msg.setText(Messages.getMessage("app.dialog.close.title"));
   		msg.setMessage(Messages.getMessage("app.dialog.close.message"));
   		int resp = msg.open();
   		if (resp == SWT.YES){
   			return true;
   		}
   		return false;
   	}
}
