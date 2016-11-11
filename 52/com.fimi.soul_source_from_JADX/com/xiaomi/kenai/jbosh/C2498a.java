package com.xiaomi.kenai.jbosh;

/* renamed from: com.xiaomi.kenai.jbosh.a */
abstract class C2498a<T extends Comparable> implements Comparable {
    private final T f12636a;

    protected C2498a(T t) {
        this.f12636a = t;
    }

    public final T m14304a() {
        return this.f12636a;
    }

    public int compareTo(Object obj) {
        return obj == null ? 1 : this.f12636a.compareTo(obj);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C2498a)) {
            return false;
        }
        return this.f12636a.equals(((C2498a) obj).f12636a);
    }

    public int hashCode() {
        return this.f12636a.hashCode();
    }

    public String toString() {
        return this.f12636a.toString();
    }
}
