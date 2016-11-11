package org.p122a.p123a.p167i.p169b;

import com.facebook.common.util.UriUtil;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.PasswordAuthentication;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p152c.p153a.C2923a;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.i.b.ak */
public class ak implements CredentialsProvider {
    private static final Map<String, String> f15271a;
    private final C3083e f15272b;

    static {
        f15271a = new ConcurrentHashMap();
        f15271a.put(C2923a.f14784a.toUpperCase(Locale.ENGLISH), C2923a.f14784a);
        f15271a.put(C2923a.f14785b.toUpperCase(Locale.ENGLISH), C2923a.f14785b);
        f15271a.put(C2923a.f14786c.toUpperCase(Locale.ENGLISH), C2923a.f14786c);
        f15271a.put(C2923a.f14787d.toUpperCase(Locale.ENGLISH), "SPNEGO");
        f15271a.put(C2923a.f14788e.toUpperCase(Locale.ENGLISH), C2923a.f14788e);
    }

    public ak() {
        this.f15272b = new C3083e();
    }

    private static String m17361a(String str) {
        if (str == null) {
            return null;
        }
        String str2 = (String) f15271a.get(str);
        return str2 == null ? str : str2;
    }

    private static PasswordAuthentication m17362a(AuthScope authScope, RequestorType requestorType) {
        String host = authScope.getHost();
        int port = authScope.getPort();
        return Authenticator.requestPasswordAuthentication(host, null, port, port == 443 ? UriUtil.HTTPS_SCHEME : UriUtil.HTTP_SCHEME, null, ak.m17361a(authScope.getScheme()), null, requestorType);
    }

    public void clear() {
        this.f15272b.clear();
    }

    public Credentials getCredentials(AuthScope authScope) {
        C3234a.m17886a((Object) authScope, "Auth scope");
        Credentials credentials = this.f15272b.getCredentials(authScope);
        if (credentials != null) {
            return credentials;
        }
        if (authScope.getHost() != null) {
            PasswordAuthentication a = ak.m17362a(authScope, RequestorType.SERVER);
            PasswordAuthentication a2 = a == null ? ak.m17362a(authScope, RequestorType.PROXY) : a;
            if (a2 != null) {
                String property = System.getProperty("http.auth.ntlm.domain");
                return property != null ? new NTCredentials(a2.getUserName(), new String(a2.getPassword()), null, property) : C2923a.f14786c.equalsIgnoreCase(authScope.getScheme()) ? new NTCredentials(a2.getUserName(), new String(a2.getPassword()), null, null) : new UsernamePasswordCredentials(a2.getUserName(), new String(a2.getPassword()));
            }
        }
        return null;
    }

    public void setCredentials(AuthScope authScope, Credentials credentials) {
        this.f15272b.setCredentials(authScope, credentials);
    }
}
