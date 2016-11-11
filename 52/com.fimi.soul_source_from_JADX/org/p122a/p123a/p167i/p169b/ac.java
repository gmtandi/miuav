package org.p122a.p123a.p167i.p169b;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;

/* renamed from: org.a.a.i.b.ac */
class ac implements ClientConnectionManager {
    final /* synthetic */ ab f15247a;

    ac(ab abVar) {
        this.f15247a = abVar;
    }

    public void closeExpiredConnections() {
        this.f15247a.f15244a.m17136a();
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        this.f15247a.f15244a.m17137a(j, timeUnit);
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
        this.f15247a.f15244a.m17141b();
    }
}
