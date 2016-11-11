package org.p122a.p123a.p167i.p169b;

import it.p074a.p075a.C2799f;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import org.apache.http.HttpResponse;
import org.p122a.p123a.p152c.C2966d;

/* renamed from: org.a.a.i.b.j */
public class C3086j implements C2966d {
    public boolean m17379a(Throwable th) {
        return (th instanceof SocketTimeoutException) || (th instanceof ConnectException);
    }

    public boolean m17380a(HttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode() == C2799f.f14266d;
    }
}
