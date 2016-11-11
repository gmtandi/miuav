package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

/* renamed from: com.xiaomi.xmpush.thrift.i */
public class C2744i implements Serializable, Cloneable, C2478b<C2744i, C2743a> {
    public static final Map<C2743a, C3243b> f13660i;
    private static final C3262m f13661j;
    private static final C3255e f13662k;
    private static final C3255e f13663l;
    private static final C3255e f13664m;
    private static final C3255e f13665n;
    private static final C3255e f13666o;
    private static final C3255e f13667p;
    private static final C3255e f13668q;
    private static final C3255e f13669r;
    public String f13670a;
    public C2740g f13671b;
    public String f13672c;
    public String f13673d;
    public String f13674e;
    public List<String> f13675f;
    public String f13676g;
    public String f13677h;

    /* renamed from: com.xiaomi.xmpush.thrift.i.a */
    public enum C2743a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        CMD_NAME((short) 5, "cmdName"),
        CMD_ARGS((short) 6, "cmdArgs"),
        PACKAGE_NAME((short) 7, C2537j.f12839W),
        CATEGORY((short) 9, "category");
        
        private static final Map<String, C2743a> f13656i;
        private final short f13658j;
        private final String f13659k;

        static {
            f13656i = new HashMap();
            Iterator it = EnumSet.allOf(C2743a.class).iterator();
            while (it.hasNext()) {
                C2743a c2743a = (C2743a) it.next();
                f13656i.put(c2743a.m15519a(), c2743a);
            }
        }

        private C2743a(short s, String str) {
            this.f13658j = s;
            this.f13659k = str;
        }

