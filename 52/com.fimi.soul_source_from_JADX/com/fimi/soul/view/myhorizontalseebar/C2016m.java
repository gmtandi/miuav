package com.fimi.soul.view.myhorizontalseebar;

/* renamed from: com.fimi.soul.view.myhorizontalseebar.m */
class C2016m<T extends C2007c<T>> implements C2005b<T> {
    private final C2005b<T> f10915a;
    private final Object f10916b;

    public C2016m(C2005b<T> c2005b) {
        this.f10915a = c2005b;
        this.f10916b = this;
    }

    public C2016m(C2005b<T> c2005b, Object obj) {
        this.f10915a = c2005b;
        this.f10916b = obj;
    }

    public T m12914a() {
        T a;
        synchronized (this.f10916b) {
            a = this.f10915a.m12883a();
        }
        return a;
    }

    public void m12915a(T t) {
        synchronized (this.f10916b) {
            this.f10915a.m12884a(t);
        }
    }
}
