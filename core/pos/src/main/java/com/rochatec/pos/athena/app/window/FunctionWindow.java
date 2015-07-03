package com.rochatec.pos.athena.app.window;

import com.google.common.collect.Lists;
import com.rochatec.pos.athena.app.provider.content.GenericContentProvider;
import com.rochatec.pos.athena.app.provider.label.FunctionLabelProvider;
import com.rochatec.pos.athena.main.Activator;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.service.IResourceService;
import com.rochatec.pos.athena.utils.Messages;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import java.util.List;

/**
 * Created by epr on 03/07/15.
 */
public class FunctionWindow extends Window {

    public FunctionWindow(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.getMessage("function.window.title"));
    }

    @Override
    protected Control createContents(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        TableViewer tableViewer = new TableViewer(composite,SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        tableViewer.getTable().setHeaderVisible(true);
        tableViewer.getTable().setLinesVisible(true);
        createColumns(tableViewer);
        tableViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        tableViewer.setContentProvider(new GenericContentProvider<Function>());
        tableViewer.setLabelProvider(new FunctionLabelProvider());

        Composite bottomBar = new Composite(parent,SWT.BORDER);
        bottomBar.setLayout(new GridLayout(1, false));
        bottomBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        Button button = new Button(bottomBar,SWT.PUSH);
        button.setText(Messages.getMessage("function.window.button.ok"));
        button.addSelectionListener(new CloseWindowListenerImpl());
        fill(tableViewer);
        return composite;
    }

    private void fill(TableViewer tableViewer){
        IResourceService resourceService = Activator.getController().getResourceService();
        List<Function> functionList = Lists.newArrayList(resourceService.findAllFunctions());
        tableViewer.setInput(functionList);
    }

    private void createColumns(TableViewer tableViewer){
        TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
        id.getColumn().setText(Messages.getMessage("function.field.label.key"));
        id.getColumn().setWidth(70);
        id.getColumn().setResizable(true);
        id.getColumn().setMoveable(false);
        id.getColumn().setAlignment(SWT.CENTER);

        TableViewerColumn socialSecurity = new TableViewerColumn(tableViewer,SWT.CENTER);
        socialSecurity.getColumn().setText(Messages.getMessage("function.field.label.name"));
        socialSecurity.getColumn().setWidth(150);
        socialSecurity.getColumn().setResizable(true);
        socialSecurity.getColumn().setMoveable(true);
        socialSecurity.getColumn().setAlignment(SWT.CENTER);
    }


    class CloseWindowListenerImpl extends SelectionAdapter{
        @Override
        public void widgetSelected(SelectionEvent e) {
            close();
        }
    }
}
