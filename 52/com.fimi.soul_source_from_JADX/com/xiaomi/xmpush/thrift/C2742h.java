package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
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
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.h */
public class C2742h implements Serializable, Cloneable, C2478b<C2742h, C2741a> {
    public static final Map<C2741a, C3243b> f13625k;
    private static final C3262m f13626l;
    private static final C3255e f13627m;
    private static final C3255e f13628n;
    private static final C3255e f13629o;
    private static final C3255e f13630p;
    private static final C3255e f13631q;
    private static final C3255e f13632r;
    private static final C3255e f13633s;
    private static final C3255e f13634t;
    private static final C3255e f13635u;
    private static final C3255e f13636v;
    public String f13637a;
    public C2740g f13638b;
    public String f13639c;
    public String f13640d;
    public long f13641e;
    public String f13642f;
    public String f13643g;
    public C2760q f13644h;
    public String f13645i;
    public String f13646j;
    private BitSet f13647w;

    /* renamed from: com.xiaomi.xmpush.thrift.h.a */
    public enum C2741a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        MESSAGE_TS((short) 5, "messageTs"),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        REQUEST((short) 8, SocialConstants.TYPE_REQUEST),
        PACKAGE_NAME((short) 9, C2537j.f12839W),
        CATEGORY((short) 10, "category");
        
        private static final Map<String, C2741a> f13621k;
        private final short f13623l;
        private final String f13624m;

        static {
            f13621k = new HashMap();
            Iterator it = EnumSet.allOf(C2741a.class).iterator();
            while (it.hasNext()) {
                C2741a c2741a = (C2741a) it.next();
                f13621k.put(c2741a.m15497a(), c2741a);
            }
        }

        private C2741a(short s, String str) {
            this.f13623l = s;
            this.f13624m = str;
        }

