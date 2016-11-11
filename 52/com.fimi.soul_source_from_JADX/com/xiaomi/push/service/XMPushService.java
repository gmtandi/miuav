package com.xiaomi.push.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.misc.C2464a;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.channel.commonutils.string.C2473a;
import com.xiaomi.channel.commonutils.string.C2475c;
import com.xiaomi.kenai.jbosh.C2518s;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.push.service.C2669v.C2668c;
import com.xiaomi.push.service.timers.C2665a;
import com.xiaomi.smack.C2557n;
import com.xiaomi.smack.C2559l;
import com.xiaomi.smack.C2674k;
import com.xiaomi.smack.C2675a;
import com.xiaomi.smack.C2678j;
import com.xiaomi.smack.C2679b;
import com.xiaomi.smack.C2711t;
import com.xiaomi.smack.C2723w;
import com.xiaomi.smack.packet.C2692a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2695b;
import com.xiaomi.smack.packet.C2696c;
import com.xiaomi.smack.packet.C2699f;
import com.xiaomi.smack.packet.C2699f.C2698b;
import com.xiaomi.smack.util.C2718g;
import com.xiaomi.stats.C2728b;
import com.xiaomi.xmpush.thrift.C2729a;
import com.xiaomi.xmpush.thrift.C2740g;
import com.xiaomi.xmpush.thrift.C2748k;
import com.xiaomi.xmpush.thrift.C2750l;
import com.xiaomi.xmpush.thrift.C2752m;
import com.xiaomi.xmpush.thrift.C2773x;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p137b.C2478b;

public class XMPushService extends Service implements C2559l {
    public static int f13082a;
    private C2674k f13083b;
    private C2675a f13084c;
    private aa f13085d;
    private long f13086e;
    private C2711t f13087f;
    private C2679b f13088g;
    private C2678j f13089h;
    private C2645b f13090i;
    private PacketSync f13091j;
    private C2665a f13092k;
    private C2648d f13093l;
    private C2557n f13094m;

    /* renamed from: com.xiaomi.push.service.XMPushService.e */
    public abstract class C2629e {
        protected int f13061e;

        public C2629e(int i) {
            this.f13061e = i;
        }

        public abstract void m14894a();

        public abstract String m14895b();

