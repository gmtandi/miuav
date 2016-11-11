package org.p122a.p123a.p167i.p169b;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.b.l */
public class C3079l implements HttpRequestRetryHandler {
    public static final C3079l f15266a;
    private final int f15267b;
    private final boolean f15268c;
    private final Set<Class<? extends IOException>> f15269d;

    static {
        f15266a = new C3079l();
    }

    public C3079l() {
        this(3, false);
    }

    public C3079l(int i, boolean z) {
        this(i, z, Arrays.asList(new Class[]{InterruptedIOException.class, UnknownHostException.class, ConnectException.class, SSLException.class}));
    }

    protected C3079l(int i, boolean z, Collection<Class<? extends IOException>> collection) {
        this.f15267b = i;
        this.f15268c = z;
        this.f15269d = new HashSet();
        for (Class add : collection) {
            this.f15269d.add(add);
        }
    }

    public boolean m17354a() {
        return this.f15268c;
    }

    protected boolean m17355a(HttpRequest httpRequest) {
        return !(httpRequest instanceof HttpEntityEnclosingRequest);
    }

    public int m17356b() {
        return this.f15267b;
    }

    @Deprecated
    protected boolean m17357b(HttpRequest httpRequest) {
        HttpRequest original = httpRequest instanceof RequestWrapper ? ((RequestWrapper) httpRequest).getOriginal() : httpRequest;
        return (original instanceof HttpUriRequest) && ((HttpUriRequest) original).isAborted();
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        C3234a.m17886a((Object) iOException, "Exception parameter");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        if (i > this.f15267b) {
            return false;
        }
        if (this.f15269d.contains(iOException.getClass())) {
            return false;
        }
        for (Class isInstance : this.f15269d) {
            if (isInstance.isInstance(iOException)) {
                return false;
            }
        }
        C2968a a = C2968a.m16884a(httpContext);
        HttpRequest q = a.m16879q();
        return m17357b(q) ? false : m17355a(q) ? true : !a.m16880r() || this.f15268c;
    }
}
