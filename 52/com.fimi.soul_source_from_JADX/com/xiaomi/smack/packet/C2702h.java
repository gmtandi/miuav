package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.xiaomi.smack.packet.h */
public class C2702h {
    private int f13380a;
    private String f13381b;
    private String f13382c;
    private String f13383d;
    private String f13384e;
    private List<C2692a> f13385f;

    /* renamed from: com.xiaomi.smack.packet.h.a */
    public class C2701a {
        public static final C2701a f13355a;
        public static final C2701a f13356b;
        public static final C2701a f13357c;
        public static final C2701a f13358d;
        public static final C2701a f13359e;
        public static final C2701a f13360f;
        public static final C2701a f13361g;
        public static final C2701a f13362h;
        public static final C2701a f13363i;
        public static final C2701a f13364j;
        public static final C2701a f13365k;
        public static final C2701a f13366l;
        public static final C2701a f13367m;
        public static final C2701a f13368n;
        public static final C2701a f13369o;
        public static final C2701a f13370p;
        public static final C2701a f13371q;
        public static final C2701a f13372r;
        public static final C2701a f13373s;
        public static final C2701a f13374t;
        public static final C2701a f13375u;
        public static final C2701a f13376v;
        public static final C2701a f13377w;
        public static final C2701a f13378x;
        private String f13379y;

        static {
            f13355a = new C2701a("internal-server-error");
            f13356b = new C2701a("forbidden");
            f13357c = new C2701a("bad-request");
            f13358d = new C2701a("conflict");
            f13359e = new C2701a("feature-not-implemented");
            f13360f = new C2701a("gone");
            f13361g = new C2701a("item-not-found");
            f13362h = new C2701a("jid-malformed");
            f13363i = new C2701a("not-acceptable");
            f13364j = new C2701a("not-allowed");
            f13365k = new C2701a("not-authorized");
            f13366l = new C2701a("payment-required");
            f13367m = new C2701a("recipient-unavailable");
            f13368n = new C2701a("redirect");
            f13369o = new C2701a("registration-required");
            f13370p = new C2701a("remote-server-error");
            f13371q = new C2701a("remote-server-not-found");
            f13372r = new C2701a("remote-server-timeout");
            f13373s = new C2701a("resource-constraint");
            f13374t = new C2701a("service-unavailable");
            f13375u = new C2701a("subscription-required");
            f13376v = new C2701a("undefined-condition");
            f13377w = new C2701a("unexpected-request");
            f13378x = new C2701a("request-timeout");
        }

        public C2701a(String str) {
            this.f13379y = str;
        }

        public String toString() {
            return this.f13379y;
        }
    }

    public C2702h(int i, String str, String str2, String str3, String str4, List<C2692a> list) {
        this.f13385f = null;
        this.f13380a = i;
        this.f13381b = str;
        this.f13383d = str2;
        this.f13382c = str3;
        this.f13384e = str4;
        this.f13385f = list;
    }

    public C2702h(Bundle bundle) {
        this.f13385f = null;
        this.f13380a = bundle.getInt("ext_err_code");
        if (bundle.containsKey("ext_err_type")) {
            this.f13381b = bundle.getString("ext_err_type");
        }
        this.f13382c = bundle.getString("ext_err_cond");
        this.f13383d = bundle.getString("ext_err_reason");
        this.f13384e = bundle.getString("ext_err_msg");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f13385f = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                C2692a a = C2692a.m15214a((Bundle) parcelable);
                if (a != null) {
                    this.f13385f.add(a);
                }
            }
        }
    }

    public C2702h(C2701a c2701a) {
        this.f13385f = null;
        m15280a(c2701a);
        this.f13384e = null;
    }

    public C2702h(C2701a c2701a, String str) {
        this.f13385f = null;
        m15280a(c2701a);
        this.f13384e = str;
    }

    private void m15280a(C2701a c2701a) {
        this.f13382c = c2701a.f13379y;
    }

    public String m15281a() {
        return this.f13383d;
    }

    public String m15282b() {
        return this.f13381b;
    }

    public Bundle m15283c() {
        Bundle bundle = new Bundle();
        if (this.f13381b != null) {
            bundle.putString("ext_err_type", this.f13381b);
        }
        bundle.putInt("ext_err_code", this.f13380a);
        if (this.f13383d != null) {
            bundle.putString("ext_err_reason", this.f13383d);
        }
        if (this.f13382c != null) {
            bundle.putString("ext_err_cond", this.f13382c);
        }
        if (this.f13384e != null) {
            bundle.putString("ext_err_msg", this.f13384e);
        }
        if (this.f13385f != null) {
            Parcelable[] parcelableArr = new Bundle[this.f13385f.size()];
            int i = 0;
            for (C2692a e : this.f13385f) {
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

    public String m15284d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<error code=\"").append(this.f13380a).append("\"");
        if (this.f13381b != null) {
            stringBuilder.append(" type=\"");
            stringBuilder.append(this.f13381b);
            stringBuilder.append("\"");
        }
        if (this.f13383d != null) {
            stringBuilder.append(" reason=\"");
            stringBuilder.append(this.f13383d);
            stringBuilder.append("\"");
        }
        stringBuilder.append(">");
        if (this.f13382c != null) {
            stringBuilder.append("<").append(this.f13382c);
            stringBuilder.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.f13384e != null) {
            stringBuilder.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            stringBuilder.append(this.f13384e);
            stringBuilder.append("</text>");
        }
        for (C2692a d : m15285e()) {
            stringBuilder.append(d.m15213d());
        }
        stringBuilder.append("</error>");
        return stringBuilder.toString();
    }

    public synchronized List<C2692a> m15285e() {
        return this.f13385f == null ? Collections.emptyList() : Collections.unmodifiableList(this.f13385f);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f13382c != null) {
            stringBuilder.append(this.f13382c);
        }
        stringBuilder.append("(").append(this.f13380a).append(")");
        if (this.f13384e != null) {
            stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f13384e);
        }
        return stringBuilder.toString();
    }
}
