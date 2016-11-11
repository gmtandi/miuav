package org.p122a.p123a.p167i.p170c;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p124f.C3017b;
import org.p122a.p123a.p124f.C3040h;
import org.p122a.p123a.p124f.C3041i;
import org.p122a.p123a.p124f.C3042j;
import org.p122a.p123a.p124f.p164b.C3014a;
import org.p122a.p123a.p124f.p164b.C3015b;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p162e.C2997c;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.c.m */
class C3121m {
    static final String f15425a = "http.socket-factory-registry";
    private static final String f15426b = "HttpClient";
    private final C2997c<C3014a> f15427c;
    private final C3041i f15428d;
    private final C3017b f15429e;

    C3121m(C2997c<C3014a> c2997c, C3041i c3041i, C3017b c3017b) {
        C3234a.m17886a((Object) c2997c, "Socket factory registry");
        this.f15427c = c2997c;
        if (c3041i == null) {
            c3041i = C3120l.f15424a;
        }
        this.f15428d = c3041i;
        if (c3017b == null) {
            c3017b = C3132w.f15456a;
        }
        this.f15429e = c3017b;
    }

    private static String m17583a(IOException iOException, HttpHost httpHost, InetAddress... inetAddressArr) {
        StringBuilder append = new StringBuilder().append("Connect to ").append(httpHost != null ? httpHost.toHostString() : "remote host");
        String str = (inetAddressArr == null || inetAddressArr.length <= 0) ? C2915a.f14760f : MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Arrays.asList(inetAddressArr);
        append = append.append(str);
        str = (iOException == null || iOException.getMessage() == null) ? " timed out" : " failed: " + iOException.getMessage();
        return append.append(str).toString();
    }

