package com.rochatec.pos.athena.app.window;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.composite.ProductComposite;
import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.app.model.SellStatus;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.ItemSale;
import com.rochatec.pos.athena.model.Product;
import com.rochatec.pos.athena.utils.Formatter;
import com.rochatec.pos.athena.utils.Messages;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;

import java.math.BigDecimal;

/**
 * Created by epr on 16/07/15.
 */
public class AthenaWindowHelper {

    public static void refresh(AthenaApplicationWindow window, ApplicationController controller, SellStatus status) {
        CLabel welcomeText = null;
        switch (controller.getStatus()) {
            case CLOSED:
                welcomeText = window.getCLabel(IAppConfig.GUI_WELCOME_MESSAGE);
                welcomeText.setText(Messages.getMessage("com.rochatec.pos.athena.model.closed"));
                break;
            case PARTIAL_CLOSED:
                welcomeText = window.getCLabel(IAppConfig.GUI_WELCOME_MESSAGE);
                welcomeText.setText(Messages.getMessage("com.rochatec.pos.athena.model.partial.closed"));
                break;
            case OPEN:
                welcomeText = window.getCLabel(IAppConfig.GUI_WELCOME_MESSAGE);
                welcomeText.setText(Messages.getMessage("com.rochatec.pos.athena.model.open"));
                CLabel operatorText = window.getCLabel(IAppConfig.GUI_FOOTER_OPERATOR);
                operatorText.setText(controller.getOperator().getName());
                CLabel ecfText = window.getCLabel(IAppConfig.GUI_FOOTER_ECF);
                ecfText.setText(controller.getBox().getEcf());
                if (status.equals(SellStatus.WAIT_VALUE)) {
                    window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setVisible(true);
                } else {
                    window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setVisible(false);
                }
                break;
        }
    }

    public static void showProduct(AthenaApplicationWindow window, Product product) {
        Text txtQuantity = window.getText(IAppConfig.GUI_SELL_QUANTITY);
        Text txtDescription = window.getText(IAppConfig.GUI_SELL_DESCRIPTION);
        Text txtPrice = window.getText(IAppConfig.GUI_SELL_PRICE);
        Text txtSubTotal = window.getText(IAppConfig.GUI_SELL_SUBTOTAL);
        CLabel txtBarcode = window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE);
        try {
            txtDescription.setText(product.getShortName());
            txtPrice.setText(Formatter.getCurrency().mask(product.getSellPrice()));
            BigDecimal quantity = Formatter.getWeight().parse(txtQuantity.getText().trim());
            BigDecimal subTotal = quantity.multiply(product.getSellPrice());
            txtSubTotal.setText(Formatter.getCurrency().mask(subTotal));
        } catch (BadFormatException ex) {

        } finally {
            txtBarcode.setText("");
        }

    }

    public static void clear(AthenaApplicationWindow window) {
        Text txtQuantity = window.getText(IAppConfig.GUI_SELL_QUANTITY);
        Text txtDescription = window.getText(IAppConfig.GUI_SELL_DESCRIPTION);
        Text txtPrice = window.getText(IAppConfig.GUI_SELL_PRICE);
        CLabel txtBarcode = window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE);
        txtDescription.setText("");
        txtPrice.setText("");
        txtBarcode.setText("");
        txtQuantity.setText("1.000");
    }

    public static void writeTicket(AthenaApplicationWindow window, Integer count, ItemSale itemSale) {
        ScrolledComposite ticketItem = window.getScrolledComposite(IAppConfig.GUI_SELL_CUPOM);
        ProductComposite productComposite = new ProductComposite(ticketItem);
        productComposite.setItemSale(itemSale);
        ticketItem.setContent(productComposite.getComposite());
        ticketItem.layout(true);
    }

    private static String getCountFormated(Integer count) {
        if (count >= 1 || count <= 9) {
            return "00" + count.toString();
        } else if (count >= 10 || count <= 99) {
            return "0" + count.toString();
        } else {
            return count.toString();
        }
    }

    private static String getDescriptionFormated(String name) {
        if (name.length() > 50) {
            return name.substring(0, 50);
        } else if (name.length() < 50) {
            for (int i = name.length(); i < 50; i++) {
                name = name + " ";
            }
            return name;
        } else {
            return name;
        }
    }

}
