package com.xiaomi.common.logger.thrift.mfs;

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

/* renamed from: com.xiaomi.common.logger.thrift.mfs.f */
public class C2491f implements Serializable, Cloneable, C2478b<C2491f, C2490a> {
    public static final Map<C2490a, C3243b> f12592a;
    private static final C3262m f12593b;
    private static final C3255e f12594c;
    private static final C3255e f12595d;
    private static final C3255e f12596e;
    private static final C3255e f12597f;
    private static final C3255e f12598g;
    private static final C3255e f12599h;
    private static final C3255e f12600i;
    private String f12601j;
    private String f12602k;
    private String f12603l;
    private String f12604m;
    private String f12605n;
    private C2489e f12606o;
    private Set<C2493g> f12607p;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.f.a */
    public enum C2490a {
        CATEGORY((short) 1, "category"),
        UUID((short) 2, "uuid"),
        VERSION((short) 3, C2537j.aq),
        NETWORK((short) 4, "network"),
        RID((short) 5, "rid"),
        LOCATION((short) 6, "location"),
        HOST_INFO((short) 7, "host_info");
        
        private static final Map<String, C2490a> f12588h;
        private final short f12590i;
        private final String f12591j;

        static {
            f12588h = new HashMap();
            Iterator it = EnumSet.allOf(C2490a.class).iterator();
            while (it.hasNext()) {
                C2490a c2490a = (C2490a) it.next();
                f12588h.put(c2490a.m14271a(), c2490a);
            }
        }

        private C2490a(short s, String str) {
            this.f12590i = s;
            this.f12591j = str;
        }

        public String m14271a() {
            return this.f12591j;
        }
    }

