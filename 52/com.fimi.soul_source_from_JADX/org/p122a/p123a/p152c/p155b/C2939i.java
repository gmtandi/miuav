package org.p122a.p123a.p152c.p155b;

import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.NameValuePair;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p161f.C2989l;
import org.p122a.p123a.p154h.C2938j;
import org.p122a.p123a.p154h.C3050e;

@C2913c
/* renamed from: org.a.a.c.b.i */
public class C2939i extends C2938j {
    public C2939i(Iterable<? extends NameValuePair> iterable) {
        this((Iterable) iterable, null);
    }

    public C2939i(Iterable<? extends NameValuePair> iterable, Charset charset) {
        super(C2989l.m16978a((Iterable) iterable, charset != null ? charset : Charset.forName("ISO-8859-1")), C3050e.m17157a(C2989l.f14939a, charset));
    }

    public C2939i(List<? extends NameValuePair> list) {
        this((Iterable) list, (Charset) null);
    }

    public C2939i(List<? extends NameValuePair> list, String str) {
        super(C2989l.m16983a((List) list, str != null ? str : Charset.forName("ISO-8859-1").name()), C3050e.m17156a(C2989l.f14939a, str));
    }
}
