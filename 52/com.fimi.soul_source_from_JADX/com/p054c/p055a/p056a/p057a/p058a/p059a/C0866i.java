package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.c.a.a.a.a.a.i */
class C0866i implements Closeable {
    private static final byte f4690a = (byte) 13;
    private static final byte f4691b = (byte) 10;
    private final InputStream f4692c;
    private final Charset f4693d;
    private byte[] f4694e;
    private int f4695f;
    private int f4696g;

    public C0866i(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (charset.equals(C0868k.f4698a)) {
            this.f4692c = inputStream;
            this.f4693d = charset;
            this.f4694e = new byte[i];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public C0866i(InputStream inputStream, Charset charset) {
        this(inputStream, Opcodes.ACC_ANNOTATION, charset);
    }

    private void m7047b() {
        int read = this.f4692c.read(this.f4694e, 0, this.f4694e.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f4695f = 0;
        this.f4696g = read;
    }

    public String m7048a() {
        String str;
        synchronized (this.f4692c) {
            if (this.f4694e == null) {
                throw new IOException("LineReader is closed");
            }
            int i;
            if (this.f4695f >= this.f4696g) {
                m7047b();
            }
            int i2 = this.f4695f;
            while (i2 != this.f4696g) {
                if (this.f4694e[i2] == f4691b) {
                    int i3 = (i2 == this.f4695f || this.f4694e[i2 - 1] != 13) ? i2 : i2 - 1;
                    str = new String(this.f4694e, this.f4695f, i3 - this.f4695f, this.f4693d.name());
                    this.f4695f = i2 + 1;
                } else {
                    i2++;
                }
            }
            ByteArrayOutputStream c0867j = new C0867j(this, (this.f4696g - this.f4695f) + 80);
            loop1:
            while (true) {
                c0867j.write(this.f4694e, this.f4695f, this.f4696g - this.f4695f);
                this.f4696g = -1;
                m7047b();
                i = this.f4695f;
                while (i != this.f4696g) {
                    if (this.f4694e[i] == f4691b) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f4695f) {
                c0867j.write(this.f4694e, this.f4695f, i - this.f4695f);
            }
            this.f4695f = i + 1;
            str = c0867j.toString();
        }
        return str;
    }

    public void close() {
        synchronized (this.f4692c) {
            if (this.f4694e != null) {
                this.f4694e = null;
                this.f4692c.close();
            }
        }
    }
}
