package org.p122a.p123a.p167i;

import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.i */
public class C3206i implements HttpResponseFactory {
    public static final C3206i f15649a;
    protected final ReasonPhraseCatalog f15650b;

    static {
        f15649a = new C3206i();
    }

    public C3206i() {
        this(C3207j.f15651a);
    }

    public C3206i(ReasonPhraseCatalog reasonPhraseCatalog) {
        this.f15650b = (ReasonPhraseCatalog) C3234a.m17886a((Object) reasonPhraseCatalog, "Reason phrase catalog");
    }

    protected Locale m17787a(HttpContext httpContext) {
        return Locale.getDefault();
    }

    public HttpResponse newHttpResponse(ProtocolVersion protocolVersion, int i, HttpContext httpContext) {
        C3234a.m17886a((Object) protocolVersion, "HTTP version");
        Locale a = m17787a(httpContext);
        return new BasicHttpResponse(new BasicStatusLine(protocolVersion, i, this.f15650b.getReason(i, a)), this.f15650b, a);
    }

    public HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext) {
        C3234a.m17886a((Object) statusLine, "Status line");
        return new BasicHttpResponse(statusLine, this.f15650b, m17787a(httpContext));
    }
}
