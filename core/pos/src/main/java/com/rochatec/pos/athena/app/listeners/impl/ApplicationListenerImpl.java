package com.rochatec.pos.athena.app.listeners.impl;

import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.event.AppEvent;
import com.rochatec.pos.athena.app.listeners.ApplicationAdapter;
import com.rochatec.pos.athena.app.model.SellStatus;
import com.rochatec.pos.athena.app.window.AthenaWindowHelper;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.Product;
import com.rochatec.pos.athena.service.ISaleService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * Created by epr on 16/07/15.
 */
public class ApplicationListenerImpl extends ApplicationAdapter {

    private ApplicationController controller;

    private SellStatus status = SellStatus.SELL_STARTED;
    private StringBuilder value = new StringBuilder();

    public ApplicationListenerImpl(ApplicationController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(AppEvent event) {
        switch (event.keyCode) {
            case IAppConfig.KEY_ENTER:
                if (status.equals(SellStatus.WAIT_VALUE)){
                    searchProduct(event);
                }
                break;
            case IAppConfig.KEY_ENTER_NUMERICO:
                if (status.equals(SellStatus.WAIT_VALUE)){
                    searchProduct(event);
                }
                break;
            case SWT.F4:
                event.window.getCLabel(IAppConfig.GUI_FOOTER_QUANTITY).setText(value.toString());
                event.window.getText(IAppConfig.GUI_SELL_QUANTITY).setText(value.toString());
                break;
            case SWT.F10:
                event.window.updateScreen(SellComposite.ID);
                event.window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setVisible(true);
                event.window.getText(IAppConfig.GUI_SELL_QUANTITY).setText("1,000");
                status = SellStatus.WAIT_VALUE;
                controller.openSale();
                AthenaWindowHelper.writeTicketHeader(event.window);
                break;
            case SWT.F11:
                event.window.updateScreen(PaymentComposite.ID);
                status = SellStatus.PAY_STARTED;
                break;
            case IAppConfig.BACKSPACE:
                if (value.toString().length() > 0){
                    String newValue = value.substring(0,value.toString().length()-1);
                    value = new StringBuilder(newValue);
                    event.window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setText(value.toString());
                }
                break;
            default:
                if (status.equals(SellStatus.WAIT_VALUE)){
                    value.append(event.character);
                    event.window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setText(value.toString());
                }
                break;
        }
    }

    private void searchProduct(AppEvent event){
        ISaleService saleService = controller.getFactory().getSaleService();
        Product product = saleService.findProductByBarcode(value.toString());
        AthenaWindowHelper.showProduct(event.window,product);

    }

    @Override
    public void activated(AppEvent event) {
        AthenaWindowHelper.refresh(event.window, controller, status);
    }
}
