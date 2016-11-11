package org.p122a.p123a.p167i.p172g;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.io.SessionOutputBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.p */
public class C3197p extends OutputStream {
    private final SessionOutputBuffer f15619a;
    private boolean f15620b;

    public C3197p(SessionOutputBuffer sessionOutputBuffer) {
        this.f15620b = false;
        this.f15619a = (SessionOutputBuffer) C3234a.m17886a((Object) sessionOutputBuffer, "Session output buffer");
    }

    public void close() {
        if (!this.f15620b) {
            this.f15620b = true;
            this.f15619a.flush();
        }
    }

    public void flush() {
        this.f15619a.flush();
    }

    public void write(int i) {
        if (this.f15620b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f15619a.write(i);
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f15620b) {
            throw new IOException("Attempted write to closed stream.");
        }
        this.f15619a.write(bArr, i, i2);
    }
}
