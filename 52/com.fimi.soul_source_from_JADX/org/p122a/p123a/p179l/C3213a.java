package org.p122a.p123a.p179l;

import java.nio.charset.Charset;
import org.apache.http.params.HttpParams;
import org.p122a.p123a.p162e.C2995a;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p162e.C3002h;

@Deprecated
/* renamed from: org.a.a.l.a */
public final class C3213a {
    private C3213a() {
    }

    public static C3002h m17825a(HttpParams httpParams) {
        return C3002h.m17038g().m17046a(httpParams.getIntParameter("http.socket.timeout", 0)).m17048b(httpParams.getIntParameter("http.socket.linger", -1)).m17050c(httpParams.getBooleanParameter("http.tcp.nodelay", true)).m17045a();
    }

    public static C2998d m17826b(HttpParams httpParams) {
        return C2998d.m17026d().m17032b(httpParams.getIntParameter("http.connection.max-header-count", -1)).m17031a(httpParams.getIntParameter("http.connection.max-line-length", -1)).m17030a();
    }

    public static C2995a m17827c(HttpParams httpParams) {
        String str = (String) httpParams.getParameter("http.protocol.element-charset");
        return C2995a.m17008h().m17018a(str != null ? Charset.forName(str) : null).m17020a(C3213a.m17826b(httpParams)).m17016a();
    }
}
