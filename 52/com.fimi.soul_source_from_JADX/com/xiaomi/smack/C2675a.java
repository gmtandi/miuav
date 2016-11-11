package com.xiaomi.smack;

import android.text.TextUtils;
import com.xiaomi.network.Fallback;
import java.net.URI;

/* renamed from: com.xiaomi.smack.a */
public class C2675a extends C2674k {
    private boolean f13241a;
    private String f13242b;
    private Fallback f13243c;
    private String f13244d;

    public C2675a(boolean z, Fallback fallback, int i, String str, String str2, C2688m c2688m) {
        super(null, i, str2, c2688m);
        this.f13243c = null;
        this.f13244d = "mibind.chat.gslb.mi-idc.com";
        this.f13243c = fallback;
        this.f13241a = z;
        if (str == null) {
            str = "/";
        }
        this.f13242b = str;
    }

    public Fallback m15144a() {
        return this.f13243c;
    }

    public void m15145a(Fallback fallback) {
        if (fallback != null) {
            this.f13243c = fallback;
            this.f13244d = "mibind.chat.gslb.mi-idc.com";
            if (!this.f13243c.m14845c().isEmpty()) {
                String str = (String) this.f13243c.m14845c().get(0);
                if (!TextUtils.isEmpty(str)) {
                    this.f13244d = str;
                }
            }
        }
    }

    public String m15146b() {
        return this.f13244d;
    }

    public URI m15147c() {
        if (this.f13242b.charAt(0) != '/') {
            this.f13242b = '/' + this.f13242b;
        }
        return new URI((this.f13241a ? "https://" : "http://") + this.f13244d + ":" + m15141g() + this.f13242b);
    }
}
