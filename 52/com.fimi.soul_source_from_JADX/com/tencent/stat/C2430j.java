package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.common.C2418k;
import com.tencent.stat.p136a.C2394e;
import com.tencent.stat.p136a.C2402i;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;

/* renamed from: com.tencent.stat.j */
class C2430j implements Runnable {
    private Context f12376a;
    private Map<String, Integer> f12377b;

    public C2430j(Context context, Map<String, Integer> map) {
        this.f12376a = null;
        this.f12377b = null;
        this.f12376a = context;
        if (map != null) {
            this.f12377b = map;
        }
    }

    private NetworkMonitor m14073a(String str, int i) {
        NetworkMonitor networkMonitor = new NetworkMonitor();
        Socket socket = new Socket();
        int i2 = 0;
        try {
            networkMonitor.setDomain(str);
            networkMonitor.setPort(i);
            long currentTimeMillis = System.currentTimeMillis();
            SocketAddress inetSocketAddress = new InetSocketAddress(str, i);
            socket.connect(inetSocketAddress, 30000);
            networkMonitor.setMillisecondsConsume(System.currentTimeMillis() - currentTimeMillis);
            networkMonitor.setRemoteIp(inetSocketAddress.getAddress().getHostAddress());
            if (socket != null) {
                socket.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Object th) {
                    StatService.f12248i.m13978e(th);
                }
            }
        } catch (Exception e) {
            Exception exception = e;
            i2 = -1;
            StatService.f12248i.m13977e(exception);
            if (socket != null) {
                socket.close();
            }
        } catch (Object th2) {
            StatService.f12248i.m13978e(th2);
        }
        networkMonitor.setStatusCode(i2);
        return networkMonitor;
    }

    private Map<String, Integer> m14074a() {
        Map<String, Integer> hashMap = new HashMap();
        String a = StatConfig.m13898a("__MTA_TEST_SPEED__", null);
        if (!(a == null || a.trim().length() == 0)) {
            for (String a2 : a2.split(";")) {
                String[] split = a2.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                if (split != null && split.length == 2) {
                    String str = split[0];
                    if (!(str == null || str.trim().length() == 0)) {
                        try {
                            hashMap.put(str, Integer.valueOf(Integer.valueOf(split[1]).intValue()));
                        } catch (Exception e) {
                            StatService.f12248i.m13977e(e);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    public void run() {
        try {
            if (C2418k.m14034h(this.f12376a)) {
                if (this.f12377b == null) {
                    this.f12377b = m14074a();
                }
                if (this.f12377b == null || this.f12377b.size() == 0) {
                    StatService.f12248i.m13981w("empty domain list.");
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (Entry entry : this.f12377b.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null || str.length() == 0) {
                        StatService.f12248i.m13981w("empty domain name.");
                    } else if (((Integer) entry.getValue()) == null) {
                        StatService.f12248i.m13981w("port is null for " + str);
                    } else {
                        jSONArray.put(m14073a((String) entry.getKey(), ((Integer) entry.getValue()).intValue()).toJSONObject());
                    }
                }
                if (jSONArray.length() != 0) {
                    C2394e c2402i = new C2402i(this.f12376a, StatService.m13922a(this.f12376a, false));
                    c2402i.m13956a(jSONArray.toString());
                    if (StatService.m13930c(this.f12376a) != null) {
                        StatService.m13930c(this.f12376a).post(new C2431k(c2402i));
                    }
                }
            }
        } catch (Object th) {
            StatService.f12248i.m13978e(th);
        }
    }
}
