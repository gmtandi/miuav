package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.channel.commonutils.string.C2475c;
import com.xiaomi.channel.commonutils.string.C2476d;
import com.xiaomi.push.service.C2671x;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ac;
import com.xiaomi.xmpush.thrift.C2729a;
import com.xiaomi.xmpush.thrift.C2734d;
import com.xiaomi.xmpush.thrift.C2752m;
import com.xiaomi.xmpush.thrift.C2766t;
import com.xiaomi.xmpush.thrift.C2773x;
import java.util.ArrayList;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p137b.C2478b;

/* renamed from: com.xiaomi.mipush.sdk.e */
public class C2573e {
    private static C2573e f12889b;
    private static final ArrayList<C2572a> f12890e;
    private boolean f12891a;
    private Context f12892c;
    private String f12893d;
    private Intent f12894f;
    private Integer f12895g;

    /* renamed from: com.xiaomi.mipush.sdk.e.a */
    class C2572a<T extends C2478b<T, ?>> {
        T f12886a;
        C2729a f12887b;
        boolean f12888c;

        C2572a() {
        }
    }

    static {
        f12890e = new ArrayList();
    }

    private C2573e(Context context) {
        this.f12891a = false;
        this.f12894f = null;
        this.f12895g = null;
        this.f12892c = context.getApplicationContext();
        this.f12893d = C2476d.m14165a(6);
        this.f12891a = m14656g();
    }

    public static C2573e m14652a(Context context) {
        if (f12889b == null) {
            f12889b = new C2573e(context);
        }
        return f12889b;
    }

    private final <T extends C2478b<T, ?>> void m14654a(T t, C2729a c2729a, boolean z, boolean z2, C2734d c2734d) {
        if (C2566a.m14615a(this.f12892c).m14632i()) {
            Intent h = m14657h();
            C2478b a = C2569c.m14639a(this.f12892c, t, c2729a, z);
            if (c2734d != null) {
                a.m15563a(c2734d);
            }
            byte[] a2 = C2773x.m15815a(a);
            if (a2 == null) {
                C2463b.m14123a("send message fail, because msgBytes is null.");
                return;
            }
            h.setAction("com.xiaomi.mipush.SEND_MESSAGE");
            h.putExtra("mipush_payload", a2);
            this.f12892c.startService(h);
        } else if (z2) {
            m14666a((C2478b) t, c2729a, z);
        } else {
            C2463b.m14123a("drop the message before initialization.");
        }
    }

    private boolean m14656g() {
        try {
            PackageInfo packageInfo = this.f12892c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            return packageInfo != null && packageInfo.versionCode >= Opcodes.LMUL;
        } catch (Exception e) {
            return false;
        }
    }

    private Intent m14657h() {
        String packageName = this.f12892c.getPackageName();
        if (!m14669b() || "com.xiaomi.xmsf".equals(packageName)) {
            m14660k();
            Intent intent = new Intent(this.f12892c, XMPushService.class);
            intent.putExtra("mipush_app_package", packageName);
            return intent;
        }
        intent = new Intent();
        intent.setPackage("com.xiaomi.xmsf");
        intent.setClassName("com.xiaomi.xmsf", m14658i());
        intent.putExtra("mipush_app_package", packageName);
        m14659j();
        return intent;
    }

    private String m14658i() {
        try {
            if (this.f12892c.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= Opcodes.FMUL) {
                return "com.xiaomi.push.service.XMPushService";
            }
        } catch (Exception e) {
        }
        return "com.xiaomi.xmsf.push.service.XMPushService";
    }

