package com.p017b.p018a;

/* renamed from: com.b.a.r */
class C0637r extends C0636q {
    float f3899d;

    C0637r(float f) {
        this.a = f;
        this.b = Float.TYPE;
    }

    C0637r(float f, float f2) {
        this.a = f;
        this.f3899d = f2;
        this.b = Float.TYPE;
        this.c = true;
    }

    public void m5657a(Object obj) {
        if (obj != null && obj.getClass() == Float.class) {
            this.f3899d = ((Float) obj).floatValue();
            this.c = true;
        }
    }

    public Object m5658b() {
        return Float.valueOf(this.f3899d);
    }

    public /* synthetic */ Object clone() {
        return m5661h();
    }

    public /* synthetic */ C0636q m5659f() {
        return m5661h();
    }

    public float m5660g() {
        return this.f3899d;
    }

    public C0637r m5661h() {
        C0637r c0637r = new C0637r(m5652c(), this.f3899d);
        c0637r.m5648a(m5653d());
        return c0637r;
    }
}
