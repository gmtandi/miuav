package org.p122a.p123a.p167i.p168a;

import java.nio.charset.Charset;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p151b.C2918c;

@C2912b
/* renamed from: org.a.a.i.a.d */
public class C3058d implements C2918c, AuthSchemeFactory {
    private final Charset f15128a;

    public C3058d() {
        this(null);
    }

    public C3058d(Charset charset) {
        this.f15128a = charset;
    }

    public AuthScheme m17181a(HttpContext httpContext) {
        return new C3059e(this.f15128a);
    }

    public AuthScheme newInstance(HttpParams httpParams) {
        return new C3059e();
    }
}
