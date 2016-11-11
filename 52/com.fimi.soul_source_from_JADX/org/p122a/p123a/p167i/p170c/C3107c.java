package org.p122a.p123a.p167i.p170c;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p171m.C3106a;
import org.p122a.p123a.p171m.C3130f;

@C2914d
/* renamed from: org.a.a.i.c.c */
class C3107c extends C3106a<HttpRoute, C3040h, C3109d> {
    private static final AtomicLong f15389a;
    private final long f15390b;
    private final TimeUnit f15391c;

    static {
        f15389a = new AtomicLong();
    }

    public C3107c(C3130f<HttpRoute, C3040h> c3130f, int i, int i2, long j, TimeUnit timeUnit) {
        super(c3130f, i, i2);
        this.f15390b = j;
        this.f15391c = timeUnit;
    }

    protected C3109d m17531a(HttpRoute httpRoute, C3040h c3040h) {
        return new C3109d(Long.toString(f15389a.getAndIncrement()), httpRoute, c3040h, this.f15390b, this.f15391c);
    }
}
