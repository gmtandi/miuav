package org.p122a.p123a.p167i.p169b;

import android.net.SSLCertificateSocketFactory;
import com.facebook.common.util.UriUtil;
import com.tencent.connect.common.Constants;
import java.io.Closeable;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p124f.p125c.C3026h;
import org.p122a.p123a.p124f.p164b.C3015b;
import org.p122a.p123a.p124f.p164b.C3016c;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p151b.C2918c;
import org.p122a.p123a.p152c.C2940b;
import org.p122a.p123a.p152c.C2964c;
import org.p122a.p123a.p152c.C2966d;
import org.p122a.p123a.p152c.C2977e;
import org.p122a.p123a.p152c.C2990f;
import org.p122a.p123a.p152c.p153a.C2923a;
import org.p122a.p123a.p152c.p153a.C2924b;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p160e.C2969b;
import org.p122a.p123a.p152c.p160e.C2970c;
import org.p122a.p123a.p152c.p160e.C2971d;
import org.p122a.p123a.p152c.p160e.C2972e;
import org.p122a.p123a.p152c.p160e.C2973f;
import org.p122a.p123a.p152c.p160e.C2974g;
import org.p122a.p123a.p152c.p160e.C2975h;
import org.p122a.p123a.p152c.p160e.C2976i;
import org.p122a.p123a.p159n.C3225d;
import org.p122a.p123a.p159n.C3229h;
import org.p122a.p123a.p159n.C3231j;
import org.p122a.p123a.p159n.C3232k;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p162e.C3001g;
import org.p122a.p123a.p162e.C3002h;
import org.p122a.p123a.p166g.C3045b;
import org.p122a.p123a.p167i.C3201g;
import org.p122a.p123a.p167i.C3208k;
import org.p122a.p123a.p167i.p168a.C3055b;
import org.p122a.p123a.p167i.p168a.C3058d;
import org.p122a.p123a.p167i.p168a.C3071q;
import org.p122a.p123a.p167i.p170c.C3118k;
import org.p122a.p123a.p167i.p170c.C3119j;
import org.p122a.p123a.p167i.p170c.C3120l;
import org.p122a.p123a.p167i.p170c.C3127s;
import org.p122a.p123a.p167i.p170c.C3133x;
import org.p122a.p123a.p167i.p174d.C3147k;
import org.p122a.p123a.p167i.p174d.C3149m;
import org.p122a.p123a.p167i.p174d.C3156u;
import org.p122a.p123a.p167i.p174d.C3159x;
import org.p122a.p123a.p167i.p174d.ac;
import org.p122a.p123a.p167i.p174d.aj;
import org.p122a.p123a.p167i.p176f.C3167b;
import org.p122a.p123a.p167i.p176f.C3168a;
import org.p122a.p123a.p167i.p176f.C3171e;
import org.p122a.p123a.p167i.p176f.C3173g;
import org.p122a.p123a.p167i.p176f.C3174h;
import org.p122a.p123a.p167i.p176f.C3178l;
import org.p122a.p123a.p167i.p176f.C3179m;
import org.p122a.p123a.p180o.C3239f;
import org.p122a.p123a.p180o.C3240g;

@C2913c
/* renamed from: org.a.a.i.b.u */
public class C3095u {
    static final String f15298a;
    private CredentialsProvider f15299A;
    private String f15300B;
    private HttpHost f15301C;
    private Collection<? extends Header> f15302D;
    private C3002h f15303E;
    private C2995a f15304F;
    private C2925c f15305G;
    private boolean f15306H;
    private boolean f15307I;
    private boolean f15308J;
    private boolean f15309K;
    private boolean f15310L;
    private boolean f15311M;
    private boolean f15312N;
    private int f15313O;
    private int f15314P;
    private List<Closeable> f15315Q;
    private HttpRequestExecutor f15316b;
    private X509HostnameVerifier f15317c;
    private C3015b f15318d;
    private SSLContext f15319e;
    private C3036d f15320f;
    private C3041i f15321g;
    private ConnectionReuseStrategy f15322h;
    private ConnectionKeepAliveStrategy f15323i;
    private C2940b f15324j;
    private C2940b f15325k;
    private UserTokenHandler f15326l;
    private HttpProcessor f15327m;
    private LinkedList<HttpRequestInterceptor> f15328n;
    private LinkedList<HttpRequestInterceptor> f15329o;
    private LinkedList<HttpResponseInterceptor> f15330p;
    private LinkedList<HttpResponseInterceptor> f15331q;
    private HttpRequestRetryHandler f15332r;
    private HttpRoutePlanner f15333s;
    private C2977e f15334t;
    private C2966d f15335u;
    private C2964c f15336v;
    private C2990f f15337w;
    private C2997c<C2918c> f15338x;
    private C2997c<C3045b> f15339y;
    private CookieStore f15340z;

