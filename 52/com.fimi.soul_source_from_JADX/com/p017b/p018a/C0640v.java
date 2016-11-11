package com.p017b.p018a;

import android.view.View;
import com.p017b.p019b.C0617d;
import com.p017b.p020c.p021a.C0647a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.b.a.v */
public final class C0640v extends as {
    private static final boolean f3902p = false;
    private static final Map<String, C0617d> f3903q;
    private Object f3904r;
    private String f3905s;
    private C0617d f3906t;

    static {
        f3903q = new HashMap();
        f3903q.put("alpha", C0641w.f3907a);
        f3903q.put("pivotX", C0641w.f3908b);
        f3903q.put("pivotY", C0641w.f3909c);
        f3903q.put("translationX", C0641w.f3910d);
        f3903q.put("translationY", C0641w.f3911e);
        f3903q.put("rotation", C0641w.f3912f);
        f3903q.put("rotationX", C0641w.f3913g);
        f3903q.put("rotationY", C0641w.f3914h);
        f3903q.put("scaleX", C0641w.f3915i);
        f3903q.put("scaleY", C0641w.f3916j);
        f3903q.put("scrollX", C0641w.f3917k);
        f3903q.put("scrollY", C0641w.f3918l);
        f3903q.put("x", C0641w.f3919m);
        f3903q.put("y", C0641w.f3920n);
    }

    private <T> C0640v(T t, C0617d<T, ?> c0617d) {
        this.f3904r = t;
        m5680a((C0617d) c0617d);
    }

    private C0640v(Object obj, String str) {
        this.f3904r = obj;
        m5682a(str);
    }

    public static <T, V> C0640v m5671a(T t, C0617d<T, V> c0617d, ar<V> arVar, V... vArr) {
        C0640v c0640v = new C0640v((Object) t, (C0617d) c0617d);
        c0640v.m5685a((Object[]) vArr);
        c0640v.m5523a((ar) arVar);
        return c0640v;
    }

    public static <T> C0640v m5672a(T t, C0617d<T, Float> c0617d, float... fArr) {
        C0640v c0640v = new C0640v((Object) t, (C0617d) c0617d);
        c0640v.m5683a(fArr);
        return c0640v;
    }

    public static <T> C0640v m5673a(T t, C0617d<T, Integer> c0617d, int... iArr) {
        C0640v c0640v = new C0640v((Object) t, (C0617d) c0617d);
        c0640v.m5684a(iArr);
        return c0640v;
    }

    public static C0640v m5674a(Object obj, String str, ar arVar, Object... objArr) {
        C0640v c0640v = new C0640v(obj, str);
        c0640v.m5685a(objArr);
        c0640v.m5523a(arVar);
        return c0640v;
    }

    public static C0640v m5675a(Object obj, String str, float... fArr) {
        C0640v c0640v = new C0640v(obj, str);
        c0640v.m5683a(fArr);
        return c0640v;
    }

    public static C0640v m5676a(Object obj, String str, int... iArr) {
        C0640v c0640v = new C0640v(obj, str);
        c0640v.m5684a(iArr);
        return c0640v;
    }

    public static C0640v m5677a(Object obj, al... alVarArr) {
        C0640v c0640v = new C0640v();
        c0640v.f3904r = obj;
        c0640v.m5533b(alVarArr);
        return c0640v;
    }

    public void m5678a() {
        super.m5518a();
    }

    void m5679a(float f) {
        super.m5519a(f);
        for (al d : this.k) {
            d.m5479d(this.f3904r);
        }
    }

    public void m5680a(C0617d c0617d) {
        if (this.k != null) {
            al alVar = this.k[0];
            String c = alVar.m5476c();
            alVar.m5466a(c0617d);
            this.l.remove(c);
            this.l.put(this.f3905s, alVar);
        }
        if (this.f3906t != null) {
            this.f3905s = c0617d.m5398b();
        }
        this.f3906t = c0617d;
        this.j = false;
    }

    public void m5681a(Object obj) {
        if (this.f3904r != obj) {
            Object obj2 = this.f3904r;
            this.f3904r = obj;
            if (obj2 == null || obj == null || obj2.getClass() != obj.getClass()) {
                this.j = false;
            }
        }
    }

    public void m5682a(String str) {
        if (this.k != null) {
            al alVar = this.k[0];
            String c = alVar.m5476c();
            alVar.m5469a(str);
            this.l.remove(c);
            this.l.put(str, alVar);
        }
        this.f3905s = str;
        this.j = false;
    }

    public void m5683a(float... fArr) {
        if (this.k != null && this.k.length != 0) {
            super.m5525a(fArr);
        } else if (this.f3906t != null) {
            m5533b(al.m5451a(this.f3906t, fArr));
        } else {
            m5533b(al.m5455a(this.f3905s, fArr));
        }
    }

    public void m5684a(int... iArr) {
        if (this.k != null && this.k.length != 0) {
            super.m5526a(iArr);
        } else if (this.f3906t != null) {
            m5533b(al.m5452a(this.f3906t, iArr));
        } else {
            m5533b(al.m5456a(this.f3905s, iArr));
        }
    }

    public void m5685a(Object... objArr) {
        if (this.k != null && this.k.length != 0) {
            super.m5527a(objArr);
        } else if (this.f3906t != null) {
            m5533b(al.m5450a(this.f3906t, (ar) null, objArr));
        } else {
            m5533b(al.m5454a(this.f3905s, (ar) null, objArr));
        }
    }

    public C0640v a_(long j) {
        super.m5537d(j);
        return this;
    }

    public /* synthetic */ C0616a m5686b(long j) {
        return a_(j);
    }

    public /* synthetic */ Object clone() {
        return m5694p();
    }

    public /* synthetic */ as m5687d(long j) {
        return a_(j);
    }

    public /* synthetic */ C0616a m5688j() {
        return m5694p();
    }

    public void m5689k() {
        m5692n();
        for (al b : this.k) {
            b.m5475b(this.f3904r);
        }
    }

    public void m5690l() {
        m5692n();
        for (al c : this.k) {
            c.m5477c(this.f3904r);
        }
    }

    public String m5691m() {
        return this.f3905s;
    }

    void m5692n() {
        if (!this.j) {
            if (this.f3906t == null && C0647a.f3927a && (this.f3904r instanceof View) && f3903q.containsKey(this.f3905s)) {
                m5680a((C0617d) f3903q.get(this.f3905s));
            }
            for (al a : this.k) {
                a.m5468a(this.f3904r);
            }
            super.m5543n();
        }
    }

    public Object m5693o() {
        return this.f3904r;
    }

    public C0640v m5694p() {
        return (C0640v) super.m5544q();
    }

    public /* synthetic */ as m5695q() {
        return m5694p();
    }

    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.f3904r;
        if (this.k != null) {
            for (al alVar : this.k) {
                str = str + "\n    " + alVar.toString();
            }
        }
        return str;
    }
}
