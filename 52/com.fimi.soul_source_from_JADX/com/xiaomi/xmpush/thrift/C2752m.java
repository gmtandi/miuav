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

/* renamed from: com.xiaomi.xmpush.thrift.m */
public class C2752m implements Serializable, Cloneable, C2478b<C2752m, C2751a> {
    public static final Map<C2751a, C3243b> f13800k;
    private static final C3262m f13801l;
    private static final C3255e f13802m;
    private static final C3255e f13803n;
    private static final C3255e f13804o;
    private static final C3255e f13805p;
    private static final C3255e f13806q;
    private static final C3255e f13807r;
    private static final C3255e f13808s;
    private static final C3255e f13809t;
    private static final C3255e f13810u;
    private static final C3255e f13811v;
    public String f13812a;
    public C2740g f13813b;
    public String f13814c;
    public String f13815d;
    public String f13816e;
    public String f13817f;
    public String f13818g;
    public String f13819h;
    public String f13820i;
    public String f13821j;

    /* renamed from: com.xiaomi.xmpush.thrift.m.a */
    public enum C2751a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        APP_VERSION((short) 5, "appVersion"),
        PACKAGE_NAME((short) 6, C2537j.f12839W),
        TOKEN((short) 7, "token"),
        DEVICE_ID((short) 8, C2537j.as),
        ALIAS_NAME((short) 9, "aliasName"),
        SDK_VERSION((short) 10, "sdkVersion");
        
        private static final Map<String, C2751a> f13796k;
        private final short f13798l;
        private final String f13799m;

        static {
            f13796k = new HashMap();
            Iterator it = EnumSet.allOf(C2751a.class).iterator();
            while (it.hasNext()) {
                C2751a c2751a = (C2751a) it.next();
                f13796k.put(c2751a.m15612a(), c2751a);
            }
        }

        private C2751a(short s, String str) {
            this.f13798l = s;
            this.f13799m = str;
        }

