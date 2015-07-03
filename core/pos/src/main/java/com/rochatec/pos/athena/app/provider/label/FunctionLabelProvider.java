package com.rochatec.pos.athena.app.provider.label;

import com.rochatec.pos.athena.model.Function;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Created by epr on 03/07/15.
 */
public class FunctionLabelProvider extends LabelProvider implements ITableLabelProvider {

    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        Function function = (Function)element;
        switch (columnIndex){
            case 0:
                return function.getId().toString();
            case 1:
                return function.getName();
        }
        return null;
    }
}
