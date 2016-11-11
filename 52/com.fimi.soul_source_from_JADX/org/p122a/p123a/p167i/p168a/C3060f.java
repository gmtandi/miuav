package org.p122a.p123a.p167i.p168a;

import android.util.Log;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p151b.C2916a;
import org.p122a.p123a.p151b.C2917b;
import org.p122a.p123a.p151b.C2919d;
import org.p122a.p123a.p151b.C2921f;
import org.p122a.p123a.p152c.C2940b;
import org.p122a.p123a.p180o.C3235b;

/* renamed from: org.a.a.i.a.f */
public class C3060f {
    private static final String f15140a = "HttpClient";

    private Header m17191a(AuthScheme authScheme, Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return authScheme instanceof C2921f ? ((C2921f) authScheme).m16738a(credentials, httpRequest, httpContext) : authScheme.authenticate(credentials, httpRequest);
    }

    private void m17192a(AuthScheme authScheme) {
        C3235b.m17894a((Object) authScheme, "Auth scheme");
    }

    public void m17193a(HttpRequest httpRequest, C2919d c2919d, HttpContext httpContext) {
        AuthScheme c = c2919d.m16731c();
        Credentials d = c2919d.m16732d();
        switch (C3061g.f15141a[c2919d.m16730b().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                Queue e = c2919d.m16733e();
                if (e == null) {
                    m17192a(c);
                    break;
                }
                while (!e.isEmpty()) {
                    C2916a c2916a = (C2916a) e.remove();
                    c = c2916a.m16720a();
                    d = c2916a.m16721b();
                    c2919d.m16727a(c, d);
                    if (Log.isLoggable(f15140a, 3)) {
                        Log.d(f15140a, "Generating response to an authentication challenge using " + c.getSchemeName() + " scheme");
                    }
                    try {
                        httpRequest.addHeader(m17191a(c, d, httpRequest, httpContext));
                        return;
                    } catch (AuthenticationException e2) {
                        if (Log.isLoggable(f15140a, 5)) {
                            Log.w(f15140a, c + " authentication error: " + e2.getMessage());
                        }
                    }
                }
                return;
            case Type.BYTE /*3*/:
                m17192a(c);
                if (c.isConnectionBased()) {
                    return;
                }
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return;
        }
        if (c != null) {
            try {
                httpRequest.addHeader(m17191a(c, d, httpRequest, httpContext));
            } catch (AuthenticationException e22) {
                if (Log.isLoggable(f15140a, 6)) {
                    Log.e(f15140a, c + " authentication error: " + e22.getMessage());
                }
            }
        }
    }

    public boolean m17194a(HttpHost httpHost, HttpResponse httpResponse, C2940b c2940b, C2919d c2919d, HttpContext httpContext) {
        if (c2940b.m16814a(httpHost, httpResponse, httpContext)) {
            if (Log.isLoggable(f15140a, 3)) {
                Log.d(f15140a, "Authentication required");
            }
            if (c2919d.m16730b() == C2917b.SUCCESS) {
                c2940b.m16816b(httpHost, c2919d.m16731c(), httpContext);
            }
            return true;
        }
        switch (C3061g.f15141a[c2919d.m16730b().ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (Log.isLoggable(f15140a, 3)) {
                    Log.d(f15140a, "Authentication succeeded");
                }
                c2919d.m16725a(C2917b.SUCCESS);
                c2940b.m16813a(httpHost, c2919d.m16731c(), httpContext);
                break;
            case Type.BYTE /*3*/:
                break;
            default:
                c2919d.m16725a(C2917b.UNCHALLENGED);
                break;
        }
        return false;
    }

    public boolean m17195b(HttpHost httpHost, HttpResponse httpResponse, C2940b c2940b, C2919d c2919d, HttpContext httpContext) {
        try {
            if (Log.isLoggable(f15140a, 3)) {
                Log.d(f15140a, httpHost.toHostString() + " requested authentication");
            }
            Map b = c2940b.m16815b(httpHost, httpResponse, httpContext);
            if (b.isEmpty()) {
                if (Log.isLoggable(f15140a, 3)) {
                    Log.d(f15140a, "Response contains no authentication challenges");
                }
                return false;
            }
            AuthScheme c = c2919d.m16731c();
            switch (C3061g.f15141a[c2919d.m16730b().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (c == null) {
                        if (Log.isLoggable(f15140a, 3)) {
                            Log.d(f15140a, "Auth scheme is null");
                        }
                        c2940b.m16816b(httpHost, null, httpContext);
                        c2919d.m16723a();
                        c2919d.m16725a(C2917b.FAILURE);
                        return false;
                    }
                    break;
                case Type.BYTE /*3*/:
                    c2919d.m16723a();
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    return false;
                case Type.INT /*5*/:
                    break;
            }
            if (c != null) {
                Header header = (Header) b.get(c.getSchemeName().toLowerCase(Locale.ENGLISH));
                if (header != null) {
                    if (Log.isLoggable(f15140a, 3)) {
                        Log.d(f15140a, "Authorization challenge processed");
                    }
                    c.processChallenge(header);
                    if (c.isComplete()) {
                        if (Log.isLoggable(f15140a, 3)) {
                            Log.d(f15140a, "Authentication failed");
                        }
                        c2940b.m16816b(httpHost, c2919d.m16731c(), httpContext);
                        c2919d.m16723a();
                        c2919d.m16725a(C2917b.FAILURE);
                        return false;
                    }
                    c2919d.m16725a(C2917b.HANDSHAKE);
                    return true;
                }
                c2919d.m16723a();
            }
            Queue a = c2940b.m16812a(b, httpHost, httpResponse, httpContext);
            if (a == null || a.isEmpty()) {
                return false;
            }
            if (Log.isLoggable(f15140a, 3)) {
                Log.d(f15140a, "Selected authentication options: " + a);
            }
            c2919d.m16725a(C2917b.CHALLENGED);
            c2919d.m16724a(a);
            return true;
        } catch (MalformedChallengeException e) {
            if (Log.isLoggable(f15140a, 5)) {
                Log.w(f15140a, "Malformed challenge: " + e.getMessage());
            }
            c2919d.m16723a();
            return false;
        }
    }
}
