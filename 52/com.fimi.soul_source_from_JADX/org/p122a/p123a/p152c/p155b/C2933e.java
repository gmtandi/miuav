package org.p122a.p123a.p152c.p155b;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p154h.C2938j;
import org.p122a.p123a.p154h.C3047b;
import org.p122a.p123a.p154h.C3049d;
import org.p122a.p123a.p154h.C3050e;
import org.p122a.p123a.p154h.C3051f;
import org.p122a.p123a.p154h.C3052h;
import org.p122a.p123a.p154h.C3053i;

@C2913c
/* renamed from: org.a.a.c.b.e */
public class C2933e {
    private String f14830a;
    private byte[] f14831b;
    private InputStream f14832c;
    private List<NameValuePair> f14833d;
    private Serializable f14834e;
    private File f14835f;
    private C3050e f14836g;
    private String f14837h;
    private boolean f14838i;
    private boolean f14839j;

    C2933e() {
    }

    public static C2933e m16780a() {
        return new C2933e();
    }

    private C3050e m16781b(C3050e c3050e) {
        return this.f14836g != null ? this.f14836g : c3050e;
    }

    private void m16782o() {
        this.f14830a = null;
        this.f14831b = null;
        this.f14832c = null;
        this.f14833d = null;
        this.f14834e = null;
        this.f14835f = null;
    }

    public C2933e m16783a(File file) {
        m16782o();
        this.f14835f = file;
        return this;
    }

    public C2933e m16784a(InputStream inputStream) {
        m16782o();
        this.f14832c = inputStream;
        return this;
    }

    public C2933e m16785a(Serializable serializable) {
        m16782o();
        this.f14834e = serializable;
        return this;
    }

    public C2933e m16786a(String str) {
        m16782o();
        this.f14830a = str;
        return this;
    }

    public C2933e m16787a(List<NameValuePair> list) {
        m16782o();
        this.f14833d = list;
        return this;
    }

    public C2933e m16788a(C3050e c3050e) {
        this.f14836g = c3050e;
        return this;
    }

    public C2933e m16789a(byte[] bArr) {
        m16782o();
        this.f14831b = bArr;
        return this;
    }

    public C2933e m16790a(NameValuePair... nameValuePairArr) {
        return m16787a(Arrays.asList(nameValuePairArr));
    }

    public String m16791b() {
        return this.f14830a;
    }

    public C2933e m16792b(String str) {
        this.f14837h = str;
        return this;
    }

    public byte[] m16793c() {
        return this.f14831b;
    }

    public InputStream m16794d() {
        return this.f14832c;
    }

    public List<NameValuePair> m16795e() {
        return this.f14833d;
    }

    public Serializable m16796f() {
        return this.f14834e;
    }

    public File m16797g() {
        return this.f14835f;
    }

    public C3050e m16798h() {
        return this.f14836g;
    }

    public String m16799i() {
        return this.f14837h;
    }

    public boolean m16800j() {
        return this.f14838i;
    }

    public C2933e m16801k() {
        this.f14838i = true;
        return this;
    }

    public boolean m16802l() {
        return this.f14839j;
    }

    public C2933e m16803m() {
        this.f14839j = true;
        return this;
    }

    public HttpEntity m16804n() {
        HttpEntity c2938j;
        if (this.f14830a != null) {
            c2938j = new C2938j(this.f14830a, m16781b(C3050e.f15113m));
        } else if (this.f14831b != null) {
            c2938j = new C3049d(this.f14831b, m16781b(C3050e.f15114n));
        } else if (this.f14832c != null) {
            c2938j = new C3052h(this.f14832c, 1, m16781b(C3050e.f15114n));
        } else if (this.f14833d != null) {
            Object c2939i = new C2939i(this.f14833d, this.f14836g != null ? this.f14836g.m17167b() : null);
        } else if (this.f14834e != null) {
            c2938j = new C3053i(this.f14834e);
            c2938j.m16807a(C3050e.f15114n.toString());
        } else {
            c2938j = this.f14835f != null ? new C3051f(this.f14835f, m16781b(C3050e.f15114n)) : new C3047b();
        }
        if (!(c2938j.getContentType() == null || this.f14836g == null)) {
            c2938j.m16807a(this.f14836g.toString());
        }
        c2938j.m16810b(this.f14837h);
        c2938j.m16809a(this.f14838i);
        return this.f14839j ? new C2934f(c2938j) : c2938j;
    }
}
