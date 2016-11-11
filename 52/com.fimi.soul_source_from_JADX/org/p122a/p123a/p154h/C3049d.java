package org.p122a.p123a.p154h;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.h.d */
public class C3049d extends C2937a implements Cloneable {
    @Deprecated
    protected final byte[] f15097e;
    private final byte[] f15098f;
    private final int f15099g;
    private final int f15100h;

    public C3049d(byte[] bArr) {
        this(bArr, null);
    }

    public C3049d(byte[] bArr, int i, int i2) {
        this(bArr, i, i2, null);
    }

    public C3049d(byte[] bArr, int i, int i2, C3050e c3050e) {
        C3234a.m17886a((Object) bArr, "Source byte array");
        if (i < 0 || i > bArr.length || i2 < 0 || i + i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
        }
        this.f15097e = bArr;
        this.f15098f = bArr;
        this.f15099g = i;
        this.f15100h = i2;
        if (c3050e != null) {
            m16807a(c3050e.toString());
        }
    }

    public C3049d(byte[] bArr, C3050e c3050e) {
        C3234a.m17886a((Object) bArr, "Source byte array");
        this.f15097e = bArr;
        this.f15098f = bArr;
        this.f15099g = 0;
        this.f15100h = this.f15098f.length;
        if (c3050e != null) {
            m16807a(c3050e.toString());
        }
    }

    public Object clone() {
        return super.clone();
    }

    public InputStream getContent() {
        return new ByteArrayInputStream(this.f15098f, this.f15099g, this.f15100h);
    }

    public long getContentLength() {
        return (long) this.f15100h;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeTo(OutputStream outputStream) {
        C3234a.m17886a((Object) outputStream, "Output stream");
        outputStream.write(this.f15098f, this.f15099g, this.f15100h);
        outputStream.flush();
    }
}
