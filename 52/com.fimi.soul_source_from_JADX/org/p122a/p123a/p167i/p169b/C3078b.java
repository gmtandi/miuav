package org.p122a.p123a.p167i.p169b;

import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p151b.C2916a;
import org.p122a.p123a.p151b.C2918c;
import org.p122a.p123a.p152c.C2927a;
import org.p122a.p123a.p152c.C2940b;
import org.p122a.p123a.p152c.p153a.C2923a;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.b.b */
abstract class C3078b implements C2940b {
    private static final String f15249a = "HttpClient";
    private static final List<String> f15250b;
    private final int f15251c;
    private final String f15252d;

    static {
        f15250b = Collections.unmodifiableList(Arrays.asList(new String[]{C2923a.f14787d, C2923a.f14788e, C2923a.f14786c, C2923a.f14785b, C2923a.f14784a}));
    }

    C3078b(int i, String str) {
        this.f15251c = i;
        this.f15252d = str;
    }

    abstract Collection<String> m17332a(C2925c c2925c);

    public Queue<C2916a> m17333a(Map<String, Header> map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        C3234a.m17886a((Object) map, "Map of auth challenges");
        C3234a.m17886a((Object) httpHost, C3004e.f15040z);
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        C2968a a = C2968a.m16884a(httpContext);
        Queue<C2916a> linkedList = new LinkedList();
        C2997c h = a.m16900h();
        if (h == null) {
            if (Log.isLoggable(f15249a, 3)) {
                Log.d(f15249a, "Auth scheme registry not set in the context");
            }
            return linkedList;
        }
        CredentialsProvider i = a.m16901i();
        if (i == null) {
            if (Log.isLoggable(f15249a, 3)) {
                Log.d(f15249a, "Credentials provider not set in the context");
            }
            return linkedList;
        }
        Collection a2 = m17332a(a.m16906n());
        if (a2 == null) {
            a2 = f15250b;
        }
        if (Log.isLoggable(f15249a, 3)) {
            Log.d(f15249a, "Authentication schemes in the order of preference: " + r0);
        }
        for (String str : r0) {
            Header header = (Header) map.get(str.toLowerCase(Locale.ENGLISH));
            if (header != null) {
                C2918c c2918c = (C2918c) h.m17023a(str);
                if (c2918c != null) {
                    AuthScheme a3 = c2918c.m16722a(httpContext);
                    a3.processChallenge(header);
                    Credentials credentials = i.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort(), a3.getRealm(), a3.getSchemeName()));
                    if (credentials != null) {
                        linkedList.add(new C2916a(a3, credentials));
                    }
                } else if (Log.isLoggable(f15249a, 5)) {
                    Log.w(f15249a, "Authentication scheme " + str + " not supported");
                }
            } else if (Log.isLoggable(f15249a, 3)) {
                Log.d(f15249a, "Challenge for " + str + " authentication scheme not available");
            }
        }
        return linkedList;
    }

    public void m17334a(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        C3234a.m17886a((Object) httpHost, C3004e.f15040z);
        C3234a.m17886a((Object) authScheme, "Auth scheme");
        C3234a.m17886a((Object) httpContext, "HTTP context");
        C2968a a = C2968a.m16884a(httpContext);
        if (m17336a(authScheme)) {
            C2927a j = a.m16902j();
            if (j == null) {
                j = new C3081c();
                a.m16889a(j);
            }
            if (Log.isLoggable(f15249a, 3)) {
                Log.d(f15249a, "Caching '" + authScheme.getSchemeName() + "' auth scheme for " + httpHost);
            }
            j.m16775a(httpHost, authScheme);
        }
    }

    public boolean m17335a(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        return httpResponse.getStatusLine().getStatusCode() == this.f15251c;
    }

    protected boolean m17336a(AuthScheme authScheme) {
        if (authScheme == null || !authScheme.isComplete()) {
            return false;
        }
        String schemeName = authScheme.getSchemeName();
        return schemeName.equalsIgnoreCase(C2923a.f14784a) || schemeName.equalsIgnoreCase(C2923a.f14785b);
    }

    public Map<String, Header> m17337b(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext) {
        C3234a.m17886a((Object) httpResponse, "HTTP response");
        Header[] headers = httpResponse.getHeaders(this.f15252d);
        Map<String, Header> hashMap = new HashMap(headers.length);
        for (Header header : headers) {
            CharArrayBuffer buffer;
            int valuePos;
            if (header instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header).getBuffer();
                valuePos = ((FormattedHeader) header).getValuePos();
            } else {
                String value = header.getValue();
                if (value == null) {
                    throw new MalformedChallengeException("Header value is null");
                }
                CharArrayBuffer charArrayBuffer = new CharArrayBuffer(value.length());
                charArrayBuffer.append(value);
                buffer = charArrayBuffer;
                valuePos = 0;
            }
            while (valuePos < buffer.length() && HTTP.isWhitespace(buffer.charAt(valuePos))) {
                valuePos++;
            }
            int i = valuePos;
            while (i < buffer.length() && !HTTP.isWhitespace(buffer.charAt(i))) {
                i++;
            }
            hashMap.put(buffer.substring(valuePos, i).toLowerCase(Locale.ENGLISH), header);
        }
        return hashMap;
    }

    public void m17338b(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext) {
        C3234a.m17886a((Object) httpHost, C3004e.f15040z);
        C3234a.m17886a((Object) httpContext, "HTTP context");
        C2927a j = C2968a.m16884a(httpContext).m16902j();
        if (j != null) {
            if (Log.isLoggable(f15249a, 3)) {
                Log.d(f15249a, "Clearing cached auth scheme for " + httpHost);
            }
            j.m16776b(httpHost);
        }
    }
}
