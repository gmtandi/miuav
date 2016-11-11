package com.xiaomi.xmpush.thrift;

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

/* renamed from: com.xiaomi.xmpush.thrift.c */
public class C2732c implements Serializable, Cloneable, C2478b<C2732c, C2731a> {
    public static final Map<C2731a, C3243b> f13496i;
    private static final C3262m f13497j;
    private static final C3255e f13498k;
    private static final C3255e f13499l;
    private static final C3255e f13500m;
    private static final C3255e f13501n;
    private static final C3255e f13502o;
    private static final C3255e f13503p;
    private static final C3255e f13504q;
    private static final C3255e f13505r;
    public C2740g f13506a;
    public String f13507b;
    public String f13508c;
    public String f13509d;
    public long f13510e;
    public long f13511f;
    public String f13512g;
    public String f13513h;
    private BitSet f13514s;

    /* renamed from: com.xiaomi.xmpush.thrift.c.a */
    public enum C2731a {
        TO((short) 1, "to"),
        ID((short) 2, LocaleUtil.INDONESIAN),
        APP_ID((short) 3, "appId"),
        PAYLOAD((short) 4, "payload"),
        CREATE_AT((short) 5, "createAt"),
        TTL((short) 6, "ttl"),
        COLLAPSE_KEY((short) 7, "collapseKey"),
        PACKAGE_NAME((short) 8, C2537j.f12839W);
        
        private static final Map<String, C2731a> f13492i;
        private final short f13494j;
        private final String f13495k;

        static {
            f13492i = new HashMap();
            Iterator it = EnumSet.allOf(C2731a.class).iterator();
            while (it.hasNext()) {
                C2731a c2731a = (C2731a) it.next();
                f13492i.put(c2731a.m15402a(), c2731a);
            }
        }

        private C2731a(short s, String str) {
            this.f13494j = s;
            this.f13495k = str;
        }

        public String m15402a() {
            return this.f13495k;
        }
    }

