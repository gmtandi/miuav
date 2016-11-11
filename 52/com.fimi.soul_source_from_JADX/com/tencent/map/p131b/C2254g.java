package com.tencent.map.p131b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tencent.map.b.g */
public final class C2254g {
    private Context f11694a;
    private WifiManager f11695b;
    private C2252a f11696c;
    private Handler f11697d;
    private Runnable f11698e;
    private int f11699f;
    private C2249c f11700g;
    private C2253b f11701h;
    private boolean f11702i;
    private byte[] f11703j;

    /* renamed from: com.tencent.map.b.g.c */
    public interface C2249c {
        void m13413a(C2253b c2253b);

        void m13414b(int i);
    }

    /* renamed from: com.tencent.map.b.g.1 */
    final class C22511 implements Runnable {
        private /* synthetic */ C2254g f11688a;

        C22511(C2254g c2254g) {
            this.f11688a = c2254g;
        }

        public final void run() {
            C2254g.m13471a(this.f11688a);
        }
    }

    /* renamed from: com.tencent.map.b.g.a */
    public final class C2252a extends BroadcastReceiver {
        private int f11689a;
        private List<ScanResult> f11690b;
        private boolean f11691c;
        private /* synthetic */ C2254g f11692d;

        public C2252a(C2254g c2254g) {
            this.f11692d = c2254g;
            this.f11689a = 4;
            this.f11690b = null;
            this.f11691c = false;
        }

        private void m13468a(List<ScanResult> list) {
            if (list != null) {
                if (this.f11691c) {
                    if (this.f11690b == null) {
                        this.f11690b = new ArrayList();
                    }
                    int size = this.f11690b.size();
                    for (ScanResult scanResult : list) {
                        for (int i = 0; i < size; i++) {
                            if (((ScanResult) this.f11690b.get(i)).BSSID.equals(scanResult.BSSID)) {
                                this.f11690b.remove(i);
                                break;
                            }
                        }
                        this.f11690b.add(scanResult);
                    }
                    return;
                }
                if (this.f11690b == null) {
                    this.f11690b = new ArrayList();
                } else {
                    this.f11690b.clear();
                }
                for (ScanResult scanResult2 : list) {
                    this.f11690b.add(scanResult2);
                }
            }
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                this.f11689a = intent.getIntExtra("wifi_state", 4);
                if (this.f11692d.f11700g != null) {
                    this.f11692d.f11700g.m13414b(this.f11689a);
                }
            }
            if (intent.getAction().equals("android.net.wifi.SCAN_RESULTS") || intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                List list = null;
                if (this.f11692d.f11695b != null) {
                    list = this.f11692d.f11695b.getScanResults();
                }
                if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    if (list == null) {
                        return;
                    }
                    if (list != null && list.size() == 0) {
                        return;
                    }
                }
                if (this.f11691c || this.f11690b == null || this.f11690b.size() < 4 || list == null || list.size() > 2) {
                    m13468a(list);
                    this.f11691c = false;
                    this.f11692d.f11701h = new C2253b(this.f11692d, this.f11690b, System.currentTimeMillis(), this.f11689a);
                    if (this.f11692d.f11700g != null) {
                        this.f11692d.f11700g.m13413a(this.f11692d.f11701h);
                    }
                    this.f11692d.m13477a(((long) this.f11692d.f11699f) * 20000);
                    return;
                }
                m13468a(list);
                this.f11691c = true;
                this.f11692d.m13477a(0);
            }
        }
    }

    /* renamed from: com.tencent.map.b.g.b */
    public final class C2253b implements Cloneable {
        private List<ScanResult> f11693a;

        public C2253b(C2254g c2254g, List<ScanResult> list, long j, int i) {
            this.f11693a = null;
            if (list != null) {
                this.f11693a = new ArrayList();
                for (ScanResult add : list) {
                    this.f11693a.add(add);
                }
            }
        }

        public final List<ScanResult> m13469a() {
            return this.f11693a;
        }

        public final Object clone() {
            C2253b c2253b;
            try {
                c2253b = (C2253b) super.clone();
            } catch (Exception e) {
                c2253b = null;
            }
            if (this.f11693a != null) {
                c2253b.f11693a = new ArrayList();
                c2253b.f11693a.addAll(this.f11693a);
            }
            return c2253b;
        }
    }

    public C2254g() {
        this.f11694a = null;
        this.f11695b = null;
        this.f11696c = null;
        this.f11697d = null;
        this.f11698e = new C22511(this);
        this.f11699f = 1;
        this.f11700g = null;
        this.f11701h = null;
        this.f11702i = false;
        this.f11703j = new byte[0];
    }

    static /* synthetic */ void m13471a(C2254g c2254g) {
        if (c2254g.f11695b != null && c2254g.f11695b.isWifiEnabled()) {
            c2254g.f11695b.startScan();
        }
    }

    public final void m13476a() {
        synchronized (this.f11703j) {
            if (!this.f11702i) {
            } else if (this.f11694a == null || this.f11696c == null) {
            } else {
                try {
                    this.f11694a.unregisterReceiver(this.f11696c);
                } catch (Exception e) {
                }
                this.f11697d.removeCallbacks(this.f11698e);
                this.f11702i = false;
            }
        }
    }

    public final void m13477a(long j) {
        if (this.f11697d != null && this.f11702i) {
            this.f11697d.removeCallbacks(this.f11698e);
            this.f11697d.postDelayed(this.f11698e, j);
        }
    }

    public final boolean m13478a(Context context, C2249c c2249c, int i) {
        synchronized (this.f11703j) {
            if (this.f11702i) {
                return true;
            } else if (context == null || c2249c == null) {
                return false;
            } else {
                this.f11697d = new Handler(Looper.getMainLooper());
                this.f11694a = context;
                this.f11700g = c2249c;
                this.f11699f = 1;
                try {
                    this.f11695b = (WifiManager) this.f11694a.getSystemService("wifi");
                    IntentFilter intentFilter = new IntentFilter();
                    this.f11696c = new C2252a(this);
                    if (this.f11695b == null || this.f11696c == null) {
                        return false;
                    }
                    intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                    intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
                    this.f11694a.registerReceiver(this.f11696c, intentFilter);
                    m13477a(0);
                    this.f11702i = true;
                    return this.f11702i;
                } catch (Exception e) {
                    return false;
                }
            }
        }
    }

    public final boolean m13479b() {
        return this.f11702i;
    }

    public final boolean m13480c() {
        return (this.f11694a == null || this.f11695b == null) ? false : this.f11695b.isWifiEnabled();
    }
}
