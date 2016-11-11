package org.p122a.p123a.p152c.p156c;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.AbortableHttpRequest;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;
import org.p122a.p123a.p152c.p161f.C2978a;
import org.p122a.p123a.p157d.C2943b;

/* renamed from: org.a.a.c.c.a */
public abstract class C2942a extends AbstractHttpMessage implements Cloneable, C2941h, HttpRequest, AbortableHttpRequest {
    private final AtomicBoolean f14849a;
    private final AtomicReference<C2943b> f14850b;

    protected C2942a() {
        this.f14849a = new AtomicBoolean(false);
        this.f14850b = new AtomicReference(null);
    }

    public void m16818a() {
        this.f14850b.set(null);
    }

    public void m16819a(C2943b c2943b) {
        if (!this.f14849a.get()) {
            this.f14850b.set(c2943b);
        }
    }

    public void abort() {
        if (this.f14849a.compareAndSet(false, true)) {
            C2943b c2943b = (C2943b) this.f14850b.getAndSet(null);
            if (c2943b != null) {
                c2943b.m16821a();
            }
        }
    }

    public void m16820b() {
        C2943b c2943b = (C2943b) this.f14850b.getAndSet(null);
        if (c2943b != null) {
            c2943b.m16821a();
        }
        this.f14849a.set(false);
    }

    public Object clone() {
        C2942a c2942a = (C2942a) super.clone();
        c2942a.headergroup = (HeaderGroup) C2978a.m16912a(this.headergroup);
        c2942a.params = (HttpParams) C2978a.m16912a(this.params);
        return c2942a;
    }

    public boolean isAborted() {
        return this.f14849a.get();
    }

    @Deprecated
    public void setConnectionRequest(ClientConnectionRequest clientConnectionRequest) {
        m16819a(new C2944b(this, clientConnectionRequest));
    }

    @Deprecated
    public void setReleaseTrigger(ConnectionReleaseTrigger connectionReleaseTrigger) {
        m16819a(new C2945c(this, connectionReleaseTrigger));
    }
}
