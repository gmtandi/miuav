package org.p004c.p207d;

import org.p004c.C3459c;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p198b.p200b.C3395b;
import org.p004c.p198b.p200b.C3396c;
import org.p184b.C3275p;
import org.p184b.C3311h;
import org.p184b.C3318s;

/* renamed from: org.c.d.d */
public class C3465d implements C3460r {
    private final C3467f f15981a;
    private String f15982b;

    private C3465d() {
        this.f15981a = new C3467f();
        this.f15982b = "Expected test to throw %s";
    }

    public static C3465d m18917a() {
        return new C3465d();
    }

    private void m18918a(Throwable th) {
        if (m18922d()) {
            C3459c.m18847a((Object) th, this.f15981a.m18940b());
            return;
        }
        throw th;
    }

    private boolean m18922d() {
        return this.f15981a.m18939a();
    }

    private void m18923e() {
        C3459c.m18848a(m18924f());
    }

    private String m18924f() {
        String b = C3318s.m18312b(this.f15981a.m18940b());
        return String.format(this.f15982b, new Object[]{b});
    }

    public C3465d m18925a(String str) {
        this.f15982b = str;
        return this;
    }

    public C3377k m18926a(C3377k c3377k, C3507d c3507d) {
        return new C3466e(this, c3377k);
    }

    public void m18927a(Class<? extends Throwable> cls) {
        m18928a(C3311h.m18288d((Class) cls));
    }

    public void m18928a(C3275p<?> c3275p) {
        this.f15981a.m18938a(c3275p);
    }

    @Deprecated
    public C3465d m18929b() {
        return this;
    }

    public void m18930b(String str) {
        m18931b(C3311h.m18281b(str));
    }

    public void m18931b(C3275p<String> c3275p) {
        m18928a(C3396c.m18649a((C3275p) c3275p));
    }

    @Deprecated
    public C3465d m18932c() {
        return this;
    }

    public void m18933c(C3275p<? extends Throwable> c3275p) {
        m18928a(C3395b.m18643a((C3275p) c3275p));
    }
}
