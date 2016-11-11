package com.xiaomi.smack.packet;

import android.os.Bundle;
import com.fimi.kernel.p084e.C1186y;
import com.xiaomi.smack.util.C2718g;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.xiaomi.smack.packet.f */
public class C2699f extends C2694d {
    private C2698b f13350a;
    private String f13351d;
    private int f13352e;
    private C2697a f13353f;

    /* renamed from: com.xiaomi.smack.packet.f.a */
    public enum C2697a {
        chat,
        available,
        away,
        xa,
        dnd
    }

    /* renamed from: com.xiaomi.smack.packet.f.b */
    public enum C2698b {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public C2699f(Bundle bundle) {
        super(bundle);
        this.f13350a = C2698b.available;
        this.f13351d = null;
        this.f13352e = C1186y.f5353a;
        this.f13353f = null;
        if (bundle.containsKey("ext_pres_type")) {
            this.f13350a = C2698b.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.f13351d = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.f13352e = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.f13353f = C2697a.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public C2699f(C2698b c2698b) {
        this.f13350a = C2698b.available;
        this.f13351d = null;
        this.f13352e = C1186y.f5353a;
        this.f13353f = null;
        m15277a(c2698b);
    }

    public String m15274a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<presence");
        if (m15248t() != null) {
            stringBuilder.append(" xmlns=\"").append(m15248t()).append("\"");
        }
        if (m15232k() != null) {
            stringBuilder.append(" id=\"").append(m15232k()).append("\"");
        }
        if (m15236m() != null) {
            stringBuilder.append(" to=\"").append(C2718g.m15358a(m15236m())).append("\"");
        }
        if (m15238n() != null) {
            stringBuilder.append(" from=\"").append(C2718g.m15358a(m15238n())).append("\"");
        }
        if (m15234l() != null) {
            stringBuilder.append(" chid=\"").append(C2718g.m15358a(m15234l())).append("\"");
        }
        if (this.f13350a != null) {
            stringBuilder.append(" type=\"").append(this.f13350a).append("\"");
        }
        stringBuilder.append(">");
        if (this.f13351d != null) {
            stringBuilder.append("<status>").append(C2718g.m15358a(this.f13351d)).append("</status>");
        }
        if (this.f13352e != C1186y.f5353a) {
            stringBuilder.append("<priority>").append(this.f13352e).append("</priority>");
        }
        if (!(this.f13353f == null || this.f13353f == C2697a.available)) {
            stringBuilder.append("<show>").append(this.f13353f).append("</show>");
        }
        stringBuilder.append(m15247s());
        C2702h p = m15243p();
        if (p != null) {
            stringBuilder.append(p.m15284d());
        }
        stringBuilder.append("</presence>");
        return stringBuilder.toString();
    }

    public void m15275a(int i) {
        if (i < -128 || i > SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
            throw new IllegalArgumentException("Priority value " + i + " is not valid. Valid range is -128 through 128.");
        }
        this.f13352e = i;
    }

    public void m15276a(C2697a c2697a) {
        this.f13353f = c2697a;
    }

    public void m15277a(C2698b c2698b) {
        if (c2698b == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.f13350a = c2698b;
    }

    public void m15278a(String str) {
        this.f13351d = str;
    }

    public Bundle a_() {
        Bundle a_ = super.a_();
        if (this.f13350a != null) {
            a_.putString("ext_pres_type", this.f13350a.toString());
        }
        if (this.f13351d != null) {
            a_.putString("ext_pres_status", this.f13351d);
        }
        if (this.f13352e != C1186y.f5353a) {
            a_.putInt("ext_pres_prio", this.f13352e);
        }
        if (!(this.f13353f == null || this.f13353f == C2697a.available)) {
            a_.putString("ext_pres_mode", this.f13353f.toString());
        }
        return a_;
    }
}
