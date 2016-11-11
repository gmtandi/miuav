package org.p122a.p123a.p152c.p155b;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p154h.C2928g;
import org.p122a.p123a.p180o.C3234a;

/* renamed from: org.a.a.c.b.f */
public class C2934f extends C2928g {
    private static final String f14840b = "gzip";

    public C2934f(HttpEntity httpEntity) {
        super(httpEntity);
    }

    public InputStream getContent() {
        throw new UnsupportedOperationException();
    }

    public Header getContentEncoding() {
        return new BasicHeader(C3004e.f15025k, f14840b);
    }

    public long getContentLength() {
        return -1;
    }

    public boolean isChunked() {
        return true;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        OutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        this.a.writeTo(gZIPOutputStream);
        gZIPOutputStream.close();
    }
}
