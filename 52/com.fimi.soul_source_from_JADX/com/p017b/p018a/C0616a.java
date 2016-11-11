package com.p017b.p018a;

import android.view.animation.Interpolator;
import java.util.ArrayList;

/* renamed from: com.b.a.a */
public abstract class C0616a implements Cloneable {
    ArrayList<C0620b> f3769a;

    public C0616a() {
        this.f3769a = null;
    }

    public void m5376a() {
    }

    public abstract void m5377a(long j);

    public abstract void m5378a(Interpolator interpolator);

    public void m5379a(C0620b c0620b) {
        if (this.f3769a == null) {
            this.f3769a = new ArrayList();
        }
        this.f3769a.add(c0620b);
    }

    public void m5380a(Object obj) {
    }

    public abstract C0616a m5381b(long j);

    public void m5382b() {
    }

    public void m5383b(C0620b c0620b) {
        if (this.f3769a != null) {
            this.f3769a.remove(c0620b);
            if (this.f3769a.size() == 0) {
                this.f3769a = null;
            }
        }
    }

    public void m5384c() {
    }

    public /* synthetic */ Object clone() {
        return m5391j();
    }

    public abstract long m5385d();

    public abstract long m5386e();

    public abstract boolean m5387f();

    public boolean m5388g() {
        return m5387f();
    }

    public ArrayList<C0620b> m5389h() {
        return this.f3769a;
    }

    public void m5390i() {
        if (this.f3769a != null) {
            this.f3769a.clear();
            this.f3769a = null;
        }
    }

    public C0616a m5391j() {
        try {
            C0616a c0616a = (C0616a) super.clone();
            if (this.f3769a != null) {
                ArrayList arrayList = this.f3769a;
                c0616a.f3769a = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    c0616a.f3769a.add(arrayList.get(i));
                }
            }
            return c0616a;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void m5392k() {
    }

    public void m5393l() {
    }
}
