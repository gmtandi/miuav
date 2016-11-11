package com.xiaomi.common.logger.thrift.mfs;

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
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.common.logger.thrift.mfs.h */
public class C2495h implements Serializable, Cloneable, C2478b<C2495h, C2494a> {
    public static final Map<C2494a, C3243b> f12627a;
    private static final C3262m f12628b;
    private static final C3255e f12629c;
    private static final C3255e f12630d;
    private static final C3255e f12631e;
    private int f12632f;
    private int f12633g;
    private int f12634h;
    private BitSet f12635i;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.h.a */
    public enum C2494a {
        IP((short) 1, "ip"),
        EID((short) 2, "eid"),
        RT((short) 3, "rt");
        
        private static final Map<String, C2494a> f12623d;
        private final short f12625e;
        private final String f12626f;

        static {
            f12623d = new HashMap();
            Iterator it = EnumSet.allOf(C2494a.class).iterator();
            while (it.hasNext()) {
                C2494a c2494a = (C2494a) it.next();
                f12623d.put(c2494a.m14292a(), c2494a);
            }
        }

        private C2494a(short s, String str) {
            this.f12625e = s;
            this.f12626f = str;
        }

        public String m14292a() {
            return this.f12626f;
        }
    }

    static {
        f12628b = new C3262m("PassportLandNodeInfo");
        f12629c = new C3255e("ip", (byte) 8, (short) 1);
        f12630d = new C3255e("eid", (byte) 8, (short) 2);
        f12631e = new C3255e("rt", (byte) 8, (short) 3);
        Map enumMap = new EnumMap(C2494a.class);
        enumMap.put(C2494a.IP, new C3243b("ip", (byte) 1, new C3241c((byte) 8)));
        enumMap.put(C2494a.EID, new C3243b("eid", (byte) 1, new C3241c((byte) 8)));
        enumMap.put(C2494a.RT, new C3243b("rt", (byte) 1, new C3241c((byte) 8)));
        f12627a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2495h.class, f12627a);
    }

    public C2495h() {
        this.f12635i = new BitSet(3);
    }

    public void m14293a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (!m14295a()) {
                    throw new C3259i("Required field 'ip' was not found in serialized data! Struct: " + toString());
                } else if (!m14300b()) {
                    throw new C3259i("Required field 'eid' was not found in serialized data! Struct: " + toString());
                } else if (m14302c()) {
                    m14303d();
                    return;
                } else {
                    throw new C3259i("Required field 'rt' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12632f = c3249h.m17956t();
                    m14294a(true);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12633g = c3249h.m17956t();
                    m14299b(true);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12634h = c3249h.m17956t();
                    m14301c(true);
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m14294a(boolean z) {
        this.f12635i.set(0, z);
    }

    public boolean m14295a() {
        return this.f12635i.get(0);
    }

    public boolean m14296a(C2495h c2495h) {
        return c2495h != null && this.f12632f == c2495h.f12632f && this.f12633g == c2495h.f12633g && this.f12634h == c2495h.f12634h;
    }

    public int m14297b(C2495h c2495h) {
        if (!getClass().equals(c2495h.getClass())) {
            return getClass().getName().compareTo(c2495h.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14295a()).compareTo(Boolean.valueOf(c2495h.m14295a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14295a()) {
            compareTo = C3269c.m18087a(this.f12632f, c2495h.f12632f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14300b()).compareTo(Boolean.valueOf(c2495h.m14300b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14300b()) {
            compareTo = C3269c.m18087a(this.f12633g, c2495h.f12633g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14302c()).compareTo(Boolean.valueOf(c2495h.m14302c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14302c()) {
            compareTo = C3269c.m18087a(this.f12634h, c2495h.f12634h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m14298b(C3249h c3249h) {
        m14303d();
        c3249h.m17936a(f12628b);
        c3249h.m17932a(f12629c);
        c3249h.m17928a(this.f12632f);
        c3249h.m17938b();
        c3249h.m17932a(f12630d);
        c3249h.m17928a(this.f12633g);
        c3249h.m17938b();
        c3249h.m17932a(f12631e);
        c3249h.m17928a(this.f12634h);
        c3249h.m17938b();
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m14299b(boolean z) {
        this.f12635i.set(1, z);
    }

    public boolean m14300b() {
        return this.f12635i.get(1);
    }

    public void m14301c(boolean z) {
        this.f12635i.set(2, z);
    }

    public boolean m14302c() {
        return this.f12635i.get(2);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14297b((C2495h) obj);
    }

    public void m14303d() {
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2495h)) ? m14296a((C2495h) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PassportLandNodeInfo(");
        stringBuilder.append("ip:");
        stringBuilder.append(this.f12632f);
        stringBuilder.append(", ");
        stringBuilder.append("eid:");
        stringBuilder.append(this.f12633g);
        stringBuilder.append(", ");
        stringBuilder.append("rt:");
        stringBuilder.append(this.f12634h);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
