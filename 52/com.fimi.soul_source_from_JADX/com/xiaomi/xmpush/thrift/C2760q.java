package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.message.RMsgInfoDB;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
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

/* renamed from: com.xiaomi.xmpush.thrift.q */
public class C2760q implements Serializable, Cloneable, C2478b<C2760q, C2759a> {
    public static final Map<C2759a, C3243b> f13929l;
    private static final C3262m f13930m;
    private static final C3255e f13931n;
    private static final C3255e f13932o;
    private static final C3255e f13933p;
    private static final C3255e f13934q;
    private static final C3255e f13935r;
    private static final C3255e f13936s;
    private static final C3255e f13937t;
    private static final C3255e f13938u;
    private static final C3255e f13939v;
    private static final C3255e f13940w;
    private static final C3255e f13941x;
    public String f13942a;
    public C2740g f13943b;
    public String f13944c;
    public String f13945d;
    public String f13946e;
    public String f13947f;
    public String f13948g;
    public C2732c f13949h;
    public boolean f13950i;
    public Map<String, String> f13951j;
    public String f13952k;
    private BitSet f13953y;

    /* renamed from: com.xiaomi.xmpush.thrift.q.a */
    public enum C2759a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        PACKAGE_NAME((short) 5, C2537j.f12839W),
        TOPIC((short) 6, "topic"),
        ALIAS_NAME((short) 7, "aliasName"),
        MESSAGE((short) 8, RMsgInfoDB.TABLE),
        NEED_ACK((short) 9, "needAck"),
        PARAMS((short) 10, "params"),
        CATEGORY((short) 11, "category");
        
        private static final Map<String, C2759a> f13925l;
        private final short f13927m;
        private final String f13928n;

        static {
            f13925l = new HashMap();
            Iterator it = EnumSet.allOf(C2759a.class).iterator();
            while (it.hasNext()) {
                C2759a c2759a = (C2759a) it.next();
                f13925l.put(c2759a.m15680a(), c2759a);
            }
        }

        private C2759a(short s, String str) {
            this.f13927m = s;
            this.f13928n = str;
        }

