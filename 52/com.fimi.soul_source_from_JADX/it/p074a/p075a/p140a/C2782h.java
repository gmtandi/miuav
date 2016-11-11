package it.p074a.p075a.p140a;

import it.p074a.p075a.C2777i;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/* renamed from: it.a.a.a.h */
public class C2782h extends C2777i {
    private String f14179d;
    private int f14180e;
    private String f14181f;
    private String f14182g;

    public C2782h(String str, int i) {
        this(str, i, null, null);
    }

    public C2782h(String str, int i, String str2, String str3) {
        this.f14179d = str;
        this.f14180e = i;
        this.f14181f = str2;
        this.f14182g = str3;
    }

    private int m15854a(InputStream inputStream) {
        int read = inputStream.read();
        if (read >= 0) {
            return read;
        }
        throw new IOException("SOCKS5Connector: connection closed by the proxy");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.Socket m15855a(java.lang.String r12, int r13, boolean r14) {
        /*
        r11 = this;
        r2 = 0;
        r10 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r0 = 0;
        r9 = 5;
        r1 = 1;
        r3 = r11.f14181f;
        if (r3 == 0) goto L_0x000f;
    L_0x000a:
        r3 = r11.f14182g;
        if (r3 == 0) goto L_0x000f;
    L_0x000e:
        r0 = r1;
    L_0x000f:
        if (r14 == 0) goto L_0x0056;
    L_0x0011:
        r3 = r11.f14179d;	 Catch:{ IOException -> 0x01a9, all -> 0x01a0 }
        r4 = r11.f14180e;	 Catch:{ IOException -> 0x01a9, all -> 0x01a0 }
        r4 = r11.m15836b(r3, r4);	 Catch:{ IOException -> 0x01a9, all -> 0x01a0 }
    L_0x0019:
        r3 = r4.getInputStream();	 Catch:{ IOException -> 0x01ae, all -> 0x01a5 }
        r2 = r4.getOutputStream();	 Catch:{ IOException -> 0x01b3, all -> 0x0068 }
        r5 = 5;
        r2.write(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r0 == 0) goto L_0x005f;
    L_0x0027:
        r5 = 1;
        r2.write(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r5 = 2;
        r2.write(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x002f:
        r5 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r5 == r9) goto L_0x006a;
    L_0x0035:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: invalid proxy response";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x003d:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        r3 = r4;
    L_0x0041:
        throw r0;	 Catch:{ all -> 0x0042 }
    L_0x0042:
        r0 = move-exception;
        r4 = r3;
        r3 = r2;
        r2 = r1;
    L_0x0046:
        if (r2 == 0) goto L_0x004b;
    L_0x0048:
        r2.close();	 Catch:{ Throwable -> 0x0197 }
    L_0x004b:
        if (r3 == 0) goto L_0x0050;
    L_0x004d:
        r3.close();	 Catch:{ Throwable -> 0x019a }
    L_0x0050:
        if (r4 == 0) goto L_0x0055;
    L_0x0052:
        r4.close();	 Catch:{ Throwable -> 0x019d }
    L_0x0055:
        throw r0;
    L_0x0056:
        r3 = r11.f14179d;	 Catch:{ IOException -> 0x01a9, all -> 0x01a0 }
        r4 = r11.f14180e;	 Catch:{ IOException -> 0x01a9, all -> 0x01a0 }
        r4 = r11.m15832a(r3, r4);	 Catch:{ IOException -> 0x01a9, all -> 0x01a0 }
        goto L_0x0019;
    L_0x005f:
        r5 = 1;
        r2.write(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r5 = 0;
        r2.write(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        goto L_0x002f;
    L_0x0068:
        r0 = move-exception;
        goto L_0x0046;
    L_0x006a:
        r5 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r0 == 0) goto L_0x00cd;
    L_0x0070:
        r0 = 2;
        if (r5 == r0) goto L_0x007b;
    L_0x0073:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: proxy doesn't support username/password authentication method";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x007b:
        r0 = r11.f14181f;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r5 = "UTF-8";
        r0 = r0.getBytes(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r5 = r11.f14182g;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r6 = "UTF-8";
        r5 = r5.getBytes(r6);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r6 = r0.length;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r7 = r5.length;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r6 <= r10) goto L_0x0097;
    L_0x008f:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: username too long";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0097:
        if (r7 <= r10) goto L_0x00a1;
    L_0x0099:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: password too long";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x00a1:
        r8 = 1;
        r2.write(r8);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r2.write(r6);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r2.write(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r2.write(r7);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r2.write(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r0 == r1) goto L_0x00bf;
    L_0x00b7:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: invalid proxy response";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x00bf:
        r0 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r0 == 0) goto L_0x00d7;
    L_0x00c5:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: authentication failed";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x00cd:
        if (r5 == 0) goto L_0x00d7;
    L_0x00cf:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: proxy requires authentication";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x00d7:
        r0 = 5;
        r2.write(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = 1;
        r2.write(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = 0;
        r2.write(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = 3;
        r2.write(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = "UTF-8";
        r0 = r12.getBytes(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r5 = r0.length;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r5 <= r10) goto L_0x00f8;
    L_0x00f0:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: domain name too long";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x00f8:
        r5 = r0.length;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r2.write(r5);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r2.write(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = r13 >> 8;
        r2.write(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r2.write(r13);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r0 == r9) goto L_0x0115;
    L_0x010d:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: invalid proxy response";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0115:
        r0 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        switch(r0) {
            case 0: goto L_0x0164;
            case 1: goto L_0x0124;
            case 2: goto L_0x012c;
            case 3: goto L_0x0134;
            case 4: goto L_0x013c;
            case 5: goto L_0x0144;
            case 6: goto L_0x014c;
            case 7: goto L_0x0154;
            case 8: goto L_0x015c;
            default: goto L_0x011c;
        };	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x011c:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: invalid proxy response";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0124:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: general failure";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x012c:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: connection not allowed by ruleset";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0134:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: network unreachable";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x013c:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: host unreachable";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0144:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: connection refused by destination host";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x014c:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: TTL expired";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0154:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: command not supported / protocol error";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x015c:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: address type not supported";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0164:
        r6 = 1;
        r3.skip(r6);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        if (r0 != r1) goto L_0x017a;
    L_0x016f:
        r0 = 4;
        r3.skip(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0174:
        r0 = 2;
        r3.skip(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        return r4;
    L_0x017a:
        r1 = 3;
        if (r0 != r1) goto L_0x0186;
    L_0x017d:
        r0 = r11.m15854a(r3);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r0 = (long) r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r3.skip(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        goto L_0x0174;
    L_0x0186:
        r1 = 4;
        if (r0 != r1) goto L_0x018f;
    L_0x0189:
        r0 = 16;
        r3.skip(r0);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        goto L_0x0174;
    L_0x018f:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        r1 = "SOCKS5Connector: invalid proxy response";
        r0.<init>(r1);	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
        throw r0;	 Catch:{ IOException -> 0x003d, all -> 0x0068 }
    L_0x0197:
        r1 = move-exception;
        goto L_0x004b;
    L_0x019a:
        r1 = move-exception;
        goto L_0x0050;
    L_0x019d:
        r1 = move-exception;
        goto L_0x0055;
    L_0x01a0:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        goto L_0x0046;
    L_0x01a5:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0046;
    L_0x01a9:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        goto L_0x0041;
    L_0x01ae:
        r0 = move-exception;
        r1 = r2;
        r3 = r4;
        goto L_0x0041;
    L_0x01b3:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.a.a.a.h.a(java.lang.String, int, boolean):java.net.Socket");
    }

    public Socket m15856c(String str, int i) {
        return m15855a(str, i, false);
    }

    public Socket m15857d(String str, int i) {
        return m15855a(str, i, true);
    }
}
