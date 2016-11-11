package com.p054c.p055a.p056a.p061b.p062a;

import android.graphics.Bitmap;
import com.p054c.p055a.p056a.p061b.C0877b;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.c.a.a.b.a.g */
public class C0884g extends C0877b {
    private final Map<Bitmap, Integer> f4734a;

    public C0884g(int i) {
        super(i);
        this.f4734a = Collections.synchronizedMap(new HashMap());
    }

    public Bitmap m7126a(String str) {
        Bitmap a = super.m7078a(str);
        if (a != null) {
            Integer num = (Integer) this.f4734a.get(a);
            if (num != null) {
                this.f4734a.put(a, Integer.valueOf(num.intValue() + 1));
            }
        }
        return a;
    }

    protected Reference<Bitmap> m7127a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }

    public boolean m7128a(String str, Bitmap bitmap) {
        if (!super.m7084a(str, bitmap)) {
            return false;
        }
        this.f4734a.put(bitmap, Integer.valueOf(0));
        return true;
    }

    protected int m7129b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public Bitmap m7130b(String str) {
        Bitmap a = super.m7078a(str);
        if (a != null) {
            this.f4734a.remove(a);
        }
        return super.m7086b(str);
    }

    public void m7131b() {
        this.f4734a.clear();
        super.m7087b();
    }

    protected Bitmap m7132d() {
        Bitmap bitmap = null;
        Set<Entry> entrySet = this.f4734a.entrySet();
        synchronized (this.f4734a) {
            Integer num = null;
            for (Entry entry : entrySet) {
                Bitmap bitmap2;
                Integer num2;
                if (bitmap == null) {
                    bitmap2 = (Bitmap) entry.getKey();
                    num2 = (Integer) entry.getValue();
                } else {
                    Integer num3 = (Integer) entry.getValue();
                    if (num3.intValue() < num.intValue()) {
                        Bitmap bitmap3 = (Bitmap) entry.getKey();
                        num2 = num3;
                        bitmap2 = bitmap3;
                    } else {
                        bitmap2 = bitmap;
                        num2 = num;
                    }
                }
                bitmap = bitmap2;
                num = num2;
            }
        }
        this.f4734a.remove(bitmap);
        return bitmap;
    }
}
