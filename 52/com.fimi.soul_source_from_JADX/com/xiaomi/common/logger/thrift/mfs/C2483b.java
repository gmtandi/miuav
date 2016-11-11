package com.xiaomi.common.logger.thrift.mfs;

import com.tencent.open.GameAppOperation;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3246f;
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3261l;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.common.logger.thrift.mfs.b */
public class C2483b implements Serializable, Cloneable, C2478b<C2483b, C2482a> {
    public static final Map<C2482a, C3243b> f12495a;
    private static final C3262m f12496b;
    private static final C3255e f12497c;
    private static final C3255e f12498d;
    private static final C3255e f12499e;
    private static final C3255e f12500f;
    private static final C3255e f12501g;
    private static final C3255e f12502h;
    private static final C3255e f12503i;
    private static final C3255e f12504j;
    private static final C3255e f12505k;
    private static final C3255e f12506l;
    private String f12507m;
    private String f12508n;
    private String f12509o;
    private String f12510p;
    private String f12511q;
    private C2489e f12512r;
    private Set<C2481a> f12513s;
    private String f12514t;
    private String f12515u;
    private String f12516v;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.b.a */
    public enum C2482a {
        CATEGORY((short) 1, "category"),
        UUID((short) 2, "uuid"),
        VERSION((short) 3, C2537j.aq),
        NETWORK((short) 4, "network"),
        CLIENT_IP((short) 5, "client_ip"),
        LOCATION((short) 6, "location"),
        HOST_INFO((short) 7, "host_info"),
        VERSION_TYPE((short) 8, "version_type"),
        APP_NAME((short) 9, GameAppOperation.QQFAV_DATALINE_APPNAME),
        APP_VERSION((short) 10, "app_version");
        
        private static final Map<String, C2482a> f12491k;
        private final short f12493l;
        private final String f12494m;

        static {
            f12491k = new HashMap();
            Iterator it = EnumSet.allOf(C2482a.class).iterator();
            while (it.hasNext()) {
                C2482a c2482a = (C2482a) it.next();
                f12491k.put(c2482a.m14194a(), c2482a);
            }
        }

        private C2482a(short s, String str) {
            this.f12493l = s;
            this.f12494m = str;
        }

        public String m14194a() {
            return this.f12494m;
        }
    }

