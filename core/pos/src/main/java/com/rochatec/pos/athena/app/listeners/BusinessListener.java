package com.rochatec.pos.athena.app.listeners;

import com.rochatec.pos.athena.app.event.BusinessEvent;

/**
 * Created by epr on 07/07/15.
 */
public interface BusinessListener {

    public void execute(BusinessEvent event);

}
