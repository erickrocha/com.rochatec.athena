package com.rochatec.pos.athena.app.listeners;

import com.rochatec.pos.athena.app.event.FunctionEvent;

/**
 * Created by epr on 07/07/15.
 */
public interface FunctionListener {

    void execute(FunctionEvent event);

}
