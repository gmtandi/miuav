package com.p054c.p055a.p063b.p064a;

import java.io.InputStream;

/* renamed from: com.c.a.b.a.a */
public class C0895a extends InputStream {
    private final InputStream f4751a;
    private final int f4752b;

    public C0895a(InputStream inputStream, int i) {
        this.f4751a = inputStream;
        this.f4752b = i;
    }

    public int available() {
        return this.f4752b;
    }

    public void close() {
        this.f4751a.close();
    }

    public void mark(int i) {
        this.f4751a.mark(i);
    }

    public boolean markSupported() {
        return this.f4751a.markSupported();
    }

    public int read() {
        return this.f4751a.read();
    }

    public int read(byte[] bArr) {
        return this.f4751a.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) {
        return this.f4751a.read(bArr, i, i2);
    }

    public void reset() {
        this.f4751a.reset();
    }

    public long skip(long j) {
        return this.f4751a.skip(j);
    }
}
