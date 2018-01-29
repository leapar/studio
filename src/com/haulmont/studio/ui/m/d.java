package com.haulmont.studio.ui.m;

//import com.haulmont.studio.backend.j.a;
import com.haulmont.studio.common.Container;
import com.haulmont.studio.ui.app.aO;
import com.haulmont.studio.ui.h.c;
import com.haulmont.studio.backend.plugin.j;
//import com.haulmont.studio.backend.plugin.a.i;
//import com.haulmont.studio.backend.e.c.w;
import com.haulmont.studio.common.e;
import com.haulmont.studio.common.b;
import javax.annotation.Nullable;
import org.eclipse.jetty.server.Server;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.lifecycle.LifecycleState;

public class d implements b {
    public static final String NAME = "ServerBeans";
    private MutablePicoContainer picoContainer = (new PicoBuilder()).withCaching().withJavaEE5Lifecycle().build();
    private Server server;

    public d() {
        Class cls = null;
        this.picoContainer.addComponent(aO.class);//class com.haulmont.studio.ui.app.aO
        this.picoContainer.addComponent(j.class);//class com.haulmont.studio.backend.plugin.j
        //this.b.addComponent(i.class);//class com.haulmont.studio.backend.plugin.a.i
        try {
            cls = Class.forName("com.haulmont.studio.backend.plugin.a.i");
            this.picoContainer.addComponent(cls);

            cls = Class.forName("com.haulmont.studio.backend.j.a");
            this.picoContainer.addComponent(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //this.picoContainer.addComponent(a.class);//com.haulmont.studio.backend.j.a

        this.picoContainer.addComponent(c.class);//class com.haulmont.studio.ui.h.c
        this.picoContainer.addComponent(com.haulmont.studio.ui.h.a.class);//class com.haulmont.studio.ui.h.a
        this.picoContainer.addComponent(e.class);//class com.haulmont.studio.common.e
        //this.b.addComponent(w.class);//class com.haulmont.studio.backend.e.c.w
        try {
            cls = Class.forName("com.haulmont.studio.backend.e.c.w");
            this.picoContainer.addComponent(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.picoContainer.start();
    }

    public MutablePicoContainer a() {
        return this.picoContainer;
    }

    public <T> T a(Class<T> var1) {
        T var2 = this.b(var1);
        if (var2 != null) {
            return var2;
        } else {
            throw new IllegalStateException("Cannot get bean of class " + var1.getName());
        }
    }


    //getComponent
    @Nullable
    @Override
    public <T> T b(Class<T> var1) {
        return this.picoContainer.getComponent(var1);
    }

    //dispose
    @Override
    public void b() {
        LifecycleState var1 = this.picoContainer.getLifecycleState();
        if (var1 != null && !var1.isDisposed()) {
            this.picoContainer.dispose();
        }

    }

    //set server
    public void a(Server var1) {
        this.server = var1;
    }

    //getServer
    @Nullable
    public Server c() {
        return this.server;
    }


}
