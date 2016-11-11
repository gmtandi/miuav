package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.C0555c;
import com.android.volley.C0563k;
import com.android.volley.C0570r;
import com.android.volley.ad;
import com.android.volley.ae;
import com.android.volley.ag;
import com.android.volley.ah;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;

/* renamed from: com.android.volley.toolbox.c */
public class C0576c implements C0563k {
    protected static final boolean f3600a;
    private static int f3601d;
    private static int f3602e;
    protected final C0584n f3603b;
    protected final C0577d f3604c;

    static {
        f3600a = ah.f3498b;
        f3601d = 3000;
        f3602e = Opcodes.ACC_SYNTHETIC;
    }

    public C0576c(C0584n c0584n) {
        this(c0584n, new C0577d(f3602e));
    }

    public C0576c(C0584n c0584n, C0577d c0577d) {
        this.f3603b = c0584n;
        this.f3604c = c0577d;
    }

    protected static Map<String, String> m5178a(Header[] headerArr) {
        Map<String, String> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }

    private void m5179a(long j, C0570r<?> c0570r, byte[] bArr, StatusLine statusLine) {
        if (f3600a || j > ((long) f3601d)) {
            String str = "HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]";
            Object[] objArr = new Object[5];
            objArr[0] = c0570r;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(c0570r.m5135z().m5054b());
            ah.m5058b(str, objArr);
        }
    }

