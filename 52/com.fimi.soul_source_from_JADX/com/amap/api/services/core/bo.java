package com.amap.api.services.core;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class bo implements Closeable {
    private final InputStream f3115a;
    private final Charset f3116b;
    private byte[] f3117c;
    private int f3118d;
    private int f3119e;

    /* renamed from: com.amap.api.services.core.bo.1 */
    class C04671 extends ByteArrayOutputStream {
        final /* synthetic */ bo f3114a;

        C04671(bo boVar, int i) {
            this.f3114a = boVar;
            super(i);
        }

        public String toString() {
            int i = (this.count <= 0 || this.buf[this.count - 1] != 13) ? this.count : this.count - 1;
            try {
                return new String(this.buf, 0, i, this.f3114a.f3116b.name());
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
    }

    public bo(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(bp.f3120a)) {
            this.f3115a = inputStream;
            this.f3116b = charset;
            this.f3117c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public bo(InputStream inputStream, Charset charset) {
        this(inputStream, Opcodes.ACC_ANNOTATION, charset);
    }

    private void m4724b() {
        int read = this.f3115a.read(this.f3117c, 0, this.f3117c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f3118d = 0;
        this.f3119e = read;
    }

    public String m4725a() {
        String str;
        synchronized (this.f3115a) {
            if (this.f3117c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f3118d >= this.f3119e) {
                m4724b();
            }
            int i2 = this.f3118d;
            while (i2 != this.f3119e) {
                if (this.f3117c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f3118d || this.f3117c[i2 - 1] != 13) ? i2 : i2 - 1;
                    str = new String(this.f3117c, this.f3118d, i3 - this.f3118d, this.f3116b.name());
                    this.f3118d = i2 + 1;
                } else {
                    i2++;
                }
            }
            ByteArrayOutputStream c04671 = new C04671(this, (this.f3119e - this.f3118d) + 80);
            loop1:
            while (true) {
                c04671.write(this.f3117c, this.f3118d, this.f3119e - this.f3118d);
                this.f3119e = -1;
                m4724b();
                i = this.f3118d;
                while (i != this.f3119e) {
                    if (this.f3117c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f3118d) {
                c04671.write(this.f3117c, this.f3118d, i - this.f3118d);
            }
            this.f3118d = i + 1;
            str = c04671.toString();
        }
        return str;
    }

    public void close() {
        synchronized (this.f3115a) {
            if (this.f3117c != null) {
                this.f3117c = null;
                this.f3115a.close();
            }
        }
    }
}
