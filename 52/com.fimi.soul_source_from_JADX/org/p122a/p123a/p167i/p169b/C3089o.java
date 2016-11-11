package org.p122a.p123a.p167i.p169b;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpConnection;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p151b.C2919d;
import org.p122a.p123a.p152c.p160e.C2968a;

@C2912b
/* renamed from: org.a.a.i.b.o */
public class C3089o implements UserTokenHandler {
    public static final C3089o f15282a;

    static {
        f15282a = new C3089o();
    }

    private static Principal m17383a(C2919d c2919d) {
        AuthScheme c = c2919d.m16731c();
        if (c != null && c.isComplete() && c.isConnectionBased()) {
            Credentials d = c2919d.m16732d();
            if (d != null) {
                return d.getUserPrincipal();
            }
        }
        return null;
    }

    public Object getUserToken(HttpContext httpContext) {
        C2968a a = C2968a.m16884a(httpContext);
        Principal principal = null;
        C2919d k = a.m16903k();
        if (k != null) {
            principal = C3089o.m17383a(k);
            if (principal == null) {
                principal = C3089o.m17383a(a.m16904l());
            }
        }
        if (principal == null) {
            HttpConnection p = a.m16878p();
            if (p.isOpen() && (p instanceof C3040h)) {
                SSLSession c = ((C3040h) p).m17151c();
                if (c != null) {
                    return c.getLocalPrincipal();
                }
            }
        }
        return principal;
    }
}
