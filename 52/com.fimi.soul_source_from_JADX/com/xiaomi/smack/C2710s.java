package com.xiaomi.smack;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.string.C2474b;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.smack.packet.C2692a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2702h;
import com.xiaomi.smack.util.C2718g;
import java.util.HashMap;
import java.util.Map;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.smack.s */
public class C2710s {

    /* renamed from: com.xiaomi.smack.s.a */
    public class C2707a extends C2694d {
        final /* synthetic */ C2710s f13396a;

        public C2707a(C2710s c2710s, C2667b c2667b, String str, C2678j c2678j) {
            Object obj;
            this.f13396a = c2710s;
            Map hashMap = new HashMap();
            int k = c2678j.m15173k();
            hashMap.put("challenge", str);
            hashMap.put("token", c2667b.f13179c);
            hashMap.put("chid", c2667b.f13184h);
            hashMap.put("from", c2667b.f13178b);
            hashMap.put(LocaleUtil.INDONESIAN, m15232k());
            hashMap.put("to", "xiaomi.com");
            if (c2667b.f13181e) {
                hashMap.put("kick", Constants.VIA_TO_TYPE_QQ_GROUP);
            } else {
                hashMap.put("kick", Constants.VIA_RESULT_SUCCESS);
            }
            if (c2678j.m15175m() > 0) {
                String format = String.format("conn:%1$d,t:%2$d", new Object[]{Integer.valueOf(k), Long.valueOf(c2678j.m15175m())});
                hashMap.put(Constants.PARAM_PLATFORM_ID, format);
                c2678j.m15174l();
                c2678j.m15176n();
                obj = format;
            } else {
                obj = null;
            }
            if (TextUtils.isEmpty(c2667b.f13182f)) {
                hashMap.put("client_attrs", C2915a.f14760f);
            } else {
                hashMap.put("client_attrs", c2667b.f13182f);
            }
            if (TextUtils.isEmpty(c2667b.f13183g)) {
                hashMap.put("cloud_attrs", C2915a.f14760f);
            } else {
                hashMap.put("cloud_attrs", c2667b.f13183g);
            }
            String a = (c2667b.f13180d.equals("XIAOMI-PASS") || c2667b.f13180d.equals("XMPUSH-PASS")) ? C2474b.m14161a(c2667b.f13180d, null, hashMap, c2667b.f13185i) : c2667b.f13180d.equals("XIAOMI-SASL") ? null : null;
            m15235l(c2667b.f13184h);
            m15239n(c2667b.f13178b);
            m15237m("xiaomi.com");
            C2692a c2692a = new C2692a("token", null, (String[]) null, (String[]) null);
            c2692a.m15220b(c2667b.f13179c);
            m15229a(c2692a);
            c2692a = new C2692a("kick", null, (String[]) null, (String[]) null);
            c2692a.m15220b(c2667b.f13181e ? Constants.VIA_TO_TYPE_QQ_GROUP : Constants.VIA_RESULT_SUCCESS);
            m15229a(c2692a);
            c2692a = new C2692a("sig", null, (String[]) null, (String[]) null);
            c2692a.m15220b(a);
            m15229a(c2692a);
            C2692a c2692a2 = new C2692a("method", null, (String[]) null, (String[]) null);
            if (TextUtils.isEmpty(c2667b.f13180d)) {
                c2692a2.m15220b("XIAOMI-SASL");
            } else {
                c2692a2.m15220b(c2667b.f13180d);
            }
            m15229a(c2692a2);
            c2692a2 = new C2692a("client_attrs", null, (String[]) null, (String[]) null);
            c2692a2.m15220b(c2667b.f13182f == null ? C2915a.f14760f : C2718g.m15358a(c2667b.f13182f));
            m15229a(c2692a2);
            c2692a2 = new C2692a("cloud_attrs", null, (String[]) null, (String[]) null);
            c2692a2.m15220b(c2667b.f13183g == null ? C2915a.f14760f : C2718g.m15358a(c2667b.f13183g));
            m15229a(c2692a2);
            if (!TextUtils.isEmpty(obj)) {
                c2692a2 = new C2692a(Constants.PARAM_PLATFORM_ID, null, (String[]) null, (String[]) null);
                c2692a2.m15220b(obj);
                m15229a(c2692a2);
            }
        }

