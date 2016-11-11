package com.p016a;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.a.bh */
public class bh implements Closeable {
    private final InputStream f623a;
    private final Charset f624b;
    private byte[] f625c;
    private int f626d;
    private int f627e;

    public bh(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(bj.f629a)) {
            this.f623a = inputStream;
            this.f624b = charset;
            this.f625c = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public bh(InputStream inputStream, Charset charset) {
        this(inputStream, Opcodes.ACC_ANNOTATION, charset);
    }

    private void m1165b() {
        int read = this.f623a.read(this.f625c, 0, this.f625c.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f626d = 0;
        this.f627e = read;
    }

    public String m1166a() {
        String str;
        synchronized (this.f623a) {
            if (this.f625c == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f626d >= this.f627e) {
                m1165b();
            }
            int i2 = this.f626d;
            while (i2 != this.f627e) {
                if (this.f625c[i2] == (byte) 10) {
                    int i3 = (i2 == this.f626d || this.f625c[i2 - 1] != 13) ? i2 : i2 - 1;
                    str = new String(this.f625c, this.f626d, i3 - this.f626d, this.f624b.name());
                    this.f626d = i2 + 1;
                } else {
                    i2++;
                }
            }
            ByteArrayOutputStream biVar = new bi(this, (this.f627e - this.f626d) + 80);
            loop1:
            while (true) {
                biVar.write(this.f625c, this.f626d, this.f627e - this.f626d);
                this.f627e = -1;
                m1165b();
                i = this.f626d;
                while (i != this.f627e) {
                    if (this.f625c[i] == (byte) 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f626d) {
                biVar.write(this.f625c, this.f626d, i - this.f626d);
            }
            this.f626d = i + 1;
            str = biVar.toString();
        }
        return str;
    }

    public void close() {
        synchronized (this.f623a) {
            if (this.f625c != null) {
                this.f625c = null;
                this.f623a.close();
            }
        }
    }
}
