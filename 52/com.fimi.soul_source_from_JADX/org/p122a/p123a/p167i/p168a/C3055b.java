package org.p122a.p123a.p167i.p168a;

import java.nio.charset.Charset;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p151b.C2918c;

@C2912b
/* renamed from: org.a.a.i.a.b */
public class C3055b implements C2918c, AuthSchemeFactory {
    private final Charset f15124a;

    public C3055b() {
        this(null);
    }

    public C3055b(Charset charset) {
        this.f15124a = charset;
    }

    public AuthScheme m17174a(HttpContext httpContext) {
        return new C3057c(this.f15124a);
    }

    public AuthScheme newInstance(HttpParams httpParams) {
        return new C3057c();
    }
}
