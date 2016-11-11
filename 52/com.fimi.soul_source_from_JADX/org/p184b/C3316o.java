package org.p184b;

import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p184b.p186b.C3303b;

/* renamed from: org.b.o */
public abstract class C3316o<T, U> extends C3281t<T> {
    private static final C3303b f15821a;
    private final C3275p<? super U> f15822b;
    private final String f15823c;
    private final String f15824d;

    static {
        f15821a = new C3303b("featureValueOf", 1, 0);
    }

    public C3316o(C3275p<? super U> c3275p, String str, String str2) {
        super(f15821a);
        this.f15822b = c3275p;
        this.f15823c = str;
        this.f15824d = str2;
    }

    public final void m18306a(C3300k c3300k) {
        c3300k.m18222a(this.f15823c).m18222a(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).m18225a(this.f15822b);
    }

    protected abstract U m18307b(T t);

    protected boolean m18308b(T t, C3300k c3300k) {
        Object b = m18307b(t);
        if (this.f15822b.m18107a(b)) {
            return true;
        }
        c3300k.m18222a(this.f15824d).m18222a(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        this.f15822b.m18106a(b, c3300k);
        return false;
    }
}