    static {
        f13497j = new C3262m("PushMessage");
        f13498k = new C3255e("to", (byte) 12, (short) 1);
        f13499l = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 2);
        f13500m = new C3255e("appId", (byte) 11, (short) 3);
        f13501n = new C3255e("payload", (byte) 11, (short) 4);
        f13502o = new C3255e("createAt", (byte) 10, (short) 5);
        f13503p = new C3255e("ttl", (byte) 10, (short) 6);
        f13504q = new C3255e("collapseKey", (byte) 11, (short) 7);
        f13505r = new C3255e(C2537j.f12839W, (byte) 11, (short) 8);
        Map enumMap = new EnumMap(C2731a.class);
        enumMap.put(C2731a.TO, new C3243b("to", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2731a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2731a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2731a.PAYLOAD, new C3243b("payload", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2731a.CREATE_AT, new C3243b("createAt", (byte) 2, new C3241c((byte) 10)));
        enumMap.put(C2731a.TTL, new C3243b("ttl", (byte) 2, new C3241c((byte) 10)));
        enumMap.put(C2731a.COLLAPSE_KEY, new C3243b("collapseKey", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2731a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        f13496i = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2732c.class, f13496i);
    }

    public C2732c() {
        this.f13514s = new BitSet(2);
    }

    public void m15403a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15420l();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13506a = new C2740g();
                    this.f13506a.m15485a(c3249h);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13507b = c3249h.m17959w();
                        break;
                    }
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13508c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13509d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13510e = c3249h.m17957u();
                    m15404a(true);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13511f = c3249h.m17957u();
                    m15410b(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13512g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13513h = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15404a(boolean z) {
        this.f13514s.set(0, z);
    }

    public boolean m15405a() {
        return this.f13506a != null;
    }

    public boolean m15406a(C2732c c2732c) {
        if (c2732c == null) {
            return false;
        }
        boolean a = m15405a();
        boolean a2 = c2732c.m15405a();
        if ((a || a2) && (!a || !a2 || !this.f13506a.m15488a(c2732c.f13506a))) {
            return false;
        }
        a = m15411c();
        a2 = c2732c.m15411c();
        if ((a || a2) && (!a || !a2 || !this.f13507b.equals(c2732c.f13507b))) {
            return false;
        }
        a = m15412d();
        a2 = c2732c.m15412d();
        if ((a || a2) && (!a || !a2 || !this.f13508c.equals(c2732c.f13508c))) {
            return false;
        }
        a = m15414f();
        a2 = c2732c.m15414f();
        if ((a || a2) && (!a || !a2 || !this.f13509d.equals(c2732c.f13509d))) {
            return false;
        }
        a = m15416h();
        a2 = c2732c.m15416h();
        if ((a || a2) && (!a || !a2 || this.f13510e != c2732c.f13510e)) {
            return false;
        }
        a = m15417i();
        a2 = c2732c.m15417i();
        if ((a || a2) && (!a || !a2 || this.f13511f != c2732c.f13511f)) {
            return false;
        }
        a = m15418j();
        a2 = c2732c.m15418j();
        if ((a || a2) && (!a || !a2 || !this.f13512g.equals(c2732c.f13512g))) {
            return false;
        }
        a = m15419k();
        a2 = c2732c.m15419k();
        return !(a || a2) || (a && a2 && this.f13513h.equals(c2732c.f13513h));
    }

    public int m15407b(C2732c c2732c) {
        if (!getClass().equals(c2732c.getClass())) {
            return getClass().getName().compareTo(c2732c.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15405a()).compareTo(Boolean.valueOf(c2732c.m15405a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15405a()) {
            compareTo = C3269c.m18089a(this.f13506a, c2732c.f13506a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15411c()).compareTo(Boolean.valueOf(c2732c.m15411c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15411c()) {
            compareTo = C3269c.m18090a(this.f13507b, c2732c.f13507b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15412d()).compareTo(Boolean.valueOf(c2732c.m15412d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15412d()) {
            compareTo = C3269c.m18090a(this.f13508c, c2732c.f13508c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15414f()).compareTo(Boolean.valueOf(c2732c.m15414f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15414f()) {
            compareTo = C3269c.m18090a(this.f13509d, c2732c.f13509d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15416h()).compareTo(Boolean.valueOf(c2732c.m15416h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15416h()) {
            compareTo = C3269c.m18088a(this.f13510e, c2732c.f13510e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15417i()).compareTo(Boolean.valueOf(c2732c.m15417i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15417i()) {
            compareTo = C3269c.m18088a(this.f13511f, c2732c.f13511f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15418j()).compareTo(Boolean.valueOf(c2732c.m15418j()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15418j()) {
            compareTo = C3269c.m18090a(this.f13512g, c2732c.f13512g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15419k()).compareTo(Boolean.valueOf(c2732c.m15419k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15419k()) {
            compareTo = C3269c.m18090a(this.f13513h, c2732c.f13513h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public String m15408b() {
        return this.f13507b;
    }

    public void m15409b(C3249h c3249h) {
        m15420l();
        c3249h.m17936a(f13497j);
        if (this.f13506a != null && m15405a()) {
            c3249h.m17932a(f13498k);
            this.f13506a.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13507b != null) {
            c3249h.m17932a(f13499l);
            c3249h.m17930a(this.f13507b);
            c3249h.m17938b();
        }
        if (this.f13508c != null) {
            c3249h.m17932a(f13500m);
            c3249h.m17930a(this.f13508c);
            c3249h.m17938b();
        }
        if (this.f13509d != null) {
            c3249h.m17932a(f13501n);
            c3249h.m17930a(this.f13509d);
            c3249h.m17938b();
        }
        if (m15416h()) {
            c3249h.m17932a(f13502o);
            c3249h.m17929a(this.f13510e);
            c3249h.m17938b();
        }
        if (m15417i()) {
            c3249h.m17932a(f13503p);
            c3249h.m17929a(this.f13511f);
            c3249h.m17938b();
        }
        if (this.f13512g != null && m15418j()) {
            c3249h.m17932a(f13504q);
            c3249h.m17930a(this.f13512g);
            c3249h.m17938b();
        }
        if (this.f13513h != null && m15419k()) {
            c3249h.m17932a(f13505r);
            c3249h.m17930a(this.f13513h);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m15410b(boolean z) {
        this.f13514s.set(1, z);
    }

    public boolean m15411c() {
        return this.f13507b != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15407b((C2732c) obj);
    }

    public boolean m15412d() {
        return this.f13508c != null;
    }

    public String m15413e() {
        return this.f13509d;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2732c)) ? m15406a((C2732c) obj) : false;
    }

    public boolean m15414f() {
        return this.f13509d != null;
    }

    public long m15415g() {
        return this.f13510e;
    }

    public boolean m15416h() {
        return this.f13514s.get(0);
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15417i() {
        return this.f13514s.get(1);
    }

    public boolean m15418j() {
        return this.f13512g != null;
    }

    public boolean m15419k() {
        return this.f13513h != null;
    }

    public void m15420l() {
        if (this.f13507b == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13508c == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f13509d == null) {
            throw new C3259i("Required field 'payload' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMessage(");
        Object obj = 1;
        if (m15405a()) {
            stringBuilder.append("to:");
            if (this.f13506a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13506a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13507b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13507b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13508c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13508c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("payload:");
        if (this.f13509d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13509d);
        }
        if (m15416h()) {
            stringBuilder.append(", ");
            stringBuilder.append("createAt:");
            stringBuilder.append(this.f13510e);
        }
        if (m15417i()) {
            stringBuilder.append(", ");
            stringBuilder.append("ttl:");
            stringBuilder.append(this.f13511f);
        }
        if (m15418j()) {
            stringBuilder.append(", ");
            stringBuilder.append("collapseKey:");
            if (this.f13512g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13512g);
            }
        }
        if (m15419k()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13513h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13513h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
