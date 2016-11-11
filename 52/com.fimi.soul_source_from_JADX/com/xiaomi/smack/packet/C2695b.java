package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.smack.util.C2718g;

/* renamed from: com.xiaomi.smack.packet.b */
public class C2695b extends C2694d {
    private C2693a f13321a;

    /* renamed from: com.xiaomi.smack.packet.b.a */
    public class C2693a {
        public static final C2693a f13302a;
        public static final C2693a f13303b;
        public static final C2693a f13304c;
        public static final C2693a f13305d;
        private String f13306e;

        static {
            f13302a = new C2693a("get");
            f13303b = new C2693a("set");
            f13304c = new C2693a("result");
            f13305d = new C2693a(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
        }

        private C2693a(String str) {
            this.f13306e = str;
        }

        public static C2693a m15225a(String str) {
            if (str == null) {
                return null;
            }
            String toLowerCase = str.toLowerCase();
            return f13302a.toString().equals(toLowerCase) ? f13302a : f13303b.toString().equals(toLowerCase) ? f13303b : f13305d.toString().equals(toLowerCase) ? f13305d : f13304c.toString().equals(toLowerCase) ? f13304c : null;
        }

        public String toString() {
            return this.f13306e;
        }
    }

    public C2695b() {
        this.f13321a = C2693a.f13302a;
    }

    public C2695b(Bundle bundle) {
        super(bundle);
        this.f13321a = C2693a.f13302a;
        if (bundle.containsKey("ext_iq_type")) {
            this.f13321a = C2693a.m15225a(bundle.getString("ext_iq_type"));
        }
    }

    public String m15249a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<iq ");
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
            stringBuilder.append("chid=\"").append(C2718g.m15358a(m15234l())).append("\" ");
        }
        if (this.f13321a == null) {
            stringBuilder.append("type=\"get\">");
        } else {
            stringBuilder.append("type=\"").append(m15251b()).append("\">");
        }
        String d = m15252d();
        if (d != null) {
            stringBuilder.append(d);
        }
        stringBuilder.append(m15247s());
        C2702h p = m15243p();
        if (p != null) {
            stringBuilder.append(p.m15284d());
        }
        stringBuilder.append("</iq>");
        return stringBuilder.toString();
    }

    public void m15250a(C2693a c2693a) {
        if (c2693a == null) {
            this.f13321a = C2693a.f13302a;
        } else {
            this.f13321a = c2693a;
        }
    }

    public Bundle a_() {
        Bundle a_ = super.a_();
        if (this.f13321a != null) {
            a_.putString("ext_iq_type", this.f13321a.toString());
        }
        return a_;
    }

    public C2693a m15251b() {
        return this.f13321a;
    }

    public String m15252d() {
        return null;
    }
}
