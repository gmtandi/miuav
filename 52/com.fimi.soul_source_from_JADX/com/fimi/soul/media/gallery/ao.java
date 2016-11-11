package com.fimi.soul.media.gallery;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

class ao extends LruCache<String, Bitmap> {
    final /* synthetic */ an f7802a;

    ao(an anVar, int i) {
        this.f7802a = anVar;
        super(i);
    }

    @SuppressLint({"NewApi"})
    protected int m10770a(String str, Bitmap bitmap) {
        return bitmap.getByteCount();
    }

    protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
        return m10770a((String) obj, (Bitmap) obj2);
    }
}
