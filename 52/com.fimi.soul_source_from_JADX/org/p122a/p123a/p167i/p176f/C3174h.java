package org.p122a.p123a.p167i.p176f;

import org.apache.http.conn.routing.HttpRoutePlanner;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p152c.C2977e;
import org.p122a.p123a.p180o.C3234a;

@C2914d
/* renamed from: org.a.a.i.f.h */
public class C3174h implements C3167b {
    private static final String f15554a = "HttpClient";
    private final C3167b f15555b;
    private final C2977e f15556c;
    private final HttpRoutePlanner f15557d;

    public C3174h(C3167b c3167b, HttpRoutePlanner httpRoutePlanner, C2977e c2977e) {
        C3234a.m17886a((Object) c3167b, "HTTP client request executor");
        C3234a.m17886a((Object) httpRoutePlanner, "HTTP route planner");
        C3234a.m17886a((Object) c2977e, "HTTP redirect strategy");
        this.f15555b = c3167b;
        this.f15557d = httpRoutePlanner;
        this.f15556c = c2977e;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.p122a.p123a.p152c.p156c.C2946d m17716a(org.apache.http.conn.routing.HttpRoute r12, org.p122a.p123a.p152c.p156c.C2957p r13, org.p122a.p123a.p152c.p160e.C2968a r14, org.p122a.p123a.p152c.p156c.C2941h r15) {
        /*
        r11 = this;
        r1 = "HTTP route";
        org.p122a.p123a.p180o.C3234a.m17886a(r12, r1);
        r1 = "HTTP request";
        org.p122a.p123a.p180o.C3234a.m17886a(r13, r1);
        r1 = "HTTP context";
        org.p122a.p123a.p180o.C3234a.m17886a(r14, r1);
        r1 = r14.m16895c();
        if (r1 == 0) goto L_0x0018;
    L_0x0015:
        r1.clear();
    L_0x0018:
        r5 = r14.m16906n();
        r1 = r5.m16749i();
        if (r1 <= 0) goto L_0x0063;
    L_0x0022:
        r1 = r5.m16749i();
        r3 = r1;
    L_0x0027:
        r1 = 0;
        r2 = r13;
    L_0x0029:
        r4 = r11.f15555b;
        r6 = r4.m17698a(r12, r2, r14, r15);
        r4 = r5.m16746f();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r4 == 0) goto L_0x0167;
    L_0x0035:
        r4 = r11.f15556c;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r4 = r4.m16910a(r2, r6, r14);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r4 == 0) goto L_0x0167;
    L_0x003d:
        if (r1 < r3) goto L_0x0067;
    L_0x003f:
        r1 = new org.apache.http.client.RedirectException;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2.<init>();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r4 = "Maximum redirects (";
        r2 = r2.append(r4);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r3 = ") exceeded";
        r2 = r2.append(r3);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2 = r2.toString();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1.<init>(r2);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        throw r1;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x005e:
        r1 = move-exception;
        r6.close();
        throw r1;
    L_0x0063:
        r1 = 50;
        r3 = r1;
        goto L_0x0027;
    L_0x0067:
        r4 = r1 + 1;
        r1 = r11.f15556c;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r1.m16911b(r2, r6, r14);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2 = r1.headerIterator();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2 = r2.hasNext();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r2 != 0) goto L_0x0084;
    L_0x0079:
        r2 = r13.m16831a();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2 = r2.getAllHeaders();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1.setHeaders(r2);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x0084:
        r2 = org.p122a.p123a.p152c.p156c.C2957p.m16830a(r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r2 instanceof org.apache.http.HttpEntityEnclosingRequest;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r1 == 0) goto L_0x0093;
    L_0x008c:
        r0 = r2;
        r0 = (org.apache.http.HttpEntityEnclosingRequest) r0;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r0;
        org.p122a.p123a.p167i.p176f.C3176j.m17717a(r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x0093:
        r1 = r2.getURI();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r7 = org.p122a.p123a.p152c.p161f.C2988k.m16975b(r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r7 != 0) goto L_0x00bb;
    L_0x009d:
        r2 = new org.apache.http.ProtocolException;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r3 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r3.<init>();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r4 = "Redirect URI does not specify a valid host name: ";
        r3 = r3.append(r4);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r3.append(r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r1.toString();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r2.<init>(r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        throw r2;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x00b6:
        r1 = move-exception;
        r6.close();
        throw r1;
    L_0x00bb:
        r8 = r12.getTargetHost();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r8 = r8.equals(r7);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r8 != 0) goto L_0x0103;
    L_0x00c5:
        r8 = r14.m16903k();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r8 == 0) goto L_0x00de;
    L_0x00cb:
        r9 = "HttpClient";
        r10 = 3;
        r9 = android.util.Log.isLoggable(r9, r10);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r9 == 0) goto L_0x00db;
    L_0x00d4:
        r9 = "HttpClient";
        r10 = "Resetting target auth state";
        android.util.Log.d(r9, r10);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x00db:
        r8.m16723a();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x00de:
        r8 = r14.m16904l();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r8 == 0) goto L_0x0103;
    L_0x00e4:
        r9 = r8.m16731c();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r9 == 0) goto L_0x0103;
    L_0x00ea:
        r9 = r9.isConnectionBased();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r9 == 0) goto L_0x0103;
    L_0x00f0:
        r9 = "HttpClient";
        r10 = 3;
        r9 = android.util.Log.isLoggable(r9, r10);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r9 == 0) goto L_0x0100;
    L_0x00f9:
        r9 = "HttpClient";
        r10 = "Resetting proxy auth state";
        android.util.Log.d(r9, r10);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x0100:
        r8.m16723a();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x0103:
        r8 = r11.f15557d;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r12 = r8.determineRoute(r7, r2, r14);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r7 = "HttpClient";
        r8 = 3;
        r7 = android.util.Log.isLoggable(r7, r8);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        if (r7 == 0) goto L_0x0134;
    L_0x0112:
        r7 = "HttpClient";
        r8 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r8.<init>();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r9 = "Redirecting to '";
        r8 = r8.append(r9);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r8.append(r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r8 = "' via ";
        r1 = r1.append(r8);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r1.append(r12);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r1.toString();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        android.util.Log.d(r7, r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
    L_0x0134:
        r1 = r6.getEntity();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        org.p122a.p123a.p180o.C3237d.m17904b(r1);	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r6.close();	 Catch:{ RuntimeException -> 0x005e, IOException -> 0x00b6, HttpException -> 0x0141 }
        r1 = r4;
        goto L_0x0029;
    L_0x0141:
        r1 = move-exception;
        r2 = r6.getEntity();	 Catch:{ IOException -> 0x014d }
        org.p122a.p123a.p180o.C3237d.m17904b(r2);	 Catch:{ IOException -> 0x014d }
        r6.close();
    L_0x014c:
        throw r1;
    L_0x014d:
        r2 = move-exception;
        r3 = "HttpClient";
        r4 = 3;
        r3 = android.util.Log.isLoggable(r3, r4);	 Catch:{ all -> 0x0162 }
        if (r3 == 0) goto L_0x015e;
    L_0x0157:
        r3 = "HttpClient";
        r4 = "I/O error while releasing connection";
        android.util.Log.d(r3, r4, r2);	 Catch:{ all -> 0x0162 }
    L_0x015e:
        r6.close();
        goto L_0x014c;
    L_0x0162:
        r1 = move-exception;
        r6.close();
        throw r1;
    L_0x0167:
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.f.h.a(org.apache.http.conn.routing.HttpRoute, org.a.a.c.c.p, org.a.a.c.e.a, org.a.a.c.c.h):org.a.a.c.c.d");
    }
}
