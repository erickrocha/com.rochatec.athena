package com.rochatec.pos.athena.app.window;

import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.provisional.action.ICoolBarManager2;
import org.eclipse.jface.internal.provisional.action.IToolBarManager2;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by epr on 03/07/15.
 */
public class AthenaWindow extends Window {

    public AthenaWindow(Shell parentShell) {
        super(parentShell);
    }

    protected boolean canHandleShellCloseEvent() {
        return super.canHandleShellCloseEvent();
    }

    /* (non-Javadoc)
     * Method declared on Window.
     */
    public boolean close() {

        if (super.close()) {
            return true;
        }
        return false;
    }

    /**
     * Extends the super implementation by creating the trim widgets using <code>createTrimWidgets</code>.
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
    }

    protected Font getFont() {
        return JFaceResources.getFont(getSymbolicFontName());
    }

    public String getSymbolicFontName() {
        return JFaceResources.TEXT_FONT;
    }
}
