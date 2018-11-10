package com.haulmont.studio.server;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Closeables;
import com.haulmont.studio.common.H;
import com.haulmont.studio.ui.app.aH;
import com.vaadin.server.communication.JSR356WebsocketInitializer;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import org.eclipse.jetty.server.Handler;
import com.vaadin.ui.UIDetachedException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ShutdownHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.gradle.api.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class WebServer {
    public Logger logger;
    private Config config;
    public org.eclipse.jetty.server.Server server;
    public LoadingCache<String, Boolean> loadingCache = CacheBuilder.newBuilder().maximumSize(100L).build(new CacheLoader<String, Boolean>() {
        @Override
        public Boolean load(String s) throws Exception {
            try {
                InetAddress var2 = InetAddress.getByName(s);
                return var2.isAnyLocalAddress() || var2.isLoopbackAddress() || NetworkInterface.getByInetAddress(var2) != null;
            } catch (UnknownHostException | SocketException var3) {
                logger.warn("Error checking remote address", var3);
                return false;
            }
        }
    });
    private boolean isRunning;

    public WebServer() {
    }

    public void start(@Nullable Integer var1, boolean var2) {
        this.server = new org.eclipse.jetty.server.Server(var1 != null ? var1.intValue() : this.config.getPort());
        WebAppContext var3 = new WebAppContext();
        var3.setContextPath("/studio");
        var3.setInitParameter("productionMode", "true");
        var3.setInitParameter("runFromElectron", String.valueOf(var2));
        Path var4 = H.a();
        if (!Files.exists(var4, new LinkOption[0])) {
            throw new IllegalStateException("Invalid web resources path: " + var4);
        } else {
            var3.setResourceBase(var4.toString());
            ServletHolder var5 = new ServletHolder(new aH());
            var5.setAsyncSupported(true);
            var5.setInitParameter("UI", "com.haulmont.studio.ui.app.App");
            var5.setInitParameter("UIProvider", "com.haulmont.studio.ui.app.StudioUIProvider");
            var5.setInitParameter("widgetset", "com.haulmont.studio.ui.StudioApplicationWidgetset");
            var5.setInitParameter("org.atmosphere.websocket.maxIdleTime", "3600000");
            if (var2) {
                var5.setInitParameter("heartbeatInterval", "-1");
            }
            var3.addServlet(var5, "/*");
            var3.addServlet(var5, "/VAADIN/*");
            var3.addEventListener(new JSR356WebsocketInitializer());
            var3.addEventListener(new ServletContextListenerImp(this));
            var3.addEventListener(new HttpSessionListenerImp(this));
            var3.addEventListener(new ServletRequestListenerImp(this));
            ShutdownHandler var6 = new ShutdownHandler("studio", true, false);
            HandlerList var7 = new HandlerList();
            var7.setHandlers(new Handler[]{var3, var6});

            this.server.setHandler(var7);

            try {
                this.server.start();
            } catch (Exception var9) {
                throw new RuntimeException("Unable to start server", var9);
            }

            var3.getSessionHandler().getSessionManager().setMaxInactiveInterval(var2 ? -1 : 86400);
            this.isRunning = true;
        }
    }

    public void stop() {
        try {
            if (this.server != null)
            this.server.stop();
        } catch (Exception var2) {
            this.logger.warn("Error stopping the server", var2);
        }

        this.isRunning = false;
    }

    protected void init() {
        this.initUserHome();
        this.initLogger();
        this.setExceptionHandler();
        this.initConfig();
    }

    private void initUserHome() {
        if (System.getProperty("studio.user.home") == null) {
            Path var1 = Paths.get(System.getProperty("user.home"), ".haulmont", "studio");
            System.setProperty("studio.user.home", var1.toFile().getAbsolutePath());
        }

    }

    private void initLogger() {
        InputStream var1 = null;

        try {
            var1 = WebServer.class.getResourceAsStream("/logging.properties");
            if (var1 != null) {
                try {
                    LogManager.getLogManager().readConfiguration(var1);
                } catch (IOException var6) {
                    System.out.println("Error configuring JUL: " + var6.getMessage());
                }
            }
        } finally {
            Closeables.closeQuietly(var1);
        }

        SLF4JBridgeHandler.install();
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.logger.info("CUBA Studio v." + H.i() + " built on " + H.j());
    }

    private void setExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof UIDetachedException) {
                    logger.debug("UIDetachedException", e);
                } else {
                    logger.error("Exception in thread " + t, e);
                }
            }
        });
    }

    public void initConfig() {
        this.config = new Config();
    }

    public Config getConfig() {
        return this.config;
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}

