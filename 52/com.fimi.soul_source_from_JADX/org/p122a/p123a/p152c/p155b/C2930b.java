package org.p122a.p123a.p152c.p155b;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* renamed from: org.a.a.c.b.b */
public class C2930b extends C2929a {
    public C2930b(HttpEntity httpEntity) {
        super(httpEntity);
    }

    InputStream m16779a(InputStream inputStream) {
        return new C2931c(inputStream);
    }

    public /* bridge */ /* synthetic */ InputStream getContent() {
        return super.getContent();
    }

    public Header getContentEncoding() {
        return null;
    }

    public long getContentLength() {
        return -1;
    }

    public /* bridge */ /* synthetic */ void writeTo(OutputStream outputStream) {
        super.writeTo(outputStream);
    }
}
