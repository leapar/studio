//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.haulmont.studio.server;

import com.haulmont.studio.server.launcher.AppStartWindow;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application {
    public static final String a = "/com/haulmont/studio/server/launcher/launcher.css";
    private Logger logger;
    private Config config;
    private WebServer webServer;
    private Stage stage;
    private AppStartWindow window;
    private TrayIcon trayIcon;
    private MenuItem menuItem;

    public App() {
    }

    public void initConfig() {
        this.webServer.initConfig();
        this.config = this.webServer.getConfig();
    }

    public void start(Stage stage) {
        this.webServer = new WebServer();
        this.webServer.init();
        this.config = this.webServer.getConfig();
        this.logger = LoggerFactory.getLogger(this.getClass());
        FXMLLoader var2 = new FXMLLoader();
        var2.setLocation(this.getClass().getResource("/com/haulmont/studio/server/launcher/app_start_window.fxml"));
        Parent var3 = null;
        try {
            var3 = (Parent)var2.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.stage = stage;
        Scene var4 = new Scene(var3, -1.0D / 0.0, -1.0D);
        var4.getStylesheets().add("/com/haulmont/studio/server/launcher/launcher.css");
        stage.setScene(var4);
        this.window = (AppStartWindow)var2.getController();
        this.window.init(this, stage);
        this.initScreen(stage);
        this.show(stage);
    }

    public void initScreen(Stage var1) {
        if (this.isSupported()) {
            Platform.setImplicitExit(false);
            SystemTray var2 = SystemTray.getSystemTray();
            BufferedImage var3 = null;
            try {
                var3 = ImageIO.read(this.getClass().getResource("launcher/studio16.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            var1.setOnCloseRequest((var2x) -> {
                Platform.runLater(() -> {
                    if (SystemTray.isSupported()) {
                        var1.hide();
                        if (SystemUtils.IS_OS_WINDOWS && !this.config.isNotifyShown()) {
                            this.trayIcon.displayMessage("克里特科技开发平台", "应用保持运行，可以通过托盘图标控制.", MessageType.INFO);
                            this.config.setNotifyShown(true);
                        }
                    } else {
                        System.exit(0);
                    }

                });
            });
            PopupMenu var4 = new PopupMenu();
            MenuItem var5 = new MenuItem("www.sketchcub.com");
            var5.addActionListener((var1x) -> {
                Platform.runLater(this::bringFront);
            });
            
            var4.add(var5);
            var4.addSeparator();
            this.menuItem = new MenuItem("open url");
            this.menuItem.setEnabled(false);
            this.menuItem.addActionListener((var1x) -> {
                Platform.runLater(() -> {
                    this.getHostServices().showDocument(this.getUrl());
                });
            });
            var4.add(this.menuItem);
            var4.addSeparator();
            MenuItem var6 = new MenuItem("exit");
            var6.addActionListener((var1x) -> {
                this.exit();
            });
            var4.add(var6);
            this.trayIcon = new TrayIcon(var3, "克里特科技开发平台", var4);
            this.trayIcon.setImageAutoSize(true);
            this.trayIcon.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent var1) {
                    if (var1.getButton() == 1) {
                        Platform.runLater(()->{
                            App.this.bringFront();
                        });
                    }

                }
            });

            try {
                var2.add(this.trayIcon);
            } catch (AWTException var8) {
                this.logger.error("Tray icon error:", var8);
            }
        }

    }

    public void bringFront() {
        if (this.isSupported()) {
            this.stage.show();
            this.stage.toFront();
        }

    }

    private void show(Stage var1) {
        if (this.isSupported() && this.config.isSilentMode()) {
            this.window.start();
            this.trayIcon.displayMessage("克里特科技开发平台", "服务正在启动...", MessageType.NONE);
        } else {
            var1.show();
        }

    }

    public void openUrl() {
        if (this.isSupported() && this.config.isSilentMode()) {
            this.getHostServices().showDocument(this.getUrl());
        }

    }

    public String getUrl() {
        return "http://localhost:" + this.config.getPort() + "/studio";
    }

    public void a(boolean var1) {
        if (this.isSupported() && this.menuItem != null) {
            this.menuItem.setEnabled(var1);
        }

    }

    public Config getConfig() {
        return this.config;
    }

    public boolean isRunning() {
        return this.webServer.isRunning();
    }

    public void start() {
        this.webServer.start((Integer)null, false);
    }

    public void stop() {
        this.webServer.stop();
    }

    public void exit() {
        if (this.webServer.isRunning()) {
            this.webServer.stop();
        }

        this.config.load();
        this.config.setSilentMode(this.window.isSilent());
        this.config.setRemoteEnable(this.window.isRemoteAble());
        Integer var1 = this.window.getPort();
        if (var1 != null) {
            this.config.setPort(var1.intValue());
        }

        this.config.save();
        System.exit(0);
    }

    public boolean isSupported() {
        return SystemTray.isSupported() && SystemUtils.IS_OS_WINDOWS;
    }
}
