package org.p122a.p123a.p152c.p155b;

import java.io.InputStream;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.c.b.h */
class C2936h extends InputStream {
    private final InputStream f14841a;
    private final C2929a f14842b;
    private InputStream f14843c;

    public C2936h(InputStream inputStream, C2929a c2929a) {
        this.f14841a = inputStream;
        this.f14842b = c2929a;
    }

    private void m16806a() {
        if (this.f14843c == null) {
            this.f14843c = this.f14842b.m16778a(this.f14841a);
        }
    }

    public int available() {
        m16806a();
        return this.f14843c.available();
    }

    public void close() {
        try {
            if (this.f14843c != null) {
                this.f14843c.close();
            }
            this.f14841a.close();
        } catch (Throwable th) {
            this.f14841a.close();
        }
    }

    public boolean markSupported() {
        return false;
    }

    public int read() {
        m16806a();
        return this.f14843c.read();
    }

    public int read(byte[] bArr) {
        m16806a();
        return this.f14843c.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) {
        m16806a();
        return this.f14843c.read(bArr, i, i2);
    }

    public long skip(long j) {
        m16806a();
        return this.f14843c.skip(j);
    }
}