        public String m15612a() {
            return this.f13799m;
        }
    }

    static {
        f13801l = new C3262m("XmPushActionRegistration");
        f13802m = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13803n = new C3255e("target", (byte) 12, (short) 2);
        f13804o = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13805p = new C3255e("appId", (byte) 11, (short) 4);
        f13806q = new C3255e("appVersion", (byte) 11, (short) 5);
        f13807r = new C3255e(C2537j.f12839W, (byte) 11, (short) 6);
        f13808s = new C3255e("token", (byte) 11, (short) 7);
        f13809t = new C3255e(C2537j.as, (byte) 11, (short) 8);
        f13810u = new C3255e("aliasName", (byte) 11, (short) 9);
        f13811v = new C3255e("sdkVersion", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2751a.class);
        enumMap.put(C2751a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2751a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2751a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2751a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2751a.APP_VERSION, new C3243b("appVersion", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2751a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2751a.TOKEN, new C3243b("token", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2751a.DEVICE_ID, new C3243b(C2537j.as, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2751a.ALIAS_NAME, new C3243b("aliasName", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2751a.SDK_VERSION, new C3243b("sdkVersion", (byte) 2, new C3241c((byte) 11)));
        f13800k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2752m.class, f13800k);
    }

    public C2752m m15613a(String str) {
        this.f13814c = str;
        return this;
    }

    public void m15614a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15635m();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13812a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13813b = new C2740g();
                    this.f13813b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13814c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13815d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13816e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13817f = c3249h.m17959w();
                        break;
                    }
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13818g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13819h = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13820i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13821j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15615a() {
        return this.f13812a != null;
    }

    public boolean m15616a(C2752m c2752m) {
        if (c2752m == null) {
            return false;
        }
        boolean a = m15615a();
        boolean a2 = c2752m.m15615a();
        if ((a || a2) && (!a || !a2 || !this.f13812a.equals(c2752m.f13812a))) {
            return false;
        }
        a = m15620b();
        a2 = c2752m.m15620b();
        if ((a || a2) && (!a || !a2 || !this.f13813b.m15488a(c2752m.f13813b))) {
            return false;
        }
        a = m15622c();
        a2 = c2752m.m15622c();
        if ((a || a2) && (!a || !a2 || !this.f13814c.equals(c2752m.f13814c))) {
            return false;
        }
        a = m15626e();
        a2 = c2752m.m15626e();
        if ((a || a2) && (!a || !a2 || !this.f13815d.equals(c2752m.f13815d))) {
            return false;
        }
        a = m15628f();
        a2 = c2752m.m15628f();
        if ((a || a2) && (!a || !a2 || !this.f13816e.equals(c2752m.f13816e))) {
            return false;
        }
        a = m15629g();
        a2 = c2752m.m15629g();
        if ((a || a2) && (!a || !a2 || !this.f13817f.equals(c2752m.f13817f))) {
            return false;
        }
        a = m15631i();
        a2 = c2752m.m15631i();
        if ((a || a2) && (!a || !a2 || !this.f13818g.equals(c2752m.f13818g))) {
            return false;
        }
        a = m15632j();
        a2 = c2752m.m15632j();
        if ((a || a2) && (!a || !a2 || !this.f13819h.equals(c2752m.f13819h))) {
            return false;
        }
        a = m15633k();
        a2 = c2752m.m15633k();
        if ((a || a2) && (!a || !a2 || !this.f13820i.equals(c2752m.f13820i))) {
            return false;
        }
        a = m15634l();
        a2 = c2752m.m15634l();
        return !(a || a2) || (a && a2 && this.f13821j.equals(c2752m.f13821j));
    }

    public int m15617b(C2752m c2752m) {
        if (!getClass().equals(c2752m.getClass())) {
            return getClass().getName().compareTo(c2752m.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15615a()).compareTo(Boolean.valueOf(c2752m.m15615a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15615a()) {
            compareTo = C3269c.m18090a(this.f13812a, c2752m.f13812a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15620b()).compareTo(Boolean.valueOf(c2752m.m15620b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15620b()) {
            compareTo = C3269c.m18089a(this.f13813b, c2752m.f13813b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15622c()).compareTo(Boolean.valueOf(c2752m.m15622c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15622c()) {
            compareTo = C3269c.m18090a(this.f13814c, c2752m.f13814c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15626e()).compareTo(Boolean.valueOf(c2752m.m15626e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15626e()) {
            compareTo = C3269c.m18090a(this.f13815d, c2752m.f13815d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15628f()).compareTo(Boolean.valueOf(c2752m.m15628f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15628f()) {
            compareTo = C3269c.m18090a(this.f13816e, c2752m.f13816e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15629g()).compareTo(Boolean.valueOf(c2752m.m15629g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15629g()) {
            compareTo = C3269c.m18090a(this.f13817f, c2752m.f13817f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15631i()).compareTo(Boolean.valueOf(c2752m.m15631i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15631i()) {
            compareTo = C3269c.m18090a(this.f13818g, c2752m.f13818g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15632j()).compareTo(Boolean.valueOf(c2752m.m15632j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15632j()) {
            compareTo = C3269c.m18090a(this.f13819h, c2752m.f13819h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15633k()).compareTo(Boolean.valueOf(c2752m.m15633k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15633k()) {
            compareTo = C3269c.m18090a(this.f13820i, c2752m.f13820i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15634l()).compareTo(Boolean.valueOf(c2752m.m15634l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15634l()) {
            compareTo = C3269c.m18090a(this.f13821j, c2752m.f13821j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2752m m15618b(String str) {
        this.f13815d = str;
        return this;
    }

    public void m15619b(C3249h c3249h) {
        m15635m();
        c3249h.m17936a(f13801l);
        if (this.f13812a != null && m15615a()) {
            c3249h.m17932a(f13802m);
            c3249h.m17930a(this.f13812a);
            c3249h.m17938b();
        }
        if (this.f13813b != null && m15620b()) {
            c3249h.m17932a(f13803n);
            this.f13813b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13814c != null) {
            c3249h.m17932a(f13804o);
            c3249h.m17930a(this.f13814c);
            c3249h.m17938b();
        }
        if (this.f13815d != null) {
            c3249h.m17932a(f13805p);
            c3249h.m17930a(this.f13815d);
            c3249h.m17938b();
        }
        if (this.f13816e != null && m15628f()) {
            c3249h.m17932a(f13806q);
            c3249h.m17930a(this.f13816e);
            c3249h.m17938b();
        }
        if (this.f13817f != null && m15629g()) {
            c3249h.m17932a(f13807r);
            c3249h.m17930a(this.f13817f);
            c3249h.m17938b();
        }
        if (this.f13818g != null) {
            c3249h.m17932a(f13808s);
            c3249h.m17930a(this.f13818g);
            c3249h.m17938b();
        }
        if (this.f13819h != null && m15632j()) {
            c3249h.m17932a(f13809t);
            c3249h.m17930a(this.f13819h);
            c3249h.m17938b();
        }
        if (this.f13820i != null && m15633k()) {
            c3249h.m17932a(f13810u);
            c3249h.m17930a(this.f13820i);
            c3249h.m17938b();
        }
        if (this.f13821j != null && m15634l()) {
            c3249h.m17932a(f13811v);
            c3249h.m17930a(this.f13821j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15620b() {
        return this.f13813b != null;
    }

    public C2752m m15621c(String str) {
        this.f13816e = str;
        return this;
    }

    public boolean m15622c() {
        return this.f13814c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15617b((C2752m) obj);
    }

    public C2752m m15623d(String str) {
        this.f13817f = str;
        return this;
    }

    public String m15624d() {
        return this.f13815d;
    }

    public C2752m m15625e(String str) {
        this.f13818g = str;
        return this;
    }

    public boolean m15626e() {
        return this.f13815d != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2752m)) ? m15616a((C2752m) obj) : false;
    }

    public C2752m m15627f(String str) {
        this.f13819h = str;
        return this;
    }

    public boolean m15628f() {
        return this.f13816e != null;
    }

    public boolean m15629g() {
        return this.f13817f != null;
    }

    public String m15630h() {
        return this.f13818g;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15631i() {
        return this.f13818g != null;
    }

    public boolean m15632j() {
        return this.f13819h != null;
    }

    public boolean m15633k() {
        return this.f13820i != null;
    }

    public boolean m15634l() {
        return this.f13821j != null;
    }

    public void m15635m() {
        if (this.f13814c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13815d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f13818g == null) {
            throw new C3259i("Required field 'token' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionRegistration(");
        Object obj2 = 1;
        if (m15615a()) {
            stringBuilder.append("debug:");
            if (this.f13812a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13812a);
            }
            obj2 = null;
        }
        if (m15620b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13813b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13813b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13814c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13814c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13815d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13815d);
        }
        if (m15628f()) {
            stringBuilder.append(", ");
            stringBuilder.append("appVersion:");
            if (this.f13816e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13816e);
            }
        }
        if (m15629g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13817f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13817f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("token:");
        if (this.f13818g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13818g);
        }
        if (m15632j()) {
            stringBuilder.append(", ");
            stringBuilder.append("deviceId:");
            if (this.f13819h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13819h);
            }
        }
        if (m15633k()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.f13820i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13820i);
            }
        }
        if (m15634l()) {
            stringBuilder.append(", ");
            stringBuilder.append("sdkVersion:");
            if (this.f13821j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13821j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
