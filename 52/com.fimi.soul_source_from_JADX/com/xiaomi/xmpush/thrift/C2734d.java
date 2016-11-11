package com.xiaomi.xmpush.thrift;

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
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3269c;
import org.p122a.p137b.p181a.C3241c;
import org.p122a.p137b.p181a.C3243b;
import org.p122a.p137b.p181a.C3245e;
import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3255e;
import org.p122a.p137b.p182b.C3257g;
import org.p122a.p137b.p182b.C3259i;
import org.p122a.p137b.p182b.C3260k;
import org.p122a.p137b.p182b.C3262m;

/* renamed from: com.xiaomi.xmpush.thrift.d */
public class C2734d implements Serializable, Cloneable, C2478b<C2734d, C2733a> {
    public static final Map<C2733a, C3243b> f13529k;
    private static final C3262m f13530l;
    private static final C3255e f13531m;
    private static final C3255e f13532n;
    private static final C3255e f13533o;
    private static final C3255e f13534p;
    private static final C3255e f13535q;
    private static final C3255e f13536r;
    private static final C3255e f13537s;
    private static final C3255e f13538t;
    private static final C3255e f13539u;
    private static final C3255e f13540v;
    public String f13541a;
    public long f13542b;
    public String f13543c;
    public String f13544d;
    public String f13545e;
    public int f13546f;
    public String f13547g;
    public int f13548h;
    public int f13549i;
    public Map<String, String> f13550j;
    private BitSet f13551w;

    /* renamed from: com.xiaomi.xmpush.thrift.d.a */
    public enum C2733a {
        ID((short) 1, LocaleUtil.INDONESIAN),
        MESSAGE_TS((short) 2, "messageTs"),
        TOPIC((short) 3, "topic"),
        TITLE((short) 4, SocialConstants.PARAM_TITLE),
        DESCRIPTION((short) 5, SocialConstants.PARAM_COMMENT),
        NOTIFY_TYPE((short) 6, "notifyType"),
        URL((short) 7, SocialConstants.PARAM_URL),
        PASS_THROUGH((short) 8, "passThrough"),
        NOTIFY_ID((short) 9, "notifyId"),
        EXTRA((short) 10, "extra");
        
        private static final Map<String, C2733a> f13525k;
        private final short f13527l;
        private final String f13528m;

        static {
            f13525k = new HashMap();
            Iterator it = EnumSet.allOf(C2733a.class).iterator();
            while (it.hasNext()) {
                C2733a c2733a = (C2733a) it.next();
                f13525k.put(c2733a.m15421a(), c2733a);
            }
        }

        private C2733a(short s, String str) {
            this.f13527l = s;
            this.f13528m = str;
        }

        public String m15421a() {
            return this.f13528m;
        }
    }

