package com.xiaomi.smack;

import android.os.SystemClock;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2699f;
import com.xiaomi.smack.packet.C2699f.C2698b;
import com.xiaomi.stats.C2728b;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.smack.t */
public class C2711t extends C2678j {
    public Exception f13403a;
    protected Socket f13404b;
    String f13405o;
    C2705q f13406p;
    C2689o f13407q;
    private String f13408r;
    private String f13409s;
    private String f13410t;
    private XMPushService f13411u;
    private volatile long f13412v;
    private volatile long f13413w;
    private final String f13414x;
    private volatile long f13415y;
    private int f13416z;

    public C2711t(XMPushService xMPushService, C2674k c2674k) {
        super(xMPushService, c2674k);
        this.f13403a = null;
        this.f13405o = null;
        this.f13408r = null;
        this.f13409s = C2915a.f14760f;
        this.f13412v = 0;
        this.f13413w = 0;
        this.f13414x = "<pf><p>t:%1$d</p></pf>";
        this.f13415y = 0;
        this.f13411u = xMPushService;
    }

    private void m15315a(C2674k c2674k) {
        m15317a(c2674k.m15142h(), c2674k.m15141g());
    }

    private void m15316a(Exception exception) {
        if (SystemClock.elapsedRealtime() - this.f13415y >= MiStatInterface.MIN_UPLOAD_INTERVAL) {
            this.f13416z = 0;
        } else if (C2472a.m14152d(this.f13411u)) {
            this.f13416z++;
            if (this.f13416z >= 2) {
                String e = m15332e();
                C2463b.m14123a("max short conn time reached, sink down current host:" + e);
                m15318a(e, 0, exception);
                this.f13416z = 0;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m15317a(java.lang.String r13, int r14) {
        /*
        r12 = this;
        r2 = 0;
        r0 = 0;
        r12.f13403a = r0;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = "get bucket for host : ";
        r0 = r0.append(r3);
        r0 = r0.append(r13);
        r0 = r0.toString();
        r0 = com.xiaomi.channel.commonutils.logger.C2463b.m14128d(r0);
        r3 = r0.intValue();
        r0 = r12.m15330c(r13);
        r3 = java.lang.Integer.valueOf(r3);
        com.xiaomi.channel.commonutils.logger.C2463b.m14122a(r3);
        if (r0 == 0) goto L_0x0035;
    L_0x0031:
        r1 = r0.m14845c();
    L_0x0035:
        r3 = r1.isEmpty();
        if (r3 == 0) goto L_0x003e;
    L_0x003b:
        r1.add(r13);
    L_0x003e:
        r4 = 0;
        r12.f13415y = r4;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = r1.iterator();
    L_0x004b:
        r1 = r9.hasNext();
        if (r1 == 0) goto L_0x00d9;
    L_0x0051:
        r1 = r9.next();
        r1 = (java.lang.String) r1;
        r10 = java.lang.System.currentTimeMillis();
        r3 = r12.d;
        r3 = r3 + 1;
        r12.d = r3;
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3.<init>();	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r4 = "begin to connect to ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3 = r3.append(r1);	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        com.xiaomi.channel.commonutils.logger.C2463b.m14123a(r3);	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3 = r12.m15335v();	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r12.f13404b = r3;	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3 = r12.f13404b;	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r4 = 0;
        r3.bind(r4);	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3 = new java.net.InetSocketAddress;	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3.<init>(r1, r14);	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r4 = r12.f13404b;	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r5 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4.connect(r3, r5);	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r3 = r12.f13404b;	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r4 = 1;
        r3.setTcpNoDelay(r4);	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r12.f13410t = r1;	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r12.m15319y();	 Catch:{ IOException -> 0x00ec, w -> 0x0142, Throwable -> 0x0196 }
        r6 = 1;
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2 = r2 - r10;
        r12.e = r2;	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        if (r0 == 0) goto L_0x00ab;
    L_0x00a4:
        r2 = r12.e;	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r4 = 0;
        r0.m14837a(r1, r2, r4);	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
    L_0x00ab:
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r12.f13415y = r2;	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2.<init>();	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r3 = "connected to ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2 = r2.append(r1);	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r3 = " in ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r4 = r12.e;	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2 = r2.toString();	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        com.xiaomi.channel.commonutils.logger.C2463b.m14123a(r2);	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2 = r12.e;	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        com.xiaomi.stats.C2728b.m15393a(r2, r1);	 Catch:{ IOException -> 0x01b7, w -> 0x01b3, Throwable -> 0x01af, all -> 0x01aa }
        r2 = r6;
    L_0x00d9:
        r0 = com.xiaomi.network.HostManager.getInstance();
        r0.persist();
        if (r2 != 0) goto L_0x01a9;
    L_0x00e2:
        r0 = new com.xiaomi.smack.w;
        r1 = r8.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00ec:
        r6 = move-exception;
        r7 = r2;
    L_0x00ee:
        if (r0 == 0) goto L_0x00fa;
    L_0x00f0:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x01ad }
        r2 = r2 - r10;
        r4 = 0;
        r0.m14838a(r1, r2, r4, r6);	 Catch:{ all -> 0x01ad }
    L_0x00fa:
        r12.f13403a = r6;	 Catch:{ all -> 0x01ad }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ad }
        r2.<init>();	 Catch:{ all -> 0x01ad }
        r3 = "SMACK: Could not connect to:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r1);	 Catch:{ all -> 0x01ad }
        r2 = r2.toString();	 Catch:{ all -> 0x01ad }
        com.xiaomi.channel.commonutils.logger.C2463b.m14127c(r2);	 Catch:{ all -> 0x01ad }
        r2 = "SMACK: Could not connect to ";
        r2 = r8.append(r2);	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r1);	 Catch:{ all -> 0x01ad }
        r3 = " port:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r14);	 Catch:{ all -> 0x01ad }
        r3 = " ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r3 = r6.getMessage();	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r3 = "\n";
        r2.append(r3);	 Catch:{ all -> 0x01ad }
        if (r7 != 0) goto L_0x01be;
    L_0x013b:
        com.xiaomi.stats.C2728b.m15394a(r1);
        r1 = r7;
    L_0x013f:
        r2 = r1;
        goto L_0x004b;
    L_0x0142:
        r6 = move-exception;
        r7 = r2;
    L_0x0144:
        if (r0 == 0) goto L_0x0150;
    L_0x0146:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x01ad }
        r2 = r2 - r10;
        r4 = 0;
        r0.m14838a(r1, r2, r4, r6);	 Catch:{ all -> 0x01ad }
    L_0x0150:
        r12.f13403a = r6;	 Catch:{ all -> 0x01ad }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01ad }
        r2.<init>();	 Catch:{ all -> 0x01ad }
        r3 = "SMACK: Could not connect to:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r1);	 Catch:{ all -> 0x01ad }
        r2 = r2.toString();	 Catch:{ all -> 0x01ad }
        com.xiaomi.channel.commonutils.logger.C2463b.m14127c(r2);	 Catch:{ all -> 0x01ad }
        r2 = "SMACK: Could not connect to ";
        r2 = r8.append(r2);	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r1);	 Catch:{ all -> 0x01ad }
        r3 = " port:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r14);	 Catch:{ all -> 0x01ad }
        r3 = " ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r3 = r6.getMessage();	 Catch:{ all -> 0x01ad }
        r2 = r2.append(r3);	 Catch:{ all -> 0x01ad }
        r3 = "\n";
        r2.append(r3);	 Catch:{ all -> 0x01ad }
        if (r7 != 0) goto L_0x01be;
    L_0x0191:
        com.xiaomi.stats.C2728b.m15394a(r1);
        r1 = r7;
        goto L_0x013f;
    L_0x0196:
        r3 = move-exception;
    L_0x0197:
        com.xiaomi.channel.commonutils.logger.C2463b.m14125a(r3);	 Catch:{ all -> 0x01a1 }
        if (r2 != 0) goto L_0x01bc;
    L_0x019c:
        com.xiaomi.stats.C2728b.m15394a(r1);
        r1 = r2;
        goto L_0x013f;
    L_0x01a1:
        r0 = move-exception;
        r7 = r2;
    L_0x01a3:
        if (r7 != 0) goto L_0x01a8;
    L_0x01a5:
        com.xiaomi.stats.C2728b.m15394a(r1);
    L_0x01a8:
        throw r0;
    L_0x01a9:
        return;
    L_0x01aa:
        r0 = move-exception;
        r7 = r6;
        goto L_0x01a3;
    L_0x01ad:
        r0 = move-exception;
        goto L_0x01a3;
    L_0x01af:
        r2 = move-exception;
        r3 = r2;
        r2 = r6;
        goto L_0x0197;
    L_0x01b3:
        r2 = move-exception;
        r7 = r6;
        r6 = r2;
        goto L_0x0144;
    L_0x01b7:
        r2 = move-exception;
        r7 = r6;
        r6 = r2;
        goto L_0x00ee;
    L_0x01bc:
        r1 = r2;
        goto L_0x013f;
    L_0x01be:
        r1 = r7;
        goto L_0x013f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.t.a(java.lang.String, int):void");
    }

    private void m15318a(String str, long j, Exception exception) {
        Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(C2674k.m15134d());
        if (fallbacksByHost != null) {
            fallbacksByHost.m14838a(str, j, 0, exception);
            HostManager.getInstance().persist();
        }
    }

    private synchronized void m15319y() {
        m15320z();
        this.f13406p = new C2705q(this);
        this.f13407q = new C2689o(this);
        if (this.m.m15143i()) {
            m15154a(this.h.m14567c(), null);
            if (this.h.m14568d() != null) {
                m15163b(this.h.m14568d(), null);
            }
        }
        this.f13406p.m15296c();
        this.f13407q.m15210b();
    }

    private void m15320z() {
        try {
            this.i = new BufferedReader(new InputStreamReader(this.f13404b.getInputStream(), C1142e.f5201a), Opcodes.ACC_SYNTHETIC);
            this.j = new BufferedWriter(new OutputStreamWriter(this.f13404b.getOutputStream(), C1142e.f5201a));
            if (this.i != null && this.j != null) {
                m15161b();
            }
        } catch (Throwable e) {
            throw new C2723w("Error to init reader and writer", e);
        }
    }

    public String m15321a() {
        return this.k;
    }

    public void m15322a(int i, Exception exception) {
        this.f13411u.m14945a(new C2722v(this, 2, i, exception));
    }

    public synchronized void m15323a(C2667b c2667b) {
        new C2710s().m15313a(c2667b, m15321a(), this);
    }

    public void m15324a(C2694d c2694d) {
        if (this.f13406p != null) {
            this.f13406p.m15294a(c2694d);
            return;
        }
        throw new C2723w("the writer is null.");
    }

    public void m15325a(C2699f c2699f, int i, Exception exception) {
        m15328b(c2699f, i, exception);
        if (exception != null && this.f13415y != 0) {
            m15316a(exception);
        }
    }

    public synchronized void m15326a(String str, String str2) {
        C2694d c2699f = new C2699f(C2698b.unavailable);
        c2699f.m15235l(str);
        c2699f.m15239n(str2);
        if (this.f13406p != null) {
            this.f13406p.m15294a(c2699f);
        }
    }

    public void m15327a(C2694d[] c2694dArr) {
        for (C2694d a : c2694dArr) {
            m15324a(a);
        }
    }

    protected synchronized void m15328b(C2699f c2699f, int i, Exception exception) {
        if (m15177o() != 2) {
            m15151a(2, i, exception);
            this.k = C2915a.f14760f;
            if (this.f13407q != null) {
                this.f13407q.m15211c();
                this.f13407q.m15212d();
                this.f13407q = null;
            }
            if (this.f13406p != null) {
                try {
                    this.f13406p.m15295b();
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                }
                this.f13406p.m15293a();
                this.f13406p = null;
            }
            try {
                this.f13404b.close();
            } catch (Throwable th) {
            }
            if (this.i != null) {
                try {
                    this.i.close();
                } catch (Throwable th2) {
                }
                this.i = null;
            }
            if (this.j != null) {
                try {
                    this.j.close();
                } catch (Throwable th3) {
                }
                this.j = null;
            }
            this.f13412v = 0;
            this.f13413w = 0;
        }
    }

    public void m15329b(String str) {
        this.f13409s = str;
    }

    public Fallback m15330c(String str) {
        return HostManager.getInstance().getFallbacksByHost(str);
    }

    public void m15331c() {
        if (this.f13406p != null) {
            this.f13406p.m15297d();
            this.f13411u.m14946a(new C2712u(this, 13, System.currentTimeMillis()), 15000);
            return;
        }
        throw new C2723w("the packetwriter is null.");
    }

    public String m15332e() {
        return this.f13410t;
    }

    public synchronized void m15333t() {
        try {
            if (m15172j() || m15171i()) {
                C2463b.m14123a("WARNING: current xmpp has connected");
            } else {
                m15151a(0, 0, null);
                m15315a(this.m);
            }
        } catch (Throwable e) {
            throw new C2723w(e);
        }
    }

    public String m15334u() {
        String format = (this.f13413w == 0 || this.f13412v == 0) ? C2915a.f14760f : String.format("<pf><p>t:%1$d</p></pf>", new Object[]{Long.valueOf(this.f13413w - this.f13412v)});
        String c = C2728b.m15398c();
        c = c != null ? "<q>" + c + "</q>" : C2915a.f14760f;
        return String.format(this.f13409s, new Object[]{format, c});
    }

    public Socket m15335v() {
        return new Socket();
    }

    public void m15336w() {
        this.f13412v = SystemClock.uptimeMillis();
    }

    public void m15337x() {
        this.f13413w = SystemClock.uptimeMillis();
    }
}