    private static void m5180a(String str, C0570r<?> c0570r, ag agVar) {
        ad z = c0570r.m5135z();
        int y = c0570r.m5134y();
        try {
            z.m5053a(agVar);
            c0570r.m5106a(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(y)}));
        } catch (ag e) {
            c0570r.m5106a(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(y)}));
            throw e;
        }
    }

    private void m5181a(Map<String, String> map, C0555c c0555c) {
        if (c0555c != null) {
            if (c0555c.f3507b != null) {
                map.put(C3004e.f14992D, c0555c.f3507b);
            }
            if (c0555c.f3509d > 0) {
                map.put(C3004e.f14991C, DateUtils.formatDate(new Date(c0555c.f3509d)));
            }
        }
    }

    private byte[] m5182a(HttpEntity httpEntity) {
        ag agVar = new ag(this.f3604c, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ae();
            }
            bArr = this.f3604c.m5187a((int) SmileConstants.MAX_SHARED_STRING_VALUES);
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                agVar.write(bArr, 0, read);
            }
            byte[] toByteArray = agVar.toByteArray();
            return toByteArray;
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
                ah.m5056a("Error occured when calling consumingContent", new Object[0]);
            }
            this.f3604c.m5186a(bArr);
            agVar.close();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.C0566n m5183a(com.android.volley.C0570r<?> r19) {
        /*
        r18 = this;
        r16 = android.os.SystemClock.elapsedRealtime();
    L_0x0004:
        r3 = 0;
        r14 = 0;
        r6 = java.util.Collections.emptyMap();
        r2 = new java.util.HashMap;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r4 = r19.m5120k();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r0 = r18;
        r0.m5181a(r2, r4);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r0 = r18;
        r4 = r0.f3603b;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r0 = r19;
        r15 = r4.m5218a(r0, r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x00f3 }
        r12 = r15.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r4 = r12.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r2 = r15.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r6 = com.android.volley.toolbox.C0576c.m5178a(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r4 != r2) goto L_0x0065;
    L_0x0036:
        r2 = r19.m5120k();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        if (r2 != 0) goto L_0x004c;
    L_0x003c:
        r3 = new com.android.volley.n;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r4 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r5 = 0;
        r7 = 1;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
    L_0x004b:
        return r3;
    L_0x004c:
        r3 = r2.f3512g;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r3.putAll(r6);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r7 = new com.android.volley.n;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        r9 = r2.f3506a;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r10 = r2.f3512g;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r11 = 1;
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r12 = r2 - r16;
        r7.<init>(r8, r9, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r3 = r7;
        goto L_0x004b;
    L_0x0065:
        r2 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r4 == r2) goto L_0x006d;
    L_0x0069:
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r4 != r2) goto L_0x007a;
    L_0x006d:
        r2 = "Location";
        r2 = r6.get(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r2 = (java.lang.String) r2;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r0 = r19;
        r0.m5112c(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
    L_0x007a:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        if (r2 == 0) goto L_0x00b4;
    L_0x0080:
        r2 = r15.getEntity();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        r0 = r18;
        r11 = r0.m5182a(r2);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
    L_0x008a:
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
        r8 = r2 - r16;
        r7 = r18;
        r10 = r19;
        r7.m5179a(r8, r10, r11, r12);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r4 < r2) goto L_0x009f;
    L_0x009b:
        r2 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r4 <= r2) goto L_0x00b8;
    L_0x009f:
        r2 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
        r2.<init>();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
        throw r2;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
    L_0x00a5:
        r2 = move-exception;
        r2 = "socket";
        r3 = new com.android.volley.af;
        r3.<init>();
        r0 = r19;
        com.android.volley.toolbox.C0576c.m5180a(r2, r0, r3);
        goto L_0x0004;
    L_0x00b4:
        r2 = 0;
        r11 = new byte[r2];	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0180 }
        goto L_0x008a;
    L_0x00b8:
        r3 = new com.android.volley.n;	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
        r8 = r8 - r16;
        r5 = r11;
        r3.<init>(r4, r5, r6, r7, r8);	 Catch:{ SocketTimeoutException -> 0x00a5, ConnectTimeoutException -> 0x00c6, MalformedURLException -> 0x00d5, IOException -> 0x0185 }
        goto L_0x004b;
    L_0x00c6:
        r2 = move-exception;
        r2 = "connection";
        r3 = new com.android.volley.af;
        r3.<init>();
        r0 = r19;
        com.android.volley.toolbox.C0576c.m5180a(r2, r0, r3);
        goto L_0x0004;
    L_0x00d5:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "Bad URL ";
        r4 = r4.append(r5);
        r5 = r19.m5116g();
        r4 = r4.append(r5);
        r4 = r4.toString();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00f3:
        r2 = move-exception;
        r5 = r14;
    L_0x00f5:
        if (r3 == 0) goto L_0x0141;
    L_0x00f7:
        r3 = r3.getStatusLine();
        r4 = r3.getStatusCode();
        r3 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r4 == r3) goto L_0x0107;
    L_0x0103:
        r3 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r4 != r3) goto L_0x0147;
    L_0x0107:
        r3 = "Request at %s has been redirected to %s";
        r7 = 2;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r9 = r19.m5117h();
        r7[r8] = r9;
        r8 = 1;
        r9 = r19.m5116g();
        r7[r8] = r9;
        com.android.volley.ah.m5060c(r3, r7);
    L_0x011d:
        if (r5 == 0) goto L_0x017a;
    L_0x011f:
        r3 = new com.android.volley.n;
        r7 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r8 = r8 - r16;
        r3.<init>(r4, r5, r6, r7, r8);
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r4 == r2) goto L_0x0133;
    L_0x012f:
        r2 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r4 != r2) goto L_0x015e;
    L_0x0133:
        r2 = "auth";
        r4 = new com.android.volley.a;
        r4.<init>(r3);
        r0 = r19;
        com.android.volley.toolbox.C0576c.m5180a(r2, r0, r4);
        goto L_0x0004;
    L_0x0141:
        r3 = new com.android.volley.o;
        r3.<init>(r2);
        throw r3;
    L_0x0147:
        r3 = "Unexpected response code %d for %s";
        r7 = 2;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r9 = java.lang.Integer.valueOf(r4);
        r7[r8] = r9;
        r8 = 1;
        r9 = r19.m5116g();
        r7[r8] = r9;
        com.android.volley.ah.m5060c(r3, r7);
        goto L_0x011d;
    L_0x015e:
        r2 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r4 == r2) goto L_0x0166;
    L_0x0162:
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r4 != r2) goto L_0x0174;
    L_0x0166:
        r2 = "redirect";
        r4 = new com.android.volley.q;
        r4.<init>(r3);
        r0 = r19;
        com.android.volley.toolbox.C0576c.m5180a(r2, r0, r4);
        goto L_0x0004;
    L_0x0174:
        r2 = new com.android.volley.ae;
        r2.<init>(r3);
        throw r2;
    L_0x017a:
        r3 = new com.android.volley.m;
        r3.<init>(r2);
        throw r3;
    L_0x0180:
        r2 = move-exception;
        r5 = r14;
        r3 = r15;
        goto L_0x00f5;
    L_0x0185:
        r2 = move-exception;
        r5 = r11;
        r3 = r15;
        goto L_0x00f5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.c.a(com.android.volley.r):com.android.volley.n");
    }

    protected void m5184a(String str, String str2, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ah.m5056a("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(elapsedRealtime - j), str2);
    }
}
