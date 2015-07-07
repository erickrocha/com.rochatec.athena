package com.rochatec.pos.athena.app.window;

import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.provider.content.GenericContentProvider;
import com.rochatec.pos.athena.app.service.PreferenceService;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.BoxStatus;
import com.rochatec.pos.athena.model.Operator;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.service.ISecurityService;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by epr on 06/07/15.
 */
public class Loader {

    private ApplicationController controller;

    public Loader(ApplicationController controller) {
        this.controller = controller;
    }

    public void execute() {
        Display display = new Display();
        final Shell shell = new Shell(SWT.ON_TOP);
        shell.setSize(200, 400);
        shell.setLayout(new GridLayout(1, false));
        Composite body = new Composite(shell, SWT.NONE);
        body.setLayout(new GridLayout(1, false));
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        ListViewer listViewer = new ListViewer(body, SWT.NONE);
        listViewer.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        listViewer.setContentProvider(new GenericContentProvider<String>());
        listViewer.setLabelProvider(new LabelProvider());

        final ProgressBar bar = new ProgressBar(body, SWT.NONE);
        bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        bar.setMaximum(4);

        shell.pack();
        Rectangle splashRect = shell.getBounds();
        Rectangle displayRect = display.getBounds();
        int x = (displayRect.width - splashRect.width) / 2;
        int y = (displayRect.height - splashRect.height) / 2;
        shell.setLocation(x, y);
        shell.open();

        final List<Boolean> finisheds = new ArrayList<>();
        finisheds.add(new Boolean(false));
        bar.setMaximum(finisheds.size());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                PreferenceService preferenceService = new PreferenceService();
                String boxStatusString = preferenceService.getString(IAppConfig.BOX_STATUS);
                BoxStatus boxStatus = BoxStatus.valueOf(boxStatusString);
//                addMessage(Messages.getMessage("load.window.reading.file.config"));
                switch (boxStatus) {
                    case OPEN:
                        ISecurityService securityService = controller.getSecurityService();
                        ISaleService saleService = controller.getSaleService();
                        String operatorKey = preferenceService.getString(IAppConfig.BOX_OPERATOR);
                        Operator operator = securityService.findOperatorByKey(operatorKey);
                        controller.getManager().setOperator(operator);
                        Box box = saleService.findBoxByOperatorAndOpen(operator);
                        controller.getManager().setBox(box);
                        finisheds.set(0,true);
                        break;
                }
//                addMessage(Messages.getMessage("load.window.finished.loading.file.config"));
            }
        });
        thread.start();

        display.asyncExec(new Runnable() {
            public void run() {

                for (int i = 0; i < finisheds.size(); i++) {
                    if (finisheds.get(i)) {
                        bar.setSelection(i + 1);
                    } else {

                        try {
                            Thread.sleep(1000);
                        } catch (Throwable e) {
                        }
                    }
                }
                if (bar.getMaximum() == finisheds.size()) {
                    shell.close();
                }
            }
        });

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
