package org.p122a.p123a.p167i.p172g;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p173j.C3198a;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;

@C2913c
/* renamed from: org.a.a.i.g.r */
public class C3200r implements C3198a, SessionOutputBuffer {
    private static final byte[] f15631a;
    private final HttpTransportMetricsImpl f15632b;
    private final ByteArrayBuffer f15633c;
    private final int f15634d;
    private final CharsetEncoder f15635e;
    private OutputStream f15636f;
    private ByteBuffer f15637g;

    static {
        f15631a = new byte[]{(byte) 13, (byte) 10};
    }

    public C3200r(HttpTransportMetricsImpl httpTransportMetricsImpl, int i) {
        this(httpTransportMetricsImpl, i, i, null);
    }

    public C3200r(HttpTransportMetricsImpl httpTransportMetricsImpl, int i, int i2, CharsetEncoder charsetEncoder) {
        C3234a.m17883a(i, "Buffer size");
        C3234a.m17886a((Object) httpTransportMetricsImpl, "HTTP transport metrcis");
        this.f15632b = httpTransportMetricsImpl;
        this.f15633c = new ByteArrayBuffer(i);
        if (i2 < 0) {
            i2 = 0;
        }
        this.f15634d = i2;
        this.f15635e = charsetEncoder;
    }

    private void m17767a(CharBuffer charBuffer) {
        if (charBuffer.hasRemaining()) {
            if (this.f15637g == null) {
                this.f15637g = ByteBuffer.allocate(SmileConstants.MAX_SHARED_STRING_VALUES);
            }
            this.f15635e.reset();
            while (charBuffer.hasRemaining()) {
                m17768a(this.f15635e.encode(charBuffer, this.f15637g, true));
            }
            m17768a(this.f15635e.flush(this.f15637g));
            this.f15637g.clear();
        }
    }

    private void m17768a(CoderResult coderResult) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.f15637g.flip();
        while (this.f15637g.hasRemaining()) {
            write(this.f15637g.get());
        }
        this.f15637g.compact();
    }

    private void m17769a(byte[] bArr, int i, int i2) {
        C3235b.m17894a(this.f15636f, "Output stream");
        this.f15636f.write(bArr, i, i2);
    }

    private void m17770e() {
        if (this.f15636f != null) {
            this.f15636f.flush();
        }
    }

    private void m17771f() {
        int length = this.f15633c.length();
        if (length > 0) {
            m17769a(this.f15633c.buffer(), 0, length);
            this.f15633c.clear();
            this.f15632b.incrementBytesTransferred((long) length);
        }
    }

    public void m17772a(OutputStream outputStream) {
        this.f15636f = outputStream;
    }

    public boolean m17773a() {
        return this.f15636f != null;
    }

    public int m17774b() {
        return this.f15633c.capacity();
    }

    public int m17775c() {
        return this.f15633c.length();
    }

    public int m17776d() {
        return m17774b() - m17775c();
    }

    public void flush() {
        m17771f();
        m17770e();
    }

    public HttpTransportMetrics getMetrics() {
        return this.f15632b;
    }

    public void write(int i) {
        if (this.f15634d > 0) {
            if (this.f15633c.isFull()) {
                m17771f();
            }
            this.f15633c.append(i);
            return;
        }
        m17771f();
        this.f15636f.write(i);
    }

    public void write(byte[] bArr) {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i2 > this.f15634d || i2 > this.f15633c.capacity()) {
                m17771f();
                m17769a(bArr, i, i2);
                this.f15632b.incrementBytesTransferred((long) i2);
                return;
            }
            if (i2 > this.f15633c.capacity() - this.f15633c.length()) {
                m17771f();
            }
            this.f15633c.append(bArr, i, i2);
        }
    }

    public void writeLine(String str) {
        if (str != null) {
            if (str.length() > 0) {
                if (this.f15635e == null) {
                    for (int i = 0; i < str.length(); i++) {
                        write(str.charAt(i));
                    }
                } else {
                    m17767a(CharBuffer.wrap(str));
                }
            }
            write(f15631a);
        }
    }

    public void writeLine(CharArrayBuffer charArrayBuffer) {
        int i = 0;
        if (charArrayBuffer != null) {
            if (this.f15635e == null) {
                int length = charArrayBuffer.length();
                while (length > 0) {
                    int min = Math.min(this.f15633c.capacity() - this.f15633c.length(), length);
                    if (min > 0) {
                        this.f15633c.append(charArrayBuffer, i, min);
                    }
                    if (this.f15633c.isFull()) {
                        m17771f();
                    }
                    i += min;
                    length -= min;
                }
            } else {
                m17767a(CharBuffer.wrap(charArrayBuffer.buffer(), 0, charArrayBuffer.length()));
            }
            write(f15631a);
        }
    }
}
