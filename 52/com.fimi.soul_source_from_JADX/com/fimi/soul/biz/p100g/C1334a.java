package com.fimi.soul.biz.p100g;

import java.util.Observable;

/* renamed from: com.fimi.soul.biz.g.a */
public class C1334a extends Observable {
    private boolean f5956a;

    public void m8961a(boolean z) {
        if ((!this.f5956a) == z) {
            setChanged();
            notifyObservers();
        }
        this.f5956a = z;
    }

    public boolean m8962a() {
        return this.f5956a;
    }
}
