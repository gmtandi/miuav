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

/* renamed from: com.xiaomi.xmpush.thrift.u */
public class C2768u implements Serializable, Cloneable, C2478b<C2768u, C2767a> {
    public static final Map<C2767a, C3243b> f14066i;
    private static final C3262m f14067j;
    private static final C3255e f14068k;
    private static final C3255e f14069l;
    private static final C3255e f14070m;
    private static final C3255e f14071n;
    private static final C3255e f14072o;
    private static final C3255e f14073p;
    private static final C3255e f14074q;
    private static final C3255e f14075r;
    public String f14076a;
    public C2740g f14077b;
    public String f14078c;
    public String f14079d;
    public C2766t f14080e;
    public long f14081f;
    public String f14082g;
    public String f14083h;
    private BitSet f14084s;

    /* renamed from: com.xiaomi.xmpush.thrift.u.a */
    public enum C2767a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        PACKAGE_NAME((short) 8, C2537j.f12839W);
        
        private static final Map<String, C2767a> f14062i;
        private final short f14064j;
        private final String f14065k;

        static {
            f14062i = new HashMap();
            Iterator it = EnumSet.allOf(C2767a.class).iterator();
            while (it.hasNext()) {
                C2767a c2767a = (C2767a) it.next();
                f14062i.put(c2767a.m15762a(), c2767a);
            }
        }

        private C2767a(short s, String str) {
            this.f14064j = s;
            this.f14065k = str;
        }

