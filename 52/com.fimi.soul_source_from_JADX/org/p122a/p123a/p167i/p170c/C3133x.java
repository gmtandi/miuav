package org.p122a.p123a.p167i.p170c;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.i.c.x */
public class C3133x extends C3118k {
    private final ProxySelector f15457a;

    public C3133x(ProxySelector proxySelector) {
        this(null, proxySelector);
    }

    public C3133x(C3041i c3041i, ProxySelector proxySelector) {
        super(c3041i);
        if (proxySelector == null) {
            proxySelector = ProxySelector.getDefault();
        }
        this.f15457a = proxySelector;
    }

    private String m17641a(InetSocketAddress inetSocketAddress) {
        return inetSocketAddress.isUnresolved() ? inetSocketAddress.getHostName() : inetSocketAddress.getAddress().getHostAddress();
    }

    private Proxy m17642a(List<Proxy> list) {
        Proxy proxy = null;
        int i = 0;
        while (proxy == null && i < list.size()) {
            Proxy proxy2 = (Proxy) list.get(i);
            switch (C3134y.f15458a[proxy2.type().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    break;
                default:
                    proxy2 = proxy;
                    break;
            }
            i++;
            proxy = proxy2;
        }
        return proxy == null ? Proxy.NO_PROXY : proxy;
    }

    protected HttpHost m17643a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        try {
            Proxy a = m17642a(this.f15457a.select(new URI(httpHost.toURI())));
            if (a.type() != Type.HTTP) {
                return null;
            }
            if (a.address() instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) a.address();
                return new HttpHost(m17641a(inetSocketAddress), inetSocketAddress.getPort());
            }
            throw new HttpException("Unable to handle non-Inet proxy address: " + a.address());
        } catch (Throwable e) {
            throw new HttpException("Cannot convert host to URI: " + httpHost, e);
        }
    }
}
