package com.xiaomi.xmpush.thrift;

import com.mi.live.openlivesdk.C2115a;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.open.SocialConstants;
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

/* renamed from: com.xiaomi.xmpush.thrift.p */
public class C2758p implements Serializable, Cloneable, C2478b<C2758p, C2757a> {
    public static final Map<C2757a, C3243b> f13895i;
    private static final C3262m f13896j;
    private static final C3255e f13897k;
    private static final C3255e f13898l;
    private static final C3255e f13899m;
    private static final C3255e f13900n;
    private static final C3255e f13901o;
    private static final C3255e f13902p;
    private static final C3255e f13903q;
    private static final C3255e f13904r;
    public String f13905a;
    public C2740g f13906b;
    public String f13907c;
    public String f13908d;
    public C2756o f13909e;
    public long f13910f;
    public String f13911g;
    public String f13912h;
    private BitSet f13913s;

    /* renamed from: com.xiaomi.xmpush.thrift.p.a */
    public enum C2757a {
        DEBUG((short) 1, C2115a.f11114c),
        TARGET((short) 2, "target"),
        ID((short) 3, LocaleUtil.INDONESIAN),
        APP_ID((short) 4, "appId"),
        REQUEST((short) 5, SocialConstants.TYPE_REQUEST),
        ERROR_CODE((short) 6, "errorCode"),
        REASON((short) 7, "reason"),
        CATEGORY((short) 8, "category");
        
        private static final Map<String, C2757a> f13891i;
        private final short f13893j;
        private final String f13894k;

        static {
            f13891i = new HashMap();
            Iterator it = EnumSet.allOf(C2757a.class).iterator();
            while (it.hasNext()) {
                C2757a c2757a = (C2757a) it.next();
                f13891i.put(c2757a.m15665a(), c2757a);
            }
        }

        private C2757a(short s, String str) {
            this.f13893j = s;
            this.f13894k = str;
        }

        public String m15665a() {
            return this.f13894k;
        }
    }

