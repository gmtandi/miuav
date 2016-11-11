package com.p017b.p018a;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.p122a.p123a.C2915a;

/* renamed from: com.b.a.as */
public class as extends C0616a {
    private static long f3795I = 0;
    static final int f3796b = 0;
    static final int f3797c = 1;
    static final int f3798d = 0;
    static final int f3799e = 1;
    static final int f3800f = 2;
    public static final int f3801m = 1;
    public static final int f3802n = 2;
    public static final int f3803o = -1;
    private static final long f3804p = 10;
    private static ThreadLocal<ay> f3805q;
    private static final ThreadLocal<ArrayList<as>> f3806r;
    private static final ThreadLocal<ArrayList<as>> f3807s;
    private static final ThreadLocal<ArrayList<as>> f3808t;
    private static final ThreadLocal<ArrayList<as>> f3809u;
    private static final ThreadLocal<ArrayList<as>> f3810v;
    private static final Interpolator f3811w;
    private static final ar f3812x;
    private static final ar f3813y;
    private int f3814A;
    private float f3815B;
    private boolean f3816C;
    private long f3817D;
    private boolean f3818E;
    private boolean f3819F;
    private long f3820G;
    private long f3821H;
    private int f3822J;
    private int f3823K;
    private Interpolator f3824L;
    private ArrayList<az> f3825M;
    long f3826g;
    long f3827h;
    int f3828i;
    boolean f3829j;
    al[] f3830k;
    HashMap<String, al> f3831l;
    private boolean f3832z;

    static {
        f3805q = new ThreadLocal();
        f3806r = new at();
        f3807s = new au();
        f3808t = new av();
        f3809u = new aw();
        f3810v = new ax();
        f3811w = new AccelerateDecelerateInterpolator();
        f3812x = new C0634o();
        f3813y = new C0631m();
        f3795I = f3804p;
    }

    public as() {
        this.f3827h = -1;
        this.f3832z = false;
        this.f3814A = f3798d;
        this.f3815B = 0.0f;
        this.f3816C = false;
        this.f3828i = f3798d;
        this.f3818E = false;
        this.f3819F = false;
        this.f3829j = false;
        this.f3820G = 300;
        this.f3821H = 0;
        this.f3822J = f3798d;
        this.f3823K = f3801m;
        this.f3824L = f3811w;
        this.f3825M = null;
    }

    public static int m5494B() {
        return ((ArrayList) f3806r.get()).size();
    }

    public static void m5495C() {
        ((ArrayList) f3806r.get()).clear();
        ((ArrayList) f3807s.get()).clear();
        ((ArrayList) f3808t.get()).clear();
    }

    public static as m5503a(ar arVar, Object... objArr) {
        as asVar = new as();
        asVar.m5527a(objArr);
        asVar.m5523a(arVar);
        return asVar;
    }

    public static as m5504a(al... alVarArr) {
        as asVar = new as();
        asVar.m5533b(alVarArr);
        return asVar;
    }

    private void m5505a(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.f3832z = z;
        this.f3814A = f3798d;
        this.f3828i = f3798d;
        this.f3819F = true;
        this.f3816C = false;
        ((ArrayList) f3807s.get()).add(this);
        if (this.f3821H == 0) {
            m5539e(m5546s());
            this.f3828i = f3798d;
            this.f3818E = true;
            if (this.a != null) {
                ArrayList arrayList = (ArrayList) this.a.clone();
                int size = arrayList.size();
                for (int i = f3798d; i < size; i += f3801m) {
                    ((C0620b) arrayList.get(i)).m5564a(this);
                }
            }
        }
        ay ayVar = (ay) f3805q.get();
        if (ayVar == null) {
            ayVar = new ay();
            f3805q.set(ayVar);
        }
        ayVar.sendEmptyMessage(f3798d);
    }

    public static as m5508b(float... fArr) {
        as asVar = new as();
        asVar.m5525a(fArr);
        return asVar;
    }

    public static as m5509b(int... iArr) {
        as asVar = new as();
        asVar.m5526a(iArr);
        return asVar;
    }

    public static void m5512f(long j) {
        f3795I = j;
    }

    private boolean m5513g(long j) {
        if (this.f3816C) {
            long j2 = j - this.f3817D;
            if (j2 > this.f3821H) {
                this.f3826g = j - (j2 - this.f3821H);
                this.f3828i = f3801m;
                return true;
            }
        }
        this.f3816C = true;
        this.f3817D = j;
        return false;
    }

