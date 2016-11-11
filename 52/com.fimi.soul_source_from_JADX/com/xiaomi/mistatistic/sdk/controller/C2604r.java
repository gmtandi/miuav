package com.xiaomi.mistatistic.sdk.controller;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.r */
public final class C2604r extends FilterInputStream {
    private boolean f12976a;

    public C2604r(InputStream inputStream) {
        super(inputStream);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!this.f12976a) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                return read;
            }
        }
        this.f12976a = true;
        return -1;
    }
}