        public String m15762a() {
            return this.f14065k;
        }
    }

    static {
        f14067j = new C3262m("XmPushActionUnRegistrationResult");
        f14068k = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f14069l = new C3255e("target", (byte) 12, (short) 2);
        f14070m = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f14071n = new C3255e("appId", (byte) 11, (short) 4);
        f14072o = new C3255e(SocialConstants.TYPE_REQUEST, (byte) 12, (short) 5);
        f14073p = new C3255e("errorCode", (byte) 10, (short) 6);
        f14074q = new C3255e("reason", (byte) 11, (short) 7);
        f14075r = new C3255e(C2537j.f12839W, (byte) 11, (short) 8);
        Map enumMap = new EnumMap(C2767a.class);
        enumMap.put(C2767a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2767a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2767a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2767a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2767a.REQUEST, new C3243b(SocialConstants.TYPE_REQUEST, (byte) 2, new C3247g((byte) 12, C2766t.class)));
        enumMap.put(C2767a.ERROR_CODE, new C3243b("errorCode", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2767a.REASON, new C3243b("reason", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2767a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        f14066i = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2768u.class, f14066i);
    }

    public C2768u() {
        this.f14084s = new BitSet(1);
    }

    public void m15763a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15773f()) {
                    m15776i();
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
                        this.f14076a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14077b = new C2740g();
                    this.f14077b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14078c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14079d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14080e = new C2766t();
                    this.f14080e.m15743a(c3249h);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14081f = c3249h.m17957u();
                    m15764a(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14082g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14083h = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15764a(boolean z) {
        this.f14084s.set(0, z);
    }

    public boolean m15765a() {
        return this.f14076a != null;
    }

    public boolean m15766a(C2768u c2768u) {
        if (c2768u == null) {
            return false;
        }
        boolean a = m15765a();
        boolean a2 = c2768u.m15765a();
        if ((a || a2) && (!a || !a2 || !this.f14076a.equals(c2768u.f14076a))) {
            return false;
        }
        a = m15769b();
        a2 = c2768u.m15769b();
        if ((a || a2) && (!a || !a2 || !this.f14077b.m15488a(c2768u.f14077b))) {
            return false;
        }
        a = m15770c();
        a2 = c2768u.m15770c();
        if ((a || a2) && (!a || !a2 || !this.f14078c.equals(c2768u.f14078c))) {
            return false;
        }
        a = m15771d();
        a2 = c2768u.m15771d();
        if ((a || a2) && (!a || !a2 || !this.f14079d.equals(c2768u.f14079d))) {
            return false;
        }
        a = m15772e();
        a2 = c2768u.m15772e();
        if (((a || a2) && (!a || !a2 || !this.f14080e.m15745a(c2768u.f14080e))) || this.f14081f != c2768u.f14081f) {
            return false;
        }
        a = m15774g();
        a2 = c2768u.m15774g();
        if ((a || a2) && (!a || !a2 || !this.f14082g.equals(c2768u.f14082g))) {
            return false;
        }
        a = m15775h();
        a2 = c2768u.m15775h();
        return !(a || a2) || (a && a2 && this.f14083h.equals(c2768u.f14083h));
    }

    public int m15767b(C2768u c2768u) {
        if (!getClass().equals(c2768u.getClass())) {
            return getClass().getName().compareTo(c2768u.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15765a()).compareTo(Boolean.valueOf(c2768u.m15765a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15765a()) {
            compareTo = C3269c.m18090a(this.f14076a, c2768u.f14076a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15769b()).compareTo(Boolean.valueOf(c2768u.m15769b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15769b()) {
            compareTo = C3269c.m18089a(this.f14077b, c2768u.f14077b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15770c()).compareTo(Boolean.valueOf(c2768u.m15770c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15770c()) {
            compareTo = C3269c.m18090a(this.f14078c, c2768u.f14078c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15771d()).compareTo(Boolean.valueOf(c2768u.m15771d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15771d()) {
            compareTo = C3269c.m18090a(this.f14079d, c2768u.f14079d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15772e()).compareTo(Boolean.valueOf(c2768u.m15772e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15772e()) {
            compareTo = C3269c.m18089a(this.f14080e, c2768u.f14080e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15773f()).compareTo(Boolean.valueOf(c2768u.m15773f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15773f()) {
            compareTo = C3269c.m18088a(this.f14081f, c2768u.f14081f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15774g()).compareTo(Boolean.valueOf(c2768u.m15774g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15774g()) {
            compareTo = C3269c.m18090a(this.f14082g, c2768u.f14082g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15775h()).compareTo(Boolean.valueOf(c2768u.m15775h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15775h()) {
            compareTo = C3269c.m18090a(this.f14083h, c2768u.f14083h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15768b(C3249h c3249h) {
        m15776i();
        c3249h.m17936a(f14067j);
        if (this.f14076a != null && m15765a()) {
            c3249h.m17932a(f14068k);
            c3249h.m17930a(this.f14076a);
            c3249h.m17938b();
        }
        if (this.f14077b != null && m15769b()) {
            c3249h.m17932a(f14069l);
            this.f14077b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f14078c != null) {
            c3249h.m17932a(f14070m);
            c3249h.m17930a(this.f14078c);
            c3249h.m17938b();
        }
        if (this.f14079d != null) {
            c3249h.m17932a(f14071n);
            c3249h.m17930a(this.f14079d);
            c3249h.m17938b();
        }
        if (this.f14080e != null && m15772e()) {
            c3249h.m17932a(f14072o);
            this.f14080e.m15748b(c3249h);
            c3249h.m17938b();
        }
        c3249h.m17932a(f14073p);
        c3249h.m17929a(this.f14081f);
        c3249h.m17938b();
        if (this.f14082g != null && m15774g()) {
            c3249h.m17932a(f14074q);
            c3249h.m17930a(this.f14082g);
            c3249h.m17938b();
        }
        if (this.f14083h != null && m15775h()) {
            c3249h.m17932a(f14075r);
            c3249h.m17930a(this.f14083h);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15769b() {
        return this.f14077b != null;
    }

    public boolean m15770c() {
        return this.f14078c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15767b((C2768u) obj);
    }

    public boolean m15771d() {
        return this.f14079d != null;
    }

    public boolean m15772e() {
        return this.f14080e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2768u)) ? m15766a((C2768u) obj) : false;
    }

    public boolean m15773f() {
        return this.f14084s.get(0);
    }

    public boolean m15774g() {
        return this.f14082g != null;
    }

    public boolean m15775h() {
        return this.f14083h != null;
    }

    public int hashCode() {
        return 0;
    }

    public void m15776i() {
        if (this.f14078c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f14079d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnRegistrationResult(");
        Object obj2 = 1;
        if (m15765a()) {
            stringBuilder.append("debug:");
            if (this.f14076a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14076a);
            }
            obj2 = null;
        }
        if (m15769b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f14077b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14077b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f14078c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14078c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f14079d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14079d);
        }
        if (m15772e()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f14080e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14080e);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.f14081f);
        if (m15774g()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.f14082g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14082g);
            }
        }
        if (m15775h()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f14083h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14083h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
