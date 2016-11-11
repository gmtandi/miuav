package com.fimi.soul.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.toolbox.C0596w;

@TargetApi(12)
public class bj implements C0596w {
    private static LruCache<String, Bitmap> f10089a;
    private static bj f10090b;

    public bj() {
        f10089a = new bk(this, ((int) Runtime.getRuntime().maxMemory()) / 8);
    }

    public static bj m12402a() {
        if (f10090b == null) {
            f10090b = new bj();
        }
        return f10090b;
    }

    public Bitmap m12403a(String str) {
        return (Bitmap) f10089a.get(str);
    }

    public void m12404a(String str, int i, int i2) {
        f10089a.remove(m12406b(str, i, i2));
    }

    public void m12405a(String str, Bitmap bitmap) {
        f10089a.put(str, bitmap);
    }

    public String m12406b(String str, int i, int i2) {
        return new StringBuilder(str.length() + 12).append("#W").append(i).append("#H").append(i2).append(str).toString();
    }

    public void m12407b() {
        f10089a.evictAll();
    }
}
