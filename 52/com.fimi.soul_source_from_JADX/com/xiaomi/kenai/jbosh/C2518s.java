package com.xiaomi.kenai.jbosh;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.kenai.jbosh.ai.C2500a;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.xiaomi.kenai.jbosh.s */
public final class C2518s {
    static final /* synthetic */ boolean f12737a;
    private static final int f12738b;
    private static final int f12739c;
    private static final boolean f12740d;
    private Context f12741A;
    private final Set<C2525x> f12742e;
    private final Set<C2526y> f12743f;
    private final Set<C2527z> f12744g;
    private final ReentrantLock f12745h;
    private final Condition f12746i;
    private final Condition f12747j;
    private final Condition f12748k;
    private long f12749l;
    private final C2523v f12750m;
    private final Runnable f12751n;
    private final am f12752o;
    private final ao f12753p;
    private final ScheduledExecutorService f12754q;
    private ThreadPoolExecutor f12755r;
    private ScheduledFuture<?> f12756s;
    private ah f12757t;
    private Queue<ak> f12758u;
    private SortedSet<Long> f12759v;
    private Long f12760w;
    private List<ai> f12761x;
    private volatile long f12762y;
    private volatile long f12763z;

    static {
        boolean z = true;
        f12737a = !C2518s.class.desiredAssertionStatus();
        f12738b = Integer.getInteger(C2518s.class.getName() + ".emptyRequestDelay", 100).intValue();
        f12739c = Integer.getInteger(C2518s.class.getName() + ".pauseMargin", C2799f.f14263a).intValue();
        String str = C2518s.class.getSimpleName() + ".assertionsEnabled";
        if (System.getProperty(str) != null) {
            z = Boolean.getBoolean(str);
        } else if (f12737a) {
            z = false;
        }
        f12740d = z;
    }

    private C2518s(C2523v c2523v, Context context) {
        this.f12742e = new CopyOnWriteArraySet();
        this.f12743f = new CopyOnWriteArraySet();
        this.f12744g = new CopyOnWriteArraySet();
        this.f12745h = new ReentrantLock();
        this.f12746i = this.f12745h.newCondition();
        this.f12747j = this.f12745h.newCondition();
        this.f12748k = this.f12745h.newCondition();
        this.f12749l = 0;
        this.f12751n = new C2519t(this);
        this.f12752o = new C2504e();
        this.f12753p = new ao();
        this.f12754q = Executors.newSingleThreadScheduledExecutor();
        this.f12758u = new LinkedList();
        this.f12759v = new TreeSet();
        this.f12760w = Long.valueOf(-1);
        this.f12761x = new ArrayList();
        this.f12762y = 0;
        this.f12763z = 0;
        this.f12750m = c2523v;
        this.f12741A = context.getApplicationContext();
        m14419c();
    }

    private ai m14400a(long j, ai aiVar) {
        m14432k();
        C2500a e = aiVar.m14345e();
        e.m14331a(C2517r.f12733w, this.f12750m.m14446b());
        e.m14331a(C2517r.f12710A, this.f12750m.m14448d());
        e.m14331a(C2517r.f12735y, C2515p.m14397b().toString());
        e.m14331a(C2517r.f12736z, "300");
        e.m14331a(C2517r.f12718h, Constants.VIA_TO_TYPE_QQ_GROUP);
        e.m14331a(C2517r.f12727q, Long.toString(j));
        m14404a(e);
        m14413b(e);
        e.m14331a(C2517r.f12713c, Constants.VIA_TO_TYPE_QQ_GROUP);
        e.m14331a(C2517r.f12730t, null);
        return e.m14334a();
    }

    private aq m14401a(int i, C2501b c2501b) {
        m14432k();
        return C2518s.m14410a(c2501b) ? aq.m14367a(c2501b.m14336a(C2517r.f12715e)) : (this.f12757t == null || this.f12757t.m14325c() != null) ? null : aq.m14366a(i);
    }

    public static C2518s m14402a(C2523v c2523v, Context context) {
        if (c2523v != null) {
            return new C2518s(c2523v, context);
        }
        throw new IllegalArgumentException("Client configuration may not be null");
    }

