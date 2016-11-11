package com.xiaomi.mistatistic.sdk.p138a;

import java.io.OutputStream;

/* renamed from: com.xiaomi.mistatistic.sdk.a.d */
final class C2578d extends OutputStream {
    private OutputStream f12917a;
    private C2575a f12918b;
    private C2576b f12919c;
    private int f12920d;

    public C2578d(C2575a c2575a, OutputStream outputStream) {
        this.f12920d = 0;
        this.f12917a = outputStream;
        this.f12918b = c2575a;
    }

    public C2578d(C2576b c2576b, OutputStream outputStream) {
        this.f12920d = 0;
        this.f12917a = outputStream;
        this.f12919c = c2576b;
    }

    private void m14687a(Exception exception) {
        if (this.f12918b != null) {
            this.f12918b.m14679a(exception);
        }
        if (this.f12919c != null) {
            this.f12919c.m14683a(exception);
        }
    }

    public int m14688a() {
        return this.f12920d;
    }

    public void close() {
        try {
            this.f12917a.close();
        } catch (Exception e) {
            m14687a(e);
            throw e;
        }
    }

    public void flush() {
        try {
            this.f12917a.flush();
        } catch (Exception e) {
            m14687a(e);
            throw e;
        }
    }

    public void write(int i) {
        try {
            this.f12917a.write(i);
            this.f12920d++;
        } catch (Exception e) {
            m14687a(e);
            throw e;
        }
    }

    public void write(byte[] bArr) {
        try {
            this.f12917a.write(bArr);
            this.f12920d += bArr.length;
        } catch (Exception e) {
            m14687a(e);
            throw e;
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        try {
            this.f12917a.write(bArr, i, i2);
            this.f12920d += i2;
        } catch (Exception e) {
            m14687a(e);
            throw e;
        }
    }
}
