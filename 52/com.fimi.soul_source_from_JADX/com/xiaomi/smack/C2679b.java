package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.kenai.jbosh.C2518s;
import com.xiaomi.kenai.jbosh.C2523v.C2522a;
import com.xiaomi.kenai.jbosh.C2524w;
import com.xiaomi.kenai.jbosh.C2525x;
import com.xiaomi.kenai.jbosh.aa;
import com.xiaomi.kenai.jbosh.ag;
import com.xiaomi.kenai.jbosh.ai;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.C2678j.C2687a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2699f;
import com.xiaomi.smack.packet.C2699f.C2698b;
import com.xiaomi.smack.packet.C2702h;
import com.xiaomi.smack.packet.C2702h.C2701a;
import com.xiaomi.stats.C2728b;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.smack.b */
public class C2679b extends C2678j {
    protected String f13265a;
    protected String f13266b;
    private C2518s f13267o;
    private final C2675a f13268p;
    private boolean f13269q;
    private boolean f13270r;
    private boolean f13271s;
    private boolean f13272t;
    private boolean f13273u;
    private ExecutorService f13274v;
    private PipedWriter f13275w;
    private Thread f13276x;
    private String f13277y;
    private Object f13278z;

    /* renamed from: com.xiaomi.smack.b.a */
    class C2676a implements C2525x {
        final /* synthetic */ C2679b f13245a;

        private C2676a(C2679b c2679b) {
            this.f13245a = c2679b;
        }

        public void m15148a(C2524w c2524w) {
            if (!c2524w.m14455a()) {
                this.f13245a.m15151a(2, 0, null);
                this.f13245a.m15191a((Exception) c2524w.m14456b());
            }
            synchronized (this.f13245a.f13278z) {
                this.f13245a.f13278z.notifyAll();
            }
        }
    }

    /* renamed from: com.xiaomi.smack.b.b */
    class C2677b implements Runnable {
        final /* synthetic */ C2679b f13246a;
        private C2694d f13247b;

        public C2677b(C2679b c2679b, C2694d c2694d) {
            this.f13246a = c2679b;
            this.f13247b = c2694d;
        }

        public void run() {
            for (C2687a a : this.f13246a.f.values()) {
                a.m15203a(this.f13247b);
            }
        }
    }

    public C2679b(XMPushService xMPushService, C2675a c2675a) {
        super(xMPushService, c2675a);
        this.f13269q = false;
        this.f13270r = false;
        this.f13271s = true;
        this.f13272t = false;
        this.f13273u = false;
        this.f13265a = null;
        this.f13266b = null;
        this.f13277y = null;
        this.f13278z = new Object();
        this.f13268p = c2675a;
    }

