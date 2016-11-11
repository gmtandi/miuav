package com.xiaomi.xmpush.thrift;

import com.tencent.open.SocialConstants;
import com.tencent.tauth.AuthActivity;
import com.xiaomi.market.sdk.C2537j;
import java.io.Serializable;
import java.nio.ByteBuffer;
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
import org.p122a.p137b.p181a.C3242a;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3247g;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.k */
public class C2748k implements Serializable, Cloneable, C2478b<C2748k, C2747a> {
    public static final Map<C2747a, C3243b> f13730i;
    private static final C3262m f13731j;
    private static final C3255e f13732k;
    private static final C3255e f13733l;
    private static final C3255e f13734m;
    private static final C3255e f13735n;
    private static final C3255e f13736o;
    private static final C3255e f13737p;
    private static final C3255e f13738q;
    private static final C3255e f13739r;
    public C2729a f13740a;
    public boolean f13741b;
    public boolean f13742c;
    public ByteBuffer f13743d;
    public String f13744e;
    public String f13745f;
    public C2740g f13746g;
    public C2734d f13747h;
    private BitSet f13748s;

    /* renamed from: com.xiaomi.xmpush.thrift.k.a */
    public enum C2747a {
        ACTION((short) 1, AuthActivity.ACTION_KEY),
        ENCRYPT_ACTION((short) 2, "encryptAction"),
        IS_REQUEST((short) 3, "isRequest"),
        PUSH_ACTION((short) 4, "pushAction"),
        APPID((short) 5, SocialConstants.PARAM_APP_ID),
        PACKAGE_NAME((short) 6, C2537j.f12839W),
        TARGET((short) 7, "target"),
        META_INFO((short) 8, "metaInfo");
        
        private static final Map<String, C2747a> f13726i;
        private final short f13728j;
        private final String f13729k;

        static {
            f13726i = new HashMap();
            Iterator it = EnumSet.allOf(C2747a.class).iterator();
            while (it.hasNext()) {
                C2747a c2747a = (C2747a) it.next();
                f13726i.put(c2747a.m15560a(), c2747a);
            }
        }

        private C2747a(short s, String str) {
            this.f13728j = s;
            this.f13729k = str;
        }

        public String m15560a() {
            return this.f13729k;
        }
    }

