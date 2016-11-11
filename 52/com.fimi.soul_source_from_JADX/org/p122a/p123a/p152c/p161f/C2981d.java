package org.p122a.p123a.p152c.p161f;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/* renamed from: org.a.a.c.f.d */
final class C2981d extends ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> {
    C2981d() {
    }

    protected SoftReference<Map<String, SimpleDateFormat>> m16922a() {
        return new SoftReference(new HashMap());
    }

    protected /* synthetic */ Object initialValue() {
        return m16922a();
    }
}