        public String m15519a() {
            return this.f13659k;
        }
    }

    static {
        f13661j = new C3262m("XmPushActionCommand");
        f13662k = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13663l = new C3255e("target", (byte) 12, (short) 2);
        f13664m = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13665n = new C3255e("appId", (byte) 11, (short) 4);
        f13666o = new C3255e("cmdName", (byte) 11, (short) 5);
        f13667p = new C3255e("cmdArgs", (byte) 15, (short) 6);
        f13668q = new C3255e(C2537j.f12839W, (byte) 11, (short) 7);
        f13669r = new C3255e("category", (byte) 11, (short) 9);
        Map enumMap = new EnumMap(C2743a.class);
        enumMap.put(C2743a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2743a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2743a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2743a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2743a.CMD_NAME, new C3243b("cmdName", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2743a.CMD_ARGS, new C3243b("cmdArgs", (byte) 2, new C3244d((byte) 15, new C3241c((byte) 11))));
        enumMap.put(C2743a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2743a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13660i = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2744i.class, f13660i);
    }

    public C2744i m15520a(String str) {
        this.f13672c = str;
        return this;
    }

    public void m15521a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                m15538i();
                return;
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13670a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13671b = new C2740g();
                    this.f13671b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13672c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13673d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13674e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 15) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3256f m = c3249h.m17949m();
                    this.f13675f = new ArrayList(m.f15759b);
                    for (int i2 = 0; i2 < m.f15759b; i2++) {
                        this.f13675f.add(c3249h.m17959w());
                    }
                    c3249h.m17950n();
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13676g = c3249h.m17959w();
                        break;
                    }
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13677h = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15522a() {
        return this.f13670a != null;
    }

    public boolean m15523a(C2744i c2744i) {
        if (c2744i == null) {
            return false;
        }
        boolean a = m15522a();
        boolean a2 = c2744i.m15522a();
        if ((a || a2) && (!a || !a2 || !this.f13670a.equals(c2744i.f13670a))) {
            return false;
        }
        a = m15527b();
        a2 = c2744i.m15527b();
        if ((a || a2) && (!a || !a2 || !this.f13671b.m15488a(c2744i.f13671b))) {
            return false;
        }
        a = m15529c();
        a2 = c2744i.m15529c();
        if ((a || a2) && (!a || !a2 || !this.f13672c.equals(c2744i.f13672c))) {
            return false;
        }
        a = m15531d();
        a2 = c2744i.m15531d();
        if ((a || a2) && (!a || !a2 || !this.f13673d.equals(c2744i.f13673d))) {
            return false;
        }
        a = m15533e();
        a2 = c2744i.m15533e();
        if ((a || a2) && (!a || !a2 || !this.f13674e.equals(c2744i.f13674e))) {
            return false;
        }
        a = m15535f();
        a2 = c2744i.m15535f();
        if ((a || a2) && (!a || !a2 || !this.f13675f.equals(c2744i.f13675f))) {
            return false;
        }
        a = m15536g();
        a2 = c2744i.m15536g();
        if ((a || a2) && (!a || !a2 || !this.f13676g.equals(c2744i.f13676g))) {
            return false;
        }
        a = m15537h();
        a2 = c2744i.m15537h();
        return !(a || a2) || (a && a2 && this.f13677h.equals(c2744i.f13677h));
    }

    public int m15524b(C2744i c2744i) {
        if (!getClass().equals(c2744i.getClass())) {
            return getClass().getName().compareTo(c2744i.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15522a()).compareTo(Boolean.valueOf(c2744i.m15522a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15522a()) {
            compareTo = C3269c.m18090a(this.f13670a, c2744i.f13670a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15527b()).compareTo(Boolean.valueOf(c2744i.m15527b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15527b()) {
            compareTo = C3269c.m18089a(this.f13671b, c2744i.f13671b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15529c()).compareTo(Boolean.valueOf(c2744i.m15529c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15529c()) {
            compareTo = C3269c.m18090a(this.f13672c, c2744i.f13672c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15531d()).compareTo(Boolean.valueOf(c2744i.m15531d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15531d()) {
            compareTo = C3269c.m18090a(this.f13673d, c2744i.f13673d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15533e()).compareTo(Boolean.valueOf(c2744i.m15533e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15533e()) {
            compareTo = C3269c.m18090a(this.f13674e, c2744i.f13674e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15535f()).compareTo(Boolean.valueOf(c2744i.m15535f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15535f()) {
            compareTo = C3269c.m18092a(this.f13675f, c2744i.f13675f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15536g()).compareTo(Boolean.valueOf(c2744i.m15536g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15536g()) {
            compareTo = C3269c.m18090a(this.f13676g, c2744i.f13676g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15537h()).compareTo(Boolean.valueOf(c2744i.m15537h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15537h()) {
            compareTo = C3269c.m18090a(this.f13677h, c2744i.f13677h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2744i m15525b(String str) {
        this.f13673d = str;
        return this;
    }

    public void m15526b(C3249h c3249h) {
        m15538i();
        c3249h.m17936a(f13661j);
        if (this.f13670a != null && m15522a()) {
            c3249h.m17932a(f13662k);
            c3249h.m17930a(this.f13670a);
            c3249h.m17938b();
        }
        if (this.f13671b != null && m15527b()) {
            c3249h.m17932a(f13663l);
            this.f13671b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13672c != null) {
            c3249h.m17932a(f13664m);
            c3249h.m17930a(this.f13672c);
            c3249h.m17938b();
        }
        if (this.f13673d != null) {
            c3249h.m17932a(f13665n);
            c3249h.m17930a(this.f13673d);
            c3249h.m17938b();
        }
        if (this.f13674e != null) {
            c3249h.m17932a(f13666o);
            c3249h.m17930a(this.f13674e);
            c3249h.m17938b();
        }
        if (this.f13675f != null && m15535f()) {
            c3249h.m17932a(f13667p);
            c3249h.m17933a(new C3256f((byte) 11, this.f13675f.size()));
            for (String a : this.f13675f) {
                c3249h.m17930a(a);
            }
            c3249h.m17941e();
            c3249h.m17938b();
        }
        if (this.f13676g != null && m15536g()) {
            c3249h.m17932a(f13668q);
            c3249h.m17930a(this.f13676g);
            c3249h.m17938b();
        }
        if (this.f13677h != null && m15537h()) {
            c3249h.m17932a(f13669r);
            c3249h.m17930a(this.f13677h);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15527b() {
        return this.f13671b != null;
    }

    public C2744i m15528c(String str) {
        this.f13674e = str;
        return this;
    }

    public boolean m15529c() {
        return this.f13672c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15524b((C2744i) obj);
    }

    public void m15530d(String str) {
        if (this.f13675f == null) {
            this.f13675f = new ArrayList();
        }
        this.f13675f.add(str);
    }

    public boolean m15531d() {
        return this.f13673d != null;
    }

    public C2744i m15532e(String str) {
        this.f13676g = str;
        return this;
    }

    public boolean m15533e() {
        return this.f13674e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2744i)) ? m15523a((C2744i) obj) : false;
    }

    public C2744i m15534f(String str) {
        this.f13677h = str;
        return this;
    }

    public boolean m15535f() {
        return this.f13675f != null;
    }

    public boolean m15536g() {
        return this.f13676g != null;
    }

    public boolean m15537h() {
        return this.f13677h != null;
    }

    public int hashCode() {
        return 0;
    }

    public void m15538i() {
        if (this.f13672c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13673d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f13674e == null) {
            throw new C3259i("Required field 'cmdName' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionCommand(");
        Object obj2 = 1;
        if (m15522a()) {
            stringBuilder.append("debug:");
            if (this.f13670a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13670a);
            }
            obj2 = null;
        }
        if (m15527b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13671b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13671b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13672c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13672c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13673d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13673d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("cmdName:");
        if (this.f13674e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13674e);
        }
        if (m15535f()) {
            stringBuilder.append(", ");
            stringBuilder.append("cmdArgs:");
            if (this.f13675f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13675f);
            }
        }
        if (m15536g()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13676g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13676g);
            }
        }
        if (m15537h()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13677h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13677h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
