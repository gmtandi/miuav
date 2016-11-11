package org.p122a.p123a.p154h;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.h.g */
public class C2928g implements HttpEntity {
    protected HttpEntity f14825a;

    public C2928g(HttpEntity httpEntity) {
        this.f14825a = (HttpEntity) C3234a.m17886a((Object) httpEntity, "Wrapped entity");
    }

    @Deprecated
    public void consumeContent() {
        this.f14825a.consumeContent();
    }

    public InputStream getContent() {
        return this.f14825a.getContent();
    }

    public Header getContentEncoding() {
        return this.f14825a.getContentEncoding();
    }

    public long getContentLength() {
        return this.f14825a.getContentLength();
    }

    public Header getContentType() {
        return this.f14825a.getContentType();
    }

    public boolean isChunked() {
        return this.f14825a.isChunked();
    }

    public boolean isRepeatable() {
        return this.f14825a.isRepeatable();
    }

    public boolean isStreaming() {
        return this.f14825a.isStreaming();
    }

    public void writeTo(OutputStream outputStream) {
        this.f14825a.writeTo(outputStream);
    }
}