    private void m14659j() {
        this.f12892c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f12892c, XMPushService.class), 2, 1);
    }

    private void m14660k() {
        this.f12892c.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f12892c, XMPushService.class), 1, 1);
    }

    public void m14661a() {
        this.f12892c.startService(m14657h());
    }

    public void m14662a(int i) {
        Intent h = m14657h();
        h.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        h.putExtra(C2671x.f13223v, this.f12892c.getPackageName());
        h.putExtra(C2671x.f13224w, i);
        this.f12892c.startService(h);
    }

    public final void m14663a(C2752m c2752m, boolean z) {
        this.f12894f = null;
        Intent h = m14657h();
        byte[] a = C2773x.m15815a(C2569c.m14638a(this.f12892c, c2752m, C2729a.Registration));
        if (a == null) {
            C2463b.m14123a("register fail, because msgBytes is null.");
            return;
        }
        h.setAction("com.xiaomi.mipush.REGISTER_APP");
        h.putExtra("mipush_app_id", C2566a.m14615a(this.f12892c).m14626c());
        h.putExtra("mipush_payload", a);
        h.putExtra("mipush_session", this.f12893d);
        h.putExtra("mipush_env_chanage", z);
        h.putExtra("mipush_env_type", C2566a.m14615a(this.f12892c).m14636m());
        if (C2472a.m14152d(this.f12892c) && m14673f()) {
            this.f12892c.startService(h);
        } else {
            this.f12894f = h;
        }
    }

    public final void m14664a(C2766t c2766t) {
        Intent h = m14657h();
        byte[] a = C2773x.m15815a(C2569c.m14638a(this.f12892c, c2766t, C2729a.UnRegistration));
        if (a == null) {
            C2463b.m14123a("unregister fail, because msgBytes is null.");
            return;
        }
        h.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        h.putExtra("mipush_app_id", C2566a.m14615a(this.f12892c).m14626c());
        h.putExtra("mipush_payload", a);
        this.f12892c.startService(h);
    }

    public final <T extends C2478b<T, ?>> void m14665a(T t, C2729a c2729a, C2734d c2734d) {
        m14667a(t, c2729a, !c2729a.equals(C2729a.Registration), c2734d);
    }

    public <T extends C2478b<T, ?>> void m14666a(T t, C2729a c2729a, boolean z) {
        C2572a c2572a = new C2572a();
        c2572a.f12886a = t;
        c2572a.f12887b = c2729a;
        c2572a.f12888c = z;
        synchronized (f12890e) {
            f12890e.add(c2572a);
            if (f12890e.size() > 10) {
                f12890e.remove(0);
            }
        }
    }

    public final <T extends C2478b<T, ?>> void m14667a(T t, C2729a c2729a, boolean z, C2734d c2734d) {
        m14654a(t, c2729a, z, true, c2734d);
    }

    public void m14668b(int i) {
        Intent h = m14657h();
        h.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        h.putExtra(C2671x.f13223v, this.f12892c.getPackageName());
        h.putExtra(C2671x.f13225x, i);
        h.putExtra(C2671x.f13227z, C2475c.m14164b(this.f12892c.getPackageName() + i));
        this.f12892c.startService(h);
    }

    public boolean m14669b() {
        return this.f12891a && 1 == C2566a.m14615a(this.f12892c).m14636m();
    }

    public void m14670c() {
        if (this.f12894f != null) {
            this.f12892c.startService(this.f12894f);
            this.f12894f = null;
        }
    }

    public void m14671d() {
        synchronized (f12890e) {
            Iterator it = f12890e.iterator();
            while (it.hasNext()) {
                C2572a c2572a = (C2572a) it.next();
                m14654a(c2572a.f12886a, c2572a.f12887b, c2572a.f12888c, false, null);
            }
            f12890e.clear();
        }
    }

    public void m14672e() {
        Intent h = m14657h();
        h.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        h.putExtra(C2671x.f13223v, this.f12892c.getPackageName());
        h.putExtra(C2671x.f13227z, C2475c.m14164b(this.f12892c.getPackageName()));
        this.f12892c.startService(h);
    }

    public boolean m14673f() {
        if (!m14669b() || !ac.m14975a(this.f12892c).m14977a()) {
            return true;
        }
        if (this.f12895g == null) {
            this.f12895g = Integer.valueOf(ac.m14975a(this.f12892c).m14978b());
            if (this.f12895g.intValue() == 0) {
                this.f12892c.getContentResolver().registerContentObserver(ac.m14975a(this.f12892c).m14979c(), false, new C2574f(this, new Handler(Looper.getMainLooper())));
            }
        }
        return this.f12895g.intValue() != 0;
    }
}
