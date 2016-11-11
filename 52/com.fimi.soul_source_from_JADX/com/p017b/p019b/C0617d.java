package com.p017b.p019b;

/* renamed from: com.b.b.d */
public abstract class C0617d<T, V> {
    private final String f3770a;
    private final Class<V> f3771b;

    public C0617d(Class<V> cls, String str) {
        this.f3770a = str;
        this.f3771b = cls;
    }

    public static <T, V> C0617d<T, V> m5394a(Class<T> cls, Class<V> cls2, String str) {
        return new C0646e(cls, cls2, str);
    }

    public abstract V m5395a(T t);

    public void m5396a(T t, V v) {
        throw new UnsupportedOperationException("Property " + m5398b() + " is read-only");
    }

    public boolean m5397a() {
        return false;
    }

    public String m5398b() {
        return this.f3770a;
    }

    public Class<V> m5399c() {
        return this.f3771b;
    }
}
