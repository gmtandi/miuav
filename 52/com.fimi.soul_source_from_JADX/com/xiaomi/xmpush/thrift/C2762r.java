package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
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
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.r */
public class C2762r implements Serializable, Cloneable, C2478b<C2762r, C2761a> {
    public static final Map<C2761a, C3243b> f13965h;
    private static final C3262m f13966i;
    private static final C3255e f13967j;
    private static final C3255e f13968k;
    private static final C3255e f13969l;
    private static final C3255e f13970m;
    private static final C3255e f13971n;
    private static final C3255e f13972o;
    private static final C3255e f13973p;
    public String f13974a;
    public C2740g f13975b;
    public String f13976c;
    public String f13977d;
    public String f13978e;
    public String f13979f;
    public String f13980g;

    /* renamed from: com.xiaomi.xmpush.thrift.r.a */
    public enum C2761a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        TOPIC((short) 5, "topic"),
        PACKAGE_NAME((short) 6, C2537j.f12839W),
        CATEGORY((short) 7, "category");
        
        private static final Map<String, C2761a> f13961h;
        private final short f13963i;
        private final String f13964j;

        static {
            f13961h = new HashMap();
            Iterator it = EnumSet.allOf(C2761a.class).iterator();
            while (it.hasNext()) {
                C2761a c2761a = (C2761a) it.next();
                f13961h.put(c2761a.m15704a(), c2761a);
            }
        }

        private C2761a(short s, String str) {
            this.f13963i = s;
            this.f13964j = str;
        }

