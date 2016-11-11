package org.p184b.p185a;

import org.p184b.C3275p;
import org.p184b.C3277m;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.l */
public class C3291l extends C3277m<Object> {
    private final Class<?> f15801a;
    private final Class<?> f15802b;

    public C3291l(Class<?> cls) {
        this.f15801a = cls;
        this.f15802b = C3291l.m18184c(cls);
    }

    @C3315n
    public static <T> C3275p<T> m18182a(Class<?> cls) {
        return new C3291l(cls);
    }

    @C3315n
    public static <T> C3275p<T> m18183b(Class<T> cls) {
        return new C3291l(cls);
    }

    private static Class<?> m18184c(Class<?> cls) {
        return Boolean.TYPE.equals(cls) ? Boolean.class : Byte.TYPE.equals(cls) ? Byte.class : Character.TYPE.equals(cls) ? Character.class : Double.TYPE.equals(cls) ? Double.class : Float.TYPE.equals(cls) ? Float.class : Integer.TYPE.equals(cls) ? Integer.class : Long.TYPE.equals(cls) ? Long.class : Short.TYPE.equals(cls) ? Short.class : cls;
    }

    public void m18185a(C3300k c3300k) {
        c3300k.m18222a("an instance of ").m18222a(this.f15801a.getName());
    }

    protected boolean m18186b(Object obj, C3300k c3300k) {
        if (obj == null) {
            c3300k.m18222a("null");
            return false;
        } else if (this.f15802b.isInstance(obj)) {
            return true;
        } else {
            c3300k.m18221a(obj).m18222a(" is a " + obj.getClass().getName());
            return false;
        }
    }
}
