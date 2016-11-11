package com.xiaomi.xmpush.thrift;

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
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.g */
public class C2740g implements Serializable, Cloneable, C2478b<C2740g, C2739a> {
    public static final Map<C2739a, C3243b> f13598f;
    private static final C3262m f13599g;
    private static final C3255e f13600h;
    private static final C3255e f13601i;
    private static final C3255e f13602j;
    private static final C3255e f13603k;
    private static final C3255e f13604l;
    public long f13605a;
    public String f13606b;
    public String f13607c;
    public String f13608d;
    public boolean f13609e;
    private BitSet f13610m;

    /* renamed from: com.xiaomi.xmpush.thrift.g.a */
    public enum C2739a {
        CHANNEL_ID((short) 1, "channelId"),
        USER_ID((short) 2, "userId"),
        SERVER((short) 3, "server"),
        RESOURCE((short) 4, "resource"),
        IS_PREVIEW((short) 5, "isPreview");
        
        private static final Map<String, C2739a> f13594f;
        private final short f13596g;
        private final String f13597h;

        static {
            f13594f = new HashMap();
            Iterator it = EnumSet.allOf(C2739a.class).iterator();
            while (it.hasNext()) {
                C2739a c2739a = (C2739a) it.next();
                f13594f.put(c2739a.m15484a(), c2739a);
            }
        }

        private C2739a(short s, String str) {
            this.f13596g = s;
            this.f13597h = str;
        }

        public String m15484a() {
            return this.f13597h;
        }
    }

    static {
        f13599g = new C3262m("Target");
        f13600h = new C3255e("channelId", (byte) 10, (short) 1);
        f13601i = new C3255e("userId", (byte) 11, (short) 2);
        f13602j = new C3255e("server", (byte) 11, (short) 3);
        f13603k = new C3255e("resource", (byte) 11, (short) 4);
        f13604l = new C3255e("isPreview", (byte) 2, (short) 5);
        Map enumMap = new EnumMap(C2739a.class);
        enumMap.put(C2739a.CHANNEL_ID, new C3243b("channelId", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2739a.USER_ID, new C3243b("userId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2739a.SERVER, new C3243b("server", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2739a.RESOURCE, new C3243b("resource", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2739a.IS_PREVIEW, new C3243b("isPreview", (byte) 2, new C3241c((byte) 2)));
        f13598f = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2740g.class, f13598f);
    }

    public C2740g() {
        this.f13610m = new BitSet(2);
        this.f13605a = 5;
        this.f13607c = "xiaomi.com";
        this.f13608d = C2915a.f14760f;
        this.f13609e = false;
    }

    public void m15485a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15487a()) {
                    m15496f();
                    return;
                }
                throw new C3259i("Required field 'channelId' was not found in serialized data! Struct: " + toString());
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13605a = c3249h.m17957u();
                    m15486a(true);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13606b = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13607c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13608d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != 2) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13609e = c3249h.m17953q();
                    m15491b(true);
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15486a(boolean z) {
        this.f13610m.set(0, z);
    }

    public boolean m15487a() {
        return this.f13610m.get(0);
    }

    public boolean m15488a(C2740g c2740g) {
        if (c2740g == null || this.f13605a != c2740g.f13605a) {
            return false;
        }
        boolean b = m15492b();
        boolean b2 = c2740g.m15492b();
        if ((b || b2) && (!b || !b2 || !this.f13606b.equals(c2740g.f13606b))) {
            return false;
        }
        b = m15493c();
        b2 = c2740g.m15493c();
        if ((b || b2) && (!b || !b2 || !this.f13607c.equals(c2740g.f13607c))) {
            return false;
        }
        b = m15494d();
        b2 = c2740g.m15494d();
        if ((b || b2) && (!b || !b2 || !this.f13608d.equals(c2740g.f13608d))) {
            return false;
        }
        b = m15495e();
        b2 = c2740g.m15495e();
        return !(b || b2) || (b && b2 && this.f13609e == c2740g.f13609e);
    }

    public int m15489b(C2740g c2740g) {
        if (!getClass().equals(c2740g.getClass())) {
            return getClass().getName().compareTo(c2740g.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15487a()).compareTo(Boolean.valueOf(c2740g.m15487a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15487a()) {
            compareTo = C3269c.m18088a(this.f13605a, c2740g.f13605a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15492b()).compareTo(Boolean.valueOf(c2740g.m15492b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15492b()) {
            compareTo = C3269c.m18090a(this.f13606b, c2740g.f13606b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15493c()).compareTo(Boolean.valueOf(c2740g.m15493c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15493c()) {
            compareTo = C3269c.m18090a(this.f13607c, c2740g.f13607c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15494d()).compareTo(Boolean.valueOf(c2740g.m15494d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15494d()) {
            compareTo = C3269c.m18090a(this.f13608d, c2740g.f13608d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15495e()).compareTo(Boolean.valueOf(c2740g.m15495e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15495e()) {
            compareTo = C3269c.m18095a(this.f13609e, c2740g.f13609e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15490b(C3249h c3249h) {
        m15496f();
        c3249h.m17936a(f13599g);
        c3249h.m17932a(f13600h);
        c3249h.m17929a(this.f13605a);
        c3249h.m17938b();
        if (this.f13606b != null) {
            c3249h.m17932a(f13601i);
            c3249h.m17930a(this.f13606b);
            c3249h.m17938b();
        }
        if (this.f13607c != null && m15493c()) {
            c3249h.m17932a(f13602j);
            c3249h.m17930a(this.f13607c);
            c3249h.m17938b();
        }
        if (this.f13608d != null && m15494d()) {
            c3249h.m17932a(f13603k);
            c3249h.m17930a(this.f13608d);
            c3249h.m17938b();
        }
        if (m15495e()) {
            c3249h.m17932a(f13604l);
            c3249h.m17937a(this.f13609e);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m15491b(boolean z) {
        this.f13610m.set(1, z);
    }

    public boolean m15492b() {
        return this.f13606b != null;
    }

    public boolean m15493c() {
        return this.f13607c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15489b((C2740g) obj);
    }

    public boolean m15494d() {
        return this.f13608d != null;
    }

    public boolean m15495e() {
        return this.f13610m.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2740g)) ? m15488a((C2740g) obj) : false;
    }

    public void m15496f() {
        if (this.f13606b == null) {
            throw new C3259i("Required field 'userId' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Target(");
        stringBuilder.append("channelId:");
        stringBuilder.append(this.f13605a);
        stringBuilder.append(", ");
        stringBuilder.append("userId:");
        if (this.f13606b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13606b);
        }
        if (m15493c()) {
            stringBuilder.append(", ");
            stringBuilder.append("server:");
            if (this.f13607c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13607c);
            }
        }
        if (m15494d()) {
            stringBuilder.append(", ");
            stringBuilder.append("resource:");
            if (this.f13608d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13608d);
            }
        }
        if (m15495e()) {
            stringBuilder.append(", ");
            stringBuilder.append("isPreview:");
            stringBuilder.append(this.f13609e);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
