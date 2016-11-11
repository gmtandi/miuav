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

/* renamed from: com.xiaomi.xmpush.thrift.w */
public class C2772w implements Serializable, Cloneable, C2478b<C2772w, C2771a> {
    public static final Map<C2771a, C3243b> f14126k;
    private static final C3262m f14127l;
    private static final C3255e f14128m;
    private static final C3255e f14129n;
    private static final C3255e f14130o;
    private static final C3255e f14131p;
    private static final C3255e f14132q;
    private static final C3255e f14133r;
    private static final C3255e f14134s;
    private static final C3255e f14135t;
    private static final C3255e f14136u;
    private static final C3255e f14137v;
    public String f14138a;
    public C2740g f14139b;
    public String f14140c;
    public String f14141d;
    public C2770v f14142e;
    public long f14143f;
    public String f14144g;
    public String f14145h;
    public String f14146i;
    public String f14147j;
    private BitSet f14148w;

    /* renamed from: com.xiaomi.xmpush.thrift.w.a */
    public enum C2771a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        TOPIC((short) 8, "topic"),
        PACKAGE_NAME((short) 9, C2537j.f12839W),
        CATEGORY((short) 10, "category");
        
        private static final Map<String, C2771a> f14122k;
        private final short f14124l;
        private final String f14125m;

        static {
            f14122k = new HashMap();
            Iterator it = EnumSet.allOf(C2771a.class).iterator();
            while (it.hasNext()) {
                C2771a c2771a = (C2771a) it.next();
                f14122k.put(c2771a.m15795a(), c2771a);
            }
        }

        private C2771a(short s, String str) {
            this.f14124l = s;
            this.f14125m = str;
        }

