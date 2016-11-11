package org.p122a.p123a.p167i.p177h;

import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p171m.C3108i;

@C2914d
/* renamed from: org.a.a.i.h.c */
public class C3204c extends C3108i<HttpHost, HttpClientConnection> {
    public C3204c(String str, HttpHost httpHost, HttpClientConnection httpClientConnection) {
        super(str, httpHost, httpClientConnection);
    }

    public boolean m17784e() {
        return !((HttpClientConnection) m17540i()).isOpen();
    }

    public void m17785f() {
        try {
            ((HttpClientConnection) m17540i()).close();
        } catch (IOException e) {
        }
    }
}
