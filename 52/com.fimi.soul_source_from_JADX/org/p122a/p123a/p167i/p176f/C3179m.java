package org.p122a.p123a.p167i.p176f;

import android.util.Log;
import java.io.InterruptedIOException;
import org.apache.http.Header;
import org.apache.http.conn.routing.HttpRoute;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.C2990f;
import org.p122a.p123a.p152c.p156c.C2941h;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p152c.p156c.C2957p;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.f.m */
public class C3179m implements C3167b {
    private static final String f15564a = "HttpClient";
    private final C3167b f15565b;
    private final C2990f f15566c;

    public C3179m(C3167b c3167b, C2990f c2990f) {
        C3234a.m17886a((Object) c3167b, "HTTP request executor");
        C3234a.m17886a((Object) c2990f, "Retry strategy");
        this.f15565b = c3167b;
        this.f15566c = c2990f;
    }

    public C2946d m17726a(HttpRoute httpRoute, C2957p c2957p, C2968a c2968a, C2941h c2941h) {
        Header[] allHeaders = c2957p.getAllHeaders();
        int i = 1;
        while (true) {
            C2946d a = this.f15565b.m17698a(httpRoute, c2957p, c2968a, c2941h);
            if (!this.f15566c.m16998a(a, i, c2968a)) {
                return a;
            }
            a.close();
            long a2 = this.f15566c.m16997a();
            if (a2 > 0) {
                try {
                    if (Log.isLoggable(f15564a, 3)) {
                        Log.d(f15564a, "Wait for " + a2);
                    }
                    Thread.sleep(a2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException();
                } catch (RuntimeException e2) {
                    a.close();
                    throw e2;
                }
            }
            c2957p.setHeaders(allHeaders);
            i++;
        }
    }
}
