package com.amap.api.mapcore.util;

public abstract class al {
    protected int f2125a;
    protected C0385g f2126b;

    public al(int i, C0385g c0385g) {
        this.f2125a = i;
        this.f2126b = c0385g;
    }

    public void m3450a() {
        af.m3415a("Wrong call delete()  State: " + m3452b() + "  " + getClass());
    }

    public boolean m3451a(al alVar) {
        return alVar.m3452b() == m3452b();
    }

    public int m3452b() {
        return this.f2125a;
    }

    public void m3453b(al alVar) {
        af.m3415a(m3452b() + " ==> " + alVar.m3452b() + "   " + getClass() + "==>" + alVar.getClass());
    }

    public abstract void m3454c();

    public void m3455d() {
        af.m3415a("Wrong call start()  State: " + m3452b() + "  " + getClass());
    }

    public void m3456e() {
        af.m3415a("Wrong call continueDownload()  State: " + m3452b() + "  " + getClass());
    }

    public void m3457f() {
        af.m3415a("Wrong call pause()  State: " + m3452b() + "  " + getClass());
    }

    public void m3458g() {
        af.m3415a("Wrong call fail()  State: " + m3452b() + "  " + getClass());
    }

    public void m3459h() {
        af.m3415a("Wrong call hasNew()  State: " + m3452b() + "  " + getClass());
    }

    public void m3460i() {
        af.m3415a("Wrong call complete()  State: " + m3452b() + "  " + getClass());
    }
}
