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

/* renamed from: com.xiaomi.xmpush.thrift.v */
public class C2770v implements Serializable, Cloneable, C2478b<C2770v, C2769a> {
    public static final Map<C2769a, C3243b> f14096h;
    private static final C3262m f14097i;
    private static final C3255e f14098j;
    private static final C3255e f14099k;
    private static final C3255e f14100l;
    private static final C3255e f14101m;
    private static final C3255e f14102n;
    private static final C3255e f14103o;
    private static final C3255e f14104p;
    public String f14105a;
    public C2740g f14106b;
    public String f14107c;
    public String f14108d;
    public String f14109e;
    public String f14110f;
    public String f14111g;

    /* renamed from: com.xiaomi.xmpush.thrift.v.a */
    public enum C2769a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        TOPIC((short) 5, "topic"),
        PACKAGE_NAME((short) 6, C2537j.f12839W),
        CATEGORY((short) 7, "category");
        
        private static final Map<String, C2769a> f14092h;
        private final short f14094i;
        private final String f14095j;

        static {
            f14092h = new HashMap();
            Iterator it = EnumSet.allOf(C2769a.class).iterator();
            while (it.hasNext()) {
                C2769a c2769a = (C2769a) it.next();
                f14092h.put(c2769a.m15777a(), c2769a);
            }
        }

        private C2769a(short s, String str) {
            this.f14094i = s;
            this.f14095j = str;
        }

