package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3245e;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3257g;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.common.logger.thrift.mfs.d */
public class C2487d implements Serializable, Cloneable, C2478b<C2487d, C2486a> {
    public static final Map<C2486a, C3243b> f12546a;
    private static final C3262m f12547b;
    private static final C3255e f12548c;
    private static final C3255e f12549d;
    private static final C3255e f12550e;
    private static final C3255e f12551f;
    private static final C3255e f12552g;
    private static final C3255e f12553h;
    private static final C3255e f12554i;
    private String f12555j;
    private int f12556k;
    private int f12557l;
    private long f12558m;
    private int f12559n;
    private Map<String, Integer> f12560o;
    private Map<Integer, Integer> f12561p;
    private BitSet f12562q;

    /* renamed from: com.xiaomi.common.logger.thrift.mfs.d.a */
    public enum C2486a {
        IP((short) 1, "ip"),
        FAILED_COUNT((short) 2, "failed_count"),
        SUCCESS_COUNT((short) 3, "success_count"),
        DURATION((short) 4, "duration"),
        SIZE((short) 5, "size"),
        EXP_INFO((short) 6, "exp_info"),
        HTTP_INFO((short) 7, "http_info");
        
        private static final Map<String, C2486a> f12542h;
        private final short f12544i;
        private final String f12545j;

        static {
            f12542h = new HashMap();
            Iterator it = EnumSet.allOf(C2486a.class).iterator();
            while (it.hasNext()) {
                C2486a c2486a = (C2486a) it.next();
                f12542h.put(c2486a.m14234a(), c2486a);
            }
        }

        private C2486a(short s, String str) {
            this.f12544i = s;
            this.f12545j = str;
        }

        public String m14234a() {
            return this.f12545j;
        }
    }

