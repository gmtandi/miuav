package com.tencent.map.p131b;

import android.net.wifi.ScanResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.tencent.map.b.c */
public final class C2234c {
    private static C2234c f11580a;
    private long f11581b;
    private List<C2232a> f11582c;
    private List<C2233b> f11583d;
    private String f11584e;

    /* renamed from: com.tencent.map.b.c.a */
    final class C2232a {
        public int f11575a;
        public int f11576b;
        public int f11577c;
        public int f11578d;

        private C2232a() {
            this.f11575a = -1;
            this.f11576b = -1;
            this.f11577c = -1;
            this.f11578d = -1;
        }
    }

    /* renamed from: com.tencent.map.b.c.b */
    final class C2233b {
        public String f11579a;

        private C2233b() {
            this.f11579a = null;
        }
    }

    public C2234c() {
        this.f11581b = 0;
        this.f11582c = new ArrayList();
        this.f11583d = new ArrayList();
    }

    public static C2234c m13372a() {
        if (f11580a == null) {
            f11580a = new C2234c();
        }
        return f11580a;
    }

    private static boolean m13373a(StringBuffer stringBuffer) {
        try {
            return new JSONObject(stringBuffer.toString()).getJSONObject("location").getDouble("accuracy") < 5000.0d;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean m13374a(List<ScanResult> list) {
        if (list == null) {
            return false;
        }
        int i;
        if (this.f11583d != null) {
            i = 0;
            for (int i2 = 0; i2 < this.f11583d.size(); i2++) {
                String str = ((C2233b) this.f11583d.get(i2)).f11579a;
                int i3 = 0;
                while (str != null && i3 < list.size()) {
                    if (str.equals(((ScanResult) list.get(i3)).BSSID)) {
                        i++;
                        break;
                    }
                    i3++;
                }
            }
        } else {
            i = 0;
        }
        int size = list.size();
        return (size < 6 || i < (size / 2) + 1) ? (size >= 6 || i < 2) ? this.f11583d.size() <= 2 && list.size() <= 2 && Math.abs(System.currentTimeMillis() - this.f11581b) <= 30000 : true : true;
    }

    public final void m13375a(int i, int i2, int i3, int i4, List<ScanResult> list) {
        this.f11581b = System.currentTimeMillis();
        this.f11584e = null;
        this.f11582c.clear();
        C2232a c2232a = new C2232a();
        c2232a.f11575a = i;
        c2232a.f11576b = i2;
        c2232a.f11577c = i3;
        c2232a.f11578d = i4;
        this.f11582c.add(c2232a);
        if (list != null) {
            this.f11583d.clear();
            for (int i5 = 0; i5 < list.size(); i5++) {
                C2233b c2233b = new C2233b();
                c2233b.f11579a = ((ScanResult) list.get(i5)).BSSID;
                int i6 = ((ScanResult) list.get(i5)).level;
                this.f11583d.add(c2233b);
            }
        }
    }

    public final void m13376a(String str) {
        this.f11584e = str;
    }

    public final String m13377b(int i, int i2, int i3, int i4, List<ScanResult> list) {
        if (this.f11584e == null || this.f11584e.length() < 10) {
            return null;
        }
        String str = this.f11584e;
        if (str == null || list == null) {
            str = null;
        } else {
            long abs = Math.abs(System.currentTimeMillis() - this.f11581b);
            if ((abs > 30000 && list.size() > 2) || ((abs > 45000 && list.size() <= 2) || !C2234c.m13373a(new StringBuffer(str)))) {
                str = null;
            }
        }
        this.f11584e = str;
        if (this.f11584e == null) {
            return null;
        }
        if (this.f11582c != null && this.f11582c.size() > 0) {
            C2232a c2232a = (C2232a) this.f11582c.get(0);
            if (c2232a.f11575a != i || c2232a.f11576b != i2 || c2232a.f11577c != i3 || c2232a.f11578d != i4) {
                return null;
            }
            if ((this.f11583d == null || this.f11583d.size() == 0) && (list == null || list.size() == 0)) {
                return this.f11584e;
            }
            if (m13374a((List) list)) {
                return this.f11584e;
            }
        }
        return m13374a((List) list) ? this.f11584e : null;
    }

    public final void m13378b() {
        this.f11584e = null;
    }
}
