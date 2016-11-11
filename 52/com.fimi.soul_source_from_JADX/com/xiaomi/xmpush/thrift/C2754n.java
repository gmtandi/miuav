package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.open.SocialConstants;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.util.BitSet;
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

/* renamed from: com.xiaomi.xmpush.thrift.n */
public class C2754n implements Serializable, Cloneable, C2478b<C2754n, C2753a> {
    public static final Map<C2753a, C3243b> f13836k;
    private static final C3262m f13837l;
    private static final C3255e f13838m;
    private static final C3255e f13839n;
    private static final C3255e f13840o;
    private static final C3255e f13841p;
    private static final C3255e f13842q;
    private static final C3255e f13843r;
    private static final C3255e f13844s;
    private static final C3255e f13845t;
    private static final C3255e f13846u;
    private static final C3255e f13847v;
    public String f13848a;
    public C2740g f13849b;
    public String f13850c;
    public String f13851d;
    public C2752m f13852e;
    public long f13853f;
    public String f13854g;
    public String f13855h;
    public String f13856i;
    public String f13857j;
    private BitSet f13858w;

    /* renamed from: com.xiaomi.xmpush.thrift.n.a */
    public enum C2753a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        REG_ID((short) 8, "regId"),
        REG_SECRET((short) 9, "regSecret"),
        PACKAGE_NAME((short) 10, C2537j.f12839W);
        
        private static final Map<String, C2753a> f13832k;
        private final short f13834l;
        private final String f13835m;

        static {
            f13832k = new HashMap();
            Iterator it = EnumSet.allOf(C2753a.class).iterator();
            while (it.hasNext()) {
                C2753a c2753a = (C2753a) it.next();
                f13832k.put(c2753a.m15636a(), c2753a);
            }
        }

        private C2753a(short s, String str) {
            this.f13834l = s;
            this.f13835m = str;
        }

