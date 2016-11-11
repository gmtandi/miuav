package com.tencent.map.p131b;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.p054c.p055a.p063b.p068d.C0921a;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.tencent.map.b.k */
public final class C2259k {
    private static int f11715a;
    private static int f11716b;
    private static int f11717c;
    private static int f11718d;
    private static int f11719e;
    private static int f11720f;
    private static ArrayList<C2258a> f11721g;
    private static long f11722h;
    private static long f11723i;
    private static long f11724j;
    private static long f11725k;
    private static long f11726l;
    private static long f11727m;
    private static long f11728n;
    private static long f11729o;
    private static long f11730p;
    private static long f11731q;
    private static int f11732r;
    private static int f11733s;
    private static int f11734t;
    private static int f11735u;

    /* renamed from: com.tencent.map.b.k.a */
    public final class C2258a {
        public long f11707a;
        public long f11708b;
        public long f11709c;
        public long f11710d;
        public int f11711e;
        public long f11712f;
        public int f11713g;
        public int f11714h;
    }

    static {
        f11715a = C1873o.ak;
        f11716b = 15000;
        f11717c = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
        f11718d = C0921a.f4833b;
        f11719e = 25000;
        f11720f = 15000;
        f11715a = 12000;
        f11716b = C0921a.f4833b;
        f11717c = 8000;
        f11718d = C0921a.f4833b;
        f11719e = 25000;
        f11720f = 15000;
        ConnectivityManager connectivityManager = (ConnectivityManager) C2260l.m13507b().getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                if (activeNetworkInfo.isConnected() && type == 0) {
                    String subscriberId = ((TelephonyManager) C2260l.m13507b().getSystemService("phone")).getSubscriberId();
                    if (subscriberId != null && subscriberId.length() > 3 && !subscriberId.startsWith("46000") && !subscriberId.startsWith("46002")) {
                        f11715a = 15000;
                        f11716b = 25000;
                        f11717c = C1873o.ak;
                        f11718d = 25000;
                        f11719e = 35000;
                        f11720f = 15000;
                    }
                }
            }
        }
    }

    public static int m13496a() {
        int max = (f11724j <= 0 || f11725k <= 0) ? f11715a : (int) ((Math.max(f11727m, f11724j) + f11725k) - f11726l);
        ConnectivityManager connectivityManager = (ConnectivityManager) C2260l.m13507b().getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (!activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) {
                    max = f11716b;
                } else if (f11725k > 0 && f11725k < ((long) f11717c)) {
                    max = f11717c;
                }
            }
        }
        int i = (f11735u * f11717c) + max;
        if (i <= f11717c) {
            i = f11717c;
        }
        if (((long) i) <= f11725k) {
            i = (int) (f11725k + ((long) f11717c));
        }
        if (i >= f11716b) {
            i = f11716b;
        }
        C2258a b = C2259k.m13502b(Thread.currentThread().getId());
        if (b == null) {
            b = C2259k.m13497a(Thread.currentThread().getId());
        }
        if (i < b.f11713g + f11717c) {
            i = b.f11713g + f11717c;
        }
        b.f11713g = i;
        return i;
    }

    private static C2258a m13497a(long j) {
        C2258a c2258a;
        if (f11721g == null) {
            f11721g = new ArrayList();
        }
        synchronized (f11721g) {
            if (f11721g.size() > 20) {
                int size = f11721g.size();
                int i = 0;
                Object obj = null;
                int i2 = 0;
                while (i < size / 2) {
                    Object obj2;
                    int i3;
                    if (((C2258a) f11721g.get(i2)).f11712f > 0 || System.currentTimeMillis() - ((C2258a) f11721g.get(i2)).f11708b > 600000) {
                        f11721g.remove(i2);
                        obj2 = 1;
                        i3 = i2;
                    } else {
                        Object obj3 = obj;
                        i3 = i2 + 1;
                        obj2 = obj3;
                    }
                    i++;
                    i2 = i3;
                    obj = obj2;
                }
                if (obj != null) {
                    f11721g.get(0);
                    f11722h = 0;
                    f11721g.get(0);
                    f11723i = 0;
                    f11725k = ((C2258a) f11721g.get(0)).f11709c;
                    f11726l = ((C2258a) f11721g.get(0)).f11709c;
                    f11729o = ((C2258a) f11721g.get(0)).f11710d;
                    f11730p = ((C2258a) f11721g.get(0)).f11710d;
                    if (((C2258a) f11721g.get(0)).f11712f > 0) {
                        f11732r = (int) (((long) (((C2258a) f11721g.get(0)).f11711e * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) / ((C2258a) f11721g.get(0)).f11712f);
                    }
                    f11733s = f11732r;
                    Iterator it = f11721g.iterator();
                    while (it.hasNext()) {
                        c2258a = (C2258a) it.next();
                        if (0 > f11722h) {
                            f11722h = 0;
                        }
                        if (0 < f11723i) {
                            f11723i = 0;
                        }
                        if (c2258a.f11709c > f11725k) {
                            f11725k = c2258a.f11709c;
                        }
                        if (c2258a.f11709c < f11726l) {
                            f11726l = c2258a.f11709c;
                        }
                        if (c2258a.f11710d > f11729o) {
                            f11729o = c2258a.f11710d;
                        }
                        if (c2258a.f11710d < f11730p) {
                            f11730p = c2258a.f11710d;
                        }
                        if (c2258a.f11712f > 0) {
                            int i4 = (int) (((long) (c2258a.f11711e * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) / c2258a.f11712f);
                            if (i4 > f11732r) {
                                f11732r = i4;
                            }
                            if (i4 < f11733s) {
                                f11733s = i4;
                            }
                        }
                    }
                }
            }
            c2258a = new C2258a();
            c2258a.f11707a = j;
            f11721g.add(c2258a);
        }
        return c2258a;
    }

    public static void m13498a(int i) {
        C2258a b = C2259k.m13502b(Thread.currentThread().getId());
        if (b != null) {
            b.f11712f = System.currentTimeMillis() - b.f11708b;
            b.f11708b = System.currentTimeMillis();
            b.f11711e = i;
            int i2 = (int) (((long) (i * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) / (b.f11712f == 0 ? 1 : b.f11712f));
            f11734t = i2;
            f11732r = i2 > f11732r ? f11734t : f11732r;
            i2 = f11734t < f11733s ? f11734t : f11733s == 0 ? f11734t : f11733s;
            f11733s = i2;
            if (f11721g != null) {
                synchronized (f11721g) {
                    Iterator it = f11721g.iterator();
                    while (it.hasNext()) {
                        C2258a c2258a = (C2258a) it.next();
                        int i3 = c2258a.f11711e;
                        long j = c2258a.f11712f;
                    }
                }
            }
            if (f11735u > 0 && b.f11709c < ((long) f11717c) && b.f11710d < ((long) f11720f)) {
                f11735u--;
            }
            b.f11713g = (int) b.f11709c;
        }
    }

    public static void m13499a(HttpURLConnection httpURLConnection) {
        C2258a b = C2259k.m13502b(Thread.currentThread().getId());
        if (b == null) {
            b = C2259k.m13497a(Thread.currentThread().getId());
        }
        if (b != null) {
            b.f11708b = System.currentTimeMillis();
        }
    }

    public static void m13500a(boolean z) {
        if (!z) {
            f11735u++;
        }
        C2258a c = C2259k.m13503c(Thread.currentThread().getId());
        if (c != null) {
            long j = c.f11708b;
        }
    }

    public static int m13501b() {
        int max = (f11728n <= 0 || f11729o <= 0) ? f11718d : (int) ((Math.max(f11731q, f11728n) + f11729o) - f11730p);
        ConnectivityManager connectivityManager = (ConnectivityManager) C2260l.m13507b().getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (!activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) {
                    max = f11719e;
                } else if (f11729o > 0 && f11729o < ((long) f11720f)) {
                    max = f11720f;
                }
            }
        }
        int i = (f11735u * f11717c) + max;
        if (i <= f11720f) {
            i = f11720f;
        }
        if (((long) i) <= f11729o) {
            i = (int) (f11729o + ((long) f11720f));
        }
        if (i >= f11719e) {
            i = f11719e;
        }
        C2258a b = C2259k.m13502b(Thread.currentThread().getId());
        if (b != null) {
            if (i < b.f11714h + f11720f) {
                i = b.f11714h + f11720f;
            }
            if (i < b.f11713g + f11720f) {
                i = b.f11713g + f11720f;
            }
            b.f11714h = i;
        }
        return i;
    }

    private static C2258a m13502b(long j) {
        if (f11721g == null) {
            return null;
        }
        synchronized (f11721g) {
            Iterator it = f11721g.iterator();
            while (it.hasNext()) {
                C2258a c2258a = (C2258a) it.next();
                if (c2258a.f11707a == j) {
                    return c2258a;
                }
            }
            return null;
        }
    }

    private static C2258a m13503c(long j) {
        if (f11721g != null) {
            synchronized (f11721g) {
                for (int size = f11721g.size() - 1; size >= 0; size--) {
                    if (((C2258a) f11721g.get(size)).f11707a == j) {
                        C2258a c2258a = (C2258a) f11721g.remove(size);
                        return c2258a;
                    }
                }
            }
        }
        return null;
    }

    public static void m13504c() {
        C2258a b = C2259k.m13502b(Thread.currentThread().getId());
        if (b != null) {
            b.f11709c = System.currentTimeMillis() - b.f11708b;
            b.f11708b = System.currentTimeMillis();
            f11727m = b.f11709c;
            f11725k = b.f11709c > f11725k ? b.f11709c : f11725k;
            long j = b.f11709c < f11726l ? b.f11709c : f11726l == 0 ? b.f11709c : f11726l;
            f11726l = j;
            if (f11721g != null) {
                synchronized (f11721g) {
                    Iterator it = f11721g.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        C2258a c2258a = (C2258a) it.next();
                        if (c2258a.f11709c > 0) {
                            f11724j += c2258a.f11709c;
                            i++;
                        }
                    }
                    if (i > 0) {
                        f11724j /= (long) i;
                    }
                }
            }
        }
    }

    public static void m13505d() {
        C2258a b = C2259k.m13502b(Thread.currentThread().getId());
        if (b != null) {
            b.f11710d = System.currentTimeMillis() - b.f11708b;
            b.f11708b = System.currentTimeMillis();
            f11731q = b.f11710d;
            f11729o = b.f11710d > f11729o ? b.f11710d : f11729o;
            long j = b.f11710d < f11730p ? b.f11710d : f11730p == 0 ? b.f11710d : f11730p;
            f11730p = j;
            if (f11721g != null) {
                synchronized (f11721g) {
                    Iterator it = f11721g.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        C2258a c2258a = (C2258a) it.next();
                        if (c2258a.f11710d > 0) {
                            f11728n += c2258a.f11710d;
                            i++;
                        }
                    }
                    if (i > 0) {
                        f11728n /= (long) i;
                    }
                }
            }
        }
    }
}