    static {
        f13731j = new C3262m("XmPushActionContainer");
        f13732k = new C3255e(AuthActivity.ACTION_KEY, (byte) 8, (short) 1);
        f13733l = new C3255e("encryptAction", (byte) 2, (short) 2);
        f13734m = new C3255e("isRequest", (byte) 2, (short) 3);
        f13735n = new C3255e("pushAction", (byte) 11, (short) 4);
        f13736o = new C3255e(SocialConstants.PARAM_APP_ID, (byte) 11, (short) 5);
        f13737p = new C3255e(C2537j.f12839W, (byte) 11, (short) 6);
        f13738q = new C3255e("target", (byte) 12, (short) 7);
        f13739r = new C3255e("metaInfo", (byte) 12, (short) 8);
        Map enumMap = new EnumMap(C2747a.class);
        enumMap.put(C2747a.ACTION, new C3243b(AuthActivity.ACTION_KEY, (byte) 1, new C3242a((byte) 16, C2729a.class)));
        enumMap.put(C2747a.ENCRYPT_ACTION, new C3243b("encryptAction", (byte) 1, new C3241c((byte) 2)));
        enumMap.put(C2747a.IS_REQUEST, new C3243b("isRequest", (byte) 1, new C3241c((byte) 2)));
        enumMap.put(C2747a.PUSH_ACTION, new C3243b("pushAction", (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2747a.APPID, new C3243b(SocialConstants.PARAM_APP_ID, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2747a.PACKAGE_NAME, new C3243b(C2537j.f12839W, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2747a.TARGET, new C3243b("target", (byte) 1, new C3247g((byte) 12, C2740g.class)));
        enumMap.put(C2747a.META_INFO, new C3243b("metaInfo", (byte) 2, new C3247g((byte) 12, C2734d.class)));
        f13730i = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2748k.class, f13730i);
    }

    public C2748k() {
        this.f13748s = new BitSet(2);
        this.f13741b = true;
        this.f13742c = true;
    }

    public C2729a m15561a() {
        return this.f13740a;
    }

    public C2748k m15562a(C2729a c2729a) {
        this.f13740a = c2729a;
        return this;
    }

    public C2748k m15563a(C2734d c2734d) {
        this.f13747h = c2734d;
        return this;
    }

    public C2748k m15564a(C2740g c2740g) {
        this.f13746g = c2740g;
        return this;
    }

    public C2748k m15565a(String str) {
        this.f13744e = str;
        return this;
    }

    public C2748k m15566a(ByteBuffer byteBuffer) {
        this.f13743d = byteBuffer;
        return this;
    }

    public C2748k m15567a(boolean z) {
        this.f13741b = z;
        m15573b(true);
        return this;
    }

    public void m15568a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (!m15578d()) {
                    throw new C3259i("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                } else if (m15579e()) {
                    m15589o();
                    return;
                } else {
                    throw new C3259i("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (i.f15757c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (i.f15756b != 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13740a = C2729a.m15399a(c3249h.m17956t());
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != (byte) 2) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13741b = c3249h.m17953q();
                    m15573b(true);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 2) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13742c = c3249h.m17953q();
                    m15577d(true);
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13743d = c3249h.m17960x();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13744e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13745f = c3249h.m17959w();
                        break;
                    }
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13746g = new C2740g();
                    this.f13746g.m15485a(c3249h);
                    break;
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 12) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13747h = new C2734d();
                    this.f13747h.m15427a(c3249h);
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public boolean m15569a(C2748k c2748k) {
        if (c2748k == null) {
            return false;
        }
        boolean b = m15574b();
        boolean b2 = c2748k.m15574b();
        if (((b || b2) && (!b || !b2 || !this.f13740a.equals(c2748k.f13740a))) || this.f13741b != c2748k.f13741b || this.f13742c != c2748k.f13742c) {
            return false;
        }
        b = m15581g();
        b2 = c2748k.m15581g();
        if ((b || b2) && (!b || !b2 || !this.f13743d.equals(c2748k.f13743d))) {
            return false;
        }
        b = m15583i();
        b2 = c2748k.m15583i();
        if ((b || b2) && (!b || !b2 || !this.f13744e.equals(c2748k.f13744e))) {
            return false;
        }
        b = m15585k();
        b2 = c2748k.m15585k();
        if ((b || b2) && (!b || !b2 || !this.f13745f.equals(c2748k.f13745f))) {
            return false;
        }
        b = m15586l();
        b2 = c2748k.m15586l();
        if ((b || b2) && (!b || !b2 || !this.f13746g.m15488a(c2748k.f13746g))) {
            return false;
        }
        b = m15588n();
        b2 = c2748k.m15588n();
        return !(b || b2) || (b && b2 && this.f13747h.m15429a(c2748k.f13747h));
    }

    public int m15570b(C2748k c2748k) {
        if (!getClass().equals(c2748k.getClass())) {
            return getClass().getName().compareTo(c2748k.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15574b()).compareTo(Boolean.valueOf(c2748k.m15574b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15574b()) {
            compareTo = C3269c.m18089a(this.f13740a, c2748k.f13740a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15578d()).compareTo(Boolean.valueOf(c2748k.m15578d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15578d()) {
            compareTo = C3269c.m18095a(this.f13741b, c2748k.f13741b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15579e()).compareTo(Boolean.valueOf(c2748k.m15579e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15579e()) {
            compareTo = C3269c.m18095a(this.f13742c, c2748k.f13742c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15581g()).compareTo(Boolean.valueOf(c2748k.m15581g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15581g()) {
            compareTo = C3269c.m18089a(this.f13743d, c2748k.f13743d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15583i()).compareTo(Boolean.valueOf(c2748k.m15583i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15583i()) {
            compareTo = C3269c.m18090a(this.f13744e, c2748k.f13744e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15585k()).compareTo(Boolean.valueOf(c2748k.m15585k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15585k()) {
            compareTo = C3269c.m18090a(this.f13745f, c2748k.f13745f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15586l()).compareTo(Boolean.valueOf(c2748k.m15586l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15586l()) {
            compareTo = C3269c.m18089a(this.f13746g, c2748k.f13746g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15588n()).compareTo(Boolean.valueOf(c2748k.m15588n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15588n()) {
            compareTo = C3269c.m18089a(this.f13747h, c2748k.f13747h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2748k m15571b(String str) {
        this.f13745f = str;
        return this;
    }

    public void m15572b(C3249h c3249h) {
        m15589o();
        c3249h.m17936a(f13731j);
        if (this.f13740a != null) {
            c3249h.m17932a(f13732k);
            c3249h.m17928a(this.f13740a.m15400a());
            c3249h.m17938b();
        }
        c3249h.m17932a(f13733l);
        c3249h.m17937a(this.f13741b);
        c3249h.m17938b();
        c3249h.m17932a(f13734m);
        c3249h.m17937a(this.f13742c);
        c3249h.m17938b();
        if (this.f13743d != null) {
            c3249h.m17932a(f13735n);
            c3249h.m17931a(this.f13743d);
            c3249h.m17938b();
        }
        if (this.f13744e != null && m15583i()) {
            c3249h.m17932a(f13736o);
            c3249h.m17930a(this.f13744e);
            c3249h.m17938b();
        }
        if (this.f13745f != null && m15585k()) {
            c3249h.m17932a(f13737p);
            c3249h.m17930a(this.f13745f);
            c3249h.m17938b();
        }
        if (this.f13746g != null) {
            c3249h.m17932a(f13738q);
            this.f13746g.m15490b(c3249h);
            c3249h.m17938b();
        }
        if (this.f13747h != null && m15588n()) {
            c3249h.m17932a(f13739r);
            this.f13747h.m15434b(c3249h);
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m15573b(boolean z) {
        this.f13748s.set(0, z);
    }

    public boolean m15574b() {
        return this.f13740a != null;
    }

    public C2748k m15575c(boolean z) {
        this.f13742c = z;
        m15577d(true);
        return this;
    }

    public boolean m15576c() {
        return this.f13741b;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15570b((C2748k) obj);
    }

    public void m15577d(boolean z) {
        this.f13748s.set(1, z);
    }

    public boolean m15578d() {
        return this.f13748s.get(0);
    }

    public boolean m15579e() {
        return this.f13748s.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2748k)) ? m15569a((C2748k) obj) : false;
    }

    public byte[] m15580f() {
        m15566a(C3269c.m18101c(this.f13743d));
        return this.f13743d.array();
    }

    public boolean m15581g() {
        return this.f13743d != null;
    }

    public String m15582h() {
        return this.f13744e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15583i() {
        return this.f13744e != null;
    }

    public String m15584j() {
        return this.f13745f;
    }

    public boolean m15585k() {
        return this.f13745f != null;
    }

    public boolean m15586l() {
        return this.f13746g != null;
    }

    public C2734d m15587m() {
        return this.f13747h;
    }

    public boolean m15588n() {
        return this.f13747h != null;
    }

    public void m15589o() {
        if (this.f13740a == null) {
            throw new C3259i("Required field 'action' was not present! Struct: " + toString());
        } else if (this.f13743d == null) {
            throw new C3259i("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.f13746g == null) {
            throw new C3259i("Required field 'target' was not present! Struct: " + toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionContainer(");
        stringBuilder.append("action:");
        if (this.f13740a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13740a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("encryptAction:");
        stringBuilder.append(this.f13741b);
        stringBuilder.append(", ");
        stringBuilder.append("isRequest:");
        stringBuilder.append(this.f13742c);
        stringBuilder.append(", ");
        stringBuilder.append("pushAction:");
        if (this.f13743d == null) {
            stringBuilder.append("null");
        } else {
            C3269c.m18098a(this.f13743d, stringBuilder);
        }
        if (m15583i()) {
            stringBuilder.append(", ");
            stringBuilder.append("appid:");
            if (this.f13744e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13744e);
            }
        }
        if (m15585k()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f13745f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13745f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("target:");
        if (this.f13746g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13746g);
        }
        if (m15588n()) {
            stringBuilder.append(", ");
            stringBuilder.append("metaInfo:");
            if (this.f13747h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13747h);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
