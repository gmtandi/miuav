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
/* renamed from: org.a.a.i.d */
public class C3162d implements C2994d<C3116c> {
    public static final C3162d f15508a;
    private final C2995a f15509b;
    private final ContentLengthStrategy f15510c;
    private final ContentLengthStrategy f15511d;
    private final C3190c<HttpRequest> f15512e;
    private final C3114b<HttpResponse> f15513f;

    static {
        f15508a = new C3162d();
    }

    public C3162d() {
        this(null, null, null, null, null);
    }

    public C3162d(C2995a c2995a) {
        this(c2995a, null, null, null, null);
    }

    public C3162d(C2995a c2995a, C3190c<HttpRequest> c3190c, C3114b<HttpResponse> c3114b) {
        this(c2995a, null, null, c3190c, c3114b);
    }

    public C3162d(C2995a c2995a, ContentLengthStrategy contentLengthStrategy, ContentLengthStrategy contentLengthStrategy2, C3190c<HttpRequest> c3190c, C3114b<HttpResponse> c3114b) {
        if (c2995a == null) {
            c2995a = C2995a.f14958a;
        }
        this.f15509b = c2995a;
        this.f15510c = contentLengthStrategy;
        this.f15511d = contentLengthStrategy2;
        this.f15512e = c3190c;
        this.f15513f = c3114b;
    }

    public /* synthetic */ HttpConnection m17693a(Socket socket) {
        return m17694b(socket);
    }

    public C3116c m17694b(Socket socket) {
        C3116c c3116c = new C3116c(this.f15509b.m17009a(), this.f15509b.m17010b(), C3101b.m17472a(this.f15509b), C3101b.m17473b(this.f15509b), this.f15509b.m17014f(), this.f15510c, this.f15511d, this.f15512e, this.f15513f);
        c3116c.m17573a(socket);
        return c3116c;
    }
}
