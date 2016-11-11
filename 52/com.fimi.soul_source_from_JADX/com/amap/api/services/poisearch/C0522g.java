package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.C0449r;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.services.poisearch.g */
abstract class C0522g<T, V> extends C0449r<T, V> {
    public C0522g(Context context, T t) {
        super(context, t);
    }

    protected boolean m4973a(String str) {
        return str == null || str.equals(C2915a.f14760f) || str.equals("[]");
    }
}
