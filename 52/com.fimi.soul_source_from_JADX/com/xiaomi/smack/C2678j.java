package com.xiaomi.smack;

import android.util.Pair;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.measite.smack.C2552a;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.C2671x;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.debugger.C2551a;
import com.xiaomi.smack.filter.C2642a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2699f;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.smack.j */
public abstract class C2678j {
    private static final AtomicInteger f13248a;
    public static boolean f13249c;
    private LinkedList<Pair<Integer, Long>> f13250b;
    protected int f13251d;
    protected long f13252e;
    protected final Map<C2557n, C2687a> f13253f;
    protected final Map<C2557n, C2687a> f13254g;
    protected C2551a f13255h;
    protected Reader f13256i;
    protected Writer f13257j;
    protected String f13258k;
    protected final int f13259l;
    protected C2674k f13260m;
    protected XMPushService f13261n;
    private final Collection<C2559l> f13262o;
    private int f13263p;
    private long f13264q;

    /* renamed from: com.xiaomi.smack.j.a */
    public class C2687a {
        private C2557n f13289a;
        private C2642a f13290b;

        public C2687a(C2557n c2557n, C2642a c2642a) {
            this.f13289a = c2557n;
            this.f13290b = c2642a;
        }

        public void m15203a(C2694d c2694d) {
            if (this.f13290b == null || this.f13290b.m14981a(c2694d)) {
                this.f13289a.m14582a(c2694d);
            }
        }
    }

    static {
        f13248a = new AtomicInteger(0);
        f13249c = false;
        try {
            f13249c = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception e) {
        }
        C2706r.m15299a();
    }

    protected C2678j(XMPushService xMPushService, C2674k c2674k) {
        this.f13251d = 0;
        this.f13252e = -1;
        this.f13250b = new LinkedList();
        this.f13262o = new CopyOnWriteArrayList();
        this.f13253f = new ConcurrentHashMap();
        this.f13254g = new ConcurrentHashMap();
        this.f13255h = null;
        this.f13258k = C2915a.f14760f;
        this.f13263p = 2;
        this.f13259l = f13248a.getAndIncrement();
        this.f13264q = 0;
        this.f13260m = c2674k;
        this.f13261n = xMPushService;
    }

    private String m15149a(int i) {
        return i == 1 ? "connected" : i == 0 ? "connecting" : i == 2 ? "disconnected" : FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
    }

