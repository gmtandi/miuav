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
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3245e;
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3257g;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.l */
public class C2750l implements Serializable, Cloneable, C2478b<C2750l, C2749a> {
    public static final Map<C2749a, C3243b> f13763k;
    private static final C3262m f13764l;
    private static final C3255e f13765m;
    private static final C3255e f13766n;
    private static final C3255e f13767o;
    private static final C3255e f13768p;
    private static final C3255e f13769q;
    private static final C3255e f13770r;
    private static final C3255e f13771s;
    private static final C3255e f13772t;
    private static final C3255e f13773u;
    private static final C3255e f13774v;
    public String f13775a;
    public C2740g f13776b;
    public String f13777c;
    public String f13778d;
    public String f13779e;
    public boolean f13780f;
    public String f13781g;
    public Map<String, String> f13782h;
    public String f13783i;
    public String f13784j;
    private BitSet f13785w;

    /* renamed from: com.xiaomi.xmpush.thrift.l.a */
    public enum C2749a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        TYPE((short) 5, SocialConstants.PARAM_TYPE),
        REQUIRE_ACK((short) 6, "requireAck"),
        PAYLOAD((short) 7, "payload"),
        EXTRA((short) 8, "extra"),
        PACKAGE_NAME((short) 9, C2537j.f12839W),
        CATEGORY((short) 10, "category");
        
        private static final Map<String, C2749a> f13759k;
        private final short f13761l;
        private final String f13762m;

        static {
            f13759k = new HashMap();
            Iterator it = EnumSet.allOf(C2749a.class).iterator();
            while (it.hasNext()) {
                C2749a c2749a = (C2749a) it.next();
                f13759k.put(c2749a.m15590a(), c2749a);
            }
        }

        private C2749a(short s, String str) {
            this.f13761l = s;
            this.f13762m = str;
        }

