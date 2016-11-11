package com.xiaomi.common.logger.thrift;

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
import org.p122a.p123a.C2915a;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.common.logger.thrift.a */
public class C2479a implements Serializable, Cloneable, C2478b<C2479a, C2477a> {
    public static final Map<C2477a, C3243b> f12456f;
    private static final C3262m f12457g;
    private static final C3255e f12458h;
    private static final C3255e f12459i;
    private static final C3255e f12460j;
    private static final C3255e f12461k;
    private static final C3255e f12462l;
    public long f12463a;
    public String f12464b;
    public String f12465c;
    public String f12466d;
    public String f12467e;
    private BitSet f12468m;

    /* renamed from: com.xiaomi.common.logger.thrift.a.a */
    public enum C2477a {
        UUID((short) 1, "uuid"),
        TIME((short) 2, "time"),
        CLIENT_IP((short) 3, "clientIp"),
        SERVER_IP((short) 4, "serverIp"),
        SERVER_HOST((short) 5, "serverHost");
        
        private static final Map<String, C2477a> f12452f;
        private final short f12454g;
        private final String f12455h;

        static {
            f12452f = new HashMap();
            Iterator it = EnumSet.allOf(C2477a.class).iterator();
            while (it.hasNext()) {
                C2477a c2477a = (C2477a) it.next();
                f12452f.put(c2477a.m14170a(), c2477a);
            }
        }

        private C2477a(short s, String str) {
            this.f12454g = s;
            this.f12455h = str;
        }

        public String m14170a() {
            return this.f12455h;
        }
    }

    static {
        f12457g = new C3262m("Common");
        f12458h = new C3255e("uuid", (byte) 10, (short) 1);
        f12459i = new C3255e("time", (byte) 11, (short) 2);
        f12460j = new C3255e("clientIp", (byte) 11, (short) 3);
        f12461k = new C3255e("serverIp", (byte) 11, (short) 4);
        f12462l = new C3255e("serverHost", (byte) 11, (short) 5);
        Map enumMap = new EnumMap(C2477a.class);
        enumMap.put(C2477a.UUID, new C3243b("uuid", (byte) 2, new C3241c((byte) 10)));
        enumMap.put(C2477a.TIME, new C3243b("time", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2477a.CLIENT_IP, new C3243b("clientIp", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2477a.SERVER_IP, new C3243b("serverIp", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2477a.SERVER_HOST, new C3243b("serverHost", (byte) 2, new C3241c((byte) 11)));
        f12456f = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2479a.class, f12456f);
    }

    public C2479a() {
        this.f12468m = new BitSet(1);
        this.f12463a = 0;
        this.f12464b = C2915a.f14760f;
        this.f12465c = C2915a.f14760f;
        this.f12466d = C2915a.f14760f;
        this.f12467e = C2915a.f14760f;
    }

    public void m14173a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m14183f();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12463a = c3249h.m17957u();
                    m14174a(true);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12464b = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12465c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12466d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12467e = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m14174a(boolean z) {
        this.f12468m.set(0, z);
    }

    public boolean m14175a() {
        return this.f12468m.get(0);
    }

    public boolean m14176a(C2479a c2479a) {
        if (c2479a == null) {
            return false;
        }
        boolean a = m14175a();
        boolean a2 = c2479a.m14175a();
        if ((a || a2) && (!a || !a2 || this.f12463a != c2479a.f12463a)) {
            return false;
        }
        a = m14179b();
        a2 = c2479a.m14179b();
        if ((a || a2) && (!a || !a2 || !this.f12464b.equals(c2479a.f12464b))) {
            return false;
        }
        a = m14180c();
        a2 = c2479a.m14180c();
        if ((a || a2) && (!a || !a2 || !this.f12465c.equals(c2479a.f12465c))) {
            return false;
        }
        a = m14181d();
        a2 = c2479a.m14181d();
        if ((a || a2) && (!a || !a2 || !this.f12466d.equals(c2479a.f12466d))) {
            return false;
        }
        a = m14182e();
        a2 = c2479a.m14182e();
        return !(a || a2) || (a && a2 && this.f12467e.equals(c2479a.f12467e));
    }

    public int m14177b(C2479a c2479a) {
        if (!getClass().equals(c2479a.getClass())) {
            return getClass().getName().compareTo(c2479a.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14175a()).compareTo(Boolean.valueOf(c2479a.m14175a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14175a()) {
            compareTo = C3269c.m18088a(this.f12463a, c2479a.f12463a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14179b()).compareTo(Boolean.valueOf(c2479a.m14179b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14179b()) {
            compareTo = C3269c.m18090a(this.f12464b, c2479a.f12464b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14180c()).compareTo(Boolean.valueOf(c2479a.m14180c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14180c()) {
            compareTo = C3269c.m18090a(this.f12465c, c2479a.f12465c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14181d()).compareTo(Boolean.valueOf(c2479a.m14181d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14181d()) {
            compareTo = C3269c.m18090a(this.f12466d, c2479a.f12466d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14182e()).compareTo(Boolean.valueOf(c2479a.m14182e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14182e()) {
            compareTo = C3269c.m18090a(this.f12467e, c2479a.f12467e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m14178b(C3249h c3249h) {
        m14183f();
        c3249h.m17936a(f12457g);
        if (m14175a()) {
            c3249h.m17932a(f12458h);
            c3249h.m17929a(this.f12463a);
            c3249h.m17938b();
        }
        if (this.f12464b != null && m14179b()) {
            c3249h.m17932a(f12459i);
            c3249h.m17930a(this.f12464b);
            c3249h.m17938b();
        }
        if (this.f12465c != null && m14180c()) {
            c3249h.m17932a(f12460j);
            c3249h.m17930a(this.f12465c);
            c3249h.m17938b();
        }
        if (this.f12466d != null && m14181d()) {
            c3249h.m17932a(f12461k);
            c3249h.m17930a(this.f12466d);
            c3249h.m17938b();
        }
        if (this.f12467e != null && m14182e()) {
            c3249h.m17932a(f12462l);
            c3249h.m17930a(this.f12467e);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m14179b() {
        return this.f12464b != null;
    }

    public boolean m14180c() {
        return this.f12465c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14177b((C2479a) obj);
    }

    public boolean m14181d() {
        return this.f12466d != null;
    }

    public boolean m14182e() {
        return this.f12467e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2479a)) ? m14176a((C2479a) obj) : false;
    }

    public void m14183f() {
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("Common(");
        Object obj2 = 1;
        if (m14175a()) {
            stringBuilder.append("uuid:");
            stringBuilder.append(this.f12463a);
            obj2 = null;
        }
        if (m14179b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("time:");
            if (this.f12464b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12464b);
            }
            obj2 = null;
        }
        if (m14180c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("clientIp:");
            if (this.f12465c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12465c);
            }
            obj2 = null;
        }
        if (m14181d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("serverIp:");
            if (this.f12466d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12466d);
            }
        } else {
            obj = obj2;
        }
        if (m14182e()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("serverHost:");
            if (this.f12467e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12467e);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
