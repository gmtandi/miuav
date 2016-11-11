package org.p122a.p123a.p159n;

import java.net.InetAddress;
import org.apache.http.HttpConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpInetConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.n.j */
public class C3231j implements HttpRequestInterceptor {
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        C2967c b = C2967c.m16873b(httpContext);
        ProtocolVersion protocolVersion = httpRequest.getRequestLine().getProtocolVersion();
        if ((!httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") || !protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) && !httpRequest.containsHeader(C3004e.f15040z)) {
            HttpHost httpHost;
            HttpHost t = b.m16882t();
            if (t == null) {
                HttpConnection p = b.m16878p();
                if (p instanceof HttpInetConnection) {
                    InetAddress remoteAddress = ((HttpInetConnection) p).getRemoteAddress();
                    int remotePort = ((HttpInetConnection) p).getRemotePort();
                    if (remoteAddress != null) {
                        httpHost = new HttpHost(remoteAddress.getHostName(), remotePort);
                        if (httpHost == null) {
                            if (!protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
                                throw new ProtocolException("Target host missing");
                            }
                            return;
                        }
                    }
                }
                httpHost = t;
                if (httpHost == null) {
                    if (!protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
                        throw new ProtocolException("Target host missing");
                    }
                    return;
                }
            }
            httpHost = t;
            httpRequest.addHeader(C3004e.f15040z, httpHost.toHostString());
        }
    }
}
