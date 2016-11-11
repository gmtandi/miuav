package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smack.util.C2718g;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.xiaomi.smack.packet.d */
public abstract class C2694d {
    private static String f13307a;
    protected static final String f13308b;
    public static final DateFormat f13309c;
    private static String f13310d;
    private static long f13311e;
    private String f13312f;
    private String f13313g;
    private String f13314h;
    private String f13315i;
    private String f13316j;
    private String f13317k;
    private List<C2692a> f13318l;
    private final Map<String, Object> f13319m;
    private C2702h f13320n;

    static {
        f13308b = Locale.getDefault().getLanguage().toLowerCase();
        f13307a = null;
        f13309c = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        f13309c.setTimeZone(TimeZone.getTimeZone("UTC"));
        f13310d = C2718g.m15357a(5) + "-";
        f13311e = 0;
    }

    public C2694d() {
        this.f13312f = f13307a;
        this.f13313g = null;
        this.f13314h = null;
        this.f13315i = null;
        this.f13316j = null;
        this.f13317k = null;
        this.f13318l = new CopyOnWriteArrayList();
        this.f13319m = new HashMap();
        this.f13320n = null;
    }

    public C2694d(Bundle bundle) {
        this.f13312f = f13307a;
        this.f13313g = null;
        this.f13314h = null;
        this.f13315i = null;
        this.f13316j = null;
        this.f13317k = null;
        this.f13318l = new CopyOnWriteArrayList();
        this.f13319m = new HashMap();
        this.f13320n = null;
        this.f13314h = bundle.getString("ext_to");
        this.f13315i = bundle.getString("ext_from");
        this.f13316j = bundle.getString("ext_chid");
        this.f13313g = bundle.getString("ext_pkt_id");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f13318l = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                C2692a a = C2692a.m15214a((Bundle) parcelable);
                if (a != null) {
                    this.f13318l.add(a);
                }
            }
        }
        Bundle bundle2 = bundle.getBundle("ext_ERROR");
        if (bundle2 != null) {
            this.f13320n = new C2702h(bundle2);
        }
    }

    public static synchronized String m15226j() {
        String stringBuilder;
        synchronized (C2694d.class) {
            StringBuilder append = new StringBuilder().append(f13310d);
            long j = f13311e;
            f13311e = 1 + j;
            stringBuilder = append.append(Long.toString(j)).toString();
        }
        return stringBuilder;
    }

    public static String m15227u() {
        return f13308b;
    }

    public abstract String m15228a();

    public void m15229a(C2692a c2692a) {
        this.f13318l.add(c2692a);
    }

    public void m15230a(C2702h c2702h) {
        this.f13320n = c2702h;
    }

    public Bundle a_() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.f13312f)) {
            bundle.putString("ext_ns", this.f13312f);
        }
        if (!TextUtils.isEmpty(this.f13315i)) {
            bundle.putString("ext_from", this.f13315i);
        }
        if (!TextUtils.isEmpty(this.f13314h)) {
            bundle.putString("ext_to", this.f13314h);
        }
        if (!TextUtils.isEmpty(this.f13313g)) {
            bundle.putString("ext_pkt_id", this.f13313g);
        }
        if (!TextUtils.isEmpty(this.f13316j)) {
            bundle.putString("ext_chid", this.f13316j);
        }
        if (this.f13320n != null) {
            bundle.putBundle("ext_ERROR", this.f13320n.m15283c());
        }
        if (this.f13318l != null) {
            Parcelable[] parcelableArr = new Bundle[this.f13318l.size()];
            int i = 0;
            for (C2692a e : this.f13318l) {
                int i2;
                Bundle e2 = e.m15223e();
                if (e2 != null) {
                    i2 = i + 1;
                    parcelableArr[i] = e2;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            bundle.putParcelableArray("ext_exts", parcelableArr);
        }
        return bundle;
    }

    public C2692a m15231b(String str, String str2) {
        for (C2692a c2692a : this.f13318l) {
            if ((str2 == null || str2.equals(c2692a.m15219b())) && str.equals(c2692a.m15217a())) {
                return c2692a;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r5 = (com.xiaomi.smack.packet.C2694d) r5;
        r2 = r4.f13320n;
        if (r2 == 0) goto L_0x0083;
    L_0x0018:
        r2 = r4.f13320n;
        r3 = r5.f13320n;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0022:
        r2 = r4.f13315i;
        if (r2 == 0) goto L_0x0089;
    L_0x0026:
        r2 = r4.f13315i;
        r3 = r5.f13315i;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0030:
        r2 = r4.f13318l;
        r3 = r5.f13318l;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x003a:
        r2 = r4.f13313g;
        if (r2 == 0) goto L_0x008f;
    L_0x003e:
        r2 = r4.f13313g;
        r3 = r5.f13313g;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0048:
        r2 = r4.f13316j;
        if (r2 == 0) goto L_0x0095;
    L_0x004c:
        r2 = r4.f13316j;
        r3 = r5.f13316j;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0056:
        r2 = r4.f13319m;
        if (r2 == 0) goto L_0x009b;
    L_0x005a:
        r2 = r4.f13319m;
        r3 = r5.f13319m;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0064:
        r2 = r4.f13314h;
        if (r2 == 0) goto L_0x00a1;
    L_0x0068:
        r2 = r4.f13314h;
        r3 = r5.f13314h;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0072:
        r2 = r4.f13312f;
        if (r2 == 0) goto L_0x00a7;
    L_0x0076:
        r2 = r4.f13312f;
        r3 = r5.f13312f;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0081;
    L_0x0080:
        r0 = r1;
    L_0x0081:
        r1 = r0;
        goto L_0x0005;
    L_0x0083:
        r2 = r5.f13320n;
        if (r2 == 0) goto L_0x0022;
    L_0x0087:
        goto L_0x0005;
    L_0x0089:
        r2 = r5.f13315i;
        if (r2 == 0) goto L_0x0030;
    L_0x008d:
        goto L_0x0005;
    L_0x008f:
        r2 = r5.f13313g;
        if (r2 == 0) goto L_0x0048;
    L_0x0093:
        goto L_0x0005;
    L_0x0095:
        r2 = r5.f13316j;
        if (r2 == 0) goto L_0x0056;
    L_0x0099:
        goto L_0x0005;
    L_0x009b:
        r2 = r5.f13319m;
        if (r2 == 0) goto L_0x0064;
    L_0x009f:
        goto L_0x0005;
    L_0x00a1:
        r2 = r5.f13314h;
        if (r2 == 0) goto L_0x0072;
    L_0x00a5:
        goto L_0x0005;
    L_0x00a7:
        r2 = r5.f13312f;
        if (r2 != 0) goto L_0x0080;
    L_0x00ab:
        goto L_0x0081;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.packet.d.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.f13316j != null ? this.f13316j.hashCode() : 0) + (((this.f13315i != null ? this.f13315i.hashCode() : 0) + (((this.f13314h != null ? this.f13314h.hashCode() : 0) + (((this.f13313g != null ? this.f13313g.hashCode() : 0) + ((this.f13312f != null ? this.f13312f.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31) + this.f13318l.hashCode()) * 31) + this.f13319m.hashCode()) * 31;
        if (this.f13320n != null) {
            i = this.f13320n.hashCode();
        }
        return hashCode + i;
    }

    public String m15232k() {
        if ("ID_NOT_AVAILABLE".equals(this.f13313g)) {
            return null;
        }
        if (this.f13313g == null) {
            this.f13313g = C2694d.m15226j();
        }
        return this.f13313g;
    }

    public void m15233k(String str) {
        this.f13313g = str;
    }

    public String m15234l() {
        return this.f13316j;
    }

    public void m15235l(String str) {
        this.f13316j = str;
    }

    public String m15236m() {
        return this.f13314h;
    }

    public void m15237m(String str) {
        this.f13314h = str;
    }

    public String m15238n() {
        return this.f13315i;
    }

    public void m15239n(String str) {
        this.f13315i = str;
    }

    public String m15240o() {
        return this.f13317k;
    }

    public void m15241o(String str) {
        this.f13317k = str;
    }

    public C2692a m15242p(String str) {
        return m15231b(str, null);
    }

    public C2702h m15243p() {
        return this.f13320n;
    }

    public synchronized Object m15244q(String str) {
        return this.f13319m == null ? null : this.f13319m.get(str);
    }

    public synchronized Collection<C2692a> m15245q() {
        return this.f13318l == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(this.f13318l));
    }

    public synchronized Collection<String> m15246r() {
        return this.f13319m == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(this.f13319m.keySet()));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized java.lang.String m15247s() {
        /*
        r8 = this;
        r4 = 0;
        monitor-enter(r8);
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0023 }
        r6.<init>();	 Catch:{ all -> 0x0023 }
        r1 = r8.m15245q();	 Catch:{ all -> 0x0023 }
        r2 = r1.iterator();	 Catch:{ all -> 0x0023 }
    L_0x000f:
        r1 = r2.hasNext();	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0026;
    L_0x0015:
        r1 = r2.next();	 Catch:{ all -> 0x0023 }
        r1 = (com.xiaomi.smack.packet.C2692a) r1;	 Catch:{ all -> 0x0023 }
        r1 = r1.m15213d();	 Catch:{ all -> 0x0023 }
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x000f;
    L_0x0023:
        r1 = move-exception;
        monitor-exit(r8);
        throw r1;
    L_0x0026:
        r1 = r8.f13319m;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0149;
    L_0x002a:
        r1 = r8.f13319m;	 Catch:{ all -> 0x0023 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0023 }
        if (r1 != 0) goto L_0x0149;
    L_0x0032:
        r1 = "<properties xmlns=\"http://www.jivesoftware.com/xmlns/xmpp/properties\">";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r8.m15246r();	 Catch:{ all -> 0x0023 }
        r7 = r1.iterator();	 Catch:{ all -> 0x0023 }
    L_0x003f:
        r1 = r7.hasNext();	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0144;
    L_0x0045:
        r1 = r7.next();	 Catch:{ all -> 0x0023 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0023 }
        r2 = r8.m15244q(r1);	 Catch:{ all -> 0x0023 }
        r3 = "<property>";
        r6.append(r3);	 Catch:{ all -> 0x0023 }
        r3 = "<name>";
        r3 = r6.append(r3);	 Catch:{ all -> 0x0023 }
        r1 = com.xiaomi.smack.util.C2718g.m15358a(r1);	 Catch:{ all -> 0x0023 }
        r1 = r3.append(r1);	 Catch:{ all -> 0x0023 }
        r3 = "</name>";
        r1.append(r3);	 Catch:{ all -> 0x0023 }
        r1 = "<value type=\"";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r2 instanceof java.lang.Integer;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0085;
    L_0x0070:
        r1 = "integer\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
    L_0x007f:
        r1 = "</property>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x003f;
    L_0x0085:
        r1 = r2 instanceof java.lang.Long;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x0099;
    L_0x0089:
        r1 = "long\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x0099:
        r1 = r2 instanceof java.lang.Float;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00ad;
    L_0x009d:
        r1 = "float\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00ad:
        r1 = r2 instanceof java.lang.Double;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00c1;
    L_0x00b1:
        r1 = "double\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00c1:
        r1 = r2 instanceof java.lang.Boolean;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00d5;
    L_0x00c5:
        r1 = "boolean\">";
        r1 = r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0023 }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00d5:
        r1 = r2 instanceof java.lang.String;	 Catch:{ all -> 0x0023 }
        if (r1 == 0) goto L_0x00ef;
    L_0x00d9:
        r1 = "string\">";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r0 = r2;
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0023 }
        r1 = r0;
        r1 = com.xiaomi.smack.util.C2718g.m15358a(r1);	 Catch:{ all -> 0x0023 }
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        r1 = "</value>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
        goto L_0x007f;
    L_0x00ef:
        r5 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0121, all -> 0x0136 }
        r5.<init>();	 Catch:{ Exception -> 0x0121, all -> 0x0136 }
        r3 = new java.io.ObjectOutputStream;	 Catch:{ Exception -> 0x0160, all -> 0x0157 }
        r3.<init>(r5);	 Catch:{ Exception -> 0x0160, all -> 0x0157 }
        r3.writeObject(r2);	 Catch:{ Exception -> 0x0164, all -> 0x015a }
        r1 = "java-object\">";
        r6.append(r1);	 Catch:{ Exception -> 0x0164, all -> 0x015a }
        r1 = r5.toByteArray();	 Catch:{ Exception -> 0x0164, all -> 0x015a }
        r1 = com.xiaomi.smack.util.C2718g.m15360a(r1);	 Catch:{ Exception -> 0x0164, all -> 0x015a }
        r1 = r6.append(r1);	 Catch:{ Exception -> 0x0164, all -> 0x015a }
        r2 = "</value>";
        r1.append(r2);	 Catch:{ Exception -> 0x0164, all -> 0x015a }
        if (r3 == 0) goto L_0x0117;
    L_0x0114:
        r3.close();	 Catch:{ Exception -> 0x014f }
    L_0x0117:
        if (r5 == 0) goto L_0x007f;
    L_0x0119:
        r5.close();	 Catch:{ Exception -> 0x011e }
        goto L_0x007f;
    L_0x011e:
        r1 = move-exception;
        goto L_0x007f;
    L_0x0121:
        r1 = move-exception;
        r2 = r4;
        r3 = r4;
    L_0x0124:
        r1.printStackTrace();	 Catch:{ all -> 0x015c }
        if (r2 == 0) goto L_0x012c;
    L_0x0129:
        r2.close();	 Catch:{ Exception -> 0x0151 }
    L_0x012c:
        if (r3 == 0) goto L_0x007f;
    L_0x012e:
        r3.close();	 Catch:{ Exception -> 0x0133 }
        goto L_0x007f;
    L_0x0133:
        r1 = move-exception;
        goto L_0x007f;
    L_0x0136:
        r1 = move-exception;
        r3 = r4;
        r5 = r4;
    L_0x0139:
        if (r3 == 0) goto L_0x013e;
    L_0x013b:
        r3.close();	 Catch:{ Exception -> 0x0153 }
    L_0x013e:
        if (r5 == 0) goto L_0x0143;
    L_0x0140:
        r5.close();	 Catch:{ Exception -> 0x0155 }
    L_0x0143:
        throw r1;	 Catch:{ all -> 0x0023 }
    L_0x0144:
        r1 = "</properties>";
        r6.append(r1);	 Catch:{ all -> 0x0023 }
    L_0x0149:
        r1 = r6.toString();	 Catch:{ all -> 0x0023 }
        monitor-exit(r8);
        return r1;
    L_0x014f:
        r1 = move-exception;
        goto L_0x0117;
    L_0x0151:
        r1 = move-exception;
        goto L_0x012c;
    L_0x0153:
        r2 = move-exception;
        goto L_0x013e;
    L_0x0155:
        r2 = move-exception;
        goto L_0x0143;
    L_0x0157:
        r1 = move-exception;
        r3 = r4;
        goto L_0x0139;
    L_0x015a:
        r1 = move-exception;
        goto L_0x0139;
    L_0x015c:
        r1 = move-exception;
        r5 = r3;
        r3 = r2;
        goto L_0x0139;
    L_0x0160:
        r1 = move-exception;
        r2 = r4;
        r3 = r5;
        goto L_0x0124;
    L_0x0164:
        r1 = move-exception;
        r2 = r3;
        r3 = r5;
        goto L_0x0124;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.packet.d.s():java.lang.String");
    }

    public String m15248t() {
        return this.f13312f;
    }
}