        public String m15305a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<bind ");
            if (m15232k() != null) {
                stringBuilder.append("id=\"" + m15232k() + "\" ");
            }
            if (m15236m() != null) {
                stringBuilder.append("to=\"").append(C2718g.m15358a(m15236m())).append("\" ");
            }
            if (m15238n() != null) {
                stringBuilder.append("from=\"").append(C2718g.m15358a(m15238n())).append("\" ");
            }
            if (m15234l() != null) {
                stringBuilder.append("chid=\"").append(C2718g.m15358a(m15234l())).append("\">");
            }
            if (m15245q() != null) {
                for (C2692a d : m15245q()) {
                    stringBuilder.append(d.m15213d());
                }
            }
            stringBuilder.append("</bind>");
            return stringBuilder.toString();
        }
    }

    /* renamed from: com.xiaomi.smack.s.b */
    public class C2709b extends C2694d {
        private C2708a f13400a;
        private boolean f13401d;
        private int f13402e;

        /* renamed from: com.xiaomi.smack.s.b.a */
        public class C2708a {
            public static final C2708a f13397a;
            public static final C2708a f13398b;
            private String f13399c;

            static {
                f13397a = new C2708a("result");
                f13398b = new C2708a(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
            }

            private C2708a(String str) {
                this.f13399c = str;
            }

            public static C2708a m15306a(String str) {
                if (str == null) {
                    return null;
                }
                String toLowerCase = str.toLowerCase();
                return f13398b.toString().equals(toLowerCase) ? f13398b : f13397a.toString().equals(toLowerCase) ? f13397a : null;
            }

            public String toString() {
                return this.f13399c;
            }
        }

        public String m15307a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<bind ");
            if (m15232k() != null) {
                stringBuilder.append("id=\"" + m15232k() + "\" ");
            }
            if (m15236m() != null) {
                stringBuilder.append("to=\"").append(C2718g.m15358a(m15236m())).append("\" ");
            }
            if (m15238n() != null) {
                stringBuilder.append("from=\"").append(C2718g.m15358a(m15238n())).append("\" ");
            }
            if (m15234l() != null) {
                stringBuilder.append(" chid=\"").append(C2718g.m15358a(m15234l())).append("\" ");
            }
            if (this.f13400a == null) {
                stringBuilder.append("type=\"result\">");
            } else {
                stringBuilder.append("type=\"").append(m15310b()).append("\">");
            }
            if (this.f13401d) {
                stringBuilder.append("<p peroid = ");
                stringBuilder.append(this.f13402e);
                stringBuilder.append("/>");
            }
            if (m15245q() != null) {
                for (C2692a d : m15245q()) {
                    stringBuilder.append(d.m15213d());
                }
            }
            C2702h p = m15243p();
            if (p != null) {
                stringBuilder.append(p.m15284d());
            }
            stringBuilder.append("</bind>");
            return stringBuilder.toString();
        }

        public void m15308a(C2708a c2708a) {
            if (c2708a == null) {
                this.f13400a = C2708a.f13397a;
            } else {
                this.f13400a = c2708a;
            }
        }

        public void m15309a(boolean z, int i) {
            this.f13401d = z;
            this.f13402e = i;
        }

        public C2708a m15310b() {
            return this.f13400a;
        }

        public boolean m15311c() {
            return this.f13401d;
        }

        public int m15312d() {
            return this.f13402e;
        }
    }

    public void m15313a(C2667b c2667b, String str, C2678j c2678j) {
        C2694d c2707a = new C2707a(this, c2667b, str, c2678j);
        c2678j.m15155a(c2707a);
        C2463b.m14123a("SMACK: bind id=" + c2707a.m15232k());
    }
}