    public void m15186a() {
        if (m15172j()) {
            C2463b.m14127c("SMACK-BOSH: Already connected to a server.");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.f13273u = false;
        this.f13266b = null;
        this.f13265a = null;
        try {
            m15151a(0, 0, null);
            URI c = this.f13268p.m15147c();
            C2463b.m14126b("SMACK-BOSH: connecting using uri:" + c.toString());
            this.f13267o = C2518s.m14402a(C2522a.m14443a(c, this.f13268p.m15139e()).m14444a(), this.n.getApplicationContext());
            this.f13274v = Executors.newSingleThreadExecutor(new C2680c(this));
            this.f13267o.m14438a(new C2676a());
            this.f13267o.m14440a(new C2686i(this));
            if (this.f13268p.m15143i()) {
                m15194b();
                if (this.f13271s) {
                    if (this.h.m14567c() != null) {
                        m15154a(this.h.m14567c(), null);
                    }
                    if (this.h.m14568d() != null) {
                        m15163b(this.h.m14568d(), null);
                    }
                }
            }
            this.f13267o.m14437a(ai.m14341d().m14331a(ag.m14315a("xm", C2537j.aq), "102").m14334a());
            synchronized (this.f13278z) {
                if (!m15172j()) {
                    try {
                        this.f13278z.wait((long) (C2706r.m15301b() * 6));
                    } catch (InterruptedException e) {
                    }
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (m15172j()) {
                C2728b.m15393a(currentTimeMillis2, this.f13268p.m15146b());
                this.f13268p.m15144a().m14837a(this.f13268p.m15146b(), currentTimeMillis2, 0);
                return;
            }
            this.f13273u = true;
            C2728b.m15394a(this.f13268p.m15146b());
            String str = "Timeout reached for the connection to " + this.f13268p.m15146b() + ":" + m15169g() + ".";
            this.f13268p.m15144a().m14838a(this.f13268p.m15146b(), currentTimeMillis2, 0, null);
            throw new C2723w(str, new C2702h(C2701a.f13372r, str));
        } catch (Throwable e2) {
            C2728b.m15394a(this.f13268p.m15146b());
            throw new C2723w("Can't connect to " + m15166d(), e2);
        }
    }

    protected void m15187a(ai aiVar) {
        if (!m15172j()) {
            throw new aa("Not connected to a server!");
        } else if (aiVar == null) {
            throw new NullPointerException("Body mustn't be null!");
        } else {
            if (this.f13266b != null) {
                aiVar = aiVar.m14345e().m14331a(ag.m14315a("xm", "sid"), this.f13266b).m14334a();
            }
            this.f13267o.m14437a(aiVar);
        }
    }

    public synchronized void m15188a(C2667b c2667b) {
        new C2710s().m15313a(c2667b, this.k, this);
    }

    public void m15189a(C2694d c2694d) {
        if (this.f13273u) {
            throw new C2723w("try send packet while the connection is done.");
        }
        try {
            m15187a(ai.m14341d().m14332a(c2694d.m15228a()).m14334a());
            m15165c(c2694d);
        } catch (Throwable e) {
            throw new C2723w(e);
        }
    }

    public void m15190a(C2699f c2699f, int i, Exception exception) {
        if (m15177o() != 2) {
            m15196b(c2699f, i, exception);
            this.g.clear();
            this.f.clear();
            this.f13272t = false;
            this.f13271s = true;
        }
    }

    protected void m15191a(Exception exception) {
        this.n.m14945a(new C2685h(this, 2, exception));
    }

    public synchronized void m15192a(String str, String str2) {
        C2694d c2699f = new C2699f(C2698b.unavailable);
        c2699f.m15235l(str);
        c2699f.m15239n(str2);
        m15189a(c2699f);
    }

    public void m15193a(C2694d[] c2694dArr) {
        int i = 0;
        if (this.f13273u) {
            throw new C2723w("try send packet while connection is done.");
        }
        int length;
        StringBuilder stringBuilder = new StringBuilder();
        for (C2694d c2694d : c2694dArr) {
            if (c2694d == null) {
                throw new NullPointerException("Packet is null.");
            }
            stringBuilder.append(c2694d.m15228a());
        }
        try {
            m15187a(ai.m14341d().m14332a(stringBuilder.toString()).m14334a());
            length = c2694dArr.length;
            while (i < length) {
                m15165c(c2694dArr[i]);
                i++;
            }
        } catch (Throwable e) {
            throw new C2723w(e);
        }
    }

    protected void m15194b() {
        this.j = new C2681d(this);
        try {
            this.f13275w = new PipedWriter();
            this.i = new PipedReader(this.f13275w);
        } catch (IOException e) {
        }
        super.m15161b();
        this.f13267o.m14440a(new C2682e(this));
        this.f13267o.m14439a(new C2683f(this));
        this.f13276x = new C2684g(this);
        this.f13276x.setDaemon(true);
        this.f13276x.start();
    }

    protected void m15195b(C2694d c2694d) {
        if (c2694d != null) {
            this.f13274v.submit(new C2677b(this, c2694d));
        }
    }

    protected void m15196b(C2699f c2699f, int i, Exception exception) {
        this.f13265a = null;
        this.f13266b = null;
        this.f13273u = true;
        this.f13269q = false;
        m15151a(2, i, exception);
        this.f13271s = false;
        this.k = C2915a.f14760f;
        try {
            this.f13267o.m14442b(ai.m14341d().m14333a("xmpp", "xm").m14334a());
            Thread.sleep(150);
        } catch (Exception e) {
        }
        if (this.f13267o != null) {
            this.f13267o.m14436a();
            this.f13267o = null;
        }
        if (this.f13275w != null) {
            try {
                this.f13275w.close();
            } catch (Throwable th) {
            }
            this.i = null;
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
        if (this.f13274v != null) {
            this.f13274v.shutdown();
        }
        for (C2559l a : m15170h()) {
            try {
                a.m14585a(i, exception);
            } catch (Throwable e2) {
                C2463b.m14124a("SMACK-BOSH: Error while shut down connection", e2);
            }
        }
        this.f13276x = null;
    }

    public void m15197c() {
        if (m15172j()) {
            C2463b.m14126b("SMACK-BOSH: scheduling empty request for ping");
            this.f13267o.m14441b();
        }
    }
}
