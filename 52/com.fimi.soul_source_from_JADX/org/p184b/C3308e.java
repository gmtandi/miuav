package org.p184b;

/* renamed from: org.b.e */
final class C3308e<T> extends C3306c<T> {
    private final T f15817b;
    private final C3300k f15818c;

    private C3308e(T t, C3300k c3300k) {
        super();
        this.f15817b = t;
        this.f15818c = c3300k;
    }

    public <U> C3306c<U> m18251a(C3310g<? super T, U> c3310g) {
        return c3310g.m18255a(this.f15817b, this.f15818c);
    }

    public boolean m18252a(C3275p<T> c3275p, String str) {
        if (c3275p.m18107a(this.f15817b)) {
            return true;
        }
        this.f15818c.m18222a(str);
        c3275p.m18106a(this.f15817b, this.f15818c);
        return false;
    }
}
