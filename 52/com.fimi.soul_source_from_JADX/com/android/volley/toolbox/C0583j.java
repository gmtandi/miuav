package com.android.volley.toolbox;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: com.android.volley.toolbox.j */
class C0583j extends FilterInputStream {
    private int f3627a;

    private C0583j(InputStream inputStream) {
        super(inputStream);
        this.f3627a = 0;
    }

    public int read() {
        int read = super.read();
        if (read != -1) {
            this.f3627a++;
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.f3627a += read;
        }
        return read;
    }
}
