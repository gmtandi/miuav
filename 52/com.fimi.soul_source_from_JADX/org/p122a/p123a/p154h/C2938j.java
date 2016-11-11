package org.p122a.p123a.p154h;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.h.j */
public class C2938j extends C2937a implements Cloneable {
    protected final byte[] f14848e;

    public C2938j(String str) {
        this(str, C3050e.f15113m);
    }

    public C2938j(String str, String str2) {
        this(str, C3050e.m17156a(C3050e.f15110j.m17164a(), str2));
    }

    @Deprecated
    public C2938j(String str, String str2, String str3) {
        C3234a.m17886a((Object) str, "Source string");
        if (str2 == null) {
            str2 = "text/plain";
        }
        if (str3 == null) {
            str3 = "ISO-8859-1";
        }
        this.f14848e = str.getBytes(str3);
        m16807a(str2 + "; charset=" + str3);
    }

    public C2938j(String str, Charset charset) {
        this(str, C3050e.m17157a(C3050e.f15110j.m17164a(), charset));
    }

    public C2938j(String str, C3050e c3050e) {
        C3234a.m17886a((Object) str, "Source string");
        Charset b = c3050e != null ? c3050e.m17167b() : null;
        if (b == null) {
            b = Charset.forName("ISO-8859-1");
        }
        try {
            this.f14848e = str.getBytes(b.name());
            if (c3050e != null) {
                m16807a(c3050e.toString());
            }
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedCharsetException(b.name());
        }
    }

    public Object clone() {
        return super.clone();
    }

    public InputStream getContent() {
        return new ByteArrayInputStream(this.f14848e);
    }

    public long getContentLength() {
        return (long) this.f14848e.length;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        outputStream.write(this.f14848e);
        outputStream.flush();
    }
}
