package com.haulmont.studio.server;

import com.google.common.primitives.Ints;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class Main  {
    public static void main(String[] args) {
        if (!checkJava()) {
            JDialog.setDefaultLookAndFeelDecorated(true);
            JDialog var4 = new JDialog();
            var4.setAlwaysOnTop(true);
            var4.pack();
            var4.setDefaultCloseOperation(2);
            JOptionPane.showMessageDialog(var4, "<html>Studio requires Java SE Development Kit (JDK) 8(minimum 1.8.0_45). <br />Please install the latest JDK and check JAVA_HOME. <br /><br />Your JDK version: " + System.getProperty("java.version"), "Failed run", 0);
            System.exit(-1);
        } else {
            /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();*/
            boolean var1 = checkArg("-nogui", args);
            if (!var1 && !GraphicsEnvironment.isHeadless()) {
                System.out.println("Run Studio GUI");
                run();
            } else {
                System.out.println("Run Studio headless");
                String var2 = b("-port", args);
                Integer var3 = var2 == null ? null : Ints.tryParse(var2);
                startServer(var3, checkArg("-electron", args));
            }
        }
    }

    @Nullable
    private static String b(String var0, String... var1) {
        String[] var2 = var1;
        int var3 = var1.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if (var5.startsWith(var0 + "=")) {
                return var5.split("=")[1];
            }
        }

        return null;
    }

    private static void startServer(Integer var0, boolean var1) {
        WebServer var2 = new WebServer();
        var2.init();

        try {
            var2.start(var0, var1);
        } catch (Throwable var4) {
            System.out.println("Exception in headless mode " + var4);
            var4.printStackTrace();
            System.exit(-1);
        }

    }

    private static boolean checkArg(String var0, String... var1) {
        String[] var2 = var1;
        int var3 = var1.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if (Objects.equals(var5, var0)) {
                return true;
            }
        }

        return false;
    }

    private static void run() {
        com.haulmont.studio.server.App.launch(com.haulmont.studio.server.App.class, new String[0]);
    }

    private static boolean checkJava() {
        String ver = System.getProperty("java.version");
        return ver != null && cmpVersion(ver, "1.8.0_45") >= 0;
    }

    public static int cmpVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        } else {
            Pattern var2 = Pattern.compile("[\\._]");
            String[] var3 = var2.split(v1);
            String[] var4 = var2.split(v2);

            for(int var5 = 0; var5 < var3.length; ++var5) {
                if (var4.length <= var5) {
                    return -1;
                }

                String var6 = var3[var5];
                String var7 = var4[var5];
                if (!var6.equals(var7)) {
                    try {
                        Integer var8 = Integer.valueOf(var6);
                        Integer var9 = Integer.valueOf(var7);
                        return var8.intValue() - var9.intValue();
                    } catch (NumberFormatException var10) {
                        return var6.compareTo(var7);
                    }
                }
            }

            return var3.length - var4.length;
        }
    }

}
