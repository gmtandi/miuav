package it.p074a.p075a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: it.a.a.g */
public class C2800g {
    private ArrayList f14289a;
    private Socket f14290b;
    private String f14291c;
    private C2809v f14292d;
    private C2810w f14293e;

    public C2800g(Socket socket, String str) {
        this.f14289a = new ArrayList();
        this.f14290b = null;
        this.f14291c = null;
        this.f14292d = null;
        this.f14293e = null;
        this.f14290b = socket;
        this.f14291c = str;
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        this.f14292d = new C2809v(inputStream, str);
        this.f14293e = new C2810w(outputStream, str);
    }

    private String m15975d() {
        String a = this.f14292d.m16000a();
        if (a == null) {
            throw new IOException("FTPConnection closed");
        }
        Iterator it = this.f14289a.iterator();
        while (it.hasNext()) {
            ((C2801h) it.next()).m15985b(a);
        }
        return a;
    }

    public void m15976a() {
        try {
            this.f14290b.close();
        } catch (Exception e) {
        }
    }

    public void m15977a(C2801h c2801h) {
        this.f14289a.add(c2801h);
    }

    public void m15978a(String str) {
        this.f14293e.m16003b(str);
        Iterator it = this.f14289a.iterator();
        while (it.hasNext()) {
            ((C2801h) it.next()).m15984a(str);
        }
    }

    public void m15979a(SSLSocketFactory sSLSocketFactory) {
        this.f14290b = sSLSocketFactory.createSocket(this.f14290b, this.f14290b.getInetAddress().getHostName(), this.f14290b.getPort(), true);
        InputStream inputStream = this.f14290b.getInputStream();
        OutputStream outputStream = this.f14290b.getOutputStream();
        this.f14292d = new C2809v(inputStream, this.f14291c);
        this.f14293e = new C2810w(outputStream, this.f14291c);
    }

    public void m15980b(C2801h c2801h) {
        this.f14289a.remove(c2801h);
    }

    public void m15981b(String str) {
        this.f14291c = str;
        this.f14292d.m16001a(str);
        this.f14293e.m16002a(str);
    }

    public C2801h[] m15982b() {
        int size = this.f14289a.size();
        C2801h[] c2801hArr = new C2801h[size];
        for (int i = 0; i < size; i++) {
            c2801hArr[i] = (C2801h) this.f14289a.get(i);
        }
        return c2801hArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public it.p074a.p075a.C2808t m15983c() {
        /*
        r8 = this;
        r1 = 0;
        r7 = 3;
        r5 = new java.util.ArrayList;
        r5.<init>();
        r0 = r1;
    L_0x0008:
        r2 = r8.m15975d();
        r3 = r2.trim();
        r3 = r3.length();
        if (r3 == 0) goto L_0x0008;
    L_0x0016:
        r3 = "\n";
        r3 = r2.startsWith(r3);
        if (r3 == 0) goto L_0x009d;
    L_0x001e:
        r3 = 1;
        r2 = r2.substring(r3);
        r3 = r2;
    L_0x0024:
        r6 = r3.length();
        if (r0 != 0) goto L_0x0032;
    L_0x002a:
        if (r6 >= r7) goto L_0x0032;
    L_0x002c:
        r0 = new it.a.a.p;
        r0.<init>();
        throw r0;
    L_0x0032:
        r2 = 0;
        r4 = 3;
        r2 = r3.substring(r2, r4);	 Catch:{ Exception -> 0x0048 }
        r4 = java.lang.Integer.parseInt(r2);	 Catch:{ Exception -> 0x0048 }
    L_0x003c:
        if (r0 == 0) goto L_0x0053;
    L_0x003e:
        if (r4 == 0) goto L_0x0053;
    L_0x0040:
        if (r4 == r0) goto L_0x0053;
    L_0x0042:
        r0 = new it.a.a.p;
        r0.<init>();
        throw r0;
    L_0x0048:
        r2 = move-exception;
        if (r0 != 0) goto L_0x0051;
    L_0x004b:
        r0 = new it.a.a.p;
        r0.<init>();
        throw r0;
    L_0x0051:
        r4 = r1;
        goto L_0x003c;
    L_0x0053:
        if (r0 != 0) goto L_0x009b;
    L_0x0055:
        r2 = r4;
    L_0x0056:
        if (r4 <= 0) goto L_0x0091;
    L_0x0058:
        if (r6 <= r7) goto L_0x0089;
    L_0x005a:
        r0 = r3.charAt(r7);
        r4 = 4;
        r3 = r3.substring(r4, r6);
        r5.add(r3);
        r3 = 32;
        if (r0 != r3) goto L_0x007d;
    L_0x006a:
        r3 = r5.size();
        r4 = new java.lang.String[r3];
    L_0x0070:
        if (r1 >= r3) goto L_0x0095;
    L_0x0072:
        r0 = r5.get(r1);
        r0 = (java.lang.String) r0;
        r4[r1] = r0;
        r1 = r1 + 1;
        goto L_0x0070;
    L_0x007d:
        r3 = 45;
        if (r0 != r3) goto L_0x0083;
    L_0x0081:
        r0 = r2;
        goto L_0x0008;
    L_0x0083:
        r0 = new it.a.a.p;
        r0.<init>();
        throw r0;
    L_0x0089:
        if (r6 == r7) goto L_0x006a;
    L_0x008b:
        r5.add(r3);
    L_0x008e:
        r0 = r2;
        goto L_0x0008;
    L_0x0091:
        r5.add(r3);
        goto L_0x008e;
    L_0x0095:
        r0 = new it.a.a.t;
        r0.<init>(r2, r4);
        return r0;
    L_0x009b:
        r2 = r0;
        goto L_0x0056;
    L_0x009d:
        r3 = r2;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.a.a.g.c():it.a.a.t");
    }
}
