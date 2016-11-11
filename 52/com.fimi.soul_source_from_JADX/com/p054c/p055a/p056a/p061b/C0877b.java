package com.p054c.p055a.p056a.p061b;

import android.graphics.Bitmap;
import com.p054c.p055a.p072c.C0958f;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.c.a.a.b.b */
public abstract class C0877b extends C0876a {
    private static final int f4716a = 16;
    private static final int f4717b = 16777216;
    private final int f4718c;
    private final AtomicInteger f4719d;
    private final List<Bitmap> f4720e;

    public C0877b(int i) {
        this.f4720e = Collections.synchronizedList(new LinkedList());
        this.f4718c = i;
        this.f4719d = new AtomicInteger();
        if (i > f4717b) {
            C0958f.m7561c("You set too large memory cache size (more than %1$d Mb)", Integer.valueOf(f4716a));
        }
    }

    public boolean m7084a(String str, Bitmap bitmap) {
        boolean z = false;
        int b = m7085b(bitmap);
        int c = m7088c();
        int i = this.f4719d.get();
        if (b < c) {
            int i2 = i;
            while (i2 + b > c) {
                Bitmap d = m7089d();
                if (this.f4720e.remove(d)) {
                    i2 = this.f4719d.addAndGet(-m7085b(d));
                }
            }
            this.f4720e.add(bitmap);
            this.f4719d.addAndGet(b);
            z = true;
        }
        super.m7081a(str, bitmap);
        return z;
    }

    protected abstract int m7085b(Bitmap bitmap);

    public Bitmap m7086b(String str) {
        Bitmap a = super.m7078a(str);
        if (a != null && this.f4720e.remove(a)) {
            this.f4719d.addAndGet(-m7085b(a));
        }
        return super.m7082b(str);
    }

    public void m7087b() {
        this.f4720e.clear();
        this.f4719d.set(0);
        super.m7083b();
    }

    protected int m7088c() {
        return this.f4718c;
    }

    protected abstract Bitmap m7089d();
}
