package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.smack.util.C2718g;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.smack.packet.c */
public class C2696c extends C2694d {
    private String f13322a;
    private String f13323d;
    private String f13324e;
    private String f13325f;
    private String f13326g;
    private String f13327h;
    private boolean f13328i;
    private String f13329j;
    private String f13330k;
    private String f13331l;
    private String f13332m;
    private String f13333n;
    private boolean f13334o;

    public C2696c() {
        this.f13322a = null;
        this.f13323d = null;
        this.f13328i = false;
        this.f13330k = C2915a.f14760f;
        this.f13331l = C2915a.f14760f;
        this.f13332m = C2915a.f14760f;
        this.f13333n = C2915a.f14760f;
        this.f13334o = false;
    }

    public C2696c(Bundle bundle) {
        super(bundle);
        this.f13322a = null;
        this.f13323d = null;
        this.f13328i = false;
        this.f13330k = C2915a.f14760f;
        this.f13331l = C2915a.f14760f;
        this.f13332m = C2915a.f14760f;
        this.f13333n = C2915a.f14760f;
        this.f13334o = false;
        this.f13322a = bundle.getString("ext_msg_type");
        this.f13324e = bundle.getString("ext_msg_lang");
        this.f13323d = bundle.getString("ext_msg_thread");
        this.f13325f = bundle.getString("ext_msg_sub");
        this.f13326g = bundle.getString("ext_msg_body");
        this.f13327h = bundle.getString("ext_body_encode");
        this.f13329j = bundle.getString("ext_msg_appid");
        this.f13328i = bundle.getBoolean("ext_msg_trans", false);
        this.f13334o = bundle.getBoolean("ext_msg_encrypt", false);
        this.f13330k = bundle.getString("ext_msg_seq");
        this.f13331l = bundle.getString("ext_msg_mseq");
        this.f13332m = bundle.getString("ext_msg_fseq");
        this.f13333n = bundle.getString("ext_msg_status");
    }

