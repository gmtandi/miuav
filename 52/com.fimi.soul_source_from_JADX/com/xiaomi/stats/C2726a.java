package com.xiaomi.stats;

import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.push.service.C2651g;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.C2706r;
import com.xiaomi.xmpush.thrift.C2736e;
import com.xiaomi.xmpush.thrift.C2738f;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.stats.a */
public class C2726a {
    private static final int f13453a;
    private int f13454b;
    private boolean f13455c;
    private XMPushService f13456d;
    private String f13457e;
    private long f13458f;
    private LinkedList<C2725b> f13459g;

    /* renamed from: com.xiaomi.stats.a.a */
    class C2724a {
        static final C2726a f13450a;

        static {
            f13450a = new C2726a();
        }
    }

    /* renamed from: com.xiaomi.stats.a.b */
    class C2725b {
        long f13451a;
        C2736e f13452b;

        C2725b() {
            this.f13452b = new C2736e();
        }
    }

    static {
        f13453a = C2706r.m15303d();
    }

    public C2726a() {
        this.f13454b = f13453a;
        this.f13455c = false;
        this.f13458f = System.currentTimeMillis();
        this.f13459g = new LinkedList();
    }

    private C2725b m15379a(long j) {
        C2725b c2725b = null;
        while (!this.f13459g.isEmpty()) {
            C2725b c2725b2 = (C2725b) this.f13459g.getFirst();
            if (this.f13459g.size() <= 100) {
                break;
            }
            this.f13459g.removeFirst();
            c2725b = c2725b2;
        }
        return c2725b;
    }

    public static C2726a m15380a() {
        return C2724a.f13450a;
    }

    private String m15381a(String str) {
        String str2 = null;
        if (str != null) {
            try {
                str2 = Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(str.getBytes()), 8).substring(0, 16);
            } catch (Throwable e) {
                C2463b.m14125a(e);
            }
        }
        return str2;
    }

    private void m15382a(int i, int i2, int i3, String str, String str2, long j) {
        C2725b a = m15379a(j);
        if (a == null) {
            a = new C2725b();
        }
        a.f13452b.f13568a = (byte) i;
        a.f13452b.f13569b = i2;
        a.f13452b.f13570c = i3;
        a.f13452b.f13571d = str2;
        a.f13452b.f13572e = str;
        a.f13451a = System.currentTimeMillis();
        this.f13459g.addLast(a);
        C2463b.m14126b(String.format(Locale.US, "add stats: chid = %s, type =%d, value = %d, connpt = %s", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), str2}));
    }

    public void m15383a(int i) {
        int i2 = i * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        if (i2 < f13453a) {
            i2 = f13453a;
        }
        this.f13454b = i2;
    }

    public synchronized void m15384a(int i, int i2, int i3, String str) {
        if (this.f13457e == null) {
            C2463b.m14126b(String.format(Locale.US, "StatsHandler.add() Should initialized before add", new Object[0]));
        } else {
            Object f = C2472a.m14154f(this.f13456d);
            if (!TextUtils.isEmpty(f)) {
                m15382a(i, i2, i3, str, f, System.currentTimeMillis());
            }
        }
    }

    public synchronized void m15385a(XMPushService xMPushService) {
        this.f13456d = xMPushService;
        this.f13457e = m15381a(C2651g.m15026c(xMPushService));
    }

    public void m15386a(boolean z) {
        this.f13455c = z;
    }

    boolean m15387a(int i, int i2, int i3, String str, String str2) {
        boolean z = false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("chid = ").append(i);
        stringBuilder.append("key = ").append(i2);
        stringBuilder.append("host = ").append(str);
        stringBuilder.append("val = ").append(i3);
        stringBuilder.append("salt = ").append("XIAOMI_STATS");
        String substring = str2.substring(str2.length() - 4);
        stringBuilder.append(substring);
        String str3 = C2915a.f14760f;
        try {
            return (Base64.encodeToString(MessageDigest.getInstance("MD5").digest(stringBuilder.toString().getBytes()), 8).substring(0, 12) + substring).equals(str2);
        } catch (NoSuchAlgorithmException e) {
            return z;
        }
    }

    boolean m15388b() {
        return this.f13455c && !this.f13459g.isEmpty() && System.currentTimeMillis() - this.f13458f > ((long) this.f13454b);
    }

    synchronized C2738f m15389c() {
        C2738f c2738f;
        c2738f = null;
        if (m15388b()) {
            List arrayList = new ArrayList();
            Iterator it = this.f13459g.iterator();
            while (it.hasNext()) {
                arrayList.add(((C2725b) it.next()).f13452b);
            }
            this.f13459g.clear();
            this.f13458f = System.currentTimeMillis();
            c2738f = new C2738f(this.f13457e, arrayList);
            if (!C2472a.m14153e(this.f13456d)) {
                c2738f.m15475a(C2651g.m15027d(this.f13456d));
            }
        }
        return c2738f;
    }
}
