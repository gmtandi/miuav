package org.p122a.p123a.p167i.p172g;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.f */
public class C3186f extends OutputStream {
    private final SessionOutputBuffer f15599a;
    private final long f15600b;
    private long f15601c;
    private boolean f15602d;

    public C3186f(SessionOutputBuffer sessionOutputBuffer, long j) {
        this.f15601c = 0;
        this.f15602d = false;
        this.f15599a = (SessionOutputBuffer) C3234a.m17886a((Object) sessionOutputBuffer, "Session output buffer");
        this.f15600b = C3234a.m17891b(j, "Content length");
    }

    public void close() {
        if (!this.f15602d) {
            this.f15602d = true;
            this.f15599a.flush();
        }
    }

    public void flush() {
        this.f15599a.flush();
    }

    public void write(int i) {
        if (this.f15602d) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.f15601c < this.f15600b) {
            this.f15599a.write(i);
            this.f15601c++;
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f15602d) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.f15601c < this.f15600b) {
            long j = this.f15600b - this.f15601c;
            if (((long) i2) > j) {
                i2 = (int) j;
            }
            this.f15599a.write(bArr, i, i2);
            this.f15601c += (long) i2;
        }
    }
}