    static {
        f13530l = new C3262m("PushMetaInfo");
        f13531m = new C3255e(LocaleUtil.INDONESIAN, (byte) 11, (short) 1);
        f13532n = new C3255e("messageTs", (byte) 10, (short) 2);
        f13533o = new C3255e("topic", (byte) 11, (short) 3);
        f13534p = new C3255e(SocialConstants.PARAM_TITLE, (byte) 11, (short) 4);
        f13535q = new C3255e(SocialConstants.PARAM_COMMENT, (byte) 11, (short) 5);
        f13536r = new C3255e("notifyType", (byte) 8, (short) 6);
        f13537s = new C3255e(SocialConstants.PARAM_URL, (byte) 11, (short) 7);
        f13538t = new C3255e("passThrough", (byte) 8, (short) 8);
        f13539u = new C3255e("notifyId", (byte) 8, (short) 9);
        f13540v = new C3255e("extra", (byte) 13, (short) 10);
        Map enumMap = new EnumMap(C2733a.class);
        enumMap.put(C2733a.ID, new C3243b(LocaleUtil.INDONESIAN, (byte) 1, new C3241c((byte) 11)));
        enumMap.put(C2733a.MESSAGE_TS, new C3243b("messageTs", (byte) 1, new C3241c((byte) 10)));
        enumMap.put(C2733a.TOPIC, new C3243b("topic", (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2733a.TITLE, new C3243b(SocialConstants.PARAM_TITLE, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2733a.DESCRIPTION, new C3243b(SocialConstants.PARAM_COMMENT, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2733a.NOTIFY_TYPE, new C3243b("notifyType", (byte) 2, new C3241c((byte) 8)));
        enumMap.put(C2733a.URL, new C3243b(SocialConstants.PARAM_URL, (byte) 2, new C3241c((byte) 11)));
        enumMap.put(C2733a.PASS_THROUGH, new C3243b("passThrough", (byte) 2, new C3241c((byte) 8)));
        enumMap.put(C2733a.NOTIFY_ID, new C3243b("notifyId", (byte) 2, new C3241c((byte) 8)));
        enumMap.put(C2733a.EXTRA, new C3243b("extra", (byte) 2, new C3245e((byte) 13, new C3241c((byte) 11), new C3241c((byte) 11))));
        f13529k = Collections.unmodifiableMap(enumMap);
        C3243b.m17921a(C2734d.class, f13529k);
    }

    public C2734d() {
        this.f13551w = new BitSet(4);
    }

    public C2734d(C2734d c2734d) {
        this.f13551w = new BitSet(4);
        this.f13551w.clear();
        this.f13551w.or(c2734d.f13551w);
        if (c2734d.m15439c()) {
            this.f13541a = c2734d.f13541a;
        }
        this.f13542b = c2734d.f13542b;
        if (c2734d.m15445g()) {
            this.f13543c = c2734d.f13543c;
        }
        if (c2734d.m15447i()) {
            this.f13544d = c2734d.f13544d;
        }
        if (c2734d.m15449k()) {
            this.f13545e = c2734d.f13545e;
        }
        this.f13546f = c2734d.f13546f;
        if (c2734d.m15452n()) {
            this.f13547g = c2734d.f13547g;
        }
        this.f13548h = c2734d.f13548h;
        this.f13549i = c2734d.f13549i;
        if (c2734d.m15458t()) {
            Map hashMap = new HashMap();
            for (Entry entry : c2734d.f13550j.entrySet()) {
                hashMap.put((String) entry.getKey(), (String) entry.getValue());
            }
            this.f13550j = hashMap;
        }
    }

    public C2734d m15422a() {
        return new C2734d(this);
    }

    public C2734d m15423a(int i) {
        this.f13546f = i;
        m15435b(true);
        return this;
    }

    public C2734d m15424a(String str) {
        this.f13541a = str;
        return this;
    }

    public C2734d m15425a(Map<String, String> map) {
        this.f13550j = map;
        return this;
    }

    public void m15426a(String str, String str2) {
        if (this.f13550j == null) {
            this.f13550j = new HashMap();
        }
        this.f13550j.put(str, str2);
    }

    public void m15427a(C3249h c3249h) {
        c3249h.m17943g();
        while (true) {
            C3255e i = c3249h.m17945i();
            if (i.f15756b == null) {
                c3249h.m17944h();
                if (m15443e()) {
                    m15459u();
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
                        this.f13541a = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (i.f15756b != 10) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13542b = c3249h.m17957u();
                    m15428a(true);
                    break;
                case Type.BYTE /*3*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13543c = c3249h.m17959w();
                        break;
                    }
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13544d = c3249h.m17959w();
                        break;
                    }
                case Type.INT /*5*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13545e = c3249h.m17959w();
                        break;
                    }
                case Type.FLOAT /*6*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13546f = c3249h.m17956t();
                    m15435b(true);
                    break;
                case Type.LONG /*7*/:
                    if (i.f15756b != (byte) 11) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    } else {
                        this.f13547g = c3249h.m17959w();
                        break;
                    }
                case Type.DOUBLE /*8*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13548h = c3249h.m17956t();
                    m15438c(true);
                    break;
                case Type.ARRAY /*9*/:
                    if (i.f15756b != (byte) 8) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    this.f13549i = c3249h.m17956t();
                    m15442d(true);
                    break;
                case Type.OBJECT /*10*/:
                    if (i.f15756b != 13) {
                        C3260k.m18060a(c3249h, i.f15756b);
                        break;
                    }
                    C3257g k = c3249h.m17947k();
                    this.f13550j = new HashMap(k.f15762c * 2);
                    for (int i2 = 0; i2 < k.f15762c; i2++) {
                        this.f13550j.put(c3249h.m17959w(), c3249h.m17959w());
                    }
                    c3249h.m17948l();
                    break;
                default:
                    C3260k.m18060a(c3249h, i.f15756b);
                    break;
            }
            c3249h.m17946j();
        }
    }

    public void m15428a(boolean z) {
        this.f13551w.set(0, z);
    }

    public boolean m15429a(C2734d c2734d) {
        if (c2734d == null) {
            return false;
        }
        boolean c = m15439c();
        boolean c2 = c2734d.m15439c();
        if (((c || c2) && (!c || !c2 || !this.f13541a.equals(c2734d.f13541a))) || this.f13542b != c2734d.f13542b) {
            return false;
        }
        c = m15445g();
        c2 = c2734d.m15445g();
        if ((c || c2) && (!c || !c2 || !this.f13543c.equals(c2734d.f13543c))) {
            return false;
        }
        c = m15447i();
        c2 = c2734d.m15447i();
        if ((c || c2) && (!c || !c2 || !this.f13544d.equals(c2734d.f13544d))) {
            return false;
        }
        c = m15449k();
        c2 = c2734d.m15449k();
        if ((c || c2) && (!c || !c2 || !this.f13545e.equals(c2734d.f13545e))) {
            return false;
        }
        c = m15451m();
        c2 = c2734d.m15451m();
        if ((c || c2) && (!c || !c2 || this.f13546f != c2734d.f13546f)) {
            return false;
        }
        c = m15452n();
        c2 = c2734d.m15452n();
        if ((c || c2) && (!c || !c2 || !this.f13547g.equals(c2734d.f13547g))) {
            return false;
        }
        c = m15454p();
        c2 = c2734d.m15454p();
        if ((c || c2) && (!c || !c2 || this.f13548h != c2734d.f13548h)) {
            return false;
        }
        c = m15456r();
        c2 = c2734d.m15456r();
        if ((c || c2) && (!c || !c2 || this.f13549i != c2734d.f13549i)) {
            return false;
        }
        c = m15458t();
        c2 = c2734d.m15458t();
        return !(c || c2) || (c && c2 && this.f13550j.equals(c2734d.f13550j));
    }

    public int m15430b(C2734d c2734d) {
        if (!getClass().equals(c2734d.getClass())) {
            return getClass().getName().compareTo(c2734d.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m15439c()).compareTo(Boolean.valueOf(c2734d.m15439c()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15439c()) {
            compareTo = C3269c.m18090a(this.f13541a, c2734d.f13541a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15443e()).compareTo(Boolean.valueOf(c2734d.m15443e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15443e()) {
            compareTo = C3269c.m18088a(this.f13542b, c2734d.f13542b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15445g()).compareTo(Boolean.valueOf(c2734d.m15445g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15445g()) {
            compareTo = C3269c.m18090a(this.f13543c, c2734d.f13543c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15447i()).compareTo(Boolean.valueOf(c2734d.m15447i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15447i()) {
            compareTo = C3269c.m18090a(this.f13544d, c2734d.f13544d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15449k()).compareTo(Boolean.valueOf(c2734d.m15449k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15449k()) {
            compareTo = C3269c.m18090a(this.f13545e, c2734d.f13545e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15451m()).compareTo(Boolean.valueOf(c2734d.m15451m()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15451m()) {
            compareTo = C3269c.m18087a(this.f13546f, c2734d.f13546f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15452n()).compareTo(Boolean.valueOf(c2734d.m15452n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15452n()) {
            compareTo = C3269c.m18090a(this.f13547g, c2734d.f13547g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15454p()).compareTo(Boolean.valueOf(c2734d.m15454p()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15454p()) {
            compareTo = C3269c.m18087a(this.f13548h, c2734d.f13548h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15456r()).compareTo(Boolean.valueOf(c2734d.m15456r()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15456r()) {
            compareTo = C3269c.m18087a(this.f13549i, c2734d.f13549i);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(m15458t()).compareTo(Boolean.valueOf(c2734d.m15458t()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m15458t()) {
            compareTo = C3269c.m18093a(this.f13550j, c2734d.f13550j);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public C2734d m15431b(int i) {
        this.f13548h = i;
        m15438c(true);
        return this;
    }

    public C2734d m15432b(String str) {
        this.f13543c = str;
        return this;
    }

    public String m15433b() {
        return this.f13541a;
    }

    public void m15434b(C3249h c3249h) {
        m15459u();
        c3249h.m17936a(f13530l);
        if (this.f13541a != null) {
            c3249h.m17932a(f13531m);
            c3249h.m17930a(this.f13541a);
            c3249h.m17938b();
        }
        c3249h.m17932a(f13532n);
        c3249h.m17929a(this.f13542b);
        c3249h.m17938b();
        if (this.f13543c != null && m15445g()) {
            c3249h.m17932a(f13533o);
            c3249h.m17930a(this.f13543c);
            c3249h.m17938b();
        }
        if (this.f13544d != null && m15447i()) {
            c3249h.m17932a(f13534p);
            c3249h.m17930a(this.f13544d);
            c3249h.m17938b();
        }
        if (this.f13545e != null && m15449k()) {
            c3249h.m17932a(f13535q);
            c3249h.m17930a(this.f13545e);
            c3249h.m17938b();
        }
        if (m15451m()) {
            c3249h.m17932a(f13536r);
            c3249h.m17928a(this.f13546f);
            c3249h.m17938b();
        }
        if (this.f13547g != null && m15452n()) {
            c3249h.m17932a(f13537s);
            c3249h.m17930a(this.f13547g);
            c3249h.m17938b();
        }
        if (m15454p()) {
            c3249h.m17932a(f13538t);
            c3249h.m17928a(this.f13548h);
            c3249h.m17938b();
        }
        if (m15456r()) {
            c3249h.m17932a(f13539u);
            c3249h.m17928a(this.f13549i);
            c3249h.m17938b();
        }
        if (this.f13550j != null && m15458t()) {
            c3249h.m17932a(f13540v);
            c3249h.m17934a(new C3257g((byte) 11, (byte) 11, this.f13550j.size()));
            for (Entry entry : this.f13550j.entrySet()) {
                c3249h.m17930a((String) entry.getKey());
                c3249h.m17930a((String) entry.getValue());
            }
            c3249h.m17940d();
            c3249h.m17938b();
        }
        c3249h.m17939c();
        c3249h.m17926a();
    }

    public void m15435b(boolean z) {
        this.f13551w.set(1, z);
    }

    public C2734d m15436c(int i) {
        this.f13549i = i;
        m15442d(true);
        return this;
    }

    public C2734d m15437c(String str) {
        this.f13544d = str;
        return this;
    }

    public void m15438c(boolean z) {
        this.f13551w.set(2, z);
    }

    public boolean m15439c() {
        return this.f13541a != null;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m15430b((C2734d) obj);
    }

    public long m15440d() {
        return this.f13542b;
    }

    public C2734d m15441d(String str) {
        this.f13545e = str;
        return this;
    }

    public void m15442d(boolean z) {
        this.f13551w.set(3, z);
    }

    public boolean m15443e() {
        return this.f13551w.get(0);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof C2734d)) ? m15429a((C2734d) obj) : false;
    }

    public String m15444f() {
        return this.f13543c;
    }

    public boolean m15445g() {
        return this.f13543c != null;
    }

    public String m15446h() {
        return this.f13544d;
    }

    public int hashCode() {
        return 0;
    }

    public boolean m15447i() {
        return this.f13544d != null;
    }

    public String m15448j() {
        return this.f13545e;
    }

    public boolean m15449k() {
        return this.f13545e != null;
    }

    public int m15450l() {
        return this.f13546f;
    }

    public boolean m15451m() {
        return this.f13551w.get(1);
    }

    public boolean m15452n() {
        return this.f13547g != null;
    }

    public int m15453o() {
        return this.f13548h;
    }

    public boolean m15454p() {
        return this.f13551w.get(2);
    }

    public int m15455q() {
        return this.f13549i;
    }

    public boolean m15456r() {
        return this.f13551w.get(3);
    }

    public Map<String, String> m15457s() {
        return this.f13550j;
    }

    public boolean m15458t() {
        return this.f13550j != null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMetaInfo(");
        stringBuilder.append("id:");
        if (this.f13541a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f13541a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.f13542b);
        if (m15445g()) {
            stringBuilder.append(", ");
            stringBuilder.append("topic:");
            if (this.f13543c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13543c);
            }
        }
        if (m15447i()) {
            stringBuilder.append(", ");
            stringBuilder.append("title:");
            if (this.f13544d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13544d);
            }
        }
        if (m15449k()) {
            stringBuilder.append(", ");
            stringBuilder.append("description:");
            if (this.f13545e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13545e);
            }
        }
        if (m15451m()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyType:");
            stringBuilder.append(this.f13546f);
        }
        if (m15452n()) {
            stringBuilder.append(", ");
            stringBuilder.append("url:");
            if (this.f13547g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13547g);
            }
        }
        if (m15454p()) {
            stringBuilder.append(", ");
            stringBuilder.append("passThrough:");
            stringBuilder.append(this.f13548h);
        }
        if (m15456r()) {
            stringBuilder.append(", ");
            stringBuilder.append("notifyId:");
            stringBuilder.append(this.f13549i);
        }
        if (m15458t()) {
            stringBuilder.append(", ");
            stringBuilder.append("extra:");
            if (this.f13550j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f13550j);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m15459u() {
        if (this.f13541a == null) {
            throw new C3259i("Required field 'id' was not present! Struct: " + toString());
        }
    }
}
