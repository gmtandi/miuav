package org.p122a.p123a.p124f;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.apache.http.HttpHost;
import org.p122a.p123a.p180o.C3234a;

@Deprecated
/* renamed from: org.a.a.f.f */
public class C3038f extends InetSocketAddress {
    private static final long serialVersionUID = -6650701828361907957L;
    private final HttpHost f15093a;

    public C3038f(HttpHost httpHost, InetAddress inetAddress, int i) {
        super(inetAddress, i);
        C3234a.m17886a((Object) httpHost, "HTTP host");
        this.f15093a = httpHost;
    }

    public HttpHost m17144a() {
        return this.f15093a;
    }

    public String toString() {
        return this.f15093a.getHostName() + ":" + getPort();
    }
}
