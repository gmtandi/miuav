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

/* renamed from: com.xiaomi.xmpush.thrift.s */
public class C2764s implements Serializable, Cloneable, C2478b<C2764s, C2763a> {
    public static final Map<C2763a, C3243b> f13995k;
    private static final C3262m f13996l;
    private static final C3255e f13997m;
    private static final C3255e f13998n;
    private static final C3255e f13999o;
    private static final C3255e f14000p;
    private static final C3255e f14001q;
    private static final C3255e f14002r;
    private static final C3255e f14003s;
    private static final C3255e f14004t;
    private static final C3255e f14005u;
    private static final C3255e f14006v;
    public String f14007a;
    public C2740g f14008b;
    public String f14009c;
    public String f14010d;
    public C2762r f14011e;
    public long f14012f;
    public String f14013g;
    public String f14014h;
    public String f14015i;
    public String f14016j;
    private BitSet f14017w;

    /* renamed from: com.xiaomi.xmpush.thrift.s.a */
    public enum C2763a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        TOPIC((short) 8, "topic"),
        PACKAGE_NAME((short) 9, C2537j.f12839W),
        CATEGORY((short) 10, "category");
        
        private static final Map<String, C2763a> f13991k;
        private final short f13993l;
        private final String f13994m;

        static {
            f13991k = new HashMap();
            Iterator it = EnumSet.allOf(C2763a.class).iterator();
            while (it.hasNext()) {
                C2763a c2763a = (C2763a) it.next();
                f13991k.put(c2763a.m15722a(), c2763a);
            }
        }

        private C2763a(short s, String str) {
            this.f13993l = s;
            this.f13994m = str;
        }

