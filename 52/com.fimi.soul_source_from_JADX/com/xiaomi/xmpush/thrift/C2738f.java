package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Type;
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

/* renamed from: com.xiaomi.xmpush.thrift.f */
public class C2738f implements Serializable, Cloneable, C2478b<C2738f, C2737a> {
    public static final Map<C2737a, C3243b> f13581d;
    private static final C3262m f13582e;
    private static final C3255e f13583f;
    private static final C3255e f13584g;
    private static final C3255e f13585h;
    public String f13586a;
    public String f13587b;
    public List<C2736e> f13588c;

    /* renamed from: com.xiaomi.xmpush.thrift.f.a */
    public enum C2737a {
        UUID((short) 1, "uuid"),
        OPERATOR((short) 2, "operator"),
        EVENTS((short) 3, "events");
        
        private static final Map<String, C2737a> f13577d;
        private final short f13579e;
        private final String f13580f;

        static {
            f13577d = new HashMap();
            Iterator it = EnumSet.allOf(C2737a.class).iterator();
            while (it.hasNext()) {
                C2737a c2737a = (C2737a) it.next();
                f13577d.put(c2737a.m15474a(), c2737a);
            }
        }

        private C2737a(short s, String str) {
            this.f13579e = s;
            this.f13580f = str;
        }

        public String m15474a() {
            return this.f13580f;
        }
    }

    static {
        f13582e = new C3262m("StatsEvents");
        f13583f = new C3255e("uuid", (byte) 11, (short) 1);
        f13584g = new C3255e("operator", (byte) 11, (short) 2);
        f13585h = new C3255e("events", (byte) 15, (short) 3);
        Map enumMap = new EnumMap(C2737a.class);
        enumMap.put(C2737a.UUID, new C3243b("uuid", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2737a.OPERATOR, new C3243b("operator", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2737a.EVENTS, new C3243b("events", (byte) 1, new C3244d((byte) 15, new C3247g((byte) 12, C2736e.class))));
        f13581d = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2738f.class, f13581d);
    }

    public C2738f(String str, List<C2736e> list) {
        this();
        this.f13586a = str;
        this.f13588c = list;
    }

    public C2738f m15475a(String str) {
        this.f13587b = str;
        return this;
    }

    public void m15476a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15483d();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13586a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13587b = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != 15) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3256f m = c3249h.m17949m();
                    this.f13588c = new ArrayList(m.f15759b);
                    for (int i2 = 0; i2 < m.f15759b; i2++) {
                        C2736e c2736e = new C2736e();
                        c2736e.m15461a(c3249h);
                        this.f13588c.add(c2736e);
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

    public boolean m15477a() {
        return this.f13586a != null;
    }

    public boolean m15478a(C2738f c2738f) {
        if (c2738f == null) {
            return false;
        }
        boolean a = m15477a();
        boolean a2 = c2738f.m15477a();
        if ((a || a2) && (!a || !a2 || !this.f13586a.equals(c2738f.f13586a))) {
            return false;
        }
        a = m15481b();
        a2 = c2738f.m15481b();
        if ((a || a2) && (!a || !a2 || !this.f13587b.equals(c2738f.f13587b))) {
            return false;
        }
        a = m15482c();
        a2 = c2738f.m15482c();
        return !(a || a2) || (a && a2 && this.f13588c.equals(c2738f.f13588c));
    }

    public int m15479b(C2738f c2738f) {
        if (!getClass().equals(c2738f.getClass())) {
            return getClass().getName().compareTo(c2738f.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15477a()).compareTo(Boolean.valueOf(c2738f.m15477a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15477a()) {
            compareTo = C3269c.m18090a(this.f13586a, c2738f.f13586a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15481b()).compareTo(Boolean.valueOf(c2738f.m15481b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15481b()) {
            compareTo = C3269c.m18090a(this.f13587b, c2738f.f13587b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15482c()).compareTo(Boolean.valueOf(c2738f.m15482c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15482c()) {
            compareTo = C3269c.m18092a(this.f13588c, c2738f.f13588c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15480b(C3249h c3249h) {
        m15483d();
        c3249h.m17936a(f13582e);
        if (this.f13586a != null) {
            c3249h.m17932a(f13583f);
            c3249h.m17930a(this.f13586a);
            c3249h.m17938b();
        }
        if (this.f13587b != null && m15481b()) {
            c3249h.m17932a(f13584g);
            c3249h.m17930a(this.f13587b);
            c3249h.m17938b();
        }
        if (this.f13588c != null) {
            c3249h.m17932a(f13585h);
            c3249h.m17933a(new C3256f((byte) 12, this.f13588c.size()));
            for (C2736e b : this.f13588c) {
                b.m15466b(c3249h);
            }
            c3249h.m17941e();
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15481b() {
        return this.f13587b != null;
    }

    public boolean m15482c() {
        return this.f13588c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15479b((C2738f) obj);
    }

    public void m15483d() {
        if (this.f13586a == null) {
            throw new C3259i("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.f13588c == null) {
            throw new C3259i("Required field 'events' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2738f)) ? m15478a((C2738f) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("StatsEvents(");
        stringBuilder.append("uuid:");
        if (this.f13586a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13586a);
        }
        if (m15481b()) {
            stringBuilder.append(", ");
            stringBuilder.append("operator:");
            if (this.f13587b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13587b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("events:");
        if (this.f13588c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13588c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