    static {
        f13896j = new C3262m("XmPushActionSendFeedbackResult");
        f13897k = new C3255e(C2115a.f11114c, (byte) 11, (short) 1);
        f13898l = new C3255e("target", (byte) 12, (short) 2);
        f13899m = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 3);
        f13900n = new C3255e("appId", (byte) 11, (short) 4);
        f13901o = new C3255e(SocialConstants.TYPE_REQUEST, (byte) 12, (short) 5);
        f13902p = new C3255e("errorCode", (byte) 10, (short) 6);
        f13903q = new C3255e("reason", (byte) 11, (short) 7);
        f13904r = new C3255e("category", (byte) 11, (short) 8);
        Map enumMap = new EnumMap(C2757a.class);
        enumMap.put(C2757a.DEBUG, new C3243b(C2115a.f11114c, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2757a.TARGET, new C3243b("target", (byte) 2, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2757a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2757a.APP_ID, new C3243b("appId", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2757a.REQUEST, new C3243b(SocialConstants.TYPE_REQUEST, (byte) 2, new C3247g((byte) 12, C2756o.class)));
        enumMap.put(C2757a.ERROR_CODE, new C3243b("errorCode", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2757a.REASON, new C3243b("reason", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2757a.CATEGORY, new C3243b("category", (byte) 2, new C3241c((byte) 11)));
        f13895i = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2758p.class, f13895i);
    }

    public C2758p() {
        this.f13913s = new BitSet(1);
    }

    public void m15666a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15676f()) {
                    m15679i();
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
                        this.f13905a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13906b = new C2740g();
                    this.f13906b.m15485a(c3249h);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13907c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13908d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13909e = new C2756o();
                    this.f13909e.m15654a(c3249h);
                    break;
                case Type.FLOAT /*6*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13910f = c3249h.m17957u();
                    m15667a(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13911g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13912h = c3249h.m17959w();
                        break;
                    }
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15667a(boolean z) {
        this.f13913s.set(0, z);
    }

    public boolean m15668a() {
        return this.f13905a != null;
    }

    public boolean m15669a(C2758p c2758p) {
        if (c2758p == null) {
            return false;
        }
        boolean a = m15668a();
        boolean a2 = c2758p.m15668a();
        if ((a || a2) && (!a || !a2 || !this.f13905a.equals(c2758p.f13905a))) {
            return false;
        }
        a = m15672b();
        a2 = c2758p.m15672b();
        if ((a || a2) && (!a || !a2 || !this.f13906b.m15488a(c2758p.f13906b))) {
            return false;
        }
        a = m15673c();
        a2 = c2758p.m15673c();
        if ((a || a2) && (!a || !a2 || !this.f13907c.equals(c2758p.f13907c))) {
            return false;
        }
        a = m15674d();
        a2 = c2758p.m15674d();
        if ((a || a2) && (!a || !a2 || !this.f13908d.equals(c2758p.f13908d))) {
            return false;
        }
        a = m15675e();
        a2 = c2758p.m15675e();
        if (((a || a2) && (!a || !a2 || !this.f13909e.m15656a(c2758p.f13909e))) || this.f13910f != c2758p.f13910f) {
            return false;
        }
        a = m15677g();
        a2 = c2758p.m15677g();
        if ((a || a2) && (!a || !a2 || !this.f13911g.equals(c2758p.f13911g))) {
            return false;
        }
        a = m15678h();
        a2 = c2758p.m15678h();
        return !(a || a2) || (a && a2 && this.f13912h.equals(c2758p.f13912h));
    }

    public int m15670b(C2758p c2758p) {
        if (!getClass().equals(c2758p.getClass())) {
            return getClass().getName().compareTo(c2758p.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15668a()).compareTo(Boolean.valueOf(c2758p.m15668a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15668a()) {
            compareTo = C3269c.m18090a(this.f13905a, c2758p.f13905a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15672b()).compareTo(Boolean.valueOf(c2758p.m15672b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15672b()) {
            compareTo = C3269c.m18089a(this.f13906b, c2758p.f13906b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15673c()).compareTo(Boolean.valueOf(c2758p.m15673c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15673c()) {
            compareTo = C3269c.m18090a(this.f13907c, c2758p.f13907c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15674d()).compareTo(Boolean.valueOf(c2758p.m15674d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15674d()) {
            compareTo = C3269c.m18090a(this.f13908d, c2758p.f13908d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15675e()).compareTo(Boolean.valueOf(c2758p.m15675e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15675e()) {
            compareTo = C3269c.m18089a(this.f13909e, c2758p.f13909e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15676f()).compareTo(Boolean.valueOf(c2758p.m15676f()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15676f()) {
            compareTo = C3269c.m18088a(this.f13910f, c2758p.f13910f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15677g()).compareTo(Boolean.valueOf(c2758p.m15677g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15677g()) {
            compareTo = C3269c.m18090a(this.f13911g, c2758p.f13911g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15678h()).compareTo(Boolean.valueOf(c2758p.m15678h()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15678h()) {
            compareTo = C3269c.m18090a(this.f13912h, c2758p.f13912h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public void m15671b(C3249h c3249h) {
        m15679i();
        c3249h.m17936a(f13896j);
        if (this.f13905a != null && m15668a()) {
            c3249h.m17932a(f13897k);
            c3249h.m17930a(this.f13905a);
            c3249h.m17938b();
        }
        if (this.f13906b != null && m15672b()) {
            c3249h.m17932a(f13898l);
            this.f13906b.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13907c != null) {
            c3249h.m17932a(f13899m);
            c3249h.m17930a(this.f13907c);
            c3249h.m17938b();
        }
        if (this.f13908d != null) {
            c3249h.m17932a(f13900n);
            c3249h.m17930a(this.f13908d);
            c3249h.m17938b();
        }
        if (this.f13909e != null && m15675e()) {
            c3249h.m17932a(f13901o);
            this.f13909e.m15658b(c3249h);
            c3249h.m17938b();
        }
        c3249h.m17932a(f13902p);
        c3249h.m17929a(this.f13910f);
        c3249h.m17938b();
        if (this.f13911g != null && m15677g()) {
            c3249h.m17932a(f13903q);
            c3249h.m17930a(this.f13911g);
            c3249h.m17938b();
        }
        if (this.f13912h != null && m15678h()) {
            c3249h.m17932a(f13904r);
            c3249h.m17930a(this.f13912h);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public boolean m15672b() {
        return this.f13906b != null;
    }

    public boolean m15673c() {
        return this.f13907c != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15670b((C2758p) obj);
    }

    public boolean m15674d() {
        return this.f13908d != null;
    }

    public boolean m15675e() {
        return this.f13909e != null;
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2758p)) ? m15669a((C2758p) obj) : false;
    }

    public boolean m15676f() {
        return this.f13913s.get(0);
    }

    public boolean m15677g() {
        return this.f13911g != null;
    }

    public boolean m15678h() {
        return this.f13912h != null;
    }

    public int hashCode() {
        return 0;
    }

    public void m15679i() {
        if (this.f13907c == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f13908d == null) {
            throw new C3259i("Required field 'appId' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("XmPushActionSendFeedbackResult(");
        Object obj2 = 1;
        if (m15668a()) {
            stringBuilder.append("debug:");
            if (this.f13905a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13905a);
            }
            obj2 = null;
        }
        if (m15672b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("target:");
            if (this.f13906b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13906b);
            }
        } else {
            obj = obj2;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.f13907c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13907c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("appId:");
        if (this.f13908d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13908d);
        }
        if (m15675e()) {
            stringBuilder.append(", ");
            stringBuilder.append("request:");
            if (this.f13909e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13909e);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.f13910f);
        if (m15677g()) {
            stringBuilder.append(", ");
            stringBuilder.append("reason:");
            if (this.f13911g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13911g);
            }
        }
        if (m15678h()) {
            stringBuilder.append(", ");
            stringBuilder.append("category:");
            if (this.f13912h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13912h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
