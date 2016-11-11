package com.p016a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/* renamed from: com.a.ba */
public final class ba {
    final /* synthetic */ az f605a;
    private final bd f606b;
    private final boolean[] f607c;
    private boolean f608d;
    private boolean f609e;

    private ba(az azVar, bd bdVar) {
        this.f605a = azVar;
        this.f606b = bdVar;
        this.f607c = bdVar.f619d ? null : new boolean[azVar.f596i];
    }

    public OutputStream m1144a(int i) {
        if (i < 0 || i >= this.f605a.f596i) {
            throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + this.f605a.f596i);
        }
        OutputStream d;
        synchronized (this.f605a) {
            File b;
            OutputStream fileOutputStream;
            if (this.f606b.f620e != this) {
                throw new IllegalStateException();
            }
            if (!this.f606b.f619d) {
                this.f607c[i] = true;
            }
            b = this.f606b.m1161b(i);
            try {
                fileOutputStream = new FileOutputStream(b);
            } catch (FileNotFoundException e) {
                this.f605a.f590c.mkdirs();
                try {
                    fileOutputStream = new FileOutputStream(b);
                } catch (FileNotFoundException e2) {
                    d = az.f588q;
                }
            }
            d = new bb(fileOutputStream, null);
        }
        return d;
    }

    public void m1145a() {
        if (this.f608d) {
            this.f605a.m1117a(this, false);
            this.f605a.m1140c(this.f606b.f617b);
        } else {
            this.f605a.m1117a(this, true);
        }
        this.f609e = true;
    }

    public void m1146b() {
        this.f605a.m1117a(this, false);
    }
}
