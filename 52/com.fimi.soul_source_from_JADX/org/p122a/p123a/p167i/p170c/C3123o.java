package org.p122a.p123a.p167i.p170c;

import java.io.IOException;
import java.io.InputStream;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.c.o */
class C3123o extends InputStream {
    private final InputStream f15432a;
    private final C3135z f15433b;

    public C3123o(InputStream inputStream, C3135z c3135z) {
        this.f15432a = inputStream;
        this.f15433b = c3135z;
    }

    public int available() {
        try {
            return this.f15432a.available();
        } catch (IOException e) {
            this.f15433b.m17653b("[available] I/O error : " + e.getMessage());
            throw e;
        }
    }

    public void close() {
        try {
            this.f15432a.close();
        } catch (IOException e) {
            this.f15433b.m17653b("[close] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void mark(int i) {
        super.mark(i);
    }

    public boolean markSupported() {
        return false;
    }

    public int read() {
        try {
            int read = this.f15432a.read();
            if (read == -1) {
                this.f15433b.m17653b("end of stream");
            } else {
                this.f15433b.m17651b(read);
            }
            return read;
        } catch (IOException e) {
            this.f15433b.m17653b("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public int read(byte[] bArr) {
        try {
            int read = this.f15432a.read(bArr);
            if (read == -1) {
                this.f15433b.m17653b("end of stream");
            } else if (read > 0) {
                this.f15433b.m17655b(bArr, 0, read);
            }
            return read;
        } catch (IOException e) {
            this.f15433b.m17653b("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        try {
            int read = this.f15432a.read(bArr, i, i2);
            if (read == -1) {
                this.f15433b.m17653b("end of stream");
            } else if (read > 0) {
                this.f15433b.m17655b(bArr, i, read);
            }
            return read;
        } catch (IOException e) {
            this.f15433b.m17653b("[read] I/O error: " + e.getMessage());
            throw e;
        }
    }

    public void reset() {
        super.reset();
    }

    public long skip(long j) {
        try {
            return super.skip(j);
        } catch (IOException e) {
            this.f15433b.m17653b("[skip] I/O error: " + e.getMessage());
            throw e;
        }
    }
}
