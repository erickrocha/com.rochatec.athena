package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.model.ItemSale;
import com.rochatec.pos.athena.utils.Formatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by epr on 28/07/15.
 */
public class ProductComposite  {

    private Composite composite;
    private CLabel lblDescription;
    private CLabel lblQuantity;
    private CLabel lblPrice;
    private CLabel lblTotalItem;

    public ProductComposite(Composite parent) {
        createContents(parent);
    }

    private void createContents(Composite parent){
        composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1,false));
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));

        Composite header = new Composite(composite,SWT.NONE);
        header.setLayout(new GridLayout(1,true));
        lblDescription = new CLabel(header,SWT.NONE);
        lblDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        Composite footer = new Composite(composite,SWT.NONE);
        footer.setLayout(new GridLayout(4,false));
        lblQuantity = new CLabel(footer,SWT.NONE|SWT.LEFT);
        lblQuantity.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
        new CLabel(footer,SWT.NONE).setText("X");
        lblPrice = new CLabel(footer,SWT.NONE|SWT.RIGHT);
        lblPrice.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
        lblTotalItem = new CLabel(footer,SWT.NONE|SWT.RIGHT);
        lblTotalItem.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
    }

    public Composite getComposite(){
        return composite;
    }

    public void setItemSale(ItemSale itemSale){
        try{
            lblDescription.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            lblQuantity.setText(Formatter.getWeight().mask(5.000));
            lblPrice.setText(Formatter.getCurrency().mask(199.99));
            lblTotalItem.setText(Formatter.getCurrency().mask(999.95));
        }catch (BadFormatException ex){

        }
    }
}
