package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.io.Serializable;
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
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3257g;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.o */
public class C2756o implements Serializable, Cloneable, C2478b<C2756o, C2755a> {
    public static final Map<C2755a, C3243b> f13869g;
    private static final C3262m f13870h;
    private static final C3255e f13871i;
    private static final C3255e f13872j;
    private static final C3255e f13873k;
    private static final C3255e f13874l;
    private static final C3255e f13875m;
    private static final C3255e f13876n;
    public String f13877a;
    public C2740g f13878b;
    public String f13879c;
    public String f13880d;
    public Map<String, String> f13881e;
    public String f13882f;

    /* renamed from: com.xiaomi.xmpush.thrift.o.a */
    public enum C2755a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        FEEDBACKS((short) 5, "feedbacks"),
        CATEGORY((short) 6, "category");
        
        private static final Map<String, C2755a> f13865g;
        private final short f13867h;
        private final String f13868i;

        static {
            f13865g = new HashMap();
            Iterator it = EnumSet.allOf(C2755a.class).iterator();
            while (it.hasNext()) {
                C2755a c2755a = (C2755a) it.next();
                f13865g.put(c2755a.m15653a(), c2755a);
            }
        }

        private C2755a(short s, String str) {
            this.f13867h = s;
            this.f13868i = str;
        }

        public String m15653a() {
            return this.f13868i;
        }
    }

    static {
        f13870h = new C3262m("XmPushActionSendFeedback");
        f13871i = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13872j = new C3255e("target", (byte) 12, (short) 2);
        f13873k = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13874l = new C3255e("appId", (byte) 11, (short) 4);
        f13875m = new C3255e("feedbacks", (byte) 13, (short) 5);
        f13876n = new C3255e("category", (byte) 11, (short) 6);
        Map enumMap = new EnumMap(C2755a.class);
        enumMap.put(C2755a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2755a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2755a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2755a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2755a.FEEDBACKS, new C3243b("feedbacks", (byte) 2, new C3245e((byte) 13, new C3241c((byte) 11), new C3241c((byte) 11))));
        enumMap.put(C2755a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13869g = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2756o.class, f13869g);
    }

    public void m15654a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15664g();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13877a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13878b = new C2740g();
                    this.f13878b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13879c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13880d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != 13) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3257g k = c3249h.m17947k();
                    this.f13881e = new HashMap(k.f15762c * 2);
                    for (int i2 = 0; i2 < k.f15762c; i2++) {
                        this.f13881e.put(c3249h.m17959w(), c3249h.m17959w());
                    }
                    c3249h.m17948l();
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13882f = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15655a() {
        return this.f13877a != null;
    }

    public boolean m15656a(C2756o c2756o) {
        if (c2756o == null) {
            return false;
        }
        boolean a = m15655a();
        boolean a2 = c2756o.m15655a();
        if ((a || a2) && (!a || !a2 || !this.f13877a.equals(c2756o.f13877a))) {
            return false;
        }
        a = m15659b();
        a2 = c2756o.m15659b();
        if ((a || a2) && (!a || !a2 || !this.f13878b.m15488a(c2756o.f13878b))) {
            return false;
        }
        a = m15660c();
        a2 = c2756o.m15660c();
        if ((a || a2) && (!a || !a2 || !this.f13879c.equals(c2756o.f13879c))) {
            return false;
        }
        a = m15661d();
        a2 = c2756o.m15661d();
        if ((a || a2) && (!a || !a2 || !this.f13880d.equals(c2756o.f13880d))) {
            return false;
        }
        a = m15662e();
        a2 = c2756o.m15662e();
        if ((a || a2) && (!a || !a2 || !this.f13881e.equals(c2756o.f13881e))) {
            return false;
        }
        a = m15663f();
        a2 = c2756o.m15663f();
        return !(a || a2) || (a && a2 && this.f13882f.equals(c2756o.f13882f));
    }

    public int m15657b(C2756o c2756o) {
        if (!getClass().equals(c2756o.getClass())) {
            return getClass().getName().compareTo(c2756o.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15655a()).compareTo(Boolean.valueOf(c2756o.m15655a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15655a()) {
            compareTo = C3269c.m18090a(this.f13877a, c2756o.f13877a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15659b()).compareTo(Boolean.valueOf(c2756o.m15659b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15659b()) {
            compareTo = C3269c.m18089a(this.f13878b, c2756o.f13878b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15660c()).compareTo(Boolean.valueOf(c2756o.m15660c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15660c()) {
            compareTo = C3269c.m18090a(this.f13879c, c2756o.f13879c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15661d()).compareTo(Boolean.valueOf(c2756o.m15661d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15661d()) {
            compareTo = C3269c.m18090a(this.f13880d, c2756o.f13880d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15662e()).compareTo(Boolean.valueOf(c2756o.m15662e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15662e()) {
            compareTo = C3269c.m18093a(this.f13881e, c2756o.f13881e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15663f()).compareTo(Boolean.valueOf(c2756o.m15663f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15663f()) {
            compareTo = C3269c.m18090a(this.f13882f, c2756o.f13882f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15658b(C3249h c3249h) {
        m15664g();
        c3249h.m17936a(f13870h);
        if (this.f13877a != null && m15655a()) {
            c3249h.m17932a(f13871i);
            c3249h.m17930a(this.f13877a);
            c3249h.m17938b();
        }
        if (this.f13878b != null && m15659b()) {
            c3249h.m17932a(f13872j);
            this.f13878b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13879c != null) {
            c3249h.m17932a(f13873k);
            c3249h.m17930a(this.f13879c);
            c3249h.m17938b();
        }
        if (this.f13880d != null) {
            c3249h.m17932a(f13874l);
            c3249h.m17930a(this.f13880d);
            c3249h.m17938b();
        }
        if (this.f13881e != null && m15662e()) {
            c3249h.m17932a(f13875m);
            c3249h.m17934a(new C3257g((byte) 11, (byte) 11, this.f13881e.size()));
            for (Entry entry : this.f13881e.entrySet()) {
                c3249h.m17930a((String) entry.getKey());
                c3249h.m17930a((String) entry.getValue());
            }
            c3249h.m17940d();
            c3249h.m17938b();
        }
        if (this.f13882f != null && m15663f()) {
            c3249h.m17932a(f13876n);
            c3249h.m17930a(this.f13882f);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15659b() {
        return this.f13878b != null;
    }

    public boolean m15660c() {
        return this.f13879c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15657b((C2756o) obj);
    }

    public boolean m15661d() {
        return this.f13880d != null;
    }

    public boolean m15662e() {
        return this.f13881e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2756o)) ? m15656a((C2756o) obj) : false;
    }

    public boolean m15663f() {
        return this.f13882f != null;
    }

    public void m15664g() {
        if (this.f13879c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13880d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendFeedback(");
        Object obj2 = 1;
        if (m15655a()) {
            stringBuilder.append("debug:");
            if (this.f13877a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13877a);
            }
            obj2 = null;
        }
        if (m15659b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13878b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13878b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13879c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13879c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13880d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13880d);
        }
        if (m15662e()) {
            stringBuilder.append(", ");
            stringBuilder.append("feedbacks:");
            if (this.f13881e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13881e);
            }
        }
        if (m15663f()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13882f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13882f);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
