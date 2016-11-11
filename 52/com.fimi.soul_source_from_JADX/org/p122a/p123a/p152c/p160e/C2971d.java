package org.p122a.p123a.p152c.p160e;

import android.util.Log;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.routing.RouteInfo;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p151b.C2917b;
import org.p122a.p123a.p151b.C2919d;
import org.p122a.p123a.p152c.C2927a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.c.e.d */
public class C2971d implements HttpRequestInterceptor {
    private static final String f14901a = "HttpClient";

    private void m16907a(HttpHost httpHost, AuthScheme authScheme, C2919d c2919d, CredentialsProvider credentialsProvider) {
        String schemeName = authScheme.getSchemeName();
        if (Log.isLoggable(f14901a, 3)) {
            Log.d(f14901a, "Re-using cached '" + schemeName + "' auth scheme for " + httpHost);
        }
        Credentials credentials = credentialsProvider.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort(), AuthScope.ANY_REALM, schemeName));
        if (credentials != null) {
            if ("BASIC".equalsIgnoreCase(authScheme.getSchemeName())) {
                c2919d.m16725a(C2917b.CHALLENGED);
            } else {
                c2919d.m16725a(C2917b.SUCCESS);
            }
            c2919d.m16727a(authScheme, credentials);
        } else if (Log.isLoggable(f14901a, 3)) {
            Log.d(f14901a, "No credentials for preemptive authentication");
        }
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        C3234a.m17886a((Object) httpRequest, "HTTP request");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        C2968a a = C2968a.m16884a(httpContext);
        C2927a j = a.m16902j();
        if (j != null) {
            CredentialsProvider i = a.m16901i();
            if (i != null) {
                RouteInfo b = a.m16893b();
                if (b != null) {
                    HttpHost t = a.m16882t();
                    if (t != null) {
                        HttpHost httpHost = t.getPort() < 0 ? new HttpHost(t.getHostName(), b.getTargetHost().getPort(), t.getSchemeName()) : t;
                        C2919d k = a.m16903k();
                        if (k != null && k.m16730b() == C2917b.UNCHALLENGED) {
                            AuthScheme a2 = j.m16773a(httpHost);
                            if (a2 != null) {
                                m16907a(httpHost, a2, k, i);
                            }
                        }
                        httpHost = b.getProxyHost();
                        k = a.m16904l();
                        if (httpHost != null && k != null && k.m16730b() == C2917b.UNCHALLENGED) {
                            AuthScheme a3 = j.m16773a(httpHost);
                            if (a3 != null) {
                                m16907a(httpHost, a3, k, i);
                            }
                        }
                    } else if (Log.isLoggable(f14901a, 3)) {
                        Log.d(f14901a, "Target host not set in the context");
                    }
                } else if (Log.isLoggable(f14901a, 3)) {
                    Log.d(f14901a, "Route info not set in the context");
                }
            } else if (Log.isLoggable(f14901a, 3)) {
                Log.d(f14901a, "Credentials provider not set in the context");
            }
        } else if (Log.isLoggable(f14901a, 3)) {
            Log.d(f14901a, "Auth cache not set in the context");
        }
    }
}
