package com.fimi.soul.module.setting.GimalCalibration;

import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.module.dronemanage.af;
import com.fimi.soul.module.dronemanage.ai;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.setting.GimalCalibration.d */
public class C1843d implements C1234i, ai {
    public static int f9110a = 0;
    public static final byte f9111b = (byte) 7;
    public static final byte f9112c = (byte) 8;
    public static final int f9113d = 2;
    private static final byte f9114h = (byte) 0;
    af f9115e;
    C1840f f9116f;
    C1433a f9117g;
    private final int f9118i;
    private final int f9119j;
    private final int f9120k;
    private final int f9121l;
    private final int f9122m;
    private final int f9123n;
    private boolean f9124o;

    static {
        f9110a = 100;
    }

    public C1843d(C1840f c1840f, C1433a c1433a) {
        this.f9115e = null;
        this.f9118i = 0;
        this.f9119j = 1;
        this.f9120k = f9113d;
        this.f9121l = 3;
        this.f9122m = 4;
        this.f9123n = 5;
        this.f9116f = c1840f;
        this.f9117g = c1433a;
    }

    private void m11613b(byte b) {
        String a = m11615a(b);
        if (this.f9116f != null) {
            this.f9124o = false;
            if (a != null) {
                this.f9116f.m11590a(a);
            } else {
                this.f9116f.m11590a(this.f9117g.f6507c.getString(C1205R.string.GC_califail));
            }
        }
    }

    private void m11614h() {
        byte b = this.f9117g.ai().m10263b();
        if (b > null) {
            m11613b(b);
            return;
        }
        int d = this.f9117g.ai().m10265d();
        this.f9116f.m11589a(d);
        if (d == f9110a) {
            this.f9124o = false;
            m11623f();
            this.f9116f.m11593c();
        }
    }

    public String m11615a(byte b) {
        for (int i = 0; i < 8; i++) {
            if (be.m12349a((int) b, i) > 0) {
                switch (i) {
                    case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                        return m11618b((int) C1205R.string.GC_hight_temperature);
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        return m11618b((int) C1205R.string.GC_low_temperature);
                    case f9113d /*2*/:
                        return m11618b((int) C1205R.string.GC_temperature_low_accuracy);
                    case Type.BYTE /*3*/:
                        return m11618b((int) C1205R.string.GC_horLine_uneven);
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        return m11618b((int) C1205R.string.GC_hight_Vibrator);
                    case Type.INT /*5*/:
                        return m11618b((int) C1205R.string.GC_wrong_angle);
                    default:
                        break;
                }
            }
        }
        return null;
    }

    public void m11616a() {
        C1465c c = m11620c();
        this.f9117g.m9569P().m9993a(c);
        if (this.f9115e == null) {
            this.f9115e = af.m11191a(this);
        }
        this.f9115e.m11192a(this.f9117g, c);
        this.f9115e.m11193b();
        this.f9124o = true;
    }

    public void m11617a(int i) {
    }

    public String m11618b(int i) {
        return this.f9117g.f6507c.getResources().getString(i);
    }

    public void m11619b() {
        if (this.f9115e != null) {
            this.f9116f.m11591b();
            this.f9115e.m11195d();
            this.f9115e.m11194c();
            this.f9115e = null;
        }
    }

    public C1465c m11620c() {
        C1465c c1465c = new C1465c();
        c1465c.f6999b = f9113d;
        c1465c.f7000c = Opcodes.ISHR;
        c1465c.f7001d.m9828b((byte) f9112c);
        c1465c.f7001d.m9828b((byte) 0);
        return c1465c;
    }

    public void m11621d() {
        C1664h.m10813a(this.f9117g).m10842r();
    }

    public void m11622e() {
        this.f9117g.m9590a((C1234i) this);
    }

    public void m11623f() {
        this.f9117g.m9594b((C1234i) this);
    }

    public void m11624g() {
        if (this.f9116f != null) {
            this.f9116f = null;
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1844e.f9125a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m11619b();
                m11614h();
            case f9113d /*2*/:
                if (this.f9116f != null && this.f9124o) {
                    this.f9116f.m11590a(m11618b((int) C1205R.string.GC_caliFail_discon));
                }
            case Type.BYTE /*3*/:
                if (this.f9116f != null && !this.f9124o) {
                    this.f9116f.m11594d();
                } else if (this.f9116f != null && !c1433a.m9570Q() && this.f9124o) {
                    this.f9116f.m11590a(m11618b((int) C1205R.string.GC_caliFail_discon_drone));
                } else if (this.f9116f != null && this.f9124o) {
                    if (c1433a.ab().m10193R()) {
                        this.f9116f.m11590a(m11618b((int) C1205R.string.GC_hight_temperature));
                    } else if (c1433a.ab().m10225u()) {
                        this.f9116f.m11590a(m11618b((int) C1205R.string.discongc));
                    }
                }
            default:
        }
    }
}
