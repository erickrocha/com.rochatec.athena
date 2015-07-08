package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.Product;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.utils.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by epr on 02/07/15.
 */
public class SellComposite extends Composite{

    public static final String ID = "com.rochatec.pos.athena.app.composite.SellComposite";
    private ApplicationController controller;
    private Text txtQuantity;
    private Text txtPrice;
    private Text txtSubTotal;
    private Text txtItemLabel;
    private Text txtProduct;
    private CLabel lblStatus;
    private StyledText styledText;
    private char[] characteres = {'0','1','2','3','4','5','6','7','8','9',','};


    public SellComposite(Composite parent,int style,ApplicationController controller) {
        super(parent, style);
        this.controller = controller;
        createContents();
    }

    public void createContents() {
        setLayout(new GridLayout(1, true));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite body = new Composite(this,SWT.NONE);
        body.setLayout(new GridLayout(1, false));
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        createSellPanel(body);

        WidgetUtils.backgroundEquals(body);

        createFooter(this);
        txtProduct.setFocus();
        getShell().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
               for (int i = 0; i < characteres.length;i++){
                   if (e.character != characteres[i]){
                       e.doit = false;
                   }else{
                       txtQuantity.setText(txtQuantity.getText()+e.character);
                   }

               }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void createFooter(Composite parent){
        Composite footer = new Composite(parent,SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        layout.marginRight = 0;
        layout.marginTop = 0;
        layout.marginBottom = 0;
        layout.marginLeft = 0;
        footer.setLayout(layout);
        GridData footerData = new GridData(SWT.FILL, SWT.FILL,true,false);
        footerData.heightHint = 35;
        footer.setLayoutData(footerData);
        footer.setBackground(Colors.getColorBlack());
        footer.setForeground(Colors.getColorWhite());

        txtProduct = new Text(footer,  SWT.SEARCH | SWT.ICON_SEARCH | SWT.CANCEL| SWT.BORDER);
        txtProduct.setMessage(Messages.getMessage("product.search"));
        txtProduct.setBackground(footer.getBackground());
        txtProduct.setForeground(footer.getForeground());
        GridData gridData = new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false);
        gridData.horizontalAlignment = SWT.RIGHT;
        gridData.minimumWidth = 250;
        txtProduct.setLayoutData(gridData);
        txtProduct.addKeyListener(new SearchProduct());
    }

    private void createSellPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.NONE);
        composite.setLayout(new GridLayout(2, true));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createLeftPanel(composite);
        createRightPanel(composite);
    }

    private void createLeftPanel(Composite parent){
        Composite composite = new Composite(parent,SWT.BORDER);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createItemLabel(composite);
        createItemBox(composite);
    }

    private void createItemLabel(Composite parent){
        Group group = new Group(parent, SWT.BORDER);
        group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
        group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));

        CLabel lblPrice = new CLabel(group, SWT.NONE);
        lblPrice.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblPrice.setText(Messages.getMessage("label.product.description"));

        txtItemLabel = new Text(group, SWT.READ_ONLY);
        txtItemLabel.setFont(FontToolkit.getInstance().getTahoma(35, SWT.BOLD));
        txtItemLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
    }


    private void createItemBox(Composite parent){
        Group group = new Group(parent, SWT.BORDER);
        group.setLayout(GridLayoutBuilder.getInstance().build(10,0,0,0));
        group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));

        Composite composite = new Composite(group, SWT.NONE);

        composite.setLayout(GridLayoutBuilder.getInstance().build(0,0,0,0,3));
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL_EVEN_ODD,true,false));
        CLabel lblQuantity = new CLabel(composite,SWT.NONE);
        lblQuantity.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblQuantity.setText(Messages.getMessage("label.quantity"));
        new CLabel(composite, SWT.NONE|SWT.CENTER);
        CLabel lblPrice = new CLabel(composite, SWT.NONE);
        lblPrice.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblPrice.setText(Messages.getMessage("label.price"));

        composite.setLayout(new GridLayout(3,false));
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));

        txtQuantity = new Text(composite,SWT.NONE|SWT.READ_ONLY);
        txtQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        txtQuantity.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
        txtQuantity.setText("0");

        CLabel cLabel =new CLabel(composite, SWT.FLAT|SWT.CENTER);
        cLabel.setLayoutData(new GridData(SWT.FILL,SWT.FILL,false,false));
        cLabel.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
        cLabel.setText("X");

        txtPrice = new Text(composite, SWT.NONE|SWT.RIGHT|SWT.READ_ONLY);
        txtPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        txtPrice.setFont(FontToolkit.getInstance().getTahoma(50,SWT.BOLD));
        txtPrice.setText("0,00");
    }



    private void createRightPanel(Composite parent){
        Composite composite = new Composite(parent, SWT.BORDER);
        composite.setLayout(new GridLayout(1, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        createCupom(composite);
        createSubTotalComposite(composite);
    }

    private void createCupom(Composite parent){
        styledText = new StyledText(parent,SWT.BORDER|SWT.V_SCROLL);
        styledText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        styledText.setFont(FontToolkit.getInstance().getTahoma(20, SWT.NONE));
        styledText.setBackground(Colors.getColor(SWT.COLOR_INFO_BACKGROUND));
    }

    private void createSubTotalComposite(Composite parent){
        Group group = new Group(parent, SWT.NONE);
        group.setLayout(new GridLayout(1,false));
        group.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
        CLabel lblSubTotal = new CLabel(group,SWT.NONE);
        lblSubTotal.setFont(FontToolkit.getInstance().getTahomaLabel());
        lblSubTotal.setText(Messages.getMessage("label.subtotal"));
        txtSubTotal = new Text(group, SWT.FLAT|SWT.RIGHT|SWT.READ_ONLY);
        txtSubTotal.setFont(FontToolkit.getInstance().getTahoma(40, SWT.BOLD));
        txtSubTotal.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    @Override
    protected void checkSubclass() {
    }

    private void fillValues(Product product){
        if (product != null){
            try{
                txtItemLabel.setText(product.getShortName());
                txtPrice.setText(Formatter.getCurrency().mask(product.getSellPrice()));
            }catch (BadFormatException ex){

            }
        }
    }

    class QuantityListenerImpl implements Listener{

        @Override
        public void handleEvent(Event event) {
            System.out.print(event.character);
        }
    }

    class SearchProduct extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.keyCode == IAppConfig.KEY_ENTER || e.keyCode == IAppConfig.KEY_ENTER_NUMERICO){
                ISaleService saleService = controller.getSaleService();
                String productCode = ((Text)e.widget).getText();
                Product product = saleService.findProductByBarcode(productCode);
                fillValues(product);
            }
        }

    }
}
