package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.event.AppEvent;
import com.rochatec.pos.athena.app.provider.content.GenericContentProvider;
import com.rochatec.pos.athena.model.ItemSale;
import com.rochatec.pos.athena.utils.*;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * Created by epr on 02/07/15.
 */
public class SellComposite extends Composite{

    public static final String ID = "com.rochatec.pos.athena.app.composite.SellComposite";
    private AthenaApplicationWindow window;

    public SellComposite(AthenaApplicationWindow window,Composite parent,int style) {
        super(parent, style);
        this.window = window;
        createContents();
    }

    public void createContents() {
        setLayout(new GridLayout(1, true));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite body = new Composite(this,SWT.NONE);
        body.setLayout(new GridLayout(1, false));
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createSellPanel(body);
        this.addKeyListener(new ShellKeyImpl());
        body.addKeyListener(new ShellKeyImpl());
    }


    private void createSellPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.NONE);
        composite.setLayout(new GridLayout(2, true));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createLeftPanel(composite);
        createRightPanel(composite);
        composite.addKeyListener(new ShellKeyImpl());
    }

    private void createLeftPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.BORDER);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createProductDescription(composite);
        createItemBox(composite);
        WidgetUtils.backgroundEquals(composite);
        composite.addKeyListener(new ShellKeyImpl());
    }

    private void createProductDescription(Composite parent){
        Group group = new Group(parent, SWT.BORDER);
        group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        CLabel lblDescription = new CLabel(group, SWT.NONE);
        lblDescription.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblDescription.setText(Messages.getMessage("label.product.description"));

        Text txtDescription = new Text(group, SWT.READ_ONLY);
        txtDescription.setFont(FontToolkit.getInstance().getTahoma(35, SWT.BOLD));
        txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        txtDescription.addKeyListener(new ShellKeyImpl());
        window.register(IAppConfig.GUI_SELL_DESCRIPTION, txtDescription);
        group.addKeyListener(new ShellKeyImpl());
    }


    private void createItemBox(Composite parent){
        Group group = new Group(parent, SWT.BORDER);
        group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        Composite composite = new Composite(group, SWT.NONE);

        composite.setLayout(GridLayoutBuilder.getInstance().build(0,0,0,0,3));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL_EVEN_ODD, true, false));

        CLabel lblQuantity = new CLabel(composite,SWT.NONE);
        lblQuantity.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblQuantity.setText(Messages.getMessage("label.quantity"));
        new CLabel(composite, SWT.NONE|SWT.CENTER);
        CLabel lblPrice = new CLabel(composite, SWT.NONE);
        lblPrice.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblPrice.setText(Messages.getMessage("label.price"));

        composite.setLayout(new GridLayout(3, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Text txtQuantity = new Text(composite,SWT.NONE|SWT.READ_ONLY);
        txtQuantity.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        txtQuantity.setFont(FontToolkit.getInstance().getTahoma(50, SWT.BOLD));
        txtQuantity.setText("0");
        txtQuantity.addKeyListener(new ShellKeyImpl());

        CLabel cLabel =new CLabel(composite, SWT.FLAT|SWT.CENTER);
        cLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        cLabel.setFont(FontToolkit.getInstance().getTahoma(50, SWT.BOLD));
        cLabel.setText("X");

        Text txtPrice = new Text(composite, SWT.NONE|SWT.RIGHT|SWT.READ_ONLY);
        txtPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        txtPrice.setFont(FontToolkit.getInstance().getTahoma(50, SWT.BOLD));
        txtPrice.setText("0,00");
        window.register(IAppConfig.GUI_SELL_QUANTITY, txtQuantity);
        window.register(IAppConfig.GUI_SELL_PRICE,txtPrice);
    }

    private void createRightPanel(Composite parent){
        Composite composite = new Composite(parent, SWT.BORDER);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createCupom(composite);
        createSubTotalComposite(composite);
        composite.addKeyListener(new ShellKeyImpl());
    }

    private void createCupom(Composite parent){
        final ScrolledComposite sc = new ScrolledComposite(parent, SWT.BORDER|SWT.V_SCROLL);
        sc.setLayout(new GridLayout(1, false));
        sc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite composite = new Composite(sc, SWT.BORDER);
        sc.setContent(composite);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        Composite header = new Composite(composite,SWT.NONE);
        header.setLayout(new GridLayout(1, true));
        header.setLayoutData(new GridData(SWT.FILL, SWT.FILL_EVEN_ODD, true, false));
        CLabel lblDescription = new CLabel(header,SWT.NONE);
        lblDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        lblDescription.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblDescription.setText("REFRIG PEPSI COLA LT 350ML");

        Composite footer = new Composite(composite,SWT.NONE);
        footer.setLayout(new GridLayout(4, false));
        footer.setLayoutData(new GridData(SWT.FILL, SWT.FILL_EVEN_ODD, true, false));
        CLabel lblQuantity = new CLabel(footer,SWT.NONE|SWT.LEFT);
        lblQuantity.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        lblQuantity.setText("5,000");
        lblQuantity.setFont(FontToolkit.getInstance().getTahomaLabel());
        new CLabel(footer,SWT.NONE).setText("X");
        CLabel lblPrice = new CLabel(footer,SWT.NONE|SWT.RIGHT);
        lblPrice.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblPrice.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        lblPrice.setText("199,99");
        CLabel lblTotalItem = new CLabel(footer,SWT.NONE|SWT.RIGHT);
        lblTotalItem.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblTotalItem.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        lblTotalItem.setText("999,95");
        sc.setMinHeight(0);


        window.register(IAppConfig.GUI_SELL_CUPOM, sc);
        sc.addKeyListener(new ShellKeyImpl());
    }

    private void createSubTotalComposite(Composite parent){
        Group group = new Group(parent, SWT.NONE);
        group.setLayout(new GridLayout(1,false));
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        CLabel lblSubTotal = new CLabel(group,SWT.NONE);
        lblSubTotal.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblSubTotal.setText(Messages.getMessage("label.subtotal"));
        Text txtSubTotal = new Text(group, SWT.FLAT|SWT.RIGHT|SWT.READ_ONLY);
        txtSubTotal.setFont(FontToolkit.getInstance().getTahoma(40, SWT.BOLD));
        txtSubTotal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        txtSubTotal.addKeyListener(new ShellKeyImpl());
        window.register(IAppConfig.GUI_SELL_SUBTOTAL,txtSubTotal);
    }

    @Override
    protected void checkSubclass() {
    }

    class ShellKeyImpl extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            AppEvent event = new AppEvent(e);
            event.display = e.display;
            event.shell = e.display.getActiveShell().getShell();
            event.widget = e.widget;
            event.character = e.character;
            event.keyCode = e.keyCode;
            event.window = window;
            window.fireApplicationExecuteEvent(event);
        }
    }

}
