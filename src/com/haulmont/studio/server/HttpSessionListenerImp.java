package com.haulmont.studio.server;

import com.haulmont.studio.ui.k.d;
import com.haulmont.studio.ui.app.App;
import com.haulmont.studio.ui.app.ax;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


class HttpSessionListenerImp implements HttpSessionListener {
    private WebServer webServer;
    HttpSessionListenerImp(WebServer var1) {
        this.webServer = var1;
    }

    public void sessionCreated(HttpSessionEvent var1) {
        this.webServer.logger.debug("HttpSession created: " + var1.getSession());
        ax var2 = this.getBean(var1);
        if (var2 != null) {
            var2.sessionCreated(var1);
        }

    }

    public void sessionDestroyed(HttpSessionEvent var1) {
        this.webServer.logger.debug("HttpSession destroyed: " + var1.getSession());
        App var2 = (App)var1.getSession().getAttribute("StudioApp");
        if (var2 != null) {
            var2.b();
        }

        ax var3 = this.getBean(var1);
        if (var3 != null) {
            var3.sessionDestroyed(var1);
        }

    }

    private ax getBean(HttpSessionEvent var1) {
        d var2 = (d)var1.getSession().getServletContext().getAttribute("ServerBeans");
        return var2 != null ? (ax)var2.a(ax.class) : null;
    }
}

