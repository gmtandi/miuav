package org.p122a.p123a.p152c.p155b;

import java.io.InputStream;

/* renamed from: org.a.a.c.b.c */
public class C2931c extends InputStream {
    private InputStream f14828a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C2931c(java.io.InputStream r9) {
        /*
        r8 = this;
        r3 = 1;
        r7 = -1;
        r8.<init>();
        r0 = 6;
        r0 = new byte[r0];
        r1 = new java.io.PushbackInputStream;
        r2 = r0.length;
        r1.<init>(r9, r2);
        r2 = r1.read(r0);
        if (r2 != r7) goto L_0x001c;
    L_0x0014:
        r0 = new java.io.IOException;
        r1 = "Unable to read the response";
        r0.<init>(r1);
        throw r0;
    L_0x001c:
        r3 = new byte[r3];
        r4 = new java.util.zip.Inflater;
        r4.<init>();
    L_0x0023:
        r5 = r4.inflate(r3);	 Catch:{ DataFormatException -> 0x0037 }
        if (r5 != 0) goto L_0x0053;
    L_0x0029:
        r6 = r4.finished();	 Catch:{ DataFormatException -> 0x0037 }
        if (r6 == 0) goto L_0x004d;
    L_0x002f:
        r3 = new java.io.IOException;	 Catch:{ DataFormatException -> 0x0037 }
        r5 = "Unable to read the response";
        r3.<init>(r5);	 Catch:{ DataFormatException -> 0x0037 }
        throw r3;	 Catch:{ DataFormatException -> 0x0037 }
    L_0x0037:
        r3 = move-exception;
        r3 = 0;
        r1.unread(r0, r3, r2);	 Catch:{ all -> 0x005d }
        r0 = new org.a.a.c.b.d;	 Catch:{ all -> 0x005d }
        r2 = new java.util.zip.Inflater;	 Catch:{ all -> 0x005d }
        r3 = 1;
        r2.<init>(r3);	 Catch:{ all -> 0x005d }
        r0.<init>(r1, r2);	 Catch:{ all -> 0x005d }
        r8.f14828a = r0;	 Catch:{ all -> 0x005d }
        r4.end();
    L_0x004c:
        return;
    L_0x004d:
        r6 = r4.needsDictionary();	 Catch:{ DataFormatException -> 0x0037 }
        if (r6 == 0) goto L_0x0062;
    L_0x0053:
        if (r5 != r7) goto L_0x006c;
    L_0x0055:
        r3 = new java.io.IOException;	 Catch:{ DataFormatException -> 0x0037 }
        r5 = "Unable to read the response";
        r3.<init>(r5);	 Catch:{ DataFormatException -> 0x0037 }
        throw r3;	 Catch:{ DataFormatException -> 0x0037 }
    L_0x005d:
        r0 = move-exception;
        r4.end();
        throw r0;
    L_0x0062:
        r5 = r4.needsInput();	 Catch:{ DataFormatException -> 0x0037 }
        if (r5 == 0) goto L_0x0023;
    L_0x0068:
        r4.setInput(r0);	 Catch:{ DataFormatException -> 0x0037 }
        goto L_0x0023;
    L_0x006c:
        r3 = 0;
        r1.unread(r0, r3, r2);	 Catch:{ DataFormatException -> 0x0037 }
        r3 = new org.a.a.c.b.d;	 Catch:{ DataFormatException -> 0x0037 }
        r5 = new java.util.zip.Inflater;	 Catch:{ DataFormatException -> 0x0037 }
        r5.<init>();	 Catch:{ DataFormatException -> 0x0037 }
        r3.<init>(r1, r5);	 Catch:{ DataFormatException -> 0x0037 }
        r8.f14828a = r3;	 Catch:{ DataFormatException -> 0x0037 }
        r4.end();
        goto L_0x004c;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.c.b.c.<init>(java.io.InputStream):void");
    }

    public int available() {
        return this.f14828a.available();
    }

    public void close() {
        this.f14828a.close();
    }

    public void mark(int i) {
        this.f14828a.mark(i);
    }

    public boolean markSupported() {
        return this.f14828a.markSupported();
    }

    public int read() {
        return this.f14828a.read();
    }

    public int read(byte[] bArr) {
        return this.f14828a.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) {
        return this.f14828a.read(bArr, i, i2);
    }

    public void reset() {
        this.f14828a.reset();
    }

    public long skip(long j) {
        return this.f14828a.skip(j);
    }
}