        public void m14896c() {
            if (!(this.f13061e == 4 || this.f13061e == 8)) {
                C2463b.m14123a("JOB: " + m14895b());
            }
            m14894a();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.a */
    class C2630a extends C2629e {
        C2667b f13062a;
        final /* synthetic */ XMPushService f13063b;

        public C2630a(XMPushService xMPushService, C2667b c2667b) {
            this.f13063b = xMPushService;
            super(9);
            this.f13062a = null;
            this.f13062a = c2667b;
        }

        public void m14897a() {
            try {
                if (!this.f13063b.m14964f()) {
                    C2463b.m14127c("trying bind while the connection is not created, quit!");
                } else if (this.f13062a.f13189m == C2668c.unbind) {
                    this.f13062a.m15105a(C2668c.binding, 0, 0, null, null);
                    this.f13063b.f13089h.m15152a(this.f13062a);
                } else {
                    C2463b.m14123a("trying duplicate bind, ingore! " + this.f13062a.f13189m);
                }
            } catch (Throwable e) {
                C2463b.m14125a(e);
                this.f13063b.m14956b(10, e);
            }
        }

        public String m14898b() {
            return "bind the client. " + this.f13062a.f13184h + ", " + this.f13062a.f13178b;
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.b */
    class C2631b extends C2629e {
        private final C2667b f13064a;

        public C2631b(C2667b c2667b) {
            super(12);
            this.f13064a = c2667b;
        }

        public void m14899a() {
            this.f13064a.m15105a(C2668c.unbind, 1, 21, null, null);
        }

        public String m14900b() {
            return "bind time out. chid=" + this.f13064a.f13184h;
        }

        public boolean equals(Object obj) {
            return !(obj instanceof C2631b) ? false : TextUtils.equals(((C2631b) obj).f13064a.f13184h, this.f13064a.f13184h);
        }

        public int hashCode() {
            return this.f13064a.f13184h.hashCode();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.c */
    public class C2632c extends C2629e {
        final /* synthetic */ XMPushService f13065a;

        C2632c(XMPushService xMPushService) {
            this.f13065a = xMPushService;
            super(1);
        }

        public void m14901a() {
            if (this.f13065a.m14961c()) {
                this.f13065a.m14934l();
            } else {
                C2463b.m14123a("should not connect. quit the job.");
            }
        }

        public String m14902b() {
            return "do reconnect..";
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.d */
    public class C2633d extends C2629e {
        public int f13066a;
        public Exception f13067b;
        final /* synthetic */ XMPushService f13068c;

        C2633d(XMPushService xMPushService, int i, Exception exception) {
            this.f13068c = xMPushService;
            super(2);
            this.f13066a = i;
            this.f13067b = exception;
        }

        public void m14903a() {
            this.f13068c.m14956b(this.f13066a, this.f13067b);
        }

        public String m14904b() {
            return "disconnect the connection.";
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.f */
    class C2634f extends C2629e {
        final /* synthetic */ XMPushService f13069a;

        public C2634f(XMPushService xMPushService) {
            this.f13069a = xMPushService;
            super(5);
        }

        public void m14905a() {
            this.f13069a.f13093l.quit();
        }

        public String m14906b() {
            return "ask the job queue to quit";
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.g */
    public class C2635g extends Binder {
        final /* synthetic */ XMPushService f13070a;

        public C2635g(XMPushService xMPushService) {
            this.f13070a = xMPushService;
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.h */
    class C2636h extends C2629e {
        final /* synthetic */ XMPushService f13071a;
        private C2694d f13072b;

        public C2636h(XMPushService xMPushService, C2694d c2694d) {
            this.f13071a = xMPushService;
            super(8);
            this.f13072b = null;
            this.f13072b = c2694d;
        }

        public void m14907a() {
            this.f13071a.f13091j.m14893a(this.f13072b);
        }

        public String m14908b() {
            return "receive a message.";
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.i */
    class C2637i extends C2629e {
        final /* synthetic */ XMPushService f13073a;

        public C2637i(XMPushService xMPushService) {
            this.f13073a = xMPushService;
            super(4);
        }

        public void m14909a() {
            if (this.f13073a.m14964f()) {
                try {
                    C2728b.m15390a();
                    this.f13073a.f13089h.m15164c();
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                    this.f13073a.m14956b(10, e);
                }
            }
        }

        public String m14910b() {
            return "send ping..";
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.j */
    class C2638j extends C2629e {
        C2667b f13074a;
        final /* synthetic */ XMPushService f13075b;

        public C2638j(XMPushService xMPushService, C2667b c2667b) {
            this.f13075b = xMPushService;
            super(4);
            this.f13074a = null;
            this.f13074a = c2667b;
        }

        public void m14911a() {
            try {
                this.f13074a.m15105a(C2668c.unbind, 1, 16, null, null);
                this.f13075b.f13089h.m15158a(this.f13074a.f13184h, this.f13074a.f13178b);
                this.f13074a.m15105a(C2668c.binding, 1, 16, null, null);
                this.f13075b.f13089h.m15152a(this.f13074a);
            } catch (Throwable e) {
                C2463b.m14125a(e);
                this.f13075b.m14956b(10, e);
            }
        }

        public String m14912b() {
            return "bind the client. " + this.f13074a.f13184h + ", " + this.f13074a.f13178b;
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.k */
    class C2639k extends C2629e {
        final /* synthetic */ XMPushService f13076a;

        C2639k(XMPushService xMPushService) {
            this.f13076a = xMPushService;
            super(3);
        }

        public void m14913a() {
            this.f13076a.m14956b(11, null);
            if (this.f13076a.m14961c()) {
                this.f13076a.m14934l();
            }
        }

        public String m14914b() {
            return "reset the connection.";
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService.l */
    class C2640l extends C2629e {
        C2667b f13077a;
        int f13078b;
        String f13079c;
        String f13080d;
        final /* synthetic */ XMPushService f13081f;

        public C2640l(XMPushService xMPushService, C2667b c2667b, int i, String str, String str2) {
            this.f13081f = xMPushService;
            super(9);
            this.f13077a = null;
            this.f13077a = c2667b;
            this.f13078b = i;
            this.f13079c = str;
            this.f13080d = str2;
        }

        public void m14915a() {
            if (!(this.f13077a.f13189m == C2668c.unbind || this.f13081f.f13089h == null)) {
                try {
                    this.f13081f.f13089h.m15158a(this.f13077a.f13184h, this.f13077a.f13178b);
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                    this.f13081f.m14956b(10, e);
                }
            }
            this.f13077a.m15105a(C2668c.unbind, this.f13078b, 0, this.f13080d, this.f13079c);
        }

        public String m14916b() {
            return "unbind the channel. " + this.f13077a.f13184h + ", " + this.f13077a.f13178b;
        }
    }

    static {
        HostManager.addReservedHost("app.chat.xiaomi.net", "58.68.235.232");
        HostManager.addReservedHost("app.chat.xiaomi.net", "app01.nodes.gslb.mi-idc.com");
        HostManager.addReservedHost("app.chat.xiaomi.net", "42.62.48.181");
        HostManager.addReservedHost("app.chat.xiaomi.net", "223.202.68.46");
        HostManager.addReservedHost("app.chat.xiaomi.net", "app02.nodes.gslb.mi-idc.com");
        C2711t.c = true;
        if (C2464a.f12421b || C2464a.f12424e || C2464a.f12422c || C2464a.f12426g) {
            C2463b.m14117a(0);
        }
        f13082a = 1;
    }

    public XMPushService() {
        this.f13086e = 0;
        this.f13091j = null;
        this.f13092k = null;
        this.f13093l = null;
        this.f13094m = new ad(this);
    }

    private C2667b m14917a(String str, Intent intent) {
        C2667b b = C2669v.m15106a().m15114b(str, intent.getStringExtra(C2671x.f13214m));
        if (b == null) {
            b = new C2667b(this);
        }
        b.f13184h = intent.getStringExtra(C2671x.f13215n);
        b.f13178b = intent.getStringExtra(C2671x.f13214m);
        b.f13179c = intent.getStringExtra(C2671x.f13217p);
        b.f13177a = intent.getStringExtra(C2671x.f13223v);
        b.f13182f = intent.getStringExtra(C2671x.f13221t);
        b.f13183g = intent.getStringExtra(C2671x.f13222u);
        b.f13181e = intent.getBooleanExtra(C2671x.f13220s, false);
        b.f13185i = intent.getStringExtra(C2671x.f13219r);
        b.f13180d = intent.getStringExtra(C2671x.f13218q);
        b.f13187k = this.f13090i;
        b.f13188l = getApplicationContext();
        C2669v.m15106a().m15111a(b);
        return b;
    }

    private C2696c m14919a(C2696c c2696c, String str) {
        byte[] a = C2673z.m15128a(str, c2696c.m15232k());
        C2696c c2696c2 = new C2696c();
        c2696c2.m15239n(c2696c.m15238n());
        c2696c2.m15237m(c2696c.m15236m());
        c2696c2.m15233k(c2696c.m15232k());
        c2696c2.m15235l(c2696c.m15234l());
        c2696c2.m15259b(true);
        String a2 = C2673z.m15124a(a, C2718g.m15363c(c2696c.m15253a()));
        C2692a c2692a = new C2692a("s", null, (String[]) null, (String[]) null);
        c2692a.m15220b(a2);
        c2696c2.m15229a(c2692a);
        return c2696c2;
    }

    private C2694d m14920a(C2694d c2694d, String str, String str2, boolean z) {
        C2669v a = C2669v.m15106a();
        List b = a.m15116b(str);
        if (b.isEmpty()) {
            C2463b.m14123a("open channel should be called first before sending a packet, pkg=" + str);
        } else {
            c2694d.m15241o(str);
            String l = c2694d.m15234l();
            if (TextUtils.isEmpty(l)) {
                l = (String) b.get(0);
                c2694d.m15235l(l);
            }
            C2667b b2 = a.m15114b(l, c2694d.m15238n());
            if (!m14964f()) {
                C2463b.m14123a("drop a packet as the channel is not connected, chid=" + l);
            } else if (b2 == null || b2.f13189m != C2668c.binded) {
                C2463b.m14123a("drop a packet as the channel is not opened, chid=" + l);
            } else if (TextUtils.equals(str2, b2.f13186j)) {
                return ((c2694d instanceof C2696c) && z) ? m14919a((C2696c) c2694d, b2.f13185i) : c2694d;
            } else {
                C2463b.m14123a("invalid session. " + str2);
            }
        }
        return null;
    }

    private String m14921a(String str) {
        return "<iq to='" + str + "' id='0' chid='0' type='get'><ping xmlns='urn:xmpp:ping'>%1$s%2$s</ping></iq>";
    }

    private void m14922a(Intent intent) {
        if (C2671x.f13211j.equals(intent.getAction())) {
            int intExtra = intent.getIntExtra(C2671x.f13215n, -1);
            int intExtra2 = intent.getIntExtra("ext_stats_key", -1);
            int intExtra3 = intent.getIntExtra("ext_stats_val", 0);
            String stringExtra = intent.getStringExtra("ext_stats_host");
            String stringExtra2 = intent.getStringExtra("ext_stats_magic");
            if (intExtra > 0 && intExtra < Util.MASK_8BIT && intExtra2 != -1 && C2728b.m15395a(intExtra, intExtra2, intExtra3, stringExtra, stringExtra2)) {
                C2728b.m15392a(intExtra, intExtra2, intExtra3, stringExtra);
            }
        }
    }

    private void m14925a(String str, int i) {
        Collection<C2667b> c = C2669v.m15106a().m15118c(str);
        if (c != null) {
            for (C2667b c2667b : c) {
                if (c2667b != null) {
                    m14945a(new C2640l(this, c2667b, i, null, null));
                }
            }
        }
        C2669v.m15106a().m15112a(str);
    }

    private void m14932j() {
        if (C2651g.m15021a(getApplicationContext()) != null) {
            C2667b a = C2651g.m15021a(getApplicationContext()).m15020a(this);
            m14947a(a);
            C2669v.m15106a().m15111a(a);
            if (C2472a.m14152d(getApplicationContext())) {
                m14952a(true);
            }
        }
    }

    private void m14933k() {
        if (!m14961c()) {
            this.f13092k.m15096a();
        } else if (!this.f13092k.m15099b()) {
            this.f13092k.m15098a(true);
        }
    }

    private void m14934l() {
        if (this.f13089h != null && this.f13089h.m15171i()) {
            C2463b.m14127c("try to connect while connecting.");
        } else if (this.f13089h == null || !this.f13089h.m15172j()) {
            this.f13083b.m15137b(C2472a.m14154f(this));
            if (this.f13087f.m15180r()) {
                m14936n();
                if (this.f13089h == null || this.f13089h.m15177o() == 2) {
                    m14935m();
                }
            } else {
                m14935m();
                if (this.f13089h == null || this.f13089h.m15177o() == 2) {
                    m14936n();
                }
            }
            if (this.f13089h == null) {
                C2664t.m15090a();
                C2669v.m15106a().m15108a((Context) this);
            }
        } else {
            C2463b.m14127c("try to connect while is connected.");
        }
    }

    private void m14935m() {
        try {
            this.f13087f.m15333t();
            this.f13087f.m15154a(this.f13094m, new an(this));
            this.f13089h = this.f13087f;
        } catch (Exception e) {
            C2463b.m14124a("fail to create xmpp connection", (Throwable) e);
            this.f13087f.m15325a(new C2699f(C2698b.unavailable), 3, e);
        }
    }

    private void m14936n() {
        try {
            Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost("mibind.chat.gslb.mi-idc.com");
            if (fallbacksByHost != null) {
                this.f13084c.m15145a(fallbacksByHost);
            }
            this.f13088g.m15186a();
            this.f13088g.m15154a(this.f13094m, new ae(this));
            this.f13089h = this.f13088g;
        } catch (Throwable e) {
            C2463b.m14124a("fail to create BOSH connection", e);
            this.f13088g.m15190a(new C2699f(C2698b.unavailable), 3, e);
        }
    }

    public C2696c m14937a(C2748k c2748k) {
        try {
            C2696c c2696c = new C2696c();
            c2696c.m15235l(Constants.VIA_SHARE_TYPE_TEXT);
            c2696c.m15237m("xiaomi.com");
            c2696c.m15239n(C2651g.m15021a(this).f13132a);
            c2696c.m15259b(true);
            c2696c.m15266f("push");
            c2696c.m15241o(c2748k.f13745f);
            String str = C2651g.m15021a(this).f13132a;
            c2748k.f13746g.f13606b = str.substring(0, str.indexOf("@"));
            c2748k.f13746g.f13608d = str.substring(str.indexOf("/") + 1);
            String valueOf = String.valueOf(C2473a.m14158a(C2673z.m15129a(C2673z.m15128a(C2651g.m15021a(this).f13134c, c2696c.m15232k()), C2773x.m15815a(c2748k))));
            C2692a c2692a = new C2692a("s", null, (String[]) null, (String[]) null);
            c2692a.m15220b(valueOf);
            c2696c.m15229a(c2692a);
            C2463b.m14123a("try send mi push message. packagename:" + c2748k.f13745f + " action:" + c2748k.f13740a);
            return c2696c;
        } catch (Throwable e) {
            C2463b.m14125a(e);
            return null;
        }
    }

    public C2696c m14938a(byte[] bArr) {
        C2748k c2748k = new C2748k();
        try {
            C2773x.m15814a(c2748k, bArr);
            return m14937a(c2748k);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            return null;
        }
    }

    public C2711t m14939a(C2674k c2674k) {
        return new C2711t(this, c2674k);
    }

    public C2748k m14940a(String str, String str2) {
        C2478b c2750l = new C2750l();
        c2750l.m15597b(str2);
        c2750l.m15601c("package uninstalled");
        c2750l.m15591a(C2694d.m15226j());
        c2750l.m15592a(false);
        return m14941a(str, str2, c2750l, C2729a.Notification);
    }

    public <T extends C2478b<T, ?>> C2748k m14941a(String str, String str2, T t, C2729a c2729a) {
        byte[] a = C2773x.m15815a(t);
        C2748k c2748k = new C2748k();
        C2740g c2740g = new C2740g();
        c2740g.f13605a = 5;
        c2740g.f13606b = "fakeid";
        c2748k.m15564a(c2740g);
        c2748k.m15566a(ByteBuffer.wrap(a));
        c2748k.m15562a(c2729a);
        c2748k.m15575c(true);
        c2748k.m15571b(str);
        c2748k.m15567a(false);
        c2748k.m15565a(str2);
        return c2748k;
    }

    public void m14942a() {
        this.f13085d.m14971a();
        Iterator it = C2669v.m15106a().m15115b().iterator();
        while (it.hasNext()) {
            m14945a(new C2630a(this, (C2667b) it.next()));
        }
    }

    public void m14943a(int i) {
        this.f13093l.m15014a(i);
    }

    public void m14944a(int i, Exception exception) {
        m14952a(false);
    }

    public void m14945a(C2629e c2629e) {
        m14946a(c2629e, 0);
    }

    public void m14946a(C2629e c2629e, long j) {
        this.f13093l.m15016a(c2629e, j);
    }

    public void m14947a(C2667b c2667b) {
        c2667b.m15104a(new al(this));
    }

    public void m14948a(C2694d c2694d) {
        if (this.f13089h != null) {
            this.f13089h.m15155a(c2694d);
            return;
        }
        throw new C2723w("try send msg while connection is null.");
    }

    public void m14949a(Exception exception) {
        m14952a(false);
    }

    public void m14950a(String str, String str2, int i, String str3, String str4) {
        C2667b b = C2669v.m15106a().m15114b(str, str2);
        if (b != null) {
            m14945a(new C2640l(this, b, i, str4, str3));
        }
        C2669v.m15106a().m15113a(str, str2);
    }

    public void m14951a(String str, byte[] bArr) {
        if (this.f13089h != null) {
            C2694d a = m14938a(bArr);
            if (a != null) {
                this.f13089h.m15155a(a);
                return;
            } else {
                C2654j.m15038a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "not a valid message");
                return;
            }
        }
        throw new C2723w("try send msg while connection is null.");
    }

    public void m14952a(boolean z) {
        this.f13085d.m14972a(z);
    }

    public void m14953a(byte[] bArr, String str) {
        if (bArr == null) {
            C2654j.m15038a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "null payload");
            C2463b.m14123a("register request without payload");
            return;
        }
        C2748k c2748k = new C2748k();
        try {
            C2773x.m15814a(c2748k, bArr);
            if (c2748k.f13740a == C2729a.Registration) {
                C2752m c2752m = new C2752m();
                try {
                    C2773x.m15814a(c2752m, c2748k.m15580f());
                    C2654j.m15040a(c2748k.m15584j(), bArr);
                    m14945a(new C2653i(this, c2748k.m15584j(), c2752m.m15624d(), c2752m.m15630h(), bArr));
                    return;
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                    C2654j.m15038a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data action error.");
                    return;
                }
            }
            C2654j.m15038a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " registration action required.");
            C2463b.m14123a("register request with invalid payload");
        } catch (Throwable e2) {
            C2463b.m14125a(e2);
            C2654j.m15038a(this, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, " data container error.");
        }
    }

    public void m14954a(C2694d[] c2694dArr) {
        if (this.f13089h != null) {
            this.f13089h.m15159a(c2694dArr);
            return;
        }
        throw new C2723w("try send msg while connection is null.");
    }

    public void m14955b() {
        C2463b.m14126b("begin to connect...");
    }

    public void m14956b(int i, Exception exception) {
        C2463b.m14123a("disconnect " + hashCode() + ", " + (this.f13089h == null ? null : Integer.valueOf(this.f13089h.hashCode())));
        if (this.f13089h != null) {
            this.f13089h.m15156a(new C2699f(C2698b.unavailable), i, exception);
            this.f13089h = null;
        }
        m14943a(7);
        m14943a(4);
        C2669v.m15106a().m15109a((Context) this, i);
    }

    public void m14957b(C2629e c2629e) {
        this.f13093l.m15015a(c2629e.f13061e, (Object) c2629e);
    }

    public void m14958b(C2667b c2667b) {
        if (c2667b != null) {
            long a = c2667b.m15102a();
            C2463b.m14123a("schedule rebind job in " + (a / 1000));
            m14946a(new C2630a(this, c2667b), a);
        }
    }

    public void m14959b(C2748k c2748k) {
        if (this.f13089h != null) {
            C2694d a = m14937a(c2748k);
            if (a != null) {
                this.f13089h.m15155a(a);
                return;
            }
            return;
        }
        throw new C2723w("try send msg while connection is null.");
    }

    public boolean m14960b(int i) {
        return this.f13093l.m15018b(i);
    }

    public boolean m14961c() {
        return C2472a.m14152d(this) && C2669v.m15106a().m15117c() > 0;
    }

    public C2645b m14962d() {
        return new C2645b();
    }

    public C2645b m14963e() {
        return this.f13090i;
    }

    public boolean m14964f() {
        return this.f13089h != null && this.f13089h.m15172j();
    }

    public boolean m14965g() {
        return this.f13089h != null && this.f13089h.m15171i();
    }

    public C2678j m14966h() {
        return this.f13089h;
    }

    public void m14967i() {
        m14946a(new af(this, 10), 120000);
    }

    public IBinder onBind(Intent intent) {
        return new C2635g(this);
    }

    public void onCreate() {
        super.onCreate();
        C2650f a = C2651g.m15021a(this);
        if (a != null) {
            C2464a.m14129a(a.f13138g);
        }
        HostManager.init(this, null, null, Constants.VIA_RESULT_SUCCESS, "push", "2.1");
        this.f13083b = new C2674k(null, 5222, "xiaomi.com", null);
        this.f13083b.m15136a(true);
        this.f13087f = m14939a(this.f13083b);
        this.f13087f.m15329b(m14921a("xiaomi.com"));
        this.f13084c = new C2675a(false, new Fallback("mibind.chat.gslb.mi-idc.com"), 80, "mibind/http-bind", "xiaomi.com", null);
        System.setProperty(C2518s.class.getName() + ".emptyRequestDelay", String.valueOf(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
        this.f13088g = new C2679b(this, this.f13084c);
        this.f13090i = m14962d();
        this.f13090i.m15003a((Context) this);
        this.f13092k = new C2665a(this);
        this.f13087f.m15153a((C2559l) this);
        this.f13088g.m15153a((C2559l) this);
        this.f13091j = new PacketSync(this);
        this.f13085d = new aa(this);
        new C2647c().m15009a();
        this.f13093l = new C2648d("Connection Controller Thread");
        this.f13093l.start();
        m14945a(new ag(this, 11));
        C2669v a2 = C2669v.m15106a();
        a2.m15120e();
        a2.m15110a(new ah(this));
    }

    public void onDestroy() {
        this.f13093l.m15013a();
        m14945a(new am(this, 2));
        m14945a(new C2634f(this));
        C2669v.m15106a().m15120e();
        C2669v.m15106a().m15109a((Context) this, 15);
        C2669v.m15106a().m15119d();
        this.f13087f.m15162b((C2559l) this);
        this.f13088g.m15162b((C2559l) this);
        this.f13092k.m15096a();
        super.onDestroy();
        C2463b.m14123a("Service destroyed");
    }

    public void onStart(Intent intent, int i) {
        C2667b c2667b = null;
        boolean z = true;
        boolean z2 = false;
        if (intent == null) {
            C2463b.m14127c("onStart() with intent NULL");
        } else {
            C2463b.m14126b("onStart() with intent.Action = " + intent.getAction());
        }
        C2669v a = C2669v.m15106a();
        if (intent != null && intent.getAction() != null) {
            String stringExtra;
            String stringExtra2;
            boolean z3;
            if (C2671x.f13202a.equalsIgnoreCase(intent.getAction()) || C2671x.f13208g.equalsIgnoreCase(intent.getAction())) {
                stringExtra = intent.getStringExtra(C2671x.f13215n);
                stringExtra2 = intent.getStringExtra(C2671x.f13226y);
                if (TextUtils.isEmpty(intent.getStringExtra(C2671x.f13219r))) {
                    C2463b.m14123a("security is empty. ignore.");
                } else if (stringExtra != null) {
                    C2667b a2 = m14917a(stringExtra, intent);
                    if (TextUtils.isEmpty(a2.f13186j) || TextUtils.equals(stringExtra2, a2.f13186j)) {
                        z3 = false;
                    } else {
                        C2463b.m14123a("session changed. old session=" + a2.f13186j + ", new session=" + stringExtra2);
                        z3 = true;
                    }
                    a2.f13186j = stringExtra2;
                    if (!C2472a.m14152d(this)) {
                        this.f13090i.m15006a(this, a2, false, 2, null);
                    } else if (!m14964f()) {
                        m14952a(true);
                    } else if (z3) {
                        m14945a(new C2638j(this, a2));
                    } else if (a2.f13189m == C2668c.binding) {
                        C2463b.m14123a(String.format("the client is binding. %1$s %2$s.", new Object[]{a2.f13184h, a2.f13178b}));
                    } else if (a2.f13189m == C2668c.binded) {
                        this.f13090i.m15006a(this, a2, true, 0, null);
                    } else {
                        m14945a(new C2630a(this, a2));
                    }
                } else {
                    C2463b.m14127c("channel id is empty, do nothing!");
                }
            } else if (C2671x.f13207f.equalsIgnoreCase(intent.getAction())) {
                stringExtra = intent.getStringExtra(C2671x.f13223v);
                r2 = intent.getStringExtra(C2671x.f13215n);
                Object stringExtra3 = intent.getStringExtra(C2671x.f13214m);
                if (TextUtils.isEmpty(r2)) {
                    for (String stringExtra4 : a.m15116b(stringExtra4)) {
                        m14925a(stringExtra4, 2);
                    }
                } else if (TextUtils.isEmpty(stringExtra3)) {
                    m14925a(r2, 2);
                } else {
                    m14950a(r2, stringExtra3, 2, null, null);
                }
            } else if (C2671x.f13203b.equalsIgnoreCase(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(C2671x.f13223v);
                stringExtra2 = intent.getStringExtra(C2671x.f13226y);
                Bundle bundleExtra = intent.getBundleExtra("ext_packet");
                C2694d a3 = m14920a(new C2696c(bundleExtra), stringExtra4, stringExtra2, intent.getBooleanExtra("ext_encrypt", true));
                if (a3 != null) {
                    m14945a(new ab(this, a3));
                }
            } else if (C2671x.f13205d.equalsIgnoreCase(intent.getAction())) {
                stringExtra2 = intent.getStringExtra(C2671x.f13223v);
                r2 = intent.getStringExtra(C2671x.f13226y);
                Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
                C2696c[] c2696cArr = new C2696c[parcelableArrayExtra.length];
                boolean booleanExtra = intent.getBooleanExtra("ext_encrypt", true);
                while (r3 < parcelableArrayExtra.length) {
                    c2696cArr[r3] = new C2696c((Bundle) parcelableArrayExtra[r3]);
                    c2696cArr[r3] = (C2696c) m14920a(c2696cArr[r3], stringExtra2, r2, booleanExtra);
                    if (c2696cArr[r3] != null) {
                        r3++;
                    } else {
                        return;
                    }
                }
                m14945a(new C2641a(this, c2696cArr));
            } else if (C2671x.f13204c.equalsIgnoreCase(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(C2671x.f13223v);
                stringExtra2 = intent.getStringExtra(C2671x.f13226y);
                r4 = new C2695b(intent.getBundleExtra("ext_packet"));
                if (m14920a(r4, stringExtra4, stringExtra2, false) != null) {
                    m14945a(new ab(this, r4));
                }
            } else if (C2671x.f13206e.equalsIgnoreCase(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(C2671x.f13223v);
                stringExtra2 = intent.getStringExtra(C2671x.f13226y);
                r4 = new C2699f(intent.getBundleExtra("ext_packet"));
                if (m14920a(r4, stringExtra4, stringExtra2, false) != null) {
                    m14945a(new ab(this, r4));
                }
            } else if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                    C2463b.m14123a("Service called on timer");
                } else if (System.currentTimeMillis() - this.f13086e >= 30000) {
                    this.f13086e = System.currentTimeMillis();
                    C2463b.m14123a("Service called on check alive.");
                } else {
                    return;
                }
                if (this.f13093l.m15017b()) {
                    C2463b.m14127c("ERROR, the job controller is blocked.");
                    C2669v.m15106a().m15109a((Context) this, 14);
                    stopSelf();
                } else if (m14964f()) {
                    if (this.f13089h.m15179q()) {
                        m14945a(new C2637i(this));
                    } else {
                        m14945a(new C2633d(this, 17, null));
                    }
                } else if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                    m14952a(false);
                } else {
                    m14952a(true);
                }
            } else if ("com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                NetworkInfo activeNetworkInfo;
                try {
                    activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                    activeNetworkInfo = null;
                }
                if (activeNetworkInfo != null) {
                    C2463b.m14123a("network changed, " + activeNetworkInfo.toString());
                } else {
                    C2463b.m14123a("network changed, no active network");
                }
                this.f13087f.m15181s();
                this.f13088g.m15181s();
                if (!C2472a.m14152d(this)) {
                    m14945a(new C2633d(this, 2, null));
                } else if (!(m14964f() || m14965g())) {
                    this.f13093l.m15014a(1);
                    m14945a(new C2632c(this));
                }
                m14933k();
            } else if (C2671x.f13209h.equals(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(C2671x.f13215n);
                if (stringExtra4 != null) {
                    m14917a(stringExtra4, intent).f13186j = intent.getStringExtra(C2671x.f13226y);
                }
                m14945a(new C2639k(this));
            } else if (C2671x.f13210i.equals(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(C2671x.f13223v);
                List b = a.m15116b(stringExtra4);
                if (b.isEmpty()) {
                    C2463b.m14123a("open channel should be called first before update info, pkg=" + stringExtra4);
                    return;
                }
                stringExtra4 = intent.getStringExtra(C2671x.f13215n);
                Object stringExtra5 = intent.getStringExtra(C2671x.f13214m);
                if (TextUtils.isEmpty(stringExtra4)) {
                    stringExtra4 = (String) b.get(0);
                }
                if (TextUtils.isEmpty(stringExtra5)) {
                    r0 = a.m15118c(stringExtra4);
                    if (!(r0 == null || r0.isEmpty())) {
                        c2667b = (C2667b) r0.iterator().next();
                    }
                } else {
                    c2667b = a.m15114b(stringExtra4, stringExtra5);
                }
                if (c2667b != null) {
                    if (intent.hasExtra(C2671x.f13221t)) {
                        c2667b.f13182f = intent.getStringExtra(C2671x.f13221t);
                    }
                    if (intent.hasExtra(C2671x.f13222u)) {
                        c2667b.f13183g = intent.getStringExtra(C2671x.f13222u);
                    }
                }
            } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
                if (ac.m14975a(getApplicationContext()).m14977a() && ac.m14975a(getApplicationContext()).m14978b() == 0) {
                    C2463b.m14123a("register without being provisioned. " + intent.getStringExtra("mipush_app_package"));
                    return;
                }
                byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
                String stringExtra6 = intent.getStringExtra("mipush_app_package");
                z3 = intent.getBooleanExtra("mipush_env_chanage", false);
                r3 = intent.getIntExtra("mipush_env_type", 1);
                C2652h.m15031a((Context) this).m15034c(stringExtra6);
                if (!z3 || "com.xiaomi.xmsf".equals(getPackageName())) {
                    m14953a(byteArrayExtra, stringExtra6);
                } else {
                    m14945a(new ai(this, 14, r3, byteArrayExtra, stringExtra6));
                }
            } else if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                stringExtra2 = intent.getStringExtra("mipush_app_package");
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                r0 = C2669v.m15106a().m15118c(Constants.VIA_SHARE_TYPE_TEXT);
                if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                    C2652h.m15031a((Context) this).m15033b(stringExtra2);
                }
                if (r0.isEmpty()) {
                    C2654j.m15042b(stringExtra2, byteArrayExtra2);
                } else if (((C2667b) r0.iterator().next()).f13189m != C2668c.binded) {
                    C2654j.m15042b(stringExtra2, byteArrayExtra2);
                } else {
                    m14945a(new aj(this, 4, stringExtra2, byteArrayExtra2));
                }
            } else if (C2672y.f13228a.equals(intent.getAction())) {
                stringExtra4 = intent.getStringExtra("uninstall_pkg_name");
                if (stringExtra4 != null && !TextUtils.isEmpty(stringExtra4.trim())) {
                    try {
                        getPackageManager().getPackageInfo(stringExtra4, Opcodes.ACC_NATIVE);
                        z = false;
                    } catch (NameNotFoundException e2) {
                    }
                    if ("com.xiaomi.channel".equals(stringExtra4) && !C2669v.m15106a().m15118c(Constants.VIA_TO_TYPE_QQ_GROUP).isEmpty() && r9) {
                        m14925a(Constants.VIA_TO_TYPE_QQ_GROUP, 0);
                        C2463b.m14123a("close the miliao channel as the app is uninstalled.");
                        return;
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences("pref_registered_pkg_names", 0);
                    r2 = sharedPreferences.getString(stringExtra4, null);
                    if (!TextUtils.isEmpty(r2) && r9) {
                        Editor edit = sharedPreferences.edit();
                        edit.remove(stringExtra4);
                        edit.commit();
                        if (m14964f() && r2 != null) {
                            try {
                                m14959b(m14940a(stringExtra4, r2));
                                C2463b.m14123a("uninstall " + stringExtra4 + " msg sent");
                            } catch (Exception e3) {
                                C2463b.m14127c("Fail to send Message: " + e3.getMessage());
                                m14956b(10, e3);
                            }
                        }
                    }
                }
            } else if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
                stringExtra4 = intent.getStringExtra(C2671x.f13223v);
                r1 = intent.getIntExtra(C2671x.f13224w, 0);
                if (!TextUtils.isEmpty(stringExtra4)) {
                    if (r1 >= 0) {
                        C2662r.m15079a((Context) this, stringExtra4, r1);
                    } else if (r1 == -1) {
                        C2662r.m15083b(this, stringExtra4);
                    }
                }
            } else if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
                r2 = intent.getStringExtra(C2671x.f13223v);
                CharSequence stringExtra7 = intent.getStringExtra(C2671x.f13227z);
                CharSequence b2;
                if (intent.hasExtra(C2671x.f13225x)) {
                    r1 = intent.getIntExtra(C2671x.f13225x, 0);
                    b2 = C2475c.m14164b(r2 + r1);
                } else {
                    b2 = C2475c.m14164b(r2);
                    r1 = 0;
                    z2 = true;
                }
                if (TextUtils.isEmpty(r2) || !TextUtils.equals(stringExtra7, r0)) {
                    C2463b.m14127c("invalid notification for " + r2);
                } else if (z2) {
                    C2662r.m15086d(this, r2);
                } else {
                    C2662r.m15084b((Context) this, r2, r1);
                }
            } else if (C2671x.f13211j.equals(intent.getAction())) {
                m14945a(new ak(this, 4, intent));
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return f13082a;
    }
}