    static {
        f12496b = new C3262m("HttpApi");
        f12497c = new C3255e("category", (byte) 11, (short) 1);
        f12498d = new C3255e("uuid", (byte) 11, (short) 2);
        f12499e = new C3255e(C2537j.aq, (byte) 11, (short) 3);
        f12500f = new C3255e("network", (byte) 11, (short) 4);
        f12501g = new C3255e("client_ip", (byte) 11, (short) 5);
        f12502h = new C3255e("location", (byte) 12, (short) 6);
        f12503i = new C3255e("host_info", (byte) 14, (short) 7);
        f12504j = new C3255e("version_type", (byte) 11, (short) 8);
        f12505k = new C3255e(GameAppOperation.QQFAV_DATALINE_APPNAME, (byte) 11, (short) 9);
        f12506l = new C3255e("app_version", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2482a.class);
        enumMap.put(C2482a.CATEGORY, new C3243b("category", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2482a.UUID, new C3243b("uuid", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2482a.VERSION, new C3243b(C2537j.aq, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2482a.NETWORK, new C3243b("network", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2482a.CLIENT_IP, new C3243b("client_ip", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2482a.LOCATION, new C3243b("location", (byte) 2, new C3247g((byte) 12, C2489e.class)));
        enumMap.put(C2482a.HOST_INFO, new C3243b("host_info", (byte) 2, new C3246f((byte) 14, new C3247g((byte) 12, C2481a.class))));
        enumMap.put(C2482a.VERSION_TYPE, new C3243b("version_type", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2482a.APP_NAME, new C3243b(GameAppOperation.QQFAV_DATALINE_APPNAME, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2482a.APP_VERSION, new C3243b("app_version", (byte) 2, new C3241c((byte) 11)));
        f12495a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2483b.class, f12495a);
    }

    public C2483b() {
        this.f12507m = C2915a.f14760f;
        this.f12514t = C2915a.f14760f;
        this.f12515u = C2915a.f14760f;
        this.f12516v = C2915a.f14760f;
    }

    public C2483b m14195a(C2489e c2489e) {
        this.f12512r = c2489e;
        return this;
    }

    public C2483b m14196a(String str) {
        this.f12507m = str;
        return this;
    }

    public void m14197a(C2481a c2481a) {
        if (this.f12513s == null) {
            this.f12513s = new HashSet();
        }
        this.f12513s.add(c2481a);
    }

    public void m14198a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m14220l();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12507m = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12508n = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12509o = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12510p = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12511q = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12512r = new C2489e();
                    this.f12512r.m14259a(c3249h);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != 14) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3261l o = c3249h.m17951o();
                    this.f12513s = new HashSet(o.f15766b * 2);
                    for (int i2 = 0; i2 < o.f15766b; i2++) {
                        C2481a c2481a = new C2481a();
                        c2481a.m14187a(c3249h);
                        this.f12513s.add(c2481a);
                    }
                    c3249h.m17952p();
                    break;
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12514t = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12515u = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12516v = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m14199a() {
        return this.f12507m != null;
    }

    public boolean m14200a(C2483b c2483b) {
        if (c2483b == null) {
            return false;
        }
        boolean a = m14199a();
        boolean a2 = c2483b.m14199a();
        if ((a || a2) && (!a || !a2 || !this.f12507m.equals(c2483b.f12507m))) {
            return false;
        }
        a = m14204b();
        a2 = c2483b.m14204b();
        if ((a || a2) && (!a || !a2 || !this.f12508n.equals(c2483b.f12508n))) {
            return false;
        }
        a = m14206c();
        a2 = c2483b.m14206c();
        if ((a || a2) && (!a || !a2 || !this.f12509o.equals(c2483b.f12509o))) {
            return false;
        }
        a = m14208d();
        a2 = c2483b.m14208d();
        if ((a || a2) && (!a || !a2 || !this.f12510p.equals(c2483b.f12510p))) {
            return false;
        }
        a = m14210e();
        a2 = c2483b.m14210e();
        if ((a || a2) && (!a || !a2 || !this.f12511q.equals(c2483b.f12511q))) {
            return false;
        }
        a = m14212f();
        a2 = c2483b.m14212f();
        if ((a || a2) && (!a || !a2 || !this.f12512r.m14261a(c2483b.f12512r))) {
            return false;
        }
        a = m14216h();
        a2 = c2483b.m14216h();
        if ((a || a2) && (!a || !a2 || !this.f12513s.equals(c2483b.f12513s))) {
            return false;
        }
        a = m14217i();
        a2 = c2483b.m14217i();
        if ((a || a2) && (!a || !a2 || !this.f12514t.equals(c2483b.f12514t))) {
            return false;
        }
        a = m14218j();
        a2 = c2483b.m14218j();
        if ((a || a2) && (!a || !a2 || !this.f12515u.equals(c2483b.f12515u))) {
            return false;
        }
        a = m14219k();
        a2 = c2483b.m14219k();
        return !(a || a2) || (a && a2 && this.f12516v.equals(c2483b.f12516v));
    }

    public int m14201b(C2483b c2483b) {
        if (!getClass().equals(c2483b.getClass())) {
            return getClass().getName().compareTo(c2483b.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14199a()).compareTo(Boolean.valueOf(c2483b.m14199a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14199a()) {
            compareTo = C3269c.m18090a(this.f12507m, c2483b.f12507m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14204b()).compareTo(Boolean.valueOf(c2483b.m14204b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14204b()) {
            compareTo = C3269c.m18090a(this.f12508n, c2483b.f12508n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14206c()).compareTo(Boolean.valueOf(c2483b.m14206c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14206c()) {
            compareTo = C3269c.m18090a(this.f12509o, c2483b.f12509o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14208d()).compareTo(Boolean.valueOf(c2483b.m14208d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14208d()) {
            compareTo = C3269c.m18090a(this.f12510p, c2483b.f12510p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14210e()).compareTo(Boolean.valueOf(c2483b.m14210e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14210e()) {
            compareTo = C3269c.m18090a(this.f12511q, c2483b.f12511q);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14212f()).compareTo(Boolean.valueOf(c2483b.m14212f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14212f()) {
            compareTo = C3269c.m18089a(this.f12512r, c2483b.f12512r);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14216h()).compareTo(Boolean.valueOf(c2483b.m14216h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14216h()) {
            compareTo = C3269c.m18094a(this.f12513s, c2483b.f12513s);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14217i()).compareTo(Boolean.valueOf(c2483b.m14217i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14217i()) {
            compareTo = C3269c.m18090a(this.f12514t, c2483b.f12514t);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14218j()).compareTo(Boolean.valueOf(c2483b.m14218j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14218j()) {
            compareTo = C3269c.m18090a(this.f12515u, c2483b.f12515u);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14219k()).compareTo(Boolean.valueOf(c2483b.m14219k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14219k()) {
            compareTo = C3269c.m18090a(this.f12516v, c2483b.f12516v);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2483b m14202b(String str) {
        this.f12508n = str;
        return this;
    }

    public void m14203b(C3249h c3249h) {
        m14220l();
        c3249h.m17936a(f12496b);
        if (this.f12507m != null) {
            c3249h.m17932a(f12497c);
            c3249h.m17930a(this.f12507m);
            c3249h.m17938b();
        }
        if (this.f12508n != null) {
            c3249h.m17932a(f12498d);
            c3249h.m17930a(this.f12508n);
            c3249h.m17938b();
        }
        if (this.f12509o != null) {
            c3249h.m17932a(f12499e);
            c3249h.m17930a(this.f12509o);
            c3249h.m17938b();
        }
        if (this.f12510p != null) {
            c3249h.m17932a(f12500f);
            c3249h.m17930a(this.f12510p);
            c3249h.m17938b();
        }
        if (this.f12511q != null && m14210e()) {
            c3249h.m17932a(f12501g);
            c3249h.m17930a(this.f12511q);
            c3249h.m17938b();
        }
        if (this.f12512r != null && m14212f()) {
            c3249h.m17932a(f12502h);
            this.f12512r.m14264b(c3249h);
            c3249h.m17938b();
        }
        if (this.f12513s != null && m14216h()) {
            c3249h.m17932a(f12503i);
            c3249h.m17935a(new C3261l((byte) 12, this.f12513s.size()));
            for (C2481a b : this.f12513s) {
                b.m14191b(c3249h);
            }
            c3249h.m17942f();
            c3249h.m17938b();
        }
        if (this.f12514t != null && m14217i()) {
            c3249h.m17932a(f12504j);
            c3249h.m17930a(this.f12514t);
            c3249h.m17938b();
        }
        if (this.f12515u != null && m14218j()) {
            c3249h.m17932a(f12505k);
            c3249h.m17930a(this.f12515u);
            c3249h.m17938b();
        }
        if (this.f12516v != null && m14219k()) {
            c3249h.m17932a(f12506l);
            c3249h.m17930a(this.f12516v);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m14204b() {
        return this.f12508n != null;
    }

    public C2483b m14205c(String str) {
        this.f12509o = str;
        return this;
    }

    public boolean m14206c() {
        return this.f12509o != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14201b((C2483b) obj);
    }

    public C2483b m14207d(String str) {
        this.f12510p = str;
        return this;
    }

    public boolean m14208d() {
        return this.f12510p != null;
    }

    public C2483b m14209e(String str) {
        this.f12511q = str;
        return this;
    }

    public boolean m14210e() {
        return this.f12511q != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2483b)) ? m14200a((C2483b) obj) : false;
    }

    public C2483b m14211f(String str) {
        this.f12514t = str;
        return this;
    }

    public boolean m14212f() {
        return this.f12512r != null;
    }

    public int m14213g() {
        return this.f12513s == null ? 0 : this.f12513s.size();
    }

    public C2483b m14214g(String str) {
        this.f12515u = str;
        return this;
    }

    public C2483b m14215h(String str) {
        this.f12516v = str;
        return this;
    }

    public boolean m14216h() {
        return this.f12513s != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m14217i() {
        return this.f12514t != null;
    }

    public boolean m14218j() {
        return this.f12515u != null;
    }

    public boolean m14219k() {
        return this.f12516v != null;
    }

    public void m14220l() {
        if (this.f12507m == null) {
            throw new C3259i("Required field 'category' was not present! Struct: " + toString());
        } else if (this.f12508n == null) {
            throw new C3259i("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.f12509o == null) {
            throw new C3259i("Required field 'version' was not present! Struct: " + toString());
        } else if (this.f12510p == null) {
            throw new C3259i("Required field 'network' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpApi(");
        stringBuilder.append("category:");
        if (this.f12507m == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12507m);
        }
        stringBuilder.append(", ");
        stringBuilder.append("uuid:");
        if (this.f12508n == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12508n);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        if (this.f12509o == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12509o);
        }
        stringBuilder.append(", ");
        stringBuilder.append("network:");
        if (this.f12510p == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12510p);
        }
        if (m14210e()) {
            stringBuilder.append(", ");
            stringBuilder.append("client_ip:");
            if (this.f12511q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12511q);
            }
        }
        if (m14212f()) {
            stringBuilder.append(", ");
            stringBuilder.append("location:");
            if (this.f12512r == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12512r);
            }
        }
        if (m14216h()) {
            stringBuilder.append(", ");
            stringBuilder.append("host_info:");
            if (this.f12513s == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12513s);
            }
        }
        if (m14217i()) {
            stringBuilder.append(", ");
            stringBuilder.append("version_type:");
            if (this.f12514t == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12514t);
            }
        }
        if (m14218j()) {
            stringBuilder.append(", ");
            stringBuilder.append("app_name:");
            if (this.f12515u == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12515u);
            }
        }
        if (m14219k()) {
            stringBuilder.append(", ");
            stringBuilder.append("app_version:");
            if (this.f12516v == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12516v);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
