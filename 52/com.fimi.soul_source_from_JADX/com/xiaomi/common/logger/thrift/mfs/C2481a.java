package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3244d;
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3256f;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.common.logger.thrift.mfs.a */
public class C2481a implements Serializable, Cloneable, C2478b<C2481a, C2480a> {
    public static final Map<C2480a, C3243b> f12475a;
    private static final C3262m f12476b;
    private static final C3255e f12477c;
    private static final C3255e f12478d;
    private String f12479e;
    private List<C2487d> f12480f;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.a.a */
    public enum C2480a {
        HOST((short) 1, C2537j.HOST),
        LAND_NODE_INFO((short) 2, "land_node_info");
        
        private static final Map<String, C2480a> f12471c;
        private final short f12473d;
        private final String f12474e;

        static {
            f12471c = new HashMap();
            Iterator it = EnumSet.allOf(C2480a.class).iterator();
            while (it.hasNext()) {
                C2480a c2480a = (C2480a) it.next();
                f12471c.put(c2480a.m14184a(), c2480a);
            }
        }

        private C2480a(short s, String str) {
            this.f12473d = s;
            this.f12474e = str;
        }

        public String m14184a() {
            return this.f12474e;
        }
    }

    static {
        f12476b = new C3262m("HostInfo");
        f12477c = new C3255e(C2537j.HOST, (byte) 11, (short) 1);
        f12478d = new C3255e("land_node_info", (byte) 15, (short) 2);
        Map enumMap = new EnumMap(C2480a.class);
        enumMap.put(C2480a.HOST, new C3243b(C2537j.HOST, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2480a.LAND_NODE_INFO, new C3243b("land_node_info", (byte) 1, new C3244d((byte) 15, new C3247g((byte) 12, C2487d.class))));
        f12475a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2481a.class, f12475a);
    }

    public C2481a m14185a(String str) {
        this.f12479e = str;
        return this;
    }

    public C2481a m14186a(List<C2487d> list) {
        this.f12480f = list;
        return this;
    }

    public void m14187a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m14193c();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12479e = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 15) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3256f m = c3249h.m17949m();
                    this.f12480f = new ArrayList(m.f15759b);
                    for (int i2 = 0; i2 < m.f15759b; i2++) {
                        C2487d c2487d = new C2487d();
                        c2487d.m14239a(c3249h);
                        this.f12480f.add(c2487d);
                    }
                    c3249h.m17950n();
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m14188a() {
        return this.f12479e != null;
    }

    public boolean m14189a(C2481a c2481a) {
        if (c2481a == null) {
            return false;
        }
        boolean a = m14188a();
        boolean a2 = c2481a.m14188a();
        if ((a || a2) && (!a || !a2 || !this.f12479e.equals(c2481a.f12479e))) {
            return false;
        }
        a = m14192b();
        a2 = c2481a.m14192b();
        return !(a || a2) || (a && a2 && this.f12480f.equals(c2481a.f12480f));
    }

    public int m14190b(C2481a c2481a) {
        if (!getClass().equals(c2481a.getClass())) {
            return getClass().getName().compareTo(c2481a.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14188a()).compareTo(Boolean.valueOf(c2481a.m14188a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14188a()) {
            compareTo = C3269c.m18090a(this.f12479e, c2481a.f12479e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14192b()).compareTo(Boolean.valueOf(c2481a.m14192b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14192b()) {
            compareTo = C3269c.m18092a(this.f12480f, c2481a.f12480f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m14191b(C3249h c3249h) {
        m14193c();
        c3249h.m17936a(f12476b);
        if (this.f12479e != null) {
            c3249h.m17932a(f12477c);
            c3249h.m17930a(this.f12479e);
            c3249h.m17938b();
        }
        if (this.f12480f != null) {
            c3249h.m17932a(f12478d);
            c3249h.m17933a(new C3256f((byte) 12, this.f12480f.size()));
            for (C2487d b : this.f12480f) {
                b.m14245b(c3249h);
            }
            c3249h.m17941e();
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m14192b() {
        return this.f12480f != null;
    }

    public void m14193c() {
        if (this.f12479e == null) {
            throw new C3259i("Required field 'host' was not present! Struct: " + toString());
        } else if (this.f12480f == null) {
            throw new C3259i("Required field 'land_node_info' was not present! Struct: " + toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14190b((C2481a) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2481a)) ? m14189a((C2481a) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HostInfo(");
        stringBuilder.append("host:");
        if (this.f12479e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12479e);
        }
        stringBuilder.append(", ");
        stringBuilder.append("land_node_info:");
        if (this.f12480f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12480f);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
