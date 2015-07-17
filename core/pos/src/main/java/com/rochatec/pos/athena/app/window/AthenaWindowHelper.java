package com.rochatec.pos.athena.app.window;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.app.model.SellStatus;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.Product;
import com.rochatec.pos.athena.utils.Formatter;
import com.rochatec.pos.athena.utils.Messages;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;

import java.math.BigDecimal;

/**
 * Created by epr on 16/07/15.
 */
public class AthenaWindowHelper {

    public static void refresh(AthenaApplicationWindow window, ApplicationController controller,SellStatus status) {
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
                if (status.equals(SellStatus.WAIT_QUANTITY) || status.equals(SellStatus.WAIT_PRODUCT)){
                    window.getCLabel(IAppConfig.GUI_FOOTER_LABEL_QUANTITY).setVisible(true);
                    window.getText(IAppConfig.GUI_FOOTER_BARCODE).setVisible(true);
                    window.getText(IAppConfig.GUI_FOOTER_QUANTITY).setVisible(true);
                }else{
                    window.getCLabel(IAppConfig.GUI_FOOTER_LABEL_QUANTITY).setVisible(false);
                    window.getText(IAppConfig.GUI_FOOTER_BARCODE).setVisible(false);
                    window.getText(IAppConfig.GUI_FOOTER_QUANTITY).setVisible(false);
                }
                break;
        }
    }

    public static void refresQuantity(AthenaApplicationWindow window, Character value) {
        Text text = window.getText(IAppConfig.GUI_SELL_QUANTITY);

        String newValue = String.valueOf(value);
        String oldValue = text.getText().trim().equals("0")? "":text.getText().trim();
        String finalValue = "";

        finalValue = oldValue + newValue;
        text.setText(finalValue);
    }

    public static void backspaceQuantity(AthenaApplicationWindow window){
        Text text = window.getText(IAppConfig.GUI_SELL_QUANTITY);
        Text label = window.getText(IAppConfig.GUI_FOOTER_QUANTITY);
        if (text.getText().length() > 1){
            text.setText(text.getText().trim().substring(0, text.getText().trim().length() - 1));
        }
        label.setText(text.getText());
    }

    public static void showProduct(AthenaApplicationWindow window,Product product){
        Text txtQuantity = window.getText(IAppConfig.GUI_SELL_QUANTITY);
        Text txtDescription = window.getText(IAppConfig.GUI_SELL_DESCRIPTION);
        Text txtPrice = window.getText(IAppConfig.GUI_SELL_PRICE);
        Text txtSubTotal = window.getText(IAppConfig.GUI_SELL_SUBTOTAL);
        Text txtBarcode = window.getText(IAppConfig.GUI_FOOTER_BARCODE);
        try {
            txtDescription.setText(product.getShortName());
            txtPrice.setText(Formatter.getCurrency().mask(product.getSellPrice()));
            BigDecimal quantity = Formatter.getWeight().parse(txtQuantity.getText().trim());
            BigDecimal subTotal = quantity.multiply(product.getSellPrice());
            txtSubTotal.setText(Formatter.getCurrency().mask(subTotal));
        }catch (BadFormatException ex){

        }finally {
            txtBarcode.setText("");
        }

    }

}