    private C2997c<C3014a> m17584a(HttpContext httpContext) {
        C2997c<C3014a> c2997c = (C2997c) httpContext.getAttribute(f15425a);
        return c2997c == null ? this.f15427c : c2997c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m17585a(org.p122a.p123a.p124f.C3040h r13, org.apache.http.HttpHost r14, java.net.InetSocketAddress r15, int r16, org.p122a.p123a.p162e.C3002h r17, org.apache.http.protocol.HttpContext r18) {
        /*
        r12 = this;
        r0 = r18;
        r1 = r12.m17584a(r0);
        r2 = r14.getSchemeName();
        r1 = r1.m17023a(r2);
        r1 = (org.p122a.p123a.p124f.p164b.C3014a) r1;
        if (r1 != 0) goto L_0x002f;
    L_0x0012:
        r1 = new org.a.a.f.j;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r14.getSchemeName();
        r2 = r2.append(r3);
        r3 = " protocol is not supported";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        throw r1;
    L_0x002f:
        r2 = r12.f15429e;
        r3 = r14.getHostName();
        r10 = r2.m17073a(r3);
        r2 = r12.f15428d;
        r11 = r2.m17152a(r14);
        r2 = 0;
        r8 = r2;
    L_0x0041:
        r2 = r10.length;
        if (r8 >= r2) goto L_0x00d2;
    L_0x0044:
        r4 = r10[r8];
        r2 = r10.length;
        r2 = r2 + -1;
        if (r8 != r2) goto L_0x00d3;
    L_0x004b:
        r2 = 1;
        r9 = r2;
    L_0x004d:
        r0 = r18;
        r3 = r1.m17068a(r0);
        r2 = r17.m17039a();
        r3.setSoTimeout(r2);
        r2 = r17.m17040b();
        r3.setReuseAddress(r2);
        r2 = r17.m17043e();
        r3.setTcpNoDelay(r2);
        r2 = r17.m17042d();
        r3.setKeepAlive(r2);
        r5 = r17.m17041c();
        if (r5 < 0) goto L_0x007b;
    L_0x0075:
        if (r5 <= 0) goto L_0x00d7;
    L_0x0077:
        r2 = 1;
    L_0x0078:
        r3.setSoLinger(r2, r5);
    L_0x007b:
        r13.m17149a(r3);
        r5 = new java.net.InetSocketAddress;
        r5.<init>(r4, r11);
        r2 = "HttpClient";
        r4 = 3;
        r2 = android.util.Log.isLoggable(r2, r4);
        if (r2 == 0) goto L_0x00a4;
    L_0x008c:
        r2 = "HttpClient";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = "Connecting to ";
        r4 = r4.append(r6);
        r4 = r4.append(r5);
        r4 = r4.toString();
        android.util.Log.d(r2, r4);
    L_0x00a4:
        r2 = r16;
        r4 = r14;
        r6 = r15;
        r7 = r18;
        r2 = r1.m17067a(r2, r3, r4, r5, r6, r7);	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        r13.m17149a(r2);	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        r2 = "HttpClient";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        if (r2 == 0) goto L_0x00d2;
    L_0x00ba:
        r2 = "HttpClient";
        r3 = new java.lang.StringBuilder;	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        r3.<init>();	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        r4 = "Connection established ";
        r3 = r3.append(r4);	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        r3 = r3.append(r13);	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        r3 = r3.toString();	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
        android.util.Log.d(r2, r3);	 Catch:{ SocketTimeoutException -> 0x00d9, ConnectException -> 0x011a }
    L_0x00d2:
        return;
    L_0x00d3:
        r2 = 0;
        r9 = r2;
        goto L_0x004d;
    L_0x00d7:
        r2 = 0;
        goto L_0x0078;
    L_0x00d9:
        r2 = move-exception;
        if (r9 == 0) goto L_0x00e8;
    L_0x00dc:
        r3 = new org.apache.http.conn.ConnectTimeoutException;
        r4 = org.p122a.p123a.p167i.p170c.C3121m.m17583a(r2, r14, r10);
        r3.<init>(r4);
        r3.initCause(r2);
    L_0x00e8:
        r2 = "HttpClient";
        r3 = 3;
        r2 = android.util.Log.isLoggable(r2, r3);
        if (r2 == 0) goto L_0x0115;
    L_0x00f1:
        r2 = "HttpClient";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Connect to ";
        r3 = r3.append(r4);
        r3 = r3.append(r5);
        r4 = " timed out. ";
        r3 = r3.append(r4);
        r4 = "Connection will be retried using another IP address";
        r3 = r3.append(r4);
        r3 = r3.toString();
        android.util.Log.d(r2, r3);
    L_0x0115:
        r2 = r8 + 1;
        r8 = r2;
        goto L_0x0041;
    L_0x011a:
        r2 = move-exception;
        if (r9 == 0) goto L_0x00e8;
    L_0x011d:
        r1 = r2.getMessage();
        r3 = "Connection timed out";
        r1 = r3.equals(r1);
        if (r1 == 0) goto L_0x0136;
    L_0x0129:
        r1 = new org.apache.http.conn.ConnectTimeoutException;
        r3 = org.p122a.p123a.p167i.p170c.C3121m.m17583a(r2, r14, r10);
        r1.<init>(r3);
        r1.initCause(r2);
        throw r1;
    L_0x0136:
        r1 = new org.apache.http.conn.HttpHostConnectException;
        r1.<init>(r14, r2);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.c.m.a(org.a.a.f.h, org.apache.http.HttpHost, java.net.InetSocketAddress, int, org.a.a.e.h, org.apache.http.protocol.HttpContext):void");
    }

    public void m17586a(C3040h c3040h, HttpHost httpHost, HttpContext httpContext) {
        C3014a c3014a = (C3014a) m17584a(C2968a.m16884a(httpContext)).m17023a(httpHost.getSchemeName());
        if (c3014a == null) {
            throw new C3042j(httpHost.getSchemeName() + " protocol is not supported");
        } else if (c3014a instanceof C3015b) {
            c3040h.m17149a(((C3015b) c3014a).m17069a(c3040h.m17150b(), httpHost.getHostName(), this.f15428d.m17152a(httpHost), httpContext));
        } else {
            throw new C3042j(httpHost.getSchemeName() + " protocol does not support connection upgrade");
        }
    }
}
