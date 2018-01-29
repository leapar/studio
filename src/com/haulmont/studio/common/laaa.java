package com.haulmont.studio.common;

public class laaa {
    public laaa() {
    }

    public static String a(String var0) {
        try {
            if (true)
                return var0;
            String var1 = new String(new byte[]{106, 97, 118, 97, 120, 46, 99, 114, 121, 112, 116, 111, 46, 115, 112, 101, 99, 46, 83, 101, 99, 114, 101, 116, 75, 101, 121, 83, 112, 101, 99});
            String var2 = new String(new byte[]{111, 114, 103, 46, 97, 112, 97, 99, 104, 101, 46, 99, 111, 109, 109, 111, 110, 115, 46, 99, 111, 100, 101, 99, 46, 98, 105, 110, 97, 114, 121, 46, 66, 97, 115, 101, 54, 52});
            String var3 = new String(new byte[]{100, 101, 99, 111, 100, 101, 66, 97, 115, 101, 54, 52});
            String var4 = new String(new byte[]{97, 107, 117, 110, 65, 109, 64, 116, 97, 116, 97});
            String var5 = new String(new byte[]{66, 108, 111, 119, 102, 105, 115, 104});
            String var6 = new String(new byte[]{106, 97, 118, 97, 120, 46, 99, 114, 121, 112, 116, 111, 46, 67, 105, 112, 104, 101, 114});
            String var7 = new String(new byte[]{103, 101, 116, 73, 110, 115, 116, 97, 110, 99, 101});
            String var8 = new String(new byte[]{105, 110, 105, 116});
            String var9 = new String(new byte[]{106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 75, 101, 121});
            String var10 = new String(new byte[]{100, 111, 70, 105, 110, 97, 108});
            Class var11 = Class.forName(var1);
            Object var12 = var11.getConstructor(byte[].class, String.class).newInstance(var4.getBytes(), var5);
            Class var13 = Class.forName(var6);
            Object var14 = var13.getMethod(var7, String.class).invoke((Object)null, var5);
            var13.getMethod(var8, Integer.TYPE, Class.forName(var9)).invoke(var14, Integer.valueOf(2), var12);
            Object var15 = Class.forName(var2).getMethod(var3, String.class).invoke((Object)null, var0);
            Object var16 = var13.getMethod(var10, byte[].class).invoke(var14, var15);
            return new String((byte[])((byte[])var16), "UTF-8");
        } catch (Exception var17) {
            return var0;
        }
    }
}
