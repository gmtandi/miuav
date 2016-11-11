package com.p054c.p055a.p056a.p061b.p062a;

import android.graphics.Bitmap;
import com.p054c.p055a.p056a.p061b.C0877b;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.c.a.a.b.a.c */
public class C0880c extends C0877b {
    private static final int f4724a = 10;
    private static final float f4725b = 1.1f;
    private final Map<String, Bitmap> f4726c;

    public C0880c(int i) {
        super(i);
        this.f4726c = Collections.synchronizedMap(new LinkedHashMap(f4724a, f4725b, true));
    }

    public Bitmap m7101a(String str) {
        this.f4726c.get(str);
        return super.m7078a(str);
    }

    protected Reference<Bitmap> m7102a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }

    public boolean m7103a(String str, Bitmap bitmap) {
        if (!super.m7084a(str, bitmap)) {
            return false;
        }
        this.f4726c.put(str, bitmap);
        return true;
    }

    protected int m7104b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public Bitmap m7105b(String str) {
        this.f4726c.remove(str);
        return super.m7086b(str);
    }

    public void m7106b() {
        this.f4726c.clear();
        super.m7087b();
    }

    protected Bitmap m7107d() {
        Bitmap bitmap = null;
        synchronized (this.f4726c) {
            Iterator it = this.f4726c.entrySet().iterator();
            if (it.hasNext()) {
                bitmap = (Bitmap) ((Entry) it.next()).getValue();
                it.remove();
            }
        }
        return bitmap;
    }
}
