package org.p122a.p123a.p167i.p169b;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.i.b.e */
public class C3083e implements CredentialsProvider {
    private final ConcurrentHashMap<AuthScope, Credentials> f15277a;

    public C3083e() {
        this.f15277a = new ConcurrentHashMap();
    }

    private static Credentials m17375a(Map<AuthScope, Credentials> map, AuthScope authScope) {
        Credentials credentials = (Credentials) map.get(authScope);
        if (credentials != null) {
            return credentials;
        }
        int i = -1;
        AuthScope authScope2 = null;
        for (AuthScope authScope3 : map.keySet()) {
            AuthScope authScope32;
            int i2;
            int match = authScope.match(authScope32);
            if (match > i) {
                i2 = match;
            } else {
                authScope32 = authScope2;
                i2 = i;
            }
            i = i2;
            authScope2 = authScope32;
        }
        return authScope2 != null ? (Credentials) map.get(authScope2) : credentials;
    }

    public void clear() {
        this.f15277a.clear();
    }

    public Credentials getCredentials(AuthScope authScope) {
        C3234a.m17886a((Object) authScope, "Authentication scope");
        return C3083e.m17375a(this.f15277a, authScope);
    }

    public void setCredentials(AuthScope authScope, Credentials credentials) {
        C3234a.m17886a((Object) authScope, "Authentication scope");
        this.f15277a.put(authScope, credentials);
    }

    public String toString() {
        return this.f15277a.toString();
    }
}
