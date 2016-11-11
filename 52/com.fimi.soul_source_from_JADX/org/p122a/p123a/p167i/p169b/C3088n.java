package org.p122a.p123a.p167i.p169b;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.C2990f;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.b.n */
public class C3088n implements C2990f {
    private final int f15280a;
    private final long f15281b;

    public C3088n() {
        this(1, XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
    }

    public C3088n(int i, int i2) {
        C3234a.m17883a(i, "Max retries");
        C3234a.m17883a(i2, "Retry interval");
        this.f15280a = i;
        this.f15281b = (long) i2;
    }

    public long m17381a() {
        return this.f15281b;
    }

    public boolean m17382a(HttpResponse httpResponse, int i, HttpContext httpContext) {
        return i <= this.f15280a && httpResponse.getStatusLine().getStatusCode() == C2799f.f14266d;
    }
}
