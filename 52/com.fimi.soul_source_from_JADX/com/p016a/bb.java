package com.p016a;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: com.a.bb */
class bb extends FilterOutputStream {
    final /* synthetic */ ba f610a;

    private bb(ba baVar, OutputStream outputStream) {
        this.f610a = baVar;
        super(outputStream);
    }

    public void close() {
        try {
            this.out.close();
        } catch (IOException e) {
            this.f610a.f608d = true;
        }
    }

    public void flush() {
        try {
            this.out.flush();
        } catch (IOException e) {
            this.f610a.f608d = true;
        }
    }

    public void write(int i) {
        try {
            this.out.write(i);
        } catch (IOException e) {
            this.f610a.f608d = true;
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        try {
            this.out.write(bArr, i, i2);
        } catch (IOException e) {
            this.f610a.f608d = true;
        }
    }
}