        public String m15636a() {
            return this.f13835m;
        }
    }

    static {
        f13837l = new C3262m("XmPushActionRegistrationResult");
        f13838m = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13839n = new C3255e("target", (byte) 12, (short) 2);
        f13840o = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13841p = new C3255e("appId", (byte) 11, (short) 4);
        f13842q = new C3255e(SocialConstants.TYPE_REQUEST, (byte) 12, (short) 5);
        f13843r = new C3255e("errorCode", (byte) 10, (short) 6);
        f13844s = new C3255e("reason", (byte) 11, (short) 7);
        f13845t = new C3255e("regId", (byte) 11, (short) 8);
        f13846u = new C3255e("regSecret", (byte) 11, (short) 9);
        f13847v = new C3255e(C2537j.f12839W, (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2753a.class);
        enumMap.put(C2753a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2753a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2753a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2753a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2753a.REQUEST, new C3243b(SocialConstants.TYPE_REQUEST, (byte) 2, new C3247g((byte) 12, C2752m.class)));
        enumMap.put(C2753a.ERROR_CODE, new C3243b("errorCode", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2753a.REASON, new C3243b("reason", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2753a.REG_ID, new C3243b("regId", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2753a.REG_SECRET, new C3243b("regSecret", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2753a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        f13836k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2754n.class, f13836k);
    }

    public C2754n() {
        this.f13858w = new BitSet(1);
    }

    public void m15637a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15647f()) {
                    m15652k();
                    return;
                }
                throw new C3259i("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13848a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13849b = new C2740g();
                    this.f13849b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13850c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13851d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13852e = new C2752m();
                    this.f13852e.m15614a(c3249h);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13853f = c3249h.m17957u();
                    m15638a(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13854g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13855h = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13856i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13857j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15638a(boolean z) {
        this.f13858w.set(0, z);
    }

    public boolean m15639a() {
        return this.f13848a != null;
    }

    public boolean m15640a(C2754n c2754n) {
        if (c2754n == null) {
            return false;
        }
        boolean a = m15639a();
        boolean a2 = c2754n.m15639a();
        if ((a || a2) && (!a || !a2 || !this.f13848a.equals(c2754n.f13848a))) {
            return false;
        }
        a = m15643b();
        a2 = c2754n.m15643b();
        if ((a || a2) && (!a || !a2 || !this.f13849b.m15488a(c2754n.f13849b))) {
            return false;
        }
        a = m15644c();
        a2 = c2754n.m15644c();
        if ((a || a2) && (!a || !a2 || !this.f13850c.equals(c2754n.f13850c))) {
            return false;
        }
        a = m15645d();
        a2 = c2754n.m15645d();
        if ((a || a2) && (!a || !a2 || !this.f13851d.equals(c2754n.f13851d))) {
            return false;
        }
        a = m15646e();
        a2 = c2754n.m15646e();
        if (((a || a2) && (!a || !a2 || !this.f13852e.m15616a(c2754n.f13852e))) || this.f13853f != c2754n.f13853f) {
            return false;
        }
        a = m15648g();
        a2 = c2754n.m15648g();
        if ((a || a2) && (!a || !a2 || !this.f13854g.equals(c2754n.f13854g))) {
            return false;
        }
        a = m15649h();
        a2 = c2754n.m15649h();
        if ((a || a2) && (!a || !a2 || !this.f13855h.equals(c2754n.f13855h))) {
            return false;
        }
        a = m15650i();
        a2 = c2754n.m15650i();
        if ((a || a2) && (!a || !a2 || !this.f13856i.equals(c2754n.f13856i))) {
            return false;
        }
        a = m15651j();
        a2 = c2754n.m15651j();
        return !(a || a2) || (a && a2 && this.f13857j.equals(c2754n.f13857j));
    }

    public int m15641b(C2754n c2754n) {
        if (!getClass().equals(c2754n.getClass())) {
            return getClass().getName().compareTo(c2754n.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15639a()).compareTo(Boolean.valueOf(c2754n.m15639a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15639a()) {
            compareTo = C3269c.m18090a(this.f13848a, c2754n.f13848a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15643b()).compareTo(Boolean.valueOf(c2754n.m15643b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15643b()) {
            compareTo = C3269c.m18089a(this.f13849b, c2754n.f13849b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15644c()).compareTo(Boolean.valueOf(c2754n.m15644c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15644c()) {
            compareTo = C3269c.m18090a(this.f13850c, c2754n.f13850c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15645d()).compareTo(Boolean.valueOf(c2754n.m15645d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15645d()) {
            compareTo = C3269c.m18090a(this.f13851d, c2754n.f13851d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15646e()).compareTo(Boolean.valueOf(c2754n.m15646e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15646e()) {
            compareTo = C3269c.m18089a(this.f13852e, c2754n.f13852e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15647f()).compareTo(Boolean.valueOf(c2754n.m15647f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15647f()) {
            compareTo = C3269c.m18088a(this.f13853f, c2754n.f13853f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15648g()).compareTo(Boolean.valueOf(c2754n.m15648g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15648g()) {
            compareTo = C3269c.m18090a(this.f13854g, c2754n.f13854g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15649h()).compareTo(Boolean.valueOf(c2754n.m15649h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15649h()) {
            compareTo = C3269c.m18090a(this.f13855h, c2754n.f13855h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15650i()).compareTo(Boolean.valueOf(c2754n.m15650i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15650i()) {
            compareTo = C3269c.m18090a(this.f13856i, c2754n.f13856i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15651j()).compareTo(Boolean.valueOf(c2754n.m15651j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15651j()) {
            compareTo = C3269c.m18090a(this.f13857j, c2754n.f13857j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15642b(C3249h c3249h) {
        m15652k();
        c3249h.m17936a(f13837l);
        if (this.f13848a != null && m15639a()) {
            c3249h.m17932a(f13838m);
            c3249h.m17930a(this.f13848a);
            c3249h.m17938b();
        }
        if (this.f13849b != null && m15643b()) {
            c3249h.m17932a(f13839n);
            this.f13849b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13850c != null) {
            c3249h.m17932a(f13840o);
            c3249h.m17930a(this.f13850c);
            c3249h.m17938b();
        }
        if (this.f13851d != null) {
            c3249h.m17932a(f13841p);
            c3249h.m17930a(this.f13851d);
            c3249h.m17938b();
        }
        if (this.f13852e != null && m15646e()) {
            c3249h.m17932a(f13842q);
            this.f13852e.m15619b(c3249h);
            c3249h.m17938b();
        }
        c3249h.m17932a(f13843r);
        c3249h.m17929a(this.f13853f);
        c3249h.m17938b();
        if (this.f13854g != null && m15648g()) {
            c3249h.m17932a(f13844s);
            c3249h.m17930a(this.f13854g);
            c3249h.m17938b();
        }
        if (this.f13855h != null && m15649h()) {
            c3249h.m17932a(f13845t);
            c3249h.m17930a(this.f13855h);
            c3249h.m17938b();
        }
        if (this.f13856i != null && m15650i()) {
            c3249h.m17932a(f13846u);
            c3249h.m17930a(this.f13856i);
            c3249h.m17938b();
        }
        if (this.f13857j != null && m15651j()) {
            c3249h.m17932a(f13847v);
            c3249h.m17930a(this.f13857j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15643b() {
        return this.f13849b != null;
    }

    public boolean m15644c() {
        return this.f13850c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15641b((C2754n) obj);
    }

    public boolean m15645d() {
        return this.f13851d != null;
    }

    public boolean m15646e() {
        return this.f13852e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2754n)) ? m15640a((C2754n) obj) : false;
    }

    public boolean m15647f() {
        return this.f13858w.get(0);
    }

    public boolean m15648g() {
        return this.f13854g != null;
    }

    public boolean m15649h() {
        return this.f13855h != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15650i() {
        return this.f13856i != null;
    }

    public boolean m15651j() {
        return this.f13857j != null;
    }

    public void m15652k() {
        if (this.f13850c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13851d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionRegistrationResult(");
        Object obj2 = 1;
        if (m15639a()) {
            stringBuilder.append("debug:");
            if (this.f13848a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13848a);
            }
            obj2 = null;
        }
        if (m15643b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13849b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13849b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13850c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13850c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13851d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13851d);
        }
        if (m15646e()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f13852e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13852e);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.f13853f);
        if (m15648g()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.f13854g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13854g);
            }
        }
        if (m15649h()) {
            stringBuilder.append(", ");
            stringBuilder.append("regId:");
            if (this.f13855h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13855h);
            }
        }
        if (m15650i()) {
            stringBuilder.append(", ");
            stringBuilder.append("regSecret:");
            if (this.f13856i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13856i);
            }
        }
        if (m15651j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13857j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13857j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
