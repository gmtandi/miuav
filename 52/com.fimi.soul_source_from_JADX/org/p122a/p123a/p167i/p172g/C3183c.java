package org.p122a.p123a.p167i.p172g;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.C3046g;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p173j.C3198a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.c */
public class C3183c extends InputStream {
    private static final int f15577a = 1;
    private static final int f15578b = 2;
    private static final int f15579c = 3;
    private static final int f15580d = 2048;
    private final SessionInputBuffer f15581e;
    private final CharArrayBuffer f15582f;
    private int f15583g;
    private int f15584h;
    private int f15585i;
    private boolean f15586j;
    private boolean f15587k;
    private Header[] f15588l;

    public C3183c(SessionInputBuffer sessionInputBuffer) {
        this.f15586j = false;
        this.f15587k = false;
        this.f15588l = new Header[0];
        this.f15581e = (SessionInputBuffer) C3234a.m17886a((Object) sessionInputBuffer, "Session input buffer");
        this.f15585i = 0;
        this.f15582f = new CharArrayBuffer(16);
        this.f15583g = f15577a;
    }

    private void m17731b() {
        this.f15584h = m17732c();
        if (this.f15584h < 0) {
            throw new MalformedChunkCodingException("Negative chunk size");
        }
        this.f15583g = f15578b;
        this.f15585i = 0;
        if (this.f15584h == 0) {
            this.f15586j = true;
            m17733d();
        }
    }

    private int m17732c() {
        int i = 0;
        switch (this.f15583g) {
            case f15579c /*3*/:
                this.f15582f.clear();
                if (this.f15581e.readLine(this.f15582f) != -1) {
                    if (this.f15582f.isEmpty()) {
                        this.f15583g = f15577a;
                    } else {
                        throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
                    }
                }
                break;
            case f15577a /*1*/:
                this.f15582f.clear();
                if (this.f15581e.readLine(this.f15582f) != -1) {
                    i = this.f15582f.indexOf(59);
                    if (i < 0) {
                        i = this.f15582f.length();
                    }
                    try {
                        i = Integer.parseInt(this.f15582f.substringTrimmed(0, i), 16);
                        break;
                    } catch (NumberFormatException e) {
                        throw new MalformedChunkCodingException("Bad chunk header");
                    }
                }
                break;
            default:
                throw new IllegalStateException("Inconsistent codec state");
        }
        return i;
    }

    private void m17733d() {
        try {
            this.f15588l = C3112a.m17565a(this.f15581e, -1, -1, null);
        } catch (Throwable e) {
            IOException malformedChunkCodingException = new MalformedChunkCodingException("Invalid footer: " + e.getMessage());
            malformedChunkCodingException.initCause(e);
            throw malformedChunkCodingException;
        }
    }

    public Header[] m17734a() {
        return (Header[]) this.f15588l.clone();
    }

    public int available() {
        return this.f15581e instanceof C3198a ? Math.min(((C3198a) this.f15581e).m17751c(), this.f15584h - this.f15585i) : 0;
    }

    public void close() {
        if (!this.f15587k) {
            try {
                if (!this.f15586j) {
                    do {
                    } while (read(new byte[f15580d]) >= 0);
                }
                this.f15586j = true;
                this.f15587k = true;
            } catch (Throwable th) {
                this.f15586j = true;
                this.f15587k = true;
            }
        }
    }

    public int read() {
        if (this.f15587k) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f15586j) {
            return -1;
        } else {
            if (this.f15583g != f15578b) {
                m17731b();
                if (this.f15586j) {
                    return -1;
                }
            }
            int read = this.f15581e.read();
            if (read != -1) {
                this.f15585i += f15577a;
                if (this.f15585i >= this.f15584h) {
                    this.f15583g = f15579c;
                }
            }
            return read;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f15587k) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.f15586j) {
            return -1;
        } else {
            if (this.f15583g != f15578b) {
                m17731b();
                if (this.f15586j) {
                    return -1;
                }
            }
            int read = this.f15581e.read(bArr, i, Math.min(i2, this.f15584h - this.f15585i));
            if (read != -1) {
                this.f15585i += read;
                if (this.f15585i >= this.f15584h) {
                    this.f15583g = f15579c;
                }
                return read;
            }
            this.f15586j = true;
            throw new C3046g("Truncated chunk ( expected size: " + this.f15584h + "; actual size: " + this.f15585i + ")");
        }
    }
}
