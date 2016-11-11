package com.amap.api.mapcore.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class db implements Closeable {
    private final InputStream f2406a;
    private final Charset f2407b;
    private byte[] f2408c;
    private int f2409d;
    private int f2410e;

    /* renamed from: com.amap.api.mapcore.util.db.1 */
    class C03761 extends ByteArrayOutputStream {
        final /* synthetic */ db f2405a;

        C03761(db dbVar, int i) {
            this.f2405a = dbVar;
            super(i);
        }

        public String toString() {
            int i = (this.count <= 0 || this.buf[this.count - 1] != 13) ? this.count : this.count - 1;
            try {
                return new String(this.buf, 0, i, this.f2405a.f2407b.name());
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
    }

    public db(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(dc.f2411a)) {
            this.f2406a = inputStream;
            this.f2407b = charset;
            this.f2408c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public db(InputStream inputStream, Charset charset) {
        this(inputStream, Opcodes.ACC_ANNOTATION, charset);
    }

    private void m3999b() {
        int read = this.f2406a.read(this.f2408c, 0, this.f2408c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f2409d = 0;
        this.f2410e = read;
    }

    public String m4000a() {
        String str;
        synchronized (this.f2406a) {
            if (this.f2408c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f2409d >= this.f2410e) {
                m3999b();
            }
            int i2 = this.f2409d;
            while (i2 != this.f2410e) {
                if (this.f2408c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f2409d || this.f2408c[i2 - 1] != 13) ? i2 : i2 - 1;
                    str = new String(this.f2408c, this.f2409d, i3 - this.f2409d, this.f2407b.name());
                    this.f2409d = i2 + 1;
                } else {
                    i2++;
                }
            }
            ByteArrayOutputStream c03761 = new C03761(this, (this.f2410e - this.f2409d) + 80);
            loop1:
            while (true) {
                c03761.write(this.f2408c, this.f2409d, this.f2410e - this.f2409d);
                this.f2410e = -1;
                m3999b();
                i = this.f2409d;
                while (i != this.f2410e) {
                    if (this.f2408c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f2409d) {
                c03761.write(this.f2408c, this.f2409d, i - this.f2409d);
            }
            this.f2409d = i + 1;
            str = c03761.toString();
        }
        return str;
    }

    public void close() {
        synchronized (this.f2406a) {
            if (this.f2408c != null) {
                this.f2408c = null;
                this.f2406a.close();
            }
        }
    }
}
