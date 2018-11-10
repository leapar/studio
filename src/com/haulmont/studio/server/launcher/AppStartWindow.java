//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.haulmont.studio.server.launcher;

import com.haulmont.studio.common.H;
import com.haulmont.studio.server.App;
import com.vaadin.ui.UIDetachedException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Nullable;
import java.net.BindException;

public class AppStartWindow {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public App app;
    private Stage stage;
    @FXML
    public TextField portField;
    @FXML
    public Button startBtn;
    @FXML
    public Button exitBtn;
    @FXML
    public Label statusValueLab;
    @FXML
    public TextField urlTextField;
    @FXML
    private HBox urlBox;
    @FXML
    private Button urlCopyBtn;
    @FXML
    private Button urlOpenBtn;
    @FXML
    private CheckBox silentModeCb;
    @FXML
    private Label portLab;
    @FXML
    private Label silentModeLab;
    @FXML
    public Label remoteConnectionLab;
    @FXML
    public CheckBox remoteConnectionCb;
    private Timeline timeline;


    public Logger getLogger() {
        return this.logger;
    }

    public AppStartWindow() {
    }

    public void init(App app, Stage stage) {
        this.app = app;
        this.stage = stage;
        stage.setResizable(false);
        String title = "克里特科技开发平台";
        String var4 = H.i();
        if (!var4.equals("?")) {
            title = title + " v." + var4;
        }

        stage.setTitle(title);
        this.setIcon(stage);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                app.exit();
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof UIDetachedException) {
                    logger.debug("UIDetachedException", e);
                } else {
                    logger.error("Exception in thread " + t, e);
                    if (startBtn != null && startBtn.isDisable()) {
                        urlTextField.setText("");
                        initScreen(false);
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert var1 = new Alert(Alert.AlertType.ERROR);
                            var1.initOwner(stage.getOwner());
                            var1.setHeaderText((String) null);
                            String var2 = "Unexpected error";
                            String var3 = e.toString();
                            if (e.getCause() instanceof BindException) {
                                var2 = "Error";

                                var3 = e.getMessage() + "\n" + e.getCause() + "\n\nMost probably the port " + portField.getText() + " is used by another process,  try to shut it down manually";
                            }

                            var1.setTitle(var2);
                            var1.setContentText(var3);
                            DialogPane var4 = var1.getDialogPane();
                            var4.getStylesheets().add("/com/haulmont/studio/server/launcher/launcher.css");
                            app.bringFront();
                            var1.show();
                        }
                    });
                }
            }
        });
        this.initEvent();
    }

    private void initEvent() {
        this.portField.setText(String.valueOf(this.app.getConfig().getPort()));
        this.urlCopyBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Clipboard var2 = Clipboard.getSystemClipboard();
                ClipboardContent var3 = new ClipboardContent();
                var3.putString(urlTextField.getText());
                var2.setContent(var3);
            }
        });
        this.urlOpenBtn.setOnAction(new UrlAction(this));
        if (SystemUtils.IS_OS_LINUX) {
            this.urlBox.getChildren().remove(this.urlOpenBtn);
        }

        if (this.app.isSupported()) {
            this.silentModeLab.setVisible(true);
            this.silentModeCb.setVisible(true);
            this.silentModeLab.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    silentModeCb.setSelected(silentModeCb.isSelected());
                    silentModeCb.requestFocus();
                }
            });
            this.silentModeCb.setSelected(this.app.getConfig().isSilentMode());
        }

        this.remoteConnectionLab.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                remoteConnectionCb.setSelected(remoteConnectionCb.isSelected());
                remoteConnectionCb.requestFocus();
            }
        });
        this.remoteConnectionCb.setSelected(this.app.getConfig().isRemoteEnable());
        this.startBtn.setOnAction((event)->{
            start();
        });
        this.startBtn.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER && !event.isAltDown() && !event.isControlDown() && !event.isMetaDown() && !event.isShiftDown()) {
                startBtn.fire();
            }
        });
        this.exitBtn.setOnAction((event) -> {
            app.exit();
        });
        this.exitBtn.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER && !event.isAltDown() && !event.isControlDown() && !event.isMetaDown() && !event.isShiftDown()) {
                exitBtn.fire();
            }
        });

        this.startBtn.requestFocus();
        this.initScreen(false);
    }

    private void setIcon(Stage var1) {
        ObservableList var2 = var1.getIcons();
        var2.add(new Image(this.getClass().getResourceAsStream("studio16.png")));
        var2.add(new Image(this.getClass().getResourceAsStream("studio32.png")));
        var2.add(new Image(this.getClass().getResourceAsStream("studio128.png")));
    }

    private void initScreen(boolean var1) {
        boolean var2 = this.app.isRunning();
        boolean var3 = var2 || var1;
        this.startBtn.setText(var2 ? "停止" : "启动");
        this.startBtn.setDisable(var1);
        this.portLab.setDisable(var3);
        this.portField.setDisable(var3);
        this.remoteConnectionLab.setDisable(var3);
        this.remoteConnectionCb.setDisable(var3);
        this.urlCopyBtn.setDisable(!var2 || var1);
        this.urlOpenBtn.setDisable(!var2 || var1);
        this.app.a(var2 && !var1);
        this.statusValueLab.getStyleClass().remove("status-checking");
        this.statusValueLab.getStyleClass().remove("status-running");
        this.statusValueLab.getStyleClass().remove("status-stopped");
        String var4;
        if (var1) {
            var4 = "启动中...";
            this.statusValueLab.setText(var4);
            this.timeline = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(500.0D), new TimerEventHandler(this, var4), new KeyValue[0])});
            this.timeline.setCycleCount(-1);
            this.timeline.play();
            this.statusValueLab.getStyleClass().add("status-checking");
        } else {
            var4 = "空闲";
            if (var2) {
                var4 = SystemUtils.IS_OS_LINUX ? "运行中 (复制 URL地址到浏览器中)" : "运行中";
            }

            this.statusValueLab.setText(var4);
            if (this.timeline != null) {
                this.timeline.stop();
            }

            this.timeline = null;
            if (var2) {
                this.startBtn.requestFocus();
                this.statusValueLab.getStyleClass().add("status-running");
            } else {
                this.statusValueLab.getStyleClass().add("status-stopped");
            }
        }

    }

    private int getPort(String var1) {
        int var2 = 0;

        try {
            var2 = Integer.parseInt(var1);
        } catch (NumberFormatException var5) {
            ;
        }

        if (var2 > 0 && var2 < 65534) {
            return var2;
        } else {
            Alert var3 = new Alert(AlertType.ERROR);
            var3.initOwner(this.stage.getOwner());
            var3.setHeaderText((String) null);
            var3.setContentText("Invalid port");
            DialogPane var4 = var3.getDialogPane();
            var4.getStylesheets().add("/com/haulmont/studio/server/launcher/launcher.css");
            this.app.bringFront();
            var3.show();
            return 0;
        }
    }

    private boolean checkParam() {
        int var1 = this.getPort(this.portField.getText());
        if (var1 == 0) {
            return false;
        } else {
            this.app.getConfig().setPort(var1);
            this.app.getConfig().setSilentMode(this.silentModeCb.isSelected());
            this.app.getConfig().setRemoteEnable(this.remoteConnectionCb.isSelected());
            return true;
        }
    }

    public void start() {
        if (this.app.isRunning()) {
            this.app.stop();
            this.app.initConfig();
            this.urlTextField.setText("");
            this.initScreen(false);
        } else if (this.checkParam()) {
            this.app.getConfig().save();
            this.initScreen(true);
            this.app.start();
            this.urlTextField.setText(this.app.getUrl());
            this.initScreen(false);
            this.app.openUrl();
        } else {
            this.initScreen(false);
        }

    }

    public boolean isSilent() {
        return this.silentModeCb.isSelected();
    }

    public boolean isRemoteAble() {
        return this.remoteConnectionCb.isSelected();
    }

    @Nullable
    public Integer getPort() {
        String var1 = this.portField.getText();

        try {
            return new Integer(var1);
        } catch (NumberFormatException var3) {
            return null;
        }
    }
}
