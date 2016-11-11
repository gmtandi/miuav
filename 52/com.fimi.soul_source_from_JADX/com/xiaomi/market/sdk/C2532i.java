package com.xiaomi.market.sdk;

import java.io.OutputStream;

/* renamed from: com.xiaomi.market.sdk.i */
public abstract class C2532i extends OutputStream {
    final /* synthetic */ C2530c f12820G;
    protected OutputStream f12821S;

    public C2532i(C2530c c2530c, OutputStream outputStream) {
        this.f12820G = c2530c;
        if (outputStream == null) {
            throw new IllegalArgumentException("outputstream is null");
        }
        this.f12821S = outputStream;
    }

    public void close() {
        this.f12821S.close();
    }

    public void flush() {
        this.f12821S.flush();
    }

    public abstract void reset();

    public void write(int i) {
        this.f12821S.write(i);
    }

    public void write(byte[] bArr) {
        this.f12821S.write(bArr);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.f12821S.write(bArr, i, i2);
    }
}
