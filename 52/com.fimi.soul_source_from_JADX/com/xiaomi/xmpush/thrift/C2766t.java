package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.t */
public class C2766t implements Serializable, Cloneable, C2478b<C2766t, C2765a> {
    public static final Map<C2765a, C3243b> f14032k;
    private static final C3262m f14033l;
    private static final C3255e f14034m;
    private static final C3255e f14035n;
    private static final C3255e f14036o;
    private static final C3255e f14037p;
    private static final C3255e f14038q;
    private static final C3255e f14039r;
    private static final C3255e f14040s;
    private static final C3255e f14041t;
    private static final C3255e f14042u;
    private static final C3255e f14043v;
    public String f14044a;
    public C2740g f14045b;
    public String f14046c;
    public String f14047d;
    public String f14048e;
    public String f14049f;
    public String f14050g;
    public String f14051h;
    public String f14052i;
    public String f14053j;

    /* renamed from: com.xiaomi.xmpush.thrift.t.a */
    public enum C2765a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        REG_ID((short) 5, "regId"),
        APP_VERSION((short) 6, "appVersion"),
        PACKAGE_NAME((short) 7, C2537j.f12839W),
        TOKEN((short) 8, "token"),
        DEVICE_ID((short) 9, C2537j.as),
        ALIAS_NAME((short) 10, "aliasName");
        
        private static final Map<String, C2765a> f14028k;
        private final short f14030l;
        private final String f14031m;

        static {
            f14028k = new HashMap();
            Iterator it = EnumSet.allOf(C2765a.class).iterator();
            while (it.hasNext()) {
                C2765a c2765a = (C2765a) it.next();
                f14028k.put(c2765a.m15741a(), c2765a);
            }
        }

        private C2765a(short s, String str) {
            this.f14030l = s;
            this.f14031m = str;
        }

