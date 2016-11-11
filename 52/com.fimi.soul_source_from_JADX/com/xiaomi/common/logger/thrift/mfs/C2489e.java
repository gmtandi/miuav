package com.xiaomi.common.logger.thrift.mfs;

import com.amap.api.services.district.DistrictSearchQuery;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.common.logger.thrift.mfs.e */
public class C2489e implements Serializable, Cloneable, C2478b<C2489e, C2488a> {
    public static final Map<C2488a, C3243b> f12571a;
    private static final C3262m f12572b;
    private static final C3255e f12573c;
    private static final C3255e f12574d;
    private static final C3255e f12575e;
    private static final C3255e f12576f;
    private String f12577g;
    private String f12578h;
    private String f12579i;
    private String f12580j;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.e.a */
    public enum C2488a {
        CONTRY((short) 1, "contry"),
        PROVINCE((short) 2, DistrictSearchQuery.KEYWORDS_PROVINCE),
        CITY((short) 3, DistrictSearchQuery.KEYWORDS_CITY),
        ISP((short) 4, "isp");
        
        private static final Map<String, C2488a> f12567e;
        private final short f12569f;
        private final String f12570g;

        static {
            f12567e = new HashMap();
            Iterator it = EnumSet.allOf(C2488a.class).iterator();
            while (it.hasNext()) {
                C2488a c2488a = (C2488a) it.next();
                f12567e.put(c2488a.m14257a(), c2488a);
            }
        }

        private C2488a(short s, String str) {
            this.f12569f = s;
            this.f12570g = str;
        }

        public String m14257a() {
            return this.f12570g;
        }
    }

    static {
        f12572b = new C3262m(C3004e.f14996H);
        f12573c = new C3255e("contry", (byte) 11, (short) 1);
        f12574d = new C3255e(DistrictSearchQuery.KEYWORDS_PROVINCE, (byte) 11, (short) 2);
        f12575e = new C3255e(DistrictSearchQuery.KEYWORDS_CITY, (byte) 11, (short) 3);
        f12576f = new C3255e("isp", (byte) 11, (short) 4);
        Map enumMap = new EnumMap(C2488a.class);
        enumMap.put(C2488a.CONTRY, new C3243b("contry", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2488a.PROVINCE, new C3243b(DistrictSearchQuery.KEYWORDS_PROVINCE, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2488a.CITY, new C3243b(DistrictSearchQuery.KEYWORDS_CITY, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2488a.ISP, new C3243b("isp", (byte) 2, new C3241c((byte) 11)));
        f12571a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2489e.class, f12571a);
    }

    public C2489e m14258a(String str) {
        this.f12577g = str;
        return this;
    }

    public void m14259a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m14270e();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12577g = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12578h = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12579i = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12580j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m14260a() {
        return this.f12577g != null;
    }

    public boolean m14261a(C2489e c2489e) {
        if (c2489e == null) {
            return false;
        }
        boolean a = m14260a();
        boolean a2 = c2489e.m14260a();
        if ((a || a2) && (!a || !a2 || !this.f12577g.equals(c2489e.f12577g))) {
            return false;
        }
        a = m14265b();
        a2 = c2489e.m14265b();
        if ((a || a2) && (!a || !a2 || !this.f12578h.equals(c2489e.f12578h))) {
            return false;
        }
        a = m14267c();
        a2 = c2489e.m14267c();
        if ((a || a2) && (!a || !a2 || !this.f12579i.equals(c2489e.f12579i))) {
            return false;
        }
        a = m14269d();
        a2 = c2489e.m14269d();
        return !(a || a2) || (a && a2 && this.f12580j.equals(c2489e.f12580j));
    }

    public int m14262b(C2489e c2489e) {
        if (!getClass().equals(c2489e.getClass())) {
            return getClass().getName().compareTo(c2489e.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14260a()).compareTo(Boolean.valueOf(c2489e.m14260a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14260a()) {
            compareTo = C3269c.m18090a(this.f12577g, c2489e.f12577g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14265b()).compareTo(Boolean.valueOf(c2489e.m14265b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14265b()) {
            compareTo = C3269c.m18090a(this.f12578h, c2489e.f12578h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14267c()).compareTo(Boolean.valueOf(c2489e.m14267c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14267c()) {
            compareTo = C3269c.m18090a(this.f12579i, c2489e.f12579i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14269d()).compareTo(Boolean.valueOf(c2489e.m14269d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14269d()) {
            compareTo = C3269c.m18090a(this.f12580j, c2489e.f12580j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2489e m14263b(String str) {
        this.f12578h = str;
        return this;
    }

    public void m14264b(C3249h c3249h) {
        m14270e();
        c3249h.m17936a(f12572b);
        if (this.f12577g != null && m14260a()) {
            c3249h.m17932a(f12573c);
            c3249h.m17930a(this.f12577g);
            c3249h.m17938b();
        }
        if (this.f12578h != null && m14265b()) {
            c3249h.m17932a(f12574d);
            c3249h.m17930a(this.f12578h);
            c3249h.m17938b();
        }
        if (this.f12579i != null && m14267c()) {
            c3249h.m17932a(f12575e);
            c3249h.m17930a(this.f12579i);
            c3249h.m17938b();
        }
        if (this.f12580j != null && m14269d()) {
            c3249h.m17932a(f12576f);
            c3249h.m17930a(this.f12580j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m14265b() {
        return this.f12578h != null;
    }

    public C2489e m14266c(String str) {
        this.f12579i = str;
        return this;
    }

    public boolean m14267c() {
        return this.f12579i != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14262b((C2489e) obj);
    }

    public C2489e m14268d(String str) {
        this.f12580j = str;
        return this;
    }

    public boolean m14269d() {
        return this.f12580j != null;
    }

    public void m14270e() {
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2489e)) ? m14261a((C2489e) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("Location(");
        Object obj2 = 1;
        if (m14260a()) {
            stringBuilder.append("contry:");
            if (this.f12577g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12577g);
            }
            obj2 = null;
        }
        if (m14265b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("province:");
            if (this.f12578h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12578h);
            }
            obj2 = null;
        }
        if (m14267c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("city:");
            if (this.f12579i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12579i);
            }
        } else {
            obj = obj2;
        }
        if (m14269d()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("isp:");
            if (this.f12580j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12580j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
