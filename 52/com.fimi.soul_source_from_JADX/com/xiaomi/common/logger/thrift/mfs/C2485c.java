package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.common.logger.thrift.C2479a;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
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

/* renamed from: com.xiaomi.common.logger.thrift.mfs.c */
public class C2485c implements Serializable, Cloneable, C2478b<C2485c, C2484a> {
    public static final Map<C2484a, C3243b> f12525a;
    private static final C3262m f12526b;
    private static final C3255e f12527c;
    private static final C3255e f12528d;
    private static final C3255e f12529e;
    private static final C3255e f12530f;
    private C2479a f12531g;
    private String f12532h;
    private C2483b f12533i;
    private C2491f f12534j;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.c.a */
    public enum C2484a {
        COMMON((short) 1, "common"),
        CATEGORY((short) 2, "category"),
        HTTP_API((short) 3, "httpApi"),
        PASSPORT((short) 4, "passport");
        
        private static final Map<String, C2484a> f12521e;
        private final short f12523f;
        private final String f12524g;

        static {
            f12521e = new HashMap();
            Iterator it = EnumSet.allOf(C2484a.class).iterator();
            while (it.hasNext()) {
                C2484a c2484a = (C2484a) it.next();
                f12521e.put(c2484a.m14221a(), c2484a);
            }
        }

        private C2484a(short s, String str) {
            this.f12523f = s;
            this.f12524g = str;
        }

        public String m14221a() {
            return this.f12524g;
        }
    }

    static {
        f12526b = new C3262m("HttpLog");
        f12527c = new C3255e("common", (byte) 12, (short) 1);
        f12528d = new C3255e("category", (byte) 11, (short) 2);
        f12529e = new C3255e("httpApi", (byte) 12, (short) 3);
        f12530f = new C3255e("passport", (byte) 12, (short) 4);
        Map enumMap = new EnumMap(C2484a.class);
        enumMap.put(C2484a.COMMON, new C3243b("common", (byte) 1, new C3247g((byte) 12, C2479a.class)));
        enumMap.put(C2484a.CATEGORY, new C3243b("category", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2484a.HTTP_API, new C3243b("httpApi", (byte) 2, new C3247g((byte) 12, C2483b.class)));
        enumMap.put(C2484a.PASSPORT, new C3243b("passport", (byte) 2, new C3247g((byte) 12, C2491f.class)));
        f12525a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2485c.class, f12525a);
    }

    public C2485c() {
        this.f12532h = C2915a.f14760f;
    }

    public C2485c m14222a(C2479a c2479a) {
        this.f12531g = c2479a;
        return this;
    }

    public C2485c m14223a(C2483b c2483b) {
        this.f12533i = c2483b;
        return this;
    }

    public C2485c m14224a(String str) {
        this.f12532h = str;
        return this;
    }

    public void m14225a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m14233e();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12531g = new C2479a();
                    this.f12531g.m14173a(c3249h);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12532h = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12533i = new C2483b();
                    this.f12533i.m14198a(c3249h);
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12534j = new C2491f();
                    this.f12534j.m14272a(c3249h);
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m14226a() {
        return this.f12531g != null;
    }

    public boolean m14227a(C2485c c2485c) {
        if (c2485c == null) {
            return false;
        }
        boolean a = m14226a();
        boolean a2 = c2485c.m14226a();
        if ((a || a2) && (!a || !a2 || !this.f12531g.m14176a(c2485c.f12531g))) {
            return false;
        }
        a = m14230b();
        a2 = c2485c.m14230b();
        if ((a || a2) && (!a || !a2 || !this.f12532h.equals(c2485c.f12532h))) {
            return false;
        }
        a = m14231c();
        a2 = c2485c.m14231c();
        if ((a || a2) && (!a || !a2 || !this.f12533i.m14200a(c2485c.f12533i))) {
            return false;
        }
        a = m14232d();
        a2 = c2485c.m14232d();
        return !(a || a2) || (a && a2 && this.f12534j.m14274a(c2485c.f12534j));
    }

    public int m14228b(C2485c c2485c) {
        if (!getClass().equals(c2485c.getClass())) {
            return getClass().getName().compareTo(c2485c.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14226a()).compareTo(Boolean.valueOf(c2485c.m14226a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14226a()) {
            compareTo = C3269c.m18089a(this.f12531g, c2485c.f12531g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14230b()).compareTo(Boolean.valueOf(c2485c.m14230b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14230b()) {
            compareTo = C3269c.m18090a(this.f12532h, c2485c.f12532h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14231c()).compareTo(Boolean.valueOf(c2485c.m14231c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14231c()) {
            compareTo = C3269c.m18089a(this.f12533i, c2485c.f12533i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14232d()).compareTo(Boolean.valueOf(c2485c.m14232d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14232d()) {
            compareTo = C3269c.m18089a(this.f12534j, c2485c.f12534j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m14229b(C3249h c3249h) {
        m14233e();
        c3249h.m17936a(f12526b);
        if (this.f12531g != null) {
            c3249h.m17932a(f12527c);
            this.f12531g.m14178b(c3249h);
            c3249h.m17938b();
        }
        if (this.f12532h != null) {
            c3249h.m17932a(f12528d);
            c3249h.m17930a(this.f12532h);
            c3249h.m17938b();
        }
        if (this.f12533i != null && m14231c()) {
            c3249h.m17932a(f12529e);
            this.f12533i.m14203b(c3249h);
            c3249h.m17938b();
        }
        if (this.f12534j != null && m14232d()) {
            c3249h.m17932a(f12530f);
            this.f12534j.m14276b(c3249h);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m14230b() {
        return this.f12532h != null;
    }

    public boolean m14231c() {
        return this.f12533i != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14228b((C2485c) obj);
    }

    public boolean m14232d() {
        return this.f12534j != null;
    }

    public void m14233e() {
        if (this.f12531g == null) {
            throw new C3259i("Required field 'common' was not present! Struct: " + toString());
        } else if (this.f12532h == null) {
            throw new C3259i("Required field 'category' was not present! Struct: " + toString());
        }
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2485c)) ? m14227a((C2485c) obj) : false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpLog(");
        stringBuilder.append("common:");
        if (this.f12531g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12531g);
        }
        stringBuilder.append(", ");
        stringBuilder.append("category:");
        if (this.f12532h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12532h);
        }
        if (m14231c()) {
            stringBuilder.append(", ");
            stringBuilder.append("httpApi:");
            if (this.f12533i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12533i);
            }
        }
        if (m14232d()) {
            stringBuilder.append(", ");
            stringBuilder.append("passport:");
            if (this.f12534j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12534j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