        public String m15497a() {
            return this.f13624m;
        }
    }

    static {
        f13626l = new C3262m("XmPushActionAckMessage");
        f13627m = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13628n = new C3255e("target", (byte) 12, (short) 2);
        f13629o = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13630p = new C3255e("appId", (byte) 11, (short) 4);
        f13631q = new C3255e("messageTs", (byte) 10, (short) 5);
        f13632r = new C3255e("topic", (byte) 11, (short) 6);
        f13633s = new C3255e("aliasName", (byte) 11, (short) 7);
        f13634t = new C3255e(SocialConstants.TYPE_REQUEST, (byte) 12, (short) 8);
        f13635u = new C3255e(C2537j.f12839W, (byte) 11, (short) 9);
        f13636v = new C3255e("category", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2741a.class);
        enumMap.put(C2741a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2741a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2741a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2741a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2741a.MESSAGE_TS, new C3243b("messageTs", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2741a.TOPIC, new C3243b("topic", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2741a.ALIAS_NAME, new C3243b("aliasName", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2741a.REQUEST, new C3243b(SocialConstants.TYPE_REQUEST, (byte) 2, new C3247g((byte) 12, C2760q.class)));
        enumMap.put(C2741a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2741a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13625k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2742h.class, f13625k);
    }

    public C2742h() {
        this.f13647w = new BitSet(1);
    }

    public C2742h m15498a(long j) {
        this.f13641e = j;
        m15501a(true);
        return this;
    }

    public C2742h m15499a(String str) {
        this.f13639c = str;
        return this;
    }

    public void m15500a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15512e()) {
                    m15518k();
                    return;
                }
                throw new C3259i("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13637a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13638b = new C2740g();
                    this.f13638b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13639c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13640d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13641e = c3249h.m17957u();
                    m15501a(true);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13642f = c3249h.m17959w();
                        break;
                    }
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13643g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13644h = new C2760q();
                    this.f13644h.m15681a(c3249h);
                    break;
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13645i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13646j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15501a(boolean z) {
        this.f13647w.set(0, z);
    }

    public boolean m15502a() {
        return this.f13637a != null;
    }

    public boolean m15503a(C2742h c2742h) {
        if (c2742h == null) {
            return false;
        }
        boolean a = m15502a();
        boolean a2 = c2742h.m15502a();
        if ((a || a2) && (!a || !a2 || !this.f13637a.equals(c2742h.f13637a))) {
            return false;
        }
        a = m15507b();
        a2 = c2742h.m15507b();
        if ((a || a2) && (!a || !a2 || !this.f13638b.m15488a(c2742h.f13638b))) {
            return false;
        }
        a = m15509c();
        a2 = c2742h.m15509c();
        if ((a || a2) && (!a || !a2 || !this.f13639c.equals(c2742h.f13639c))) {
            return false;
        }
        a = m15511d();
        a2 = c2742h.m15511d();
        if (((a || a2) && (!a || !a2 || !this.f13640d.equals(c2742h.f13640d))) || this.f13641e != c2742h.f13641e) {
            return false;
        }
        a = m15513f();
        a2 = c2742h.m15513f();
        if ((a || a2) && (!a || !a2 || !this.f13642f.equals(c2742h.f13642f))) {
            return false;
        }
        a = m15514g();
        a2 = c2742h.m15514g();
        if ((a || a2) && (!a || !a2 || !this.f13643g.equals(c2742h.f13643g))) {
            return false;
        }
        a = m15515h();
        a2 = c2742h.m15515h();
        if ((a || a2) && (!a || !a2 || !this.f13644h.m15684a(c2742h.f13644h))) {
            return false;
        }
        a = m15516i();
        a2 = c2742h.m15516i();
        if ((a || a2) && (!a || !a2 || !this.f13645i.equals(c2742h.f13645i))) {
            return false;
        }
        a = m15517j();
        a2 = c2742h.m15517j();
        return !(a || a2) || (a && a2 && this.f13646j.equals(c2742h.f13646j));
    }

    public int m15504b(C2742h c2742h) {
        if (!getClass().equals(c2742h.getClass())) {
            return getClass().getName().compareTo(c2742h.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15502a()).compareTo(Boolean.valueOf(c2742h.m15502a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15502a()) {
            compareTo = C3269c.m18090a(this.f13637a, c2742h.f13637a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15507b()).compareTo(Boolean.valueOf(c2742h.m15507b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15507b()) {
            compareTo = C3269c.m18089a(this.f13638b, c2742h.f13638b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15509c()).compareTo(Boolean.valueOf(c2742h.m15509c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15509c()) {
            compareTo = C3269c.m18090a(this.f13639c, c2742h.f13639c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15511d()).compareTo(Boolean.valueOf(c2742h.m15511d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15511d()) {
            compareTo = C3269c.m18090a(this.f13640d, c2742h.f13640d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15512e()).compareTo(Boolean.valueOf(c2742h.m15512e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15512e()) {
            compareTo = C3269c.m18088a(this.f13641e, c2742h.f13641e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15513f()).compareTo(Boolean.valueOf(c2742h.m15513f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15513f()) {
            compareTo = C3269c.m18090a(this.f13642f, c2742h.f13642f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15514g()).compareTo(Boolean.valueOf(c2742h.m15514g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15514g()) {
            compareTo = C3269c.m18090a(this.f13643g, c2742h.f13643g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15515h()).compareTo(Boolean.valueOf(c2742h.m15515h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15515h()) {
            compareTo = C3269c.m18089a(this.f13644h, c2742h.f13644h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15516i()).compareTo(Boolean.valueOf(c2742h.m15516i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15516i()) {
            compareTo = C3269c.m18090a(this.f13645i, c2742h.f13645i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15517j()).compareTo(Boolean.valueOf(c2742h.m15517j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15517j()) {
            compareTo = C3269c.m18090a(this.f13646j, c2742h.f13646j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2742h m15505b(String str) {
        this.f13640d = str;
        return this;
    }

    public void m15506b(C3249h c3249h) {
        m15518k();
        c3249h.m17936a(f13626l);
        if (this.f13637a != null && m15502a()) {
            c3249h.m17932a(f13627m);
            c3249h.m17930a(this.f13637a);
            c3249h.m17938b();
        }
        if (this.f13638b != null && m15507b()) {
            c3249h.m17932a(f13628n);
            this.f13638b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13639c != null) {
            c3249h.m17932a(f13629o);
            c3249h.m17930a(this.f13639c);
            c3249h.m17938b();
        }
        if (this.f13640d != null) {
            c3249h.m17932a(f13630p);
            c3249h.m17930a(this.f13640d);
            c3249h.m17938b();
        }
        c3249h.m17932a(f13631q);
        c3249h.m17929a(this.f13641e);
        c3249h.m17938b();
        if (this.f13642f != null && m15513f()) {
            c3249h.m17932a(f13632r);
            c3249h.m17930a(this.f13642f);
            c3249h.m17938b();
        }
        if (this.f13643g != null && m15514g()) {
            c3249h.m17932a(f13633s);
            c3249h.m17930a(this.f13643g);
            c3249h.m17938b();
        }
        if (this.f13644h != null && m15515h()) {
            c3249h.m17932a(f13634t);
            this.f13644h.m15686b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13645i != null && m15516i()) {
            c3249h.m17932a(f13635u);
            c3249h.m17930a(this.f13645i);
            c3249h.m17938b();
        }
        if (this.f13646j != null && m15517j()) {
            c3249h.m17932a(f13636v);
            c3249h.m17930a(this.f13646j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15507b() {
        return this.f13638b != null;
    }

    public C2742h m15508c(String str) {
        this.f13642f = str;
        return this;
    }

    public boolean m15509c() {
        return this.f13639c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15504b((C2742h) obj);
    }

    public C2742h m15510d(String str) {
        this.f13643g = str;
        return this;
    }

    public boolean m15511d() {
        return this.f13640d != null;
    }

    public boolean m15512e() {
        return this.f13647w.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2742h)) ? m15503a((C2742h) obj) : false;
    }

    public boolean m15513f() {
        return this.f13642f != null;
    }

    public boolean m15514g() {
        return this.f13643g != null;
    }

    public boolean m15515h() {
        return this.f13644h != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15516i() {
        return this.f13645i != null;
    }

    public boolean m15517j() {
        return this.f13646j != null;
    }

    public void m15518k() {
        if (this.f13639c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13640d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionAckMessage(");
        Object obj2 = 1;
        if (m15502a()) {
            stringBuilder.append("debug:");
            if (this.f13637a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13637a);
            }
            obj2 = null;
        }
        if (m15507b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13638b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13638b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13639c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13639c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13640d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13640d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.f13641e);
        if (m15513f()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f13642f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13642f);
            }
        }
        if (m15514g()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.f13643g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13643g);
            }
        }
        if (m15515h()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f13644h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13644h);
            }
        }
        if (m15516i()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13645i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13645i);
            }
        }
        if (m15517j()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13646j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13646j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
