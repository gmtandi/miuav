package com.p017b.p018a;

/* renamed from: com.b.a.t */
class C0639t extends C0636q {
    Object f3901d;

    C0639t(float f, Object obj) {
        this.a = f;
        this.f3901d = obj;
        this.c = obj != null;
        this.b = this.c ? obj.getClass() : Object.class;
    }

    public void m5667a(Object obj) {
        this.f3901d = obj;
        this.c = obj != null;
    }

    public Object m5668b() {
        return this.f3901d;
    }

    public /* synthetic */ Object clone() {
        return m5670g();
    }

    public /* synthetic */ C0636q m5669f() {
        return m5670g();
    }

    public C0639t m5670g() {
        C0639t c0639t = new C0639t(m5652c(), this.f3901d);
        c0639t.m5648a(m5653d());
        return c0639t;
    }
}
