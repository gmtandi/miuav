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

/* renamed from: com.xiaomi.common.logger.thrift.mfs.g */
public class C2493g implements Serializable, Cloneable, C2478b<C2493g, C2492a> {
    public static final Map<C2492a, C3243b> f12614a;
    private static final C3262m f12615b;
    private static final C3255e f12616c;
    private static final C3255e f12617d;
    private String f12618e;
    private List<C2495h> f12619f;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.g.a */
    public enum C2492a {
        HOST((short) 1, C2537j.HOST),
        LAND_NODE_INFO((short) 2, "land_node_info");
        
        private static final Map<String, C2492a> f12610c;
        private final short f12612d;
        private final String f12613e;

        static {
            f12610c = new HashMap();
            Iterator it = EnumSet.allOf(C2492a.class).iterator();
            while (it.hasNext()) {
                C2492a c2492a = (C2492a) it.next();
                f12610c.put(c2492a.m14284a(), c2492a);
            }
        }

        private C2492a(short s, String str) {
            this.f12612d = s;
            this.f12613e = str;
        }

        public String m14284a() {
            return this.f12613e;
        }
    }

    static {
        f12615b = new C3262m("PassportHostInfo");
        f12616c = new C3255e(C2537j.HOST, (byte) 11, (short) 1);
        f12617d = new C3255e("land_node_info", (byte) 15, (short) 2);
        Map enumMap = new EnumMap(C2492a.class);
        enumMap.put(C2492a.HOST, new C3243b(C2537j.HOST, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2492a.LAND_NODE_INFO, new C3243b("land_node_info", (byte) 1, new C3244d((byte) 15, new C3247g((byte) 12, C2495h.class))));
        f12614a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2493g.class, f12614a);
    }

    public void m14285a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m14291c();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12618e = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 15) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3256f m = c3249h.m17949m();
                    this.f12619f = new ArrayList(m.f15759b);
                    for (int i2 = 0; i2 < m.f15759b; i2++) {
                        C2495h c2495h = new C2495h();
                        c2495h.m14293a(c3249h);
                        this.f12619f.add(c2495h);
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

    public boolean m14286a() {
        return this.f12618e != null;
    }

    public boolean m14287a(C2493g c2493g) {
        if (c2493g == null) {
            return false;
        }
        boolean a = m14286a();
        boolean a2 = c2493g.m14286a();
        if ((a || a2) && (!a || !a2 || !this.f12618e.equals(c2493g.f12618e))) {
            return false;
        }
        a = m14290b();
        a2 = c2493g.m14290b();
        return !(a || a2) || (a && a2 && this.f12619f.equals(c2493g.f12619f));
    }

    public int m14288b(C2493g c2493g) {
        if (!getClass().equals(c2493g.getClass())) {
            return getClass().getName().compareTo(c2493g.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14286a()).compareTo(Boolean.valueOf(c2493g.m14286a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14286a()) {
            compareTo = C3269c.m18090a(this.f12618e, c2493g.f12618e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14290b()).compareTo(Boolean.valueOf(c2493g.m14290b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14290b()) {
            compareTo = C3269c.m18092a(this.f12619f, c2493g.f12619f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m14289b(C3249h c3249h) {
        m14291c();
        c3249h.m17936a(f12615b);
        if (this.f12618e != null) {
            c3249h.m17932a(f12616c);
            c3249h.m17930a(this.f12618e);
            c3249h.m17938b();
        }
        if (this.f12619f != null) {
            c3249h.m17932a(f12617d);
            c3249h.m17933a(new C3256f((byte) 12, this.f12619f.size()));
            for (C2495h b : this.f12619f) {
                b.m14298b(c3249h);
            }
            c3249h.m17941e();
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m14290b() {
        return this.f12619f != null;
    }

    public void m14291c() {
        if (this.f12618e == null) {
            throw new C3259i("Required field 'host' was not present! Struct: " + toString());
        } else if (this.f12619f == null) {
            throw new C3259i("Required field 'land_node_info' was not present! Struct: " + toString());
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14288b((C2493g) obj);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2493g)) ? m14287a((C2493g) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PassportHostInfo(");
        stringBuilder.append("host:");
        if (this.f12618e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12618e);
        }
        stringBuilder.append(", ");
        stringBuilder.append("land_node_info:");
        if (this.f12619f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12619f);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