    private void m15150b(int i) {
        synchronized (this.f13250b) {
            if (i == 1) {
                this.f13250b.clear();
            } else {
                this.f13250b.add(new Pair(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())));
                if (this.f13250b.size() > 6) {
                    this.f13250b.remove(0);
                }
            }
        }
    }

    public void m15151a(int i, int i2, Exception exception) {
        if (i != this.f13263p) {
            C2463b.m14123a(String.format("update the connection status. %1$s -> %2$s : %3$s ", new Object[]{m15149a(this.f13263p), m15149a(i), C2671x.m15122a(i2)}));
        }
        if (C2472a.m14152d(this.f13261n)) {
            m15150b(i);
        }
        if (i == 1) {
            this.f13261n.m14943a(10);
            if (this.f13263p != 0) {
                C2463b.m14123a("try set connected while not connecting.");
            }
            this.f13263p = i;
            for (C2559l a : this.f13262o) {
                a.m14584a();
            }
        } else if (i == 0) {
            this.f13261n.m14967i();
            if (this.f13263p != 2) {
                C2463b.m14123a("try set connecting while not disconnected.");
            }
            this.f13263p = i;
            for (C2559l a2 : this.f13262o) {
                a2.m14587b();
            }
        } else if (i == 2) {
            this.f13261n.m14943a(10);
            if (this.f13263p == 0) {
                for (C2559l a22 : this.f13262o) {
                    a22.m14586a(exception == null ? new CancellationException("disconnect while connecting") : exception);
                }
            } else if (this.f13263p == 1) {
                for (C2559l a222 : this.f13262o) {
                    a222.m14585a(i2, exception);
                }
            }
            this.f13263p = i;
        }
    }

    public abstract void m15152a(C2667b c2667b);

    public void m15153a(C2559l c2559l) {
        if (c2559l != null && !this.f13262o.contains(c2559l)) {
            this.f13262o.add(c2559l);
        }
    }

    public void m15154a(C2557n c2557n, C2642a c2642a) {
        if (c2557n == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f13253f.put(c2557n, new C2687a(c2557n, c2642a));
    }

    public abstract void m15155a(C2694d c2694d);

    public abstract void m15156a(C2699f c2699f, int i, Exception exception);

    public void m15157a(String str) {
        this.f13258k = str;
        m15151a(1, 0, null);
    }

    public abstract void m15158a(String str, String str2);

    public abstract void m15159a(C2694d[] c2694dArr);

    public boolean m15160a(long j) {
        return this.f13264q >= j;
    }

    protected void m15161b() {
        Class cls = null;
        if (this.f13256i != null && this.f13257j != null && this.f13260m.m15143i()) {
            if (this.f13255h == null) {
                String property;
                try {
                    property = System.getProperty("smack.debuggerClass");
                } catch (Throwable th) {
                    Object obj = cls;
                }
                if (property != null) {
                    try {
                        cls = Class.forName(property);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (cls == null) {
                    this.f13255h = new C2552a(this, this.f13257j, this.f13256i);
                    this.f13256i = this.f13255h.m14563a();
                    this.f13257j = this.f13255h.m14566b();
                    return;
                }
                try {
                    this.f13255h = (C2551a) cls.getConstructor(new Class[]{C2678j.class, Writer.class, Reader.class}).newInstance(new Object[]{this, this.f13257j, this.f13256i});
                    this.f13256i = this.f13255h.m14563a();
                    this.f13257j = this.f13255h.m14566b();
                    return;
                } catch (Throwable e2) {
                    throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
                }
            }
            this.f13256i = this.f13255h.m14564a(this.f13256i);
            this.f13257j = this.f13255h.m14565a(this.f13257j);
        }
    }

    public void m15162b(C2559l c2559l) {
        this.f13262o.remove(c2559l);
    }

    public void m15163b(C2557n c2557n, C2642a c2642a) {
        if (c2557n == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f13254g.put(c2557n, new C2687a(c2557n, c2642a));
    }

    public abstract void m15164c();

    protected void m15165c(C2694d c2694d) {
        for (C2687a a : this.f13254g.values()) {
            a.m15203a(c2694d);
        }
    }

    public String m15166d() {
        return this.f13260m.m15139e();
    }

    public String m15167e() {
        return this.f13260m.m15142h();
    }

    public String m15168f() {
        return this.f13260m.m15140f();
    }

    public int m15169g() {
        return this.f13260m.m15141g();
    }

    public Collection<C2559l> m15170h() {
        return this.f13262o;
    }

    public boolean m15171i() {
        return this.f13263p == 0;
    }

    public boolean m15172j() {
        return this.f13263p == 1;
    }

    public int m15173k() {
        return this.f13251d;
    }

    public void m15174l() {
        this.f13251d = 0;
    }

    public long m15175m() {
        return this.f13252e;
    }

    public void m15176n() {
        this.f13252e = -1;
    }

    public int m15177o() {
        return this.f13263p;
    }

    public void m15178p() {
        this.f13264q = System.currentTimeMillis();
    }

    public boolean m15179q() {
        return System.currentTimeMillis() - this.f13264q < ((long) C2706r.m15302c());
    }

    public boolean m15180r() {
        boolean z;
        synchronized (this.f13250b) {
            Collection arrayList = new ArrayList();
            Iterator it = this.f13250b.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (System.currentTimeMillis() - ((Long) pair.second).longValue() < 1800000) {
                    arrayList.add(pair);
                }
            }
            this.f13250b.clear();
            this.f13250b.addAll(arrayList);
            z = this.f13250b.size() >= 6;
        }
        return z;
    }

    public void m15181s() {
        synchronized (this.f13250b) {
            this.f13250b.clear();
        }
    }
}
