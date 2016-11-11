package org.p122a.p123a.p167i.p176f;

import android.util.Log;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpClientConnection;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p157d.C2943b;

@C2914d
/* renamed from: org.a.a.i.f.c */
class C3169c implements Closeable, C2943b, ConnectionReleaseTrigger {
    private static final String f15525a = "HttpClient";
    private final C3036d f15526b;
    private final HttpClientConnection f15527c;
    private volatile boolean f15528d;
    private volatile Object f15529e;
    private volatile long f15530f;
    private volatile TimeUnit f15531g;
    private volatile boolean f15532h;

    public C3169c(C3036d c3036d, HttpClientConnection httpClientConnection) {
        this.f15526b = c3036d;
        this.f15527c = httpClientConnection;
    }

    public void m17700a(long j, TimeUnit timeUnit) {
        synchronized (this.f15527c) {
            this.f15530f = j;
            this.f15531g = timeUnit;
        }
    }

    public void m17701a(Object obj) {
        this.f15529e = obj;
    }

    public boolean m17702a() {
        boolean z = this.f15532h;
        if (Log.isLoggable(f15525a, 3)) {
            Log.d(f15525a, "Cancelling request execution");
        }
        abortConnection();
        return !z;
    }

    public void abortConnection() {
        synchronized (this.f15527c) {
            if (this.f15532h) {
                return;
            }
            this.f15532h = true;
            try {
                this.f15527c.shutdown();
                if (Log.isLoggable(f15525a, 3)) {
                    Log.d(f15525a, "Connection discarded");
                }
            } catch (Throwable e) {
                if (Log.isLoggable(f15525a, 3)) {
                    Log.d(f15525a, e.getMessage(), e);
                }
            } finally {
                this.f15526b.m17138a(this.f15527c, null, 0, TimeUnit.MILLISECONDS);
            }
        }
    }

    public boolean m17703b() {
        return this.f15528d;
    }

    public void m17704c() {
        this.f15528d = true;
    }

    public void close() {
        abortConnection();
    }

    public void m17705d() {
        this.f15528d = false;
    }

    public boolean m17706e() {
        return this.f15532h;
    }

    public void releaseConnection() {
        synchronized (this.f15527c) {
            if (this.f15532h) {
                return;
            }
            this.f15532h = true;
            if (this.f15528d) {
                this.f15526b.m17138a(this.f15527c, this.f15529e, this.f15530f, this.f15531g);
            } else {
                try {
                    this.f15527c.close();
                    if (Log.isLoggable(f15525a, 3)) {
                        Log.d(f15525a, "Connection discarded");
                    }
                } catch (Throwable e) {
                    if (Log.isLoggable(f15525a, 3)) {
                        Log.d(f15525a, e.getMessage(), e);
                    }
                } finally {
                    this.f15526b.m17138a(this.f15527c, null, 0, TimeUnit.MILLISECONDS);
                }
            }
        }
    }
}
