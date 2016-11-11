package org.p122a.p123a.p167i.p168a;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/* renamed from: org.a.a.i.a.h */
class C3062h extends OutputStream {
    private final MessageDigest f15142a;
    private boolean f15143b;
    private byte[] f15144c;

    C3062h(MessageDigest messageDigest) {
        this.f15142a = messageDigest;
        this.f15142a.reset();
    }

    public byte[] m17196a() {
        return this.f15144c;
    }

    public void close() {
        if (!this.f15143b) {
            this.f15143b = true;
            this.f15144c = this.f15142a.digest();
            super.close();
        }
    }

    public void write(int i) {
        if (this.f15143b) {
            throw new IOException("Stream has been already closed");
        }
        this.f15142a.update((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (this.f15143b) {
            throw new IOException("Stream has been already closed");
        }
        this.f15142a.update(bArr, i, i2);
    }
}
