//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.haulmont.studio.backend.license;

import com.google.common.base.Strings;
import com.haulmont.studio.backend.g.d;
import com.haulmont.studio.common.C;
import com.haulmont.studio.common.H;
import com.haulmont.studio.common.L;
import com.haulmont.studio.common.e;
import com.haulmont.studio.common.p;

import com.haulmont.studio.common.model.base.BaseProject;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.haulmont.studio.common.model.base.BaseProject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.conn.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionManager {
    private static final int c = 5000;
    public static final String a = "studio.subs.log";
    private static final Logger d = LoggerFactory.getLogger(SubscriptionManager.class);
    private static final int e = 600;
    public static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd");
    private String g;
    private ScheduledExecutorService h;
    private e i;

    public SubscriptionManager(e var1) {
        this.i = var1;
    }

    @PostConstruct
    public void a() {
        this.h = Executors.newSingleThreadScheduledExecutor();
        this.h.scheduleAtFixedRate(this::i, 0L, 600L, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void b() {
        this.h.shutdownNow();
    }

    public d c() {
        Path var1 = this.j();
        if (!Files.exists(var1, new LinkOption[0])) {
            return com.haulmont.studio.backend.g.d.FILE_NOT_FOUND;
        } else {
            String var2 = L.b(var1);
            com.haulmont.studio.backend.license.d var3 = this.f(var2);
            if (var3 == null) {
                return com.haulmont.studio.backend.g.d.INVALID_FILE;
            } else if (!var3.g() && !var3.e().before(new Date())) {
                return var3.f() ? com.haulmont.studio.backend.g.d.SUBSCRIPTION_BANNED : com.haulmont.studio.backend.g.d.NO_ERROR;
            } else {
                return com.haulmont.studio.backend.g.d.SUBSCRIPTION_EXPIRED;
            }
        }
    }

    public SubscriptionManager$ActivationResult a(String var1) {
        try {
            String var2 = this.c(var1);
            if (!Strings.isNullOrEmpty(var2)) {
                Path var3 = this.j();
                Files.write(var3, var2.getBytes(), new OpenOption[0]);
                return SubscriptionManager$ActivationResult.SUCCESS;
            } else {
                return SubscriptionManager$ActivationResult.ACTIVATION_FAILED;
            }
        } catch (Exception var5) {
            this.a(var5);
            return SubscriptionManager$ActivationResult.ACTIVATION_FAILED;
        }
    }

    private void i() {
        d var1 = this.c();
        if (var1 == com.haulmont.studio.backend.g.d.NO_ERROR || var1 == com.haulmont.studio.backend.g.d.SUBSCRIPTION_EXPIRED) {
            String var2 = this.f();
            if (var2 == null) {
                return;
            }

            try {
                String var3 = this.c(var2);
                if (!Strings.isNullOrEmpty(var3)) {
                    Files.write(this.j(), var3.getBytes(), new OpenOption[0]);
                }
            } catch (Exception var4) {
                this.a(var4);
            }

        }
    }



    @Nullable
    private String c(String var1) {

        return var1;
        /*
        String var2 = this.e(var1);
        SystemDefaultRoutePlanner var3 = new SystemDefaultRoutePlanner(ProxySelector.getDefault());
        RequestConfig var4 = RequestConfig.custom().setConnectTimeout(5000).build();
        CloseableHttpClient var5 = HttpClients.custom().useSystemProperties().setDefaultCredentialsProvider(p.b(var2)).setDefaultRequestConfig(var4).setRoutePlanner(var3).build();
        Throwable var6 = null;

        String var11;
        try {
            HttpGet var7 = new HttpGet(var2);
            CloseableHttpResponse var8 = var5.execute(var7);
            Throwable var9 = null;

            try {
                int var10 = var8.getStatusLine().getStatusCode();
                if (var10 == 200) {
                    var11 = EntityUtils.toString(var8.getEntity());
                    if (this.i.u() && (!b(var11) || !this.d(var11))) {
                        e.error("Incorrect sif content: " + var11);
                        throw new h("Incorrect sif content");
                    }

                    String var12 = var11;
                    return var12;
                }

                if (var10 == 503) {
                    throw new b(this);
                }

                var11 = null;
            } catch (Throwable var38) {
                var9 = var38;
                throw var38;
            } finally {
                if (var8 != null) {
                    if (var9 != null) {
                        try {
                            var8.close();
                        } catch (Throwable var37) {
                            var9.addSuppressed(var37);
                        }
                    } else {
                        var8.close();
                    }
                }

            }
        } catch (Throwable var40) {
            var6 = var40;
            throw var40;
        } finally {
            if (var5 != null) {
                if (var6 != null) {
                    try {
                        var5.close();
                    } catch (Throwable var36) {
                        var6.addSuppressed(var36);
                    }
                } else {
                    var5.close();
                }
            }

        }

        return var11;*/
    }
    private boolean d(String var1) {
        return var1.length() == 354;
    }

    public static boolean b(String var0) {
        Pattern var1 = Pattern.compile("[^ ]*");
        Pattern var2 = Pattern.compile("[\\x00-\\x7F]*");
        return var1.matcher(var0).matches() && var2.matcher(var0).matches();
    }

    private String e(String var1) {
        String var2 = new String(new byte[]{104, 116, 116, 112, 115, 58, 47, 47, 99, 104, 101, 99, 107, 115, 105, 100, 46, 99, 117, 98, 97, 45, 112, 108, 97, 116, 102, 111, 114, 109, 46, 99, 111, 109});
        String var3 = new String(new byte[]{115, 105, 100});
        String var4 = new String(new byte[]{108, 105, 100});
        var2 = "https://checksid.cuba-platform.com";
        var3 = "sid";
        var4 = "lid";

        return var2 + "?" + var3 + "=" + var1 + "&" + var4 + "=" + this.d();
    }

    public String d() {
        if (this.g != null) {
            return this.g;
        } else {
            String var1 = new String(new byte[]{117, 115, 101, 114, 46, 110, 97, 109, 101});
            var1 = "user.name";
            String var2 = System.getProperty(var1);
            String var3 = null;
            String var4 = null;

            try {
                InetAddress var5 = InetAddress.getLocalHost();
                var4 = var5.getHostName();
                /*
var4 = "wangxiaohua"
var5 = {Inet4Address@495} "wangxiaohua/172.26.32.1"
name:eth3 (Hyper-V Virtual Ethernet Adapter #4)
*/
                NetworkInterface var6 = NetworkInterface.getByInetAddress(var5);//name:eth3 (Hyper-V Virtual Ethernet Adapter #4)
                if (var6 != null) {
                    byte[] var7 = var6.getHardwareAddress();
                    if (var7 != null) {
                        var3 = DigestUtils.md5Hex(var7);//506fd8e420a1c021a611c3c19bea3202
                    } else {
                        var3 = DigestUtils.md5Hex(new byte[]{1, 1, 1, 1, 1, 1});
                    }
                } else {
                    var3 = DigestUtils.md5Hex(new byte[]{2, 2, 2, 2, 2, 2});
                }
            } catch (Exception var8) {
                this.a(var8);
            }

            String var9 = var2 + (var4 != null ? var4 : "") + (var3 != null ? var3 : "");//WXHwangxiaohua506fd8e420a1c021a611c3c19bea3202
            this.g = DigestUtils.md5Hex(var9.getBytes());//c70c46d9ae520870d24a1bd8efe2c9f9
            return this.g;
        }
    }

    private Path j() {
        String var1 = new String(new byte[]{115, 105, 102, 46, 100, 97, 116});//sif.dat
        return H.d().resolve(var1);
    }

    @Nullable
    public com.haulmont.studio.backend.license.d e() {
        Path var1 = this.j();
        if (!Files.exists(var1, new LinkOption[0])) {
            return null;
        } else {
            String var2 = L.b(var1);
            return this.f(var2);
        }
    }

    @Nullable
    private com.haulmont.studio.backend.license.d f(String var1) {
        try {
            if (true) {
                String var26 = "sid=170704000693-OOOYQOk9IJO1IFG\n" +
                        "type=C\n" +
                        "name=wang liulsha\n" +
                        "company=luilsh\n" +
                        "endDate=9918-07-04\n" +
                        "expired=0\n" +
                        "banned=0";

                Properties var27 = new Properties();
                var27.load(new StringReader(var26));
                return new com.haulmont.studio.backend.license.d(this, var27);
            }

            BigInteger var2 = new BigInteger("17369712262290647732768133445861332449863405383733306695896586821166245382729380222118948668590047591903813382253186640467063376463309880263824085810383552963627855603429835060435976633955217307266714318344160886538360012623239010786668755679438900124601074924850696725233212494777766999123952653273738958617798460338184668049410136792403729341479373919634041235053823478242208651592611582439749292909499663165109004083820192135244694907138372731716013807836312280426304459316963033144149631900633817073029029413556757588486052978078614048837784810650766996280232645714319416096306667876390555673421669667406990886847");
            BigInteger var3 = new BigInteger("65537");
            String var4 = new String(new byte[]{106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 115, 112, 101, 99, 46, 82, 83, 65, 80, 117, 98, 108, 105, 99, 75, 101, 121, 83, 112, 101, 99});
            String var5 = new String(new byte[]{106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 75, 101, 121, 70, 97, 99, 116, 111, 114, 121});
            String var6 = new String(new byte[]{103, 101, 116, 73, 110, 115, 116, 97, 110, 99, 101});
            String var7 = new String(new byte[]{82, 83, 65});
            String var8 = new String(new byte[]{103, 101, 110, 101, 114, 97, 116, 101, 80, 117, 98, 108, 105, 99});
            String var9 = new String(new byte[]{106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 115, 112, 101, 99, 46, 75, 101, 121, 83, 112, 101, 99});
            String var10 = new String(new byte[]{106, 97, 118, 97, 120, 46, 99, 114, 121, 112, 116, 111, 46, 67, 105, 112, 104, 101, 114});
            String var11 = new String(new byte[]{106, 97, 118, 97, 46, 115, 101, 99, 117, 114, 105, 116, 121, 46, 75, 101, 121});
            String var12 = new String(new byte[]{111, 114, 103, 46, 97, 112, 97, 99, 104, 101, 46, 99, 111, 109, 109, 111, 110, 115, 46, 99, 111, 100, 101, 99, 46, 98, 105, 110, 97, 114, 121, 46, 66, 97, 115, 101, 54, 52});
            String var13 = new String(new byte[]{100, 101, 99, 111, 100, 101, 66, 97, 115, 101, 54, 52});
            String var14 = new String(new byte[]{100, 111, 70, 105, 110, 97, 108});
            String var15 = new String(new byte[]{105, 110, 105, 116});
            Class var16 = Class.forName(var4);
            Constructor var17 = var16.getConstructor(BigInteger.class, BigInteger.class);
            Object var18 = var17.newInstance(var2, var3);
            Class var19 = Class.forName(var5);
            Object var20 = var19.getMethod(var6, String.class).invoke((Object)null, var7);
            Object var21 = var19.getMethod(var8, Class.forName(var9)).invoke(var20, var18);
            Class var22 = Class.forName(var10);
            Object var23 = var22.getMethod(var6, String.class).invoke((Object)null, var7);
            var22.getMethod(var15, Integer.TYPE, Class.forName(var11)).invoke(var23, Integer.valueOf(2), var21);
            Object var24 = Class.forName(var12).getMethod(var13, String.class).invoke((Object)null, var1);
            Object var25 = var22.getMethod(var14, byte[].class).invoke(var23, var24);
            String var26 = new String((byte[])((byte[])var25), StandardCharsets.UTF_8);
            Properties var27 = new Properties();
            var27.load(new StringReader(var26));
            return new com.haulmont.studio.backend.license.d(this, var27);
        } catch (Exception var28) {
            this.a(var28);
            return null;
        }
    }

    @Nullable
    public String f() {
        com.haulmont.studio.backend.license.d var1 = this.e();
        return var1 != null ? var1.a() : null;
    }

    @Nullable
    public C<String, String> g() {
        com.haulmont.studio.backend.license.d var1 = this.e();
        if (var1 != null) {
            String[] var2 = var1.a().split("-");
            return new C(var2[0], var2[1]);
        } else {
            return null;
        }
    }


    public void h() {
        Path var1 = this.j();

        try {
            Files.deleteIfExists(var1);
        } catch (IOException var3) {
            this.a((Exception) var3);
        }
    }



    public boolean a(Collection<BaseProject> var1) {
        List var2 = Arrays.asList("charts", "reports", "bpm", "fts", "ccpayments", "workflow");
        Iterator var3 = var1.iterator();

        BaseProject var4;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            var4 = (BaseProject)var3.next();
        } while(!var2.contains(var4.getName()) || com.haulmont.studio.backend.pm.b.a(var4));

        return true;
    }

    private void a(Exception var1) {
        if ("on".equals(System.getProperty(a))) {
            d.error(ExceptionUtils.getStackTrace(var1));
        }

    }
}