    public String m15253a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<message");
        if (m15248t() != null) {
            stringBuilder.append(" xmlns=\"").append(m15248t()).append("\"");
        }
        if (this.f13324e != null) {
            stringBuilder.append(" xml:lang=\"").append(m15271i()).append("\"");
        }
        if (m15232k() != null) {
            stringBuilder.append(" id=\"").append(m15232k()).append("\"");
        }
        if (m15236m() != null) {
            stringBuilder.append(" to=\"").append(C2718g.m15358a(m15236m())).append("\"");
        }
        if (!TextUtils.isEmpty(m15263e())) {
            stringBuilder.append(" seq=\"").append(m15263e()).append("\"");
        }
        if (!TextUtils.isEmpty(m15265f())) {
            stringBuilder.append(" mseq=\"").append(m15265f()).append("\"");
        }
        if (!TextUtils.isEmpty(m15267g())) {
            stringBuilder.append(" fseq=\"").append(m15267g()).append("\"");
        }
        if (!TextUtils.isEmpty(m15269h())) {
            stringBuilder.append(" status=\"").append(m15269h()).append("\"");
        }
        if (m15238n() != null) {
            stringBuilder.append(" from=\"").append(C2718g.m15358a(m15238n())).append("\"");
        }
        if (m15234l() != null) {
            stringBuilder.append(" chid=\"").append(C2718g.m15358a(m15234l())).append("\"");
        }
        if (this.f13328i) {
            stringBuilder.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.f13329j)) {
            stringBuilder.append(" appid=\"").append(m15261d()).append("\"");
        }
        if (!TextUtils.isEmpty(this.f13322a)) {
            stringBuilder.append(" type=\"").append(this.f13322a).append("\"");
        }
        if (this.f13334o) {
            stringBuilder.append(" s=\"1\"");
        }
        stringBuilder.append(">");
        if (this.f13325f != null) {
            stringBuilder.append("<subject>").append(C2718g.m15358a(this.f13325f));
            stringBuilder.append("</subject>");
        }
        if (this.f13326g != null) {
            stringBuilder.append("<body");
            if (!TextUtils.isEmpty(this.f13327h)) {
                stringBuilder.append(" encode=\"").append(this.f13327h).append("\"");
            }
            stringBuilder.append(">").append(C2718g.m15358a(this.f13326g)).append("</body>");
        }
        if (this.f13323d != null) {
            stringBuilder.append("<thread>").append(this.f13323d).append("</thread>");
        }
        if (XiaomiOAuthConstants.EXTRA_ERROR_CODE_2.equalsIgnoreCase(this.f13322a)) {
            C2702h p = m15243p();
            if (p != null) {
                stringBuilder.append(p.m15284d());
            }
        }
        stringBuilder.append(m15247s());
        stringBuilder.append("</message>");
        return stringBuilder.toString();
    }

    public void m15254a(String str) {
        this.f13329j = str;
    }

    public void m15255a(String str, String str2) {
        this.f13326g = str;
        this.f13327h = str2;
    }

    public void m15256a(boolean z) {
        this.f13328i = z;
    }

    public Bundle a_() {
        Bundle a_ = super.a_();
        if (!TextUtils.isEmpty(this.f13322a)) {
            a_.putString("ext_msg_type", this.f13322a);
        }
        if (this.f13324e != null) {
            a_.putString("ext_msg_lang", this.f13324e);
        }
        if (this.f13325f != null) {
            a_.putString("ext_msg_sub", this.f13325f);
        }
        if (this.f13326g != null) {
            a_.putString("ext_msg_body", this.f13326g);
        }
        if (!TextUtils.isEmpty(this.f13327h)) {
            a_.putString("ext_body_encode", this.f13327h);
        }
        if (this.f13323d != null) {
            a_.putString("ext_msg_thread", this.f13323d);
        }
        if (this.f13329j != null) {
            a_.putString("ext_msg_appid", this.f13329j);
        }
        if (this.f13328i) {
            a_.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.f13330k)) {
            a_.putString("ext_msg_seq", this.f13330k);
        }
        if (!TextUtils.isEmpty(this.f13331l)) {
            a_.putString("ext_msg_mseq", this.f13331l);
        }
        if (!TextUtils.isEmpty(this.f13332m)) {
            a_.putString("ext_msg_fseq", this.f13332m);
        }
        if (this.f13334o) {
            a_.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty(this.f13333n)) {
            a_.putString("ext_msg_status", this.f13333n);
        }
        return a_;
    }

    public String m15257b() {
        return this.f13322a;
    }

    public void m15258b(String str) {
        this.f13330k = str;
    }

    public void m15259b(boolean z) {
        this.f13334o = z;
    }

    public void m15260c(String str) {
        this.f13331l = str;
    }

    public String m15261d() {
        return this.f13329j;
    }

    public void m15262d(String str) {
        this.f13332m = str;
    }

    public String m15263e() {
        return this.f13330k;
    }

    public void m15264e(String str) {
        this.f13333n = str;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C2696c c2696c = (C2696c) obj;
        if (!super.equals(c2696c)) {
            return false;
        }
        if (this.f13326g != null) {
            if (!this.f13326g.equals(c2696c.f13326g)) {
                return false;
            }
        } else if (c2696c.f13326g != null) {
            return false;
        }
        if (this.f13324e != null) {
            if (!this.f13324e.equals(c2696c.f13324e)) {
                return false;
            }
        } else if (c2696c.f13324e != null) {
            return false;
        }
        if (this.f13325f != null) {
            if (!this.f13325f.equals(c2696c.f13325f)) {
                return false;
            }
        } else if (c2696c.f13325f != null) {
            return false;
        }
        if (this.f13323d != null) {
            if (!this.f13323d.equals(c2696c.f13323d)) {
                return false;
            }
        } else if (c2696c.f13323d != null) {
            return false;
        }
        if (this.f13322a != c2696c.f13322a) {
            z = false;
        }
        return z;
    }

    public String m15265f() {
        return this.f13331l;
    }

    public void m15266f(String str) {
        this.f13322a = str;
    }

    public String m15267g() {
        return this.f13332m;
    }

    public void m15268g(String str) {
        this.f13325f = str;
    }

    public String m15269h() {
        return this.f13333n;
    }

    public void m15270h(String str) {
        this.f13326g = str;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f13324e != null ? this.f13324e.hashCode() : 0) + (((this.f13323d != null ? this.f13323d.hashCode() : 0) + (((this.f13326g != null ? this.f13326g.hashCode() : 0) + ((this.f13322a != null ? this.f13322a.hashCode() : 0) * 31)) * 31)) * 31)) * 31;
        if (this.f13325f != null) {
            i = this.f13325f.hashCode();
        }
        return hashCode + i;
    }

    public String m15271i() {
        return this.f13324e;
    }

    public void m15272i(String str) {
        this.f13323d = str;
    }

    public void m15273j(String str) {
        this.f13324e = str;
    }
}
