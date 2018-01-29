package com.haulmont.studio.server;

import com.haulmont.studio.common.G;
import com.haulmont.studio.common.I;
import java.io.File;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
    private XMLConfiguration xmlConfiguration;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Config() {
        File var1 = G.d().resolve("config.xml").toFile();

        try {
            this.xmlConfiguration = new XMLConfiguration(var1);
        } catch (ConfigurationException var5) {
            this.logger.error("Error creating configuration", var5);

            try {
                I.d(var1.toPath());
                this.xmlConfiguration = new XMLConfiguration(var1);
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }

    }

    public void save() {
        try {
            this.xmlConfiguration.save();
        } catch (ConfigurationException var2) {
            this.logger.warn("Error saving configuration", var2);
        }

    }

    public int getPort() {
        String var1 = System.getProperty("studio.server.port");
        return this.xmlConfiguration.getInt("server.port", var1 == null ? 8111 : Integer.parseInt(var1));
    }

    public void setPort(int var1) {
        this.xmlConfiguration.setProperty("server.port", var1);
    }

    public boolean isRemoteEnable() {
        String var1 = System.getProperty("studio.connection.remote.enable");
        return this.xmlConfiguration.getBoolean("connection.remote.enable", var1 == null ? false : Boolean.valueOf(var1).booleanValue());
    }

    public void setRemoteEnable(boolean var1) {
        this.xmlConfiguration.setProperty("connection.remote.enable", var1);
    }

    public boolean isSilentMode() {
        String var1 = System.getProperty("studio.silentmode.enable");
        return this.xmlConfiguration.getBoolean("silentmode.enable", var1 == null ? false : Boolean.valueOf(var1).booleanValue());
    }

    public void setSilentMode(boolean var1) {
        this.xmlConfiguration.setProperty("silentmode.enable", var1);
    }

    public boolean isNotifyShown() {
        String var1 = System.getProperty("studio.silentmode.notification_shown");
        return this.xmlConfiguration.getBoolean("silentmode.notification_shown", var1 == null ? false : Boolean.valueOf(var1).booleanValue());
    }

    public void setNotifyShown(boolean var1) {
        this.xmlConfiguration.setProperty("silentmode.notification_shown", var1);
    }

    public void load() {
        this.xmlConfiguration.clear();

        try {
            this.xmlConfiguration.load();
        } catch (ConfigurationException var2) {
            this.logger.error("Error reload configuration", var2);
        }

    }
}
