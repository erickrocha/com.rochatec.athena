package com.rochatec.pos.athena.app;

import com.rochatec.pos.athena.model.Box;
import com.rochatec.pos.athena.model.Operator;

/**
 * Created by epr on 02/07/15.
 */
public class ApplicationManager {

    private Operator operator;

    private Box box;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}
