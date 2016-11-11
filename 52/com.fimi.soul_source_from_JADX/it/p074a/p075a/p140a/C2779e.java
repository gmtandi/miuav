package it.p074a.p075a.p140a;

import it.p074a.p075a.C2777i;
import it.p074a.p075a.C2799f;
import it.p074a.p075a.C2800g;
import it.p074a.p075a.C2805p;
import java.io.IOException;
import java.net.Socket;

/* renamed from: it.a.a.a.e */
public class C2779e extends C2777i {
    public static int f14165d;
    public static int f14166e;
    public int f14167f;
    private String f14168g;
    private int f14169h;
    private String f14170i;
    private String f14171j;

    static {
        f14165d = 0;
        f14166e = 1;
    }

    public C2779e(String str, int i) {
        this(str, i, "anonymous", "ftp4j");
    }

    public C2779e(String str, int i, String str2, String str3) {
        super(true);
        this.f14167f = f14165d;
        this.f14168g = str;
        this.f14169h = i;
        this.f14170i = str2;
        this.f14171j = str3;
    }

    public Socket m15844c(String str, int i) {
        Socket a = m15832a(this.f14168g, this.f14169h);
        C2800g c2800g = new C2800g(a, "ASCII");
        try {
            if (c2800g.m15983c().m15997a() != C2799f.f14251A) {
                throw new IOException("Invalid proxy response");
            }
            if (this.f14167f == f14165d) {
                c2800g.m15978a(new StringBuffer().append("USER ").append(this.f14170i).toString());
                try {
                    Object obj;
                    switch (c2800g.m15983c().m15997a()) {
                        case C2799f.f14256F /*230*/:
                            obj = null;
                            break;
                        case C2799f.f14279q /*331*/:
                            obj = 1;
                            break;
                        default:
                            throw new IOException("Proxy authentication failed");
                    }
                    if (obj != null) {
                        c2800g.m15978a(new StringBuffer().append("PASS ").append(this.f14171j).toString());
                        try {
                            if (c2800g.m15983c().m15997a() != C2799f.f14256F) {
                                throw new IOException("Proxy authentication failed");
                            }
                        } catch (C2805p e) {
                            throw new IOException("Invalid proxy response");
                        }
                    }
                    c2800g.m15978a(new StringBuffer().append("SITE ").append(str).append(":").append(i).toString());
                } catch (C2805p e2) {
                    throw new IOException("Invalid proxy response");
                }
            } else if (this.f14167f == f14166e) {
                c2800g.m15978a(new StringBuffer().append("OPEN ").append(str).append(":").append(i).toString());
            }
            return a;
        } catch (C2805p e3) {
            throw new IOException("Invalid proxy response");
        }
    }

    public Socket m15845d(String str, int i) {
        return m15836b(str, i);
    }

    public void m15846d(int i) {
        if (i == f14166e || i == f14165d) {
            this.f14167f = i;
            return;
        }
        throw new IllegalArgumentException("Invalid style");
    }
}
