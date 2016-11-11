package org.p122a.p123a.p152c.p155b;

import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* renamed from: org.a.a.c.b.d */
class C2932d extends InflaterInputStream {
    private boolean f14829a;

    public C2932d(InputStream inputStream, Inflater inflater) {
        super(inputStream, inflater);
        this.f14829a = false;
    }

    public void close() {
        if (!this.f14829a) {
            this.f14829a = true;
            this.inf.end();
            super.close();
        }
    }
}
