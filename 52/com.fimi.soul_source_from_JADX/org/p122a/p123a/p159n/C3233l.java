package org.p122a.p123a.p159n;

import it.p074a.p075a.C2799f;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.n.l */
public class C3233l implements HttpResponseInterceptor {
    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        C2967c b = C2967c.m16873b(httpContext);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 400 || statusCode == 408 || statusCode == 411 || statusCode == 413 || statusCode == 414 || statusCode == C2799f.f14266d || statusCode == C2799f.f14264b) {
            httpResponse.setHeader(C3004e.f15024j, "Close");
            return;
        }
        Header firstHeader = httpResponse.getFirstHeader(C3004e.f15024j);
        if (firstHeader == null || !"Close".equalsIgnoreCase(firstHeader.getValue())) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                ProtocolVersion protocolVersion = httpResponse.getStatusLine().getProtocolVersion();
                if (entity.getContentLength() < 0 && (!entity.isChunked() || protocolVersion.lessEquals(HttpVersion.HTTP_1_0))) {
                    httpResponse.setHeader(C3004e.f15024j, "Close");
                    return;
                }
            }
            HttpRequest q = b.m16879q();
            if (q != null) {
                firstHeader = q.getFirstHeader(C3004e.f15024j);
                if (firstHeader != null) {
                    httpResponse.setHeader(C3004e.f15024j, firstHeader.getValue());
                } else if (q.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
                    httpResponse.setHeader(C3004e.f15024j, "Close");
                }
            }
        }
    }
}