        public String m15741a() {
            return this.f14031m;
        }
    }

    static {
        f14033l = new C3262m("XmPushActionUnRegistration");
        f14034m = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f14035n = new C3255e("target", (byte) 12, (short) 2);
        f14036o = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f14037p = new C3255e("appId", (byte) 11, (short) 4);
        f14038q = new C3255e("regId", (byte) 11, (short) 5);
        f14039r = new C3255e("appVersion", (byte) 11, (short) 6);
        f14040s = new C3255e(C2537j.f12839W, (byte) 11, (short) 7);
        f14041t = new C3255e("token", (byte) 11, (short) 8);
        f14042u = new C3255e(C2537j.as, (byte) 11, (short) 9);
        f14043v = new C3255e("aliasName", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2765a.class);
        enumMap.put(C2765a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2765a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2765a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2765a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2765a.REG_ID, new C3243b("regId", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2765a.APP_VERSION, new C3243b("appVersion", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2765a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2765a.TOKEN, new C3243b("token", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2765a.DEVICE_ID, new C3243b(C2537j.as, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2765a.ALIAS_NAME, new C3243b("aliasName", (byte) 2, new C3241c((byte) 11)));
        f14032k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2766t.class, f14032k);
    }

    public C2766t m15742a(String str) {
        this.f14046c = str;
        return this;
    }

    public void m15743a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15761k();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14044a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14045b = new C2740g();
                    this.f14045b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14046c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14047d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14048e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14049f = c3249h.m17959w();
                        break;
                    }
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14050g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14051h = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14052i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14053j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15744a() {
        return this.f14044a != null;
    }

    public boolean m15745a(C2766t c2766t) {
        if (c2766t == null) {
            return false;
        }
        boolean a = m15744a();
        boolean a2 = c2766t.m15744a();
        if ((a || a2) && (!a || !a2 || !this.f14044a.equals(c2766t.f14044a))) {
            return false;
        }
        a = m15749b();
        a2 = c2766t.m15749b();
        if ((a || a2) && (!a || !a2 || !this.f14045b.m15488a(c2766t.f14045b))) {
            return false;
        }
        a = m15751c();
        a2 = c2766t.m15751c();
        if ((a || a2) && (!a || !a2 || !this.f14046c.equals(c2766t.f14046c))) {
            return false;
        }
        a = m15753d();
        a2 = c2766t.m15753d();
        if ((a || a2) && (!a || !a2 || !this.f14047d.equals(c2766t.f14047d))) {
            return false;
        }
        a = m15755e();
        a2 = c2766t.m15755e();
        if ((a || a2) && (!a || !a2 || !this.f14048e.equals(c2766t.f14048e))) {
            return false;
        }
        a = m15756f();
        a2 = c2766t.m15756f();
        if ((a || a2) && (!a || !a2 || !this.f14049f.equals(c2766t.f14049f))) {
            return false;
        }
        a = m15757g();
        a2 = c2766t.m15757g();
        if ((a || a2) && (!a || !a2 || !this.f14050g.equals(c2766t.f14050g))) {
            return false;
        }
        a = m15758h();
        a2 = c2766t.m15758h();
        if ((a || a2) && (!a || !a2 || !this.f14051h.equals(c2766t.f14051h))) {
            return false;
        }
        a = m15759i();
        a2 = c2766t.m15759i();
        if ((a || a2) && (!a || !a2 || !this.f14052i.equals(c2766t.f14052i))) {
            return false;
        }
        a = m15760j();
        a2 = c2766t.m15760j();
        return !(a || a2) || (a && a2 && this.f14053j.equals(c2766t.f14053j));
    }

    public int m15746b(C2766t c2766t) {
        if (!getClass().equals(c2766t.getClass())) {
            return getClass().getName().compareTo(c2766t.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15744a()).compareTo(Boolean.valueOf(c2766t.m15744a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15744a()) {
            compareTo = C3269c.m18090a(this.f14044a, c2766t.f14044a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15749b()).compareTo(Boolean.valueOf(c2766t.m15749b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15749b()) {
            compareTo = C3269c.m18089a(this.f14045b, c2766t.f14045b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15751c()).compareTo(Boolean.valueOf(c2766t.m15751c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15751c()) {
            compareTo = C3269c.m18090a(this.f14046c, c2766t.f14046c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15753d()).compareTo(Boolean.valueOf(c2766t.m15753d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15753d()) {
            compareTo = C3269c.m18090a(this.f14047d, c2766t.f14047d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15755e()).compareTo(Boolean.valueOf(c2766t.m15755e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15755e()) {
            compareTo = C3269c.m18090a(this.f14048e, c2766t.f14048e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15756f()).compareTo(Boolean.valueOf(c2766t.m15756f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15756f()) {
            compareTo = C3269c.m18090a(this.f14049f, c2766t.f14049f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15757g()).compareTo(Boolean.valueOf(c2766t.m15757g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15757g()) {
            compareTo = C3269c.m18090a(this.f14050g, c2766t.f14050g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15758h()).compareTo(Boolean.valueOf(c2766t.m15758h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15758h()) {
            compareTo = C3269c.m18090a(this.f14051h, c2766t.f14051h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15759i()).compareTo(Boolean.valueOf(c2766t.m15759i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15759i()) {
            compareTo = C3269c.m18090a(this.f14052i, c2766t.f14052i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15760j()).compareTo(Boolean.valueOf(c2766t.m15760j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15760j()) {
            compareTo = C3269c.m18090a(this.f14053j, c2766t.f14053j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2766t m15747b(String str) {
        this.f14047d = str;
        return this;
    }

    public void m15748b(C3249h c3249h) {
        m15761k();
        c3249h.m17936a(f14033l);
        if (this.f14044a != null && m15744a()) {
            c3249h.m17932a(f14034m);
            c3249h.m17930a(this.f14044a);
            c3249h.m17938b();
        }
        if (this.f14045b != null && m15749b()) {
            c3249h.m17932a(f14035n);
            this.f14045b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f14046c != null) {
            c3249h.m17932a(f14036o);
            c3249h.m17930a(this.f14046c);
            c3249h.m17938b();
        }
        if (this.f14047d != null) {
            c3249h.m17932a(f14037p);
            c3249h.m17930a(this.f14047d);
            c3249h.m17938b();
        }
        if (this.f14048e != null && m15755e()) {
            c3249h.m17932a(f14038q);
            c3249h.m17930a(this.f14048e);
            c3249h.m17938b();
        }
        if (this.f14049f != null && m15756f()) {
            c3249h.m17932a(f14039r);
            c3249h.m17930a(this.f14049f);
            c3249h.m17938b();
        }
        if (this.f14050g != null && m15757g()) {
            c3249h.m17932a(f14040s);
            c3249h.m17930a(this.f14050g);
            c3249h.m17938b();
        }
        if (this.f14051h != null && m15758h()) {
            c3249h.m17932a(f14041t);
            c3249h.m17930a(this.f14051h);
            c3249h.m17938b();
        }
        if (this.f14052i != null && m15759i()) {
            c3249h.m17932a(f14042u);
            c3249h.m17930a(this.f14052i);
            c3249h.m17938b();
        }
        if (this.f14053j != null && m15760j()) {
            c3249h.m17932a(f14043v);
            c3249h.m17930a(this.f14053j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15749b() {
        return this.f14045b != null;
    }

    public C2766t m15750c(String str) {
        this.f14048e = str;
        return this;
    }

    public boolean m15751c() {
        return this.f14046c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15746b((C2766t) obj);
    }

    public C2766t m15752d(String str) {
        this.f14050g = str;
        return this;
    }

    public boolean m15753d() {
        return this.f14047d != null;
    }

    public C2766t m15754e(String str) {
        this.f14051h = str;
        return this;
    }

    public boolean m15755e() {
        return this.f14048e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2766t)) ? m15745a((C2766t) obj) : false;
    }

    public boolean m15756f() {
        return this.f14049f != null;
    }

    public boolean m15757g() {
        return this.f14050g != null;
    }

    public boolean m15758h() {
        return this.f14051h != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15759i() {
        return this.f14052i != null;
    }

    public boolean m15760j() {
        return this.f14053j != null;
    }

    public void m15761k() {
        if (this.f14046c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f14047d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnRegistration(");
        Object obj2 = 1;
        if (m15744a()) {
            stringBuilder.append("debug:");
            if (this.f14044a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14044a);
            }
            obj2 = null;
        }
        if (m15749b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f14045b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14045b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f14046c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14046c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f14047d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14047d);
        }
        if (m15755e()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.f14048e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14048e);
            }
        }
        if (m15756f()) {
            stringBuilder.append(", ");
            stringBuilder.append("appVersion:");
            if (this.f14049f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14049f);
            }
        }
        if (m15757g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f14050g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14050g);
            }
        }
        if (m15758h()) {
            stringBuilder.append(", ");
            stringBuilder.append("token:");
            if (this.f14051h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14051h);
            }
        }
        if (m15759i()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceId:");
            if (this.f14052i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14052i);
            }
        }
        if (m15760j()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.f14053j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14053j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
