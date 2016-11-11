package org.p122a.p123a.p154h;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3237d;

@C2913c
/* renamed from: org.a.a.h.c */
public class C3048c extends C2928g {
    private final byte[] f15096b;

    public C3048c(HttpEntity httpEntity) {
        super(httpEntity);
        if (!httpEntity.isRepeatable() || httpEntity.getContentLength() < 0) {
            this.f15096b = C3237d.m17905c(httpEntity);
        } else {
            this.f15096b = null;
        }
    }

    public InputStream getContent() {
        return this.f15096b != null ? new ByteArrayInputStream(this.f15096b) : super.getContent();
    }

    public long getContentLength() {
        return this.f15096b != null ? (long) this.f15096b.length : super.getContentLength();
    }

    public boolean isChunked() {
        return this.f15096b == null && super.isChunked();
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.f15096b == null && super.isStreaming();
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        if (this.f15096b != null) {
            outputStream.write(this.f15096b);
        } else {
            super.writeTo(outputStream);
        }
    }
}
