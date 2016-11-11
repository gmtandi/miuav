package org.p122a.p123a.p167i;

import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import it.p074a.p075a.C2799f;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.TokenIterator;
import org.apache.http.message.BasicTokenIterator;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.g */
public class C3201g implements ConnectionReuseStrategy {
    public static final C3201g f15638a;

    static {
        f15638a = new C3201g();
    }

    private boolean m17777a(HttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return (statusCode < C2799f.f14282t || statusCode == C1458u.f6934b || statusCode == 304 || statusCode == 205) ? false : true;
    }

    protected TokenIterator m17778a(HeaderIterator headerIterator) {
        return new BasicTokenIterator(headerIterator);
    }

    public boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext) {
        boolean z = true;
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        ProtocolVersion protocolVersion = httpResponse.getStatusLine().getProtocolVersion();
        Header firstHeader = httpResponse.getFirstHeader(C3004e.f15011W);
        if (firstHeader != null) {
            if (!"chunked".equalsIgnoreCase(firstHeader.getValue())) {
                return false;
            }
        } else if (m17777a(httpResponse)) {
            Header[] headers = httpResponse.getHeaders(C3004e.f15027m);
            if (headers.length != 1) {
                return false;
            }
            try {
                if (Integer.parseInt(headers[0].getValue()) < 0) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        HeaderIterator headerIterator = httpResponse.headerIterator(C3004e.f15024j);
        if (!headerIterator.hasNext()) {
            headerIterator = httpResponse.headerIterator("Proxy-Connection");
        }
        if (headerIterator.hasNext()) {
            try {
                TokenIterator a = m17778a(headerIterator);
                boolean z2 = false;
                while (a.hasNext()) {
                    String nextToken = a.nextToken();
                    if ("Close".equalsIgnoreCase(nextToken)) {
                        return false;
                    }
                    if ("Keep-Alive".equalsIgnoreCase(nextToken)) {
                        z2 = true;
                    }
                }
                if (z2) {
                    return true;
                }
            } catch (ParseException e2) {
                return false;
            }
        }
        if (protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
            z = false;
        }
        return z;
    }
}
