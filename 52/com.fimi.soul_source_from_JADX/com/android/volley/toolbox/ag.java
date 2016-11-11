package com.android.volley.toolbox;

import java.io.ByteArrayOutputStream;

public class ag extends ByteArrayOutputStream {
    private static final int f3592a = 256;
    private final C0577d f3593b;

    public ag(C0577d c0577d) {
        this(c0577d, f3592a);
    }

    public ag(C0577d c0577d, int i) {
        this.f3593b = c0577d;
        this.buf = this.f3593b.m5187a(Math.max(i, f3592a));
    }

    private void m5164a(int i) {
        if (this.count + i > this.buf.length) {
            Object a = this.f3593b.m5187a((this.count + i) * 2);
            System.arraycopy(this.buf, 0, a, 0, this.count);
            this.f3593b.m5186a(this.buf);
            this.buf = a;
        }
    }

    public void close() {
        this.f3593b.m5186a(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.f3593b.m5186a(this.buf);
    }

    public synchronized void write(int i) {
        m5164a(1);
        super.write(i);
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        m5164a(i2);
        super.write(bArr, i, i2);
    }
}
