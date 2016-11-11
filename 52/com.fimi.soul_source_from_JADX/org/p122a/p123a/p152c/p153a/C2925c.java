package org.p122a.p123a.p152c.p153a;

import java.net.InetAddress;
import java.util.Collection;
import org.apache.http.HttpHost;

/* renamed from: org.a.a.c.a.c */
public class C2925c implements Cloneable {
    public static final C2925c f14794a;
    private final boolean f14795b;
    private final HttpHost f14796c;
    private final InetAddress f14797d;
    private final boolean f14798e;
    private final String f14799f;
    private final boolean f14800g;
    private final boolean f14801h;
    private final boolean f14802i;
    private final int f14803j;
    private final boolean f14804k;
    private final Collection<String> f14805l;
    private final Collection<String> f14806m;
    private final int f14807n;
    private final int f14808o;
    private final int f14809p;

    static {
        f14794a = new C2926d().m16757a();
    }

    C2925c(boolean z, HttpHost httpHost, InetAddress inetAddress, boolean z2, String str, boolean z3, boolean z4, boolean z5, int i, boolean z6, Collection<String> collection, Collection<String> collection2, int i2, int i3, int i4) {
        this.f14795b = z;
        this.f14796c = httpHost;
        this.f14797d = inetAddress;
        this.f14798e = z2;
        this.f14799f = str;
        this.f14800g = z3;
        this.f14801h = z4;
        this.f14802i = z5;
        this.f14803j = i;
        this.f14804k = z6;
        this.f14805l = collection;
        this.f14806m = collection2;
        this.f14807n = i2;
        this.f14808o = i3;
        this.f14809p = i4;
    }

    public static C2926d m16739a(C2925c c2925c) {
        return new C2926d().m16763a(c2925c.m16741a()).m16762a(c2925c.m16742b()).m16760a(c2925c.m16743c()).m16766b(c2925c.m16744d()).m16759a(c2925c.m16745e()).m16768c(c2925c.m16746f()).m16770d(c2925c.m16747g()).m16771e(c2925c.m16748h()).m16758a(c2925c.m16749i()).m16772f(c2925c.m16750j()).m16761a(c2925c.m16751k()).m16765b(c2925c.m16752l()).m16764b(c2925c.m16753m()).m16767c(c2925c.m16754n()).m16769d(c2925c.m16755o());
    }

    public static C2926d m16740q() {
        return new C2926d();
    }

    public boolean m16741a() {
        return this.f14795b;
    }

    public HttpHost m16742b() {
        return this.f14796c;
    }

    public InetAddress m16743c() {
        return this.f14797d;
    }

    protected /* synthetic */ Object clone() {
        return m16756p();
    }

    public boolean m16744d() {
        return this.f14798e;
    }

    public String m16745e() {
        return this.f14799f;
    }

    public boolean m16746f() {
        return this.f14800g;
    }

    public boolean m16747g() {
        return this.f14801h;
    }

    public boolean m16748h() {
        return this.f14802i;
    }

    public int m16749i() {
        return this.f14803j;
    }

    public boolean m16750j() {
        return this.f14804k;
    }

    public Collection<String> m16751k() {
        return this.f14805l;
    }

    public Collection<String> m16752l() {
        return this.f14806m;
    }

    public int m16753m() {
        return this.f14807n;
    }

    public int m16754n() {
        return this.f14808o;
    }

    public int m16755o() {
        return this.f14809p;
    }

    protected C2925c m16756p() {
        return (C2925c) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", expectContinueEnabled=").append(this.f14795b);
        stringBuilder.append(", proxy=").append(this.f14796c);
        stringBuilder.append(", localAddress=").append(this.f14797d);
        stringBuilder.append(", staleConnectionCheckEnabled=").append(this.f14798e);
        stringBuilder.append(", cookieSpec=").append(this.f14799f);
        stringBuilder.append(", redirectsEnabled=").append(this.f14800g);
        stringBuilder.append(", relativeRedirectsAllowed=").append(this.f14801h);
        stringBuilder.append(", maxRedirects=").append(this.f14803j);
        stringBuilder.append(", circularRedirectsAllowed=").append(this.f14802i);
        stringBuilder.append(", authenticationEnabled=").append(this.f14804k);
        stringBuilder.append(", targetPreferredAuthSchemes=").append(this.f14805l);
        stringBuilder.append(", proxyPreferredAuthSchemes=").append(this.f14806m);
        stringBuilder.append(", connectionRequestTimeout=").append(this.f14807n);
        stringBuilder.append(", connectTimeout=").append(this.f14808o);
        stringBuilder.append(", socketTimeout=").append(this.f14809p);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
