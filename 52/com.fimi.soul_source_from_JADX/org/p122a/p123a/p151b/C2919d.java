package org.p122a.p123a.p151b;

import java.util.Collection;
import java.util.Queue;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.b.d */
public class C2919d {
    private C2917b f14769a;
    private AuthScheme f14770b;
    private AuthScope f14771c;
    private Credentials f14772d;
    private Queue<C2916a> f14773e;

    public C2919d() {
        this.f14769a = C2917b.UNCHALLENGED;
    }

    public void m16723a() {
        this.f14769a = C2917b.UNCHALLENGED;
        this.f14773e = null;
        this.f14770b = null;
        this.f14771c = null;
        this.f14772d = null;
    }

    public void m16724a(Queue<C2916a> queue) {
        C3234a.m17887a((Collection) queue, "Queue of auth options");
        this.f14773e = queue;
        this.f14770b = null;
        this.f14772d = null;
    }

    public void m16725a(C2917b c2917b) {
        if (c2917b == null) {
            c2917b = C2917b.UNCHALLENGED;
        }
        this.f14769a = c2917b;
    }

    @Deprecated
    public void m16726a(AuthScheme authScheme) {
        if (authScheme == null) {
            m16723a();
        } else {
            this.f14770b = authScheme;
        }
    }

    public void m16727a(AuthScheme authScheme, Credentials credentials) {
        C3234a.m17886a((Object) authScheme, "Auth scheme");
        C3234a.m17886a((Object) credentials, "Credentials");
        this.f14770b = authScheme;
        this.f14772d = credentials;
        this.f14773e = null;
    }

    @Deprecated
    public void m16728a(AuthScope authScope) {
        this.f14771c = authScope;
    }

    @Deprecated
    public void m16729a(Credentials credentials) {
        this.f14772d = credentials;
    }

    public C2917b m16730b() {
        return this.f14769a;
    }

    public AuthScheme m16731c() {
        return this.f14770b;
    }

    public Credentials m16732d() {
        return this.f14772d;
    }

    public Queue<C2916a> m16733e() {
        return this.f14773e;
    }

    public boolean m16734f() {
        return (this.f14773e == null || this.f14773e.isEmpty()) ? false : true;
    }

    @Deprecated
    public void m16735g() {
        m16723a();
    }

    @Deprecated
    public boolean m16736h() {
        return this.f14770b != null;
    }

    @Deprecated
    public AuthScope m16737i() {
        return this.f14771c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state:").append(this.f14769a).append(";");
        if (this.f14770b != null) {
            stringBuilder.append("auth scheme:").append(this.f14770b.getSchemeName()).append(";");
        }
        if (this.f14772d != null) {
            stringBuilder.append("credentials present");
        }
        return stringBuilder.toString();
    }
}
