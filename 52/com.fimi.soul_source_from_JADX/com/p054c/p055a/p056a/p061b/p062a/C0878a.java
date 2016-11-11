package com.p054c.p055a.p056a.p061b.p062a;

import android.graphics.Bitmap;
import com.p054c.p055a.p056a.p061b.C0877b;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.c.a.a.b.a.a */
public class C0878a extends C0877b {
    private final List<Bitmap> f4721a;

    public C0878a(int i) {
        super(i);
        this.f4721a = Collections.synchronizedList(new LinkedList());
    }

    protected Reference<Bitmap> m7090a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }

    public boolean m7091a(String str, Bitmap bitmap) {
        if (!super.m7084a(str, bitmap)) {
            return false;
        }
        this.f4721a.add(bitmap);
        return true;
    }

    protected int m7092b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public Bitmap m7093b(String str) {
        Bitmap a = super.m7078a(str);
        if (a != null) {
            this.f4721a.remove(a);
        }
        return super.m7086b(str);
    }

    public void m7094b() {
        this.f4721a.clear();
        super.m7087b();
    }

    protected Bitmap m7095d() {
        return (Bitmap) this.f4721a.remove(0);
    }
}
