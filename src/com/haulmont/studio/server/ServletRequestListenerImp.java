package com.haulmont.studio.server;


import java.util.concurrent.ExecutionException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

class ServletRequestListenerImp implements ServletRequestListener {
    private WebServer webServer;

    ServletRequestListenerImp(WebServer var1) {
        this.webServer = var1;
    }

    public void requestDestroyed(ServletRequestEvent var1) {
    }

    public void requestInitialized(ServletRequestEvent var1) {
        try {
            if (this.webServer.getConfig().isRemoteEnable() == false && this.webServer.loadingCache.get(var1.getServletRequest().getRemoteAddr()).booleanValue() == false) {
                throw new RuntimeException("Remote connection is not permitted");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

