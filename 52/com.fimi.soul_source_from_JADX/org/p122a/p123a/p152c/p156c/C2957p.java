package org.p122a.p123a.p152c.p156c;

import java.net.URI;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.c.c.p */
public class C2957p extends AbstractHttpMessage implements HttpUriRequest {
    private final HttpRequest f14866a;
    private final String f14867b;
    private ProtocolVersion f14868c;
    private URI f14869d;

    private C2957p(HttpRequest httpRequest) {
        this.f14866a = httpRequest;
        this.f14868c = this.f14866a.getRequestLine().getProtocolVersion();
        this.f14867b = this.f14866a.getRequestLine().getMethod();
        if (httpRequest instanceof HttpUriRequest) {
            this.f14869d = ((HttpUriRequest) httpRequest).getURI();
        } else {
            this.f14869d = null;
        }
        setHeaders(httpRequest.getAllHeaders());
    }

    public static C2957p m16830a(HttpRequest httpRequest) {
        return httpRequest == null ? null : httpRequest instanceof HttpEntityEnclosingRequest ? new C2959r((HttpEntityEnclosingRequest) httpRequest) : new C2957p(httpRequest);
    }

    public HttpRequest m16831a() {
        return this.f14866a;
    }

    public void m16832a(URI uri) {
        this.f14869d = uri;
    }

    public void m16833a(ProtocolVersion protocolVersion) {
        this.f14868c = protocolVersion;
    }

    public void abort() {
        throw new UnsupportedOperationException();
    }

    public String getMethod() {
        return this.f14867b;
    }

    @Deprecated
    public HttpParams getParams() {
        if (this.params == null) {
            this.params = this.f14866a.getParams().copy();
        }
        return this.params;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.f14868c != null ? this.f14868c : this.f14866a.getProtocolVersion();
    }

    public RequestLine getRequestLine() {
        String toASCIIString = this.f14869d != null ? this.f14869d.toASCIIString() : this.f14866a.getRequestLine().getUri();
        if (toASCIIString == null || toASCIIString.length() == 0) {
            toASCIIString = "/";
        }
        return new BasicRequestLine(this.f14867b, toASCIIString, getProtocolVersion());
    }

    public URI getURI() {
        return this.f14869d;
    }

    public boolean isAborted() {
        return false;
    }

    public String toString() {
        return getRequestLine() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.headergroup;
    }
}
