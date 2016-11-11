package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* renamed from: com.c.a.a.a.a.a.j */
class C0867j extends ByteArrayOutputStream {
    final /* synthetic */ C0866i f4697a;

    C0867j(C0866i c0866i, int i) {
        this.f4697a = c0866i;
        super(i);
    }

    public String toString() {
        int i = (this.count <= 0 || this.buf[this.count - 1] != 13) ? this.count : this.count - 1;
        try {
            return new String(this.buf, 0, i, this.f4697a.f4693d.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
