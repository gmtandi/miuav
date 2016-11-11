package org.p122a.p123a.p167i.p172g;

import com.tencent.mm.sdk.platformtools.Util;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3043f;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p173j.C3198a;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;

@C2913c
/* renamed from: org.a.a.i.g.q */
public class C3199q implements C3198a, SessionInputBuffer {
    private final HttpTransportMetricsImpl f15621a;
    private final byte[] f15622b;
    private final ByteArrayBuffer f15623c;
    private final int f15624d;
    private final C2998d f15625e;
    private final CharsetDecoder f15626f;
    private InputStream f15627g;
    private int f15628h;
    private int f15629i;
    private CharBuffer f15630j;

    public C3199q(HttpTransportMetricsImpl httpTransportMetricsImpl, int i) {
        this(httpTransportMetricsImpl, i, i, null, null);
    }

    public C3199q(HttpTransportMetricsImpl httpTransportMetricsImpl, int i, int i2, C2998d c2998d, CharsetDecoder charsetDecoder) {
        C3234a.m17886a((Object) httpTransportMetricsImpl, "HTTP transport metrcis");
        C3234a.m17883a(i, "Buffer size");
        this.f15621a = httpTransportMetricsImpl;
        this.f15622b = new byte[i];
        this.f15628h = 0;
        this.f15629i = 0;
        if (i2 < 0) {
            i2 = Opcodes.ACC_INTERFACE;
        }
        this.f15624d = i2;
        if (c2998d == null) {
            c2998d = C2998d.f14971a;
        }
        this.f15625e = c2998d;
        this.f15623c = new ByteArrayBuffer(i);
        this.f15626f = charsetDecoder;
    }

    private int m17753a(CoderResult coderResult, CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.f15630j.flip();
        int remaining = this.f15630j.remaining();
        while (this.f15630j.hasRemaining()) {
            charArrayBuffer.append(this.f15630j.get());
        }
        this.f15630j.compact();
        return remaining;
    }

    private int m17754a(CharArrayBuffer charArrayBuffer) {
        int length = this.f15623c.length();
        if (length > 0) {
            if (this.f15623c.byteAt(length - 1) == 10) {
                length--;
            }
            if (length > 0 && this.f15623c.byteAt(length - 1) == 13) {
                length--;
            }
        }
        if (this.f15626f == null) {
            charArrayBuffer.append(this.f15623c, 0, length);
        } else {
            length = m17756a(charArrayBuffer, ByteBuffer.wrap(this.f15623c.buffer(), 0, length));
        }
        this.f15623c.clear();
        return length;
    }

    private int m17755a(CharArrayBuffer charArrayBuffer, int i) {
        int i2 = this.f15628h;
        this.f15628h = i + 1;
        if (i > i2 && this.f15622b[i - 1] == 13) {
            i--;
        }
        int i3 = i - i2;
        if (this.f15626f != null) {
            return m17756a(charArrayBuffer, ByteBuffer.wrap(this.f15622b, i2, i3));
        }
        charArrayBuffer.append(this.f15622b, i2, i3);
        return i3;
    }

    private int m17756a(CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) {
        int i = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.f15630j == null) {
            this.f15630j = CharBuffer.allocate(SmileConstants.MAX_SHARED_STRING_VALUES);
        }
        this.f15626f.reset();
        while (byteBuffer.hasRemaining()) {
            i += m17753a(this.f15626f.decode(byteBuffer, this.f15630j, true), charArrayBuffer, byteBuffer);
        }
        i += m17753a(this.f15626f.flush(this.f15630j), charArrayBuffer, byteBuffer);
        this.f15630j.clear();
        return i;
    }

    private int m17757a(byte[] bArr, int i, int i2) {
        C3235b.m17894a(this.f15627g, "Input stream");
        return this.f15627g.read(bArr, i, i2);
    }

    private int m17758h() {
        for (int i = this.f15628h; i < this.f15629i; i++) {
            if (this.f15622b[i] == 10) {
                return i;
            }
        }
        return -1;
    }

    public void m17759a(InputStream inputStream) {
        this.f15627g = inputStream;
    }

    public boolean m17760a() {
        return this.f15627g != null;
    }

    public int m17761b() {
        return this.f15622b.length;
    }

    public int m17762c() {
        return this.f15629i - this.f15628h;
    }

    public int m17763d() {
        return m17761b() - m17762c();
    }

    public int m17764e() {
        int i;
        if (this.f15628h > 0) {
            i = this.f15629i - this.f15628h;
            if (i > 0) {
                System.arraycopy(this.f15622b, this.f15628h, this.f15622b, 0, i);
            }
            this.f15628h = 0;
            this.f15629i = i;
        }
        int i2 = this.f15629i;
        i = m17757a(this.f15622b, i2, this.f15622b.length - i2);
        if (i == -1) {
            return -1;
        }
        this.f15629i = i2 + i;
        this.f15621a.incrementBytesTransferred((long) i);
        return i;
    }

    public boolean m17765f() {
        return this.f15628h < this.f15629i;
    }

    public void m17766g() {
        this.f15628h = 0;
        this.f15629i = 0;
    }

    public HttpTransportMetrics getMetrics() {
        return this.f15621a;
    }

    public boolean isDataAvailable(int i) {
        return m17765f();
    }

    public int read() {
        while (!m17765f()) {
            if (m17764e() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.f15622b;
        int i = this.f15628h;
        this.f15628h = i + 1;
        return bArr[i] & Util.MASK_8BIT;
    }

    public int read(byte[] bArr) {
        return bArr == null ? 0 : read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return 0;
        }
        int min;
        if (m17765f()) {
            min = Math.min(i2, this.f15629i - this.f15628h);
            System.arraycopy(this.f15622b, this.f15628h, bArr, i, min);
            this.f15628h += min;
            return min;
        } else if (i2 > this.f15624d) {
            min = m17757a(bArr, i, i2);
            if (min <= 0) {
                return min;
            }
            this.f15621a.incrementBytesTransferred((long) min);
            return min;
        } else {
            while (!m17765f()) {
                if (m17764e() == -1) {
                    return -1;
                }
            }
            min = Math.min(i2, this.f15629i - this.f15628h);
            System.arraycopy(this.f15622b, this.f15628h, bArr, i, min);
            this.f15628h += min;
            return min;
        }
    }

    public int readLine(CharArrayBuffer charArrayBuffer) {
        C3234a.m17886a((Object) charArrayBuffer, "Char array buffer");
        Object obj = 1;
        int i = 0;
        while (obj != null) {
            int h = m17758h();
            if (h == -1) {
                if (m17765f()) {
                    this.f15623c.append(this.f15622b, this.f15628h, this.f15629i - this.f15628h);
                    this.f15628h = this.f15629i;
                }
                i = m17764e();
                if (i == -1) {
                    obj = null;
                }
            } else if (this.f15623c.isEmpty()) {
                return m17755a(charArrayBuffer, h);
            } else {
                this.f15623c.append(this.f15622b, this.f15628h, (h + 1) - this.f15628h);
                this.f15628h = h + 1;
                obj = null;
            }
            h = this.f15625e.m17027a();
            if (h > 0 && this.f15623c.length() >= h) {
                throw new C3043f("Maximum line length limit exceeded");
            }
        }
        return (i == -1 && this.f15623c.isEmpty()) ? -1 : m17754a(charArrayBuffer);
    }

    public String readLine() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        return readLine(charArrayBuffer) != -1 ? charArrayBuffer.toString() : null;
    }
}
