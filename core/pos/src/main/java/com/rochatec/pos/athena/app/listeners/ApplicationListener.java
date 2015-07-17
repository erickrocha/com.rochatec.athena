package com.rochatec.pos.athena.app.listeners;

import com.rochatec.pos.athena.app.event.AppEvent;

/**
 * Created by epr on 16/07/15.
 */
public interface ApplicationListener {

    void execute(AppEvent event);

    void activated(AppEvent event);
}
