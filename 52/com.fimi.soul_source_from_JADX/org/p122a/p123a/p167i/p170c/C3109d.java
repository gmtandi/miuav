package org.p122a.p123a.p167i.p170c;

import android.util.Log;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p171m.C3108i;

@C2914d
/* renamed from: org.a.a.i.c.d */
class C3109d extends C3108i<HttpRoute, C3040h> {
    private static final String f15400a = "HttpClient";
    private volatile boolean f15401b;

    public C3109d(String str, HttpRoute httpRoute, C3040h c3040h, long j, TimeUnit timeUnit) {
        super(str, httpRoute, c3040h, j, timeUnit);
    }

    public void m17546a() {
        this.f15401b = true;
    }

    public boolean m17547a(long j) {
        boolean a = super.m17535a(j);
        if (a && Log.isLoggable(f15400a, 3)) {
            Log.d(f15400a, "Connection " + this + " expired @ " + new Date(m17545n()));
        }
        return a;
    }

    public boolean m17548b() {
        return this.f15401b;
    }

    public void m17549c() {
        ((HttpClientConnection) m17540i()).close();
    }

    public void m17550d() {
        ((HttpClientConnection) m17540i()).shutdown();
    }

    public boolean m17551e() {
        return !((HttpClientConnection) m17540i()).isOpen();
    }

    public void m17552f() {
        try {
            m17549c();
        } catch (Throwable e) {
            if (Log.isLoggable(f15400a, 3)) {
                Log.d(f15400a, "I/O error closing connection", e);
            }
        }
    }
}
