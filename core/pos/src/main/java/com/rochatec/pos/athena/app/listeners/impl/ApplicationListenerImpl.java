package com.rochatec.pos.athena.app.listeners.impl;

import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.event.AppEvent;
import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.app.listeners.ApplicationAdapter;
import com.rochatec.pos.athena.app.model.SellStatus;
import com.rochatec.pos.athena.app.window.AthenaWindowHelper;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.ItemSale;
import com.rochatec.pos.athena.model.Product;
import com.rochatec.pos.athena.service.ISaleService;
import com.rochatec.pos.athena.utils.Formatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;

import java.math.BigDecimal;

/**
 * Created by epr on 16/07/15.
 */
public class ApplicationListenerImpl extends ApplicationAdapter {

    private ApplicationController controller;
    private int count = 0;

    private SellStatus status = SellStatus.SELL_STARTED;
    private StringBuilder value = new StringBuilder();
    private char[] characteres = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.'};

    public ApplicationListenerImpl(ApplicationController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(AppEvent event) {
        switch (event.keyCode) {
            case IAppConfig.KEY_ENTER:
                sell(event);
                break;
            case IAppConfig.KEY_ENTER_NUMERICO:
                sell(event);
                break;
            case SWT.F4:
                event.window.getText(IAppConfig.GUI_SELL_QUANTITY).setText(value.toString());
                event.window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setText("");
                value = new StringBuilder();
                break;
            case SWT.F10:
                event.window.updateScreen(SellComposite.ID);
                event.window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setVisible(true);
                event.window.getText(IAppConfig.GUI_SELL_QUANTITY).setText("1.000");
                status = SellStatus.WAIT_VALUE;
                controller.openSale();
                break;
            case SWT.F11:
                event.window.updateScreen(PaymentComposite.ID);
                status = SellStatus.PAY_STARTED;
                break;
            case IAppConfig.BACKSPACE:
                if (value.toString().length() > 0) {
                    String newValue = value.substring(0, value.toString().length() - 1);
                    value = new StringBuilder(newValue);
                    event.window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setText(value.toString());
                }
                break;
            default:
                for (int i = 0; i < characteres.length; i++) {
                    if (event.character == characteres[i]) {
                        value.append(event.character);
                        event.window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setText(value.toString());
                    }
                }

                break;
        }
    }

    private void sell(AppEvent event) {
        Product product = null;
        if (count == 0){
            product = controller.findProduct(value.toString());
            AthenaWindowHelper.showProduct(event.window, product);
            count++;
        }
        if (count == 1){
            try {
                String quantityString = event.window.getText(IAppConfig.GUI_SELL_QUANTITY).getText();
                BigDecimal quantity = Formatter.getWeight().parse(quantityString);
                ItemSale itemSale =controller.addItem(product, quantity);
                AthenaWindowHelper.writeTicket(event.window,controller.getCountSellItems(),itemSale);
                count = 0;
            }catch (BadFormatException ex){

            }
        }
    }


    @Override
    public void activated(AppEvent event) {
        AthenaWindowHelper.refresh(event.window, controller, status);
    }
}
