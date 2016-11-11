package org.p122a.p123a.p167i.p176f;

import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p156c.C2946d;

@C2913c
/* renamed from: org.a.a.i.f.d */
class C3170d implements C2946d {
    private final HttpResponse f15533a;
    private final C3169c f15534b;

    public C3170d(HttpResponse httpResponse, C3169c c3169c) {
        this.f15533a = httpResponse;
        this.f15534b = c3169c;
        C3177k.m17722a(httpResponse, c3169c);
    }

    public void addHeader(String str, String str2) {
        this.f15533a.addHeader(str, str2);
    }

    public void addHeader(Header header) {
        this.f15533a.addHeader(header);
    }

    public void close() {
        if (this.f15534b != null) {
            this.f15534b.abortConnection();
        }
    }

    public boolean containsHeader(String str) {
        return this.f15533a.containsHeader(str);
    }

    public Header[] getAllHeaders() {
        return this.f15533a.getAllHeaders();
    }

    public HttpEntity getEntity() {
        return this.f15533a.getEntity();
    }

    public Header getFirstHeader(String str) {
        return this.f15533a.getFirstHeader(str);
    }

    public Header[] getHeaders(String str) {
        return this.f15533a.getHeaders(str);
    }

    public Header getLastHeader(String str) {
        return this.f15533a.getLastHeader(str);
    }

    public Locale getLocale() {
        return this.f15533a.getLocale();
    }

    @Deprecated
    public HttpParams getParams() {
        return this.f15533a.getParams();
    }

    public ProtocolVersion getProtocolVersion() {
        return this.f15533a.getProtocolVersion();
    }

    public StatusLine getStatusLine() {
        return this.f15533a.getStatusLine();
    }

    public HeaderIterator headerIterator() {
        return this.f15533a.headerIterator();
    }

    public HeaderIterator headerIterator(String str) {
        return this.f15533a.headerIterator(str);
    }

    public void removeHeader(Header header) {
        this.f15533a.removeHeader(header);
    }

    public void removeHeaders(String str) {
        this.f15533a.removeHeaders(str);
    }

    public void setEntity(HttpEntity httpEntity) {
        this.f15533a.setEntity(httpEntity);
    }

    public void setHeader(String str, String str2) {
        this.f15533a.setHeader(str, str2);
    }

    public void setHeader(Header header) {
        this.f15533a.setHeader(header);
    }

    public void setHeaders(Header[] headerArr) {
        this.f15533a.setHeaders(headerArr);
    }

    public void setLocale(Locale locale) {
        this.f15533a.setLocale(locale);
    }

    @Deprecated
    public void setParams(HttpParams httpParams) {
        this.f15533a.setParams(httpParams);
    }

    public void setReasonPhrase(String str) {
        this.f15533a.setReasonPhrase(str);
    }

    public void setStatusCode(int i) {
        this.f15533a.setStatusCode(i);
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i) {
        this.f15533a.setStatusLine(protocolVersion, i);
    }

    public void setStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        this.f15533a.setStatusLine(protocolVersion, i, str);
    }

    public void setStatusLine(StatusLine statusLine) {
        this.f15533a.setStatusLine(statusLine);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpResponseProxy{");
        stringBuilder.append(this.f15533a);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
