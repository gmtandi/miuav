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

/* renamed from: com.c.a.a.b.a.d */
public class C0881d extends C0877b {
    private final Map<Bitmap, Integer> f4727a;

    public C0881d(int i) {
        super(i);
        this.f4727a = Collections.synchronizedMap(new HashMap());
    }

    protected Reference<Bitmap> m7108a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }

    public boolean m7109a(String str, Bitmap bitmap) {
        if (!super.m7084a(str, bitmap)) {
            return false;
        }
        this.f4727a.put(bitmap, Integer.valueOf(m7110b(bitmap)));
        return true;
    }

    protected int m7110b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public Bitmap m7111b(String str) {
        Bitmap a = super.m7078a(str);
        if (a != null) {
            this.f4727a.remove(a);
        }
        return super.m7086b(str);
    }

    public void m7112b() {
        this.f4727a.clear();
        super.m7087b();
    }

    protected Bitmap m7113d() {
        Bitmap bitmap = null;
        Set<Entry> entrySet = this.f4727a.entrySet();
        synchronized (this.f4727a) {
            Integer num = null;
            for (Entry entry : entrySet) {
                Bitmap bitmap2;
                Integer num2;
                if (bitmap == null) {
                    bitmap2 = (Bitmap) entry.getKey();
                    num2 = (Integer) entry.getValue();
                } else {
                    Integer num3 = (Integer) entry.getValue();
                    if (num3.intValue() > num.intValue()) {
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
        this.f4727a.remove(bitmap);
        return bitmap;
    }
}
