package org.p122a.p123a.p167i;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.i.k */
public class C3208k implements ConnectionReuseStrategy {
    public static final C3208k f15653a;

    static {
        f15653a = new C3208k();
    }

    public boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext) {
        return false;
    }
}