        public String m15590a() {
            return this.f13762m;
        }
    }

    static {
        f13764l = new C3262m("XmPushActionNotification");
        f13765m = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13766n = new C3255e("target", (byte) 12, (short) 2);
        f13767o = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13768p = new C3255e("appId", (byte) 11, (short) 4);
        f13769q = new C3255e(SocialConstants.PARAM_TYPE, (byte) 11, (short) 5);
        f13770r = new C3255e("requireAck", (byte) 2, (short) 6);
        f13771s = new C3255e("payload", (byte) 11, (short) 7);
        f13772t = new C3255e("extra", (byte) 13, (short) 8);
        f13773u = new C3255e(C2537j.f12839W, (byte) 11, (short) 9);
        f13774v = new C3255e("category", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2749a.class);
        enumMap.put(C2749a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2749a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2749a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2749a.APP_ID, new C3243b("appId", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2749a.TYPE, new C3243b(SocialConstants.PARAM_TYPE, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2749a.REQUIRE_ACK, new C3243b("requireAck", (byte) 1, new C3241c((byte) 2)));
        enumMap.put(C2749a.PAYLOAD, new C3243b("payload", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2749a.EXTRA, new C3243b("extra", (byte) 2, new C3245e((byte) 13, new C3241c((byte) 11), new C3241c((byte) 11))));
        enumMap.put(C2749a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2749a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13763k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2750l.class, f13763k);
    }

    public C2750l() {
        this.f13785w = new BitSet(1);
        this.f13780f = true;
    }

    public C2750l m15591a(String str) {
        this.f13777c = str;
        return this;
    }

    public C2750l m15592a(boolean z) {
        this.f13780f = z;
        m15599b(true);
        return this;
    }

    public void m15593a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15605f()) {
                    m15611l();
                    return;
                }
                throw new C3259i("Required field 'requireAck' was not found in serialized data! Struct: " + toString());
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13775a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13776b = new C2740g();
                    this.f13776b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13777c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13778d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13779e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 2) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13780f = c3249h.m17953q();
                    m15599b(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13781g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != 13) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3257g k = c3249h.m17947k();
                    this.f13782h = new HashMap(k.f15762c * 2);
                    for (int i2 = 0; i2 < k.f15762c; i2++) {
                        this.f13782h.put(c3249h.m17959w(), c3249h.m17959w());
                    }
                    c3249h.m17948l();
                    break;
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13783i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13784j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15594a() {
        return this.f13775a != null;
    }

    public boolean m15595a(C2750l c2750l) {
        if (c2750l == null) {
            return false;
        }
        boolean a = m15594a();
        boolean a2 = c2750l.m15594a();
        if ((a || a2) && (!a || !a2 || !this.f13775a.equals(c2750l.f13775a))) {
            return false;
        }
        a = m15600b();
        a2 = c2750l.m15600b();
        if ((a || a2) && (!a || !a2 || !this.f13776b.m15488a(c2750l.f13776b))) {
            return false;
        }
        a = m15602c();
        a2 = c2750l.m15602c();
        if ((a || a2) && (!a || !a2 || !this.f13777c.equals(c2750l.f13777c))) {
            return false;
        }
        a = m15603d();
        a2 = c2750l.m15603d();
        if ((a || a2) && (!a || !a2 || !this.f13778d.equals(c2750l.f13778d))) {
            return false;
        }
        a = m15604e();
        a2 = c2750l.m15604e();
        if (((a || a2) && (!a || !a2 || !this.f13779e.equals(c2750l.f13779e))) || this.f13780f != c2750l.f13780f) {
            return false;
        }
        a = m15606g();
        a2 = c2750l.m15606g();
        if ((a || a2) && (!a || !a2 || !this.f13781g.equals(c2750l.f13781g))) {
            return false;
        }
        a = m15608i();
        a2 = c2750l.m15608i();
        if ((a || a2) && (!a || !a2 || !this.f13782h.equals(c2750l.f13782h))) {
            return false;
        }
        a = m15609j();
        a2 = c2750l.m15609j();
        if ((a || a2) && (!a || !a2 || !this.f13783i.equals(c2750l.f13783i))) {
            return false;
        }
        a = m15610k();
        a2 = c2750l.m15610k();
        return !(a || a2) || (a && a2 && this.f13784j.equals(c2750l.f13784j));
    }

    public int m15596b(C2750l c2750l) {
        if (!getClass().equals(c2750l.getClass())) {
            return getClass().getName().compareTo(c2750l.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15594a()).compareTo(Boolean.valueOf(c2750l.m15594a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15594a()) {
            compareTo = C3269c.m18090a(this.f13775a, c2750l.f13775a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15600b()).compareTo(Boolean.valueOf(c2750l.m15600b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15600b()) {
            compareTo = C3269c.m18089a(this.f13776b, c2750l.f13776b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15602c()).compareTo(Boolean.valueOf(c2750l.m15602c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15602c()) {
            compareTo = C3269c.m18090a(this.f13777c, c2750l.f13777c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15603d()).compareTo(Boolean.valueOf(c2750l.m15603d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15603d()) {
            compareTo = C3269c.m18090a(this.f13778d, c2750l.f13778d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15604e()).compareTo(Boolean.valueOf(c2750l.m15604e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15604e()) {
            compareTo = C3269c.m18090a(this.f13779e, c2750l.f13779e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15605f()).compareTo(Boolean.valueOf(c2750l.m15605f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15605f()) {
            compareTo = C3269c.m18095a(this.f13780f, c2750l.f13780f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15606g()).compareTo(Boolean.valueOf(c2750l.m15606g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15606g()) {
            compareTo = C3269c.m18090a(this.f13781g, c2750l.f13781g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15608i()).compareTo(Boolean.valueOf(c2750l.m15608i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15608i()) {
            compareTo = C3269c.m18093a(this.f13782h, c2750l.f13782h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15609j()).compareTo(Boolean.valueOf(c2750l.m15609j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15609j()) {
            compareTo = C3269c.m18090a(this.f13783i, c2750l.f13783i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15610k()).compareTo(Boolean.valueOf(c2750l.m15610k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15610k()) {
            compareTo = C3269c.m18090a(this.f13784j, c2750l.f13784j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2750l m15597b(String str) {
        this.f13778d = str;
        return this;
    }

    public void m15598b(C3249h c3249h) {
        m15611l();
        c3249h.m17936a(f13764l);
        if (this.f13775a != null && m15594a()) {
            c3249h.m17932a(f13765m);
            c3249h.m17930a(this.f13775a);
            c3249h.m17938b();
        }
        if (this.f13776b != null && m15600b()) {
            c3249h.m17932a(f13766n);
            this.f13776b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13777c != null) {
            c3249h.m17932a(f13767o);
            c3249h.m17930a(this.f13777c);
            c3249h.m17938b();
        }
        if (this.f13778d != null && m15603d()) {
            c3249h.m17932a(f13768p);
            c3249h.m17930a(this.f13778d);
            c3249h.m17938b();
        }
        if (this.f13779e != null && m15604e()) {
            c3249h.m17932a(f13769q);
            c3249h.m17930a(this.f13779e);
            c3249h.m17938b();
        }
        c3249h.m17932a(f13770r);
        c3249h.m17937a(this.f13780f);
        c3249h.m17938b();
        if (this.f13781g != null && m15606g()) {
            c3249h.m17932a(f13771s);
            c3249h.m17930a(this.f13781g);
            c3249h.m17938b();
        }
        if (this.f13782h != null && m15608i()) {
            c3249h.m17932a(f13772t);
            c3249h.m17934a(new C3257g((byte) 11, (byte) 11, this.f13782h.size()));
            for (Entry entry : this.f13782h.entrySet()) {
                c3249h.m17930a((String) entry.getKey());
                c3249h.m17930a((String) entry.getValue());
            }
            c3249h.m17940d();
            c3249h.m17938b();
        }
        if (this.f13783i != null && m15609j()) {
            c3249h.m17932a(f13773u);
            c3249h.m17930a(this.f13783i);
            c3249h.m17938b();
        }
        if (this.f13784j != null && m15610k()) {
            c3249h.m17932a(f13774v);
            c3249h.m17930a(this.f13784j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m15599b(boolean z) {
        this.f13785w.set(0, z);
    }

    public boolean m15600b() {
        return this.f13776b != null;
    }

    public C2750l m15601c(String str) {
        this.f13779e = str;
        return this;
    }

    public boolean m15602c() {
        return this.f13777c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15596b((C2750l) obj);
    }

    public boolean m15603d() {
        return this.f13778d != null;
    }

    public boolean m15604e() {
        return this.f13779e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2750l)) ? m15595a((C2750l) obj) : false;
    }

    public boolean m15605f() {
        return this.f13785w.get(0);
    }

    public boolean m15606g() {
        return this.f13781g != null;
    }

    public Map<String, String> m15607h() {
        return this.f13782h;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15608i() {
        return this.f13782h != null;
    }

    public boolean m15609j() {
        return this.f13783i != null;
    }

    public boolean m15610k() {
        return this.f13784j != null;
    }

    public void m15611l() {
        if (this.f13777c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionNotification(");
        Object obj2 = 1;
        if (m15594a()) {
            stringBuilder.append("debug:");
            if (this.f13775a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13775a);
            }
            obj2 = null;
        }
        if (m15600b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13776b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13776b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13777c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13777c);
        }
        if (m15603d()) {
            stringBuilder.append(", ");
            stringBuilder.append("appId:");
            if (this.f13778d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13778d);
            }
        }
        if (m15604e()) {
            stringBuilder.append(", ");
            stringBuilder.append("type:");
            if (this.f13779e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13779e);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("requireAck:");
        stringBuilder.append(this.f13780f);
        if (m15606g()) {
            stringBuilder.append(", ");
            stringBuilder.append("payload:");
            if (this.f13781g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13781g);
            }
        }
        if (m15608i()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.f13782h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13782h);
            }
        }
        if (m15609j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13783i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13783i);
            }
        }
        if (m15610k()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13784j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13784j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
