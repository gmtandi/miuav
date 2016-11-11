package org.p122a.p123a.p152c.p156c;

import java.net.URI;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.params.HttpProtocolParams;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p153a.C2925c;

@C2913c
/* renamed from: org.a.a.c.c.o */
public abstract class C2948o extends C2942a implements C2947e, HttpUriRequest {
    private ProtocolVersion f14855a;
    private URI f14856b;
    private C2925c f14857c;

    public void m16824a(URI uri) {
        this.f14856b = uri;
    }

    public void m16825a(C2925c c2925c) {
        this.f14857c = c2925c;
    }

    public void m16826a(ProtocolVersion protocolVersion) {
        this.f14855a = protocolVersion;
    }

    public void m16827c() {
    }

    public void m16828d() {
        m16820b();
    }

    public C2925c g_() {
        return this.f14857c;
    }

    public abstract String getMethod();

    public ProtocolVersion getProtocolVersion() {
        return this.f14855a != null ? this.f14855a : HttpProtocolParams.getVersion(getParams());
    }

    public RequestLine getRequestLine() {
        String method = getMethod();
        ProtocolVersion protocolVersion = getProtocolVersion();
        URI uri = getURI();
        String str = null;
        if (uri != null) {
            str = uri.toASCIIString();
        }
        if (str == null || str.length() == 0) {
            str = "/";
        }
        return new BasicRequestLine(method, str, protocolVersion);
    }

    public URI getURI() {
        return this.f14856b;
    }

    public String toString() {
        return getMethod() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getURI() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getProtocolVersion();
    }
}
