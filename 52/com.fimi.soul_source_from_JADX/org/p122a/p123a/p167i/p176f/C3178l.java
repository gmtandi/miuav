package org.p122a.p123a.p167i.p176f;

import org.apache.http.client.HttpRequestRetryHandler;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.i.f.l */
public class C3178l implements C3167b {
    private static final String f15561a = "HttpClient";
    private final C3167b f15562b;
    private final HttpRequestRetryHandler f15563c;

    public C3178l(C3167b c3167b, HttpRequestRetryHandler httpRequestRetryHandler) {
        C3234a.m17886a((Object) c3167b, "HTTP request executor");
        C3234a.m17886a((Object) httpRequestRetryHandler, "HTTP request retry handler");
        this.f15562b = c3167b;
        this.f15563c = httpRequestRetryHandler;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.p122a.p123a.p152c.p156c.C2946d m17725a(org.apache.http.conn.routing.HttpRoute r9, org.p122a.p123a.p152c.p156c.C2957p r10, org.p122a.p123a.p152c.p160e.C2968a r11, org.p122a.p123a.p152c.p156c.C2941h r12) {
        /*
        r8 = this;
        r7 = 4;
        r6 = 3;
        r0 = "HTTP route";
        org.p122a.p123a.p180o.C3234a.m17886a(r9, r0);
        r0 = "HTTP request";
        org.p122a.p123a.p180o.C3234a.m17886a(r10, r0);
        r0 = "HTTP context";
        org.p122a.p123a.p180o.C3234a.m17886a(r11, r0);
        r2 = r10.getAllHeaders();
        r0 = 1;
        r1 = r0;
    L_0x0017:
        r0 = r8.f15562b;	 Catch:{ IOException -> 0x001e }
        r0 = r0.m17698a(r9, r10, r11, r12);	 Catch:{ IOException -> 0x001e }
        return r0;
    L_0x001e:
        r0 = move-exception;
        if (r12 == 0) goto L_0x0037;
    L_0x0021:
        r3 = r12.isAborted();
        if (r3 == 0) goto L_0x0037;
    L_0x0027:
        r1 = "HttpClient";
        r1 = android.util.Log.isLoggable(r1, r6);
        if (r1 == 0) goto L_0x0036;
    L_0x002f:
        r1 = "HttpClient";
        r2 = "Request has been aborted";
        android.util.Log.d(r1, r2);
    L_0x0036:
        throw r0;
    L_0x0037:
        r3 = r8.f15563c;
        r3 = r3.retryRequest(r0, r1, r11);
        if (r3 == 0) goto L_0x00d7;
    L_0x003f:
        r3 = "HttpClient";
        r3 = android.util.Log.isLoggable(r3, r7);
        if (r3 == 0) goto L_0x007f;
    L_0x0047:
        r3 = "HttpClient";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "I/O exception (";
        r4 = r4.append(r5);
        r5 = r0.getClass();
        r5 = r5.getName();
        r4 = r4.append(r5);
        r5 = ") caught when processing request to ";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r5 = ": ";
        r4 = r4.append(r5);
        r5 = r0.getMessage();
        r4 = r4.append(r5);
        r4 = r4.toString();
        android.util.Log.i(r3, r4);
    L_0x007f:
        r3 = "HttpClient";
        r3 = android.util.Log.isLoggable(r3, r6);
        if (r3 == 0) goto L_0x0090;
    L_0x0087:
        r3 = "HttpClient";
        r4 = r0.getMessage();
        android.util.Log.d(r3, r4, r0);
    L_0x0090:
        r3 = org.p122a.p123a.p167i.p176f.C3176j.m17719a(r10);
        if (r3 != 0) goto L_0x00af;
    L_0x0096:
        r3 = "HttpClient";
        r3 = android.util.Log.isLoggable(r3, r6);
        if (r3 == 0) goto L_0x00a5;
    L_0x009e:
        r3 = "HttpClient";
        r4 = "Cannot retry non-repeatable request";
        android.util.Log.d(r3, r4);
    L_0x00a5:
        r3 = new org.apache.http.client.NonRepeatableRequestException;
        r4 = "Cannot retry request with a non-repeatable request entity";
        r3.<init>(r4);
        r3.initCause(r0);
    L_0x00af:
        r10.setHeaders(r2);
        r0 = "HttpClient";
        r0 = android.util.Log.isLoggable(r0, r7);
        if (r0 == 0) goto L_0x00d2;
    L_0x00ba:
        r0 = "HttpClient";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Retrying request to ";
        r3 = r3.append(r4);
        r3 = r3.append(r9);
        r3 = r3.toString();
        android.util.Log.i(r0, r3);
    L_0x00d2:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0017;
    L_0x00d7:
        r1 = r0 instanceof org.apache.http.NoHttpResponseException;
        if (r1 == 0) goto L_0x0103;
    L_0x00db:
        r1 = new org.apache.http.NoHttpResponseException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r9.getTargetHost();
        r3 = r3.toHostString();
        r2 = r2.append(r3);
        r3 = " failed to respond";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2);
        r0 = r0.getStackTrace();
        r1.setStackTrace(r0);
        throw r1;
    L_0x0103:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.f.l.a(org.apache.http.conn.routing.HttpRoute, org.a.a.c.c.p, org.a.a.c.e.a, org.a.a.c.c.h):org.a.a.c.c.d");
    }
}
