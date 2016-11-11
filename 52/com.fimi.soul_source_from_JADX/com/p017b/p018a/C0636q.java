package com.p017b.p018a;

import android.view.animation.Interpolator;

/* renamed from: com.b.a.q */
public abstract class C0636q implements Cloneable {
    float f3895a;
    Class f3896b;
    boolean f3897c;
    private Interpolator f3898d;

    public C0636q() {
        this.f3898d = null;
        this.f3897c = false;
    }

    public static C0636q m5642a(float f) {
        return new C0638s(f);
    }

    public static C0636q m5643a(float f, float f2) {
        return new C0637r(f, f2);
    }

    public static C0636q m5644a(float f, int i) {
        return new C0638s(f, i);
    }

    public static C0636q m5645a(float f, Object obj) {
        return new C0639t(f, obj);
    }

    public static C0636q m5646b(float f) {
        return new C0637r(f);
    }

    public static C0636q m5647c(float f) {
        return new C0639t(f, null);
    }

    public void m5648a(Interpolator interpolator) {
        this.f3898d = interpolator;
    }

    public abstract void m5649a(Object obj);

    public boolean m5650a() {
        return this.f3897c;
    }

    public abstract Object m5651b();

    public float m5652c() {
        return this.f3895a;
    }

    public /* synthetic */ Object clone() {
        return m5656f();
    }

    public Interpolator m5653d() {
        return this.f3898d;
    }

    public void m5654d(float f) {
        this.f3895a = f;
    }

    public Class m5655e() {
        return this.f3896b;
    }

    public abstract C0636q m5656f();
}