    static {
        f12547b = new C3262m("LandNodeInfo");
        f12548c = new C3255e("ip", (byte) 11, (short) 1);
        f12549d = new C3255e("failed_count", (byte) 8, (short) 2);
        f12550e = new C3255e("success_count", (byte) 8, (short) 3);
        f12551f = new C3255e("duration", (byte) 10, (short) 4);
        f12552g = new C3255e("size", (byte) 8, (short) 5);
        f12553h = new C3255e("exp_info", (byte) 13, (short) 6);
        f12554i = new C3255e("http_info", (byte) 13, (short) 7);
        Map enumMap = new EnumMap(C2486a.class);
        enumMap.put(C2486a.IP, new C3243b("ip", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2486a.FAILED_COUNT, new C3243b("failed_count", (byte) 1, new C3241c((byte) 8)));
        enumMap.put(C2486a.SUCCESS_COUNT, new C3243b("success_count", (byte) 1, new C3241c((byte) 8)));
        enumMap.put(C2486a.DURATION, new C3243b("duration", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2486a.SIZE, new C3243b("size", (byte) 1, new C3241c((byte) 8)));
        enumMap.put(C2486a.EXP_INFO, new C3243b("exp_info", (byte) 2, new C3245e((byte) 13, new C3241c((byte) 11), new C3241c((byte) 8))));
        enumMap.put(C2486a.HTTP_INFO, new C3243b("http_info", (byte) 2, new C3245e((byte) 13, new C3241c((byte) 8), new C3241c((byte) 8))));
        f12546a = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2487d.class, f12546a);
    }

    public C2487d() {
        this.f12562q = new BitSet(4);
    }

    public C2487d m14235a(int i) {
        this.f12556k = i;
        m14240a(true);
        return this;
    }

    public C2487d m14236a(long j) {
        this.f12558m = j;
        m14249c(true);
        return this;
    }

    public C2487d m14237a(String str) {
        this.f12555j = str;
        return this;
    }

    public C2487d m14238a(Map<String, Integer> map) {
        this.f12560o = map;
        return this;
    }

    public void m14239a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (!m14247b()) {
                    throw new C3259i("Required field 'failed_count' was not found in serialized data! Struct: " + toString());
                } else if (!m14250c()) {
                    throw new C3259i("Required field 'success_count' was not found in serialized data! Struct: " + toString());
                } else if (!m14252d()) {
                    throw new C3259i("Required field 'duration' was not found in serialized data! Struct: " + toString());
                } else if (m14253e()) {
                    m14256h();
                    return;
                } else {
                    throw new C3259i("Required field 'size' was not found in serialized data! Struct: " + toString());
                }
            }
            C3257g k;
            int i2;
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f12555j = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12556k = c3249h.m17956t();
                    m14240a(true);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12557l = c3249h.m17956t();
                    m14246b(true);
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12558m = c3249h.m17957u();
                    m14249c(true);
                    break;
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f12559n = c3249h.m17956t();
                    m14251d(true);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 13) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    k = c3249h.m17947k();
                    this.f12560o = new HashMap(k.f15762c * 2);
                    for (i2 = 0; i2 < k.f15762c; i2++) {
                        this.f12560o.put(c3249h.m17959w(), Integer.valueOf(c3249h.m17956t()));
                    }
                    c3249h.m17948l();
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 13) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    k = c3249h.m17947k();
                    this.f12561p = new HashMap(k.f15762c * 2);
                    for (i2 = 0; i2 < k.f15762c; i2++) {
                        this.f12561p.put(Integer.valueOf(c3249h.m17956t()), Integer.valueOf(c3249h.m17956t()));
                    }
                    c3249h.m17948l();
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m14240a(boolean z) {
        this.f12562q.set(0, z);
    }

    public boolean m14241a() {
        return this.f12555j != null;
    }

    public boolean m14242a(C2487d c2487d) {
        if (c2487d == null) {
            return false;
        }
        boolean a = m14241a();
        boolean a2 = c2487d.m14241a();
        if (((a || a2) && (!a || !a2 || !this.f12555j.equals(c2487d.f12555j))) || this.f12556k != c2487d.f12556k || this.f12557l != c2487d.f12557l || this.f12558m != c2487d.f12558m || this.f12559n != c2487d.f12559n) {
            return false;
        }
        a = m14254f();
        a2 = c2487d.m14254f();
        if ((a || a2) && (!a || !a2 || !this.f12560o.equals(c2487d.f12560o))) {
            return false;
        }
        a = m14255g();
        a2 = c2487d.m14255g();
        return !(a || a2) || (a && a2 && this.f12561p.equals(c2487d.f12561p));
    }

    public int m14243b(C2487d c2487d) {
        if (!getClass().equals(c2487d.getClass())) {
            return getClass().getName().compareTo(c2487d.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m14241a()).compareTo(Boolean.valueOf(c2487d.m14241a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14241a()) {
            compareTo = C3269c.m18090a(this.f12555j, c2487d.f12555j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14247b()).compareTo(Boolean.valueOf(c2487d.m14247b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14247b()) {
            compareTo = C3269c.m18087a(this.f12556k, c2487d.f12556k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14250c()).compareTo(Boolean.valueOf(c2487d.m14250c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14250c()) {
            compareTo = C3269c.m18087a(this.f12557l, c2487d.f12557l);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14252d()).compareTo(Boolean.valueOf(c2487d.m14252d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14252d()) {
            compareTo = C3269c.m18088a(this.f12558m, c2487d.f12558m);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14253e()).compareTo(Boolean.valueOf(c2487d.m14253e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14253e()) {
            compareTo = C3269c.m18087a(this.f12559n, c2487d.f12559n);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14254f()).compareTo(Boolean.valueOf(c2487d.m14254f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14254f()) {
            compareTo = C3269c.m18093a(this.f12560o, c2487d.f12560o);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m14255g()).compareTo(Boolean.valueOf(c2487d.m14255g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m14255g()) {
            compareTo = C3269c.m18093a(this.f12561p, c2487d.f12561p);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2487d m14244b(int i) {
        this.f12557l = i;
        m14246b(true);
        return this;
    }

    public void m14245b(C3249h c3249h) {
        m14256h();
        c3249h.m17936a(f12547b);
        if (this.f12555j != null) {
            c3249h.m17932a(f12548c);
            c3249h.m17930a(this.f12555j);
            c3249h.m17938b();
        }
        c3249h.m17932a(f12549d);
        c3249h.m17928a(this.f12556k);
        c3249h.m17938b();
        c3249h.m17932a(f12550e);
        c3249h.m17928a(this.f12557l);
        c3249h.m17938b();
        c3249h.m17932a(f12551f);
        c3249h.m17929a(this.f12558m);
        c3249h.m17938b();
        c3249h.m17932a(f12552g);
        c3249h.m17928a(this.f12559n);
        c3249h.m17938b();
        if (this.f12560o != null && m14254f()) {
            c3249h.m17932a(f12553h);
            c3249h.m17934a(new C3257g((byte) 11, (byte) 8, this.f12560o.size()));
            for (Entry entry : this.f12560o.entrySet()) {
                c3249h.m17930a((String) entry.getKey());
                c3249h.m17928a(((Integer) entry.getValue()).intValue());
            }
            c3249h.m17940d();
            c3249h.m17938b();
        }
        if (this.f12561p != null && m14255g()) {
            c3249h.m17932a(f12554i);
            c3249h.m17934a(new C3257g((byte) 8, (byte) 8, this.f12561p.size()));
            for (Entry entry2 : this.f12561p.entrySet()) {
                c3249h.m17928a(((Integer) entry2.getKey()).intValue());
                c3249h.m17928a(((Integer) entry2.getValue()).intValue());
            }
            c3249h.m17940d();
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m14246b(boolean z) {
        this.f12562q.set(1, z);
    }

    public boolean m14247b() {
        return this.f12562q.get(0);
    }

    public C2487d m14248c(int i) {
        this.f12559n = i;
        m14251d(true);
        return this;
    }

    public void m14249c(boolean z) {
        this.f12562q.set(2, z);
    }

    public boolean m14250c() {
        return this.f12562q.get(1);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m14243b((C2487d) obj);
    }

    public void m14251d(boolean z) {
        this.f12562q.set(3, z);
    }

    public boolean m14252d() {
        return this.f12562q.get(2);
    }

    public boolean m14253e() {
        return this.f12562q.get(3);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2487d)) ? m14242a((C2487d) obj) : false;
    }

    public boolean m14254f() {
        return this.f12560o != null;
    }

    public boolean m14255g() {
        return this.f12561p != null;
    }

    public void m14256h() {
        if (this.f12555j == null) {
            throw new C3259i("Required field 'ip' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LandNodeInfo(");
        stringBuilder.append("ip:");
        if (this.f12555j == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f12555j);
        }
        stringBuilder.append(", ");
        stringBuilder.append("failed_count:");
        stringBuilder.append(this.f12556k);
        stringBuilder.append(", ");
        stringBuilder.append("success_count:");
        stringBuilder.append(this.f12557l);
        stringBuilder.append(", ");
        stringBuilder.append("duration:");
        stringBuilder.append(this.f12558m);
        stringBuilder.append(", ");
        stringBuilder.append("size:");
        stringBuilder.append(this.f12559n);
        if (m14254f()) {
            stringBuilder.append(", ");
            stringBuilder.append("exp_info:");
            if (this.f12560o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12560o);
            }
        }
        if (m14255g()) {
            stringBuilder.append(", ");
            stringBuilder.append("http_info:");
            if (this.f12561p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f12561p);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