        public String m15795a() {
            return this.f14125m;
        }
    }

    static {
        f14127l = new C3262m("XmPushActionUnSubscriptionResult");
        f14128m = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f14129n = new C3255e("target", (byte) 12, (short) 2);
        f14130o = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f14131p = new C3255e("appId", (byte) 11, (short) 4);
        f14132q = new C3255e(SocialConstants.TYPE_REQUEST, (byte) 12, (short) 5);
        f14133r = new C3255e("errorCode", (byte) 10, (short) 6);
        f14134s = new C3255e("reason", (byte) 11, (short) 7);
        f14135t = new C3255e("topic", (byte) 11, (short) 8);
        f14136u = new C3255e(C2537j.f12839W, (byte) 11, (short) 9);
        f14137v = new C3255e("category", (byte) 11, (short) 10);
        Map enumMap = new EnumMap(C2771a.class);
        enumMap.put(C2771a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2771a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2771a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2771a.APP_ID, new C3243b("appId", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2771a.REQUEST, new C3243b(SocialConstants.TYPE_REQUEST, (byte) 2, new C3247g((byte) 12, C2770v.class)));
        enumMap.put(C2771a.ERROR_CODE, new C3243b("errorCode", (byte) 2, new C3241c((byte) 10)));
        enumMap.put(C2771a.REASON, new C3243b("reason", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2771a.TOPIC, new C3243b("topic", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2771a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2771a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f14126k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2772w.class, f14126k);
    }

    public C2772w() {
        this.f14148w = new BitSet(1);
    }

    public void m15796a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15813m();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14138a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14139b = new C2740g();
                    this.f14139b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14140c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14141d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14142e = new C2770v();
                    this.f14142e.m15779a(c3249h);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f14143f = c3249h.m17957u();
                    m15797a(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14144g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14145h = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14146i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f14147j = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15797a(boolean z) {
        this.f14148w.set(0, z);
    }

    public boolean m15798a() {
        return this.f14138a != null;
    }

    public boolean m15799a(C2772w c2772w) {
        if (c2772w == null) {
            return false;
        }
        boolean a = m15798a();
        boolean a2 = c2772w.m15798a();
        if ((a || a2) && (!a || !a2 || !this.f14138a.equals(c2772w.f14138a))) {
            return false;
        }
        a = m15802b();
        a2 = c2772w.m15802b();
        if ((a || a2) && (!a || !a2 || !this.f14139b.m15488a(c2772w.f14139b))) {
            return false;
        }
        a = m15803c();
        a2 = c2772w.m15803c();
        if ((a || a2) && (!a || !a2 || !this.f14140c.equals(c2772w.f14140c))) {
            return false;
        }
        a = m15804d();
        a2 = c2772w.m15804d();
        if ((a || a2) && (!a || !a2 || !this.f14141d.equals(c2772w.f14141d))) {
            return false;
        }
        a = m15805e();
        a2 = c2772w.m15805e();
        if ((a || a2) && (!a || !a2 || !this.f14142e.m15781a(c2772w.f14142e))) {
            return false;
        }
        a = m15806f();
        a2 = c2772w.m15806f();
        if ((a || a2) && (!a || !a2 || this.f14143f != c2772w.f14143f)) {
            return false;
        }
        a = m15807g();
        a2 = c2772w.m15807g();
        if ((a || a2) && (!a || !a2 || !this.f14144g.equals(c2772w.f14144g))) {
            return false;
        }
        a = m15809i();
        a2 = c2772w.m15809i();
        if ((a || a2) && (!a || !a2 || !this.f14145h.equals(c2772w.f14145h))) {
            return false;
        }
        a = m15810j();
        a2 = c2772w.m15810j();
        if ((a || a2) && (!a || !a2 || !this.f14146i.equals(c2772w.f14146i))) {
            return false;
        }
        a = m15812l();
        a2 = c2772w.m15812l();
        return !(a || a2) || (a && a2 && this.f14147j.equals(c2772w.f14147j));
    }

    public int m15800b(C2772w c2772w) {
        if (!getClass().equals(c2772w.getClass())) {
            return getClass().getName().compareTo(c2772w.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15798a()).compareTo(Boolean.valueOf(c2772w.m15798a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15798a()) {
            compareTo = C3269c.m18090a(this.f14138a, c2772w.f14138a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15802b()).compareTo(Boolean.valueOf(c2772w.m15802b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15802b()) {
            compareTo = C3269c.m18089a(this.f14139b, c2772w.f14139b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15803c()).compareTo(Boolean.valueOf(c2772w.m15803c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15803c()) {
            compareTo = C3269c.m18090a(this.f14140c, c2772w.f14140c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15804d()).compareTo(Boolean.valueOf(c2772w.m15804d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15804d()) {
            compareTo = C3269c.m18090a(this.f14141d, c2772w.f14141d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15805e()).compareTo(Boolean.valueOf(c2772w.m15805e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15805e()) {
            compareTo = C3269c.m18089a(this.f14142e, c2772w.f14142e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15806f()).compareTo(Boolean.valueOf(c2772w.m15806f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15806f()) {
            compareTo = C3269c.m18088a(this.f14143f, c2772w.f14143f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15807g()).compareTo(Boolean.valueOf(c2772w.m15807g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15807g()) {
            compareTo = C3269c.m18090a(this.f14144g, c2772w.f14144g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15809i()).compareTo(Boolean.valueOf(c2772w.m15809i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15809i()) {
            compareTo = C3269c.m18090a(this.f14145h, c2772w.f14145h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15810j()).compareTo(Boolean.valueOf(c2772w.m15810j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15810j()) {
            compareTo = C3269c.m18090a(this.f14146i, c2772w.f14146i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15812l()).compareTo(Boolean.valueOf(c2772w.m15812l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15812l()) {
            compareTo = C3269c.m18090a(this.f14147j, c2772w.f14147j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15801b(C3249h c3249h) {
        m15813m();
        c3249h.m17936a(f14127l);
        if (this.f14138a != null && m15798a()) {
            c3249h.m17932a(f14128m);
            c3249h.m17930a(this.f14138a);
            c3249h.m17938b();
        }
        if (this.f14139b != null && m15802b()) {
            c3249h.m17932a(f14129n);
            this.f14139b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f14140c != null) {
            c3249h.m17932a(f14130o);
            c3249h.m17930a(this.f14140c);
            c3249h.m17938b();
        }
        if (this.f14141d != null && m15804d()) {
            c3249h.m17932a(f14131p);
            c3249h.m17930a(this.f14141d);
            c3249h.m17938b();
        }
        if (this.f14142e != null && m15805e()) {
            c3249h.m17932a(f14132q);
            this.f14142e.m15784b(c3249h);
            c3249h.m17938b();
        }
        if (m15806f()) {
            c3249h.m17932a(f14133r);
            c3249h.m17929a(this.f14143f);
            c3249h.m17938b();
        }
        if (this.f14144g != null && m15807g()) {
            c3249h.m17932a(f14134s);
            c3249h.m17930a(this.f14144g);
            c3249h.m17938b();
        }
        if (this.f14145h != null && m15809i()) {
            c3249h.m17932a(f14135t);
            c3249h.m17930a(this.f14145h);
            c3249h.m17938b();
        }
        if (this.f14146i != null && m15810j()) {
            c3249h.m17932a(f14136u);
            c3249h.m17930a(this.f14146i);
            c3249h.m17938b();
        }
        if (this.f14147j != null && m15812l()) {
            c3249h.m17932a(f14137v);
            c3249h.m17930a(this.f14147j);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15802b() {
        return this.f14139b != null;
    }

    public boolean m15803c() {
        return this.f14140c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15800b((C2772w) obj);
    }

    public boolean m15804d() {
        return this.f14141d != null;
    }

    public boolean m15805e() {
        return this.f14142e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2772w)) ? m15799a((C2772w) obj) : false;
    }

    public boolean m15806f() {
        return this.f14148w.get(0);
    }

    public boolean m15807g() {
        return this.f14144g != null;
    }

    public String m15808h() {
        return this.f14145h;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15809i() {
        return this.f14145h != null;
    }

    public boolean m15810j() {
        return this.f14146i != null;
    }

    public String m15811k() {
        return this.f14147j;
    }

    public boolean m15812l() {
        return this.f14147j != null;
    }

    public void m15813m() {
        if (this.f14140c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionUnSubscriptionResult(");
        Object obj2 = 1;
        if (m15798a()) {
            stringBuilder.append("debug:");
            if (this.f14138a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14138a);
            }
            obj2 = null;
        }
        if (m15802b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f14139b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14139b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f14140c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f14140c);
        }
        if (m15804d()) {
            stringBuilder.append(", ");
            stringBuilder.append("appId:");
            if (this.f14141d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14141d);
            }
        }
        if (m15805e()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f14142e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14142e);
            }
        }
        if (m15806f()) {
            stringBuilder.append(", ");
            stringBuilder.append("errorCode:");
            stringBuilder.append(this.f14143f);
        }
        if (m15807g()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.f14144g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14144g);
            }
        }
        if (m15809i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f14145h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14145h);
            }
        }
        if (m15810j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f14146i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14146i);
            }
        }
        if (m15812l()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f14147j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f14147j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