        public String m15777a() {
            return this.f14095j;
        }
    }

    static {
        f14097i = new C3262m("XmPushActionUnSubscription");
        f14098j = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f14099k = new C3255e("target", (byte) 12, (short) 2);
        f14100l = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f14101m = new C3255e("appId", (byte) 11, (short) 4);
        f14102n = new C3255e("topic", (byte) 11, (short) 5);
        f14103o = new C3255e(C2537j.f12839W, (byte) 11, (short) 6);
        f14104p = new C3255e("category", (byte) 11, (short) 7);
        Map enumMap = new EnumMap(C2769a.class);
        enumMap.put(C2769a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2769a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2769a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2769a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2769a.TOPIC, new C3243b("topic", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2769a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2769a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f14096h = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2770v.class, f14096h);
    }

    public C2770v m15778a(String str) {
        this.f14107c = str;
        return this;
    }

    public void m15779a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15794h();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14105a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14106b = new C2740g();
                    this.f14106b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14107c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14108d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14109e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14110f = c3249h.m17959w();
                        break;
                    }
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14111g = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15780a() {
        return this.f14105a != null;
    }

    public boolean m15781a(C2770v c2770v) {
        if (c2770v == null) {
            return false;
        }
        boolean a = m15780a();
        boolean a2 = c2770v.m15780a();
        if ((a || a2) && (!a || !a2 || !this.f14105a.equals(c2770v.f14105a))) {
            return false;
        }
        a = m15785b();
        a2 = c2770v.m15785b();
        if ((a || a2) && (!a || !a2 || !this.f14106b.m15488a(c2770v.f14106b))) {
            return false;
        }
        a = m15787c();
        a2 = c2770v.m15787c();
        if ((a || a2) && (!a || !a2 || !this.f14107c.equals(c2770v.f14107c))) {
            return false;
        }
        a = m15789d();
        a2 = c2770v.m15789d();
        if ((a || a2) && (!a || !a2 || !this.f14108d.equals(c2770v.f14108d))) {
            return false;
        }
        a = m15791e();
        a2 = c2770v.m15791e();
        if ((a || a2) && (!a || !a2 || !this.f14109e.equals(c2770v.f14109e))) {
            return false;
        }
        a = m15792f();
        a2 = c2770v.m15792f();
        if ((a || a2) && (!a || !a2 || !this.f14110f.equals(c2770v.f14110f))) {
            return false;
        }
        a = m15793g();
        a2 = c2770v.m15793g();
        return !(a || a2) || (a && a2 && this.f14111g.equals(c2770v.f14111g));
    }

    public int m15782b(C2770v c2770v) {
        if (!getClass().equals(c2770v.getClass())) {
            return getClass().getName().compareTo(c2770v.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15780a()).compareTo(Boolean.valueOf(c2770v.m15780a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15780a()) {
            compareTo = C3269c.m18090a(this.f14105a, c2770v.f14105a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15785b()).compareTo(Boolean.valueOf(c2770v.m15785b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15785b()) {
            compareTo = C3269c.m18089a(this.f14106b, c2770v.f14106b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15787c()).compareTo(Boolean.valueOf(c2770v.m15787c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15787c()) {
            compareTo = C3269c.m18090a(this.f14107c, c2770v.f14107c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15789d()).compareTo(Boolean.valueOf(c2770v.m15789d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15789d()) {
            compareTo = C3269c.m18090a(this.f14108d, c2770v.f14108d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15791e()).compareTo(Boolean.valueOf(c2770v.m15791e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15791e()) {
            compareTo = C3269c.m18090a(this.f14109e, c2770v.f14109e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15792f()).compareTo(Boolean.valueOf(c2770v.m15792f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15792f()) {
            compareTo = C3269c.m18090a(this.f14110f, c2770v.f14110f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15793g()).compareTo(Boolean.valueOf(c2770v.m15793g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15793g()) {
            compareTo = C3269c.m18090a(this.f14111g, c2770v.f14111g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2770v m15783b(String str) {
        this.f14108d = str;
        return this;
    }

    public void m15784b(C3249h c3249h) {
        m15794h();
        c3249h.m17936a(f14097i);
        if (this.f14105a != null && m15780a()) {
            c3249h.m17932a(f14098j);
            c3249h.m17930a(this.f14105a);
            c3249h.m17938b();
        }
        if (this.f14106b != null && m15785b()) {
            c3249h.m17932a(f14099k);
            this.f14106b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f14107c != null) {
            c3249h.m17932a(f14100l);
            c3249h.m17930a(this.f14107c);
            c3249h.m17938b();
        }
        if (this.f14108d != null) {
            c3249h.m17932a(f14101m);
            c3249h.m17930a(this.f14108d);
            c3249h.m17938b();
        }
        if (this.f14109e != null) {
            c3249h.m17932a(f14102n);
            c3249h.m17930a(this.f14109e);
            c3249h.m17938b();
        }
        if (this.f14110f != null && m15792f()) {
            c3249h.m17932a(f14103o);
            c3249h.m17930a(this.f14110f);
            c3249h.m17938b();
        }
        if (this.f14111g != null && m15793g()) {
            c3249h.m17932a(f14104p);
            c3249h.m17930a(this.f14111g);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15785b() {
        return this.f14106b != null;
    }

    public C2770v m15786c(String str) {
        this.f14109e = str;
        return this;
    }

    public boolean m15787c() {
        return this.f14107c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15782b((C2770v) obj);
    }

    public C2770v m15788d(String str) {
        this.f14110f = str;
        return this;
    }

    public boolean m15789d() {
        return this.f14108d != null;
    }

    public C2770v m15790e(String str) {
        this.f14111g = str;
        return this;
    }

    public boolean m15791e() {
        return this.f14109e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2770v)) ? m15781a((C2770v) obj) : false;
    }

    public boolean m15792f() {
        return this.f14110f != null;
    }

    public boolean m15793g() {
        return this.f14111g != null;
    }

    public void m15794h() {
        if (this.f14107c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f14108d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f14109e == null) {
            throw new C3259i("Required field 'topic' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnSubscription(");
        Object obj2 = 1;
        if (m15780a()) {
            stringBuilder.append("debug:");
            if (this.f14105a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14105a);
            }
            obj2 = null;
        }
        if (m15785b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f14106b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14106b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f14107c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14107c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f14108d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14108d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("topic:");
        if (this.f14109e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14109e);
        }
        if (m15792f()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f14110f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14110f);
            }
        }
        if (m15793g()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f14111g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14111g);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
