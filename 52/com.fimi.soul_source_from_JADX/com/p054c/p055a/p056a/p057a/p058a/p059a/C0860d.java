package com.p054c.p055a.p056a.p057a.p058a.p059a;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* renamed from: com.c.a.a.a.a.a.d */
public final class C0860d {
    final /* synthetic */ C0857a f4661a;
    private final C0862f f4662b;
    private final boolean[] f4663c;
    private boolean f4664d;
    private boolean f4665e;

    private C0860d(C0857a c0857a, C0862f c0862f) {
        this.f4661a = c0857a;
        this.f4662b = c0862f;
        this.f4663c = c0862f.f4670d ? null : new boolean[c0857a.f4653t];
    }

    public InputStream m7001a(int i) {
        synchronized (this.f4661a) {
            if (this.f4662b.f4671e != this) {
                throw new IllegalStateException();
            } else if (this.f4662b.f4670d) {
                try {
                    InputStream fileInputStream = new FileInputStream(this.f4662b.m7019a(i));
                    return fileInputStream;
                } catch (FileNotFoundException e) {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public void m7002a() {
        if (this.f4664d) {
            this.f4661a.m6965a(this, false);
            this.f4661a.m6991c(this.f4662b.f4668b);
        } else {
            this.f4661a.m6965a(this, true);
        }
        this.f4665e = true;
    }

    public void m7003a(int i, String str) {
        Closeable outputStreamWriter;
        Throwable th;
        try {
            outputStreamWriter = new OutputStreamWriter(m7006c(i), C0868k.f4699b);
            try {
                outputStreamWriter.write(str);
                C0868k.m7050a(outputStreamWriter);
            } catch (Throwable th2) {
                th = th2;
                C0868k.m7050a(outputStreamWriter);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStreamWriter = null;
            C0868k.m7050a(outputStreamWriter);
            throw th;
        }
    }

    public String m7004b(int i) {
        InputStream a = m7001a(i);
        return a != null ? C0857a.m6968b(a) : null;
    }

    public void m7005b() {
        this.f4661a.m6965a(this, false);
    }

    public OutputStream m7006c(int i) {
        OutputStream i2;
        synchronized (this.f4661a) {
            OutputStream fileOutputStream;
            if (this.f4662b.f4671e != this) {
                throw new IllegalStateException();
            }
            if (!this.f4662b.f4670d) {
                this.f4663c[i] = true;
            }
            r1 = this.f4662b.m7021b(i);
            try {
                fileOutputStream = new FileOutputStream(r1);
            } catch (FileNotFoundException e) {
                this.f4661a.f4646m.mkdirs();
                try {
                    File b;
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e2) {
                    i2 = C0857a.f4632B;
                }
            }
            i2 = new C0861e(fileOutputStream, null);
        }
        return i2;
    }

    public void m7007c() {
        if (!this.f4665e) {
            try {
                m7005b();
            } catch (IOException e) {
            }
        }
    }
}
