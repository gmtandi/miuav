package org.p122a.p123a.p152c.p155b;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* renamed from: org.a.a.c.b.g */
public class C2935g extends C2929a {
    public C2935g(HttpEntity httpEntity) {
        super(httpEntity);
    }

    InputStream m16805a(InputStream inputStream) {
        return new GZIPInputStream(inputStream);
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
