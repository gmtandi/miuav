package org.p122a.p123a.p167i;

import java.net.Socket;
import org.apache.http.HttpConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentLengthStrategy;
import org.p122a.p123a.C2994d;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p173j.C3190c;

@C2912b
/* renamed from: org.a.a.i.f */
public class C3181f implements C2994d<C3166e> {
    public static final C3181f f15568a;
    private final C2995a f15569b;
    private final ContentLengthStrategy f15570c;
    private final ContentLengthStrategy f15571d;
    private final C3114b<HttpRequest> f15572e;
    private final C3190c<HttpResponse> f15573f;

    static {
        f15568a = new C3181f();
    }

    public C3181f() {
        this(null, null, null, null, null);
    }

    public C3181f(C2995a c2995a) {
        this(c2995a, null, null, null, null);
    }

    public C3181f(C2995a c2995a, C3114b<HttpRequest> c3114b, C3190c<HttpResponse> c3190c) {
        this(c2995a, null, null, c3114b, c3190c);
    }

    public C3181f(C2995a c2995a, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, C3114b<HttpRequest> c3114b, C3190c<HttpResponse> c3190c) {
        if (c2995a == null) {
            c2995a = C2995a.f14958a;
        }
        this.f15569b = c2995a;
        this.f15570c = contentLengthStrategy;
        this.f15571d = contentLengthStrategy2;
        this.f15572e = c3114b;
        this.f15573f = c3190c;
    }

    public /* synthetic */ HttpConnection m17728a(Socket socket) {
        return m17729b(socket);
    }

    public C3166e m17729b(Socket socket) {
        C3166e c3166e = new C3166e(this.f15569b.m17009a(), this.f15569b.m17010b(), C3101b.m17472a(this.f15569b), C3101b.m17473b(this.f15569b), this.f15569b.m17014f(), this.f15570c, this.f15571d, this.f15572e, this.f15573f);
        c3166e.m17695a(socket);
        return c3166e;
    }
}
