package org.p122a.p123a.p167i.p169b;

import com.amap.api.maps.model.WeightedLatLng;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p152c.C2964c;
import org.p122a.p123a.p171m.C3105h;
import org.p122a.p123a.p180o.C3234a;

/* renamed from: org.a.a.i.b.a */
public class C3075a implements C2964c {
    private final C3105h<HttpRoute> f15231a;
    private final C3080g f15232b;
    private final Map<HttpRoute, Long> f15233c;
    private final Map<HttpRoute, Long> f15234d;
    private long f15235e;
    private double f15236f;
    private int f15237g;

    public C3075a(C3105h<HttpRoute> c3105h) {
        this(c3105h, new aj());
    }

    C3075a(C3105h<HttpRoute> c3105h, C3080g c3080g) {
        this.f15235e = 5000;
        this.f15236f = 0.5d;
        this.f15237g = 2;
        this.f15232b = c3080g;
        this.f15231a = c3105h;
        this.f15233c = new HashMap();
        this.f15234d = new HashMap();
    }

    private Long m17309a(Map<HttpRoute, Long> map, HttpRoute httpRoute) {
        Long l = (Long) map.get(httpRoute);
        return l == null ? Long.valueOf(0) : l;
    }

    private int m17310b(int i) {
        return i <= 1 ? 1 : (int) Math.floor(this.f15236f * ((double) i));
    }

    public void m17311a(double d) {
        boolean z = d > 0.0d && d < WeightedLatLng.DEFAULT_INTENSITY;
        C3234a.m17888a(z, "Backoff factor must be 0.0 < f < 1.0");
        this.f15236f = d;
    }

    public void m17312a(int i) {
        C3234a.m17883a(i, "Per host connection cap");
        this.f15237g = i;
    }

    public void m17313a(long j) {
        C3234a.m17884a(this.f15235e, "Cool down");
        this.f15235e = j;
    }

    public void m17314a(HttpRoute httpRoute) {
        synchronized (this.f15231a) {
            int b = this.f15231a.m17500b((Object) httpRoute);
            Long a = m17309a(this.f15234d, httpRoute);
            long a2 = this.f15232b.m17359a();
            if (a2 - a.longValue() < this.f15235e) {
                return;
            }
            this.f15231a.m17499a(httpRoute, m17310b(b));
            this.f15234d.put(httpRoute, Long.valueOf(a2));
        }
    }

    public void m17315b(HttpRoute httpRoute) {
        synchronized (this.f15231a) {
            int b = this.f15231a.m17500b((Object) httpRoute);
            b = b >= this.f15237g ? this.f15237g : b + 1;
            Long a = m17309a(this.f15233c, httpRoute);
            Long a2 = m17309a(this.f15234d, httpRoute);
            long a3 = this.f15232b.m17359a();
            if (a3 - a.longValue() < this.f15235e || a3 - a2.longValue() < this.f15235e) {
                return;
            }
            this.f15231a.m17499a(httpRoute, b);
            this.f15233c.put(httpRoute, Long.valueOf(a3));
        }
    }
}
