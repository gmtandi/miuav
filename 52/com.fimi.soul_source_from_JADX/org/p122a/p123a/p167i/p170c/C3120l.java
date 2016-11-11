package org.p122a.p123a.p167i.p170c;

import com.facebook.common.util.UriUtil;
import org.apache.http.HttpHost;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p124f.C3042j;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.c.l */
public class C3120l implements C3041i {
    public static final C3120l f15424a;

    static {
        f15424a = new C3120l();
    }

    public int m17582a(HttpHost httpHost) {
        C3234a.m17886a((Object) httpHost, "HTTP host");
        int port = httpHost.getPort();
        if (port > 0) {
            return port;
        }
        String schemeName = httpHost.getSchemeName();
        if (schemeName.equalsIgnoreCase(UriUtil.HTTP_SCHEME)) {
            return 80;
        }
        if (schemeName.equalsIgnoreCase(UriUtil.HTTPS_SCHEME)) {
            return 443;
        }
        throw new C3042j(schemeName + " protocol is not supported");
    }
}
