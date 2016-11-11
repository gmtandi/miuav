package org.p122a.p123a.p167i.p172g;

import java.io.InputStream;
import org.apache.http.io.SessionInputBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p173j.C3198a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.o */
public class C3196o extends InputStream {
    private final SessionInputBuffer f15617a;
    private boolean f15618b;

    public C3196o(SessionInputBuffer sessionInputBuffer) {
        this.f15618b = false;
        this.f15617a = (SessionInputBuffer) C3234a.m17886a((Object) sessionInputBuffer, "Session input buffer");
    }

    public int available() {
        return this.f15617a instanceof C3198a ? ((C3198a) this.f15617a).m17751c() : 0;
    }

    public void close() {
        this.f15618b = true;
    }

    public int read() {
        return this.f15618b ? -1 : this.f15617a.read();
    }

    public int read(byte[] bArr, int i, int i2) {
        return this.f15618b ? -1 : this.f15617a.read(bArr, i, i2);
    }
}
