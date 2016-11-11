package com.xiaomi.kenai.jbosh;

import com.facebook.common.util.UriUtil;
import com.tencent.mm.sdk.platformtools.Util;
import java.net.URI;
import javax.net.ssl.SSLContext;

/* renamed from: com.xiaomi.kenai.jbosh.v */
public final class C2523v {
    private final URI f12775a;
    private final String f12776b;
    private final String f12777c;
    private final String f12778d;
    private final String f12779e;
    private final String f12780f;
    private final int f12781g;
    private final SSLContext f12782h;
    private final boolean f12783i;

    /* renamed from: com.xiaomi.kenai.jbosh.v.a */
    public final class C2522a {
        private final URI f12766a;
        private final String f12767b;
        private String f12768c;
        private String f12769d;
        private String f12770e;
        private String f12771f;
        private int f12772g;
        private SSLContext f12773h;
        private Boolean f12774i;

        private C2522a(URI uri, String str) {
            this.f12766a = uri;
            this.f12767b = str;
        }

        public static C2522a m14443a(URI uri, String str) {
            if (uri == null) {
                throw new IllegalArgumentException("Connection manager URI must not be null");
            } else if (str == null) {
                throw new IllegalArgumentException("Target domain must not be null");
            } else {
                String scheme = uri.getScheme();
                if (UriUtil.HTTP_SCHEME.equals(scheme) || UriUtil.HTTPS_SCHEME.equals(scheme)) {
                    return new C2522a(uri, str);
                }
                throw new IllegalArgumentException("Only 'http' and 'https' URI are allowed");
            }
        }

        public C2523v m14444a() {
            return new C2523v(this.f12767b, this.f12768c, this.f12769d == null ? Util.ENGLISH : this.f12769d, this.f12770e, this.f12771f, this.f12771f == null ? 0 : this.f12772g, this.f12773h, this.f12774i == null ? false : this.f12774i.booleanValue(), null);
        }
    }

    private C2523v(URI uri, String str, String str2, String str3, String str4, String str5, int i, SSLContext sSLContext, boolean z) {
        this.f12775a = uri;
        this.f12776b = str;
        this.f12777c = str2;
        this.f12778d = str3;
        this.f12779e = str4;
        this.f12780f = str5;
        this.f12781g = i;
        this.f12782h = sSLContext;
        this.f12783i = z;
    }

    public URI m14445a() {
        return this.f12775a;
    }

    public String m14446b() {
        return this.f12776b;
    }

    public String m14447c() {
        return this.f12777c;
    }

    public String m14448d() {
        return this.f12778d;
    }

    public String m14449e() {
        return this.f12779e;
    }

    public String m14450f() {
        return this.f12780f;
    }

    public int m14451g() {
        return this.f12781g;
    }
}
