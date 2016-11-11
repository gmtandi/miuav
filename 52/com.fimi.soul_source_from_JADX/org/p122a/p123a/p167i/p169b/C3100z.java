package org.p122a.p123a.p167i.p169b;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;

/* renamed from: org.a.a.i.b.z */
class C3100z implements ClientConnectionManager {
    final /* synthetic */ C3099y f15363a;

    C3100z(C3099y c3099y) {
        this.f15363a = c3099y;
    }

    public void closeExpiredConnections() {
        this.f15363a.f15355c.m17136a();
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        this.f15363a.f15355c.m17137a(j, timeUnit);
    }

    public SchemeRegistry getSchemeRegistry() {
        throw new UnsupportedOperationException();
    }

    public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        throw new UnsupportedOperationException();
    }

    public void shutdown() {
        this.f15363a.f15355c.m17141b();
    }
}
