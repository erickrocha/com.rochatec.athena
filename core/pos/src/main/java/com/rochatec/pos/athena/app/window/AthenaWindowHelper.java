package com.rochatec.pos.athena.app.window;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.app.model.SellStatus;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.ItemSale;
import com.rochatec.pos.athena.model.Product;
import com.rochatec.pos.athena.utils.Formatter;
import com.rochatec.pos.athena.utils.Messages;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
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
                if (status.equals(SellStatus.WAIT_VALUE)){
                    window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setVisible(true);
                }else{
                    window.getCLabel(IAppConfig.GUI_FOOTER_BARCODE).setVisible(false);
                }
                break;
        }
    }

    public static void showProduct(AthenaApplicationWindow window,Product product){
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
        }catch (BadFormatException ex){

        }finally {
            txtBarcode.setText("");
        }

    }

    public static void writeTicketHeader(AthenaApplicationWindow window){
        StringBuilder builder = new StringBuilder();
        StyledText ticketItem = window.getStyledText(IAppConfig.GUI_SELL_CUPOM);
        builder.append("Item Descrição                            Quant X Preço Total");
        ticketItem.setText(builder.toString());
    }

    public static void writeTicket(AthenaApplicationWindow window,ItemSale itemSale){
        try{
            StyledText ticketItem = window.getStyledText(IAppConfig.GUI_SELL_CUPOM);
            StringBuilder builder = new StringBuilder();
            builder.append(itemSale.getProduct().getShortName()+" ");
            builder.append(Formatter.getWeight().mask(itemSale.getQuantity()));
            builder.append(" X ");
            builder.append(Formatter.getCurrency().mask(itemSale.getSellPrice()));
            builder.append(Formatter.getCurrency().mask(itemSale.getTotalItem()));
            ticketItem.setText(builder.toString());
        }catch (BadFormatException ex){

        }
    }


}
