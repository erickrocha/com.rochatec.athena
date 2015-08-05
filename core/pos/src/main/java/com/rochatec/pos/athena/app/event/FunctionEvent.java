package com.rochatec.pos.athena.app.event;

import com.rochatec.pos.athena.app.model.SellStatus;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Event;

/**
 * Created by epr on 07/07/15.
 */
public class FunctionEvent extends TypedEvent {

    public FunctionEvent(TypedEvent e) {
        super(e);
    }

    public Integer function;

    public SellStatus status;
}