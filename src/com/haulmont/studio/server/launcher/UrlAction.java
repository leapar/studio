package com.haulmont.studio.server.launcher;

import com.google.common.base.Strings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UrlAction implements EventHandler<ActionEvent> {
    private AppStartWindow window;
    UrlAction(AppStartWindow var1) {
        this.window = var1;
    }


    @Override
    public void handle(ActionEvent event) {
        if (!Strings.isNullOrEmpty(this.window.urlTextField.getText())) {
            this.window.app.getHostServices().showDocument(this.window.urlTextField.getText());
        }
    }
}