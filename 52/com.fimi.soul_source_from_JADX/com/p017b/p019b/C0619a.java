package com.p017b.p019b;

/* renamed from: com.b.b.a */
public abstract class C0619a<T> extends C0617d<T, Float> {
    public C0619a(String str) {
        super(Float.class, str);
    }

    public abstract void m5407a(T t, float f);

    public final void m5408a(T t, Float f) {
        m5407a((Object) t, f.floatValue());
    }
}
