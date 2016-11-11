package org.p122a.p123a.p151b;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.Credentials;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.b.a */
public final class C2916a {
    private final AuthScheme f14761a;
    private final Credentials f14762b;

    public C2916a(AuthScheme authScheme, Credentials credentials) {
        C3234a.m17886a((Object) authScheme, "Auth scheme");
        C3234a.m17886a((Object) credentials, "User credentials");
        this.f14761a = authScheme;
        this.f14762b = credentials;
    }

    public AuthScheme m16720a() {
        return this.f14761a;
    }

    public Credentials m16721b() {
        return this.f14762b;
    }

    public String toString() {
        return this.f14761a.toString();
    }
}