    private void m14403a(long j) {
        m14432k();
        if (j < 0) {
            throw new IllegalArgumentException("Empty request delay must be >= 0 (was: " + j + ")");
        }
        m14429h();
        if (m14422d()) {
            C2463b.m14126b("SMACK-BOSH: Scheduling empty request in " + j + LocaleUtil.MALAY);
            try {
                this.f12756s = this.f12754q.schedule(this.f12751n, j, TimeUnit.MILLISECONDS);
            } catch (Throwable e) {
                C2463b.m14124a("SMACK-BOSH: Could not schedule empty request", e);
            }
        }
    }

    private void m14404a(C2500a c2500a) {
        m14432k();
        String e = this.f12750m.m14449e();
        if (e != null) {
            c2500a.m14331a(C2517r.f12728r, e);
        }
    }

    private void m14405a(ak akVar) {
        this.f12758u.add(akVar);
        this.f12755r.execute(new C2520u(this));
    }

    private void m14406a(C2501b c2501b, int i) {
        aq a = m14401a(i, c2501b);
        if (a != null) {
            throw new aa("Terminal binding condition encountered: " + a.m14370a() + "  (" + a.m14371b() + ")");
        }
    }

    private void m14407a(C2501b c2501b, C2501b c2501b2) {
        m14432k();
        if (this.f12757t.m14328f() && c2501b2.m14336a(C2517r.f12725o) == null) {
            String a = c2501b2.m14336a(C2517r.f12713c);
            Long valueOf = a == null ? Long.valueOf(Long.parseLong(c2501b.m14336a(C2517r.f12727q))) : Long.valueOf(Long.parseLong(a));
            Iterator it = this.f12761x.iterator();
            while (it.hasNext()) {
                if (Long.valueOf(Long.parseLong(((C2501b) it.next()).m14336a(C2517r.f12727q))).compareTo(valueOf) <= 0) {
                    it.remove();
                }
            }
        }
    }

    private void m14409a(Throwable th) {
        m14433l();
        this.f12745h.lock();
        try {
            if (this.f12755r != null) {
                this.f12755r.shutdownNow();
                this.f12755r = null;
                this.f12745h.unlock();
                if (th == null) {
                    m14435n();
                } else {
                    m14415b(th);
                }
                this.f12745h.lock();
                try {
                    m14429h();
                    this.f12758u = null;
                    this.f12757t = null;
                    this.f12759v = null;
                    this.f12761x = null;
                    this.f12746i.signalAll();
                    this.f12747j.signalAll();
                    this.f12748k.signalAll();
                    this.f12752o.m14356a();
                } finally {
                    this.f12745h.unlock();
                }
            }
        } finally {
            this.f12745h.unlock();
        }
    }

    private static boolean m14410a(C2501b c2501b) {
        return "terminate".equals(c2501b.m14336a(C2517r.f12734x));
    }

    private ai m14411b(long j, ai aiVar) {
        m14432k();
        C2500a e = aiVar.m14345e();
        e.m14331a(C2517r.f12730t, this.f12757t.m14323a().toString());
        e.m14331a(C2517r.f12727q, Long.toString(j));
        return e.m14334a();
    }

