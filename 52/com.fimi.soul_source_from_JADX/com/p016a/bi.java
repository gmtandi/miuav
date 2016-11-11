package com.p016a;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* renamed from: com.a.bi */
class bi extends ByteArrayOutputStream {
    final /* synthetic */ bh f628a;

    bi(bh bhVar, int i) {
        this.f628a = bhVar;
        super(i);
    }

    public String toString() {
        int i = (this.count <= 0 || this.buf[this.count - 1] != 13) ? this.count : this.count - 1;
        try {
            return new String(this.buf, 0, i, this.f628a.f624b.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
