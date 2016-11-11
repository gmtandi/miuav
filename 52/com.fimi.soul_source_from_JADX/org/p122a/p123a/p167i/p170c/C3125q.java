package org.p122a.p123a.p167i.p170c;

import java.io.IOException;
import java.io.OutputStream;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.c.q */
class C3125q extends OutputStream {
    private final OutputStream f15437a;
    private final C3135z f15438b;

    public C3125q(OutputStream outputStream, C3135z c3135z) {
        this.f15437a = outputStream;
        this.f15438b = c3135z;
    }

    public void close() {
        try {
            this.f15437a.close();
        } catch (IOException e) {
            this.f15438b.m17647a("[close] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void flush() {
        try {
            this.f15437a.flush();
        } catch (IOException e) {
            this.f15438b.m17647a("[flush] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void write(int i) {
        try {
            this.f15438b.m17645a(i);
        } catch (IOException e) {
            this.f15438b.m17647a("[write] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void write(byte[] bArr) {
        try {
            this.f15438b.m17648a(bArr);
            this.f15437a.write(bArr);
        } catch (IOException e) {
            this.f15438b.m17647a("[write] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        try {
            this.f15438b.m17649a(bArr, i, i2);
            this.f15437a.write(bArr, i, i2);
        } catch (IOException e) {
            this.f15438b.m17647a("[write] I/O error: " + e.getMessage());
            throw e;
        }
    }
}