        public String m15722a() {
            return this.f13994m;
        }
    }

    static {
        f13996l = new C3262m("XmPushActionSubscriptionResult");
        f13997m = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13998n = new C3255e("target", (byte) 12, (short) 2);
        f13999o = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f14000p = new C3255e("appId", (byte) 11, (short) 4);
        f14001q = new C3255e(SocialConstants.TYPE_REQUEST, (byte) 12, (short) 5);
        f14002r = new C3255e("errorCode", (byte) 10, (short) 6);
        f14003s = new C3255e("reason", (byte) 11, (short) 7);
        f14004t = new C3255e("topic", (byte) 11, (short) 8);
        f14005u = new C3255e(C2537j.f12839W, (byte) 11, (short) 9);
        f14006v = new C3255e("category", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2763a.class);
        enumMap.put(C2763a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2763a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2763a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2763a.APP_ID, new C3243b("appId", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2763a.REQUEST, new C3243b(SocialConstants.TYPE_REQUEST, (byte) 2, new C3247g((byte) 12, C2762r.class)));
        enumMap.put(C2763a.ERROR_CODE, new C3243b("errorCode", (byte) 2, new C3241c((byte) 10)));
        enumMap.put(C2763a.REASON, new C3243b("reason", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2763a.TOPIC, new C3243b("topic", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2763a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2763a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13995k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2764s.class, f13995k);
    }

    public C2764s() {
        this.f14017w = new BitSet(1);
    }

    public void m15723a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15740m();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14007a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14008b = new C2740g();
                    this.f14008b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14009c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14010d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14011e = new C2762r();
                    this.f14011e.m15706a(c3249h);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14012f = c3249h.m17957u();
                    m15724a(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14013g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14014h = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14015i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14016j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15724a(boolean z) {
        this.f14017w.set(0, z);
    }

    public boolean m15725a() {
        return this.f14007a != null;
    }

    public boolean m15726a(C2764s c2764s) {
        if (c2764s == null) {
            return false;
        }
        boolean a = m15725a();
        boolean a2 = c2764s.m15725a();
        if ((a || a2) && (!a || !a2 || !this.f14007a.equals(c2764s.f14007a))) {
            return false;
        }
        a = m15729b();
        a2 = c2764s.m15729b();
        if ((a || a2) && (!a || !a2 || !this.f14008b.m15488a(c2764s.f14008b))) {
            return false;
        }
        a = m15730c();
        a2 = c2764s.m15730c();
        if ((a || a2) && (!a || !a2 || !this.f14009c.equals(c2764s.f14009c))) {
            return false;
        }
        a = m15731d();
        a2 = c2764s.m15731d();
        if ((a || a2) && (!a || !a2 || !this.f14010d.equals(c2764s.f14010d))) {
            return false;
        }
        a = m15732e();
        a2 = c2764s.m15732e();
        if ((a || a2) && (!a || !a2 || !this.f14011e.m15708a(c2764s.f14011e))) {
            return false;
        }
        a = m15733f();
        a2 = c2764s.m15733f();
        if ((a || a2) && (!a || !a2 || this.f14012f != c2764s.f14012f)) {
            return false;
        }
        a = m15734g();
        a2 = c2764s.m15734g();
        if ((a || a2) && (!a || !a2 || !this.f14013g.equals(c2764s.f14013g))) {
            return false;
        }
        a = m15736i();
        a2 = c2764s.m15736i();
        if ((a || a2) && (!a || !a2 || !this.f14014h.equals(c2764s.f14014h))) {
            return false;
        }
        a = m15737j();
        a2 = c2764s.m15737j();
        if ((a || a2) && (!a || !a2 || !this.f14015i.equals(c2764s.f14015i))) {
            return false;
        }
        a = m15739l();
        a2 = c2764s.m15739l();
        return !(a || a2) || (a && a2 && this.f14016j.equals(c2764s.f14016j));
    }

    public int m15727b(C2764s c2764s) {
        if (!getClass().equals(c2764s.getClass())) {
            return getClass().getName().compareTo(c2764s.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15725a()).compareTo(Boolean.valueOf(c2764s.m15725a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15725a()) {
            compareTo = C3269c.m18090a(this.f14007a, c2764s.f14007a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15729b()).compareTo(Boolean.valueOf(c2764s.m15729b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15729b()) {
            compareTo = C3269c.m18089a(this.f14008b, c2764s.f14008b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15730c()).compareTo(Boolean.valueOf(c2764s.m15730c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15730c()) {
            compareTo = C3269c.m18090a(this.f14009c, c2764s.f14009c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15731d()).compareTo(Boolean.valueOf(c2764s.m15731d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15731d()) {
            compareTo = C3269c.m18090a(this.f14010d, c2764s.f14010d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15732e()).compareTo(Boolean.valueOf(c2764s.m15732e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15732e()) {
            compareTo = C3269c.m18089a(this.f14011e, c2764s.f14011e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15733f()).compareTo(Boolean.valueOf(c2764s.m15733f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15733f()) {
            compareTo = C3269c.m18088a(this.f14012f, c2764s.f14012f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15734g()).compareTo(Boolean.valueOf(c2764s.m15734g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15734g()) {
            compareTo = C3269c.m18090a(this.f14013g, c2764s.f14013g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15736i()).compareTo(Boolean.valueOf(c2764s.m15736i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15736i()) {
            compareTo = C3269c.m18090a(this.f14014h, c2764s.f14014h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15737j()).compareTo(Boolean.valueOf(c2764s.m15737j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15737j()) {
            compareTo = C3269c.m18090a(this.f14015i, c2764s.f14015i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15739l()).compareTo(Boolean.valueOf(c2764s.m15739l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15739l()) {
            compareTo = C3269c.m18090a(this.f14016j, c2764s.f14016j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15728b(C3249h c3249h) {
        m15740m();
        c3249h.m17936a(f13996l);
        if (this.f14007a != null && m15725a()) {
            c3249h.m17932a(f13997m);
            c3249h.m17930a(this.f14007a);
            c3249h.m17938b();
        }
        if (this.f14008b != null && m15729b()) {
            c3249h.m17932a(f13998n);
            this.f14008b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f14009c != null) {
            c3249h.m17932a(f13999o);
            c3249h.m17930a(this.f14009c);
            c3249h.m17938b();
        }
        if (this.f14010d != null && m15731d()) {
            c3249h.m17932a(f14000p);
            c3249h.m17930a(this.f14010d);
            c3249h.m17938b();
        }
        if (this.f14011e != null && m15732e()) {
            c3249h.m17932a(f14001q);
            this.f14011e.m15711b(c3249h);
            c3249h.m17938b();
        }
        if (m15733f()) {
            c3249h.m17932a(f14002r);
            c3249h.m17929a(this.f14012f);
            c3249h.m17938b();
        }
        if (this.f14013g != null && m15734g()) {
            c3249h.m17932a(f14003s);
            c3249h.m17930a(this.f14013g);
            c3249h.m17938b();
        }
        if (this.f14014h != null && m15736i()) {
            c3249h.m17932a(f14004t);
            c3249h.m17930a(this.f14014h);
            c3249h.m17938b();
        }
        if (this.f14015i != null && m15737j()) {
            c3249h.m17932a(f14005u);
            c3249h.m17930a(this.f14015i);
            c3249h.m17938b();
        }
        if (this.f14016j != null && m15739l()) {
            c3249h.m17932a(f14006v);
            c3249h.m17930a(this.f14016j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15729b() {
        return this.f14008b != null;
    }

    public boolean m15730c() {
        return this.f14009c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15727b((C2764s) obj);
    }

    public boolean m15731d() {
        return this.f14010d != null;
    }

    public boolean m15732e() {
        return this.f14011e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2764s)) ? m15726a((C2764s) obj) : false;
    }

    public boolean m15733f() {
        return this.f14017w.get(0);
    }

    public boolean m15734g() {
        return this.f14013g != null;
    }

    public String m15735h() {
        return this.f14014h;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15736i() {
        return this.f14014h != null;
    }

    public boolean m15737j() {
        return this.f14015i != null;
    }

    public String m15738k() {
        return this.f14016j;
    }

    public boolean m15739l() {
        return this.f14016j != null;
    }

    public void m15740m() {
        if (this.f14009c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSubscriptionResult(");
        Object obj2 = 1;
        if (m15725a()) {
            stringBuilder.append("debug:");
            if (this.f14007a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14007a);
            }
            obj2 = null;
        }
        if (m15729b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f14008b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14008b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f14009c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14009c);
        }
        if (m15731d()) {
            stringBuilder.append(", ");
            stringBuilder.append("appId:");
            if (this.f14010d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14010d);
            }
        }
        if (m15732e()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f14011e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14011e);
            }
        }
        if (m15733f()) {
            stringBuilder.append(", ");
            stringBuilder.append("errorCode:");
            stringBuilder.append(this.f14012f);
        }
        if (m15734g()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.f14013g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14013g);
            }
        }
        if (m15736i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f14014h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14014h);
            }
        }
        if (m15737j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f14015i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14015i);
            }
        }
        if (m15739l()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f14016j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14016j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
