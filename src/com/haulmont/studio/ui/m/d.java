package com.haulmont.studio.ui.m;

import com.haulmont.studio.backend.ed.ddl.C;
import com.haulmont.studio.ui.app.aB;
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
    public static final String a = "ServerBeans";
    private MutablePicoContainer b;
    private Server c;

    public d()
    {
        this.b = new PicoBuilder().withCaching().withJavaEE5Lifecycle().build();

        this.b.addComponent(aB.class);
        this.b.addComponent(j.class);
        //this.b.addComponent(i.class);
        Class cls = null;
        try {
            cls = Class.forName("com.haulmont.studio.backend.plugin.a.i");
            this.b.addComponent(cls);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.b.addComponent(com.haulmont.studio.backend.i.a.class);
        this.b.addComponent(c.class);
        this.b.addComponent(com.haulmont.studio.ui.h.a.class);
        this.b.addComponent(e.class);
        this.b.addComponent(C.class);

        this.b.start();
    }

    public MutablePicoContainer a()
    {
        return this.b;
    }

    public <T> T a(Class<T> paramClass)
    {
        Object localObject = b(paramClass);
        if (localObject != null) {
            return (T)localObject;
        }
        throw new IllegalStateException("Cannot get bean of class " + paramClass.getName());
    }

    @Nullable
    public <T> T b(Class<T> paramClass)
    {
        return (T)this.b.getComponent(paramClass);
    }

    public void b()
    {
        LifecycleState localLifecycleState = this.b.getLifecycleState();
        if ((localLifecycleState != null) && (!localLifecycleState.isDisposed())) {
            this.b.dispose();
        }
    }

    public void a(Server paramServer)
    {
        this.c = paramServer;
    }

    @Nullable
    public Server c()
    {
        return this.c;
    }
}
