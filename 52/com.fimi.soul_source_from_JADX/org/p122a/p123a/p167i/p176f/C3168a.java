package org.p122a.p123a.p167i.p176f;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.C2964c;
import org.p122a.p123a.p152c.C2966d;
import org.p122a.p123a.p152c.p156c.C2941h;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p152c.p156c.C2957p;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.f.a */
public class C3168a implements C3167b {
    private final C3167b f15522a;
    private final C2966d f15523b;
    private final C2964c f15524c;

    public C3168a(C3167b c3167b, C2966d c2966d, C2964c c2964c) {
        C3234a.m17886a((Object) c3167b, "HTTP client request executor");
        C3234a.m17886a((Object) c2966d, "Connection backoff strategy");
        C3234a.m17886a((Object) c2964c, "Backoff manager");
        this.f15522a = c3167b;
        this.f15523b = c2966d;
        this.f15524c = c2964c;
    }

    public C2946d m17699a(HttpRoute httpRoute, C2957p c2957p, C2968a c2968a, C2941h c2941h) {
        C3234a.m17886a((Object) httpRoute, "HTTP route");
        C3234a.m17886a((Object) c2957p, "HTTP request");
        C3234a.m17886a((Object) c2968a, "HTTP context");
        C2946d c2946d = null;
        try {
            HttpResponse a = this.f15522a.m17698a(httpRoute, c2957p, c2968a, c2941h);
            if (this.f15523b.m16872a(a)) {
                this.f15524c.m16868a(httpRoute);
            } else {
                this.f15524c.m16869b(httpRoute);
            }
            return a;
        } catch (Throwable e) {
            if (c2946d != null) {
                c2946d.close();
            }
            if (this.f15523b.m16871a(e)) {
                this.f15524c.m16868a(httpRoute);
            }
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            } else if (e instanceof HttpException) {
                throw ((HttpException) e);
            } else if (e instanceof IOException) {
                throw ((IOException) e);
            } else {
                throw new UndeclaredThrowableException(e);
            }
        }
    }
}
