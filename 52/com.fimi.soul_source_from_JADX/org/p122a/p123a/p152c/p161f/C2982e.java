package org.p122a.p123a.p152c.p161f;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p180o.C3237d;

/* renamed from: org.a.a.c.f.e */
public class C2982e {
    private C2982e() {
    }

    public static void m16923a(C2946d c2946d) {
        if (c2946d != null) {
            try {
                C3237d.m17904b(c2946d.getEntity());
                c2946d.close();
            } catch (IOException e) {
            } catch (Throwable th) {
                c2946d.close();
            }
        }
    }

    public static void m16924a(HttpResponse httpResponse) {
        if (httpResponse != null) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                try {
                    C3237d.m17904b(entity);
                } catch (IOException e) {
                }
            }
        }
    }

    public static void m16925a(HttpClient httpClient) {
        if (httpClient != null && (httpClient instanceof Closeable)) {
            try {
                ((Closeable) httpClient).close();
            } catch (IOException e) {
            }
        }
    }
}