        public String m15704a() {
            return this.f13964j;
        }
    }

    static {
        f13966i = new C3262m("XmPushActionSubscription");
        f13967j = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13968k = new C3255e("target", (byte) 12, (short) 2);
        f13969l = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13970m = new C3255e("appId", (byte) 11, (short) 4);
        f13971n = new C3255e("topic", (byte) 11, (short) 5);
        f13972o = new C3255e(C2537j.f12839W, (byte) 11, (short) 6);
        f13973p = new C3255e("category", (byte) 11, (short) 7);
        Map enumMap = new EnumMap(C2761a.class);
        enumMap.put(C2761a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2761a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2761a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2761a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2761a.TOPIC, new C3243b("topic", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2761a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2761a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13965h = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2762r.class, f13965h);
    }

    public C2762r m15705a(String str) {
        this.f13976c = str;
        return this;
    }

    public void m15706a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15721h();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13974a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13975b = new C2740g();
                    this.f13975b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13976c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13977d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13978e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13979f = c3249h.m17959w();
                        break;
                    }
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13980g = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15707a() {
        return this.f13974a != null;
    }

    public boolean m15708a(C2762r c2762r) {
        if (c2762r == null) {
            return false;
        }
        boolean a = m15707a();
        boolean a2 = c2762r.m15707a();
        if ((a || a2) && (!a || !a2 || !this.f13974a.equals(c2762r.f13974a))) {
            return false;
        }
        a = m15712b();
        a2 = c2762r.m15712b();
        if ((a || a2) && (!a || !a2 || !this.f13975b.m15488a(c2762r.f13975b))) {
            return false;
        }
        a = m15714c();
        a2 = c2762r.m15714c();
        if ((a || a2) && (!a || !a2 || !this.f13976c.equals(c2762r.f13976c))) {
            return false;
        }
        a = m15716d();
        a2 = c2762r.m15716d();
        if ((a || a2) && (!a || !a2 || !this.f13977d.equals(c2762r.f13977d))) {
            return false;
        }
        a = m15718e();
        a2 = c2762r.m15718e();
        if ((a || a2) && (!a || !a2 || !this.f13978e.equals(c2762r.f13978e))) {
            return false;
        }
        a = m15719f();
        a2 = c2762r.m15719f();
        if ((a || a2) && (!a || !a2 || !this.f13979f.equals(c2762r.f13979f))) {
            return false;
        }
        a = m15720g();
        a2 = c2762r.m15720g();
        return !(a || a2) || (a && a2 && this.f13980g.equals(c2762r.f13980g));
    }

    public int m15709b(C2762r c2762r) {
        if (!getClass().equals(c2762r.getClass())) {
            return getClass().getName().compareTo(c2762r.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15707a()).compareTo(Boolean.valueOf(c2762r.m15707a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15707a()) {
            compareTo = C3269c.m18090a(this.f13974a, c2762r.f13974a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15712b()).compareTo(Boolean.valueOf(c2762r.m15712b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15712b()) {
            compareTo = C3269c.m18089a(this.f13975b, c2762r.f13975b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15714c()).compareTo(Boolean.valueOf(c2762r.m15714c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15714c()) {
            compareTo = C3269c.m18090a(this.f13976c, c2762r.f13976c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15716d()).compareTo(Boolean.valueOf(c2762r.m15716d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15716d()) {
            compareTo = C3269c.m18090a(this.f13977d, c2762r.f13977d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15718e()).compareTo(Boolean.valueOf(c2762r.m15718e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15718e()) {
            compareTo = C3269c.m18090a(this.f13978e, c2762r.f13978e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15719f()).compareTo(Boolean.valueOf(c2762r.m15719f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15719f()) {
            compareTo = C3269c.m18090a(this.f13979f, c2762r.f13979f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15720g()).compareTo(Boolean.valueOf(c2762r.m15720g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15720g()) {
            compareTo = C3269c.m18090a(this.f13980g, c2762r.f13980g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2762r m15710b(String str) {
        this.f13977d = str;
        return this;
    }

    public void m15711b(C3249h c3249h) {
        m15721h();
        c3249h.m17936a(f13966i);
        if (this.f13974a != null && m15707a()) {
            c3249h.m17932a(f13967j);
            c3249h.m17930a(this.f13974a);
            c3249h.m17938b();
        }
        if (this.f13975b != null && m15712b()) {
            c3249h.m17932a(f13968k);
            this.f13975b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13976c != null) {
            c3249h.m17932a(f13969l);
            c3249h.m17930a(this.f13976c);
            c3249h.m17938b();
        }
        if (this.f13977d != null) {
            c3249h.m17932a(f13970m);
            c3249h.m17930a(this.f13977d);
            c3249h.m17938b();
        }
        if (this.f13978e != null) {
            c3249h.m17932a(f13971n);
            c3249h.m17930a(this.f13978e);
            c3249h.m17938b();
        }
        if (this.f13979f != null && m15719f()) {
            c3249h.m17932a(f13972o);
            c3249h.m17930a(this.f13979f);
            c3249h.m17938b();
        }
        if (this.f13980g != null && m15720g()) {
            c3249h.m17932a(f13973p);
            c3249h.m17930a(this.f13980g);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15712b() {
        return this.f13975b != null;
    }

    public C2762r m15713c(String str) {
        this.f13978e = str;
        return this;
    }

    public boolean m15714c() {
        return this.f13976c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15709b((C2762r) obj);
    }

    public C2762r m15715d(String str) {
        this.f13979f = str;
        return this;
    }

    public boolean m15716d() {
        return this.f13977d != null;
    }

    public C2762r m15717e(String str) {
        this.f13980g = str;
        return this;
    }

    public boolean m15718e() {
        return this.f13978e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2762r)) ? m15708a((C2762r) obj) : false;
    }

    public boolean m15719f() {
        return this.f13979f != null;
    }

    public boolean m15720g() {
        return this.f13980g != null;
    }

    public void m15721h() {
        if (this.f13976c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13977d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f13978e == null) {
            throw new C3259i("Required field 'topic' was not present! Struct: " + toString());
        }
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSubscription(");
        Object obj2 = 1;
        if (m15707a()) {
            stringBuilder.append("debug:");
            if (this.f13974a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13974a);
            }
            obj2 = null;
        }
        if (m15712b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13975b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13975b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13976c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13976c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13977d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13977d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("topic:");
        if (this.f13978e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13978e);
        }
        if (m15719f()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13979f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13979f);
            }
        }
        if (m15720g()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13980g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13980g);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
