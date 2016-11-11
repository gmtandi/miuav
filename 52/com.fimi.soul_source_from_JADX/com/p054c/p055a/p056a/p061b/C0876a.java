package com.p054c.p055a.p056a.p061b;

import android.graphics.Bitmap;
import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* renamed from: com.c.a.a.b.a */
public abstract class C0876a implements C0875c {
    private final Map<String, Reference<Bitmap>> f4715a;

    public C0876a() {
        this.f4715a = Collections.synchronizedMap(new HashMap());
    }

    public Bitmap m7078a(String str) {
        Reference reference = (Reference) this.f4715a.get(str);
        return reference != null ? (Bitmap) reference.get() : null;
    }

    protected abstract Reference<Bitmap> m7079a(Bitmap bitmap);

    public Collection<String> m7080a() {
        Collection hashSet;
        synchronized (this.f4715a) {
            hashSet = new HashSet(this.f4715a.keySet());
        }
        return hashSet;
    }

    public boolean m7081a(String str, Bitmap bitmap) {
        this.f4715a.put(str, m7079a(bitmap));
        return true;
    }

    public Bitmap m7082b(String str) {
        Reference reference = (Reference) this.f4715a.remove(str);
        return reference == null ? null : (Bitmap) reference.get();
    }

    public void m7083b() {
        this.f4715a.clear();
    }
}
