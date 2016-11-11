package org.p122a.p123a.p167i.p176f;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.f.j */
class C3176j implements HttpEntity {
    private final HttpEntity f15558a;
    private boolean f15559b;

    C3176j(HttpEntity httpEntity) {
        this.f15559b = false;
        this.f15558a = httpEntity;
    }

    static void m17717a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        HttpEntity entity = httpEntityEnclosingRequest.getEntity();
        if (entity != null && !entity.isRepeatable() && !C3176j.m17718a(entity)) {
            httpEntityEnclosingRequest.setEntity(new C3176j(entity));
        }
    }

    static boolean m17718a(HttpEntity httpEntity) {
        return httpEntity instanceof C3176j;
    }

    static boolean m17719a(HttpRequest httpRequest) {
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntity entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
            if (entity != null) {
                return (!C3176j.m17718a(entity) || ((C3176j) entity).m17721b()) ? entity.isRepeatable() : true;
            }
        }
        return true;
    }

    public HttpEntity m17720a() {
        return this.f15558a;
    }

    public boolean m17721b() {
        return this.f15559b;
    }

    @Deprecated
    public void consumeContent() {
        this.f15559b = true;
        this.f15558a.consumeContent();
    }

    public InputStream getContent() {
        return this.f15558a.getContent();
    }

    public Header getContentEncoding() {
        return this.f15558a.getContentEncoding();
    }

    public long getContentLength() {
        return this.f15558a.getContentLength();
    }

    public Header getContentType() {
        return this.f15558a.getContentType();
    }

    public boolean isChunked() {
        return this.f15558a.isChunked();
    }

    public boolean isRepeatable() {
        return this.f15558a.isRepeatable();
    }

    public boolean isStreaming() {
        return this.f15558a.isStreaming();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("RequestEntityProxy{");
        stringBuilder.append(this.f15558a);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public void writeTo(OutputStream outputStream) {
        this.f15559b = true;
        this.f15558a.writeTo(outputStream);
    }
}
