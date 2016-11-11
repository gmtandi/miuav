package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.open.SocialConstants;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3244d;
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3256f;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.j */
public class C2746j implements Serializable, Cloneable, C2478b<C2746j, C2745a> {
    public static final Map<C2745a, C3243b> f13693l;
    private static final C3262m f13694m;
    private static final C3255e f13695n;
    private static final C3255e f13696o;
    private static final C3255e f13697p;
    private static final C3255e f13698q;
    private static final C3255e f13699r;
    private static final C3255e f13700s;
    private static final C3255e f13701t;
    private static final C3255e f13702u;
    private static final C3255e f13703v;
    private static final C3255e f13704w;
    private static final C3255e f13705x;
    public String f13706a;
    public C2740g f13707b;
    public String f13708c;
    public String f13709d;
    public String f13710e;
    public C2744i f13711f;
    public long f13712g;
    public String f13713h;
    public String f13714i;
    public List<String> f13715j;
    public String f13716k;
    private BitSet f13717y;

    /* renamed from: com.xiaomi.xmpush.thrift.j.a */
    public enum C2745a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        CMD_NAME((short) 5, "cmdName"),
        REQUEST((short) 6, SocialConstants.TYPE_REQUEST),
        ERROR_CODE((short) 7, "errorCode"),
        REASON((short) 8, "reason"),
        PACKAGE_NAME((short) 9, C2537j.f12839W),
        CMD_ARGS((short) 10, "cmdArgs"),
        CATEGORY((short) 12, "category");
        
        private static final Map<String, C2745a> f13689l;
        private final short f13691m;
        private final String f13692n;

        static {
            f13689l = new HashMap();
            Iterator it = EnumSet.allOf(C2745a.class).iterator();
            while (it.hasNext()) {
                C2745a c2745a = (C2745a) it.next();
                f13689l.put(c2745a.m15539a(), c2745a);
            }
        }

        private C2745a(short s, String str) {
            this.f13691m = s;
            this.f13692n = str;
        }

