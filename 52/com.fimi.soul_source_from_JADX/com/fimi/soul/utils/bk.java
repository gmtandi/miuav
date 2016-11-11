package com.fimi.soul.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

class bk extends LruCache<String, Bitmap> {
    final /* synthetic */ bj f10091a;

    bk(bj bjVar, int i) {
        this.f10091a = bjVar;
        super(i);
    }

    protected int m12408a(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
        return m12408a((String) obj, (Bitmap) obj2);
    }
}
