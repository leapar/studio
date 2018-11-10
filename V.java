package com.haulmont.studio.backend.o;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.haulmont.studio.backend.b.k;
import com.haulmont.studio.backend.c.a;
import com.haulmont.studio.backend.c.c;
import com.haulmont.studio.backend.c.f;
import com.haulmont.studio.backend.ed.ddl.h;
import com.haulmont.studio.common.C;
import com.haulmont.studio.common.H;
import com.haulmont.studio.common.L;
import com.haulmont.studio.common.M;
import com.haulmont.studio.common.R;
import com.haulmont.studio.common.T;
import com.haulmont.studio.common.e;
import com.haulmont.studio.common.w;
import com.haulmont.studio.common.c.b;
import com.haulmont.studio.common.model.A;
import com.haulmont.studio.common.model.aM;
import com.haulmont.studio.common.model.aO;
import com.haulmont.studio.common.model.aU;
import com.haulmont.studio.common.model.aZ;
import com.haulmont.studio.common.model.ay;
import com.haulmont.studio.common.model.bF;
import com.haulmont.studio.common.model.bI;
import com.haulmont.studio.common.model.bN;
import com.haulmont.studio.common.model.bO;
import com.haulmont.studio.common.model.bb;
import com.haulmont.studio.common.model.bd;
import com.haulmont.studio.common.model.bx;
import com.haulmont.studio.common.model.m;
import com.haulmont.studio.common.model.b.J;
import com.haulmont.studio.common.model.b.N;
import com.haulmont.studio.common.model.b.n;
import com.haulmont.studio.common.model.b.r;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.sf.practicalxml.DomUtil;
import net.sf.practicalxml.xpath.XPathWrapper;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class V {
    private Logger a = LoggerFactory.getLogger(this.getClass());
    private a b;
    private d c;
    private e d;
    private com.haulmont.studio.backend.m.d e;

    public V(a var1, d var2, e var3, com.haulmont.studio.backend.m.d var4) {
        this.b = var1;
        this.c = var2;
        this.d = var3;
        this.e = var4;
    }

    public void a(aZ var1) {
        Path var2 = Paths.get(var1.getPath(), "settings.gradle");
        if (!var2.toFile().exists()) {
            throw new IllegalStateException("settings.gradle not found in " + var1.getPath());
        } else {
            String var3 = L.b(var2);
            c var4 = this.b.a(var3, "settings.gradle", bx.a);
            var1.getAppLock().a(new W(this, var1, var4));
        }
    }

    public void a(aZ var1, c var2) {
        c var3 = x.a(var2);
        if (var3 != null) {
            var1.setName(var3.h());
        } else {
            this.a.warn("Unable to parse project name from settings.gradle");
        }

    }

    private void d(aZ var1, c var2) {
        c var3 = x.p(var2, "modulePrefix");
        String var4 = null;
        if (var3 == com.haulmont.studio.backend.c.c.a) {
            var4 = x.b(var2);
        } else {
            c var5 = var3.c(f.a(124)).c(f.a(88));
            if (var5 != com.haulmont.studio.backend.c.c.a) {
                var4 = var5.h();
            }
        }

        var1.setModulePrefix(var4);
    }

    public void b(aZ var1) {
        String var2 = this.a(new File(var1.getPath()), "root-package");
        if (StringUtils.isNotBlank(var2)) {
            var1.setRootPackage(var2);
        } else {
            var2 = this.e(var1);
            if (StringUtils.isNotBlank(var2)) {
                var1.setRootPackage(var2);
            }

        }
    }

    private void a(aZ var1, c var2, String var3) {
        var1.getRepositories().clear();

        //try {
            Collection var4 = this.a(var2, var3, true);
            var1.setRepositories(new ArrayList(var4));
       /* } catch (b var5) {
            this.a.error(var5.getMessage(), var5);
        }*/

    }

    public Collection<bd> a(File var1, boolean var2) throws com.haulmont.studio.common.c.a {
        Path var3 = Paths.get(var1.toString(), "build.gradle");
        if (!Files.exists(var3, new LinkOption[0])) {
            throw new com.haulmont.studio.common.c.a(var3);
        } else {
            String var4 = L.b(var3);
            c var5 = this.b.a(var4, "build.gradle", bx.a);
            return this.a(var5, var4, var2);
        }
    }

    private Collection<bd> a(c var1, String var2, boolean var3) {
        List var4 = x.w(var1);
        ArrayList var5 = new ArrayList();
        boolean var6 = var3;
        Iterator var7 = var4.iterator();

        while(true) {
            c var8;
            String var10;
            do {
                do {
                    do {
                        do {
                            if (!var7.hasNext()) {
                               /* if (var6) {
                                    throw new b(var5);
                                }*/

                                return var5;
                            }

                            var8 = (c)var7.next();
                            c var9 = var8.b(x.a());
                            var10 = StringUtils.trimToEmpty(var9.h());
                        } while(StringUtils.isBlank(var10));
                    } while("http://maven.vaadin.com/vaadin-addons".equals(var10));
                } while("https://repo.cuba-platform.com/content/groups/premium".equals(var10));
            } while("https://cuba-platform.bintray.com/premium".equals(var10));

            bd var11 = this.c.b(var10);
            Object var12;
            if (var6 && var11 != null && this.a(var11)) {
                var12 = new m(var11);
                var6 = false;
            } else if (var11 == null) {
                var12 = new bd(var10, "", "");
                this.a((bd)var12, (c)var8);
            } else {
                var12 = var11;
            }

            w var13 = var8.a(var2);
            String var14 = var2.substring(var13.a(), var13.b());
            ((bd)var12).setSource(var14);
            if (var12 instanceof m) {
                c var15 = x.f(var8, "username");
                c var16 = x.f(var8, "password");
                if (var15 != com.haulmont.studio.backend.c.c.a && var16 != com.haulmont.studio.backend.c.c.a) {
                    w var17 = var15.a(var2);
                    String var18 = var2.substring(var17.a(), var17.b());
                    var17 = var16.a(var2);
                    String var19 = var2.substring(var17.a(), var17.b());
                    ((m)var12).setPresentationCredentials(new C(var18, var19));
                }
            }

            var5.add(var12);
        }
    }

    private boolean a(bd var1) {
        if (this.d.t()) {
            if (!this.c.a(var1, "platform").isEmpty()) {
                return true;
            }
        } else {
            String var2 = var1.getUrl();
            if (!var2.endsWith("/")) {
                var2 = var2 + "/";
            }

            var2 = var2 + "com/haulmont/cuba/cuba-global/maven-metadata.xml";

            try {
                String var3 = this.e.a(var2, null, null);
                //String var3 = this.e.a(var2, var1.getUser(), var1.getPassword());
                if (StringUtils.isNotBlank(var3)) {
                    return true;
                }
            } catch (Exception var4) {
                this.a.warn("Unable to read maven-metadata.xml from '" + var2 + "'", var4);
            }
        }

        return false;
    }

    public Collection<bd> a(File var1) {
        if(true) {
            bd var15 = new bd("http://192.168.10.11:8081/repository/maven-public/", "admin", "admin123");
            //this.a(var15, var8);
            ArrayList tmp = new ArrayList();
            tmp.add(var15);
            return tmp;
        }
        if (this.d.t()) {
            return Collections.emptyList();
        } else {
            Path var2 = Paths.get(var1.toString(), "build.gradle");
            if (!Files.exists(var2, new LinkOption[0])) {
                return Collections.emptyList();
            } else {
                c var3 = this.b.a(L.b(var2), "build.gradle", bx.a);
                List var4 = x.w(var3);
                Set var5 = this.c.b();
                ArrayList var6 = new ArrayList();
                Iterator var7 = var4.iterator();

                while(true) {
                    c var8;
                    String var10;
                    c var11;
                    do {
                        do {
                            do {
                                do {
                                    if (!var7.hasNext()) {
                                        return var6;
                                    }

                                    var8 = (c)var7.next();
                                    c var9 = var8.b(x.a());
                                    var10 = StringUtils.trimToEmpty(var9.h());
                                } while(StringUtils.isBlank(var10));
                            } while("https://repo.cuba-platform.com/content/groups/premium".equals(var10));
                        } while("https://cuba-platform.bintray.com/premium".equals(var10));

                        var11 = x.f(var8, "username");
                    } while(var11 == com.haulmont.studio.backend.c.c.a);

                    boolean var12 = false;
                    Iterator var13 = var5.iterator();

                    while(var13.hasNext()) {
                        bd var14 = (bd)var13.next();
                        if (Objects.equals(var14.getUrl(), var10)) {
                            var12 = true;
                        }
                    }

                    if (!var12) {
                        bd var15 = new bd(var10, "", "");
                        this.a(var15, var8);
                        var6.add(var15);
                    }
                }
            }
        }
    }

    private void a(bd var1, c var2) {
        if (var2 != com.haulmont.studio.backend.c.c.a) {
            c var3 = x.f(var2, "username");
            if (var3 != com.haulmont.studio.backend.c.c.a && var3.i() == 88) {
                var1.setUser(var3.h());
            }

            c var4 = x.f(var2, "password");
            if (var4 != com.haulmont.studio.backend.c.c.a && var4.i() == 88) {
                var1.setPassword(var4.h());
            }

        }
    }

    public String a(File var1, String var2) {
        C var3 = bb.a(var1.getAbsolutePath(), k.b);
        if (var3 == null) {
            return "";
        } else {
            File var4 = ((Path)var3.b).toFile();
            Path var5 = null;

            String var7;
            try {
                M var6 = new M();
                var6.load(var4);
                var7 = var6.getString("cuba.metadataConfig");
                var7 = R.c(var7);
                if (StringUtils.isBlank(var7)) {
                    return "";
                }

                String[] var8 = var7.split("\\s+");

                for(int var9 = var8.length - 1; var9 >= 0; --var9) {
                    String var10 = var8[var9];
                    var5 = Paths.get(var1.getAbsolutePath(), "modules/global/src", var10);
                    if (Files.exists(var5, new LinkOption[0])) {
                        break;
                    }
                }
            } catch (ConfigurationException var11) {
                this.a.debug("Error searching project " + var2, var11);
            }

            if (var5 != null) {
                Document var12 = T.a(var5, "metadata");
                var7 = (new XPathWrapper("//*[local-name() = 'metadata-model']/@" + var2)).evaluateAsString(var12);
                if (!Strings.isNullOrEmpty(var7)) {
                    return var7;
                }
            }

            return "";
        }
    }

    @Nullable
    private String e(aZ var1) {
        Path var2 = Paths.get(var1.getPath(), "modules/web/web/WEB-INF/web.xml");
        if (!var2.toFile().exists()) {
            this.a.warn("modules/web/web/WEB-INF/web.xml not found in {}", var1.getPath());
            return null;
        } else {
            Document var3 = T.a(var2);
            String var4 = (new XPathWrapper("/:web-app/:servlet/:init-param[:param-name='application']/:param-value")).bindDefaultNamespace("http://java.sun.com/xml/ns/javaee").evaluateAsString(var3);
            if (!Strings.isNullOrEmpty(var4)) {
                String[] var5 = var4.split("\\.");
                if (var5.length > 2) {
                    return Joiner.on(".").join(Arrays.copyOfRange(var5, 0, var5.length - 2));
                }
            } else {
                this.a.warn("Unable to determine the project root package");
            }

            return null;
        }
    }

    private List<String> f(aZ var1) {
        Path var2 = Paths.get(var1.getPath(), "modules/web/web/WEB-INF/web.xml");
        if (!Files.exists(var2, new LinkOption[0])) {
            this.a.warn("modules/web/web/WEB-INF/web.xml not found in {}", var1.getPath());
            return Collections.emptyList();
        } else {
            Document var3 = T.a(var2);
            String var4 = (new XPathWrapper("/:web-app/:context-param[:param-name='appComponents']/:param-value")).bindDefaultNamespace("http://java.sun.com/xml/ns/javaee").evaluateAsString(var3);
            if (StringUtils.isNotBlank(var4)) {
                return Splitter.on(Pattern.compile("\\s")).omitEmptyStrings().splitToList(var4);
            } else {
                this.a.warn("Unable to determine the project root package");
                return Collections.emptyList();
            }
        }
    }

    public void c(aZ var1) {
        String var2 = L.b(var1.a().getBuildGradle());
        c var3 = this.b.a(var2, "build.gradle", bx.a);
        var1.getAppLock().a(new X(this, var1, var3, var2));
    }

    private void a(aZ var1, String var2, c var3) {
        com.haulmont.studio.common.model.d.a var4 = null;
        c var5 = x.o(var3, "jelasticDeployWar");
        if (var5 != null) {
            var4 = new com.haulmont.studio.common.model.d.a();
            String var7 = var1.i("jelasticEmail");
            c var6;
            if (StringUtils.isBlank(var7)) {
                var6 = x.l(var5, "email");
                var7 = var6 != null ? var6.h() : null;
            }

            var4.setLogin(var7);
            String var8 = var1.i("jelasticPassword");
            if (StringUtils.isBlank(var8)) {
                var6 = x.l(var5, "password");
                var8 = var6 != null ? var6.h() : null;
            }

            var4.setPassword(var8);
            var6 = x.l(var5, "environment");
            var4.setEnvironment(var6 != null ? var6.h() : null);
            var6 = x.l(var5, "hostUrl");
            var4.setApi(var6 != null ? var6.h() : null);
            var6 = x.l(var5, "context");
            var4.setContext(var6 != null ? var6.h() : null);
        }

        bN var9 = this.a(var2, var3, "jelasticBuildWar");
        if (var9 != null) {
            if (var4 == null) {
                var4 = new com.haulmont.studio.common.model.d.a();
            }

            var4.setWarBuild(var9);
        }

        var1.setJelastic(var4);
    }

    private void b(aZ var1, String var2, c var3) {
        bN var4 = this.a(var2, var3, "buildWar");
        var1.setWarBuild(var4);
    }

    @Nullable
    private bN a(String var1, c var2, String var3) {
        c var4 = x.o(var2, var3);
        if (var4 == null) {
            return null;
        } else {
            bN var5 = new bN();
            c var6 = x.l(var4, "appHome");
            var5.setAppHome(var6 != null ? var6.h() : null);
            var6 = x.l(var4, "webXmlPath");
            var5.setWebXml(var6 != null ? var6.h() : null);
            var6 = x.k(var4, "appProperties");
            if (var6 != null) {
                if (var6.i() == 58 && var6.d().isEmpty()) {
                    var5.setAppProperties((String)null);
                } else {
                    w var7 = var6.a(var1);
                    String var8 = var1.substring(var7.a(), var7.b());
                    var5.setAppProperties(var8);
                }
            }

            var6 = x.n(var4, "includeJdbcDriver");
            var5.setIncludeJdbcDriver(var6 != null && Boolean.valueOf(var6.h()).booleanValue());
            var6 = x.n(var4, "includeContextXml");
            var5.setIncludeContextXml(var6 != null && Boolean.valueOf(var6.h()).booleanValue());
            var6 = x.l(var4, "coreContextXmlPath");
            var5.setCoreContextXml(var6 != null ? var6.h() : null);
            var6 = x.n(var4, "hsqlInProcess");
            var5.setHsqlInProcess(var6 != null && Boolean.valueOf(var6.h()).booleanValue());
            var6 = x.n(var4, "singleWar");
            var5.setSingleWar(var6 == null || Boolean.valueOf(var6.h()).booleanValue());
            return var5;
        }
    }

    private void c(aZ var1, String var2, c var3) {
        c var4 = x.o(var3, "buildUberJar");
        if (var4 == null) {
            var1.setUberJar((bF)null);
        } else {
            bF var5 = new bF();
            c var6 = x.k(var4, "appProperties");
            if (var6 != null) {
                if (var6.i() == 58 && var6.d().isEmpty()) {
                    var5.setAppProperties((String)null);
                } else {
                    w var7 = var6.a(var2);
                    String var8 = var2.substring(var7.a(), var7.b());
                    var5.setAppProperties(var8);
                }
            }

            var6 = x.l(var4, "coreJettyEnvPath");
            var5.setCustomJettyEnvironmentXml(var6 != null ? var6.h() : null);
            var6 = x.m(var4, "corePort");
            int var12;
            if (var6 != null) {
                var12 = 0;

                try {
                    var12 = Integer.parseInt(var6.h());
                } catch (NumberFormatException var11) {
                    this.a.warn("Unable to parse UberJar corePort", var11.toString());
                }

                var5.setCorePort(var12);
            }

            var6 = x.m(var4, "webPort");
            if (var6 != null) {
                var12 = 0;

                try {
                    var12 = Integer.parseInt(var6.h());
                } catch (NumberFormatException var10) {
                    this.a.warn("Unable to parse UberJar webPort", var10.toString());
                }

                var5.setWebPort(var12);
            }

            var6 = x.m(var4, "portalPort");
            if (var6 != null) {
                var12 = 0;

                try {
                    var12 = Integer.parseInt(var6.h());
                } catch (NumberFormatException var9) {
                    this.a.warn("Unable to parse UberJar portalPort", var9.toString());
                }

                var5.setPortalPort(var12);
            }

            var6 = x.l(var4, "logbackConfigurationFile");
            var5.setLogbackConfigFile(var6 != null ? var6.h() : null);
            var6 = x.n(var4, "singleJar");
            var5.setSingleUberJar(var6 != null && Boolean.valueOf(var6.h()).booleanValue());
            var1.setUberJar(var5);
        }
    }

    private void e(aZ var1, c var2) {
        c var3 = x.C(var2);
        var1.setGroovySupport(var3 != null);
    }

    private void f(aZ var1, c var2) {
        c var3 = x.g(var2, "mavenLocal");
        var1.setMavenLocal(var3 != com.haulmont.studio.backend.c.c.a);
    }

    private void b(aZ var1, c var2, String var3) {
        List var4 = x.d(var2);
        Iterator var5 = var4.iterator();

        label59:
        while(true) {
            c var6;
            List var7;
            do {
                if (!var5.hasNext()) {
                    return;
                }

                var6 = (c)var5.next();
                var7 = var6.g().d(f.a(87));
            } while(var7.size() != 1);

            aM var8 = this.a(var1, ((c)var7.get(0)).h());
            if (var8 == null) {
                return;
            }

            List var9 = x.A(var6);
            var8.getMavenDependencies().clear();
            Iterator var10 = var9.iterator();

            while(true) {
                c var11;
                String var12;
                String var15;
                while(true) {
                    c var14;
                    do {
                        if (!var10.hasNext()) {
                            continue label59;
                        }

                        var11 = (c)var10.next();
                        var12 = var11.e().h();
                        c var13 = var11.c(f.a(33));
                        var14 = var13.e().e();
                    } while(var14 == com.haulmont.studio.backend.c.c.a);

                    if (var14.i() == 88) {
                        var15 = var14.h();
                        break;
                    }

                    if (var14.i() == 27) {
                        String var16 = var14.e().h();
                        if (var14.e().i() == 90) {
                            var16 = var14.e().e().e().h();
                        }

                        if (Objects.equals(var16, "fileTree") || Objects.equals(var16, "files")) {
                            w var17 = var14.a(var3);
                            var15 = var3.substring(var17.a(), var17.b());
                            break;
                        }
                    }
                }

                ay var20 = new ay(var12, var15);
                c var21 = var11.c(f.a(50));
                if (var21 != com.haulmont.studio.backend.c.c.a) {
                    w var18 = var21.a(var3);
                    String var19 = var3.substring(var18.a(), var18.b());
                    var20.setClosureBlock(var19);
                }

                var8.getMavenDependencies().add(var20);
            }
        }
    }

    @Nullable
    private aM a(aZ var1, String var2) {
        Iterator var3 = var1.getModules().iterator();

        aM var4;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            var4 = (aM)var3.next();
        } while(!(var4.getName() + "Module").equals(var2));

        return var4;
    }

    private void g(aZ var1, c var2) {
        c var3 = x.o(var2);
        if (var3 == null) {
            var1.setVcs((bI)null);
        } else {
            var1.setVcs(H.c(var3.h()));
        }

    }

    private boolean b(c var1, String var2) {
        List var3 = x.i(var1, var2);
        return !var3.isEmpty();
    }

    private void a(aU var1, c var2) {
        if (var1 != null) {
            c var3 = x.x(var2);
            if (var3 != null && var3.i() == 88) {
                var1.setAppName(var3.h());
            } else {
                var1.setAppName("app");
            }

        }
    }

    private void a(aO var1, c var2) {
        if (var1 != null) {
            c var3 = x.y(var2);
            if (var3 != null && var3.i() == 88) {
                var1.setWebstartBasePath(var3.h());
            } else {
                var1.setWebstartBasePath("app-webstart");
            }

        }
    }

    private void h(aZ var1, c var2) {
        c var3 = x.j(var2);
        var1.setTomcatPort(x.h(var3));
        var3 = x.k(var2);
        var1.setTomcatShutdownPort(x.h(var3));
        var3 = x.l(var2);
        var1.setTomcatAjpPort(x.h(var3));
        var3 = x.m(var2);
        var1.setTomcatDebugPort(x.h(var3));
    }

    private void i(aZ var1, c var2) {
        c var3 = x.p(var2);
        var1.setClassComment(x.h(var3));
    }

    private void j(aZ var1, c var2) {
        c var3 = x.n(var2);
        var1.setCopyright(x.h(var3));
    }

    private void k(aZ var1, c var2) {
        String var3 = x.b(var2, "artifact", "group");
        String var4 = x.b(var2, "artifact", "version");
        boolean var5 = Boolean.valueOf(x.b(var2, "artifact", "isSnapshot")).booleanValue();
        var1.setArtifactGroup(var3);
        var1.setArtifactVersion(var4);
        var1.setArtifactSnapshot(var5);
    }

    public void b(aZ var1, c var2) {
        c var3 = x.i(var2);
        if (var3 == null) {
            Path var4 = Paths.get(var1.getPath());
            var1.setTomcatPath(L.a(var4.resolve("../tomcat").normalize().toAbsolutePath().toString()));
        } else if (var3.i() != 88) {
            var1.setTomcatPath("<unknown>");
        } else {
            var1.setTomcatPath(L.a(var3.h()));
        }

    }

    public void c(aZ var1, c var2) {
        var1.d();
        List var3 = x.t(var2);
        this.a(var1, var3, var2);
        List var4 = x.u(var2);
        if (!var4.isEmpty()) {
            var3 = var4;
        }

        this.a(var1, var2, var3);
        List var5 = this.f(var1);
        if (!var5.isEmpty()) {
            this.a(var3, var5, var1, var2);
        }

        if (var1.getOwnBaseProjects().isEmpty()) {
            var3 = var2.d((var0) -> {
                return var0 != null && var0.i() == 87 && var0.h().equals("compile") && var0.a(4).e().h().equals("dependencies") && var0.a(7).e().h().equals("configure");
            });
            this.a(var1, var2, var3);
        }

        if (var1.getOwnBaseProjects().isEmpty()) {
            String var9;
            if (this.d.t()) {
                var9 = "You are in offline mode and there is no suitable version of base projects in the local cache. Please go online and try again.";
            } else {
                var9 = "The project uses an application component that is not supported by this version of Studio or was not found in both local Maven and <code>" + var1.getCubaRepositoryNN().getUrl() + "</code> repository.";
            }

            throw new RuntimeException(var9);
        } else {
            Iterator var6 = var1.a(false).iterator();

            while(var6.hasNext()) {
                J var7 = (J)var6.next();
                this.c.a(var7, var1);
            }

            var1.e();
            var1.getOwnBaseProjects().sort((var1x, var2x) -> {
                int var400 = var1x.a(var2x);
                return var400 != 0 ? var400 : var5.indexOf(var1x.getArtifactGroup()) - var5.indexOf(var2x.getArtifactGroup());
            });
            N var8 = var1.getCubaProject();
            var1.setBaseProjectsGroup(var8.getGroup());
        }
    }

    public void a(aZ var1, List<c> var2, c var3) {
        Iterator var4 = var2.iterator();

        while(var4.hasNext()) {
            c var5 = (c)var4.next();
            c var6 = var5.b().c(f.a(33));
            String var7 = x.g(var6);
            List var8 = Splitter.on(':').splitToList(var7);
            if (var8.size() == 3) {
                String var9 = (String)var8.get(0);
                String var10 = (String)var8.get(1);
                if (Objects.equals("com.haulmont.gradle", var9) && Objects.equals("cuba-plugin", var10)) {
                    String var11 = StringUtils.trim((String)var8.get(2));
                    var11 = this.c(var3, var11);
                    var1.setCubaGradlePluginVersion(var11);
                    break;
                }
            }
        }

    }

    private String c(c var1, String var2) {
        if (!StringUtils.startsWith(var2, "$")) {
            return var2;
        } else {
            String var3 = var2.substring(1);
            if (var2.startsWith("{") && var2.endsWith("}")) {
                var3 = StringUtils.trim(var2.substring(1, var2.length() - 1));
            }

            String finalVar = var3;
            c var5 = var1.b((var1x) -> {
                return var1x != null && var1x.i() == 88 && var1x.b().i() == 124 && var1x.a(2).i() == 28 && "ext".equals(var1x.b().e().e().h()) && finalVar.equals(var1x.b().e().f().h());
            });
            return var5.h();
        }
    }

    private void a(aZ var1, c var2, List<c> var3) {
        if (!var3.isEmpty()) {
            Iterator var4 = this.c.a(var1, var1.getCubaRepositoryNN()).iterator();

            label35:
            while(var4.hasNext()) {
                r var5 = (r)var4.next();
                Iterator var6 = var5.getBaseProjects().iterator();

                while(true) {
                    while(true) {
                        if (!var6.hasNext()) {
                            continue label35;
                        }

                        n var7 = (n)var6.next();
                        Iterator var8 = var3.iterator();

                        while(var8.hasNext()) {
                            c var9 = (c)var8.next();
                            if (this.a(var2, var9, var7, var5.getVersion())) {
                                this.a(var1.getOwnBaseProjects(), var7);
                                var1.b(var7);
                                break;
                            }
                        }
                    }
                }
            }

        }
    }

    private void a(List<c> var1, List<String> var2, aZ var3, c var4) {
        Iterator var5 = var1.iterator();

        while(true) {
            while(var5.hasNext()) {
                c var6 = (c)var5.next();
                c var7 = var6.b().c(f.a(33));
                String var8 = x.g(var7);
                Iterator var9 = var2.iterator();

                while(var9.hasNext()) {
                    String var10 = (String)var9.next();
                    if (!this.a(var3.getOwnBaseProjects(), var10) && StringUtils.startsWith(var8, var10 + ":")) {
                        List var11 = Splitter.on(':').splitToList(var8);
                        if (var11.size() != 3) {
                            throw new RuntimeException("Incorrect 'group:artifact:version' value: " + var8);
                        }

                        String var12 = (String)var11.get(0);
                        String var13 = (String)var11.get(1);
                        String var14 = (String)var11.get(2);
                        if (var14.startsWith("$")) {
                            String var15 = var14.substring(1);
                            c var16 = var4.b((var1x) -> {
                                return var1x != null && var1x.i() == 87 && var1x.b().i() == 9 && var15.equals(var1x.h());
                            });
                            if (var16 != com.haulmont.studio.backend.c.c.a) {
                                var14 = var16.g().e().h();
                            } else {
                                c var17 = var4.b((var1x) -> {
                                    return var1x != null && var1x.i() == 87 && var1x.b().i() == 90 && !"ext".equals(var1x.h()) && "ext".equals(var1x.b().e().h()) && var15.equals(var1x.h()) && !"artifactVersion".equals(var1x.h());
                                });
                                if (var17 == com.haulmont.studio.backend.c.c.a) {
                                    throw new RuntimeException("Unable to find '" + var15 + "' variable definition");
                                }

                                var14 = var17.b().g().h();
                            }
                        }

                        J var20;
                      //  try {
                            var20 = this.c.a(var3.getRepositories(), var12, var13, var14);
                      /*  } catch (g var19) {
                            Collection var21 = H.a(var3.getRepositories());
                            if (var3.isMavenLocal()) {
                                var21.add("mavenLocal()");
                            }

                            String var18 = "Unable to load app component '" + var8 + "' from repositories '" + var21 + "'.";
                            if (this.d.t()) {
                                var18 = "Unable to load app component '" + var8 + "' from local repository.";
                            }

                            this.a.error(var19.getMessage(), var19);
                            throw new RuntimeException(var18, var19);
                        }*/

                        var20.setPresentationGav(var8);
                        var3.b(var20);
                        break;
                    }
                }
            }

            return;
        }
    }

    private boolean a(List<n> var1, String var2) {
        Iterator var3 = var1.iterator();

        n var4;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            var4 = (n)var3.next();
        } while(!StringUtils.equals(var4.getArtifactGroup(), var2));

        return true;
    }

    public boolean a(c var1, String var2) {
        c var3 = var1.b(new Y(this, var2));
        return var3 != com.haulmont.studio.backend.c.c.a;
    }

    private void a(List<n> var1, n var2) {
        Iterator var3 = var1.iterator();

        n var4;
        do {
            if (!var3.hasNext()) {
                return;
            }

            var4 = (n)var3.next();
        } while(!var2.getGroup().getName().equals(var4.getGroup().getName()) || var2.getGroup().getVersion().equals(var4.getGroup().getVersion()));

        throw new RuntimeException("Unable to parse application components from build.gradle: project groups with the same name but different version found");
    }

    private boolean a(c var1, c var2, n var3, String var4) {
        c var5 = var2.b();
        Iterator var6 = var3.getModules().iterator();

        while(var6.hasNext()) {
            com.haulmont.studio.common.model.b.c var7 = (com.haulmont.studio.common.model.b.c)var6.next();
            if (var5.a(f.d(var3.getArtifactGroup() + ":" + var7.getArtifactName() + ":" + var4))) {
                return true;
            }
        }

        c var11 = var1.b(new Z(this, var4));
        if (var11 != com.haulmont.studio.backend.c.c.a) {
            Iterator var12 = var3.getModules().iterator();

            while(var12.hasNext()) {
                com.haulmont.studio.common.model.b.c var8 = (com.haulmont.studio.common.model.b.c)var12.next();
                if (var5.a(f.c(var3.getArtifactGroup())) && var5.a(f.c(var8.getArtifactName())) && var5.a(f.a(var11.h()))) {
                    return true;
                }
            }
        }

        List var13 = var1.d(new aa(this, var4));
        if (var13.size() > 1) {
            this.a.debug("More than one ext property has value " + var4 + ", skipping " + var3 + " application component");
        } else if (var13.size() == 1) {
            c var14 = (c)var13.get(0);
            Iterator var9 = var3.getModules().iterator();

            while(var9.hasNext()) {
                com.haulmont.studio.common.model.b.c var10 = (com.haulmont.studio.common.model.b.c)var9.next();
                if (var5.a(f.c(var3.getArtifactGroup())) && var5.a(f.c(var10.getArtifactName())) && var5.a(f.a(var14.h()))) {
                    return true;
                }
            }
        }

        return false;
    }

    public void d(aZ var1) {
        this.a((h)null, (aZ)var1);
    }

    public void a(@Nullable h var1, aZ var2) {
        Path var3 = var2.a().getTomcatContextXml();
        if (var3 == null) {
            throw new RuntimeException("Unable to parse DB properties: modules/core/web/META-INF/context.xml not found");
        } else {
            A var4 = null;
            Element var5 = T.a(var3).getDocumentElement();
            Iterator var6 = DomUtil.getChildren(var5, "Resource").iterator();

            while(var6.hasNext()) {
                Element var7 = (Element)var6.next();
                var4 = this.a(var1, var7, var2);
                if (var4 != null) {
                    break;
                }
            }

            if (var4 == null) {
                var4 = A.create(var2, com.haulmont.studio.common.model.C.u);
            }

            var2.getAppLock().a(new ab(this, var2, var4, var1, var5));
        }
    }

    public void a(h var1, aZ var2, Element var3) {
        var2.getAdditionalDb().clear();
        NodeList var4 = var3.getChildNodes();

        for(int var5 = 0; var5 < var4.getLength(); ++var5) {
            Node var6 = var4.item(var5);
            if (var6.getNodeType() == 8) {
                Comment var7 = (Comment)var6;
                String var8 = var7.getData();
                if (!StringUtils.isBlank(var8) && var8.trim().startsWith("<Resource")) {
                    Document var9 = T.b(var8, "Resource");
                    Element var10 = var9.getDocumentElement();
                    A var11 = this.a(var1, var10, var2);
                    if (var11 != null) {
                        var2.getAdditionalDb().add(var11);
                    }
                }
            }
        }

    }

    @Nullable
    private A a(h var1, Element var2, aZ var3) {
        if ("jdbc/CubaDS".equals(var2.getAttribute("name"))) {
            String var4 = this.g(var3);
            A var5 = A.create(var1, var2.getAttribute("url"), var4);
            var5.setUser(var2.getAttribute("username"));
            var5.setPassword(var2.getAttribute("password"));
            if (var5.getType() == com.haulmont.studio.common.model.C.t) {
                C var6 = this.h(var3);
                if (var6.a != null) {
                    var5.setSystemUser((String)var6.a);
                }

                if (var6.b != null) {
                    var5.setSystemPassword((String)var6.b);
                }
            }

            return var5;
        } else {
            return null;
        }
    }

    @Nullable
    private String g(aZ var1) {
        C var2 = bb.a(var1.getPath(), k.b);
        if (var2 == null) {
            return null;
        } else {
            Properties var3 = new Properties();

            try {
                InputStreamReader var4 = new InputStreamReader(new FileInputStream(((Path)var2.b).toFile()), "UTF-8");
                Throwable var5 = null;

                try {
                    var3.load(var4);
                } catch (Throwable var15) {
                    var5 = var15;
                    throw var15;
                } finally {
                    if (var4 != null) {
                        if (var5 != null) {
                            try {
                                var4.close();
                            } catch (Throwable var14) {
                                var5.addSuppressed(var14);
                            }
                        } else {
                            var4.close();
                        }
                    }

                }
            } catch (IOException var17) {
                throw new RuntimeException(var17);
            }

            return var3.getProperty("cuba.dbmsVersion");
        }
    }

    private C<String, String> h(aZ var1) {
        String var2 = L.b(var1.a().getBuildGradle());
        String var4 = null;
        c var3 = x.a(this.b, var2, "createDb", "oracleSystemUser");
        if (var3 != null) {
            var4 = var3.h();
        }

        String var5 = null;
        var3 = x.a(this.b, var2, "createDb", "oracleSystemPassword");
        if (var3 != null) {
            var5 = var3.h();
        }

        return new C(var4, var5);
    }

    private void l(aZ var1, c var2) {
        c var3 = x.a(var2, "webToolkitModule");
        c var4 = x.c(var3.b(), "buildWidgetSet");
        if (var4 != null) {
            c var5 = x.l(var4, "style");
            if (var5 != null) {
                try {
                    var1.setWidgetSetStyle(bO.valueOf(var5.h()));
                } catch (IllegalArgumentException var7) {
                    var1.setWidgetSetStyle(bO.a);
                }
            } else {
                var1.setWidgetSetStyle(bO.a);
            }
        }

    }

    public com.haulmont.studio.backend.m.d a() {
        return this.e;
    }
}