        public String m15539a() {
            return this.f13692n;
        }
    }

    static {
        f13694m = new C3262m("XmPushActionCommandResult");
        f13695n = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13696o = new C3255e("target", (byte) 12, (short) 2);
        f13697p = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13698q = new C3255e("appId", (byte) 11, (short) 4);
        f13699r = new C3255e("cmdName", (byte) 11, (short) 5);
        f13700s = new C3255e(SocialConstants.TYPE_REQUEST, (byte) 12, (short) 6);
        f13701t = new C3255e("errorCode", (byte) 10, (short) 7);
        f13702u = new C3255e("reason", (byte) 11, (short) 8);
        f13703v = new C3255e(C2537j.f12839W, (byte) 11, (short) 9);
        f13704w = new C3255e("cmdArgs", (byte) 15, (short) 10);
        f13705x = new C3255e("category", (byte) 11, (short) 12);
        Map enumMap = new EnumMap(C2745a.class);
        enumMap.put(C2745a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2745a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2745a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2745a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2745a.CMD_NAME, new C3243b("cmdName", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2745a.REQUEST, new C3243b(SocialConstants.TYPE_REQUEST, (byte) 2, new C3247g((byte) 12, C2744i.class)));
        enumMap.put(C2745a.ERROR_CODE, new C3243b("errorCode", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2745a.REASON, new C3243b("reason", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2745a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2745a.CMD_ARGS, new C3243b("cmdArgs", (byte) 2, new C3244d((byte) 15, new C3241c((byte) 11))));
        enumMap.put(C2745a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13693l = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2746j.class, f13693l);
    }

    public C2746j() {
        this.f13717y = new BitSet(1);
    }

    public void m15540a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15552h()) {
                    m15559o();
                    return;
                }
                throw new C3259i("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13706a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13707b = new C2740g();
                    this.f13707b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13708c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13709d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13710e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13711f = new C2744i();
                    this.f13711f.m15521a(c3249h);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13712g = c3249h.m17957u();
                    m15541a(true);
                    break;
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13713h = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13714i = c3249h.m17959w();
                        break;
                    }
                case Type.OBJECT /*10*/:
                    if (i.f15756b != 15) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3256f m = c3249h.m17949m();
                    this.f13715j = new ArrayList(m.f15759b);
                    for (int i2 = 0; i2 < m.f15759b; i2++) {
                        this.f13715j.add(c3249h.m17959w());
                    }
                    c3249h.m17950n();
                    break;
                case Opcodes.FCONST_1 /*12*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13716k = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15541a(boolean z) {
        this.f13717y.set(0, z);
    }

    public boolean m15542a() {
        return this.f13706a != null;
    }

    public boolean m15543a(C2746j c2746j) {
        if (c2746j == null) {
            return false;
        }
        boolean a = m15542a();
        boolean a2 = c2746j.m15542a();
        if ((a || a2) && (!a || !a2 || !this.f13706a.equals(c2746j.f13706a))) {
            return false;
        }
        a = m15546b();
        a2 = c2746j.m15546b();
        if ((a || a2) && (!a || !a2 || !this.f13707b.m15488a(c2746j.f13707b))) {
            return false;
        }
        a = m15547c();
        a2 = c2746j.m15547c();
        if ((a || a2) && (!a || !a2 || !this.f13708c.equals(c2746j.f13708c))) {
            return false;
        }
        a = m15548d();
        a2 = c2746j.m15548d();
        if ((a || a2) && (!a || !a2 || !this.f13709d.equals(c2746j.f13709d))) {
            return false;
        }
        a = m15550f();
        a2 = c2746j.m15550f();
        if ((a || a2) && (!a || !a2 || !this.f13710e.equals(c2746j.f13710e))) {
            return false;
        }
        a = m15551g();
        a2 = c2746j.m15551g();
        if (((a || a2) && (!a || !a2 || !this.f13711f.m15523a(c2746j.f13711f))) || this.f13712g != c2746j.f13712g) {
            return false;
        }
        a = m15553i();
        a2 = c2746j.m15553i();
        if ((a || a2) && (!a || !a2 || !this.f13713h.equals(c2746j.f13713h))) {
            return false;
        }
        a = m15554j();
        a2 = c2746j.m15554j();
        if ((a || a2) && (!a || !a2 || !this.f13714i.equals(c2746j.f13714i))) {
            return false;
        }
        a = m15556l();
        a2 = c2746j.m15556l();
        if ((a || a2) && (!a || !a2 || !this.f13715j.equals(c2746j.f13715j))) {
            return false;
        }
        a = m15558n();
        a2 = c2746j.m15558n();
        return !(a || a2) || (a && a2 && this.f13716k.equals(c2746j.f13716k));
    }

    public int m15544b(C2746j c2746j) {
        if (!getClass().equals(c2746j.getClass())) {
            return getClass().getName().compareTo(c2746j.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15542a()).compareTo(Boolean.valueOf(c2746j.m15542a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15542a()) {
            compareTo = C3269c.m18090a(this.f13706a, c2746j.f13706a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15546b()).compareTo(Boolean.valueOf(c2746j.m15546b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15546b()) {
            compareTo = C3269c.m18089a(this.f13707b, c2746j.f13707b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15547c()).compareTo(Boolean.valueOf(c2746j.m15547c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15547c()) {
            compareTo = C3269c.m18090a(this.f13708c, c2746j.f13708c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15548d()).compareTo(Boolean.valueOf(c2746j.m15548d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15548d()) {
            compareTo = C3269c.m18090a(this.f13709d, c2746j.f13709d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15550f()).compareTo(Boolean.valueOf(c2746j.m15550f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15550f()) {
            compareTo = C3269c.m18090a(this.f13710e, c2746j.f13710e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15551g()).compareTo(Boolean.valueOf(c2746j.m15551g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15551g()) {
            compareTo = C3269c.m18089a(this.f13711f, c2746j.f13711f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15552h()).compareTo(Boolean.valueOf(c2746j.m15552h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15552h()) {
            compareTo = C3269c.m18088a(this.f13712g, c2746j.f13712g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15553i()).compareTo(Boolean.valueOf(c2746j.m15553i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15553i()) {
            compareTo = C3269c.m18090a(this.f13713h, c2746j.f13713h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15554j()).compareTo(Boolean.valueOf(c2746j.m15554j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15554j()) {
            compareTo = C3269c.m18090a(this.f13714i, c2746j.f13714i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15556l()).compareTo(Boolean.valueOf(c2746j.m15556l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15556l()) {
            compareTo = C3269c.m18092a(this.f13715j, c2746j.f13715j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15558n()).compareTo(Boolean.valueOf(c2746j.m15558n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15558n()) {
            compareTo = C3269c.m18090a(this.f13716k, c2746j.f13716k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15545b(C3249h c3249h) {
        m15559o();
        c3249h.m17936a(f13694m);
        if (this.f13706a != null && m15542a()) {
            c3249h.m17932a(f13695n);
            c3249h.m17930a(this.f13706a);
            c3249h.m17938b();
        }
        if (this.f13707b != null && m15546b()) {
            c3249h.m17932a(f13696o);
            this.f13707b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13708c != null) {
            c3249h.m17932a(f13697p);
            c3249h.m17930a(this.f13708c);
            c3249h.m17938b();
        }
        if (this.f13709d != null) {
            c3249h.m17932a(f13698q);
            c3249h.m17930a(this.f13709d);
            c3249h.m17938b();
        }
        if (this.f13710e != null) {
            c3249h.m17932a(f13699r);
            c3249h.m17930a(this.f13710e);
            c3249h.m17938b();
        }
        if (this.f13711f != null && m15551g()) {
            c3249h.m17932a(f13700s);
            this.f13711f.m15526b(c3249h);
            c3249h.m17938b();
        }
        c3249h.m17932a(f13701t);
        c3249h.m17929a(this.f13712g);
        c3249h.m17938b();
        if (this.f13713h != null && m15553i()) {
            c3249h.m17932a(f13702u);
            c3249h.m17930a(this.f13713h);
            c3249h.m17938b();
        }
        if (this.f13714i != null && m15554j()) {
            c3249h.m17932a(f13703v);
            c3249h.m17930a(this.f13714i);
            c3249h.m17938b();
        }
        if (this.f13715j != null && m15556l()) {
            c3249h.m17932a(f13704w);
            c3249h.m17933a(new C3256f((byte) 11, this.f13715j.size()));
            for (String a : this.f13715j) {
                c3249h.m17930a(a);
            }
            c3249h.m17941e();
            c3249h.m17938b();
        }
        if (this.f13716k != null && m15558n()) {
            c3249h.m17932a(f13705x);
            c3249h.m17930a(this.f13716k);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15546b() {
        return this.f13707b != null;
    }

    public boolean m15547c() {
        return this.f13708c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15544b((C2746j) obj);
    }

    public boolean m15548d() {
        return this.f13709d != null;
    }

    public String m15549e() {
        return this.f13710e;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2746j)) ? m15543a((C2746j) obj) : false;
    }

    public boolean m15550f() {
        return this.f13710e != null;
    }

    public boolean m15551g() {
        return this.f13711f != null;
    }

    public boolean m15552h() {
        return this.f13717y.get(0);
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15553i() {
        return this.f13713h != null;
    }

    public boolean m15554j() {
        return this.f13714i != null;
    }

    public List<String> m15555k() {
        return this.f13715j;
    }

    public boolean m15556l() {
        return this.f13715j != null;
    }

    public String m15557m() {
        return this.f13716k;
    }

    public boolean m15558n() {
        return this.f13716k != null;
    }

    public void m15559o() {
        if (this.f13708c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13709d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f13710e == null) {
            throw new C3259i("Required field 'cmdName' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionCommandResult(");
        Object obj2 = 1;
        if (m15542a()) {
            stringBuilder.append("debug:");
            if (this.f13706a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13706a);
            }
            obj2 = null;
        }
        if (m15546b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13707b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13707b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13708c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13708c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13709d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13709d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("cmdName:");
        if (this.f13710e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13710e);
        }
        if (m15551g()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f13711f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13711f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.f13712g);
        if (m15553i()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.f13713h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13713h);
            }
        }
        if (m15554j()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13714i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13714i);
            }
        }
        if (m15556l()) {
            stringBuilder.append(", ");
            stringBuilder.append("cmdArgs:");
            if (this.f13715j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13715j);
            }
        }
        if (m15558n()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13716k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13716k);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
