package it.p074a.p075a.p140a;

import com.tencent.mm.sdk.platformtools.Util;
import java.io.IOException;
import java.io.InputStream;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: it.a.a.a.b */
class C2775b extends InputStream {
    private InputStream f14151a;
    private int[] f14152b;
    private int f14153c;
    private boolean f14154d;

    public C2775b(InputStream inputStream) {
        this.f14153c = 0;
        this.f14154d = false;
        this.f14151a = inputStream;
    }

    private void m15830a() {
        int i = 1;
        int i2 = 0;
        char[] cArr = new char[4];
        int i3 = 0;
        do {
            int read = this.f14151a.read();
            if (read != -1) {
                char c = (char) read;
                if (C2774a.f14149a.indexOf(c) != -1 || c == C2774a.f14150b) {
                    read = i3 + 1;
                    cArr[i3] = c;
                    i3 = read;
                    continue;
                } else if (!(c == C3022o.f15053a || c == '\n')) {
                    throw new IOException("Bad base64 stream");
                }
            } else if (i3 != 0) {
                throw new IOException("Bad base64 stream");
            } else {
                this.f14152b = new int[0];
                this.f14154d = true;
                return;
            }
        } while (i3 < 4);
        i3 = 0;
        for (read = 0; read < 4; read++) {
            if (cArr[read] != C2774a.f14150b) {
                if (i3 != 0) {
                    throw new IOException("Bad base64 stream");
                }
            } else if (i3 == 0) {
                i3 = 1;
            }
        }
        if (cArr[3] != C2774a.f14150b) {
            i = 3;
        } else if (this.f14151a.read() != -1) {
            throw new IOException("Bad base64 stream");
        } else {
            this.f14154d = true;
            if (cArr[2] != C2774a.f14150b) {
                i = 2;
            }
        }
        i3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            if (cArr[i4] != C2774a.f14150b) {
                i3 |= C2774a.f14149a.indexOf(cArr[i4]) << ((3 - i4) * 6);
            }
        }
        this.f14152b = new int[i];
        while (i2 < i) {
            this.f14152b[i2] = (i3 >>> ((2 - i2) * 8)) & Util.MASK_8BIT;
            i2++;
        }
    }

    public void close() {
        this.f14151a.close();
    }

    public int read() {
        if (this.f14152b == null || this.f14153c == this.f14152b.length) {
            if (this.f14154d) {
                return -1;
            }
            m15830a();
            if (this.f14152b.length == 0) {
                this.f14152b = null;
                return -1;
            }
            this.f14153c = 0;
        }
        int[] iArr = this.f14152b;
        int i = this.f14153c;
        this.f14153c = i + 1;
        return iArr[i];
    }
}
