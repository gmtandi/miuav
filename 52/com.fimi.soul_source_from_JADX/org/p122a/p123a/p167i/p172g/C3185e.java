package org.p122a.p123a.p167i.p172g;

import com.fimi.soul.media.player.FimiMediaMeta;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.ConnectionClosedException;
import org.apache.http.io.SessionInputBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p173j.C3198a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.e */
public class C3185e extends InputStream {
    private static final int f15594a = 2048;
    private final long f15595b;
    private long f15596c;
    private boolean f15597d;
    private SessionInputBuffer f15598e;

    public C3185e(SessionInputBuffer sessionInputBuffer, long j) {
        this.f15596c = 0;
        this.f15597d = false;
        this.f15598e = null;
        this.f15598e = (SessionInputBuffer) C3234a.m17886a((Object) sessionInputBuffer, "Session input buffer");
        this.f15595b = C3234a.m17891b(j, "Content length");
    }

    public int available() {
        return this.f15598e instanceof C3198a ? Math.min(((C3198a) this.f15598e).m17751c(), (int) (this.f15595b - this.f15596c)) : 0;
    }

    public void close() {
        if (!this.f15597d) {
            try {
                if (this.f15596c < this.f15595b) {
                    do {
                    } while (read(new byte[f15594a]) >= 0);
                }
                this.f15597d = true;
            } catch (Throwable th) {
                this.f15597d = true;
            }
        }
    }

    public int read() {
        if (this.f15597d) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f15596c >= this.f15595b) {
            return -1;
        } else {
            int read = this.f15598e.read();
            if (read != -1) {
                this.f15596c++;
            } else if (this.f15596c < this.f15595b) {
                throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.f15595b + "; received: " + this.f15596c);
            }
            return read;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f15597d) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f15596c >= this.f15595b) {
            return -1;
        } else {
            if (this.f15596c + ((long) i2) > this.f15595b) {
                i2 = (int) (this.f15595b - this.f15596c);
            }
            int read = this.f15598e.read(bArr, i, i2);
            if (read != -1 || this.f15596c >= this.f15595b) {
                if (read > 0) {
                    this.f15596c += (long) read;
                }
                return read;
            }
            throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.f15595b + "; received: " + this.f15596c);
        }
    }

    public long skip(long j) {
        if (j <= 0) {
            return 0;
        }
        byte[] bArr = new byte[f15594a];
        long min = Math.min(j, this.f15595b - this.f15596c);
        long j2 = 0;
        while (min > 0) {
            int read = read(bArr, 0, (int) Math.min(FimiMediaMeta.AV_CH_TOP_CENTER, min));
            if (read == -1) {
                break;
            }
            j2 += (long) read;
            min -= (long) read;
        }
        return j2;
    }
}
