package com.xiaomi.mistatistic.sdk.p138a;

import java.io.InputStream;

/* renamed from: com.xiaomi.mistatistic.sdk.a.c */
final class C2577c extends InputStream {
    private InputStream f12913a;
    private C2575a f12914b;
    private C2576b f12915c;
    private int f12916d;

    public C2577c(C2575a c2575a, InputStream inputStream) {
        this.f12916d = 0;
        this.f12914b = c2575a;
        this.f12913a = inputStream;
    }

    public C2577c(C2576b c2576b, InputStream inputStream) {
        this.f12916d = 0;
        this.f12915c = c2576b;
        this.f12913a = inputStream;
    }

    private void m14685a(Exception exception) {
        if (this.f12914b != null) {
            this.f12914b.m14679a(exception);
        }
        if (this.f12915c != null) {
            this.f12915c.m14683a(exception);
        }
    }

    public int m14686a() {
        return this.f12916d;
    }

    public int available() {
        try {
            return this.f12913a.available();
        } catch (Exception e) {
            m14685a(e);
            throw e;
        }
    }

    public void close() {
        if (this.f12914b != null) {
            this.f12914b.m14677a();
        }
        if (this.f12915c != null) {
            this.f12915c.m14681a();
        }
        try {
            this.f12913a.close();
        } catch (Exception e) {
            m14685a(e);
            throw e;
        }
    }

    public void mark(int i) {
        this.f12913a.mark(i);
    }

    public boolean markSupported() {
        return this.f12913a.markSupported();
    }

    public int read() {
        try {
            int read = this.f12913a.read();
            if (read != -1) {
                this.f12916d++;
            }
            return read;
        } catch (Exception e) {
            m14685a(e);
            throw e;
        }
    }

    public int read(byte[] bArr) {
        try {
            int read = this.f12913a.read(bArr);
            if (read != -1) {
                this.f12916d += read;
            }
            return read;
        } catch (Exception e) {
            m14685a(e);
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        try {
            int read = this.f12913a.read(bArr, i, i2);
            if (read != -1) {
                this.f12916d += read;
            }
            return read;
        } catch (Exception e) {
            m14685a(e);
            throw e;
        }
    }

    public synchronized void reset() {
        try {
            this.f12913a.reset();
        } catch (Exception e) {
            m14685a(e);
            throw e;
        }
    }

    public long skip(long j) {
        try {
            return this.f12913a.skip(j);
        } catch (Exception e) {
            m14685a(e);
            throw e;
        }
    }
}
