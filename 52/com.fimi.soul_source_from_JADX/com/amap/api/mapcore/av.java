package com.amap.api.mapcore;

import java.util.concurrent.CopyOnWriteArrayList;

class av {
    AMapDelegateImp f1628a;
    private CopyOnWriteArrayList<C0325p> f1629b;
    private CopyOnWriteArrayList<au> f1630c;

    public av() {
        this.f1629b = new CopyOnWriteArrayList();
        this.f1630c = new CopyOnWriteArrayList();
    }

    public av(AMapDelegateImp aMapDelegateImp) {
        this.f1629b = new CopyOnWriteArrayList();
        this.f1630c = new CopyOnWriteArrayList();
        this.f1628a = aMapDelegateImp;
    }

    public au m2796a() {
        if (m2799b() == 0) {
            return null;
        }
        au auVar = (au) this.f1630c.get(0);
        this.f1630c.remove(auVar);
        return auVar;
    }

    public synchronized void m2797a(au auVar) {
        this.f1628a.m2518f(false);
        this.f1630c.add(auVar);
        this.f1628a.m2518f(false);
    }

    public void m2798a(C0325p c0325p) {
        this.f1628a.m2518f(false);
        this.f1629b.add(c0325p);
        this.f1628a.m2518f(false);
    }

    public synchronized int m2799b() {
        return this.f1630c.size();
    }

    public C0325p m2800c() {
        if (m2801d() == 0) {
            return null;
        }
        C0325p c0325p = (C0325p) this.f1629b.get(0);
        this.f1629b.remove(c0325p);
        this.f1628a.m2518f(false);
        return c0325p;
    }

    public int m2801d() {
        return this.f1629b.size();
    }
}
