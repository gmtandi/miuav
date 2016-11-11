package com.p017b.p018a;

/* renamed from: com.b.a.s */
class C0638s extends C0636q {
    int f3900d;

    C0638s(float f) {
        this.a = f;
        this.b = Integer.TYPE;
    }

    C0638s(float f, int i) {
        this.a = f;
        this.f3900d = i;
        this.b = Integer.TYPE;
        this.c = true;
    }

    public void m5662a(Object obj) {
        if (obj != null && obj.getClass() == Integer.class) {
            this.f3900d = ((Integer) obj).intValue();
            this.c = true;
        }
    }

    public Object m5663b() {
        return Integer.valueOf(this.f3900d);
    }

    public /* synthetic */ Object clone() {
        return m5666h();
    }

    public /* synthetic */ C0636q m5664f() {
        return m5666h();
    }

    public int m5665g() {
        return this.f3900d;
    }

    public C0638s m5666h() {
        C0638s c0638s = new C0638s(m5652c(), this.f3900d);
        c0638s.m5648a(m5653d());
        return c0638s;
    }
}
