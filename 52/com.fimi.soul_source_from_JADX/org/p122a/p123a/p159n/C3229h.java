package org.p122a.p123a.p159n;

import com.tencent.connect.common.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
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
/* renamed from: org.a.a.n.h */
public class C3229h implements HttpRequestInterceptor {
    private final boolean f15699a;

    public C3229h() {
        this(false);
    }

    public C3229h(boolean z) {
        this.f15699a = z;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            if (this.f15699a) {
                httpRequest.removeHeaders(C3004e.f15011W);
                httpRequest.removeHeaders(C3004e.f15027m);
            } else if (httpRequest.containsHeader(C3004e.f15011W)) {
                throw new ProtocolException("Transfer-encoding header already present");
            } else if (httpRequest.containsHeader(C3004e.f15027m)) {
                throw new ProtocolException("Content-Length header already present");
            }
            ProtocolVersion protocolVersion = httpRequest.getRequestLine().getProtocolVersion();
            HttpEntity entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            if (entity == null) {
                httpRequest.addHeader(C3004e.f15027m, Constants.VIA_RESULT_SUCCESS);
                return;
            }
            if (!entity.isChunked() && entity.getContentLength() >= 0) {
                httpRequest.addHeader(C3004e.f15027m, Long.toString(entity.getContentLength()));
            } else if (protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
                throw new ProtocolException("Chunked transfer encoding not allowed for " + protocolVersion);
            } else {
                httpRequest.addHeader(C3004e.f15011W, "chunked");
            }
            if (!(entity.getContentType() == null || httpRequest.containsHeader(C3004e.f15031q))) {
                httpRequest.addHeader(entity.getContentType());
            }
            if (entity.getContentEncoding() != null && !httpRequest.containsHeader(C3004e.f15025k)) {
                httpRequest.addHeader(entity.getContentEncoding());
            }
        }
    }
}
