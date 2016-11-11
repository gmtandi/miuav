package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.XMPushService.C2631b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.xiaomi.push.service.v */
public class C2669v {
    private static C2669v f13198a;
    private ConcurrentHashMap<String, HashMap<String, C2667b>> f13199b;
    private List<C2643a> f13200c;

    /* renamed from: com.xiaomi.push.service.v.a */
    public interface C2643a {
        void m14987a();
    }

    /* renamed from: com.xiaomi.push.service.v.b */
    public class C2667b {
        public String f13177a;
        public String f13178b;
        public String f13179c;
        public String f13180d;
        public boolean f13181e;
        public String f13182f;
        public String f13183g;
        public String f13184h;
        public String f13185i;
        public String f13186j;
        public C2645b f13187k;
        public Context f13188l;
        C2668c f13189m;
        private int f13190n;
        private List<C2644a> f13191o;
        private XMPushService f13192p;
        private C2631b f13193q;

        /* renamed from: com.xiaomi.push.service.v.b.a */
        public interface C2644a {
            void m14995a(C2668c c2668c, C2668c c2668c2, int i);
        }

        public C2667b(XMPushService xMPushService) {
            this.f13189m = C2668c.unbind;
            this.f13190n = 0;
            this.f13191o = new ArrayList();
            this.f13193q = new C2631b(this);
            this.f13192p = xMPushService;
            m15104a(new C2670w(this));
        }

        public long m15102a() {
            return 1000 * (((long) ((Math.random() * 20.0d) - 10.0d)) + ((long) ((this.f13190n + 1) * 15)));
        }

        public String m15103a(int i) {
            switch (i) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return "OPEN";
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return "CLOSE";
                case Type.BYTE /*3*/:
                    return "KICK";
                default:
                    return FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
            }
        }

        public void m15104a(C2644a c2644a) {
            this.f13191o.add(c2644a);
        }

        public void m15105a(C2668c c2668c, int i, int i2, String str, String str2) {
            boolean z = true;
            for (C2644a a : this.f13191o) {
                a.m14995a(this.f13189m, c2668c, i2);
            }
            if (this.f13189m != c2668c) {
                C2463b.m14123a(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", new Object[]{this.f13189m, c2668c, m15103a(i), C2671x.m15122a(i2), str, str2, this.f13184h}));
                this.f13189m = c2668c;
            }
            if (this.f13187k == null) {
                C2463b.m14127c("status changed while the client dispatcher is missing");
            } else if (i == 2) {
                this.f13187k.m15004a(this.f13188l, this, i2);
            } else if (i == 3) {
                this.f13187k.m15005a(this.f13188l, this, str2, str);
            } else if (i == 1) {
                if (c2668c != C2668c.binded) {
                    z = false;
                }
                if (!z && "wait".equals(str2)) {
                    this.f13190n++;
                } else if (z) {
                    this.f13190n = 0;
                }
                this.f13187k.m15006a(this.f13188l, this, z, i2, str);
            }
        }
    }

    /* renamed from: com.xiaomi.push.service.v.c */
    public enum C2668c {
        unbind,
        binding,
        binded
    }

    private C2669v() {
        this.f13199b = new ConcurrentHashMap();
        this.f13200c = new ArrayList();
    }

    public static synchronized C2669v m15106a() {
        C2669v c2669v;
        synchronized (C2669v.class) {
            if (f13198a == null) {
                f13198a = new C2669v();
            }
            c2669v = f13198a;
        }
        return c2669v;
    }

    private String m15107d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("@");
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    public synchronized void m15108a(Context context) {
        for (HashMap values : this.f13199b.values()) {
            for (C2667b a : values.values()) {
                a.m15105a(C2668c.unbind, 1, 3, null, null);
            }
        }
    }

    public synchronized void m15109a(Context context, int i) {
        for (HashMap values : this.f13199b.values()) {
            for (C2667b a : values.values()) {
                a.m15105a(C2668c.unbind, 2, i, null, null);
            }
        }
    }

    public synchronized void m15110a(C2643a c2643a) {
        this.f13200c.add(c2643a);
    }

    public synchronized void m15111a(C2667b c2667b) {
        HashMap hashMap = (HashMap) this.f13199b.get(c2667b.f13184h);
        if (hashMap == null) {
            hashMap = new HashMap();
            this.f13199b.put(c2667b.f13184h, hashMap);
        }
        hashMap.put(m15107d(c2667b.f13178b), c2667b);
        for (C2643a a : this.f13200c) {
            a.m14987a();
        }
    }

    public synchronized void m15112a(String str) {
        HashMap hashMap = (HashMap) this.f13199b.get(str);
        if (hashMap != null) {
            hashMap.clear();
            this.f13199b.remove(str);
        }
        for (C2643a a : this.f13200c) {
            a.m14987a();
        }
    }

    public synchronized void m15113a(String str, String str2) {
        HashMap hashMap = (HashMap) this.f13199b.get(str);
        if (hashMap != null) {
            hashMap.remove(m15107d(str2));
            if (hashMap.isEmpty()) {
                this.f13199b.remove(str);
            }
        }
        for (C2643a a : this.f13200c) {
            a.m14987a();
        }
    }

    public synchronized C2667b m15114b(String str, String str2) {
        HashMap hashMap;
        hashMap = (HashMap) this.f13199b.get(str);
        return hashMap == null ? null : (C2667b) hashMap.get(m15107d(str2));
    }

    public synchronized ArrayList<C2667b> m15115b() {
        ArrayList<C2667b> arrayList;
        arrayList = new ArrayList();
        for (HashMap values : this.f13199b.values()) {
            arrayList.addAll(values.values());
        }
        return arrayList;
    }

    public synchronized List<String> m15116b(String str) {
        List<String> arrayList;
        arrayList = new ArrayList();
        for (HashMap values : this.f13199b.values()) {
            for (C2667b c2667b : values.values()) {
                if (str.equals(c2667b.f13177a)) {
                    arrayList.add(c2667b.f13184h);
                }
            }
        }
        return arrayList;
    }

    public synchronized int m15117c() {
        return this.f13199b.size();
    }

    public synchronized Collection<C2667b> m15118c(String str) {
        return !this.f13199b.containsKey(str) ? new ArrayList() : ((HashMap) ((HashMap) this.f13199b.get(str)).clone()).values();
    }

    public synchronized void m15119d() {
        this.f13199b.clear();
    }

    public synchronized void m15120e() {
        this.f13200c.clear();
    }
}
