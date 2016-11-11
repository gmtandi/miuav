package org.p122a.p137b.p183c;

import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: org.a.b.c.a */
public class C3266a extends C3265c {
    protected InputStream f15773a;
    protected OutputStream f15774b;

    protected C3266a() {
        this.f15773a = null;
        this.f15774b = null;
    }

    public C3266a(OutputStream outputStream) {
        this.f15773a = null;
        this.f15774b = null;
        this.f15774b = outputStream;
    }

    public int m18076a(byte[] bArr, int i, int i2) {
        if (this.f15773a == null) {
            throw new C3268d(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.f15773a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new C3268d(4);
        } catch (Throwable e) {
            throw new C3268d(0, e);
        }
    }

    public void m18077b(byte[] bArr, int i, int i2) {
        if (this.f15774b == null) {
            throw new C3268d(1, "Cannot write to null outputStream");
        }
        try {
            this.f15774b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new C3268d(0, e);
        }
    }
}
