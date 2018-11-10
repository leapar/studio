package com.haulmont.studio.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.haulmont.studio.ui.k.d;

class ServletContextListenerImp implements ServletContextListener {
    private WebServer webServer;
    ServletContextListenerImp(WebServer webServer) {
        this.webServer = webServer;
    }

    public void contextInitialized(ServletContextEvent var1) {
        this.webServer.logger.debug("ServletContext initialized: " + var1.getServletContext());
        d var2 = new d();
        var2.a(this.webServer.server);
        var1.getServletContext().setAttribute("ServerBeans", var2);
    }

    public void contextDestroyed(ServletContextEvent var1) {
        this.webServer.logger.debug("ServletContext destroyed: " + var1.getServletContext());
        d var2 = (d)var1.getServletContext().getAttribute("ServerBeans");
        if (var2 != null) {
            var2.b();
        }

    }
}
