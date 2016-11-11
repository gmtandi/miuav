package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.c.a.a.a.a.a.e */
class C0861e extends FilterOutputStream {
    final /* synthetic */ C0860d f4666a;

    private C0861e(C0860d c0860d, OutputStream outputStream) {
        this.f4666a = c0860d;
        super(outputStream);
    }

    public void close() {
        try {
            this.out.close();
        } catch (IOException e) {
            this.f4666a.f4664d = true;
        }
    }

    public void flush() {
        try {
            this.out.flush();
        } catch (IOException e) {
            this.f4666a.f4664d = true;
        }
    }

    public void write(int i) {
        try {
            this.out.write(i);
        } catch (IOException e) {
            this.f4666a.f4664d = true;
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        try {
            this.out.write(bArr, i, i2);
        } catch (IOException e) {
            this.f4666a.f4664d = true;
        }
    }
}