    static {
        f12593b = new C3262m("Passport");
        f12594c = new C3255e("category", (byte) 11, (short) 1);
        f12595d = new C3255e("uuid", (byte) 11, (short) 2);
        f12596e = new C3255e(C2537j.aq, (byte) 11, (short) 3);
        f12597f = new C3255e("network", (byte) 11, (short) 4);
        f12598g = new C3255e("rid", (byte) 11, (short) 5);
        f12599h = new C3255e("location", (byte) 12, (short) 6);
        f12600i = new C3255e("host_info", (byte) 14, (short) 7);
        Map enumMap = new EnumMap(C2490a.class);
        enumMap.put(C2490a.CATEGORY, new C3243b("category", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2490a.UUID, new C3243b("uuid", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2490a.VERSION, new C3243b(C2537j.aq, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2490a.NETWORK, new C3243b("network", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2490a.RID, new C3243b("rid", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2490a.LOCATION, new C3243b("location", (byte) 2, new C3247g((byte) 12, C2489e.class)));
        enumMap.put(C2490a.HOST_INFO, new C3243b("host_info", (byte) 2, new C3246f((byte) 14, new C3247g((byte) 12, C2493g.class))));
        f12592a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2491f.class, f12592a);
    }

    public C2491f() {
        this.f12601j = C2915a.f14760f;
    }

    public void m14272a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m14283h();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12601j = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12602k = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12603l = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12604m = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12605n = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12606o = new C2489e();
                    this.f12606o.m14259a(c3249h);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != 14) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3261l o = c3249h.m17951o();
                    this.f12607p = new HashSet(o.f15766b * 2);
                    for (int i2 = 0; i2 < o.f15766b; i2++) {
                        C2493g c2493g = new C2493g();
                        c2493g.m14285a(c3249h);
                        this.f12607p.add(c2493g);
                    }
                    c3249h.m17952p();
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m14273a() {
        return this.f12601j != null;
    }

    public boolean m14274a(C2491f c2491f) {
        if (c2491f == null) {
            return false;
        }
        boolean a = m14273a();
        boolean a2 = c2491f.m14273a();
        if ((a || a2) && (!a || !a2 || !this.f12601j.equals(c2491f.f12601j))) {
            return false;
        }
        a = m14277b();
        a2 = c2491f.m14277b();
        if ((a || a2) && (!a || !a2 || !this.f12602k.equals(c2491f.f12602k))) {
            return false;
        }
        a = m14278c();
        a2 = c2491f.m14278c();
        if ((a || a2) && (!a || !a2 || !this.f12603l.equals(c2491f.f12603l))) {
            return false;
        }
        a = m14279d();
        a2 = c2491f.m14279d();
        if ((a || a2) && (!a || !a2 || !this.f12604m.equals(c2491f.f12604m))) {
            return false;
        }
        a = m14280e();
        a2 = c2491f.m14280e();
        if ((a || a2) && (!a || !a2 || !this.f12605n.equals(c2491f.f12605n))) {
            return false;
        }
        a = m14281f();
        a2 = c2491f.m14281f();
        if ((a || a2) && (!a || !a2 || !this.f12606o.m14261a(c2491f.f12606o))) {
            return false;
        }
        a = m14282g();
        a2 = c2491f.m14282g();
        return !(a || a2) || (a && a2 && this.f12607p.equals(c2491f.f12607p));
    }

    public int m14275b(C2491f c2491f) {
        if (!getClass().equals(c2491f.getClass())) {
            return getClass().getName().compareTo(c2491f.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14273a()).compareTo(Boolean.valueOf(c2491f.m14273a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14273a()) {
            compareTo = C3269c.m18090a(this.f12601j, c2491f.f12601j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14277b()).compareTo(Boolean.valueOf(c2491f.m14277b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14277b()) {
            compareTo = C3269c.m18090a(this.f12602k, c2491f.f12602k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14278c()).compareTo(Boolean.valueOf(c2491f.m14278c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14278c()) {
            compareTo = C3269c.m18090a(this.f12603l, c2491f.f12603l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14279d()).compareTo(Boolean.valueOf(c2491f.m14279d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14279d()) {
            compareTo = C3269c.m18090a(this.f12604m, c2491f.f12604m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14280e()).compareTo(Boolean.valueOf(c2491f.m14280e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14280e()) {
            compareTo = C3269c.m18090a(this.f12605n, c2491f.f12605n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14281f()).compareTo(Boolean.valueOf(c2491f.m14281f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14281f()) {
            compareTo = C3269c.m18089a(this.f12606o, c2491f.f12606o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14282g()).compareTo(Boolean.valueOf(c2491f.m14282g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14282g()) {
            compareTo = C3269c.m18094a(this.f12607p, c2491f.f12607p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m14276b(C3249h c3249h) {
        m14283h();
        c3249h.m17936a(f12593b);
        if (this.f12601j != null) {
            c3249h.m17932a(f12594c);
            c3249h.m17930a(this.f12601j);
            c3249h.m17938b();
        }
        if (this.f12602k != null) {
            c3249h.m17932a(f12595d);
            c3249h.m17930a(this.f12602k);
            c3249h.m17938b();
        }
        if (this.f12603l != null) {
            c3249h.m17932a(f12596e);
            c3249h.m17930a(this.f12603l);
            c3249h.m17938b();
        }
        if (this.f12604m != null) {
            c3249h.m17932a(f12597f);
            c3249h.m17930a(this.f12604m);
            c3249h.m17938b();
        }
        if (this.f12605n != null) {
            c3249h.m17932a(f12598g);
            c3249h.m17930a(this.f12605n);
            c3249h.m17938b();
        }
        if (this.f12606o != null && m14281f()) {
            c3249h.m17932a(f12599h);
            this.f12606o.m14264b(c3249h);
            c3249h.m17938b();
        }
        if (this.f12607p != null && m14282g()) {
            c3249h.m17932a(f12600i);
            c3249h.m17935a(new C3261l((byte) 12, this.f12607p.size()));
            for (C2493g b : this.f12607p) {
                b.m14289b(c3249h);
            }
            c3249h.m17942f();
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m14277b() {
        return this.f12602k != null;
    }

    public boolean m14278c() {
        return this.f12603l != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14275b((C2491f) obj);
    }

    public boolean m14279d() {
        return this.f12604m != null;
    }

    public boolean m14280e() {
        return this.f12605n != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2491f)) ? m14274a((C2491f) obj) : false;
    }

    public boolean m14281f() {
        return this.f12606o != null;
    }

    public boolean m14282g() {
        return this.f12607p != null;
    }

    public void m14283h() {
        if (this.f12601j == null) {
            throw new C3259i("Required field 'category' was not present! Struct: " + toString());
        } else if (this.f12602k == null) {
            throw new C3259i("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.f12603l == null) {
            throw new C3259i("Required field 'version' was not present! Struct: " + toString());
        } else if (this.f12604m == null) {
            throw new C3259i("Required field 'network' was not present! Struct: " + toString());
        } else if (this.f12605n == null) {
            throw new C3259i("Required field 'rid' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Passport(");
        stringBuilder.append("category:");
        if (this.f12601j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12601j);
        }
        stringBuilder.append(", ");
        stringBuilder.append("uuid:");
        if (this.f12602k == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12602k);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        if (this.f12603l == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12603l);
        }
        stringBuilder.append(", ");
        stringBuilder.append("network:");
        if (this.f12604m == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12604m);
        }
        stringBuilder.append(", ");
        stringBuilder.append("rid:");
        if (this.f12605n == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12605n);
        }
        if (m14281f()) {
            stringBuilder.append(", ");
            stringBuilder.append("location:");
            if (this.f12606o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12606o);
            }
        }
        if (m14282g()) {
            stringBuilder.append(", ");
            stringBuilder.append("host_info:");
            if (this.f12607p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12607p);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
