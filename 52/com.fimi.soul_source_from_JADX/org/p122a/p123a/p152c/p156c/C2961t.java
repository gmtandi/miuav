package org.p122a.p123a.p152c.p156c;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.HeaderGroup;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p155b.C2939i;
import org.p122a.p123a.p152c.p161f.C2987j;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.c.c.t */
public class C2961t {
    private String f14872a;
    private ProtocolVersion f14873b;
    private URI f14874c;
    private HeaderGroup f14875d;
    private HttpEntity f14876e;
    private LinkedList<NameValuePair> f14877f;
    private C2925c f14878g;

    C2961t() {
        this(null);
    }

    C2961t(String str) {
        this.f14872a = str;
    }

    public static C2961t m16834a() {
        return new C2961t(C2951i.f14860a);
    }

    public static C2961t m16835a(String str) {
        C3234a.m17892b((CharSequence) str, "HTTP method");
        return new C2961t(str);
    }

    public static C2961t m16836a(HttpRequest httpRequest) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        return new C2961t().m16838b(httpRequest);
    }

    public static C2961t m16837b() {
        return new C2961t(C2952j.f14861a);
    }

    private C2961t m16838b(HttpRequest httpRequest) {
        if (httpRequest != null) {
            this.f14872a = httpRequest.getRequestLine().getMethod();
            this.f14873b = httpRequest.getRequestLine().getProtocolVersion();
            if (httpRequest instanceof HttpUriRequest) {
                this.f14874c = ((HttpUriRequest) httpRequest).getURI();
            } else {
                this.f14874c = URI.create(httpRequest.getRequestLine().getUri());
            }
            if (this.f14875d == null) {
                this.f14875d = new HeaderGroup();
            }
            this.f14875d.clear();
            this.f14875d.setHeaders(httpRequest.getAllHeaders());
            if (httpRequest instanceof HttpEntityEnclosingRequest) {
                this.f14876e = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            } else {
                this.f14876e = null;
            }
            if (httpRequest instanceof C2947e) {
                this.f14878g = ((C2947e) httpRequest).g_();
            } else {
                this.f14878g = null;
            }
            this.f14877f = null;
        }
        return this;
    }

    public static C2961t m16839c() {
        return new C2961t(C2955m.f14864a);
    }

    public static C2961t m16840d() {
        return new C2961t(C2956n.f14865a);
    }

    public static C2961t m16841e() {
        return new C2961t(C2949f.f14858a);
    }

    public static C2961t m16842f() {
        return new C2961t(C2960s.f14871a);
    }

    public static C2961t m16843g() {
        return new C2961t(C2953k.f14862a);
    }

    public C2961t m16844a(String str, String str2) {
        if (this.f14875d == null) {
            this.f14875d = new HeaderGroup();
        }
        this.f14875d.addHeader(new BasicHeader(str, str2));
        return this;
    }

    public C2961t m16845a(URI uri) {
        this.f14874c = uri;
        return this;
    }

    public C2961t m16846a(C2925c c2925c) {
        this.f14878g = c2925c;
        return this;
    }

    public C2961t m16847a(Header header) {
        if (this.f14875d == null) {
            this.f14875d = new HeaderGroup();
        }
        this.f14875d.addHeader(header);
        return this;
    }

    public C2961t m16848a(HttpEntity httpEntity) {
        this.f14876e = httpEntity;
        return this;
    }

    public C2961t m16849a(NameValuePair nameValuePair) {
        C3234a.m17886a((Object) nameValuePair, "Name value pair");
        if (this.f14877f == null) {
            this.f14877f = new LinkedList();
        }
        this.f14877f.add(nameValuePair);
        return this;
    }

    public C2961t m16850a(ProtocolVersion protocolVersion) {
        this.f14873b = protocolVersion;
        return this;
    }

    public C2961t m16851a(NameValuePair... nameValuePairArr) {
        for (NameValuePair a : nameValuePairArr) {
            m16849a(a);
        }
        return this;
    }

    public C2961t m16852b(String str) {
        this.f14874c = str != null ? URI.create(str) : null;
        return this;
    }

    public C2961t m16853b(String str, String str2) {
        if (this.f14875d == null) {
            this.f14875d = new HeaderGroup();
        }
        this.f14875d.updateHeader(new BasicHeader(str, str2));
        return this;
    }

    public C2961t m16854b(Header header) {
        if (this.f14875d == null) {
            this.f14875d = new HeaderGroup();
        }
        this.f14875d.removeHeader(header);
        return this;
    }

    public C2961t m16855c(String str, String str2) {
        return m16849a(new BasicNameValuePair(str, str2));
    }

    public C2961t m16856c(Header header) {
        if (this.f14875d == null) {
            this.f14875d = new HeaderGroup();
        }
        this.f14875d.updateHeader(header);
        return this;
    }

    public Header m16857c(String str) {
        return this.f14875d != null ? this.f14875d.getFirstHeader(str) : null;
    }

    public Header m16858d(String str) {
        return this.f14875d != null ? this.f14875d.getLastHeader(str) : null;
    }

    public Header[] m16859e(String str) {
        return this.f14875d != null ? this.f14875d.getHeaders(str) : null;
    }

    public C2961t m16860f(String str) {
        if (!(str == null || this.f14875d == null)) {
            HeaderIterator it = this.f14875d.iterator();
            while (it.hasNext()) {
                if (str.equalsIgnoreCase(it.nextHeader().getName())) {
                    it.remove();
                }
            }
        }
        return this;
    }

    public String m16861h() {
        return this.f14872a;
    }

    public ProtocolVersion m16862i() {
        return this.f14873b;
    }

    public URI m16863j() {
        return this.f14874c;
    }

    public HttpEntity m16864k() {
        return this.f14876e;
    }

    public List<NameValuePair> m16865l() {
        return this.f14877f != null ? new ArrayList(this.f14877f) : new ArrayList();
    }

    public C2925c m16866m() {
        return this.f14878g;
    }

    public HttpUriRequest m16867n() {
        URI uri;
        HttpUriRequest c2963v;
        URI create = this.f14874c != null ? this.f14874c : URI.create("/");
        HttpEntity httpEntity = this.f14876e;
        if (this.f14877f == null || this.f14877f.isEmpty()) {
            uri = create;
        } else if (httpEntity == null && (C2955m.f14864a.equalsIgnoreCase(this.f14872a) || C2956n.f14865a.equalsIgnoreCase(this.f14872a))) {
            httpEntity = new C2939i(this.f14877f, Charset.forName("ISO-8859-1"));
            uri = create;
        } else {
            try {
                uri = new C2987j(create).m16950b(this.f14877f).m16941a();
            } catch (URISyntaxException e) {
                uri = create;
            }
        }
        if (httpEntity == null) {
            c2963v = new C2963v(this.f14872a);
        } else {
            c2963v = new C2962u(this.f14872a);
            c2963v.setEntity(httpEntity);
        }
        c2963v.m16826a(this.f14873b);
        c2963v.m16824a(uri);
        if (this.f14875d != null) {
            c2963v.setHeaders(this.f14875d.getAllHeaders());
        }
        c2963v.m16825a(this.f14878g);
        return c2963v;
    }
}