        public String m15680a() {
            return this.f13928n;
        }
    }

    static {
        f13930m = new C3262m("XmPushActionSendMessage");
        f13931n = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13932o = new C3255e("target", (byte) 12, (short) 2);
        f13933p = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13934q = new C3255e("appId", (byte) 11, (short) 4);
        f13935r = new C3255e(C2537j.f12839W, (byte) 11, (short) 5);
        f13936s = new C3255e("topic", (byte) 11, (short) 6);
        f13937t = new C3255e("aliasName", (byte) 11, (short) 7);
        f13938u = new C3255e(RMsgInfoDB.TABLE, (byte) 12, (short) 8);
        f13939v = new C3255e("needAck", (byte) 2, (short) 9);
        f13940w = new C3255e("params", (byte) 13, (short) 10);
        f13941x = new C3255e("category", (byte) 11, (short) 11);
        Map enumMap = new EnumMap(C2759a.class);
        enumMap.put(C2759a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2759a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2759a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2759a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2759a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2759a.TOPIC, new C3243b("topic", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2759a.ALIAS_NAME, new C3243b("aliasName", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2759a.MESSAGE, new C3243b(RMsgInfoDB.TABLE, (byte) 2, new C3247g((byte) 12, C2732c.class)));
        enumMap.put(C2759a.NEED_ACK, new C3243b("needAck", (byte) 2, new C3241c((byte) 2)));
        enumMap.put(C2759a.PARAMS, new C3243b("params", (byte) 2, new C3245e((byte) 13, new C3241c((byte) 11), new C3241c((byte) 11))));
        enumMap.put(C2759a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13929l = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2760q.class, f13929l);
    }

    public C2760q() {
        this.f13953y = new BitSet(1);
        this.f13950i = true;
    }

    public void m15681a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15703r();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13942a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13943b = new C2740g();
                    this.f13943b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13944c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13945d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13946e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13947f = c3249h.m17959w();
                        break;
                    }
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13948g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13949h = new C2732c();
                    this.f13949h.m15403a(c3249h);
                    break;
                case Type.ARRAY /*9*/:
                    if (i.f15756b != 2) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13950i = c3249h.m17953q();
                    m15682a(true);
                    break;
                case Type.OBJECT /*10*/:
                    if (i.f15756b != 13) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3257g k = c3249h.m17947k();
                    this.f13951j = new HashMap(k.f15762c * 2);
                    for (int i2 = 0; i2 < k.f15762c; i2++) {
                        this.f13951j.put(c3249h.m17959w(), c3249h.m17959w());
                    }
                    c3249h.m17948l();
                    break;
                case Opcodes.T_LONG /*11*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13952k = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15682a(boolean z) {
        this.f13953y.set(0, z);
    }

    public boolean m15683a() {
        return this.f13942a != null;
    }

    public boolean m15684a(C2760q c2760q) {
        if (c2760q == null) {
            return false;
        }
        boolean a = m15683a();
        boolean a2 = c2760q.m15683a();
        if ((a || a2) && (!a || !a2 || !this.f13942a.equals(c2760q.f13942a))) {
            return false;
        }
        a = m15687b();
        a2 = c2760q.m15687b();
        if ((a || a2) && (!a || !a2 || !this.f13943b.m15488a(c2760q.f13943b))) {
            return false;
        }
        a = m15689d();
        a2 = c2760q.m15689d();
        if ((a || a2) && (!a || !a2 || !this.f13944c.equals(c2760q.f13944c))) {
            return false;
        }
        a = m15691f();
        a2 = c2760q.m15691f();
        if ((a || a2) && (!a || !a2 || !this.f13945d.equals(c2760q.f13945d))) {
            return false;
        }
        a = m15692g();
        a2 = c2760q.m15692g();
        if ((a || a2) && (!a || !a2 || !this.f13946e.equals(c2760q.f13946e))) {
            return false;
        }
        a = m15694i();
        a2 = c2760q.m15694i();
        if ((a || a2) && (!a || !a2 || !this.f13947f.equals(c2760q.f13947f))) {
            return false;
        }
        a = m15696k();
        a2 = c2760q.m15696k();
        if ((a || a2) && (!a || !a2 || !this.f13948g.equals(c2760q.f13948g))) {
            return false;
        }
        a = m15698m();
        a2 = c2760q.m15698m();
        if ((a || a2) && (!a || !a2 || !this.f13949h.m15406a(c2760q.f13949h))) {
            return false;
        }
        a = m15699n();
        a2 = c2760q.m15699n();
        if ((a || a2) && (!a || !a2 || this.f13950i != c2760q.f13950i)) {
            return false;
        }
        a = m15700o();
        a2 = c2760q.m15700o();
        if ((a || a2) && (!a || !a2 || !this.f13951j.equals(c2760q.f13951j))) {
            return false;
        }
        a = m15702q();
        a2 = c2760q.m15702q();
        return !(a || a2) || (a && a2 && this.f13952k.equals(c2760q.f13952k));
    }

    public int m15685b(C2760q c2760q) {
        if (!getClass().equals(c2760q.getClass())) {
            return getClass().getName().compareTo(c2760q.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15683a()).compareTo(Boolean.valueOf(c2760q.m15683a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15683a()) {
            compareTo = C3269c.m18090a(this.f13942a, c2760q.f13942a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15687b()).compareTo(Boolean.valueOf(c2760q.m15687b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15687b()) {
            compareTo = C3269c.m18089a(this.f13943b, c2760q.f13943b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15689d()).compareTo(Boolean.valueOf(c2760q.m15689d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15689d()) {
            compareTo = C3269c.m18090a(this.f13944c, c2760q.f13944c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15691f()).compareTo(Boolean.valueOf(c2760q.m15691f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15691f()) {
            compareTo = C3269c.m18090a(this.f13945d, c2760q.f13945d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15692g()).compareTo(Boolean.valueOf(c2760q.m15692g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15692g()) {
            compareTo = C3269c.m18090a(this.f13946e, c2760q.f13946e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15694i()).compareTo(Boolean.valueOf(c2760q.m15694i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15694i()) {
            compareTo = C3269c.m18090a(this.f13947f, c2760q.f13947f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15696k()).compareTo(Boolean.valueOf(c2760q.m15696k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15696k()) {
            compareTo = C3269c.m18090a(this.f13948g, c2760q.f13948g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15698m()).compareTo(Boolean.valueOf(c2760q.m15698m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15698m()) {
            compareTo = C3269c.m18089a(this.f13949h, c2760q.f13949h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15699n()).compareTo(Boolean.valueOf(c2760q.m15699n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15699n()) {
            compareTo = C3269c.m18095a(this.f13950i, c2760q.f13950i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15700o()).compareTo(Boolean.valueOf(c2760q.m15700o()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15700o()) {
            compareTo = C3269c.m18093a(this.f13951j, c2760q.f13951j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15702q()).compareTo(Boolean.valueOf(c2760q.m15702q()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15702q()) {
            compareTo = C3269c.m18090a(this.f13952k, c2760q.f13952k);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15686b(C3249h c3249h) {
        m15703r();
        c3249h.m17936a(f13930m);
        if (this.f13942a != null && m15683a()) {
            c3249h.m17932a(f13931n);
            c3249h.m17930a(this.f13942a);
            c3249h.m17938b();
        }
        if (this.f13943b != null && m15687b()) {
            c3249h.m17932a(f13932o);
            this.f13943b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13944c != null) {
            c3249h.m17932a(f13933p);
            c3249h.m17930a(this.f13944c);
            c3249h.m17938b();
        }
        if (this.f13945d != null) {
            c3249h.m17932a(f13934q);
            c3249h.m17930a(this.f13945d);
            c3249h.m17938b();
        }
        if (this.f13946e != null && m15692g()) {
            c3249h.m17932a(f13935r);
            c3249h.m17930a(this.f13946e);
            c3249h.m17938b();
        }
        if (this.f13947f != null && m15694i()) {
            c3249h.m17932a(f13936s);
            c3249h.m17930a(this.f13947f);
            c3249h.m17938b();
        }
        if (this.f13948g != null && m15696k()) {
            c3249h.m17932a(f13937t);
            c3249h.m17930a(this.f13948g);
            c3249h.m17938b();
        }
        if (this.f13949h != null && m15698m()) {
            c3249h.m17932a(f13938u);
            this.f13949h.m15409b(c3249h);
            c3249h.m17938b();
        }
        if (m15699n()) {
            c3249h.m17932a(f13939v);
            c3249h.m17937a(this.f13950i);
            c3249h.m17938b();
        }
        if (this.f13951j != null && m15700o()) {
            c3249h.m17932a(f13940w);
            c3249h.m17934a(new C3257g((byte) 11, (byte) 11, this.f13951j.size()));
            for (Entry entry : this.f13951j.entrySet()) {
                c3249h.m17930a((String) entry.getKey());
                c3249h.m17930a((String) entry.getValue());
            }
            c3249h.m17940d();
            c3249h.m17938b();
        }
        if (this.f13952k != null && m15702q()) {
            c3249h.m17932a(f13941x);
            c3249h.m17930a(this.f13952k);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15687b() {
        return this.f13943b != null;
    }

    public String m15688c() {
        return this.f13944c;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15685b((C2760q) obj);
    }

    public boolean m15689d() {
        return this.f13944c != null;
    }

    public String m15690e() {
        return this.f13945d;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2760q)) ? m15684a((C2760q) obj) : false;
    }

    public boolean m15691f() {
        return this.f13945d != null;
    }

    public boolean m15692g() {
        return this.f13946e != null;
    }

    public String m15693h() {
        return this.f13947f;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15694i() {
        return this.f13947f != null;
    }

    public String m15695j() {
        return this.f13948g;
    }

    public boolean m15696k() {
        return this.f13948g != null;
    }

    public C2732c m15697l() {
        return this.f13949h;
    }

    public boolean m15698m() {
        return this.f13949h != null;
    }

    public boolean m15699n() {
        return this.f13953y.get(0);
    }

    public boolean m15700o() {
        return this.f13951j != null;
    }

    public String m15701p() {
        return this.f13952k;
    }

    public boolean m15702q() {
        return this.f13952k != null;
    }

    public void m15703r() {
        if (this.f13944c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13945d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendMessage(");
        Object obj2 = 1;
        if (m15683a()) {
            stringBuilder.append("debug:");
            if (this.f13942a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13942a);
            }
            obj2 = null;
        }
        if (m15687b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13943b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13943b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13944c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13944c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13945d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13945d);
        }
        if (m15692g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13946e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13946e);
            }
        }
        if (m15694i()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f13947f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13947f);
            }
        }
        if (m15696k()) {
            stringBuilder.append(", ");
            stringBuilder.append("aliasName:");
            if (this.f13948g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13948g);
            }
        }
        if (m15698m()) {
            stringBuilder.append(", ");
            stringBuilder.append("message:");
            if (this.f13949h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13949h);
            }
        }
        if (m15699n()) {
            stringBuilder.append(", ");
            stringBuilder.append("needAck:");
            stringBuilder.append(this.f13950i);
        }
        if (m15700o()) {
            stringBuilder.append(", ");
            stringBuilder.append("params:");
            if (this.f13951j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13951j);
            }
        }
        if (m15702q()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13952k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13952k);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