    private void m14413b(C2500a c2500a) {
        m14432k();
        String c = this.f12750m.m14447c();
        if (c != null) {
            c2500a.m14331a(C2517r.f12717g, c);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m14414b(com.xiaomi.kenai.jbosh.ak r11) {
        /*
        r10 = this;
        r0 = 0;
        r8 = 0;
        r10.m14433l();
        r1 = r11.m14351b();	 Catch:{ aa -> 0x0083, InterruptedException -> 0x008d }
        r2 = r1.m14352b();	 Catch:{ aa -> 0x0083, InterruptedException -> 0x008d }
        r3 = r1.m14353c();	 Catch:{ aa -> 0x0083, InterruptedException -> 0x008d }
        r4 = r10.f12745h;
        r4.lock();
        r4 = r1.m14354d();	 Catch:{ InterruptedException -> 0x010d }
        r6 = r10.f12762y;	 Catch:{ InterruptedException -> 0x010d }
        r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r1 != 0) goto L_0x0025;
    L_0x0021:
        r6 = 0;
        r10.f12762y = r6;	 Catch:{ InterruptedException -> 0x010d }
    L_0x0025:
        r6 = r10.f12749l;	 Catch:{ InterruptedException -> 0x010d }
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 > 0) goto L_0x0097;
    L_0x002b:
        r1 = r10.f12748k;	 Catch:{ InterruptedException -> 0x010d }
        r1.signalAll();	 Catch:{ InterruptedException -> 0x010d }
    L_0x0030:
        r4 = r10.f12749l;	 Catch:{ InterruptedException -> 0x010d }
        r6 = 1;
        r4 = r4 + r6;
        r10.f12749l = r4;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r10.f12745h;
        r1.unlock();
        r10.m14427g(r2);
        r4 = r11.m14349a();
        r1 = r10.f12745h;
        r1.lock();
        r1 = r10.m14422d();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        if (r1 != 0) goto L_0x012d;
    L_0x004e:
        r0 = r10.f12745h;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0.unlock();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = r10.f12745h;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x0126;
    L_0x005b:
        r0 = r10.f12758u;	 Catch:{ all -> 0x011f }
        if (r0 == 0) goto L_0x0078;
    L_0x005f:
        r0 = r10.f12758u;	 Catch:{ all -> 0x011f }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x011f }
        if (r0 == 0) goto L_0x0078;
    L_0x0067:
        r0 = r10.m14428g();	 Catch:{ all -> 0x011f }
        if (r0 != 0) goto L_0x0078;
    L_0x006d:
        r0 = r10.m14417c(r4);	 Catch:{ all -> 0x011f }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x0078;
    L_0x0075:
        r10.m14403a(r0);	 Catch:{ all -> 0x011f }
    L_0x0078:
        r0 = r10.f12747j;	 Catch:{ all -> 0x011f }
        r0.signalAll();	 Catch:{ all -> 0x011f }
        r0 = r10.f12745h;
        r0.unlock();
    L_0x0082:
        return;
    L_0x0083:
        r0 = move-exception;
        r1 = "SMACK-BOSH: Could not obtain response";
        com.xiaomi.channel.commonutils.logger.C2463b.m14124a(r1, r0);
        r10.m14409a(r0);
        goto L_0x0082;
    L_0x008d:
        r0 = move-exception;
        r1 = "Interrupted";
        com.xiaomi.channel.commonutils.logger.C2463b.m14124a(r1, r0);
        r10.m14409a(r0);
        goto L_0x0082;
    L_0x0097:
        r1 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x010d }
        r1.<init>();	 Catch:{ InterruptedException -> 0x010d }
        r6 = "SMACK-BOSH: responded rid(";
        r1 = r1.append(r6);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r4 = ") is not expected (";
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r4 = r10.f12749l;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r4 = "), wait";
        r1 = r1.append(r4);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.toString();	 Catch:{ InterruptedException -> 0x010d }
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r1);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r10.f12748k;	 Catch:{ InterruptedException -> 0x010d }
        r4 = 30;
        r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.await(r4, r6);	 Catch:{ InterruptedException -> 0x010d }
        if (r1 != 0) goto L_0x0030;
    L_0x00cb:
        r0 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x010d }
        r0.<init>();	 Catch:{ InterruptedException -> 0x010d }
        r1 = "SMACK-BOSH: wait for ";
        r0 = r0.append(r1);	 Catch:{ InterruptedException -> 0x010d }
        r2 = r10.f12749l;	 Catch:{ InterruptedException -> 0x010d }
        r0 = r0.append(r2);	 Catch:{ InterruptedException -> 0x010d }
        r1 = " timeout, terminate!";
        r0 = r0.append(r1);	 Catch:{ InterruptedException -> 0x010d }
        r0 = r0.toString();	 Catch:{ InterruptedException -> 0x010d }
        com.xiaomi.channel.commonutils.logger.C2463b.m14127c(r0);	 Catch:{ InterruptedException -> 0x010d }
        r0 = new com.xiaomi.kenai.jbosh.aa;	 Catch:{ InterruptedException -> 0x010d }
        r1 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x010d }
        r1.<init>();	 Catch:{ InterruptedException -> 0x010d }
        r2 = "wait timeout for rid";
        r1 = r1.append(r2);	 Catch:{ InterruptedException -> 0x010d }
        r2 = r10.f12749l;	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.append(r2);	 Catch:{ InterruptedException -> 0x010d }
        r1 = r1.toString();	 Catch:{ InterruptedException -> 0x010d }
        r0.<init>(r1);	 Catch:{ InterruptedException -> 0x010d }
        r10.m14409a(r0);	 Catch:{ InterruptedException -> 0x010d }
        r0 = r10.f12745h;
        r0.unlock();
        goto L_0x0082;
    L_0x010d:
        r0 = move-exception;
        r10.m14409a(r0);	 Catch:{ all -> 0x0118 }
        r0 = r10.f12745h;
        r0.unlock();
        goto L_0x0082;
    L_0x0118:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1.unlock();
        throw r0;
    L_0x011f:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1.unlock();
        throw r0;
    L_0x0126:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r0);
        goto L_0x0082;
    L_0x012d:
        r1 = r10.f12757t;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        if (r1 != 0) goto L_0x013a;
    L_0x0131:
        r1 = com.xiaomi.kenai.jbosh.ah.m14321a(r4, r2);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r10.f12757t = r1;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r10.m14434m();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
    L_0x013a:
        r5 = r10.f12757t;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r10.m14406a(r2, r3);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r1 = com.xiaomi.kenai.jbosh.C2518s.m14410a(r2);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        if (r1 == 0) goto L_0x018d;
    L_0x0145:
        r0 = r10.f12745h;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0.unlock();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = 0;
        r10.m14409a(r0);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = r10.f12745h;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x0186;
    L_0x0156:
        r0 = r10.f12758u;	 Catch:{ all -> 0x017f }
        if (r0 == 0) goto L_0x0173;
    L_0x015a:
        r0 = r10.f12758u;	 Catch:{ all -> 0x017f }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x017f }
        if (r0 == 0) goto L_0x0173;
    L_0x0162:
        r0 = r10.m14428g();	 Catch:{ all -> 0x017f }
        if (r0 != 0) goto L_0x0173;
    L_0x0168:
        r0 = r10.m14417c(r4);	 Catch:{ all -> 0x017f }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x0173;
    L_0x0170:
        r10.m14403a(r0);	 Catch:{ all -> 0x017f }
    L_0x0173:
        r0 = r10.f12747j;	 Catch:{ all -> 0x017f }
        r0.signalAll();	 Catch:{ all -> 0x017f }
        r0 = r10.f12745h;
        r0.unlock();
        goto L_0x0082;
    L_0x017f:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1.unlock();
        throw r0;
    L_0x0186:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r0);
        goto L_0x0082;
    L_0x018d:
        r1 = com.xiaomi.kenai.jbosh.C2518s.m14416b(r2);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        if (r1 == 0) goto L_0x02aa;
    L_0x0193:
        if (r0 != 0) goto L_0x032d;
    L_0x0195:
        r1 = new java.util.ArrayList;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = r10.f12758u;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = r0.size();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r1.<init>(r0);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
    L_0x01a0:
        r0 = r10.f12758u;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r2 = r0.iterator();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
    L_0x01a6:
        r0 = r2.hasNext();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        if (r0 == 0) goto L_0x01fe;
    L_0x01ac:
        r0 = r2.next();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = (com.xiaomi.kenai.jbosh.ak) r0;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r3 = new com.xiaomi.kenai.jbosh.ak;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = r0.m14349a();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r3.<init>(r0);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r1.add(r3);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        goto L_0x01a6;
    L_0x01bf:
        r0 = move-exception;
        r1 = "SMACK-BOSH: Could not process response";
        com.xiaomi.channel.commonutils.logger.C2463b.m14124a(r1, r0);	 Catch:{ all -> 0x02c5 }
        r1 = r10.f12745h;	 Catch:{ all -> 0x02c5 }
        r1.unlock();	 Catch:{ all -> 0x02c5 }
        r10.m14409a(r0);	 Catch:{ all -> 0x02c5 }
        r0 = r10.f12745h;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x030b;
    L_0x01d5:
        r0 = r10.f12758u;	 Catch:{ all -> 0x0304 }
        if (r0 == 0) goto L_0x01f2;
    L_0x01d9:
        r0 = r10.f12758u;	 Catch:{ all -> 0x0304 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0304 }
        if (r0 == 0) goto L_0x01f2;
    L_0x01e1:
        r0 = r10.m14428g();	 Catch:{ all -> 0x0304 }
        if (r0 != 0) goto L_0x01f2;
    L_0x01e7:
        r0 = r10.m14417c(r4);	 Catch:{ all -> 0x0304 }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x01f2;
    L_0x01ef:
        r10.m14403a(r0);	 Catch:{ all -> 0x0304 }
    L_0x01f2:
        r0 = r10.f12747j;	 Catch:{ all -> 0x0304 }
        r0.signalAll();	 Catch:{ all -> 0x0304 }
        r0 = r10.f12745h;
        r0.unlock();
        goto L_0x0082;
    L_0x01fe:
        r2 = r1.iterator();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
    L_0x0202:
        r0 = r2.hasNext();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        if (r0 == 0) goto L_0x0251;
    L_0x0208:
        r0 = r2.next();	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0 = (com.xiaomi.kenai.jbosh.ak) r0;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r10.m14405a(r0);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        goto L_0x0202;
    L_0x0212:
        r0 = move-exception;
        r1 = "SMACK-BOSH: Could not process response";
        com.xiaomi.channel.commonutils.logger.C2463b.m14124a(r1, r0);	 Catch:{ all -> 0x02c5 }
        r1 = r10.f12745h;	 Catch:{ all -> 0x02c5 }
        r1.unlock();	 Catch:{ all -> 0x02c5 }
        r10.m14409a(r0);	 Catch:{ all -> 0x02c5 }
        r0 = r10.f12745h;
        r0 = r0.isHeldByCurrentThread();
        if (r0 == 0) goto L_0x0319;
    L_0x0228:
        r0 = r10.f12758u;	 Catch:{ all -> 0x0312 }
        if (r0 == 0) goto L_0x0245;
    L_0x022c:
        r0 = r10.f12758u;	 Catch:{ all -> 0x0312 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0312 }
        if (r0 == 0) goto L_0x0245;
    L_0x0234:
        r0 = r10.m14428g();	 Catch:{ all -> 0x0312 }
        if (r0 != 0) goto L_0x0245;
    L_0x023a:
        r0 = r10.m14417c(r4);	 Catch:{ all -> 0x0312 }
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 <= 0) goto L_0x0245;
    L_0x0242:
        r10.m14403a(r0);	 Catch:{ all -> 0x0312 }
    L_0x0245:
        r0 = r10.f12747j;	 Catch:{ all -> 0x0312 }
        r0.signalAll();	 Catch:{ all -> 0x0312 }
        r0 = r10.f12745h;
        r0.unlock();
        goto L_0x0082;
    L_0x0251:
        r0 = r1;
    L_0x0252:
        r1 = r10.f12745h;
        r1 = r1.isHeldByCurrentThread();
        if (r1 == 0) goto L_0x02fd;
    L_0x025a:
        r1 = r10.f12758u;	 Catch:{ all -> 0x02f6 }
        if (r1 == 0) goto L_0x0277;
    L_0x025e:
        r1 = r10.f12758u;	 Catch:{ all -> 0x02f6 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x02f6 }
        if (r1 == 0) goto L_0x0277;
    L_0x0266:
        r1 = r10.m14428g();	 Catch:{ all -> 0x02f6 }
        if (r1 != 0) goto L_0x0277;
    L_0x026c:
        r2 = r10.m14417c(r4);	 Catch:{ all -> 0x02f6 }
        r1 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r1 <= 0) goto L_0x0277;
    L_0x0274:
        r10.m14403a(r2);	 Catch:{ all -> 0x02f6 }
    L_0x0277:
        r1 = r10.f12747j;	 Catch:{ all -> 0x02f6 }
        r1.signalAll();	 Catch:{ all -> 0x02f6 }
        r1 = r10.f12745h;
        r1.unlock();
    L_0x0281:
        if (r0 == 0) goto L_0x0082;
    L_0x0283:
        r1 = r0.iterator();
    L_0x0287:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0082;
    L_0x028d:
        r0 = r1.next();
        r0 = (com.xiaomi.kenai.jbosh.ak) r0;
        r2 = r10.f12752o;
        r3 = r0.m14349a();
        r4 = r10.f12741A;
        r2 = r2.m14355a(r5, r3, r4);
        r0.m14350a(r2);
        r0 = r0.m14349a();
        r10.m14426f(r0);
        goto L_0x0287;
    L_0x02aa:
        r10.m14407a(r4, r2);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r10.m14420d(r4);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r1 = r10.m14423e(r2);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        if (r1 == 0) goto L_0x0252;
    L_0x02b6:
        if (r0 != 0) goto L_0x0252;
    L_0x02b8:
        r0 = new java.util.ArrayList;	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r2 = 1;
        r0.<init>(r2);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r0.add(r1);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        r10.m14405a(r1);	 Catch:{ aa -> 0x01bf, InterruptedException -> 0x0212 }
        goto L_0x0252;
    L_0x02c5:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1 = r1.isHeldByCurrentThread();
        if (r1 == 0) goto L_0x0327;
    L_0x02ce:
        r1 = r10.f12758u;	 Catch:{ all -> 0x0320 }
        if (r1 == 0) goto L_0x02eb;
    L_0x02d2:
        r1 = r10.f12758u;	 Catch:{ all -> 0x0320 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0320 }
        if (r1 == 0) goto L_0x02eb;
    L_0x02da:
        r1 = r10.m14428g();	 Catch:{ all -> 0x0320 }
        if (r1 != 0) goto L_0x02eb;
    L_0x02e0:
        r2 = r10.m14417c(r4);	 Catch:{ all -> 0x0320 }
        r1 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r1 <= 0) goto L_0x02eb;
    L_0x02e8:
        r10.m14403a(r2);	 Catch:{ all -> 0x0320 }
    L_0x02eb:
        r1 = r10.f12747j;	 Catch:{ all -> 0x0320 }
        r1.signalAll();	 Catch:{ all -> 0x0320 }
        r1 = r10.f12745h;
        r1.unlock();
    L_0x02f5:
        throw r0;
    L_0x02f6:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1.unlock();
        throw r0;
    L_0x02fd:
        r1 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r1);
        goto L_0x0281;
    L_0x0304:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1.unlock();
        throw r0;
    L_0x030b:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r0);
        goto L_0x0082;
    L_0x0312:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1.unlock();
        throw r0;
    L_0x0319:
        r0 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r0);
        goto L_0x0082;
    L_0x0320:
        r0 = move-exception;
        r1 = r10.f12745h;
        r1.unlock();
        throw r0;
    L_0x0327:
        r1 = "SMACK-BOSH: lock is not held by this thread, don't schedule empty request";
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r1);
        goto L_0x02f5;
    L_0x032d:
        r1 = r0;
        goto L_0x01a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.kenai.jbosh.s.b(com.xiaomi.kenai.jbosh.ak):void");
    }

    private void m14415b(Throwable th) {
        m14433l();
        C2524w c2524w = null;
        for (C2525x c2525x : this.f12742e) {
            if (c2524w == null) {
                c2524w = C2524w.m14453a(this, this.f12761x, th);
            }
            try {
                c2525x.m14457a(c2524w);
            } catch (Throwable e) {
                C2463b.m14124a("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    private static boolean m14416b(C2501b c2501b) {
        return XiaomiOAuthConstants.EXTRA_ERROR_CODE_2.equals(c2501b.m14336a(C2517r.f12734x));
    }

    private long m14417c(C2501b c2501b) {
        m14432k();
        if (!(this.f12757t == null || this.f12757t.m14327e() == null)) {
            try {
                C2511l a = C2511l.m14390a(c2501b.m14336a(C2517r.f12723m));
                if (a != null) {
                    long c = (long) (a.m14391c() - f12739c);
                    return c < 0 ? (long) f12738b : c;
                }
            } catch (Throwable e) {
                C2463b.m14124a("SMACK-BOSH: Could not extract", e);
            }
        }
        return m14430i();
    }

    private void m14419c() {
        m14433l();
        this.f12745h.lock();
        try {
            this.f12752o.m14357a(this.f12750m);
            this.f12755r = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        } finally {
            this.f12745h.unlock();
        }
    }

    private void m14420d(C2501b c2501b) {
        m14432k();
        Long valueOf = Long.valueOf(Long.parseLong(c2501b.m14336a(C2517r.f12727q)));
        if (this.f12760w.equals(Long.valueOf(-1))) {
            this.f12760w = valueOf;
            return;
        }
        this.f12759v.add(valueOf);
        valueOf = Long.valueOf(this.f12760w.longValue() + 1);
        while (!this.f12759v.isEmpty() && valueOf.equals(this.f12759v.first())) {
            this.f12760w = valueOf;
            this.f12759v.remove(valueOf);
            valueOf = Long.valueOf(valueOf.longValue() + 1);
        }
    }

    private boolean m14422d() {
        m14432k();
        return this.f12755r != null;
    }

    private ak m14423e(C2501b c2501b) {
        C2501b c2501b2 = null;
        m14432k();
        String a = c2501b.m14336a(C2517r.f12725o);
        if (a == null) {
            return null;
        }
        Long valueOf = Long.valueOf(Long.parseLong(a));
        Long.valueOf(Long.parseLong(c2501b.m14336a(C2517r.f12732v)));
        Iterator it = this.f12761x.iterator();
        while (it.hasNext() && c2501b2 == null) {
            C2501b c2501b3 = (C2501b) it.next();
            if (!valueOf.equals(Long.valueOf(Long.parseLong(c2501b3.m14336a(C2517r.f12727q))))) {
                c2501b3 = c2501b2;
            }
            c2501b2 = c2501b3;
        }
        if (c2501b2 == null) {
            throw new aa("Report of missing message with RID '" + a + "' but local copy of that request was not found");
        }
        ak akVar = new ak(c2501b2);
        m14405a(akVar);
        this.f12746i.signalAll();
        return akVar;
    }

    private void m14424e() {
        ak f = m14425f();
        if (f != null) {
            this.f12745h.lock();
            try {
                long longValue = Long.valueOf(f.m14349a().m14336a(C2517r.f12727q)).longValue();
                if (this.f12749l == 0) {
                    this.f12749l = longValue;
                }
                this.f12745h.unlock();
                m14414b(f);
            } catch (Throwable th) {
                this.f12745h.unlock();
            }
        }
    }

    private ak m14425f() {
        ak akVar;
        m14433l();
        this.f12745h.lock();
        do {
            if (this.f12758u == null) {
                this.f12745h.unlock();
                return null;
            }
            try {
                akVar = (ak) this.f12758u.poll();
                if (akVar == null) {
                    this.f12746i.await();
                    continue;
                }
            } catch (Throwable e) {
                C2463b.m14124a("Interrupted", e);
                continue;
            } catch (Throwable th) {
                this.f12745h.unlock();
            }
        } while (akVar == null);
        this.f12745h.unlock();
        return akVar;
    }

    private void m14426f(C2501b c2501b) {
        m14433l();
        ab abVar = null;
        for (C2526y c2526y : this.f12743f) {
            if (abVar == null) {
                abVar = ab.m14305a(this, c2501b);
            }
            try {
                c2526y.m14458a(abVar);
            } catch (Throwable e) {
                C2463b.m14124a("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    private void m14427g(C2501b c2501b) {
        m14433l();
        ab abVar = null;
        for (C2527z c2527z : this.f12744g) {
            if (abVar == null) {
                abVar = ab.m14306b(this, c2501b);
            }
            try {
                c2527z.m14459a(abVar);
            } catch (Throwable e) {
                C2463b.m14124a("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    private boolean m14428g() {
        return !(this.f12756s == null || this.f12756s.isDone()) || this.f12762y > 0;
    }

    private void m14429h() {
        m14432k();
        if (this.f12756s != null) {
            this.f12756s.cancel(false);
            this.f12756s = null;
        }
    }

    private long m14430i() {
        m14432k();
        C2512m d = this.f12757t.m14326d();
        long j = (long) f12738b;
        if (d != null) {
            long c = (long) d.m14393c();
            if (c > j) {
                return c;
            }
        }
        return j;
    }

    private void m14431j() {
        m14433l();
        try {
            m14437a(ai.m14341d().m14334a());
        } catch (Throwable e) {
            m14409a(e);
        }
    }

    private void m14432k() {
        if (f12740d && !this.f12745h.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is not held by current thread");
        }
    }

    private void m14433l() {
        if (f12740d && this.f12745h.isHeldByCurrentThread()) {
            throw new AssertionError("Lock is held by current thread");
        }
    }

    private void m14434m() {
        boolean isHeldByCurrentThread = this.f12745h.isHeldByCurrentThread();
        if (isHeldByCurrentThread) {
            this.f12745h.unlock();
        }
        try {
            C2524w c2524w = null;
            for (C2525x c2525x : this.f12742e) {
                if (c2524w == null) {
                    c2524w = C2524w.m14452a(this);
                }
                c2525x.m14457a(c2524w);
            }
            if (isHeldByCurrentThread) {
                this.f12745h.lock();
            }
        } catch (Throwable e) {
            C2463b.m14124a("SMACK-BOSH:Unhandled Exception", e);
        } catch (Throwable th) {
            if (isHeldByCurrentThread) {
                this.f12745h.lock();
            }
        }
    }

    private void m14435n() {
        m14433l();
        C2524w c2524w = null;
        for (C2525x c2525x : this.f12742e) {
            if (c2524w == null) {
                c2524w = C2524w.m14454b(this);
            }
            try {
                c2525x.m14457a(c2524w);
            } catch (Throwable e) {
                C2463b.m14124a("SMACK-BOSH:Unhandled Exception", e);
            }
        }
    }

    public void m14436a() {
        m14409a(new aa("Session explicitly closed by caller"));
    }

    public void m14437a(ai aiVar) {
        m14433l();
        if (aiVar == null) {
            throw new IllegalArgumentException("Message body may not be null");
        }
        this.f12745h.lock();
        if (m14422d() || C2518s.m14410a((C2501b) aiVar)) {
            ak akVar;
            try {
                C2501b a;
                long a2 = this.f12753p.m14362a();
                if (TextUtils.isEmpty(aiVar.m14346f())) {
                    this.f12762y = a2;
                }
                ah ahVar = this.f12757t;
                if (ahVar == null && this.f12758u.isEmpty()) {
                    a = m14400a(a2, aiVar);
                } else {
                    a = m14411b(a2, aiVar);
                    if (this.f12757t.m14328f()) {
                        this.f12761x.add(a);
                    }
                }
                akVar = new ak(a);
                m14405a(akVar);
                this.f12746i.signalAll();
                m14429h();
                a = akVar.m14349a();
                akVar.m14350a(this.f12752o.m14355a(ahVar, a, this.f12741A));
                m14426f(a);
            } finally {
                akVar = this.f12745h;
                akVar.unlock();
            }
        } else {
            throw new aa("Cannot send message when session is closed");
        }
    }

    public void m14438a(C2525x c2525x) {
        if (c2525x == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.f12742e.add(c2525x);
    }

    public void m14439a(C2526y c2526y) {
        if (c2526y == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.f12743f.add(c2526y);
    }

    public void m14440a(C2527z c2527z) {
        if (c2527z == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.f12744g.add(c2527z);
    }

    public void m14441b() {
        if (this.f12755r != null) {
            if (System.currentTimeMillis() - this.f12763z > 30000 && this.f12755r.getActiveCount() > 1) {
                m14409a(new aa("SMACK-BOSH: request timeout happened, reset connection"));
            } else if (this.f12755r.getActiveCount() <= 0 || m14428g()) {
                this.f12745h.lock();
                try {
                    m14403a(0);
                } finally {
                    this.f12745h.unlock();
                }
            }
        }
    }

    public void m14442b(ai aiVar) {
        if (aiVar == null) {
            throw new IllegalArgumentException("Message body may not be null");
        }
        C2500a e = aiVar.m14345e();
        e.m14331a(C2517r.f12734x, "terminate");
        m14437a(e.m14334a());
    }
}