    static {
        C3240g a = C3240g.m17913a("org.apache.http.client", C3095u.class.getClassLoader());
        f15298a = "Apache-HttpClient/" + (a != null ? a.m17918c() : C3240g.f15702a) + " (java 1.5)";
    }

    protected C3095u() {
        this.f15313O = 0;
        this.f15314P = 0;
    }

    public static C3095u m17407a() {
        return new C3095u();
    }

    private static String[] m17408b(String str) {
        return C3239f.m17911b(str) ? null : str.split(" *, *");
    }

    public final C3095u m17409a(int i) {
        this.f15313O = i;
        return this;
    }

    public final C3095u m17410a(String str) {
        this.f15300B = str;
        return this;
    }

    public final C3095u m17411a(Collection<? extends Header> collection) {
        this.f15302D = collection;
        return this;
    }

    public final C3095u m17412a(SSLContext sSLContext) {
        this.f15319e = sSLContext;
        return this;
    }

    public final C3095u m17413a(C2925c c2925c) {
        this.f15305G = c2925c;
        return this;
    }

    public final C3095u m17414a(C2940b c2940b) {
        this.f15324j = c2940b;
        return this;
    }

    public final C3095u m17415a(C2964c c2964c) {
        this.f15336v = c2964c;
        return this;
    }

    public final C3095u m17416a(C2966d c2966d) {
        this.f15335u = c2966d;
        return this;
    }

    public final C3095u m17417a(C2977e c2977e) {
        this.f15334t = c2977e;
        return this;
    }

    public final C3095u m17418a(C2990f c2990f) {
        this.f15337w = c2990f;
        return this;
    }

    public final C3095u m17419a(C2995a c2995a) {
        this.f15304F = c2995a;
        return this;
    }

    public final C3095u m17420a(C2997c<C2918c> c2997c) {
        this.f15338x = c2997c;
        return this;
    }

    public final C3095u m17421a(C3002h c3002h) {
        this.f15303E = c3002h;
        return this;
    }

    public final C3095u m17422a(C3015b c3015b) {
        this.f15318d = c3015b;
        return this;
    }

    public final C3095u m17423a(C3036d c3036d) {
        this.f15320f = c3036d;
        return this;
    }

    public final C3095u m17424a(C3041i c3041i) {
        this.f15321g = c3041i;
        return this;
    }

    public final C3095u m17425a(ConnectionReuseStrategy connectionReuseStrategy) {
        this.f15322h = connectionReuseStrategy;
        return this;
    }

    public final C3095u m17426a(HttpHost httpHost) {
        this.f15301C = httpHost;
        return this;
    }

