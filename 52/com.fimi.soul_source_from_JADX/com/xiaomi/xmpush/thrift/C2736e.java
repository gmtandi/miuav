package com.xiaomi.xmpush.thrift;

import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import com.xiaomi.market.sdk.C2537j;
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

/* renamed from: com.xiaomi.xmpush.thrift.e */
public class C2736e implements Serializable, Cloneable, C2478b<C2736e, C2735a> {
    public static final Map<C2735a, C3243b> f13561f;
    private static final C3262m f13562g;
    private static final C3255e f13563h;
    private static final C3255e f13564i;
    private static final C3255e f13565j;
    private static final C3255e f13566k;
    private static final C3255e f13567l;
    public byte f13568a;
    public int f13569b;
    public int f13570c;
    public String f13571d;
    public String f13572e;
    private BitSet f13573m;

    /* renamed from: com.xiaomi.xmpush.thrift.e.a */
    public enum C2735a {
        CHID((short) 1, "chid"),
        TYPE((short) 2, SocialConstants.PARAM_TYPE),
        VALUE((short) 3, SharedPref.VALUE),
        CONNPT((short) 4, "connpt"),
        HOST((short) 5, C2537j.HOST);
        
        private static final Map<String, C2735a> f13557f;
        private final short f13559g;
        private final String f13560h;

        static {
            f13557f = new HashMap();
            Iterator it = EnumSet.allOf(C2735a.class).iterator();
            while (it.hasNext()) {
                C2735a c2735a = (C2735a) it.next();
                f13557f.put(c2735a.m15460a(), c2735a);
            }
        }

        private C2735a(short s, String str) {
            this.f13559g = s;
            this.f13560h = str;
        }

        public String m15460a() {
            return this.f13560h;
        }
    }

    static {
        f13562g = new C3262m("StatsEvent");
        f13563h = new C3255e("chid", (byte) 3, (short) 1);
        f13564i = new C3255e(SocialConstants.PARAM_TYPE, (byte) 8, (short) 2);
        f13565j = new C3255e(SharedPref.VALUE, (byte) 8, (short) 3);
        f13566k = new C3255e("connpt", (byte) 11, (short) 4);
        f13567l = new C3255e(C2537j.HOST, (byte) 11, (short) 5);
        Map enumMap = new EnumMap(C2735a.class);
        enumMap.put(C2735a.CHID, new C3243b("chid", (byte) 1, new C3241c((byte) 3)));
        enumMap.put(C2735a.TYPE, new C3243b(SocialConstants.PARAM_TYPE, (byte) 1, new C3241c((byte) 8)));
        enumMap.put(C2735a.VALUE, new C3243b(SharedPref.VALUE, (byte) 1, new C3241c((byte) 8)));
        enumMap.put(C2735a.CONNPT, new C3243b("connpt", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2735a.HOST, new C3243b(C2537j.HOST, (byte) 2, new C3241c((byte) 11)));
        f13561f = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2736e.class, f13561f);
    }

    public C2736e() {
        this.f13573m = new BitSet(3);
    }

    public void m15461a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (!m15463a()) {
                    throw new C3259i("Required field 'chid' was not found in serialized data! Struct: " + toString());
                } else if (!m15468b()) {
                    throw new C3259i("Required field 'type' was not found in serialized data! Struct: " + toString());
                } else if (m15470c()) {
                    m15473f();
                    return;
                } else {
                    throw new C3259i("Required field 'value' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 3) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13568a = c3249h.m17954r();
                    m15462a(true);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13569b = c3249h.m17956t();
                    m15467b(true);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13570c = c3249h.m17956t();
                    m15469c(true);
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13571d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13572e = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15462a(boolean z) {
        this.f13573m.set(0, z);
    }

    public boolean m15463a() {
        return this.f13573m.get(0);
    }

    public boolean m15464a(C2736e c2736e) {
        if (c2736e == null || this.f13568a != c2736e.f13568a || this.f13569b != c2736e.f13569b || this.f13570c != c2736e.f13570c) {
            return false;
        }
        boolean d = m15471d();
        boolean d2 = c2736e.m15471d();
        if ((d || d2) && (!d || !d2 || !this.f13571d.equals(c2736e.f13571d))) {
            return false;
        }
        d = m15472e();
        d2 = c2736e.m15472e();
        return !(d || d2) || (d && d2 && this.f13572e.equals(c2736e.f13572e));
    }

    public int m15465b(C2736e c2736e) {
        if (!getClass().equals(c2736e.getClass())) {
            return getClass().getName().compareTo(c2736e.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15463a()).compareTo(Boolean.valueOf(c2736e.m15463a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15463a()) {
            compareTo = C3269c.m18086a(this.f13568a, c2736e.f13568a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15468b()).compareTo(Boolean.valueOf(c2736e.m15468b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15468b()) {
            compareTo = C3269c.m18087a(this.f13569b, c2736e.f13569b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15470c()).compareTo(Boolean.valueOf(c2736e.m15470c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15470c()) {
            compareTo = C3269c.m18087a(this.f13570c, c2736e.f13570c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15471d()).compareTo(Boolean.valueOf(c2736e.m15471d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15471d()) {
            compareTo = C3269c.m18090a(this.f13571d, c2736e.f13571d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15472e()).compareTo(Boolean.valueOf(c2736e.m15472e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15472e()) {
            compareTo = C3269c.m18090a(this.f13572e, c2736e.f13572e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15466b(C3249h c3249h) {
        m15473f();
        c3249h.m17936a(f13562g);
        c3249h.m17932a(f13563h);
        c3249h.m17927a(this.f13568a);
        c3249h.m17938b();
        c3249h.m17932a(f13564i);
        c3249h.m17928a(this.f13569b);
        c3249h.m17938b();
        c3249h.m17932a(f13565j);
        c3249h.m17928a(this.f13570c);
        c3249h.m17938b();
        if (this.f13571d != null) {
            c3249h.m17932a(f13566k);
            c3249h.m17930a(this.f13571d);
            c3249h.m17938b();
        }
        if (this.f13572e != null && m15472e()) {
            c3249h.m17932a(f13567l);
            c3249h.m17930a(this.f13572e);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m15467b(boolean z) {
        this.f13573m.set(1, z);
    }

    public boolean m15468b() {
        return this.f13573m.get(1);
    }

    public void m15469c(boolean z) {
        this.f13573m.set(2, z);
    }

    public boolean m15470c() {
        return this.f13573m.get(2);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15465b((C2736e) obj);
    }

    public boolean m15471d() {
        return this.f13571d != null;
    }

    public boolean m15472e() {
        return this.f13572e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2736e)) ? m15464a((C2736e) obj) : false;
    }

    public void m15473f() {
        if (this.f13571d == null) {
            throw new C3259i("Required field 'connpt' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("StatsEvent(");
        stringBuilder.append("chid:");
        stringBuilder.append(this.f13568a);
        stringBuilder.append(", ");
        stringBuilder.append("type:");
        stringBuilder.append(this.f13569b);
        stringBuilder.append(", ");
        stringBuilder.append("value:");
        stringBuilder.append(this.f13570c);
        stringBuilder.append(", ");
        stringBuilder.append("connpt:");
        if (this.f13571d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13571d);
        }
        if (m15472e()) {
            stringBuilder.append(", ");
            stringBuilder.append("host:");
            if (this.f13572e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13572e);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
