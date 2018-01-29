package com.haulmont.studio.server.launcher;

import com.google.common.base.Strings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TimerEventHandler implements EventHandler<ActionEvent> {
    private int c;
    private AppStartWindow window;
    private String title;

    TimerEventHandler(AppStartWindow var1, String var2) {
        this.window = var1;
        this.title = var2;
    }

    @Override
    public void handle(ActionEvent event) {
        this.window.statusValueLab.setText(this.title + Strings.repeat(".", ++this.c));
        if (this.c > 10) {
            this.c = 0;
        }
    }
}