    private void m5514m() {
        ((ArrayList) f3806r.get()).remove(this);
        ((ArrayList) f3807s.get()).remove(this);
        ((ArrayList) f3808t.get()).remove(this);
        this.f3828i = f3798d;
        if (this.f3818E && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = f3798d; i < size; i += f3801m) {
                ((C0620b) arrayList.get(i)).m5565b(this);
            }
        }
        this.f3818E = false;
        this.f3819F = false;
    }

    private void m5515o() {
        m5543n();
        ((ArrayList) f3806r.get()).add(this);
        if (this.f3821H > 0 && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = f3798d; i < size; i += f3801m) {
                ((C0620b) arrayList.get(i)).m5564a(this);
            }
        }
    }

    public static long m5516t() {
        return f3795I;
    }

    public float m5517A() {
        return this.f3815B;
    }

    public void m5518a() {
        m5505a(false);
    }

    void m5519a(float f) {
        int i;
        float interpolation = this.f3824L.getInterpolation(f);
        this.f3815B = interpolation;
        int length = this.f3830k.length;
        for (i = f3798d; i < length; i += f3801m) {
            this.f3830k[i].m5464a(interpolation);
        }
        if (this.f3825M != null) {
            int size = this.f3825M.size();
            for (i = f3798d; i < size; i += f3801m) {
                ((az) this.f3825M.get(i)).onAnimationUpdate(this);
            }
        }
    }

    public void m5520a(int i) {
        this.f3822J = i;
    }

    public void m5521a(long j) {
        this.f3821H = j;
    }

    public void m5522a(Interpolator interpolator) {
        if (interpolator != null) {
            this.f3824L = interpolator;
        } else {
            this.f3824L = new LinearInterpolator();
        }
    }

    public void m5523a(ar arVar) {
        if (arVar != null && this.f3830k != null && this.f3830k.length > 0) {
            this.f3830k[f3798d].m5465a(arVar);
        }
    }

    public void m5524a(az azVar) {
        if (this.f3825M == null) {
            this.f3825M = new ArrayList();
        }
        this.f3825M.add(azVar);
    }

    public void m5525a(float... fArr) {
        if (fArr != null && fArr.length != 0) {
            if (this.f3830k == null || this.f3830k.length == 0) {
                al[] alVarArr = new al[f3801m];
                alVarArr[f3798d] = al.m5455a(C2915a.f14760f, fArr);
                m5533b(alVarArr);
            } else {
                this.f3830k[f3798d].m5470a(fArr);
            }
            this.f3829j = false;
        }
    }

    public void m5526a(int... iArr) {
        if (iArr != null && iArr.length != 0) {
            if (this.f3830k == null || this.f3830k.length == 0) {
                al[] alVarArr = new al[f3801m];
                alVarArr[f3798d] = al.m5456a(C2915a.f14760f, iArr);
                m5533b(alVarArr);
            } else {
                this.f3830k[f3798d].m5471a(iArr);
            }
            this.f3829j = false;
        }
    }

    public void m5527a(Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            if (this.f3830k == null || this.f3830k.length == 0) {
                al[] alVarArr = new al[f3801m];
                alVarArr[f3798d] = al.m5454a(C2915a.f14760f, (ar) null, objArr);
                m5533b(alVarArr);
            } else {
                this.f3830k[f3798d].m5473a(objArr);
            }
            this.f3829j = false;
        }
    }

    public /* synthetic */ C0616a m5528b(long j) {
        return m5537d(j);
    }

    public Object m5529b(String str) {
        al alVar = (al) this.f3831l.get(str);
        return alVar != null ? alVar.m5478d() : null;
    }

    public void m5530b() {
        if (this.f3828i != 0 || ((ArrayList) f3807s.get()).contains(this) || ((ArrayList) f3808t.get()).contains(this)) {
            if (this.f3818E && this.a != null) {
                Iterator it = ((ArrayList) this.a.clone()).iterator();
                while (it.hasNext()) {
                    ((C0620b) it.next()).m5566c(this);
                }
            }
            m5514m();
        }
    }

    public void m5531b(int i) {
        this.f3823K = i;
    }

    public void m5532b(az azVar) {
        if (this.f3825M != null) {
            this.f3825M.remove(azVar);
            if (this.f3825M.size() == 0) {
                this.f3825M = null;
            }
        }
    }

    public void m5533b(al... alVarArr) {
        int length = alVarArr.length;
        this.f3830k = alVarArr;
        this.f3831l = new HashMap(length);
        for (int i = f3798d; i < length; i += f3801m) {
            al alVar = alVarArr[i];
            this.f3831l.put(alVar.m5476c(), alVar);
        }
        this.f3829j = false;
    }

    public void m5534c() {
        if (!((ArrayList) f3806r.get()).contains(this) && !((ArrayList) f3807s.get()).contains(this)) {
            this.f3816C = false;
            m5515o();
        } else if (!this.f3829j) {
            m5543n();
        }
        if (this.f3822J <= 0 || (this.f3822J & f3801m) != f3801m) {
            m5519a((float) C2020f.f10933c);
        } else {
            m5519a(0.0f);
        }
        m5514m();
    }

    boolean m5535c(long j) {
        boolean z = false;
        if (this.f3828i == 0) {
            this.f3828i = f3801m;
            if (this.f3827h < 0) {
                this.f3826g = j;
            } else {
                this.f3826g = j - this.f3827h;
                this.f3827h = -1;
            }
        }
        switch (this.f3828i) {
            case f3801m /*1*/:
            case f3802n /*2*/:
                float f;
                float f2 = this.f3820G > 0 ? ((float) (j - this.f3826g)) / ((float) this.f3820G) : C2020f.f10933c;
                if (f2 < C2020f.f10933c) {
                    f = f2;
                } else if (this.f3814A < this.f3822J || this.f3822J == f3803o) {
                    if (this.a != null) {
                        int size = this.a.size();
                        for (int i = f3798d; i < size; i += f3801m) {
                            ((C0620b) this.a.get(i)).m5567d(this);
                        }
                    }
                    if (this.f3823K == f3802n) {
                        this.f3832z = !this.f3832z;
                    }
                    this.f3814A += (int) f2;
                    f = f2 % C2020f.f10933c;
                    this.f3826g += this.f3820G;
                } else {
                    f = Math.min(f2, C2020f.f10933c);
                    z = f3801m;
                }
                if (this.f3832z) {
                    f = C2020f.f10933c - f;
                }
                m5519a(f);
                break;
        }
        return z;
    }

    public /* synthetic */ Object clone() {
        return m5544q();
    }

    public long m5536d() {
        return this.f3821H;
    }

    public as m5537d(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.f3820G = j;
        return this;
    }

    public long m5538e() {
        return this.f3820G;
    }

    public void m5539e(long j) {
        m5543n();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.f3828i != f3801m) {
            this.f3827h = j;
            this.f3828i = f3802n;
        }
        this.f3826g = currentAnimationTimeMillis - j;
        m5535c(currentAnimationTimeMillis);
    }

    public boolean m5540f() {
        return this.f3828i == f3801m || this.f3818E;
    }

    public boolean m5541g() {
        return this.f3819F;
    }

    public /* synthetic */ C0616a m5542j() {
        return m5544q();
    }

    void m5543n() {
        if (!this.f3829j) {
            int length = this.f3830k.length;
            for (int i = f3798d; i < length; i += f3801m) {
                this.f3830k[i].m5474b();
            }
            this.f3829j = true;
        }
    }

    public as m5544q() {
        int i = f3798d;
        as asVar = (as) super.m5391j();
        if (this.f3825M != null) {
            ArrayList arrayList = this.f3825M;
            asVar.f3825M = new ArrayList();
            int size = arrayList.size();
            for (int i2 = f3798d; i2 < size; i2 += f3801m) {
                asVar.f3825M.add(arrayList.get(i2));
            }
        }
        asVar.f3827h = -1;
        asVar.f3832z = false;
        asVar.f3814A = f3798d;
        asVar.f3829j = false;
        asVar.f3828i = f3798d;
        asVar.f3816C = false;
        al[] alVarArr = this.f3830k;
        if (alVarArr != null) {
            int length = alVarArr.length;
            asVar.f3830k = new al[length];
            asVar.f3831l = new HashMap(length);
            while (i < length) {
                al a = alVarArr[i].m5463a();
                asVar.f3830k[i] = a;
                asVar.f3831l.put(a.m5476c(), a);
                i += f3801m;
            }
        }
        return asVar;
    }

    public al[] m5545r() {
        return this.f3830k;
    }

    public long m5546s() {
        return (!this.f3829j || this.f3828i == 0) ? 0 : AnimationUtils.currentAnimationTimeMillis() - this.f3826g;
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.f3830k != null) {
            for (int i = f3798d; i < this.f3830k.length; i += f3801m) {
                str = str + "\n    " + this.f3830k[i].toString();
            }
        }
        return str;
    }

    public Object m5547u() {
        return (this.f3830k == null || this.f3830k.length <= 0) ? null : this.f3830k[f3798d].m5478d();
    }

    public int m5548v() {
        return this.f3822J;
    }

    public int m5549w() {
        return this.f3823K;
    }

    public void m5550x() {
        if (this.f3825M != null) {
            this.f3825M.clear();
            this.f3825M = null;
        }
    }

    public Interpolator m5551y() {
        return this.f3824L;
    }

    public void m5552z() {
        this.f3832z = !this.f3832z;
        if (this.f3828i == f3801m) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f3826g = currentAnimationTimeMillis - (this.f3820G - (currentAnimationTimeMillis - this.f3826g));
            return;
        }
        m5505a(true);
    }
}
