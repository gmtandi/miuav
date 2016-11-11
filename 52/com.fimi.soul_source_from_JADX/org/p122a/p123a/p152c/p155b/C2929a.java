package org.p122a.p123a.p152c.p155b;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.p122a.p123a.p154h.C2928g;
import org.p122a.p123a.p180o.C3234a;

/* renamed from: org.a.a.c.b.a */
abstract class C2929a extends C2928g {
    private static final int f14826b = 2048;
    private InputStream f14827c;

    public C2929a(HttpEntity httpEntity) {
        super(httpEntity);
    }

    private InputStream m16777a() {
        return new C2936h(this.a.getContent(), this);
    }

    abstract InputStream m16778a(InputStream inputStream);

    public InputStream getContent() {
        if (!this.a.isStreaming()) {
            return m16777a();
        }
        if (this.f14827c == null) {
            this.f14827c = m16777a();
        }
        return this.f14827c;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        InputStream content = getContent();
        try {
            byte[] bArr = new byte[f14826b];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            content.close();
        }
    }
}
