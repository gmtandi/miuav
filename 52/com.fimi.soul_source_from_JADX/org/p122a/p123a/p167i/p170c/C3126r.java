package org.p122a.p123a.p167i.p170c;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p124f.C3037e;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p167i.p172g.C3191j;
import org.p122a.p123a.p173j.C3114b;
import org.p122a.p123a.p173j.C3190c;

@C2912b
/* renamed from: org.a.a.i.c.r */
public class C3126r implements C3037e<HttpRoute, C3040h> {
    public static final C3126r f15439a;
    private static final AtomicLong f15440b;
    private final C3190c<HttpRequest> f15441c;
    private final C3114b<HttpResponse> f15442d;

    static {
        f15440b = new AtomicLong();
        f15439a = new C3126r();
    }

    public C3126r() {
        this(null, null);
    }

    public C3126r(C3114b<HttpResponse> c3114b) {
        this(null, c3114b);
    }

    public C3126r(C3190c<HttpRequest> c3190c, C3114b<HttpResponse> c3114b) {
        C3190c c3190c2;
        C3114b c3114b2;
        if (c3190c == null) {
            c3190c2 = C3191j.f15608a;
        }
        this.f15441c = c3190c2;
        if (c3114b == null) {
            c3114b2 = C3115h.f15414a;
        }
        this.f15442d = c3114b2;
    }

    public C3040h m17593a(HttpRoute httpRoute, C2995a c2995a) {
        CharsetDecoder newDecoder;
        CharsetEncoder newEncoder;
        if (c2995a == null) {
            c2995a = C2995a.f14958a;
        }
        Charset c = c2995a.m17011c();
        CodingErrorAction d = c2995a.m17012d() != null ? c2995a.m17012d() : CodingErrorAction.REPORT;
        CodingErrorAction e = c2995a.m17013e() != null ? c2995a.m17013e() : CodingErrorAction.REPORT;
        if (c != null) {
            newDecoder = c.newDecoder();
            newDecoder.onMalformedInput(d);
            newDecoder.onUnmappableCharacter(e);
            newEncoder = c.newEncoder();
            newEncoder.onMalformedInput(d);
            newEncoder.onUnmappableCharacter(e);
        } else {
            newEncoder = null;
            newDecoder = null;
        }
        return new C3124p("http-outgoing-" + Long.toString(f15440b.getAndIncrement()), c2995a.m17009a(), c2995a.m17010b(), newDecoder, newEncoder, c2995a.m17014f(), null, null, this.f15441c, this.f15442d);
    }
}