    public final C3095u m17427a(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            if (this.f15328n == null) {
                this.f15328n = new LinkedList();
            }
            this.f15328n.addFirst(httpRequestInterceptor);
        }
        return this;
    }

    public final C3095u m17428a(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            if (this.f15330p == null) {
                this.f15330p = new LinkedList();
            }
            this.f15330p.addFirst(httpResponseInterceptor);
        }
        return this;
    }

    public final C3095u m17429a(CookieStore cookieStore) {
        this.f15340z = cookieStore;
        return this;
    }

    public final C3095u m17430a(CredentialsProvider credentialsProvider) {
        this.f15299A = credentialsProvider;
        return this;
    }

    public final C3095u m17431a(HttpRequestRetryHandler httpRequestRetryHandler) {
        this.f15332r = httpRequestRetryHandler;
        return this;
    }

    public final C3095u m17432a(UserTokenHandler userTokenHandler) {
        this.f15326l = userTokenHandler;
        return this;
    }

    public final C3095u m17433a(ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        this.f15323i = connectionKeepAliveStrategy;
        return this;
    }

    public final C3095u m17434a(HttpRoutePlanner httpRoutePlanner) {
        this.f15333s = httpRoutePlanner;
        return this;
    }

    public final C3095u m17435a(X509HostnameVerifier x509HostnameVerifier) {
        this.f15317c = x509HostnameVerifier;
        return this;
    }

    public final C3095u m17436a(HttpProcessor httpProcessor) {
        this.f15327m = httpProcessor;
        return this;
    }

    public final C3095u m17437a(HttpRequestExecutor httpRequestExecutor) {
        this.f15316b = httpRequestExecutor;
        return this;
    }

    protected C3167b m17438a(C3167b c3167b) {
        return c3167b;
    }

    protected void m17439a(Closeable closeable) {
        if (closeable != null) {
            if (this.f15315Q == null) {
                this.f15315Q = new ArrayList();
            }
            this.f15315Q.add(closeable);
        }
    }

    public final C3095u m17440b() {
        this.f15312N = true;
        return this;
    }

    public final C3095u m17441b(int i) {
        this.f15314P = i;
        return this;
    }

    public final C3095u m17442b(C2940b c2940b) {
        this.f15325k = c2940b;
        return this;
    }

    public final C3095u m17443b(C2997c<C3045b> c2997c) {
        this.f15339y = c2997c;
        return this;
    }

    public final C3095u m17444b(HttpRequestInterceptor httpRequestInterceptor) {
        if (httpRequestInterceptor != null) {
            if (this.f15329o == null) {
                this.f15329o = new LinkedList();
            }
            this.f15329o.addLast(httpRequestInterceptor);
        }
        return this;
    }

    public final C3095u m17445b(HttpResponseInterceptor httpResponseInterceptor) {
        if (httpResponseInterceptor != null) {
            if (this.f15331q == null) {
                this.f15331q = new LinkedList();
            }
            this.f15331q.addLast(httpResponseInterceptor);
        }
        return this;
    }

    protected C3167b m17446b(C3167b c3167b) {
        return c3167b;
    }

    public final C3095u m17447c() {
        this.f15310L = true;
        return this;
    }

    public final C3095u m17448d() {
        this.f15309K = true;
        return this;
    }

    public final C3095u m17449e() {
        this.f15311M = true;
        return this;
    }

    public final C3095u m17450f() {
        this.f15308J = true;
        return this;
    }

    public final C3095u m17451g() {
        this.f15307I = true;
        return this;
    }

    public final C3095u m17452h() {
        this.f15306H = true;
        return this;
    }

    public C3077h m17453i() {
        C3167b c3167b;
        List list = null;
        HttpRequestExecutor httpRequestExecutor = this.f15316b;
        if (httpRequestExecutor == null) {
            httpRequestExecutor = new HttpRequestExecutor();
        }
        C3036d c3036d = this.f15320f;
        if (c3036d == null) {
            Object obj = this.f15318d;
            if (obj == null) {
                String[] b = this.f15306H ? C3095u.m17408b(System.getProperty("https.protocols")) : null;
                String[] b2 = this.f15306H ? C3095u.m17408b(System.getProperty("https.cipherSuites")) : null;
                X509HostnameVerifier x509HostnameVerifier = this.f15317c;
                X509HostnameVerifier x509HostnameVerifier2 = x509HostnameVerifier == null ? C3026h.f15066e : x509HostnameVerifier;
                if (this.f15319e != null) {
                    obj = new C3026h(this.f15319e, b, b2, x509HostnameVerifier2);
                } else {
                    C3026h c3026h = this.f15306H ? new C3026h((SSLSocketFactory) SSLCertificateSocketFactory.getDefault(0), b, b2, x509HostnameVerifier2) : new C3026h((SSLSocketFactory) SSLCertificateSocketFactory.getDefault(0), x509HostnameVerifier2);
                }
            }
            c3036d = new C3127s(C3001g.m17034a().m17035a(UriUtil.HTTP_SCHEME, C3016c.m17070a()).m17035a(UriUtil.HTTPS_SCHEME, obj).m17036b());
            if (this.f15303E != null) {
                c3036d.m17609a(this.f15303E);
            }
            if (this.f15304F != null) {
                c3036d.m17608a(this.f15304F);
            }
            if (this.f15306H) {
                if ("true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true"))) {
                    int parseInt = Integer.parseInt(System.getProperty("http.maxConnections", Constants.VIA_SHARE_TYPE_TEXT));
                    c3036d.m17620b(parseInt);
                    c3036d.m17605a(parseInt * 2);
                }
            }
            if (this.f15313O > 0) {
                c3036d.m17605a(this.f15313O);
            }
            if (this.f15314P > 0) {
                c3036d.m17620b(this.f15314P);
            }
        }
        ConnectionReuseStrategy connectionReuseStrategy = this.f15322h;
        if (connectionReuseStrategy == null) {
            if (this.f15306H) {
                connectionReuseStrategy = "true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true")) ? C3201g.f15638a : C3208k.f15653a;
            } else {
                connectionReuseStrategy = C3201g.f15638a;
            }
        }
        ConnectionKeepAliveStrategy connectionKeepAliveStrategy = this.f15323i;
        if (connectionKeepAliveStrategy == null) {
            connectionKeepAliveStrategy = C3087k.f15279a;
        }
        C2940b c2940b = this.f15324j;
        if (c2940b == null) {
            c2940b = al.f15273a;
        }
        C2940b c2940b2 = this.f15325k;
        if (c2940b2 == null) {
            c2940b2 = af.f15253a;
        }
        UserTokenHandler userTokenHandler = this.f15326l;
        if (userTokenHandler == null) {
            userTokenHandler = !this.f15312N ? C3089o.f15282a : ad.f15248a;
        }
        C3167b a = m17438a(new C3171e(httpRequestExecutor, c3036d, connectionReuseStrategy, connectionKeepAliveStrategy, c2940b, c2940b2, userTokenHandler));
        HttpProcessor httpProcessor = this.f15327m;
        if (httpProcessor == null) {
            String str;
            C3225d a2;
            Iterator it;
            Iterator it2;
            String str2 = this.f15300B;
            if (str2 == null) {
                if (this.f15306H) {
                    str2 = System.getProperty("http.agent");
                }
                if (str2 == null) {
                    str = f15298a;
                    a2 = C3225d.m17866a();
                    if (this.f15328n != null) {
                        it = this.f15328n.iterator();
                        while (it.hasNext()) {
                            a2.m17869a((HttpRequestInterceptor) it.next());
                        }
                    }
                    if (this.f15330p != null) {
                        it = this.f15330p.iterator();
                        while (it.hasNext()) {
                            a2.m17870a((HttpResponseInterceptor) it.next());
                        }
                    }
                    a2.m17880c(new C2973f(this.f15302D), new C3229h(), new C3231j(), new C2972e(), new C3232k(str), new C2974g());
                    if (!this.f15310L) {
                        a2.m17878c(new C2970c());
                    }
                    if (!this.f15309K) {
                        a2.m17878c(new C2969b());
                    }
                    if (!this.f15311M) {
                        a2.m17878c(new C2971d());
                    }
                    if (!this.f15310L) {
                        a2.m17879c(new C2976i());
                    }
                    if (!this.f15309K) {
                        a2.m17879c(new C2975h());
                    }
                    if (this.f15329o != null) {
                        it2 = this.f15329o.iterator();
                        while (it2.hasNext()) {
                            a2.m17873b((HttpRequestInterceptor) it2.next());
                        }
                    }
                    if (this.f15331q != null) {
                        it2 = this.f15331q.iterator();
                        while (it2.hasNext()) {
                            a2.m17874b((HttpResponseInterceptor) it2.next());
                        }
                    }
                    httpProcessor = a2.m17877b();
                }
            }
            str = str2;
            a2 = C3225d.m17866a();
            if (this.f15328n != null) {
                it = this.f15328n.iterator();
                while (it.hasNext()) {
                    a2.m17869a((HttpRequestInterceptor) it.next());
                }
            }
            if (this.f15330p != null) {
                it = this.f15330p.iterator();
                while (it.hasNext()) {
                    a2.m17870a((HttpResponseInterceptor) it.next());
                }
            }
            a2.m17880c(new C2973f(this.f15302D), new C3229h(), new C3231j(), new C2972e(), new C3232k(str), new C2974g());
            if (this.f15310L) {
                a2.m17878c(new C2970c());
            }
            if (this.f15309K) {
                a2.m17878c(new C2969b());
            }
            if (this.f15311M) {
                a2.m17878c(new C2971d());
            }
            if (this.f15310L) {
                a2.m17879c(new C2976i());
            }
            if (this.f15309K) {
                a2.m17879c(new C2975h());
            }
            if (this.f15329o != null) {
                it2 = this.f15329o.iterator();
                while (it2.hasNext()) {
                    a2.m17873b((HttpRequestInterceptor) it2.next());
                }
            }
            if (this.f15331q != null) {
                it2 = this.f15331q.iterator();
                while (it2.hasNext()) {
                    a2.m17874b((HttpResponseInterceptor) it2.next());
                }
            }
            httpProcessor = a2.m17877b();
        }
        a = m17446b(new C3173g(a, httpProcessor));
        if (this.f15308J) {
            c3167b = a;
        } else {
            HttpRequestRetryHandler httpRequestRetryHandler = this.f15332r;
            if (httpRequestRetryHandler == null) {
                httpRequestRetryHandler = C3079l.f15266a;
            }
            c3167b = new C3178l(a, httpRequestRetryHandler);
        }
        HttpRoutePlanner httpRoutePlanner = this.f15333s;
        if (httpRoutePlanner == null) {
            C3041i c3041i = this.f15321g;
            if (c3041i == null) {
                c3041i = C3120l.f15424a;
            }
            httpRoutePlanner = this.f15301C != null ? new C3119j(this.f15301C, c3041i) : this.f15306H ? new C3133x(c3041i, ProxySelector.getDefault()) : new C3118k(c3041i);
        }
        if (!this.f15307I) {
            C2977e c2977e = this.f15334t;
            if (c2977e == null) {
                c2977e = C3076m.f15239b;
            }
            c3167b = new C3174h(c3167b, httpRoutePlanner, c2977e);
        }
        C2990f c2990f = this.f15337w;
        if (c2990f != null) {
            c3167b = new C3179m(c3167b, c2990f);
        }
        C2964c c2964c = this.f15336v;
        C2966d c2966d = this.f15335u;
        C3167b c3168a = (c2964c == null || c2966d == null) ? c3167b : new C3168a(c3167b, c2966d, c2964c);
        C2997c c2997c = this.f15338x;
        if (c2997c == null) {
            c2997c = C3001g.m17034a().m17035a(C2923a.f14784a, new C3055b()).m17035a(C2923a.f14785b, new C3058d()).m17035a(C2923a.f14786c, new C3071q()).m17036b();
        }
        C2997c c2997c2 = this.f15339y;
        if (c2997c2 == null) {
            c2997c2 = C3001g.m17034a().m17035a(C2924b.f14792d, new C3147k()).m17035a(C2924b.f14791c, new aj()).m17035a(C2924b.f14789a, new C3149m()).m17035a(C2924b.f14790b, new C3159x()).m17035a(C2924b.f14793e, new C3156u()).m17035a("rfc2109", new ac()).m17035a("rfc2965", new aj()).m17036b();
        }
        CookieStore cookieStore = this.f15340z;
        if (cookieStore == null) {
            cookieStore = new C3082d();
        }
        CredentialsProvider credentialsProvider = this.f15299A;
        if (credentialsProvider == null) {
            credentialsProvider = this.f15306H ? new ak() : new C3083e();
        }
        C2925c c2925c = this.f15305G != null ? this.f15305G : C2925c.f14794a;
        if (this.f15315Q != null) {
            list = new ArrayList(this.f15315Q);
        }
        return new C3099y(c3168a, c3036d, httpRoutePlanner, c2997c2, c2997c, cookieStore, credentialsProvider, c2925c, list);
    }
}
