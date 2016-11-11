package org.p122a.p123a.p152c.p153a;

import java.net.InetAddress;
import java.util.Collection;
import org.apache.http.HttpHost;

/* renamed from: org.a.a.c.a.d */
public class C2926d {
    private boolean f14810a;
    private HttpHost f14811b;
    private InetAddress f14812c;
    private boolean f14813d;
    private String f14814e;
    private boolean f14815f;
    private boolean f14816g;
    private boolean f14817h;
    private int f14818i;
    private boolean f14819j;
    private Collection<String> f14820k;
    private Collection<String> f14821l;
    private int f14822m;
    private int f14823n;
    private int f14824o;

    C2926d() {
        this.f14813d = true;
        this.f14815f = true;
        this.f14818i = 50;
        this.f14816g = true;
        this.f14819j = true;
        this.f14822m = -1;
        this.f14823n = -1;
        this.f14824o = -1;
    }

    public C2925c m16757a() {
        return new C2925c(this.f14810a, this.f14811b, this.f14812c, this.f14813d, this.f14814e, this.f14815f, this.f14816g, this.f14817h, this.f14818i, this.f14819j, this.f14820k, this.f14821l, this.f14822m, this.f14823n, this.f14824o);
    }

    public C2926d m16758a(int i) {
        this.f14818i = i;
        return this;
    }

    public C2926d m16759a(String str) {
        this.f14814e = str;
        return this;
    }

    public C2926d m16760a(InetAddress inetAddress) {
        this.f14812c = inetAddress;
        return this;
    }

    public C2926d m16761a(Collection<String> collection) {
        this.f14820k = collection;
        return this;
    }

    public C2926d m16762a(HttpHost httpHost) {
        this.f14811b = httpHost;
        return this;
    }

    public C2926d m16763a(boolean z) {
        this.f14810a = z;
        return this;
    }

    public C2926d m16764b(int i) {
        this.f14822m = i;
        return this;
    }

    public C2926d m16765b(Collection<String> collection) {
        this.f14821l = collection;
        return this;
    }

    public C2926d m16766b(boolean z) {
        this.f14813d = z;
        return this;
    }

    public C2926d m16767c(int i) {
        this.f14823n = i;
        return this;
    }

    public C2926d m16768c(boolean z) {
        this.f14815f = z;
        return this;
    }

    public C2926d m16769d(int i) {
        this.f14824o = i;
        return this;
    }

    public C2926d m16770d(boolean z) {
        this.f14816g = z;
        return this;
    }

    public C2926d m16771e(boolean z) {
        this.f14817h = z;
        return this;
    }

    public C2926d m16772f(boolean z) {
        this.f14819j = z;
        return this;
    }
}
