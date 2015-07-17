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

    public ApplicationListenerImpl(ApplicationController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(AppEvent event) {

        switch (event.keyCode) {
            case SWT.F10:
                event.window.updateScreen(SellComposite.ID);
                event.window.getCLabel(IAppConfig.GUI_FOOTER_LABEL_QUANTITY).setVisible(true);
                event.window.getText(IAppConfig.GUI_FOOTER_BARCODE).setVisible(true);
                event.window.getText(IAppConfig.GUI_FOOTER_QUANTITY).setVisible(true);
                event.window.getText(IAppConfig.GUI_FOOTER_QUANTITY).setFocus();
                event.window.getText(IAppConfig.GUI_SELL_QUANTITY).setText("");
                status = SellStatus.WAIT_QUANTITY;
                break;
            case SWT.F11:
                event.window.updateScreen(PaymentComposite.ID);
                break;
        }
        if (status.equals(SellStatus.WAIT_QUANTITY)) {
            if (event.keyCode == IAppConfig.KEY_ENTER || event.keyCode == IAppConfig.KEY_ENTER_NUMERICO) {
                status = SellStatus.WAIT_PRODUCT;
                event.window.getText(IAppConfig.GUI_FOOTER_QUANTITY).setText("");
                event.window.getText(IAppConfig.GUI_SELL_QUANTITY).setText("");
                Text text = event.window.getText(IAppConfig.GUI_FOOTER_BARCODE);
                text.setFocus();
            }
        }
        if (status.equals(SellStatus.WAIT_PRODUCT)) {
            Text text = event.window.getText(IAppConfig.GUI_FOOTER_BARCODE);
            if (!text.getText().trim().equals("")) {
                ISaleService saleService = controller.getFactory().getSaleService();
                Product product = saleService.findProductByBarcode(text.getText().trim());
                AthenaWindowHelper.showProduct(event.window, product);
                status = SellStatus.WAIT_QUANTITY;
                event.window.getText(IAppConfig.GUI_FOOTER_QUANTITY).setFocus();
            }
        }

    }

    @Override
    public void activated(AppEvent event) {
        AthenaWindowHelper.refresh(event.window, controller, status);
    }
}
